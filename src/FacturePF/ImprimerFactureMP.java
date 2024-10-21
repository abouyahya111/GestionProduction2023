package FacturePF;

import groovy.lang.Sequence;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.PrintException;
import javax.swing.ButtonGroup;
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

import main.AuthentificationView;
import main.ProdLauncher;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.ConverterNumberToWords;
import util.JasperUtils;
import util.NumberUtils;
import util.Utils;
import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.ClientDAOImpl;

import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailFactureVenteMPDAOImpl;
import dao.daoImplManager.FactureVenteMPDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.StockPFDAOImpl;

import dao.daoManager.ArticlesDAO;
import dao.daoManager.ChargeFixeDAO;
import dao.daoManager.ChargeProductionDAO;
import dao.daoManager.ChargesDAO;
import dao.daoManager.ClientDAO;

import dao.daoManager.CompteurProductionDAO;
import dao.daoManager.DepotDAO;

import dao.daoManager.DetailCoutProductionDAO;
import dao.daoManager.DetailFactureVenteMPDAO;
import dao.daoManager.FactureVenteMPDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;

import dao.entity.Articles;
import dao.entity.ChargeProduction;
import dao.entity.Charges;
import dao.entity.Client;
import dao.entity.ChargeFixe;

import dao.entity.CompteurProduction;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailChargeFixe;
import dao.entity.DetailChargeVariable;

import dao.entity.DetailCoutProduction;

import dao.entity.DetailFactureVenteMP;
import dao.entity.DetailFraisDepot;
import dao.entity.DetailResponsableProd;
import dao.entity.Employe;

import dao.entity.FactureVenteMP;
import dao.entity.FicheEmploye;
import dao.entity.FraisDepot;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.Parametre;
import dao.entity.Production;
import dao.entity.StockMP;
import dao.entity.StockPF;

import dao.entity.Utilisateur;

import javax.swing.JFormattedTextField;

































import com.toedter.calendar.JDateChooser;

import java.util.Locale;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.GridBagLayout;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.JRadioButton;

import java.awt.Component;

import javax.swing.JToggleButton;
import javax.swing.JCheckBox;


public class ImprimerFactureMP extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleArticle;
	private DefaultTableModel	 modelefacture;

	private JXTable  tableArticle = new JXTable();
	private JXTable table = new JXTable();
	
	private List<DetailFactureVenteMP> listDetailFactureVenteMP =new ArrayList<DetailFactureVenteMP>();
	
	
	private List<Articles> listArticle =new ArrayList<Articles>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Depot> listparDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private List<StockPF> listStockPF =new ArrayList<StockPF>();


	
	private ImageIcon imgExcel;
	private Map< String, Articles> mapArticle = new HashMap<>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Depot> mapparDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	
	private ImageIcon imgModifierr;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private ImageIcon imgChercher;
	private ImageIcon imgImprimer;
	private ImageIcon imgSelectAll;
	private ImageIcon imgDeselectAll;
	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnRechercher;
	private Utilisateur utilisateur;
	private ChargesDAO chargedao=new ChargesDAOImpl();
	private ChargeProductionDAO chargeproductiondao;
	private ArticlesDAO articleDAO;

	ChargeProduction chargeproduction;
	private JTextField txtlibelle=new JTextField();
	JComboBox combochargefixe = new JComboBox();
	JComboBox combodepot = new JComboBox();
	private DepotDAO depotdao;
	
	 JComboBox combomagasin = new JComboBox();
	 private JDateChooser dateChooser = new JDateChooser();
	private ChargeFixe chargefixe=new ChargeFixe();
	private ChargeProduction chargeProductionTmp=new ChargeProduction();
	private  JCheckBox checkmodepaiement = new JCheckBox();
	private JTextField txttotalmontantTTC;
	private JTextField txttotalquantite;
	 JButton btnSupprimer = new JButton();
	private JTextField txtparnumfacture;
	private JRadioButton rdbtnDateFacture;
	private JDateChooser datedebut;
	private JDateChooser datefin ;
	private  JComboBox combopardepot;
	private JTextField txttotalmontantTVA;
	private JTextField txttotalmontantHT;
	private StockPFDAO stockpfDAO;
	
	private JTextField txttimbre;
	private JTextField txtnetapayer;
	
	 JComboBox comboparClient = new JComboBox();
	 private JComboBox comboClientpf;
	 private ClientDAO clientDAO;
	 private Map< String, Client> mapClient= new HashMap<>();
	 FactureVenteMPDAO factureVenteMPDAO;
	 private DetailFactureVenteMPDAO detailFactureVenteMPDAO;
		private List<FactureVenteMP> listFactureVenteMP =new ArrayList<FactureVenteMP>();
		private List<FactureVenteMP> listFactureVenteMPTmp =new ArrayList<FactureVenteMP>();
		private Map< Integer, FactureVenteMP> mapImprimer = new HashMap<>();
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ImprimerFactureMP() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1448, 1062);
      
	
        try{ 
        	
        	
        	 imgExcel=new ImageIcon(this.getClass().getResource("/img/excel.png"));
             imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
        	 imgModifierr= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
             imgValider= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
             imgChercher=new ImageIcon(this.getClass().getResource("/img/chercher.png"));
             imgImprimer=new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
             imgDeselectAll=new ImageIcon(this.getClass().getResource("/img/allDeselect.png"));
             imgSelectAll=new ImageIcon(this.getClass().getResource("/img/allSelect.png"));
            utilisateur=AuthentificationView.utilisateur;
         	depotdao=new DepotDAOImpl();


         	articleDAO=new ArticlesDAOImpl();
         	stockpfDAO=new StockPFDAOImpl();

         	factureVenteMPDAO=new FactureVenteMPDAOImpl();
         	detailFactureVenteMPDAO=new DetailFactureVenteMPDAOImpl();

         	clientDAO=new ClientDAOImpl();
         	
          } catch (Exception exp){exp.printStackTrace();}
      
       
        
       tableArticle.setModel(new DefaultTableModel(
       	new Object[][] {
       	},
       	new String[] {
       			"Code MP","MP","Fournisseur", "Prix Unitaire", "Quantite","Montant HT", "Montant TVA", "Montant TTC"
       	}
       ));
				  		
       tableArticle.setShowVerticalLines(false);
       tableArticle.setSelectionBackground(new Color(51, 204, 255));
       tableArticle.setRowHeightEnabled(true);
       tableArticle.setBackground(new Color(255, 255, 255));
       tableArticle.setHighlighters(HighlighterFactory.createSimpleStriping());
       tableArticle.setColumnControlVisible(true);
       tableArticle.setForeground(Color.BLACK);
       tableArticle.setGridColor(new Color(0, 0, 255));
       tableArticle.setAutoCreateRowSorter(true);
       tableArticle.setBounds(2, 27, 411, 198);
       tableArticle.setRowHeight(20);
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(tableArticle);
				  		     	scrollPane.setBounds(10, 424, 1262, 377);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			  		    
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Des Articles");
				  		     	titledSeparator.setBounds(10, 383, 1262, 30);
				  		     	add(titledSeparator);
		      
		     
		
		JButton buttonvalider = new JButton("Imprimer");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				mapImprimer.clear();
	    		if(!remplirMapImprimer())	{
					JOptionPane.showMessageDialog(null, "Il faut remplir les Facture à imprimer SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
				} else {
					
					boolean apercu=false;
					
					 int reponse = JOptionPane.showConfirmDialog(null, "Voulez Vous Afficher l'aperçu Avant l'impression ?", 
								"Satisfaction", JOptionPane.YES_NO_OPTION);
						 
						if(reponse == JOptionPane.YES_OPTION )		
							
						{
							apercu=true;
						}
					
					
					
					for(int k=0;k<listFactureVenteMP.size();k++)
					{
						FactureVenteMP facturepfTmp=listFactureVenteMP.get(k);
						
						if(mapImprimer.containsKey(facturepfTmp.getId()))
						{
							
							///////////////////////////////////////////////
							
			detailFactureVenteMPDAO.ViderSession();
			String sommetowords="";

			List<DetailFactureVenteMP> listDetailFacturePFImprimer =new ArrayList<DetailFactureVenteMP>();
			List<DetailFactureVenteMP> listDetailFacturePFImprimerTmp =new ArrayList<DetailFactureVenteMP>();
			   List<DetailFactureVenteMP> listDetailFacturePFTmp =new ArrayList<DetailFactureVenteMP>();
			 
			
			  
				 listDetailFacturePFTmp=detailFactureVenteMPDAO.listeDetailFactureMPByFacture(facturepfTmp.getId());
				 
			 
			    BigDecimal montanttotal=BigDecimal.ZERO;
		        BigDecimal montanttotalHT=BigDecimal.ZERO;
		        BigDecimal montanttotalTVA=BigDecimal.ZERO;
		        BigDecimal netapayer=BigDecimal.ZERO;
		        BigDecimal timber=BigDecimal.ZERO;
		        BigDecimal timberversement=BigDecimal.ZERO;
		        BigDecimal timberespece=BigDecimal.ZERO;
							boolean trouve=false;
							
							 if(listDetailFacturePFTmp.size()!=0)
							 {
								 
								 for(int i=0;i<listDetailFacturePFTmp.size();i++)
								 {
									 trouve=false;
									 
									 for(int j=0;j<listDetailFacturePFImprimer.size();j++)
									 {
										if(listDetailFacturePFImprimer.get(j).getMatierePremier().getId()== listDetailFacturePFTmp.get(i).getMatierePremier().getId() && !listDetailFacturePFImprimer.get(j).getPrixUnitaire().equals(BigDecimal.ZERO.setScale(6)) && !listDetailFacturePFTmp.get(i).getPrixUnitaire().equals(BigDecimal.ZERO.setScale(6))) 
										{
											trouve=true;
											
											listDetailFacturePFImprimer.get(j).setQuantite(listDetailFacturePFImprimer.get(j).getQuantite().add(listDetailFacturePFTmp.get(i).getQuantite()));
											listDetailFacturePFImprimer.get(j).setMontantHT(listDetailFacturePFImprimer.get(j).getMontantHT().add(listDetailFacturePFTmp.get(i).getMontantHT()));
											listDetailFacturePFImprimer.get(j).setPrixUnitaire(listDetailFacturePFImprimer.get(j).getMontantHT().divide(listDetailFacturePFImprimer.get(j).getQuantite(), 6, RoundingMode.FLOOR));

											listDetailFacturePFImprimer.get(j).setMontantTVA(listDetailFacturePFImprimer.get(j).getMontantTVA().add(listDetailFacturePFTmp.get(i).getMontantTVA()));
											listDetailFacturePFImprimer.get(j).setMontantTTC(listDetailFacturePFImprimer.get(j).getMontantTTC().add(listDetailFacturePFTmp.get(i).getMontantTTC()));
													
											listDetailFacturePFImprimer.set(j, listDetailFacturePFImprimer.get(j));
											
										}
										
										
									 }
									 if(trouve==false)
									 {
										 listDetailFacturePFImprimer.add(listDetailFacturePFTmp.get(i)); 
									 }
									  montanttotal=  montanttotal.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFTmp.get(i).getMontantTTC().setScale(6, RoundingMode.DOWN)); 
							          montanttotalHT=montanttotalHT.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFTmp.get(i).getMontantHT().setScale(6, RoundingMode.DOWN));
							          montanttotalTVA=montanttotalTVA.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFTmp.get(i).getMontantTVA().setScale(6, RoundingMode.DOWN));
									 
								 }
								 
								 if(listDetailFacturePFImprimer.size()!=0)
								 {
									 
									    montanttotal=BigDecimal.ZERO;
								         montanttotalHT=BigDecimal.ZERO;
								         montanttotalTVA=BigDecimal.ZERO;
									 
									 for(int i=0;i<listDetailFacturePFImprimer.size();i++)
									 {
										 trouve=false;
										 
										 for(int j=0;j<listDetailFacturePFImprimerTmp.size();j++)
										 {
											if(listDetailFacturePFImprimerTmp.get(j).getMatierePremier().getId() ==listDetailFacturePFImprimer.get(i).getMatierePremier().getId() && listDetailFacturePFImprimerTmp.get(j).getPrixUnitaire().setScale(2, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(2)) && listDetailFacturePFImprimer.get(i).getPrixUnitaire().setScale(2, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(2))) 
											{
												trouve=true;
												
												listDetailFacturePFImprimerTmp.get(j).setQuantite(listDetailFacturePFImprimerTmp.get(j).getQuantite().add(listDetailFacturePFImprimer.get(i).getQuantite()));
												listDetailFacturePFImprimerTmp.get(j).setPrixUnitaire((listDetailFacturePFImprimerTmp.get(j).getPrixUnitaire().add(listDetailFacturePFImprimer.get(i).getPrixUnitaire())).divide(new BigDecimal(2), 6, RoundingMode.HALF_UP));
												listDetailFacturePFImprimerTmp.get(j).setMontantHT(listDetailFacturePFImprimerTmp.get(j).getMontantHT().add(listDetailFacturePFImprimer.get(i).getMontantHT()));
												listDetailFacturePFImprimerTmp.get(j).setMontantTVA(listDetailFacturePFImprimerTmp.get(j).getMontantTVA().add(listDetailFacturePFImprimer.get(i).getMontantTVA()));
												listDetailFacturePFImprimerTmp.get(j).setMontantTTC(listDetailFacturePFImprimerTmp.get(j).getMontantTTC().add(listDetailFacturePFImprimer.get(i).getMontantTTC()));
															
												listDetailFacturePFImprimerTmp.set(j, listDetailFacturePFImprimerTmp.get(j));
												
											}
											
											
										 }
										 if(trouve==false)
										 {
											 listDetailFacturePFImprimerTmp.add(listDetailFacturePFImprimer.get(i)); 
										 }
										  montanttotal=  montanttotal.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFImprimer.get(i).getMontantTTC().setScale( 6, RoundingMode.DOWN)); 
								          montanttotalHT=montanttotalHT.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFImprimer.get(i).getMontantHT().setScale(6, RoundingMode.DOWN));
								          montanttotalTVA=montanttotalTVA.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFImprimer.get(i).getMontantTVA().setScale(6, RoundingMode.DOWN));
										 
									 }  
								 }
								 
							

							 
								 if(facturepfTmp.getModeReglement().equals(MODE_REGLEMENT_ESPECE))
								 {
									 timber=Constantes.TIMBER.divide(new BigDecimal(100), 6, RoundingMode.HALF_UP);
										timberespece=timberespece.add(timber.multiply(facturepfTmp.getMontantTTC().setScale(6, RoundingMode.HALF_UP))) ;
									 
								 }else if(facturepfTmp.getModeReglement().equals(MODE_REGLEMENT_VERSEMENT))
								 {
									 
									 timber=Constantes.TIMBER.divide(new BigDecimal(100), 6, RoundingMode.HALF_UP);
										timberversement=timberversement.add(timber.multiply(facturepfTmp.getMontantTTC().setScale(6, RoundingMode.HALF_UP))) ;
								 }
								 
								 
									
									
									
									 txttotalmontantTTC.setText(montanttotal.setScale(2, RoundingMode.HALF_UP)+"");
									netapayer=timberversement.add(timberespece).setScale(2, RoundingMode.HALF_UP).add(new BigDecimal(txttotalmontantTTC.getText()));
								   txttotalmontantHT.setText(montanttotalHT.setScale(2, RoundingMode.HALF_UP)+"");
							  			txttotalmontantTVA.setText(montanttotalTVA.setScale(2, RoundingMode.HALF_UP)+"");
							  			txttimbre.setText(timberversement.add(timberespece).setScale(2, RoundingMode.HALF_UP)+"");
							  			txtnetapayer.setText(netapayer.setScale(2, RoundingMode.HALF_UP)+"");
							  			
						  			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						  		  	
								 String dateFacture="";
						  		   
								 
								 dateFacture=dateFormat.format(facturepfTmp.getDateFacture());
								/*
								 * if(facturepfTmp.getType().equals(Constantes.TYPE_BON_LIVRAISON)) {
								 * 
								 * }else { dateFacture =facturepfTmp.getNumFacture().substring(4,
								 * 6)+"/"+facturepfTmp.getNumFacture().substring(2,
								 * 4)+"/"+20+facturepfTmp.getNumFacture().substring(0, 2); }
								 */
						  		 
									Map parameters = new HashMap();
									parameters.put("dateFacture", dateFacture);
									String totalht=String.valueOf(new BigDecimal(txttotalmontantHT.getText()));
									String totaltva=String.valueOf(new BigDecimal(txttotalmontantTVA.getText()));
									String totalttc=String.valueOf(new BigDecimal(txttotalmontantTTC.getText()));
									String timbertmp=String.valueOf(new BigDecimal(txttimbre.getText()));
									String netapayerTmp=String.valueOf(new BigDecimal(txtnetapayer.getText()));
									
									parameters.put("TotalHT", totalht);
									parameters.put("TotalTVA", totaltva);
									parameters.put("TotalTTC",totalttc);
									parameters.put("client", facturepfTmp.getClient().getNom());
									
									
										parameters.put("NumFacture", facturepfTmp.getNumFacture());
										parameters.put("type", "BL N°    :");
										
									
								
									parameters.put("adresse", facturepfTmp.getClient().getAdresse());
									parameters.put("timber",timbertmp);
									parameters.put("netapayer",netapayerTmp);
									parameters.put("code",facturepfTmp.getClient().getCode());
									


									
									
									/*if(checkmodepaiement.isSelected()==true)
									{*/
										if( facturepfTmp.getModeReglement()!=null)
										{
											
											String ModePaiement ="";
											
											
											
									
									
											
									
											
											
											
											
											parameters.put("modepaiement", facturepfTmp.getModeReglement());
											
										
												
												
												
												  if(facturepfTmp.getModeReglement().equals(Constantes.MODE_REGLEMENT_CHEQUE) && facturepfTmp.getNumPiece()!=null)
												  { 
													  parameters.put("numcheque","Cheque N° : "+ facturepfTmp.getNumPiece());
												  
												  }else if(facturepfTmp.getModeReglement().equals(Constantes.MODE_REGLEMENT_TRAITE) && facturepfTmp.getNumPiece()!=null) 
												  {
													  parameters.put("numcheque","Traite N° : "+ facturepfTmp.getNumPiece());
												  
												  }else if(facturepfTmp.getModeReglement().equals(Constantes.MODE_REGLEMENT_VERSEMENT) && facturepfTmp.getNumPiece()!=null) 
												  {
													  parameters.put("numcheque","Versement N° : "+ facturepfTmp.getNumPiece());
												  
												  }
												  
												  
												  
												  else 
												  {
													  parameters.put("numcheque", "");
													  
													  
												}
												
											
											
											
										
										}else
										{
											parameters.put("modepaiement", "");
											parameters.put("numcheque", "");
										}
								
									
									/*}else
									{
									parameters.put("modepaiement", "");
									}*/
									
									//double totalttc=Double.valueOf(txtnetapayer.getText());
									String x=txtnetapayer.getText().replace(".", ",");
									
									sommetowords= ConverterNumberToWords.converter(x);
								
									parameters.put("NumberToWords",sommetowords);
									
									
									try {
										
										if(listDetailFacturePFImprimerTmp.size()!=0)
										{
											JasperUtils.imprimerFactureVenteMP (listDetailFacturePFImprimerTmp,parameters,apercu);
											
										}else
										{
											
											JasperUtils.imprimerFactureVenteMP(listDetailFacturePFImprimer,parameters,apercu);
										}
										
									} catch (PrintException | IOException e1) {
										// TODO Auto-generated catch block
										JOptionPane.showMessageDialog(null, e1.getMessage());
									}
								
									
							 }else
							 {
								 JOptionPane.showMessageDialog(null, "Il n'existe auccun article pour cette facture ", "Erreur", JOptionPane.ERROR_MESSAGE); 
							 }
								
							
						}
						
						
					}
					
					
				}
				
				

					
			//	JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
	  		  	
				///////////////////////////////////////////////
				
				/*
				
				detailFacturePfdao.ViderSession();
String sommetowords="";

listDetailFacturePF.clear();
listDetailFacturePFImprimer.clear();
listFacturePFTmp.clear();
listDetailFacturePFImprimer =new ArrayList<DetailFacturePF>();
 listDetailFacturePF=new ArrayList<DetailFacturePF>();
 listFacturePFTmp=new ArrayList<FacturePF>();
 FacturePF facturepfTmp=listFacturePF.get(table.getSelectedRow());
 listFacturePFTmp=facturepfdao.findByNumFacture(facturepfTmp.getNumFacture(),facturepfTmp.getDepot());
	for(int k=0;k<listFacturePFTmp.size();k++)
	{
		listDetailFacturePF.addAll(listFacturePFTmp.get(k).getDetailFacturePF());
	}

				boolean trouve=false;
				 if(listDetailFacturePF.size()!=0)
				 {
					 
					 for(int i=0;i<listDetailFacturePF.size();i++)
					 {
						 trouve=false;
						 
						 for(int j=0;j<listDetailFacturePFImprimer.size();j++)
						 {
							if(listDetailFacturePFImprimer.get(j).getArticle().equals(listDetailFacturePF.get(i).getArticle()) && !listDetailFacturePFImprimer.get(j).getPrixUnitaire().equals(BigDecimal.ZERO.setScale(2)) && !listDetailFacturePF.get(i).getPrixUnitaire().equals(BigDecimal.ZERO.setScale(2))) 
							{
								trouve=true;
								
								listDetailFacturePFImprimer.get(j).setQuantite(listDetailFacturePFImprimer.get(j).getQuantite().add(listDetailFacturePF.get(i).getQuantite()));
								listDetailFacturePFImprimer.get(j).setPrixUnitaire((listDetailFacturePFImprimer.get(j).getPrixUnitaire().add(listDetailFacturePF.get(i).getPrixUnitaire())).divide(new BigDecimal(2), 6, RoundingMode.HALF_UP));
								listDetailFacturePFImprimer.get(j).setMontantHT(listDetailFacturePFImprimer.get(j).getMontantHT().add(listDetailFacturePF.get(i).getMontantHT()));
								listDetailFacturePFImprimer.get(j).setMontantTVA(listDetailFacturePFImprimer.get(j).getMontantTVA().add(listDetailFacturePF.get(i).getMontantTVA()));
								listDetailFacturePFImprimer.get(j).setMontantTTC(listDetailFacturePFImprimer.get(j).getMontantTTC().add(listDetailFacturePF.get(i).getMontantTTC()));
								listDetailFacturePFImprimer.get(j).setReduction(listDetailFacturePFImprimer.get(j).getReduction().add(listDetailFacturePF.get(i).getReduction()));			
								listDetailFacturePFImprimer.set(j, listDetailFacturePFImprimer.get(j));
								
							}
							
							 
						 }
						 if(trouve==false)
						 {
							 listDetailFacturePFImprimer.add(listDetailFacturePF.get(i)); 
						 }
						 
						 
					 }
					 
					 
			  		  	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			  		  	String dateFacture=dateFormat.format(dateChooserfacture.getDate());
			  		    FacturePF facturepf=listFacturePF.get(table.getSelectedRow());
						
						Map parameters = new HashMap();
						parameters.put("dateFacture", dateFacture);
						String[]totalht=String.format("%,f", new BigDecimal(txttotalmontantHT.getText())).split(",");
						String[]totaltva=String.format("%,f",new BigDecimal(txttotalmontantTVA.getText())).split(",");
						String[]totalttc=String.format("%,f",new BigDecimal(txttotalmontantTTC.getText())).split(",");
						String[]timber=String.format("%,f",new BigDecimal(txttimbre.getText())).split(",");
						String[]netapayer=String.format("%,f",new BigDecimal(txtnetapayer.getText())).split(",");
						
						parameters.put("TotalHT", totalht[0]+","+ totalht[1].substring(0, 2));
						parameters.put("TotalTVA", totaltva[0]+","+ totaltva[1].substring(0, 2));
						parameters.put("TotalTTC",totalttc[0]+","+ totalttc[1].substring(0, 2));
						parameters.put("client", facturepf.getClientPF().getNom());
						
						if(facturepf.getType().equals(Constantes.TRANSFERE_BL_FACTURE) ||facturepf.getType().equals(Constantes.TYPE_FACTURE))
						{
							parameters.put("NumFacture", facturepf.getNumFacture());
							parameters.put("type", "Facture N°    :");
							
						}else if(facturepf.getType().equals(Constantes.TYPE_BON_LIVRAISON))
						{
							parameters.put("NumFacture", facturepf.getNumBl());
							parameters.put("type", "BL N°    :");
						}
						
						
						parameters.put("tva", Constantes.TVA);
						if(utilisateur.getId()==1)
						{
							parameters.put("ice", Constantes.ICE_ETP);
						}else if (utilisateur.getId()==2)
						{
							parameters.put("ice", Constantes.ICE_AHLBRAHIM);
						}
						
						parameters.put("adresse", facturepf.getClientPF().getAdresse());
						parameters.put("timber",timber[0]+","+ timber[1].substring(0, 2));
						parameters.put("netapayer",netapayer[0]+","+ netapayer[1].substring(0, 2));
						parameters.put("code",facturepf.getClientPF().getCode());
						
						if(facturepf.getClientPF().getIce()!=null)
						{
							parameters.put("iceclient",facturepf.getClientPF().getIce());
						}else
						{
							parameters.put("iceclient","");
						}
						
						
						if(checkmodepaiement.isSelected()==true)
						{
							if( facturepf.getModeReglement()!=null)
							{
								parameters.put("modepaiement", facturepf.getModeReglement());
							}else
							{
								parameters.put("modepaiement", "");
							}
					
						
						}else
						{
						parameters.put("modepaiement", "");
						}
						
						//double totalttc=Double.valueOf(txtnetapayer.getText());
						String x=txtnetapayer.getText().replace(".", ",");
						
						sommetowords= ConverterNumberToWords.converter(x);
					
						parameters.put("NumberToWords",sommetowords);
						
						
						
						JasperUtils.imprimerFacturePF(listDetailFacturePFImprimer,parameters);
					
						
				 }else
				 {
					 JOptionPane.showMessageDialog(null, "Il n'existe auccun article pour cette facture ", "Erreur", JOptionPane.ERROR_MESSAGE); 
				 }
					
					
			//	JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
	  		  	
				
			*/}});
		
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(364, 812, 112, 32);
		buttonvalider.setIcon(imgImprimer);
		add(buttonvalider);
		  
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
		      
		  }else
		  {
			  Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
			  if(depot!=null)
			  {
				  combodepot.addItem(depot.getLibelle());
				
		     		mapDepot.put(depot.getLibelle(), depot);
			  }
		  }
		
		
		JLabel lblTotalMontant = new JLabel("Total Montant TTc :");
		lblTotalMontant.setBounds(992, 875, 112, 26);
		add(lblTotalMontant);
		
		txttotalmontantTTC = new JTextField();
		txttotalmontantTTC.setEditable(false);
		txttotalmontantTTC.setColumns(10);
		txttotalmontantTTC.setBounds(1114, 875, 136, 26);
		add(txttotalmontantTTC);
		
		txttotalquantite = new JTextField();
		txttotalquantite.setEditable(false);
		txttotalquantite.setColumns(10);
		txttotalquantite.setBounds(885, 818, 97, 26);
		add(txttotalquantite);
		
		JLabel lblTotalQuantite = new JLabel("Total Quantite  :");
		lblTotalQuantite.setBounds(778, 818, 97, 26);
		add(lblTotalQuantite);
		
		JLabel lblConslterLesFactures = new JLabel("           Consulter les BL par :");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(296, 11, 791, 35);
		add(lblConslterLesFactures);
		
		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1.setBounds(10, 154, 1252, 218);
		add(scrollPane_1);
		table.setSortable(false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
		
				
				if(table.getSelectedRow()!=-1)
				{
					if(listFactureVenteMP.size()!=0)
					{

					FactureVenteMP factureVenteMP=listFactureVenteMP.get(table.getSelectedRow());
					listDetailFactureVenteMP=detailFactureVenteMPDAO.listeDetailFactureMPByFacture(factureVenteMP.getId());
					afficher_tableDetailFacturePF(listDetailFactureVenteMP);
					
					
					}
					
				}
				
			}
		});
		
		
		scrollPane_1.setViewportView(table);
		table.setColumnControlVisible(true);
		table.setColumnSelectionAllowed(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Num BL", "Date BL", "Etat","Type", "Client", "Depot", "Magasin", "Montant TTC","Mode Réglement","N° Réglement","Imprimer"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(121);
		table.getColumnModel().getColumn(1).setPreferredWidth(106);
		table.getColumnModel().getColumn(2).setPreferredWidth(111);
		table.getColumnModel().getColumn(3).setPreferredWidth(110);
		table.getColumnModel().getColumn(4).setPreferredWidth(114);
		table.getColumnModel().getColumn(5).setPreferredWidth(136);
		table.getColumnModel().getColumn(6).setPreferredWidth(136);
		table.getColumnModel().getColumn(7).setPreferredWidth(136);
		table.getColumnModel().getColumn(8).setPreferredWidth(136);
		table.getColumnModel().getColumn(9).setPreferredWidth(136);
		table.getColumnModel().getColumn(10).setPreferredWidth(136);
		table.setShowVerticalLines(false);
		table.setSelectionBackground(new Color(51, 204, 255));
		table.setRowHeightEnabled(true);
		table.setRowHeight(20);
		table.setGridColor(Color.BLUE);
		table.setForeground(Color.BLACK);
		table.setBackground(Color.WHITE);
		table.setAutoCreateRowSorter(true);
		 //Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    
	    JButton btnAfficher = new JButton("Consulter");
	    btnAfficher.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {

	    	
	    		ChargerFactures();
	    	
	    	
	    	
	    	}
	    });
	    btnAfficher.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnAfficher.setBounds(524, 119, 107, 24);
	    btnAfficher.setIcon(imgChercher);
	    add(btnAfficher);
	    
	    JLabel label_2 = new JLabel("Total Montant TVA :");
	    label_2.setBounds(992, 844, 105, 26);
	    add(label_2);
	    
	    txttotalmontantTVA = new JTextField();
	    txttotalmontantTVA.setEditable(false);
	    txttotalmontantTVA.setColumns(10);
	    txttotalmontantTVA.setBounds(1114, 844, 136, 26);
	    add(txttotalmontantTVA);
	    
	    txttotalmontantHT = new JTextField();
	    txttotalmontantHT.setEditable(false);
	    txttotalmontantHT.setColumns(10);
	    txttotalmontantHT.setBounds(1114, 812, 136, 26);
	    add(txttotalmontantHT);
	    
	    JLabel label_5 = new JLabel("Total Montant HT :");
	    label_5.setBounds(992, 812, 105, 26);
	    add(label_5);
	    
	   
	    
	    JLayeredPane layeredPane_2 = new JLayeredPane();
	    layeredPane_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    layeredPane_2.setBounds(10, 57, 1428, 51);
	    add(layeredPane_2);
	    
	    JLabel lblNumFacture = new JLabel("Num BL:");
	    lblNumFacture.setBounds(10, 11, 97, 24);
	    layeredPane_2.add(lblNumFacture);
	    lblNumFacture.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	    txtparnumfacture = new JTextField();
	    txtparnumfacture.setBounds(106, 11, 117, 26);
	    layeredPane_2.add(txtparnumfacture);
	    util.Utils.copycoller(txtparnumfacture);
	    txtparnumfacture.addKeyListener(new KeyAdapter() {
	    	@Override
	    	public void keyPressed(KeyEvent e) {}
	    });
	    txtparnumfacture.setColumns(10);
	    
	    JLabel dateFacture_Du = new JLabel("Date Du:");
	    dateFacture_Du.setBounds(233, 10, 97, 24);
	    layeredPane_2.add(dateFacture_Du);
	    dateFacture_Du.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    datefin = new JDateChooser();
	     datedebut = new JDateChooser();
	     datedebut.setBounds(295, 8, 151, 26);
	     layeredPane_2.add(datedebut);
	     datedebut.addPropertyChangeListener(new PropertyChangeListener() {
	     	public void propertyChange(PropertyChangeEvent arg0) {}
	     });
	     datedebut.addKeyListener(new KeyAdapter() {
	     	@Override
	     	public void keyPressed(KeyEvent e) {
	     		
	     		
	     		
	     	}
	     });
	     datedebut.setLocale(Locale.FRANCE);
	     datedebut.setDateFormatString("dd/MM/yyyy");
	     
	     combopardepot = new JComboBox();
	     combopardepot.addItemListener(new ItemListener() {
	     	public void itemStateChanged(ItemEvent e) {
	     		

	     		

		  		

 	 			
	   	 		 if(e.getStateChange() == ItemEvent.SELECTED)
	   	 		 {
	   	 			int i=0;
	   	 		
	   	 				if(!combopardepot.getSelectedItem().equals(""))
	    			{
	    				Depot depot=mapDepot.get(combopardepot.getSelectedItem());
	    				if(depot!=null)
	    				{
	    					listMagasin=depotdao.listeMagasinByTypeMagasinDepot(depot.getId(),Constantes.MAGASIN_CODE_TYPE_DECHET_MP);
			     				if(listMagasin.size()!=0)
			     				{
			     					combomagasin.removeAllItems();
			     					combomagasin.addItem("");
			     					while(i<listMagasin.size())
	 		     				{
	 		     					Magasin magasin=listMagasin.get(i);
	 		     					combomagasin.addItem(magasin.getLibelle());
	 		     					mapMagasin.put(magasin.getLibelle(), magasin);
	 		     					i++;
	 		     				}
			     				}else
			     				{
			     					combomagasin.removeAllItems();
			     					
			     				}
			     				
			     				
			     				
			     			
			     			
			     				
			     				
			     			}else
			     			{
			     				combomagasin.removeAllItems();
			     				
			     			}
			     				
	    					
	    				}
	    				
	    				
	   	 		 }
	   	 	
		
		     		
		     		
		     	
	     		
	     	}
	     });
	     combopardepot.setBounds(709, 11, 175, 24);
	     layeredPane_2.add(combopardepot);
	     combopardepot.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		
	     		
	     		
	     		
	     	}
	     });
	     combopardepot.setSelectedIndex(-1);
	     
	     JLabel lblDepot = new JLabel("Depot  :");
	     lblDepot.setBounds(643, 10, 97, 24);
	     layeredPane_2.add(lblDepot);
	     lblDepot.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	     
	      checkmodepaiement = new JCheckBox("Imprimer avec Mode de Paiement");
	     checkmodepaiement.setFont(new Font("Tahoma", Font.BOLD, 11));
	     checkmodepaiement.setBounds(492, 813, 229, 30);
	     add(checkmodepaiement);
	     
	     JLabel lblTimbre = new JLabel("Timbre 0,25%       :");
	     lblTimbre.setBounds(992, 912, 112, 26);
	     add(lblTimbre);
	     
	     txttimbre = new JTextField();
	     txttimbre.setEditable(false);
	     txttimbre.setColumns(10);
	     txttimbre.setBounds(1114, 912, 136, 26);
	     add(txttimbre);
	     
	     JLabel lblNetAPayer = new JLabel("Net A Payer         :");
	     lblNetAPayer.setBounds(992, 951, 112, 26);
	     add(lblNetAPayer);
	     
	     txtnetapayer = new JTextField();
	     txtnetapayer.setEditable(false);
	     txtnetapayer.setColumns(10);
	     txtnetapayer.setBounds(1114, 951, 136, 26);
	     add(txtnetapayer);
	     
	     JButton button = new JButton("Initialiser");
	     button.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		
	     		txtparnumfacture.setText("");
	     		comboparClient.setSelectedItem("");;
	     		datedebut.setCalendar(null);
	     		datefin.setCalendar(null);
	     		combopardepot.setSelectedIndex(-1);
	     		
	     	}
	     });
	     button.setFont(new Font("Tahoma", Font.PLAIN, 11));
	     button.setBounds(657, 120, 106, 23);
	     add(button);
	   
			
	     if (AuthentificationView.utilisateur.getLogin().equals("admin")) {

	     listDepot =depotdao.findAll();
	     combopardepot.removeAllItems();
	     combopardepot.addItem("");

	     for(int d=0;d<listDepot.size();d++)
	     {

	     Depot depot=listDepot.get(d);
	     combopardepot.addItem(depot.getLibelle());
	     mapDepot.put(depot.getLibelle(), depot);




	     }



	     } else {
	     Depot depot = depotdao.findByCode(AuthentificationView.utilisateur.getCodeDepot());

	     if (depot != null) {
	     	combopardepot.removeAllItems();
	     	combopardepot.addItem("");
	     	combopardepot.addItem(depot.getLibelle());
	     mapDepot.put(depot.getLibelle(), depot);


	     }
	     }
	    
	    
	    datefin.setLocale(Locale.FRANCE);
	    datefin.setDateFormatString("dd/MM/yyyy");
	    datefin.setBounds(486, 8, 151, 26);
	    layeredPane_2.add(datefin);
	    
	    JLabel lblAu = new JLabel("Au");
	    lblAu.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblAu.setBounds(456, 10, 44, 24);
	    layeredPane_2.add(lblAu);
	    
	    JLabel label = new JLabel("Magasin :");
	    label.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    label.setBounds(894, 10, 56, 24);
	    layeredPane_2.add(label);
	    
	     combomagasin = new JComboBox();
	    combomagasin.addItemListener(new ItemListener() {
	    	public void itemStateChanged(ItemEvent e) {
	    	

		  		
		  		 if(e.getStateChange() == ItemEvent.SELECTED)
  	 		 {
		  			
		  			 if(combomagasin.getSelectedIndex()!=-1)
		  			 {
		  				 if(!combomagasin.getSelectedItem().equals(""))
		  				 {
		  					comboClientpf.removeAllItems();
		  					 Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
			  				List<Client>  Listeclient=clientDAO.findListClientByCodeDepot(magasin.getDepot().getCode());
			  				comboClientpf.addItem(""); 
			  				for(int i=0;i<Listeclient.size();i++)
			  				{
			  					
			  					Client client=Listeclient.get(i);
			  					mapClient.put(client.getNom(), client);
				  				comboClientpf.addItem(client.getNom()); 
			  					
			  				}
			  				
		  				 }
		  				
		  				 
		  			 }
		  			 
		  			 
  	 		 }
		  		
		  		
		  		
		  		
		  	
	    		
	    		
	    		
	    	}
	    });
	    combomagasin.setSelectedIndex(-1);
	    combomagasin.setBounds(948, 11, 183, 24);
	    layeredPane_2.add(combomagasin);
	    
	    JLabel lblClient_1 = new JLabel("Client :");
	    lblClient_1.setBounds(1152, 11, 64, 24);
	    layeredPane_2.add(lblClient_1);
	    lblClient_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	     comboClientpf = new JComboBox();
	     comboClientpf.setBounds(1207, 12, 211, 24);
	     layeredPane_2.add(comboClientpf);
	     comboClientpf.setSelectedIndex(-1);
	     
	     comboClientpf.addItem("");
	    
	    JButton btnDeslectionnerTout = new JButton();
	    btnDeslectionnerTout.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		for(int i=0;i<table.getRowCount();i++)
	     		{
	     			table.setValueAt(false, i, 7);
	     		}
	    	}
	    });
	    btnDeslectionnerTout.setToolTipText("deselectionner Tout");
	    btnDeslectionnerTout.setIcon(imgDeselectAll);
	    btnDeslectionnerTout.setBounds(1190, 119, 26, 26);
	    add(btnDeslectionnerTout);
	    
	    JButton btnSelectionnertout = new JButton();
	    btnSelectionnertout.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		for(int i=0;i<table.getRowCount();i++)
	     		{
	     			table.setValueAt(true, i, 7);
	     		}
	    		
	    	}
	    });
	    btnSelectionnertout.setToolTipText("Selectionner Tout");
	    btnSelectionnertout.setIcon(imgSelectAll);
	    btnSelectionnertout.setBounds(1219, 119, 26, 26);
	    add(btnSelectionnertout);
	    
	    JButton btnExporterToExcel = new JButton("Exporter To Excel");
	    btnExporterToExcel.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		

	    		mapImprimer.clear();
	    		if(!remplirMapImprimer())	{
					JOptionPane.showMessageDialog(null, "Il faut remplir les Facture à imprimer SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
				} else {
					
					boolean apercu=false;
					
					 int reponse = JOptionPane.showConfirmDialog(null, "Voulez Vous Afficher l'aperçu Avant l'impression ?", 
								"Satisfaction", JOptionPane.YES_NO_OPTION);
						 
						if(reponse == JOptionPane.YES_OPTION )		
							
						{
							apercu=true;
						}
					
					
					
					for(int k=0;k<listFactureVenteMP.size();k++)
					{
						FactureVenteMP facturepfTmp=listFactureVenteMP.get(k);
						
						if(mapImprimer.containsKey(facturepfTmp.getId()))
						{
							
							///////////////////////////////////////////////
							
			detailFactureVenteMPDAO.ViderSession();
			String sommetowords="";

			List<DetailFactureVenteMP> listDetailFacturePFImprimer =new ArrayList<DetailFactureVenteMP>();
			List<DetailFactureVenteMP> listDetailFacturePFImprimerTmp =new ArrayList<DetailFactureVenteMP>();
			   List<DetailFactureVenteMP> listDetailFacturePFTmp =new ArrayList<DetailFactureVenteMP>();
			 
			
			  
				 listDetailFacturePFTmp=detailFactureVenteMPDAO.listeDetailFactureMPByFacture(facturepfTmp.getId());
				 
			 
			    BigDecimal montanttotal=BigDecimal.ZERO;
		        BigDecimal montanttotalHT=BigDecimal.ZERO;
		        BigDecimal montanttotalTVA=BigDecimal.ZERO;
		        BigDecimal netapayer=BigDecimal.ZERO;
		        BigDecimal timber=BigDecimal.ZERO;
		        BigDecimal timberversement=BigDecimal.ZERO;
		        BigDecimal timberespece=BigDecimal.ZERO;
							boolean trouve=false;
							
							 if(listDetailFacturePFTmp.size()!=0)
							 {
								 
								 for(int i=0;i<listDetailFacturePFTmp.size();i++)
								 {
									 trouve=false;
									 
									 for(int j=0;j<listDetailFacturePFImprimer.size();j++)
									 {
										if(listDetailFacturePFImprimer.get(j).getMatierePremier().getId()== listDetailFacturePFTmp.get(i).getMatierePremier().getId() && !listDetailFacturePFImprimer.get(j).getPrixUnitaire().equals(BigDecimal.ZERO.setScale(6)) && !listDetailFacturePFTmp.get(i).getPrixUnitaire().equals(BigDecimal.ZERO.setScale(6))) 
										{
											trouve=true;
											
											listDetailFacturePFImprimer.get(j).setQuantite(listDetailFacturePFImprimer.get(j).getQuantite().add(listDetailFacturePFTmp.get(i).getQuantite()));
											listDetailFacturePFImprimer.get(j).setMontantHT(listDetailFacturePFImprimer.get(j).getMontantHT().add(listDetailFacturePFTmp.get(i).getMontantHT()));
											listDetailFacturePFImprimer.get(j).setPrixUnitaire(listDetailFacturePFImprimer.get(j).getMontantHT().divide(listDetailFacturePFImprimer.get(j).getQuantite(), 6, RoundingMode.FLOOR));

											listDetailFacturePFImprimer.get(j).setMontantTVA(listDetailFacturePFImprimer.get(j).getMontantTVA().add(listDetailFacturePFTmp.get(i).getMontantTVA()));
											listDetailFacturePFImprimer.get(j).setMontantTTC(listDetailFacturePFImprimer.get(j).getMontantTTC().add(listDetailFacturePFTmp.get(i).getMontantTTC()));
													
											listDetailFacturePFImprimer.set(j, listDetailFacturePFImprimer.get(j));
											
										}
										
										
									 }
									 if(trouve==false)
									 {
										 listDetailFacturePFImprimer.add(listDetailFacturePFTmp.get(i)); 
									 }
									  montanttotal=  montanttotal.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFTmp.get(i).getMontantTTC().setScale(6, RoundingMode.DOWN)); 
							          montanttotalHT=montanttotalHT.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFTmp.get(i).getMontantHT().setScale(6, RoundingMode.DOWN));
							          montanttotalTVA=montanttotalTVA.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFTmp.get(i).getMontantTVA().setScale(6, RoundingMode.DOWN));
									 
								 }
								 
								 if(listDetailFacturePFImprimer.size()!=0)
								 {
									 
									    montanttotal=BigDecimal.ZERO;
								         montanttotalHT=BigDecimal.ZERO;
								         montanttotalTVA=BigDecimal.ZERO;
									 
									 for(int i=0;i<listDetailFacturePFImprimer.size();i++)
									 {
										 trouve=false;
										 
										 for(int j=0;j<listDetailFacturePFImprimerTmp.size();j++)
										 {
											if(listDetailFacturePFImprimerTmp.get(j).getMatierePremier().getId() ==listDetailFacturePFImprimer.get(i).getMatierePremier().getId() && listDetailFacturePFImprimerTmp.get(j).getPrixUnitaire().setScale(2, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(2)) && listDetailFacturePFImprimer.get(i).getPrixUnitaire().setScale(2, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(2))) 
											{
												trouve=true;
												
												listDetailFacturePFImprimerTmp.get(j).setQuantite(listDetailFacturePFImprimerTmp.get(j).getQuantite().add(listDetailFacturePFImprimer.get(i).getQuantite()));
												listDetailFacturePFImprimerTmp.get(j).setPrixUnitaire((listDetailFacturePFImprimerTmp.get(j).getPrixUnitaire().add(listDetailFacturePFImprimer.get(i).getPrixUnitaire())).divide(new BigDecimal(2), 6, RoundingMode.HALF_UP));
												listDetailFacturePFImprimerTmp.get(j).setMontantHT(listDetailFacturePFImprimerTmp.get(j).getMontantHT().add(listDetailFacturePFImprimer.get(i).getMontantHT()));
												listDetailFacturePFImprimerTmp.get(j).setMontantTVA(listDetailFacturePFImprimerTmp.get(j).getMontantTVA().add(listDetailFacturePFImprimer.get(i).getMontantTVA()));
												listDetailFacturePFImprimerTmp.get(j).setMontantTTC(listDetailFacturePFImprimerTmp.get(j).getMontantTTC().add(listDetailFacturePFImprimer.get(i).getMontantTTC()));
															
												listDetailFacturePFImprimerTmp.set(j, listDetailFacturePFImprimerTmp.get(j));
												
											}
											
											
										 }
										 if(trouve==false)
										 {
											 listDetailFacturePFImprimerTmp.add(listDetailFacturePFImprimer.get(i)); 
										 }
										  montanttotal=  montanttotal.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFImprimer.get(i).getMontantTTC().setScale( 6, RoundingMode.DOWN)); 
								          montanttotalHT=montanttotalHT.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFImprimer.get(i).getMontantHT().setScale(6, RoundingMode.DOWN));
								          montanttotalTVA=montanttotalTVA.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFImprimer.get(i).getMontantTVA().setScale(6, RoundingMode.DOWN));
										 
									 }  
								 }
								 
							

							 
								 if(facturepfTmp.getModeReglement().equals(MODE_REGLEMENT_ESPECE))
								 {
									 timber=Constantes.TIMBER.divide(new BigDecimal(100), 6, RoundingMode.HALF_UP);
										timberespece=timberespece.add(timber.multiply(facturepfTmp.getMontantTTC().setScale(6, RoundingMode.HALF_UP))) ;
									 
								 }else if(facturepfTmp.getModeReglement().equals(MODE_REGLEMENT_VERSEMENT))
								 {
									 
									 timber=Constantes.TIMBER.divide(new BigDecimal(100), 6, RoundingMode.HALF_UP);
										timberversement=timberversement.add(timber.multiply(facturepfTmp.getMontantTTC().setScale(6, RoundingMode.HALF_UP))) ;
								 }
								 
								 
									
									
									
									 txttotalmontantTTC.setText(montanttotal.setScale(2, RoundingMode.HALF_UP)+"");
									netapayer=timberversement.add(timberespece).setScale(2, RoundingMode.HALF_UP).add(new BigDecimal(txttotalmontantTTC.getText()));
								   txttotalmontantHT.setText(montanttotalHT.setScale(2, RoundingMode.HALF_UP)+"");
							  			txttotalmontantTVA.setText(montanttotalTVA.setScale(2, RoundingMode.HALF_UP)+"");
							  			txttimbre.setText(timberversement.add(timberespece).setScale(2, RoundingMode.HALF_UP)+"");
							  			txtnetapayer.setText(netapayer.setScale(2, RoundingMode.HALF_UP)+"");
							  			
						  			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						  		  	
								 String dateFacture="";
						  		   
								 
								 dateFacture=dateFormat.format(facturepfTmp.getDateFacture());
								/*
								 * if(facturepfTmp.getType().equals(Constantes.TYPE_BON_LIVRAISON)) {
								 * 
								 * }else { dateFacture =facturepfTmp.getNumFacture().substring(4,
								 * 6)+"/"+facturepfTmp.getNumFacture().substring(2,
								 * 4)+"/"+20+facturepfTmp.getNumFacture().substring(0, 2); }
								 */
						  		 
									Map parameters = new HashMap();
									parameters.put("dateFacture", dateFacture);
									String totalht=String.valueOf(new BigDecimal(txttotalmontantHT.getText()));
									String totaltva=String.valueOf(new BigDecimal(txttotalmontantTVA.getText()));
									String totalttc=String.valueOf(new BigDecimal(txttotalmontantTTC.getText()));
									String timbertmp=String.valueOf(new BigDecimal(txttimbre.getText()));
									String netapayerTmp=String.valueOf(new BigDecimal(txtnetapayer.getText()));
									
									parameters.put("TotalHT", totalht);
									parameters.put("TotalTVA", totaltva);
									parameters.put("TotalTTC",totalttc);
									parameters.put("client", facturepfTmp.getClient().getNom());
									
									
										parameters.put("NumFacture", facturepfTmp.getNumFacture());
										parameters.put("type", "Facture N°    :");
										
									//parameters.put("tva", Constantes.TVA);
								
									parameters.put("adresse", facturepfTmp.getClient().getAdresse());
									parameters.put("timber",timbertmp);
									parameters.put("netapayer",netapayerTmp);
									parameters.put("code",facturepfTmp.getClient().getCode());
									


									
									
									/*if(checkmodepaiement.isSelected()==true)
									{*/
										if( facturepfTmp.getModeReglement()!=null)
										{
											
											String ModePaiement ="";
											
											
											
									
									
											
									
											
											
											
											
											parameters.put("modepaiement", facturepfTmp.getModeReglement());
											
										
												
												
												
												  if(facturepfTmp.getModeReglement().equals(Constantes.MODE_REGLEMENT_CHEQUE) && facturepfTmp.getNumPiece()!=null)
												  { 
													  parameters.put("numcheque","Cheque N° : "+ facturepfTmp.getNumPiece());
												  
												  }else if(facturepfTmp.getModeReglement().equals(Constantes.MODE_REGLEMENT_TRAITE) && facturepfTmp.getNumPiece()!=null) 
												  {
													  parameters.put("numcheque","Traite N° : "+ facturepfTmp.getNumPiece());
												  
												  }else if(facturepfTmp.getModeReglement().equals(Constantes.MODE_REGLEMENT_VERSEMENT) && facturepfTmp.getNumPiece()!=null) 
												  {
													  parameters.put("numcheque","Versement N° : "+ facturepfTmp.getNumPiece());
												  
												  }
												  
												  
												  
												  else 
												  {
													  parameters.put("numcheque", "");
													  
													  
												}
												
											
											
											
										
										}else
										{
											parameters.put("modepaiement", "");
											parameters.put("numcheque", "");
										}
								
									
									/*}else
									{
									parameters.put("modepaiement", "");
									}*/
									
									//double totalttc=Double.valueOf(txtnetapayer.getText());
									String x=txtnetapayer.getText().replace(".", ",");
									
									sommetowords= ConverterNumberToWords.converter(x);
								
									parameters.put("NumberToWords",sommetowords);;
									
									
									try {
										
										if(listDetailFacturePFImprimerTmp.size()!=0)
										{
											JasperUtils.ExporterFactureVenteMPToExcel(listDetailFacturePFImprimerTmp,parameters);
											
										}else
										{
											
											JasperUtils.ExporterFactureVenteMPToExcel(listDetailFacturePFImprimer,parameters );
										}
										
									} catch (PrintException | IOException e1) {
										// TODO Auto-generated catch block
										JOptionPane.showMessageDialog(null, e1.getMessage());
									}
								
									
							 }else
							 {
								 JOptionPane.showMessageDialog(null, "Il n'existe auccun article pour cette facture ", "Erreur", JOptionPane.ERROR_MESSAGE); 
							 }
								
							
						}
						
						
					}
					
					
				}
				
				

					
			//	JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
	  		  	
				///////////////////////////////////////////////
				
				/*
				
				detailFacturePfdao.ViderSession();
String sommetowords="";

listDetailFacturePF.clear();
listDetailFacturePFImprimer.clear();
listFacturePFTmp.clear();
listDetailFacturePFImprimer =new ArrayList<DetailFacturePF>();
 listDetailFacturePF=new ArrayList<DetailFacturePF>();
 listFacturePFTmp=new ArrayList<FacturePF>();
 FacturePF facturepfTmp=listFacturePF.get(table.getSelectedRow());
 listFacturePFTmp=facturepfdao.findByNumFacture(facturepfTmp.getNumFacture(),facturepfTmp.getDepot());
	for(int k=0;k<listFacturePFTmp.size();k++)
	{
		listDetailFacturePF.addAll(listFacturePFTmp.get(k).getDetailFacturePF());
	}

				boolean trouve=false;
				 if(listDetailFacturePF.size()!=0)
				 {
					 
					 for(int i=0;i<listDetailFacturePF.size();i++)
					 {
						 trouve=false;
						 
						 for(int j=0;j<listDetailFacturePFImprimer.size();j++)
						 {
							if(listDetailFacturePFImprimer.get(j).getArticle().equals(listDetailFacturePF.get(i).getArticle()) && !listDetailFacturePFImprimer.get(j).getPrixUnitaire().equals(BigDecimal.ZERO.setScale(2)) && !listDetailFacturePF.get(i).getPrixUnitaire().equals(BigDecimal.ZERO.setScale(2))) 
							{
								trouve=true;
								
								listDetailFacturePFImprimer.get(j).setQuantite(listDetailFacturePFImprimer.get(j).getQuantite().add(listDetailFacturePF.get(i).getQuantite()));
								listDetailFacturePFImprimer.get(j).setPrixUnitaire((listDetailFacturePFImprimer.get(j).getPrixUnitaire().add(listDetailFacturePF.get(i).getPrixUnitaire())).divide(new BigDecimal(2), 6, RoundingMode.HALF_UP));
								listDetailFacturePFImprimer.get(j).setMontantHT(listDetailFacturePFImprimer.get(j).getMontantHT().add(listDetailFacturePF.get(i).getMontantHT()));
								listDetailFacturePFImprimer.get(j).setMontantTVA(listDetailFacturePFImprimer.get(j).getMontantTVA().add(listDetailFacturePF.get(i).getMontantTVA()));
								listDetailFacturePFImprimer.get(j).setMontantTTC(listDetailFacturePFImprimer.get(j).getMontantTTC().add(listDetailFacturePF.get(i).getMontantTTC()));
								listDetailFacturePFImprimer.get(j).setReduction(listDetailFacturePFImprimer.get(j).getReduction().add(listDetailFacturePF.get(i).getReduction()));			
								listDetailFacturePFImprimer.set(j, listDetailFacturePFImprimer.get(j));
								
							}
							
							 
						 }
						 if(trouve==false)
						 {
							 listDetailFacturePFImprimer.add(listDetailFacturePF.get(i)); 
						 }
						 
						 
					 }
					 
					 
			  		  	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			  		  	String dateFacture=dateFormat.format(dateChooserfacture.getDate());
			  		    FacturePF facturepf=listFacturePF.get(table.getSelectedRow());
						
						Map parameters = new HashMap();
						parameters.put("dateFacture", dateFacture);
						String[]totalht=String.format("%,f", new BigDecimal(txttotalmontantHT.getText())).split(",");
						String[]totaltva=String.format("%,f",new BigDecimal(txttotalmontantTVA.getText())).split(",");
						String[]totalttc=String.format("%,f",new BigDecimal(txttotalmontantTTC.getText())).split(",");
						String[]timber=String.format("%,f",new BigDecimal(txttimbre.getText())).split(",");
						String[]netapayer=String.format("%,f",new BigDecimal(txtnetapayer.getText())).split(",");
						
						parameters.put("TotalHT", totalht[0]+","+ totalht[1].substring(0, 2));
						parameters.put("TotalTVA", totaltva[0]+","+ totaltva[1].substring(0, 2));
						parameters.put("TotalTTC",totalttc[0]+","+ totalttc[1].substring(0, 2));
						parameters.put("client", facturepf.getClientPF().getNom());
						
						if(facturepf.getType().equals(Constantes.TRANSFERE_BL_FACTURE) ||facturepf.getType().equals(Constantes.TYPE_FACTURE))
						{
							parameters.put("NumFacture", facturepf.getNumFacture());
							parameters.put("type", "Facture N°    :");
							
						}else if(facturepf.getType().equals(Constantes.TYPE_BON_LIVRAISON))
						{
							parameters.put("NumFacture", facturepf.getNumBl());
							parameters.put("type", "BL N°    :");
						}
						
						
						parameters.put("tva", Constantes.TVA);
						if(utilisateur.getId()==1)
						{
							parameters.put("ice", Constantes.ICE_ETP);
						}else if (utilisateur.getId()==2)
						{
							parameters.put("ice", Constantes.ICE_AHLBRAHIM);
						}
						
						parameters.put("adresse", facturepf.getClientPF().getAdresse());
						parameters.put("timber",timber[0]+","+ timber[1].substring(0, 2));
						parameters.put("netapayer",netapayer[0]+","+ netapayer[1].substring(0, 2));
						parameters.put("code",facturepf.getClientPF().getCode());
						
						if(facturepf.getClientPF().getIce()!=null)
						{
							parameters.put("iceclient",facturepf.getClientPF().getIce());
						}else
						{
							parameters.put("iceclient","");
						}
						
						
						if(checkmodepaiement.isSelected()==true)
						{
							if( facturepf.getModeReglement()!=null)
							{
								parameters.put("modepaiement", facturepf.getModeReglement());
							}else
							{
								parameters.put("modepaiement", "");
							}
					
						
						}else
						{
						parameters.put("modepaiement", "");
						}
						
						//double totalttc=Double.valueOf(txtnetapayer.getText());
						String x=txtnetapayer.getText().replace(".", ",");
						
						sommetowords= ConverterNumberToWords.converter(x);
					
						parameters.put("NumberToWords",sommetowords);
						
						
						
						JasperUtils.imprimerFacturePF(listDetailFacturePFImprimer,parameters);
					
						
				 }else
				 {
					 JOptionPane.showMessageDialog(null, "Il n'existe auccun article pour cette facture ", "Erreur", JOptionPane.ERROR_MESSAGE); 
				 }
					
					
			//	JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
	  		  	
				
			*/
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    	}
	    });
	    btnExporterToExcel.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnExporterToExcel.setBounds(171, 817, 159, 32);
	    btnExporterToExcel.setIcon(imgExcel);
	    add(btnExporterToExcel);


	    
	    
		
		}
	
	boolean remplirMapImprimer(){
		boolean trouve=false;
		int i=0;
				
		for(int j=0;j<table.getRowCount();j++){
			
			boolean imprimer=(boolean) table.getValueAt(j, 9);
			if(imprimer==true ){
				FactureVenteMP facturePF=listFactureVenteMP.get(j);
				
				mapImprimer.put(facturePF.getId(), facturePF);
				i++;
				trouve=true;
			}
			
		}
		return trouve;
	}

	
	
	void initialiserFacture()
	{


		combodepot.setSelectedIndex(-1);
		combomagasin.removeAllItems();
		combomagasin.setSelectedIndex(-1);
		comboClientpf.setSelectedIndex(-1);
		txttotalmontantTTC.setText("");
		txttotalquantite.setText("");
		txttotalmontantHT.setText("");
		txttotalmontantTVA.setText("");
	}


	
	
	
	
	void InitialiseTableau()
	{
		modeleArticle =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Code MP","MP","Fournisseur", "Prix Unitaire", "Quantite","Montant HT", "Montant TVA", "Montant TTC"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableArticle.setModel(modeleArticle);
		 tableArticle.getColumnModel().getColumn(0).setPreferredWidth(198);
	       tableArticle.getColumnModel().getColumn(1).setPreferredWidth(87);
	       tableArticle.getColumnModel().getColumn(2).setPreferredWidth(94);
		
	
}
	
	
	void InitialiseTableauFacture()
	{
		modelefacture =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Num BL", "Date BL", "Etat","Type", "Client", "Depot", "Magasin", "Montant TTC","Imprimer"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false,true
				};
				Class[] columnTypes = new Class[] {
						String.class,Date.class,String.class,String.class,String.class,String.class,String.class ,BigDecimal.class, Boolean.class
					};
				  	
		       public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		table.setModel(modelefacture);
		table.getColumnModel().getColumn(0).setPreferredWidth(121);
		table.getColumnModel().getColumn(1).setPreferredWidth(106);
		table.getColumnModel().getColumn(2).setPreferredWidth(111);
		table.getColumnModel().getColumn(3).setPreferredWidth(110);
		table.getColumnModel().getColumn(4).setPreferredWidth(114);
		table.getColumnModel().getColumn(5).setPreferredWidth(136);
		table.getColumnModel().getColumn(6).setPreferredWidth(136);
		table.getColumnModel().getColumn(7).setPreferredWidth(136);
		table.getColumnModel().getColumn(8).setPreferredWidth(136);
		
	
}
	
	
	
	void afficher_tableDetailFacturePF(List<DetailFactureVenteMP> listDetailFacture)
	{
		
		
		BigDecimal MontantHT=BigDecimal.ZERO;
		BigDecimal MontantTVA=BigDecimal.ZERO;
		BigDecimal MontantTTC=BigDecimal.ZERO;
		
		
		modeleArticle =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Code MP","MP","Fournisseur", "Prix Unitaire", "Quantite","Montant HT", "Montant TVA", "Montant TTC"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableArticle.setModel(modeleArticle);
		int i=0;
		 
		while(i<listDetailFacture.size())
		{	
		DetailFactureVenteMP detailfacturepf=listDetailFacture.get(i);
		String fournisseur="";
		if(detailfacturepf.getFournisseurMP()!=null)
		{
			fournisseur=detailfacturepf.getFournisseurMP().getCodeFournisseur();
		}
		
		MontantHT=MontantHT.add(detailfacturepf.getMontantHT().setScale(6, RoundingMode.HALF_UP));
		MontantTVA=MontantTVA.add(detailfacturepf.getMontantTVA().setScale(6, RoundingMode.HALF_UP));
		MontantTTC=MontantTTC.add(detailfacturepf.getMontantTTC().setScale(6, RoundingMode.HALF_UP));
			
			Object []ligne={detailfacturepf.getMatierePremier().getCode(), detailfacturepf.getMatierePremier().getNom(),fournisseur, detailfacturepf.getPrixUnitaire(),NumberFormat.getNumberInstance(Locale.FRANCE).format(detailfacturepf.getQuantite()),NumberFormat.getNumberInstance(Locale.FRANCE).format(detailfacturepf.getMontantHT()),NumberFormat.getNumberInstance(Locale.FRANCE).format(detailfacturepf.getMontantTVA()),NumberFormat.getNumberInstance(Locale.FRANCE).format(detailfacturepf.getMontantTTC())};

			modeleArticle.addRow(ligne);
			i++;
		}
		
		FactureVenteMP factureVenteMP=listFactureVenteMP.get(table.getSelectedRow());
		
		txttotalmontantHT.setText(NumberFormat.getNumberInstance(Locale.FRANCE).format(MontantHT)+"");
		txttotalmontantTVA.setText(NumberFormat.getNumberInstance(Locale.FRANCE).format(MontantTVA)+"");
		txttotalmontantTTC.setText(NumberFormat.getNumberInstance(Locale.FRANCE).format(MontantTTC)+"");
		if(factureVenteMP.getModeReglement().equals(MODE_REGLEMENT_ESPECE))
		{
			txttimbre.setText(NumberFormat.getNumberInstance(Locale.FRANCE).format(MontantTTC.multiply(TIMBER).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))+"");
		}else
		{
			txttimbre.setText(BigDecimal.ZERO+"");
		}
		
		
		txtnetapayer.setText(NumberFormat.getNumberInstance(Locale.FRANCE).format(new BigDecimal(txttotalmontantTTC.getText()).add(new BigDecimal(txttimbre.getText())))+"");
		
		
		
		
		
}
	
	
	void afficher_tableFacturePF(List<FactureVenteMP> listFacture)
	{
		modelefacture =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Num BL", "Date BL", "Etat", "Client", "Depot", "Magasin", "Montant TTC","Mode Réglement","N° Réglement","Imprimer"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false,false,true
				};
				
				Class[] columnTypes = new Class[] {
						String.class,Date.class,String.class,String.class,String.class,String.class ,BigDecimal.class,String.class ,BigDecimal.class, Boolean.class
					};
				  	
		       public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		table.setModel(modelefacture);
		int i=0;
		BigDecimal MontantTTC=BigDecimal.ZERO;
		 String ModeReglement="";
		 String NumReglement="";
		while(i<listFacture.size())
		{	
			
		FactureVenteMP facturepf=listFacture.get(i);
		
		Date datefacture=facturepf.getDateFacture();
	
		
		if(facturepf.getModeReglement()!=null)
		{
			ModeReglement=facturepf.getModeReglement();
		}
		
		if(facturepf.getNumPiece()!=null)
		{
			NumReglement=facturepf.getNumPiece();
		}
	
			
			Object []ligne={facturepf.getNumFacture(),datefacture,facturepf.getEtat(),facturepf.getClient().getNom(),facturepf.getMagasin().getDepot().getLibelle(),facturepf.getMagasin().getLibelle(),NumberFormat.getNumberInstance(Locale.FRANCE).format(facturepf.getMontantTTC()),ModeReglement,NumReglement,false};

			modelefacture.addRow(ligne);
			i++;
		}
}
	
	
	public void ChargerFactures()
	{

factureVenteMPDAO.ViderSession();
    	
    	String requete="";
    	
    	SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
    	
    	String DateDebut="";
    	String DateFin="";
    	
    	Depot depot=mapDepot.get(combopardepot.getSelectedItem());
    	Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
    	if(depot==null)
    	{
    		
    		JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Depot SVP","Erreur",JOptionPane.ERROR_MESSAGE);
    		return;
    	}else if (magasin==null){
    		
    		JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Magasin SVP","Erreur",JOptionPane.ERROR_MESSAGE);
    		return;
    	}else
    		
    	{
    		
    		
    		requete=requete+" magasin.id='"+magasin.getId()+"' ";	
    		
    	if(!txtparnumfacture.getText().equals(""))	
    	{
    		requete=requete+" and numFacture='"+txtparnumfacture.getText()+"' ";	
    	}
    		
    	Client client=mapClient.get(comboClientpf.getSelectedItem())	;
    	if(client!=null)
    	{
    		
    		requete=requete+" and client.id='"+client.getId()+"' ";	
    		
    	}
    		
    		if(datedebut.getDate()!=null && datefin.getDate()!=null)
    		{
    			
    			DateDebut=dt.format(datedebut.getDate());
    			DateFin=dt.format(datefin.getDate());
    			
    			requete=requete+" and dateFacture between '"+DateDebut+"' and  '"+DateFin+"' ";	
    			
    		}
    		
    		if(datedebut.getDate()!=null && datefin.getDate()==null)
    		{
    			DateDebut=dt.format(datedebut.getDate());
    			requete=requete+" and dateFacture ='"+DateDebut+"'";	
    			
    		}
    		
    		if(datedebut.getDate()==null && datefin.getDate()!=null)
    		{
    			DateFin=dt.format(datefin.getDate());
    			requete=requete+" and dateFacture ='"+DateFin+"'";	
    			
    		}
    		
    		listFactureVenteMP=factureVenteMPDAO.findByRequete(requete);
    		
    		afficher_tableFacturePF(listFactureVenteMP);
    		
    		
    		
    	}
    	
    	
    	
    	
    	
    	
	}
	}



package presentation.stockMP;

import groovy.lang.Sequence;

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
import java.util.Map;

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

import org.hibernate.Session;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.ConverterNumberToWords;
import util.HibernateUtil;
import util.JasperUtils;
import util.NumberUtils;
import util.Utils;
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailMouvementStockDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.MouvementStockGlobalDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.ChargeFixeDAO;
import dao.daoManager.ChargeProductionDAO;
import dao.daoManager.ChargesDAO;
import dao.daoManager.CompteurProductionDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailCoutProductionDAO;
import dao.daoManager.DetailMouvementStockDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.MouvementStockGlobalDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.entity.Articles;
import dao.entity.ChargeProduction;
import dao.entity.Charges;
import dao.entity.ChargeFixe;
import dao.entity.CompteurProduction;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailChargeFixe;
import dao.entity.DetailChargeVariable;
import dao.entity.DetailCoutProduction;
import dao.entity.DetailFraisDepot;
import dao.entity.DetailMouvementStock;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.EtatStockMP;
import dao.entity.FicheEmploye;
import dao.entity.FraisDepot;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.MouvementStockGlobal;
import dao.entity.MouvementStockProduitsFini;
import dao.entity.Parametre;
import dao.entity.Production;
import dao.entity.StockMP;
import dao.entity.StockPF;
import dao.entity.TransferStockMP;
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
import javax.swing.JTable;


public class ConsulterMouvementStock extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleDetailMouvementStock;
	private DefaultTableModel	 modeleMouvementStock;
	
	private DefaultTableModel	 modeleMP;
	

	private JXTable  tableMP = new JXTable();
	
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Depot> listparDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private List<DetailTransferStockMP> listDetailTransferStockMP =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPGroupebyMP =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPBytypetransfer =new ArrayList<DetailTransferStockMP>();
	private List<DetailMouvementStock> listMouvementStockMP =new ArrayList<DetailMouvementStock>();
	private List<DetailTransferStockMP> listDetailTransferStockMPAllMP =new ArrayList<DetailTransferStockMP>();
	
	private List<DetailTransferStockMP> listDetailTransferStockMPMouvementStkMP =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPGroupebyMPMouvementStkMP =new ArrayList<DetailTransferStockMP>();
	private List<DetailMouvementStock> listMouvementStockMPAfficher =new ArrayList<DetailMouvementStock>();
	private List<DetailMouvementStock> listMouvementStockMPAfficherTmp =new ArrayList<DetailMouvementStock>();
	
	private List<MatierePremier> listMP =new ArrayList<MatierePremier>();
	private List<EtatStockMP> listEtatStockMP =new ArrayList<EtatStockMP>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Depot> mapparDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();

	private Map< String, MatierePremier> mapMP= new HashMap<>();
	private Map< String, MatierePremier> mapCodeMP= new HashMap<>();
	
	private ImageIcon imgModifierr;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private ImageIcon imgChercher;
	private ImageIcon imgImprimer;
	private ImageIcon imgExcel;
	 JComboBox combomp = new JComboBox();
	 private JComboBox comboperation;
	
	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnRechercher;
	private Utilisateur utilisateur;
private MouvementStockGlobalDAO mouvementStockGlobaleDAO;
private DetailMouvementStockDAO detailMouvementStockDAO ;

private DetailTransferMPDAO detailTransferStockMPDAO;
private TransferStockMPDAO transferStockMPDAO;
	private JTextField txtlibelle=new JTextField();
	
	private ProductionDAO productionDAO;
	private DepotDAO depotdao;
	 JDateChooser dateChooserdebut = new JDateChooser();
	 JDateChooser dateChooserfin = new JDateChooser();
	 private JDateChooser dateChooser = new JDateChooser();
	 JComboBox combodepot = new JComboBox();
	 JButton btnSupprimer = new JButton();
	private JRadioButton rdbtnDateFacture;
	private StockPFDAO stockpfDAO;
	
	private MatierePremiereDAO MatierPremiereDAO;
	String titre="";
	private JTable tableMouvementStockMP;
	 JComboBox combomagasin = new JComboBox();
	
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ConsulterMouvementStock() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1485, 1137);
      
	
        try{ 
        	
        	
        	
             imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
        	 imgModifierr= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
             imgValider= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
             imgChercher=new ImageIcon(this.getClass().getResource("/img/chercher.png"));
             imgImprimer=new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
            utilisateur=AuthentificationView.utilisateur;
         	depotdao= new DepotDAOImpl();
         	productionDAO= new ProductionDAOImpl();
         	mouvementStockGlobaleDAO= new MouvementStockGlobalDAOImpl();
         	MatierPremiereDAO= new MatierePremierDAOImpl();
         	detailTransferStockMPDAO= new DetailTransferMPDAOImpl();
         	transferStockMPDAO=new TransferStockMPDAOImpl();
         listMP=MatierPremiereDAO.findAll();
         detailMouvementStockDAO= new DetailMouvementStockDAOImpl();
          } catch (Exception exp){exp.printStackTrace();}
       tableMP.addMouseListener(new MouseAdapter() {
       	@Override
       	public void mouseClicked(MouseEvent arg0) {
       		
       		
       		if(tableMP.getSelectedRow()!=-1)
       		{
       			
       			if(!tableMP.getValueAt(tableMP.getSelectedRow(), 1).equals(0))
       			{
       				SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/YYYY");
       				EtatStockMP etatstockmp=listEtatStockMP.get(tableMP.getSelectedRow());
       				MatierePremier mp=etatstockmp.getMp();
       				listMouvementStockMP.clear();
       				Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
       				listDetailTransferStockMPMouvementStkMP.clear();
    	    		listDetailTransferStockMPGroupebyMPMouvementStkMP.clear();
       				
    	    		listDetailTransferStockMPMouvementStkMP=detailTransferStockMPDAO.findAllTransferStockMPOrderByDateTransfer(magasin);
    	    		listDetailTransferStockMPGroupebyMPMouvementStkMP=detailTransferStockMPDAO.findAllTransferStockMPGroupeByDateTransferByMP(magasin);
    	    		BigDecimal QuantiteTotal=BigDecimal.ZERO;
       				Magasin magasinDestination=null;
    	    		/*for(int i=0;i<listDetailTransferStockMPGroupebyMPMouvementStkMP.size();i++)
		    		{*/
    	    			
    	    			
    	    			
    	    			for(int j=0;j<listDetailTransferStockMPMouvementStkMP.size();j++)
		    			{
    	    				QuantiteTotal=new BigDecimal(0);
    	    			/*	if(listDetailTransferStockMPGroupebyMPMouvementStkMP.get(i).getTransferStockMP().getDateTransfer().equals(listDetailTransferStockMPMouvementStkMP.get(j).getTransferStockMP().getDateTransfer()) 
			    					&& listDetailTransferStockMPGroupebyMPMouvementStkMP.get(i).getMatierePremier().equals(listDetailTransferStockMPMouvementStkMP.get(j).getMatierePremier()) && listDetailTransferStockMPMouvementStkMP.get(j).getTransferStockMP().getStatut().equals(comboperation.getSelectedItem()))	
			    			{*/
    	    					if(listDetailTransferStockMPMouvementStkMP.get(j).getQuantite()!=null)
    	    					{
    	    						 if(listDetailTransferStockMPMouvementStkMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMPMouvementStkMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMPMouvementStkMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMPMouvementStkMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMPMouvementStkMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
                                     {
        	    						 QuantiteTotal=QuantiteTotal.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPMouvementStkMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING));
                                      }else
                                      {
                                    	  QuantiteTotal=QuantiteTotal.add(listDetailTransferStockMPMouvementStkMP.get(j).getQuantite());
                                      }
    	    					}
    	    					
    	    					 magasinDestination=listDetailTransferStockMPMouvementStkMP.get(j).getMagasinDestination();
    	    					
			    			/*}*/
    	    				
    	    			/*	
		    			}*/
    	    			
    	    			
    	    			
    	    			if(comboperation.getSelectedItem().equals(Constantes.ETAT_TRANSFER_STOCK_AJOUT) && listDetailTransferStockMPMouvementStkMP.get(j).getTransferStockMP().getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_AJOUT))
    	    			{
    	    				DetailMouvementStock mouvementstockMP=new DetailMouvementStock();
        	    			mouvementstockMP.setDateCreation(listDetailTransferStockMPMouvementStkMP.get(j).getTransferStockMP().getDateTransfer());
        	    			mouvementstockMP.setMatierePremier(listDetailTransferStockMPMouvementStkMP.get(j).getMatierePremier());
        	    			mouvementstockMP.setNumOF(listDetailTransferStockMPMouvementStkMP.get(j).getTransferStockMP().getCodeTransfer());
    	    				mouvementstockMP.setReception(QuantiteTotal);
    	    				listMouvementStockMP.add(mouvementstockMP);
    	    				
    	    			}else if(comboperation.getSelectedItem().equals(Constantes.ETAT_TRANSFER_STOCK_ENTRE) && listDetailTransferStockMPMouvementStkMP.get(j).getTransferStockMP().getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_ENTRE))
    	    			{
    	    				DetailMouvementStock mouvementstockMP=new DetailMouvementStock();
        	    			mouvementstockMP.setDateCreation(listDetailTransferStockMPMouvementStkMP.get(j).getTransferStockMP().getDateTransfer());
        	    			mouvementstockMP.setMatierePremier(listDetailTransferStockMPMouvementStkMP.get(j).getMatierePremier());
        	    			mouvementstockMP.setNumOF(listDetailTransferStockMPMouvementStkMP.get(j).getTransferStockMP().getCodeTransfer());
    	    				mouvementstockMP.setEntrer(QuantiteTotal);
    	    				
    	    				mouvementstockMP.setMagasinSource(listDetailTransferStockMPMouvementStkMP.get(j).getMagasinSouce());
    	    				listMouvementStockMP.add(mouvementstockMP);
    	    				
    	    			}else if(comboperation.getSelectedItem().equals(Constantes.ETAT_TRANSFER_STOCK_SORTIE)  && listDetailTransferStockMPMouvementStkMP.get(j).getTransferStockMP().getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_SORTIE))
    	    				
    	    			{
    	    				DetailMouvementStock mouvementstockMP=new DetailMouvementStock();
        	    			mouvementstockMP.setDateCreation(listDetailTransferStockMPMouvementStkMP.get(j).getTransferStockMP().getDateTransfer());
        	    			mouvementstockMP.setMatierePremier(listDetailTransferStockMPMouvementStkMP.get(j).getMatierePremier());
        	    			mouvementstockMP.setNumOF(listDetailTransferStockMPMouvementStkMP.get(j).getTransferStockMP().getCodeTransfer());
    	    				mouvementstockMP.setSortie(QuantiteTotal);
    	    				mouvementstockMP.setMagasinDestination(magasinDestination);
    	    				listMouvementStockMP.add(mouvementstockMP);
    	    			}
    	    			
		    		}
       				
    	    		
    	    		// detailtransfer entre deux date et par article
		    		if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null && mp!=null)
		    		{
		    			listMouvementStockMPAfficher.clear();
		    			listMouvementStockMPAfficherTmp.clear();
		    			
		    		
		    		for(int i=0;i<listMouvementStockMP.size();i++)	
		    		{
		    			String ddebut=sdf.format(dateChooserdebut.getDate());
		    			String ddebutTmp=sdf.format(listMouvementStockMP.get(i).getDateCreation());
		    			
		    			if(listMouvementStockMP.get(i).getDateCreation().after(dateChooserdebut.getDate()) ==true || ddebutTmp.equals(ddebut)   )
		    				
		    			{
		    			if(listMouvementStockMP.get(i).getMatierePremier().getNom().equals(mp.getNom()))
		    			{
		    				listMouvementStockMPAfficher.add(listMouvementStockMP.get(i));	
		    			}
		    				
		    			
		    				
		    			}
		    			
		    		}
		    			
		    		for(int j=0;j<listMouvementStockMPAfficher.size();j++)	
		    		{
		    			
		    			String dfin=sdf.format(dateChooserfin.getDate());
		    			String dfinTmp=sdf.format(listMouvementStockMPAfficher.get(j).getDateCreation());
		    			if(listMouvementStockMPAfficher.get(j).getDateCreation().before(dateChooserfin.getDate())==true || dfinTmp.equals(dfin) )
		    			{
		    			if(listMouvementStockMPAfficher.get(j).getMatierePremier().getNom().equals(mp.getNom()))
		    			{
		    				listMouvementStockMPAfficherTmp.add(listMouvementStockMPAfficher.get(j));
		    			}
		    				
		    			
		    				
		    			}
		    			
		    		}
		    		
		    		
		    		if(listMouvementStockMPAfficherTmp.size()!=0)
		    		{
		    			afficher_tableDetailMouvementStock(listMouvementStockMPAfficherTmp);
		    			
		    		}else
		    		{
		    			afficher_tableDetailMouvementStock(listMouvementStockMPAfficher);
		    		}
		    		
		    			
		    		// detailtransfer entre deux date (date fin null) et par article 
		    			
		    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && mp!=null)
		    		{
		    			listMouvementStockMPAfficherTmp.clear();
		    			String d1=sdf.format(dateChooserdebut.getDate());
		    		
		    			
		    			for(int i=0;i<listMouvementStockMP.size();i++)	
			    		{
		    				String ddbut=sdf.format(listMouvementStockMP.get(i).getDateCreation());
			    			if(ddbut.equals(d1) && listMouvementStockMP.get(i).getMatierePremier().equals(mp) )
			    			{
			    			
			    				listMouvementStockMPAfficherTmp.add(listMouvementStockMP.get(i));
			    			
			    				
			    			}
			    			
			    		}
		    			
		    			afficher_tableDetailMouvementStock(listMouvementStockMPAfficherTmp);
		    			
		    			// detailtransfer entre deux date (date fin null)  
		    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && mp==null)
		    		{
		    			
		    			listMouvementStockMPAfficherTmp.clear();
	                      
	                      String d1=sdf.format(dateChooserdebut.getDate());
	  	    			
		    			
		    			for(int i=0;i<listMouvementStockMP.size();i++)	
			    		{
		    				String ddbut=sdf.format(listMouvementStockMP.get(i).getDateCreation());
			    			if(ddbut.equals(d1) )
			    			{
			    			
			    				listMouvementStockMPAfficherTmp.add(listMouvementStockMP.get(i));
			    			
			    				
			    			}
			    			
			    		}
		    			
		    			
		    			afficher_tableDetailMouvementStock(listMouvementStockMPAfficherTmp);
		    			
		    			// detailtransfer par article
		    		}else if(dateChooserdebut.getDate()==null && dateChooserfin.getDate()==null && mp!=null)
		    		{
		    			listMouvementStockMPAfficherTmp.clear();
		             
		               
		               
		    			for(int i=0;i<listMouvementStockMP.size();i++)	
			    		{
			    			if(listMouvementStockMP.get(i).getMatierePremier().getNom().equals(mp.getNom()) )
			    			{
			    			
			    				listMouvementStockMPAfficherTmp.add(listMouvementStockMP.get(i));
			    			
			    				
			    			}
			    			
			    		}
		    			
		    			
		    			afficher_tableDetailMouvementStock(listMouvementStockMPAfficherTmp);
		    			
		    			
		    			// detailtransfer entre deux date  
		    			
		    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null && mp==null)
		    		{
		    			listMouvementStockMPAfficher.clear();
		    			listMouvementStockMPAfficherTmp.clear();
		    		
		    		for(int i=0;i<listMouvementStockMP.size();i++)	
		    		{
		    			String ddebut=sdf.format(dateChooserdebut.getDate());
		    			String ddebutTmp=sdf.format(listMouvementStockMP.get(i).getDateCreation());
		    			if(listMouvementStockMP.get(i).getDateCreation().after(dateChooserdebut.getDate()) ==true || ddebutTmp.equals(ddebut))
		    			{
		    			
		    				listMouvementStockMPAfficher.add(listMouvementStockMP.get(i));
		    			
		    				
		    			}
		    			
		    		}
		    		for(int j=0;j<listMouvementStockMPAfficher.size();j++)	
		    		{
		    			
		    			String dfin=sdf.format(dateChooserfin.getDate());
		    			String dfinTmp=sdf.format(listMouvementStockMPAfficher.get(j).getDateCreation());
		    			
		    			if(listMouvementStockMPAfficher.get(j).getDateCreation().before(dateChooserfin.getDate())==true || dfinTmp.equals(dfin)  )
		    			{
		    			
		    				listMouvementStockMPAfficherTmp.add(listMouvementStockMPAfficher.get(j));
		    			
		    				
		    			}
		    			
		    		}
		    		
		    		if(listMouvementStockMPAfficherTmp.size()!=0)
		    		{
		    			afficher_tableDetailMouvementStock(listMouvementStockMPAfficherTmp);
		    			
		    		}else
		    		{
		    			afficher_tableDetailMouvementStock(listMouvementStockMPAfficher);
		    		}
		    		
		    		}
    	    		
    	    		
       				
       			}
       			
       		}
       		
       	}
       });
       tableMP.setEditable(false);
       tableMP.setAutoStartEditOnKeyStroke(false);
      
       
        
       tableMP.setModel(new DefaultTableModel(
       	new Object[][] {
       	},
       	new String[] {
				"Matiere Premiere", "Quantite"
       	}
       ));
       tableMP.getColumnModel().getColumn(0).setPreferredWidth(102);
       tableMP.getColumnModel().getColumn(1).setPreferredWidth(260);
    
      
       
       tableMP.setShowVerticalLines(false);
       tableMP.setSelectionBackground(new Color(51, 204, 255));
       tableMP.setRowHeightEnabled(true);
       tableMP.setBackground(new Color(255, 255, 255));
       tableMP.setHighlighters(HighlighterFactory.createSimpleStriping());
       tableMP.setColumnControlVisible(true);
       tableMP.setForeground(Color.BLACK);
       tableMP.setGridColor(new Color(0, 0, 255));
       tableMP.setBounds(2, 27, 411, 198);
       tableMP.setRowHeight(20);
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(tableMP);
				  		     	scrollPane.setBounds(10, 195, 925, 348);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			  		    
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste MP");
				  		     	titledSeparator.setBounds(10, 154, 1465, 30);
				  		     	add(titledSeparator);
		  
		 
		
		JLabel lblConslterLesFactures = new JLabel("           Consulter le Mouvement de Stock :");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(332, 11, 836, 35);
		add(lblConslterLesFactures);
		 //Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    
	    JButton btnAfficher = new JButton("Consulter");
	    btnAfficher.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		
	    		InitialiseTableauMouvementStock();
	    		
	    		BigDecimal quantiteTotal=new BigDecimal(0);
	    		InitialiseTableauMP();
	    	    detailTransferStockMPDAO.ViderSession();
	    	    listEtatStockMP.clear();
	    		listDetailTransferStockMP.clear();
	    		listDetailTransferStockMPGroupebyMP.clear();
	    		listDetailTransferStockMPBytypetransfer.clear();
	    		listDetailTransferStockMPAllMP.clear();
	    		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/YYYY");
	    		listMouvementStockMP.clear();
	    		boolean trouve=false;
	    		MatierePremier mp=null;
	    		Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
	    		
	    		if(dateChooserdebut.getDate()==null && dateChooserfin.getDate()==null)
	    		{
	    			JOptionPane.showMessageDialog(null, "Veuillez entrer la date SVP !!!");
    				return;
	    		}else
	    		{
	    			if(magasin!=null)
		    		{
		    			if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null)
			    		{
			    			String d1=sdf.format(dateChooserdebut.getDate());
			    			String d2=sdf.format(dateChooserfin.getDate());
			    			
			    		if(!d1.equals(d2))
			    		{
			    			if(dateChooserfin.getDate().compareTo(dateChooserdebut.getDate())<0)
			    			{
			    				JOptionPane.showMessageDialog(null, "date de fin doit etre supérieur au date debut SVP !!!");
			    				return;
			    			}
			    			
			    		}
			    		
			    	
			    		
			    		}
			    		
			    		if(dateChooserdebut.getDate()==null )
			    		{
			    			dateChooserfin.setCalendar(null);
			    			titre="Mouvement de Stock  magasin : "+magasin.getLibelle();
			    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && mp!=null)
			    		{
			    			String d1=sdf.format(dateChooserdebut.getDate());
			    			titre="Mouvement de Stock de "+mp.getNom() +" au magasin : "+magasin.getLibelle()+" entre "+d1 +" et "+d1;
			    			
			    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && mp==null)
			    		{
			    			String d1=sdf.format(dateChooserdebut.getDate());
			    			titre="Mouvement de Stock de magasin : "+magasin.getLibelle()+ "entre "+d1 +" et "+d1;
			    		}
			    		
if(comboperation.getSelectedItem().equals(Constantes.ETAT_TRANSFER_STOCK_AJOUT))
{
	listDetailTransferStockMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutAchat(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_AJOUT,magasin);
	
	listDetailTransferStockMPGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutAchat(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_AJOUT,magasin);

}else if(comboperation.getSelectedItem().equals(Constantes.ETAT_TRANSFER_STOCK_SORTIE))
{
	
listDetailTransferStockMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutCharge(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_SORTIE,magasin);
	
listDetailTransferStockMPGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutCharge(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_SORTIE,magasin);
	
	
	
}else if(comboperation.getSelectedItem().equals(Constantes.ETAT_TRANSFER_STOCK_ENTRE))
{
	
listDetailTransferStockMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutAchat(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_ENTRE,magasin);
	
listDetailTransferStockMPGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutAchat(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_ENTRE,magasin);

	
}
			    		
listDetailTransferStockMPAllMP=	detailTransferStockMPDAO.findAllTransferStockMPGroupeByMP(magasin);

for(int p=0;p<listDetailTransferStockMPAllMP.size();p++)
{
	DetailTransferStockMP detailtransferstockmp=listDetailTransferStockMPAllMP.get(p);
	EtatStockMP etatstock=new EtatStockMP();
	etatstock.setMp(detailtransferstockmp.getMatierePremier());
	etatstock.setQuantiteReception(BigDecimal.ZERO);
	etatstock.setQuantiteEntrer(BigDecimal.ZERO);
	etatstock.setQuantiteSortie(BigDecimal.ZERO);
	
	listEtatStockMP.add(etatstock);
	
}	    	


if(comboperation.getSelectedItem().equals(Constantes.ETAT_TRANSFER_STOCK_AJOUT))
{

	
	
	
 	for(int j=0;j<listDetailTransferStockMPGroupebyMP.size();j++)
	{
		
		quantiteTotal=new BigDecimal(0);
		
	for(int k=0;k<listDetailTransferStockMP.size();k++)
	{
		
		if(listDetailTransferStockMPGroupebyMP.get(j).getMatierePremier().equals(listDetailTransferStockMP.get(k).getMatierePremier()))
		{
			
			if(listDetailTransferStockMP.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMP.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMP.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMP.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMP.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
			{
				
    			quantiteTotal=quantiteTotal.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMP.get(k).getQuantite().setScale(0, RoundingMode.CEILING));
			}else
			{
				
    			quantiteTotal=quantiteTotal.add(listDetailTransferStockMP.get(k).getQuantite());
			}

			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
			
		}
		
		
	}
	
	
	for(int i=0;i<listEtatStockMP.size();i++)
	{
		if(listEtatStockMP.get(i).getMp().equals(listDetailTransferStockMPGroupebyMP.get(j).getMatierePremier()))
		{
			EtatStockMP etatstockmp=listEtatStockMP.get(i);
			
			if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
			{
				etatstockmp.setQuantiteReception(quantiteTotal.setScale(0, RoundingMode.CEILING));
				
				listEtatStockMP.set(i, etatstockmp);
				
				
			}else
			{
				
				etatstockmp.setQuantiteReception(quantiteTotal);
			
				listEtatStockMP.set(i, etatstockmp);
			}
			
			
			
		}
	}
	
	}
 	
 	
 	
 	afficher_tableMP(listEtatStockMP, Constantes.ETAT_TRANSFER_STOCK_AJOUT);
 	
 	
 	
 	
 	
 	

}else if(comboperation.getSelectedItem().equals(Constantes.ETAT_TRANSFER_STOCK_SORTIE))
{




 	for(int j=0;j<listDetailTransferStockMPGroupebyMP.size();j++)
	{
		
		quantiteTotal=new BigDecimal(0);
		
	for(int k=0;k<listDetailTransferStockMP.size();k++)
	{
		
		if(listDetailTransferStockMPGroupebyMP.get(j).getMatierePremier().equals(listDetailTransferStockMP.get(k).getMatierePremier()))
		{
			
			if(listDetailTransferStockMP.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMP.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMP.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMP.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMP.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
			{
				
    			quantiteTotal=quantiteTotal.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMP.get(k).getQuantite().setScale(0, RoundingMode.CEILING));
			}else
			{
				
    			quantiteTotal=quantiteTotal.add(listDetailTransferStockMP.get(k).getQuantite());
			}

			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
			
		}
		
		
	}
	
	
	for(int i=0;i<listEtatStockMP.size();i++)
	{
		if(listEtatStockMP.get(i).getMp().equals(listDetailTransferStockMPGroupebyMP.get(j).getMatierePremier()))
		{
			EtatStockMP etatstockmp=listEtatStockMP.get(i);
			
			if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
			{
				etatstockmp.setQuantiteSortie(quantiteTotal.setScale(0, RoundingMode.CEILING));
				
				listEtatStockMP.set(i, etatstockmp);
				
				
			}else
			{
				
				etatstockmp.setQuantiteSortie(quantiteTotal);
			
				listEtatStockMP.set(i, etatstockmp);
			}
			
			
			
		}
	}
	
	}

	
 	afficher_tableMP(listEtatStockMP, Constantes.ETAT_TRANSFER_STOCK_SORTIE);
	
	


}else if(comboperation.getSelectedItem().equals(Constantes.ETAT_TRANSFER_STOCK_ENTRE))
{







 	for(int j=0;j<listDetailTransferStockMPGroupebyMP.size();j++)
	{
		
		quantiteTotal=new BigDecimal(0);
		
	for(int k=0;k<listDetailTransferStockMP.size();k++)
	{
		
		if(listDetailTransferStockMPGroupebyMP.get(j).getMatierePremier().equals(listDetailTransferStockMP.get(k).getMatierePremier()))
		{
			
			if(listDetailTransferStockMP.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMP.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMP.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMP.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMP.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
			{
				
    			quantiteTotal=quantiteTotal.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMP.get(k).getQuantite().setScale(0, RoundingMode.CEILING));
			}else
			{
				
    			quantiteTotal=quantiteTotal.add(listDetailTransferStockMP.get(k).getQuantite());
			}

			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
			
		}
		
		
	}
	
	
	for(int i=0;i<listEtatStockMP.size();i++)
	{
		if(listEtatStockMP.get(i).getMp().equals(listDetailTransferStockMPGroupebyMP.get(j).getMatierePremier()))
		{
			EtatStockMP etatstockmp=listEtatStockMP.get(i);
			
			if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
			{
				etatstockmp.setQuantiteEntrer(quantiteTotal.setScale(0, RoundingMode.CEILING));
				
				listEtatStockMP.set(i, etatstockmp);
				
				
			}else
			{
				
				etatstockmp.setQuantiteEntrer(quantiteTotal);
			
				listEtatStockMP.set(i, etatstockmp);
			}
			
			
			
		}
	}
	
	}

	
 	afficher_tableMP(listEtatStockMP,Constantes.ETAT_TRANSFER_STOCK_ENTRE);
	
	
}

    		
		    		}else
		    		{
		    			JOptionPane.showMessageDialog(null, "Veuillez selectionner un depot SVP ","Erreur",JOptionPane.ERROR_MESSAGE);
		    			return;
		    		}
	    		}
	    
	    	
	    		
	    	}
	    });
	    btnAfficher.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnAfficher.setBounds(534, 119, 107, 24);
	    btnAfficher.setIcon(imgChercher);
	    add(btnAfficher);
	    
	    JLayeredPane layeredPane = new JLayeredPane();
	    layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    layeredPane.setBounds(20, 57, 1455, 51);
	    add(layeredPane);
	    
	    JLabel label = new JLabel("Date Debut :");
	    label.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    label.setBounds(10, 11, 136, 24);
	    layeredPane.add(label);
	    
	     dateChooserdebut = new JDateChooser();
	    dateChooserdebut.setLocale(Locale.FRANCE);
	    dateChooserdebut.setDateFormatString("dd/MM/yyyy");
	    dateChooserdebut.setBounds(112, 9, 163, 26);
	    layeredPane.add(dateChooserdebut);
	    
	    JLabel label_1 = new JLabel("Date Fin :");
	    label_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    label_1.setBounds(296, 11, 106, 24);
	    layeredPane.add(label_1);
	    
	     dateChooserfin = new JDateChooser();
	    dateChooserfin.setLocale(Locale.FRANCE);
	    dateChooserfin.setDateFormatString("dd/MM/yyyy");
	    dateChooserfin.setBounds(399, 11, 169, 26);
	    layeredPane.add(dateChooserfin);
	    
	     comboperation = new JComboBox();
	     comboperation.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
}
	     });
	    comboperation.setBounds(1209, 11, 218, 27);
	    layeredPane.add(comboperation);
	    
	    comboperation.addItem("");
	    comboperation.addItem(Constantes.ETAT_TRANSFER_STOCK_AJOUT);
	    comboperation.addItem(Constantes.ETAT_TRANSFER_STOCK_ENTRE);
	    comboperation.addItem(Constantes.ETAT_TRANSFER_STOCK_SORTIE);
	    
	    JLabel lblOperation = new JLabel("Operation :");
	    lblOperation.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    lblOperation.setBounds(1141, 11, 68, 26);
	    layeredPane.add(lblOperation);
	    
	    JLabel lblDepot = new JLabel("Depot  :");
	    lblDepot.setBounds(578, 8, 45, 26);
	    layeredPane.add(lblDepot);
	    lblDepot.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    
	     combodepot = new JComboBox();
	     combodepot.addItemListener(new ItemListener() {
	     	public void itemStateChanged(ItemEvent e) {
	     		
	     		 if(e.getStateChange() == ItemEvent.SELECTED) {
	     			 
	     		
	     				  Depot depot=depotdao.findByCode(combodepot.getSelectedItem().toString());
	     				  if(depot!=null)
	     				  {
	     					
	     					  
	     					  listMagasin=depotdao.listeMagasinByTypeMagasinDepot(depot.getId(), MAGASIN_CODE_TYPE_MP);
	     					  int k=0;
	     					  combomagasin.removeAllItems();
	     				     	 combomagasin.addItem("");
	     				     	while (k<listMagasin.size())
	     				     	{
	     				     		Magasin magasin=listMagasin.get(k);
	     				     		
	     				     		
	     				     		combomagasin.addItem(magasin.getLibelle());
	     						     		
	     						     		mapMagasin.put(magasin.getLibelle(), magasin);
	     						     	
	     				     		k++;
	     				     		
	     				     	}
	     					 
	     				  }else
	     				  {
	     					  combomagasin.removeAllItems();
	     					  combomagasin.addItem("");
	     				  }
	     			 
	     			 
	     			 
	     			 
	     			 
	     			 
	     			 
	     			 
	     			 
	     		 }
	     		
	     		
	     		
	     		
	     		
	     		
	     	}
	     });
	    combodepot.setBounds(639, 8, 202, 27);
	    layeredPane.add(combodepot);
	    try {
			  
			 
	          Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)util.DateUtils.getCurrentYear()+"-01-01");
	          dateChooserdebut.setDate(date);
	          dateChooserfin.setDate(new Date());
	          
	          JLabel lblMagasin = new JLabel("Magasin  :");
	          lblMagasin.setFont(new Font("Tahoma", Font.PLAIN, 11));
	          lblMagasin.setBounds(851, 11, 59, 26);
	          layeredPane.add(lblMagasin);
	          
	           combomagasin = new JComboBox();
	          combomagasin.setBounds(926, 11, 202, 27);
	          layeredPane.add(combomagasin);
			  
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	    JButton button = new JButton("Initialiser");
	    button.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		

	     		combomp.setSelectedItem("");
	     		
	     		dateChooserdebut.setCalendar(null);
	     		dateChooserfin.setCalendar(null);
	     		combodepot.setSelectedItem("");
	    	}
	    });
	    button.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    button.setBounds(668, 119, 107, 24);
	    add(button);
	    
	    JScrollPane scrollPane_1 = new JScrollPane((Component) null);
	    scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    scrollPane_1.setBounds(10, 585, 925, 280);
	    add(scrollPane_1);
	    
	    tableMouvementStockMP = new JTable();
	    tableMouvementStockMP.setModel(new DefaultTableModel(
	    	new Object[][] {
	    	},
	    	new String[] {
	    		"Date", "Matiere Premiere","Num Transfert" ,"Quantite"
	    	}
	    ));
	    tableMouvementStockMP.setFillsViewportHeight(true);
	    scrollPane_1.setViewportView(tableMouvementStockMP);
	    
	    JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
	    GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
	    gridBagLayout.rowWeights = new double[]{0.0};
	    gridBagLayout.rowHeights = new int[]{0};
	    gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
	    gridBagLayout.columnWidths = new int[]{0, 0, 0};
	    titledSeparator_1.setTitle("Liste Detail MP");
	    titledSeparator_1.setBounds(10, 549, 1465, 30);
	    add(titledSeparator_1);
	  
	    if(utilisateur.getLogin().equals("admin"))
		  {
	    	
	    	combodepot.addItem("");
	    	listDepot =depotdao.findAll();
	    	for(int i=0;i<listDepot.size();i++)
	    	{
	    		
	    		Depot depot=listDepot.get(i);
	    		combodepot.addItem(depot.getLibelle());
	    		mapDepot.put(depot.getLibelle(), depot);
	    		
	    		
	    	}
	    	
	  
		      
		  }else
		  {
			  Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
			  if(depot!=null)
			  {
				  
				  combodepot.addItem(""); 
				  combodepot.addItem(depot.getLibelle());
		    		mapDepot.put(depot.getLibelle(), depot);
				  
		
				 
			  }
		  }
	    
	    
	    
		
		}
	

	
	void InitialiseTableauMP()
	{
		modeleMP =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Matiere Premiere", "Quantite"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableMP.setModel(modeleMP);
		 tableMP.getColumnModel().getColumn(0).setPreferredWidth(258);
	       tableMP.getColumnModel().getColumn(1).setPreferredWidth(102);
	      
}
	
	void InitialiseTableauMouvementStock()
	{
		modeleMouvementStock =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Date Mouvement","Matiere Premiere","Num Transfert","Quantite"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
			tableMouvementStockMP.setModel(modeleMouvementStock);
			tableMouvementStockMP.getColumnModel().getColumn(0).setPreferredWidth(121);
			tableMouvementStockMP.getColumnModel().getColumn(1).setPreferredWidth(106);
			tableMouvementStockMP.getColumnModel().getColumn(2).setPreferredWidth(111);
			tableMouvementStockMP.getColumnModel().getColumn(3).setPreferredWidth(111);
		
	
}

	
	
	void afficher_tableMP(List<EtatStockMP> listEtatStockMP , String statut)
	{
		
		if(statut.equals(Constantes.ETAT_TRANSFER_STOCK_AJOUT))
		{
			
			modeleMP =new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
							"Matiere Premiere", "Quantite"
					}
				) {
					
				};
			tableMP.setModel(modeleMP);
			int i=0;
			 
			while(i<listEtatStockMP.size())
			{	
			EtatStockMP etatStockMP=listEtatStockMP.get(i);
			
				
					Object []ligne={etatStockMP.getMp().getNom() , NumberFormat.getNumberInstance(Locale.FRANCE).format(etatStockMP.getQuantiteReception())};

					modeleMP.addRow(ligne);
				
				
				i++;
			}

			
			
		}else if(statut.equals(Constantes.ETAT_TRANSFER_STOCK_ENTRE))
		{
			
			modeleMP =new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
							"Matiere Premiere", "Quantite"
					}
				) {
					
				};
			tableMP.setModel(modeleMP);
			int i=0;
			 
			while(i<listEtatStockMP.size())
			{	
			EtatStockMP etatStockMP=listEtatStockMP.get(i);
			
				
					Object []ligne={etatStockMP.getMp().getNom() , NumberFormat.getNumberInstance(Locale.FRANCE).format(etatStockMP.getQuantiteEntrer())};

					modeleMP.addRow(ligne);
				
				
				i++;
			}

			
			
		
		}else if(statut.equals(Constantes.ETAT_TRANSFER_STOCK_SORTIE))
		{
			
			modeleMP =new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
							"Matiere Premiere", "Quantite"
					}
				) {
					
				};
			tableMP.setModel(modeleMP);
			int i=0;
			 
			while(i<listEtatStockMP.size())
			{	
			EtatStockMP etatStockMP=listEtatStockMP.get(i);
			
				
					Object []ligne={etatStockMP.getMp().getNom() , NumberFormat.getNumberInstance(Locale.FRANCE).format(etatStockMP.getQuantiteSortie())};

					modeleMP.addRow(ligne);
				
				
				i++;
			}

			
		}
		
			
}
	
	
	void afficher_tableDetailMouvementStock(List<DetailMouvementStock> listDetailMouvementStock)
	{
		if(comboperation.getSelectedItem().equals(Constantes.ETAT_TRANSFER_STOCK_AJOUT))
		{
			
			modeleDetailMouvementStock =new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
							"Date Mouvement","Matiere Premiere","Num Transfert", "Quantite"
					}
				) {
					
				};
			tableMouvementStockMP.setModel(modeleDetailMouvementStock);
			
			int i=0;
			 
			while(i<listDetailMouvementStock.size())
			{	
				
				
			DetailMouvementStock detailMouvementStock=listDetailMouvementStock.get(i);
			
			
				
				Object []ligne={detailMouvementStock.getDateCreation(),detailMouvementStock.getMatierePremier().getNom(),detailMouvementStock.getNumOF(), NumberFormat.getNumberInstance(Locale.FRANCE).format(detailMouvementStock.getReception())};

				modeleDetailMouvementStock.addRow(ligne);
				i++;
			}
			
		}else if(comboperation.getSelectedItem().equals(Constantes.ETAT_TRANSFER_STOCK_ENTRE))
		{
			

			
			modeleDetailMouvementStock =new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
							"Date Mouvement","Matiere Premiere","Num Transfert","Quantite"
					}
				) {
					
				};
			tableMouvementStockMP.setModel(modeleDetailMouvementStock);
			
			int i=0;
			 
			while(i<listDetailMouvementStock.size())
			{	
				
				
			DetailMouvementStock detailMouvementStock=listDetailMouvementStock.get(i);
			
			
				
				Object []ligne={detailMouvementStock.getDateCreation(),detailMouvementStock.getMatierePremier().getNom(),detailMouvementStock.getNumOF(),NumberFormat.getNumberInstance(Locale.FRANCE).format(detailMouvementStock.getEntrer())};

				modeleDetailMouvementStock.addRow(ligne);
				i++;
			}
			
		
		}else if(comboperation.getSelectedItem().equals(Constantes.ETAT_TRANSFER_STOCK_SORTIE))
		{
			

			
			modeleDetailMouvementStock =new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
							"Date Mouvement","Matiere Premiere","Num Transfert","Quantite","Magasin Destination"
					}
				) {
					
				};
			tableMouvementStockMP.setModel(modeleDetailMouvementStock);
			
			int i=0;
			 
			while(i<listDetailMouvementStock.size())
			{	
				
				
			DetailMouvementStock detailMouvementStock=listDetailMouvementStock.get(i);
			
			if(detailMouvementStock.getMagasinDestination()!=null)
			{
				Object []ligne={detailMouvementStock.getDateCreation(),detailMouvementStock.getMatierePremier().getNom(),detailMouvementStock.getNumOF(),NumberFormat.getNumberInstance(Locale.FRANCE).format(detailMouvementStock.getSortie()),detailMouvementStock.getMagasinDestination().getLibelle()};

				modeleDetailMouvementStock.addRow(ligne);
			}else
			{
				
				Object []ligne={detailMouvementStock.getDateCreation(),detailMouvementStock.getMatierePremier().getNom(),detailMouvementStock.getNumOF(),NumberFormat.getNumberInstance(Locale.FRANCE).format(detailMouvementStock.getSortie()),""};

				modeleDetailMouvementStock.addRow(ligne);
				
			}
				
				
				i++;
			}
			
		
		}
	
}
	}



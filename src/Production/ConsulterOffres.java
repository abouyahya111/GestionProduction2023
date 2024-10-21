package Production;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EtchedBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import main.AuthentificationView;
import main.Main;
import main.ProdLauncher;

import org.jdesktop.swingx.JXTable;

import util.Constantes;
import util.Utils;
import dao.daoImplManager.ConditionOffreDAOImpl;
import dao.daoImplManager.CoutMPDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailEstimationPromoDAOImpl;
import dao.daoImplManager.EmployeDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.PromotionDAOImpl;
import dao.daoManager.CompteurAbsenceEmployeDAO;
import dao.daoManager.CompteurEmployeProdDAO;
import dao.daoManager.CompteurProductionDAO;
import dao.daoManager.CompteurResponsableProdDAO;
import dao.daoManager.ConditionOffreDAO;
import dao.daoManager.CoutMPDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailEstimationPromoDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.FicheEmployeDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.PromotionDAO;
import dao.entity.ConditionOffre;
import dao.entity.Depot;
import dao.entity.DetailEstimationPromo;
import dao.entity.DetailProduction;
import dao.entity.Employe;
import dao.entity.MatierePremier;
import dao.entity.Production;
import dao.entity.Promotion;
import dao.entity.Utilisateur;

import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ConsulterOffres extends JLayeredPane implements Constantes{
	 
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleOffre;
	private DefaultTableModel	 modeleDetailOffre;
	private JXTable  tableEmploye=new JXTable();
	 JXTable tableDetailOffre = new JXTable();
	
	private JXTable tableOffre;
	private ImageIcon imgModifier;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgSupp1;
	
	private JButton btnImprimer;
	private JButton btnAnnulerOF;
	private JButton btnValiderDelai;
	private JButton btnRechercher;
	

	private List <Object[]> listeObjectQuaniteConsommeOffre=new ArrayList<Object[]>();
 
	private List<DetailEstimationPromo> listDetailEstimationPromo =new ArrayList<DetailEstimationPromo>();
	private List<ConditionOffre> listConditionOffre =new ArrayList<ConditionOffre>();
	private List<Promotion> listPromotion =new ArrayList<Promotion>();
	private Map<String, Promotion>mapOffre=new HashMap<>();
	private CoutMPDAO coutMPDAO;
	private DetailEstimationPromoDAO detailEstimationPromoDAO;;
	 private PromotionDAO promotionDAO;
	 private ConditionOffreDAO conditionOffreDAO;
	 JComboBox comboCondition = new JComboBox();
	  JComboBox comboOffres = new JComboBox();
	  private Utilisateur utilisateur;
	  private List<Depot> listDepot =new ArrayList<Depot>();
		private DepotDAO depotDAO;
		private Map< String, Depot> depotMap = new HashMap<>();
		 JComboBox comboDepot = new JComboBox();
		 
		 
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	
	public ConsulterOffres(){
		
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1182, 800);
        try {
        	javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try{
        	
        	tableOffre=new JXTable();
        	tableOffre.addMouseListener(new MouseAdapter() {
        		@Override
        		public void mouseClicked(MouseEvent arg0) {
        			
        			if(tableOffre.getSelectedRow()!=-1)
        			{
        				
        				String req="";
        				Promotion promotion=listPromotion.get(tableOffre.getSelectedRow());
        				
        				req=req+" where c.promotionest.id='"+promotion.getId()+"' ";
        				
        				listDetailEstimationPromo=detailEstimationPromoDAO.findByReq(req);
        				
        				
        				afficher_tableDetailOffre(listDetailEstimationPromo);
        				
        				
        			}
        			
        			
        			
        			
        			
        		}
        	});
        	  
         
        	
         
        	
        	
        	detailEstimationPromoDAO=new DetailEstimationPromoDAOImpl();
        	promotionDAO=new PromotionDAOImpl();
        	conditionOffreDAO=new ConditionOffreDAOImpl();
        	coutMPDAO=new CoutMPDAOImpl();
        	utilisateur= AuthentificationView.utilisateur;
        	depotDAO= new DepotDAOImpl();
        	/*compteurAbsenceEmployeDAO=ProdLauncher.compteurAbsenceEmployeDAO;
        	ficheEmployeDAO=ProdLauncher.ficheEmployeDAO;*/
        	
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		

        initialiserTableauEmploye();
       
		
            
		    
				  		 
				  		  
				  		/*##################################################################
				  		     * FIN AJOUT LA LSITES DES PERSONNES A LA LISTE D'AFFICHAGE 
				  		    *###################################################################*/
				  		     
				  		 
				  		     
				  		     JScrollPane scrollPane = new JScrollPane(tableOffre);
				  		     scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     scrollPane.setBounds(10, 120, 1082, 276);
				  		    add(scrollPane);
				  		     
				  	
				  		     
				  		   
				  		     
				  		     JLayeredPane layeredPane = new JLayeredPane();
				  		     layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     layeredPane.setBounds(0, 11, 1092, 98);
				  		     add(layeredPane);
				  		     
				  		     JLabel lblCondition = new JLabel("Conditions");
				  		     lblCondition.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     lblCondition.setBounds(10, 35, 80, 24);
				  		     layeredPane.add(lblCondition);
				  		     
				  		     JLabel lblOffres = new JLabel("Offres");
				  		     lblOffres.setBounds(298, 34, 80, 26);
				  		     layeredPane.add(lblOffres);
				  		     
				  		      comboCondition = new JComboBox();
				  		      comboCondition.addItemListener(new ItemListener() {
				  		      	public void itemStateChanged(ItemEvent e) {
				  		      		
				  		      	 if(e.getStateChange() == ItemEvent.SELECTED) {
				  		      		
				  		      		 if(!comboCondition.getSelectedItem().equals(""))
				  		      		 {
				  		      			comboOffres.removeAllItems();
					  		      		
					  		      		
					  		      		
					  		      		String req="";
					  		      		
					  		      		req=req+ " where c.condition='"+comboCondition.getSelectedItem().toString()+"'";
					  		      		
					  		      		listPromotion=promotionDAO.findByReq(req);
					  		      	comboOffres.addItem("");
					  		      listPromotion.stream().forEach(p->{
					  		    	comboOffres.addItem(p.getCode());
					  		    	mapOffre.put(p.getCode(), p);
					  		      });	
					  		      		
				  		      		 }
				  		      		
				  		      		
				  		      		 
				  		      		 
				  		      		 
				  		      		 
				  		      		 
				  		      		 
				  		      	 }
				  		      		
				  		      		
				  		      		
				  		      		
				  		      	}
				  		      });
				  		     comboCondition.setBounds(88, 35, 184, 24);
				  		     layeredPane.add(comboCondition);
				  		     
				  		      comboOffres = new JComboBox();
				  		     comboOffres.setBounds(353, 34, 229, 24);
				  		     layeredPane.add(comboOffres);
				  		     
				  		     JButton button = new JButton("Afficher");
				  		     button.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent arg0) {
				  		     		
				  		     		
				  		     		Depot depot=depotMap.get(comboDepot.getSelectedItem().toString());
				  		     		
				  		     		if(depot==null)
				  		     		{
				  		     			JOptionPane.showMessageDialog(null, "veuillez Séléctionner le Depot SVP !!!!");
				  		     			return;
				  		     		}
				  		     		
				  		     		
				  		     		String req="";
				  		     		
				  		     		if(comboCondition.getSelectedItem().equals(""))
				  		     		{
				  		     			req="";
				  		     			req=req+" where c.typeOffre is not null ";
				  		     			
				  		     			listPromotion=promotionDAO.findByReq(req);
				  		     			afficher_tableMP(listPromotion);
				  		     			
				  		     		}else
				  		     		{
				  		     			
				  		     			if(comboOffres.getSelectedItem().equals(""))
				  		     			{
				  		     				req="";
				  		     				req=req+" where c.condition='"+comboCondition.getSelectedItem().toString()+"'";	
				  		     				
				  		     				listPromotion=promotionDAO.findByReq(req);
				  		     				afficher_tableMP(listPromotion);
				  		     				
				  		     			}else
				  		     			{
				  		     				req="";
				  		     				
                                    req=req+" where c.condition='"+comboCondition.getSelectedItem().toString()+"' and c.code='"+comboOffres.getSelectedItem().toString()+"'";	
				  		     				
                                    listPromotion=promotionDAO.findByReq(req);
				  		     				
				  		     				afficher_tableMP(listPromotion);
				  		     			}
				  		     			
				  		     			
				  		     			
				  		     			
				  		     		}
				  		     		
				  		     		
				  		     		
				  		     		
				  		     		
				  		     		
				  		     	}
				  		     });
				  		     button.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     button.setBounds(973, 35, 97, 24);
				  		     layeredPane.add(button);
				  		   comboCondition.addItem("");
				  		 comboCondition.addItem(Constantes.TYPE_OFFRE_AUTRES);
				  		 
				  		 JLabel lblDepot = new JLabel("Depot");
				  		 lblDepot.setBounds(603, 34, 80, 26);
				  		 layeredPane.add(lblDepot);
				  		 
				  		   comboDepot = new JComboBox();
				  		 comboDepot.setBounds(658, 34, 229, 24);
				  		 layeredPane.add(comboDepot);
				  		 
				  		 JScrollPane scrollPane_1 = new JScrollPane();
				  		 scrollPane_1.setBounds(10, 420, 1078, 343);
				  		 add(scrollPane_1);
				  		 
				  		  tableDetailOffre = new JXTable();
				  		 scrollPane_1.setViewportView(tableDetailOffre);
				  		 tableDetailOffre.setModel(new DefaultTableModel(
				  		 	new Object[][] {
				  		 	},
				  		 	new String[] {
				  		 		"Code MP", "MP", "Fournisseur", "Quantite Consomme"
				  		 	}
				  		 ));
				  		 listConditionOffre=conditionOffreDAO.findAll();
				  		listConditionOffre.stream().forEach( c->{
				  			comboCondition.addItem(c.getValeur().toString());
				  		});
				  	  int i=0;   
				  	comboDepot.addItem("");
				  	  if(!utilisateur.getCodeDepot().equals(CODE_DEPOT_SIEGE)) {
				  			Depot	 depot = depotDAO.findByCode(utilisateur.getCodeDepot());
					    		depotMap.put(depot.getLibelle(), depot);
		  		      			comboDepot.addItem(depot.getLibelle());
		  		      			 
					    }else {
				  		  listDepot = depotDAO.findAll();	
				  		       i=0;
				  		      	while(i<listDepot.size())
				  		      		{	
				  		      			Depot depot=listDepot.get(i);
				  		      			depotMap.put(depot.getLibelle(), depot);
				  		      			comboDepot.addItem(depot.getLibelle());
				  		      			 
				  		      			i++;
				  		      		}
					    }	     
	
				}




void initialiserTableauEmploye(){
	

	 modeleOffre =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code Offre","Condition", "Type Offre"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false 
		     	};
		    
		       
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		
		     tableOffre.setModel(modeleOffre);
		  	tableOffre.getColumnModel().getColumn(0).setPreferredWidth(60);
		     tableOffre.getColumnModel().getColumn(1).setPreferredWidth(60);
		     tableOffre.getColumnModel().getColumn(2).setPreferredWidth(160);
		    
		    
		    
		  				
		    
}



void initialiserTableauDetailOffre(){
	
 
	 modeleDetailOffre =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code MP","MP", "Fournisseur","Quantite Consomme"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false ,false 
		     	};
		    
		       
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		
		     tableDetailOffre.setModel(modeleDetailOffre);
		     tableDetailOffre.getColumnModel().getColumn(0).setPreferredWidth(60);
		     tableDetailOffre.getColumnModel().getColumn(1).setPreferredWidth(60);
		     tableDetailOffre.getColumnModel().getColumn(2).setPreferredWidth(60);
		     tableDetailOffre.getColumnModel().getColumn(3).setPreferredWidth(60);
		    
		    
		  				
		    
}


void afficher_tableMP(List<Promotion> listPromotion)
{

	initialiserTableauEmploye();
	initialiserTableauDetailOffre();
	  int i=0;
	  
	  
		while(i<listPromotion.size())
		{	
			Promotion promotion=listPromotion.get(i);
			
		 
			
			
			
			
			 
				Object []ligne={promotion.getCode() , promotion.getCondition(), promotion.getTypeOffre().getTypeOffre()};

				modeleOffre.addRow(ligne);
				i++;
			 
		
		}

		tableOffre.setModel(modeleOffre); 

	

        
	 
}

void afficher_tableDetailOffre(List<DetailEstimationPromo> listDetailOffre)
{
	
	Depot depot=depotMap.get(comboDepot.getSelectedItem().toString());
		
		if(depot==null)
		{
			JOptionPane.showMessageDialog(null, "veuillez Séléctionner le Depot SVP !!!!");
			return;
		}

	initialiserTableauDetailOffre();
	  int i=0;
	  
	  String fournisseur="";
	  BigDecimal QuantiteOffreConsomme=BigDecimal.ZERO;
		while(i<listDetailOffre.size())
		{	
			QuantiteOffreConsomme=BigDecimal.ZERO;
			fournisseur="";
			
			DetailEstimationPromo detailEstimationPromo=listDetailOffre.get(i);
			
		 
			if(detailEstimationPromo.getFournisseur()!=null)
			{
				fournisseur=detailEstimationPromo.getFournisseur().getCodeFournisseur();
			}
			
			listeObjectQuaniteConsommeOffre=coutMPDAO.QuantiteConsommeOffre(detailEstimationPromo.getMatierePremiere(), detailEstimationPromo.getPromotionest().getCode(), detailEstimationPromo.getFournisseur(), depot.getCode());		
			
			for(int j=0;j<listeObjectQuaniteConsommeOffre.size();j++)
			{
				Object [] object=(Object[]) listeObjectQuaniteConsommeOffre.get(j);
				 if(object[1]!=null)
				 {
					 QuantiteOffreConsomme=(BigDecimal)object[1] ;
				 }
			}
			
			
			
			 
				Object []ligne={detailEstimationPromo.getMatierePremiere().getCode() ,detailEstimationPromo.getMatierePremiere().getNom(), fournisseur,QuantiteOffreConsomme.setScale(6, RoundingMode.FLOOR)};

				modeleDetailOffre.addRow(ligne);
				i++;
			 
		
		}

		tableDetailOffre.setModel(modeleDetailOffre); 

	

        
	 
}
}

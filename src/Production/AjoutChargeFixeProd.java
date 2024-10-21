package Production;

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
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.ChargeFixeDAOImpl;
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailCoutProductionDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoManager.ChargeFixeDAO;
import dao.daoManager.ChargeProductionDAO;
import dao.daoManager.ChargesDAO;
import dao.daoManager.CompteurProductionDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailCoutProductionDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.StockMPDAO;
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
import dao.entity.DetailResponsableProd;
import dao.entity.Employe;
import dao.entity.FraisDepot;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.Production;
import dao.entity.StockMP;
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


public class AjoutChargeFixeProd extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleChargefixe;

	private JXTable  tablechargefixe = new JXTable();
	private List<DetailChargeFixe> listDetailChargeFixe =new ArrayList<DetailChargeFixe>();
	private List<Charges> listChargeFixe =new ArrayList<Charges>();
	private List<MatierePremier> listMatierePremiere =new ArrayList<MatierePremier>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	
	
	private Map< String, Charges> mapChargeFixe = new HashMap<>();
	private Map< String, MatierePremier> mapMatierPremier = new HashMap<>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	
	private ImageIcon imgModifierr;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgValider;
	
	
	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnInitialiser;
	private JButton btnAjouter;
	private JButton btnRechercher;
	private Utilisateur utilisateur;
	
	 private JComboBox comboMP;
	private ChargesDAO chargedao=new ChargesDAOImpl();
	private ChargeProductionDAO chargeproductiondao;
	private MatierePremiereDAO matierePremierDAO;
	private StockMPDAO stockmpDAO;
	private ChargeFixeDAO chargefixeDAO;
	private ChargeProductionDAO chargeproductionDAO;
	private DetailCoutProductionDAO detailcoutproductionDAO;
	private JTextField txtcodemp;
	ChargeProduction chargeproduction;
	private JTextField txtquantite;
	private JTextField txtcode;
	private JTextField txtdesignation;
	private JTextField txtlibelle=new JTextField();
	JComboBox combochargefixe = new JComboBox();
	JComboBox combodepot = new JComboBox();
	private DepotDAO depotdao;
	 JComboBox combomagasin = new JComboBox();
	 private JDateChooser dateChooser = new JDateChooser();
	private ChargeFixe chargefixe=new ChargeFixe();
	private ChargeProduction chargeProductionTmp=new ChargeProduction();
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public AjoutChargeFixeProd() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 724);
      
	
        try{ 
        	
        
             imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
        	 imgModifierr= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
             imgValider= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
             chargeproduction=new ChargeProduction();
             chargeproductiondao= new ChargeProductionDAOImpl();
             chargedao= new ChargesDAOImpl();
             matierePremierDAO= new MatierePremierDAOImpl();
         	depotdao= new DepotDAOImpl();
         	stockmpDAO= new StockMPDAOImpl();
         	chargefixeDAO= new ChargeFixeDAOImpl();
         	detailcoutproductionDAO= new DetailCoutProductionDAOImpl();
         	chargeproductionDAO= new ChargeProductionDAOImpl();
            
          } catch (Exception exp){exp.printStackTrace();}
        tablechargefixe.addMouseListener(new MouseAdapter() {
       	@Override
       	public void mouseClicked(MouseEvent arg0) {
       		comboMP.setSelectedItem(tablechargefixe.getValueAt(tablechargefixe.getSelectedRow(), 0));
       		txtquantite.setText(tablechargefixe.getValueAt(tablechargefixe.getSelectedRow(), 1).toString());
      	combodepot.setSelectedItem(listDetailChargeFixe.get(tablechargefixe.getSelectedRow()).getDepot().getLibelle());
      	combomagasin.setSelectedItem(listDetailChargeFixe.get(tablechargefixe.getSelectedRow()).getMagasin().getLibelle());
       		
       		btnAjouter.setEnabled(false);
       		 	}
       });
        
       tablechargefixe.setModel(new DefaultTableModel(
       	new Object[][] {
       	},
       	new String[] {
       		"Matire Premiere", "Quantit\u00E9", "Prix Unitaire", "Montant"
       	}
       ));
       tablechargefixe.getColumnModel().getColumn(0).setPreferredWidth(198);
       tablechargefixe.getColumnModel().getColumn(1).setPreferredWidth(87);
       tablechargefixe.getColumnModel().getColumn(2).setPreferredWidth(94);
				  		
       tablechargefixe.setShowVerticalLines(false);
       tablechargefixe.setSelectionBackground(new Color(51, 204, 255));
       tablechargefixe.setRowHeightEnabled(true);
       tablechargefixe.setBackground(new Color(255, 255, 255));
       tablechargefixe.setHighlighters(HighlighterFactory.createSimpleStriping());
       tablechargefixe.setColumnControlVisible(true);
       tablechargefixe.setForeground(Color.BLACK);
       tablechargefixe.setGridColor(new Color(0, 0, 255));
       tablechargefixe.setAutoCreateRowSorter(true);
       tablechargefixe.setBounds(2, 27, 411, 198);
       tablechargefixe.setRowHeight(20);
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(tablechargefixe);
				  		     	scrollPane.setBounds(10, 414, 1031, 264);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			  		    
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Matiere Premiere");
				  		     	titledSeparator.setBounds(10, 373, 1027, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(10, 221, 1031, 108);
				  		     	add(layeredPane);
		
		  JLabel lblCodeArticle = new JLabel("Code Mp :");
		  lblCodeArticle.setBounds(8, 10, 82, 26);
		  layeredPane.add(lblCodeArticle);
		  lblCodeArticle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  
		  JLabel lbllibelle = new JLabel("Matiere Premiere :");
		  lbllibelle.setBounds(283, 10, 90, 26);
		  layeredPane.add(lbllibelle);
		  lbllibelle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		      
		      txtcodemp = new JTextField();
		      util.Utils.copycoller(txtcodemp);
		      txtcodemp.addKeyListener(new KeyAdapter() {
		      	@Override
		      	public void keyPressed(KeyEvent e) {
		     			if(e.getKeyCode()==e.VK_ENTER)
  			      		{
  			      			if(!txtcodemp.getText().equals(""))
  			      			{
  			      				MatierePremier matierepremiere=matierePremierDAO.findByCode(txtcodemp.getText().toUpperCase());
  					    		
  					    		if(matierepremiere!=null)
  					    		{	
  					    			comboMP.setSelectedItem(matierepremiere.getNom());
  					    			
  					    		}else
  					    		{
  					    			 JOptionPane.showMessageDialog(null, "Code matiere premiere Introuvable !!!!", "Erreur", JOptionPane.ERROR_MESSAGE);
  					    		
  					    			
  					    		}
  			      				
  			      				
  			      		}else
  			      		{
  			      			 JOptionPane.showMessageDialog(null, "Veuillez  entrer code matiere premiere SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
  			      			
  			      			
  			      		}
  			      		}
  		     			
  		     			
  		     			
  		     			
  		     			
  		     		}
		      });
		      
		      
		      
		      txtcodemp.setColumns(10);
		      txtcodemp.setBounds(76, 10, 169, 26);
		      layeredPane.add(txtcodemp);
		      listChargeFixe=chargedao.findAllFixe();
		   
		       comboMP = new JComboBox();
		       comboMP.addActionListener(new ActionListener() {
		       	public void actionPerformed(ActionEvent arg0) {

		     	 		
  		     	 	if(comboMP.getSelectedIndex()!=-1)
  			 		{
  			 			if(!comboMP.getSelectedItem().equals(""))
  				 		{
  				 			
  				 		MatierePremier matierepremiere=mapMatierPremier.get(comboMP.getSelectedItem());
  				 		txtcodemp.setText(matierepremiere.getCode());
  				 		
  				 		
  				 			
  				 		}else
  				 		{
  				 			txtcodemp.setText("");
  				 		}
  				 	
  			 		}
  			 		
  			 		
  		     	 		
  		     	 	
		       	}
		       });
		      comboMP.setBounds(383, 10, 268, 27);
		      layeredPane.add(comboMP);
		      listMatierePremiere=matierePremierDAO.findAll();
		     	int j=0;
		        comboMP.addItem("");
		     	while(j<listMatierePremiere.size())
		     	{
		     		MatierePremier mp=listMatierePremiere.get(j);
		     		comboMP.addItem(mp.getNom());
		     		mapMatierPremier.put(mp.getNom(), mp);
		     		j++;
		     	}
		     	
		      
		      JLabel lblQuantit = new JLabel("Quantit\u00E9 :");
		      lblQuantit.setBounds(283, 56, 102, 26);
		      layeredPane.add(lblQuantit);
		      
		      txtquantite = new JTextField();
		      txtquantite.addKeyListener(new KeyAdapter() {
		      	@Override
		      	public void keyPressed(KeyEvent e) {

	     			if(e.getKeyCode()==e.VK_ENTER)
			      		{
			      			if(!txtquantite.getText().equals(""))
			      			{
			      				MatierePremier matierepremiere=matierePremierDAO.findByCode(txtcodemp.getText().toUpperCase());
			      				
					    		
					    		if(matierepremiere!=null)
					    		{	
					    			Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
			  		     			
		  		     				StockMP stockmp=stockmpDAO.findStockByMagasinMP(matierepremiere.getId(), magasin.getId());
		  		     				if(stockmp.getStock().compareTo(new BigDecimal(txtquantite.getText()))>=0)
		  		     				{
		  		     					
		  		     					
		  		     					
		  		     					
		  		     				}else
		  		     				{
		  		     					JOptionPane.showMessageDialog(null, "Stock de "+comboMP.getSelectedItem() + " insuffisant","Erreur",JOptionPane.ERROR_MESSAGE);
		  		     					return;
		  		     				}
					    			
					    			
					    			
					    		}else
					    		{
					    			 JOptionPane.showMessageDialog(null, "Code matiere premiere Introuvable !!!!", "Erreur", JOptionPane.ERROR_MESSAGE);
					    		return;
					    			
					    		}
			      				
			      				
			      		}else
			      		{
			      			 JOptionPane.showMessageDialog(null, "Veuillez  entrer code matiere premiere SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
			      			
			      			return;
			      		}
			      		}
		     			
		     			
		     			
		     			
		     			
		     		
		      		
		      		
		      		
		      		
		      		
		      		
		      		
		      		
		      	}
		      });
		      txtquantite.setColumns(10);
		      txtquantite.setBounds(386, 56, 191, 26);
		      layeredPane.add(txtquantite);
		      
		      JLabel label_3 = new JLabel("Depot :");
		      label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		      label_3.setBounds(668, 10, 56, 24);
		      layeredPane.add(label_3);
		     
		       combodepot = new JComboBox();
		      combodepot.addItemListener(new ItemListener() {
		      	public void itemStateChanged(ItemEvent e) {
		      		

	     	 			
		     	 		 if(e.getStateChange() == ItemEvent.SELECTED)
		     	 		 {
		     	 			int i=0;
		     	 		
		     	 				if(!combodepot.getSelectedItem().equals(""))
 		     			{
 		     				Depot depot=mapDepot.get(combodepot.getSelectedItem());
 		     				listMagasin=depotdao.listeMagasinByCategorieinDepot(depot.getId());
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
		      });
		      combodepot.setSelectedIndex(-1);
		      combodepot.setBounds(736, 11, 177, 24);
		      layeredPane.add(combodepot);
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
		      
		      JLabel label_4 = new JLabel("Magasin :");
		      label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		      label_4.setBounds(8, 56, 56, 24);
		      layeredPane.add(label_4);
		      combomagasin.addItem("");
		       combomagasin = new JComboBox();
		      combomagasin.setSelectedIndex(-1);
		      combomagasin.setBounds(76, 57, 169, 24);
		      layeredPane.add(combomagasin);
		
		
		JButton modifbutton = new JButton();
		modifbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

   			  BigDecimal stock=BigDecimal.ZERO;
	     		
   			try {
   				
   				
   				if(txtcode.getText().equals(""))
	     			{
	     				JOptionPane.showMessageDialog(null, "Veuillez entrer le code charge  SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
	     				return;
	     			}
	     			else if(dateChooser.getDate()==null)
	     			{
	     				JOptionPane.showMessageDialog(null, "Veuillez entrer la date d'opération SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
	     				return;
	     			}
	     			
	     			else if(txtdesignation.getText().equals(""))
	     			{
	     				JOptionPane.showMessageDialog(null, "Veuillez entrer la designation SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
	     				return;
	     			}
	     			else if(txtlibelle.getText().equals("") || combochargefixe.getSelectedIndex()==-1)
	     			{
	     				JOptionPane.showMessageDialog(null, "Veuillez choisir le type charge SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
	     				return;
	     			}
   				
	     			else if(txtcodemp.getText().equals("") || comboMP.getSelectedIndex()==-1)
	     			{
	     				JOptionPane.showMessageDialog(null, "Veuillez entrer la Matiere premiere SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
	     				return;
	     			}
	     			else if(combodepot.getSelectedItem().equals(""))
	     			{
	     				JOptionPane.showMessageDialog(null, "Veuillez entrer la Depot SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
	     				return;
	     			}
	     			else if(combomagasin.getSelectedItem().equals(""))
	     			{
	     				JOptionPane.showMessageDialog(null, "Veuillez entrer le Magasin de Stock SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
	     				return;
	     			}
	     			else if( txtquantite.getText().equals(""))
	     			{
	     				JOptionPane.showMessageDialog(null, "Veuillez entrer la Quantité SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
	     				return;
	     			}
	     			else if(new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO) <=0)
	     			{
	     				JOptionPane.showMessageDialog(null, "la Quantité doit etre supérieur à 0 SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
	     				return;
	     			}
	     		
	     			
	     			else
	     			{
	     			
			    		
			    		
	     			    DetailChargeFixe detailchargefixe=listDetailChargeFixe.get(tablechargefixe.getSelectedRow());
	     				StockMP stockmp=stockmpDAO.findStockByMagasinMP(listDetailChargeFixe.get(tablechargefixe.getSelectedRow()).getMatierePremier().getId(), listDetailChargeFixe.get(tablechargefixe.getSelectedRow()).getMagasin().getId());
	     				if((stockmp.getStock().add(detailchargefixe.getMontant())).compareTo(new BigDecimal(txtquantite.getText()))>=0)
	     				{
	     					stock=stockmp.getStock().add(detailchargefixe.getQuantite());
		     					stockmp.setStock(stock);
		     					stockmpDAO.edit(stockmp);
		     					stock=BigDecimal.ZERO;
		     					
		     					MatierePremier matierePremiertmp=mapMatierPremier.get(comboMP.getSelectedItem());
  		     				    Magasin magasintmp=mapMagasin.get(combomagasin.getSelectedItem());
  		     				    Depot depotTmp=mapDepot.get(combodepot.getSelectedItem());
		     					StockMP stockmpTmp=stockmpDAO.findStockByMagasinMP(matierePremiertmp.getId(),magasintmp.getId());
		     					
		     					if(stockmpTmp.getStock().compareTo(new BigDecimal(txtquantite.getText()))>=0)
		     					{
		     					stock=stockmpTmp.getStock().subtract(new BigDecimal(txtquantite.getText()));
  		     					stockmpTmp.setStock(stock);
  		     					stockmpDAO.edit(stockmpTmp);
  		     					detailchargefixe.setMatierePremier(matierePremiertmp);
  		     					detailchargefixe.setQuantite(new BigDecimal(txtquantite.getText()));
  		     					detailchargefixe.setMontant(new BigDecimal(txtquantite.getText()).multiply( stockmpTmp.getPrixUnitaire()));
  		     					detailchargefixe.setDepot(depotTmp);
  		     					detailchargefixe.setMagasin(magasintmp);
  		     				
  		     					listDetailChargeFixe.set(tablechargefixe.getSelectedRow(), detailchargefixe);
  		     						afficher_tableDetailChargefixe(listDetailChargeFixe);
  		     						intialiser();
  		     					
		     					}else
		     					{
		     						JOptionPane.showMessageDialog(null, "Stock de "+comboMP.getSelectedItem() + " insuffisant","Erreur",JOptionPane.ERROR_MESSAGE);
		     						stock=stockmp.getStock().subtract( detailchargefixe.getQuantite());
  		     					stockmp.setStock(stock);
  		     					stockmpDAO.edit(stockmp);
  		     					stock=BigDecimal.ZERO;
		     						return;
		     					}
	     					
		     				//listDetailChargeFixe.add(detailchargefixe);
		     				//afficher_tableDetailChargefixe(listDetailChargeFixe);
		     			
		     				
	     				}else
	     				{
	     					JOptionPane.showMessageDialog(null, "Stock de "+comboMP.getSelectedItem() + " insuffisant","Erreur",JOptionPane.ERROR_MESSAGE);
	     					return;
	     				}
			    			
			    			
			    		
			    		
	     				
	     				
	     				
	     			}
	     			
   				
   				
				
			} catch (NumberFormatException e) {JOptionPane.showMessageDialog(null, "la Quantité , le Prix Unitaire et le Montant doit etre numérique  ","Erreur",JOptionPane.ERROR_MESSAGE);
				
			}
   			
   			
   		
			}
		});
		modifbutton.setIcon(imgModifierr);
		modifbutton.setBounds(1051, 449, 73, 24);
		add(modifbutton);
		
		JButton suppbutton = new JButton();
		suppbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
	     	 		BigDecimal stock=BigDecimal.ZERO;
	     	 	Magasin magasin=mapMagasin.get(listDetailChargeFixe.get(tablechargefixe.getSelectedRow()).getMagasin().getLibelle());
	     	 	MatierePremier matierepremiere=mapMatierPremier.get(listDetailChargeFixe.get(tablechargefixe.getSelectedRow()).getMatierePremier().getNom());
	     	 	StockMP stockmp=stockmpDAO.findStockByMagasinMP(matierepremiere.getId(), magasin.getId()) ; 	 
	     	 stock=stockmp.getStock().add(listDetailChargeFixe.get(tablechargefixe.getSelectedRow()).getQuantite());
	     	stockmp.setStock(stock);
	      stockmpDAO.edit(stockmp);
	      listDetailChargeFixe.remove(tablechargefixe.getSelectedRow());
	     	
	     	 	afficher_tableDetailChargefixe(listDetailChargeFixe);
	     	 	intialiser();
	     	 	
				
			}
		});
		suppbutton.setIcon(imgSupprimer);
		suppbutton.setBounds(1051, 496, 73, 24);
		add(suppbutton);
		
		JButton buttonvalider = new JButton("Valider ");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 BigDecimal stock=BigDecimal.ZERO;
	     			boolean trouve=false;
	     			BigDecimal total=BigDecimal.ZERO;
	     			SimpleDateFormat dcn = new SimpleDateFormat("MMyyyy");
					 String code = dcn.format(dateChooser.getDate() );
  			try {
  				
  				
  				if(txtcode.getText().equals(""))
	     			{
	     				JOptionPane.showMessageDialog(null, "Veuillez entrer le code charge  SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
	     				return;
	     			}
	     			else if(dateChooser.getDate()==null)
	     			{
	     				JOptionPane.showMessageDialog(null, "Veuillez entrer la date d'opération SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
	     				return;
	     			}
	     			
	     			else if(txtdesignation.getText().equals(""))
	     			{
	     				JOptionPane.showMessageDialog(null, "Veuillez entrer la designation SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
	     				return;
	     			}
	     			else if(txtlibelle.getText().equals("") || combochargefixe.getSelectedIndex()==-1)
	     			{
	     				JOptionPane.showMessageDialog(null, "Veuillez choisir le type charge SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
	     				return;
	     			}
  				
	     			else if(listDetailChargeFixe.size()==0)
	     			{
	     				JOptionPane.showMessageDialog(null, "Veuillez entrer detail charge fixe SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
	     				return;
	     			}
	     			
	     			else
	     			{
	     				utilisateur= AuthentificationView.utilisateur;
	     				Charges charges=mapChargeFixe.get(combochargefixe.getSelectedItem());
	     				
	     				int i=0;
	     				while(i<listDetailChargeFixe.size())
	     				{
	     					total=total.add(listDetailChargeFixe.get(i).getMontant());
	     					i++;
	     				}
	     				
	     				
						
     					ChargeProduction chargeproduction=chargeproductiondao.findbycodeFix(code,Constantes.CHARGEST_FIX);
	if(chargeproduction!=null)
	{
		chargeproduction.setTotale(chargeproduction.getTotale().add(total));
		chargeproductiondao.edit(chargeproduction);
	}else
	{
		Date date=new Date();
	
		chargeProductionTmp.setCode(code);
		chargeProductionTmp.setCodeDepot(utilisateur.getCodeDepot());
		chargeProductionTmp.setDate(date);
		chargeProductionTmp.setDatedesaisi(dateChooser.getDate());
		chargeProductionTmp.setDateMiseJours(date);
		chargeProductionTmp.setTotale(total);
		chargeProductionTmp.setUtilisateurCreation(utilisateur);
		chargeProductionTmp.setUtilisateurMAJ(utilisateur);
		
		chargeProductionTmp.setType(Constantes.CHARGEST_FIX);
		chargeproductiondao.add(chargeProductionTmp);
		
	}
	     				
	     					
	     					chargefixe.setCode(txtcode.getText());
	     					chargefixe.setDesignation(txtdesignation.getText());
	     					chargefixe.setDateoperation(dateChooser.getDate());
	     					chargefixe.setMontant(total);
	     					chargefixe.setListdetailChargeFixe(listDetailChargeFixe);
	     					chargefixeDAO.add(chargefixe);
	     					
	     					
	     					
	     					DetailCoutProduction detailcoutproduction=detailcoutproductionDAO.findByCodeCharge(code,charges);
	     					
	     					if(detailcoutproduction!=null)
	     					{
	     						BigDecimal Montant=detailcoutproduction.getMontant();
	     						detailcoutproduction.setMontant(Montant.add(total));
	     						
	     						detailcoutproductionDAO.edit(detailcoutproduction);
	     						
	     						
	     					}
	     					else
	     					{
	     						DetailCoutProduction detailcoutproductiontmp=new DetailCoutProduction();
	     						detailcoutproductiontmp.setCharges(charges);
	     						detailcoutproductiontmp.setMontant(total);
	     						detailcoutproductiontmp.setCode(code);
	     						if(chargeproduction!=null)
	     						{
	     						detailcoutproductiontmp.setChargeProduction(chargeproduction);
	     						}else
	     						{
	     							detailcoutproductiontmp.setChargeProduction(chargeProductionTmp);
	     						}
	     						detailcoutproductionDAO.add(detailcoutproductiontmp);
	     						
	     					}
	     					
	     
	     					
	     		
	 JOptionPane.showMessageDialog(null, "Bon Charge Fixe Valider avec succée ","Bravo",JOptionPane.INFORMATION_MESSAGE);
	     					
	     					initialiserChargeFixe();
	     					intialiser();
	     				
	     					listDetailChargeFixe.clear();
	     					InitialiseTableau();
	     			}
				
  				chargefixe=new ChargeFixe();
				
				
				
			}		
			 catch (NumberFormatException t) {JOptionPane.showMessageDialog(null, "la Quantité , le Prix Unitaire et le Montant doit etre numérique  ","Erreur",JOptionPane.ERROR_MESSAGE);
			
			}
		}});
		buttonvalider.setIcon(imgValider);
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(379, 689, 112, 24);
		add(buttonvalider);
		
		JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
		GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
		gridBagLayout.rowWeights = new double[]{0.0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		titledSeparator_1.setTitle("Detail Charge Fixe");
		titledSeparator_1.setBounds(10, 180, 1031, 30);
		add(titledSeparator_1);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane_1.setBounds(10, 39, 1031, 97);
		add(layeredPane_1);
		
		JLabel label = new JLabel("Code  :");
		label.setBounds(10, 13, 89, 24);
		layeredPane_1.add(label);
		
		txtcode = new JTextField();
		txtcode.setEditable(false);
		txtcode.setColumns(10);
		txtcode.setBounds(109, 12, 183, 26);
		layeredPane_1.add(txtcode);
		
		JLabel label_1 = new JLabel("Date  :");
		label_1.setBounds(312, 13, 62, 24);
		layeredPane_1.add(label_1);
		
		 dateChooser = new JDateChooser();
		dateChooser.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				

				if(dateChooser.getDate()!=null)
				{
					
				SimpleDateFormat dcn = new SimpleDateFormat("MMyyyy");
				 String date = dcn.format(dateChooser.getDate());
				 String code=date+Utils.incrementerchargeVF(Constantes.BON_FIXE) ;
				txtcode.setText(code);

				}
			
			
				
				
				
			}
		});
		dateChooser.setLocale(Locale.FRANCE);
		dateChooser.setDateFormatString("dd/MM/yyyy");
		dateChooser.setBounds(373, 11, 181, 26);
		layeredPane_1.add(dateChooser);
		
		JLabel lblDesignation = new JLabel("Designation :");
		lblDesignation.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDesignation.setBounds(585, 13, 82, 26);
		layeredPane_1.add(lblDesignation);
		
		txtdesignation = new JTextField();
		txtdesignation.setColumns(10);
		txtdesignation.setBounds(662, 12, 359, 26);
		layeredPane_1.add(txtdesignation);
		
		JLabel lblTypeCharge = new JLabel("Type Charge  :");
		lblTypeCharge.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTypeCharge.setBounds(10, 48, 90, 26);
		layeredPane_1.add(lblTypeCharge);
		
		 combochargefixe = new JComboBox();
		 combochargefixe.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {


     	 		
		     	 	if(combochargefixe.getSelectedIndex()!=-1)
			 		{
			 			if(!combochargefixe.getSelectedItem().equals(""))
				 		{
				 			
				 		Charges charges =mapChargeFixe.get(combochargefixe.getSelectedItem());
				 		txtlibelle.setText(charges.getLiblle());
				 		
				 		}else
				 		{
				 			 txtlibelle.setText("");
				 		}
				 	
			 		}
			 		
			 		
		     	 		
		     	 	
	       	
		 		
		 	}
		 });
		combochargefixe.setBounds(110, 48, 182, 27);
		layeredPane_1.add(combochargefixe);
		int i=0;
		combochargefixe.addItem("");
		while(i<listChargeFixe.size())
		{
			Charges charge=listChargeFixe.get(i);
			combochargefixe.addItem(charge.getCode());
			mapChargeFixe.put(charge.getCode(), charge);
			i++;
		}
		
		txtlibelle = new JTextField();
		txtlibelle.setEnabled(false);
		txtlibelle.setColumns(10);
		txtlibelle.setBounds(373, 48, 229, 26);
		layeredPane_1.add(txtlibelle);
		
		JLabel lblLibelle = new JLabel("Libelle :");
		lblLibelle.setBounds(312, 48, 102, 26);
		layeredPane_1.add(lblLibelle);
		
		JXTitledSeparator titledSeparator_2 = new JXTitledSeparator();
		GridBagLayout gridBagLayout_1 = (GridBagLayout) titledSeparator_2.getLayout();
		gridBagLayout_1.rowWeights = new double[]{0.0};
		gridBagLayout_1.rowHeights = new int[]{0};
		gridBagLayout_1.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout_1.columnWidths = new int[]{0, 0, 0};
		titledSeparator_2.setTitle("Charge Fixe");
		titledSeparator_2.setBounds(10, 11, 1031, 30);
		add(titledSeparator_2);
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(390, 340, 107, 24);
		add(btnAjouter);
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	     			  BigDecimal stock=BigDecimal.ZERO;
		     			boolean trouve=false;
	     			try {
	     				
	     				
	     				if(txtcode.getText().equals(""))
		     			{
		     				JOptionPane.showMessageDialog(null, "Veuillez entrer le code charge  SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
		     				return;
		     			}
		     			else if(dateChooser.getDate()==null)
		     			{
		     				JOptionPane.showMessageDialog(null, "Veuillez entrer la date d'opération SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
		     				return;
		     			}
		     			
		     			else if(txtdesignation.getText().equals(""))
		     			{
		     				JOptionPane.showMessageDialog(null, "Veuillez entrer la designation SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
		     				return;
		     			}
		     			else if(txtlibelle.getText().equals("") || combochargefixe.getSelectedIndex()==-1)
		     			{
		     				JOptionPane.showMessageDialog(null, "Veuillez choisir le type charge SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
		     				return;
		     			}
	     				
		     			else if(txtcodemp.getText().equals("") || comboMP.getSelectedIndex()==-1)
		     			{
		     				JOptionPane.showMessageDialog(null, "Veuillez entrer la Matiere premiere SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
		     				return;
		     			}
		     			else if(combodepot.getSelectedItem().equals(""))
		     			{
		     				JOptionPane.showMessageDialog(null, "Veuillez entrer la Depot SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
		     				return;
		     			}
		     			else if(combomagasin.getSelectedItem().equals(""))
		     			{
		     				JOptionPane.showMessageDialog(null, "Veuillez entrer le Magasin de Stock SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
		     				return;
		     			}
		     			else if( txtquantite.getText().equals(""))
		     			{
		     				JOptionPane.showMessageDialog(null, "Veuillez entrer la Quantité SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
		     				return;
		     			}
		     			else if(new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)<=0)
		     			{
		     				JOptionPane.showMessageDialog(null, "la Quantité doit etre supérieur à 0 SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
		     				return;
		     			}
		     		
		     			
		     			else
		     			{
		     				int i=0;
		     				while(i<listDetailChargeFixe.size())
		     				{
		     					DetailChargeFixe detailchargefixe=listDetailChargeFixe.get(i);
		     					if(detailchargefixe.getMatierePremier().getCode().equals(txtcodemp.getText()))
		     					{
		     						trouve=true;
		     					}
		     					
		     					i++;
		     				}
		     			
				    		if(trouve==false)
				    		{
				    		ChargeFixe chargefixetmp=chargefixeDAO.findByCode(txtcode.getText());
  		     				MatierePremier matierePremier=mapMatierPremier.get(comboMP.getSelectedItem());
  		     				Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
  		     				Depot depot=mapDepot.get(combodepot.getSelectedItem());
  		     			
  		     				StockMP stockmp=stockmpDAO.findStockByMagasinMP(matierePremier.getId(), magasin.getId());
  		     				if(stockmp.getStock().compareTo(new BigDecimal(txtquantite.getText()))>=0)
  		     				{
  		     					stock=stockmp.getStock().subtract(new BigDecimal(txtquantite.getText()));
  		     					stockmp.setStock(stock);
  		     					stockmpDAO.edit(stockmp);
  		     					DetailChargeFixe detailchargefixe=new DetailChargeFixe();
  		     					
  		     					detailchargefixe.setMatierePremier(matierePremier);
  		     					detailchargefixe.setQuantite(new BigDecimal(txtquantite.getText()));
  		     					detailchargefixe.setPrixUnitaire(stockmp.getPrixUnitaire());
  		     					detailchargefixe.setMontant(stockmp.getPrixUnitaire().multiply(new BigDecimal(txtquantite.getText())));
  		     					detailchargefixe.setMagasin(magasin);
  		     					detailchargefixe.setDepot(depot);
  		     					if(chargefixetmp!=null)
  		     					{
  		     						detailchargefixe.setChargeFixe(chargefixetmp);	
  		     					}else
  		     					{
  		     						detailchargefixe.setChargeFixe(chargefixe);
  		     					}
  		     					
	  		     				listDetailChargeFixe.add(detailchargefixe);
	  		     				afficher_tableDetailChargefixe(listDetailChargeFixe);
	  		     			
	  		     				intialiser();
  		     				}else
  		     				{
  		     					JOptionPane.showMessageDialog(null, "Stock de "+comboMP.getSelectedItem() + " insuffisant","Erreur",JOptionPane.ERROR_MESSAGE);
  		     					return;
  		     				}
				    			
				    			
				    		
				    		}else
				    		{
				    			 JOptionPane.showMessageDialog(null, "la matiere premiere existe deja Veuillez le modifier !!!!", "Erreur", JOptionPane.ERROR_MESSAGE);
				    			 return;
				    		}
		     				
		     				
		     				
		     			}
		     			
	     				
	     				
					
				} catch (NumberFormatException e) {JOptionPane.showMessageDialog(null, "la Quantité , le Prix Unitaire et le Montant doit etre numérique  ","Erreur",JOptionPane.ERROR_MESSAGE);
					
				}
	     			
	     			
	     		}
		});	
		btnAjouter.setIcon(imgAjouter);
		
		  btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  btnInitialiser = new JButton("Initialiser");
		  btnInitialiser.setBounds(521, 339, 106, 23);
		  add(btnInitialiser);
		  btnInitialiser.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  	
		  		intialiser();
		  		
		  	}
		  });
		  btnInitialiser.setIcon(imgInit);
		  btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  
		  JButton button = new JButton("Initialiser");
		  button.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		
		  		
		  		initialiserChargeFixe();
		  		
		  	}
		  });
		  button.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  button.setBounds(441, 147, 106, 23);
		  button.setIcon(imgInit);
		  add(button);
		
		
	
			
		}
	void initialiserChargeFixe()
	{
		txtcode.setText("");
		dateChooser.setCalendar(null);
		txtdesignation.setText("");
		combochargefixe.setSelectedIndex(-1);
		txtlibelle.setText("");
		
		
	}
	void intialiser()
	{
		txtcodemp.setText("");
		comboMP.setSelectedIndex(-1);
		combodepot.setSelectedIndex(-1);
		combomagasin.setSelectedIndex(-1);
		txtquantite.setText("");
		
	     btnAjouter.setEnabled(true);
		
	}
	
	void InitialiseTableau()
	{
		modeleChargefixe =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Matire Premiere", "Quantit\u00E9", "Prix Unitaire", "Montant"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tablechargefixe.setModel(modeleChargefixe);
		 tablechargefixe.getColumnModel().getColumn(0).setPreferredWidth(198);
	       tablechargefixe.getColumnModel().getColumn(1).setPreferredWidth(87);
	       tablechargefixe.getColumnModel().getColumn(2).setPreferredWidth(94);
		
	
}
	
	
	void afficher_tableDetailChargefixe(List<DetailChargeFixe> listDetailChargefixe)
	{
		modeleChargefixe =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Matire Premiere", "Quantit\u00E9", "Prix Unitaire", "Montant"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tablechargefixe.setModel(modeleChargefixe);
		int i=0;
		 
		while(i<listDetailChargefixe.size())
		{	
			DetailChargeFixe detaichargefixe=listDetailChargefixe.get(i);
			MatierePremier Matierepremier=detaichargefixe.getMatierePremier();
			Object []ligne={Matierepremier.getNom(),detaichargefixe.getQuantite(),detaichargefixe.getPrixUnitaire(),NumberFormat.getNumberInstance(Locale.FRANCE).format(detaichargefixe.getMontant())};

			modeleChargefixe.addRow(ligne);
			i++;
		}
}
	}



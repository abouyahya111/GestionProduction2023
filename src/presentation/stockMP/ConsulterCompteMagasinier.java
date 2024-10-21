package presentation.stockMP;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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

import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.CompteMagasinierDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoManager.CompteMagasinierDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.StockMPDAO;
import dao.entity.CompteMagasinier;
import dao.entity.Depot;
import dao.entity.DetailCompteMagasinier;
import dao.entity.DetailEstimationPromo;

import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.StockMP;
import dao.entity.StockPF;
import dao.entity.Utilisateur;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


public class ConsulterCompteMagasinier extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;

	private JXTable table;
	private ImageIcon imgModifier;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgRechercher;
	private ImageIcon imgImprimer;
	
	private List<MatierePremier> listMatierePremiere =new ArrayList<MatierePremier>();
	private List<DetailCompteMagasinier> listDetailCompteMagasinier =new ArrayList<DetailCompteMagasinier>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private Map< String, MatierePremier> mapMatierPremier = new HashMap<>();
	private Map< String, CompteMagasinier> mapNomCompteMagasinier = new HashMap<>();
	private Map< String, CompteMagasinier> mapCodeCompteMagasinier = new HashMap<>();
	private Map< Integer, String> mapQuantite= new HashMap<>();
	private Map< Integer, String> mapPrixUnitaire= new HashMap<>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	
	private MatierePremiereDAO matierePremierDAO;
	private CompteMagasinierDAO compteMagasinierDAO;
	private StockMPDAO stockmpDAO;
	private Utilisateur utilisateur;
	private DepotDAO depotdao;
	private Map< Integer, DetailCompteMagasinier> mapImprimer = new HashMap<>();
	private CompteMagasinier compteMagasinier=new CompteMagasinier();
	private List<CompteMagasinier> listCompteMagasinier =new ArrayList<CompteMagasinier>();
	
	private JDateChooser dateChooserOperation = new JDateChooser();
	private JDateChooser dateChooserdebut;
	private JTextField textCodeMagasinier;
	private JComboBox combomp = new JComboBox();
    private	JButton btnAjouter = new JButton("Ajouter");
    private JButton modifbutton = new JButton();
    private JButton suppbutton = new JButton();
  
   private JTextField textTotal;
   JComboBox comboNom = new JComboBox();
   JDateChooser dateChooserfin = new JDateChooser();
   private ImageIcon imgSelectAll;
	private ImageIcon imgDeselectAll;
	JComboBox comboDepot = new JComboBox();
	JComboBox comboMagasin = new JComboBox();
	private DepotDAO depotDAO;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	@SuppressWarnings("unchecked")
	public ConsulterCompteMagasinier() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1290, 837);
        try{
        	depotDAO= new DepotDAOImpl();
        	matierePremierDAO= new MatierePremierDAOImpl();
        	compteMagasinierDAO= new CompteMagasinierDAOImpl();
        	stockmpDAO= new StockMPDAOImpl();
        	utilisateur= AuthentificationView.utilisateur;
        	depotdao= new DepotDAOImpl();
        	listCompteMagasinier=compteMagasinierDAO.findAll();
        	
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion √† la base de donn√©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
        
      
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
            imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
            imgImprimer = new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
            imgDeselectAll=new ImageIcon(this.getClass().getResource("/img/allDeselect.png"));
            imgSelectAll=new ImageIcon(this.getClass().getResource("/img/allSelect.png"));
          } catch (Exception exp){exp.printStackTrace();}
				  		     table = new JXTable();
				  		     table.addMouseListener(new MouseAdapter() {
				  		     	@Override
				  		     	public void mouseClicked(MouseEvent arg0) {
				  		     	
				  		     		
				  		     	}
				  		     });
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
				  		     intialiserTabeleau();
				  		   DefaultCellEditor ce = (DefaultCellEditor) table.getDefaultEditor(Object.class);
					        JTextComponent textField = (JTextComponent) ce.getComponent();
					        util.Utils.copycollercell(textField);
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(0, 272, 1136, 367);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  		   
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
				  		     	titledSeparator.setBounds(0, 205, 1136, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
				  		     	GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
				  		     	gridBagLayout.rowWeights = new double[]{0.0};
				  		     	gridBagLayout.rowHeights = new int[]{0};
				  		     	gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
				  		     	gridBagLayout.columnWidths = new int[]{0, 0, 0};
				  		     	titledSeparator_1.setTitle("Magasinier");
				  		     	titledSeparator_1.setBounds(10, 11, 1136, 30);
				  		     	add(titledSeparator_1);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(10, 47, 1136, 147);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblNom = new JLabel("Nom  :");
				  		     	lblNom.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	lblNom.setBounds(265, 64, 77, 24);
				  		     	layeredPane.add(lblNom);
				  		     	
				  		     	 dateChooserdebut = new JDateChooser();
				  		     	dateChooserdebut.setLocale(Locale.FRANCE);
				  		     	dateChooserdebut.setDateFormatString("dd/MM/yyyy");
				  		     	dateChooserdebut.setBounds(641, 14, 189, 26);
				  		     	layeredPane.add(dateChooserdebut);
				  		     	
				  		     	JLabel lblDateOpration = new JLabel("Date Debut:");
				  		     	lblDateOpration.setBounds(550, 14, 87, 26);
				  		     	layeredPane.add(lblDateOpration);
				  		     	
				  		     	JLabel lblCode = new JLabel("Code :");
				  		     	lblCode.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	lblCode.setBounds(10, 65, 67, 24);
				  		     	layeredPane.add(lblCode);
				  		     	
				  		     	textCodeMagasinier = new JTextField();
				  		     	textCodeMagasinier.addKeyListener(new KeyAdapter() {
				  		     		@Override
				  		     		public void keyPressed(KeyEvent e) {
				  		     			if(e.getKeyCode()==e.VK_ENTER)
				  			      		{
				  			      			if(!textCodeMagasinier.getText().equals(""))
				  			      			{
				  			      			CompteMagasinier compteMagasinier=mapCodeCompteMagasinier.get(textCodeMagasinier.getText());
				  					    		
				  					    		if(compteMagasinier!=null)
				  					    		{	
				  					    			comboNom.setSelectedItem(compteMagasinier.getNom());
				  					    			
				  					    		}else
				  					    		{
				  					    			 JOptionPane.showMessageDialog(null, "Code Magasinier Introuvable !!!!", "Erreur", JOptionPane.ERROR_MESSAGE);
				  					    			comboNom.setSelectedItem("");
				  					    			textCodeMagasinier.requestFocus();
				  					    		}
				  			      				
				  			      				
				  			      		}else
				  			      		{
				  			      			 JOptionPane.showMessageDialog(null, "Veuillez  entrer code magasinier SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
				  			      			comboNom.setSelectedItem("");
		  					    			textCodeMagasinier.requestFocus();
				  			      		}
				  			      		}
				  		     			
				  		     			
				  		     			
				  		     			
				  		     			
				  		     			
				  		     		}
				  		     	});
				  		     	textCodeMagasinier.setColumns(10);
				  		     	textCodeMagasinier.setBackground(Color.WHITE);
				  		     	textCodeMagasinier.setBounds(66, 64, 189, 24);
				  		     	layeredPane.add(textCodeMagasinier);
				  		     	
				  		     	JButton btnConsulter = new JButton("Consulter");
				  		     	btnConsulter.addActionListener(new ActionListener() {
				  		     		public void actionPerformed(ActionEvent e) {
				  		     			String req=" ";
				  		     		  DecimalFormat format = new DecimalFormat("#0.00");
				  		     		BigDecimal MontantTotaltmp ;
				  		     		BigDecimal number ; 
									BigDecimal MontantTotal = new BigDecimal("0");
				  		     			
				  		     			if(textCodeMagasinier.getText().equals(""))
				  		     			{
				  		     				JOptionPane.showMessageDialog(null, "Veuillez entrer le code magasinier SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				  		     				vidertotalettableau();
				  		     				return;
				  		     			}else if(comboNom.getSelectedItem().equals(""))
				  		     			{
				  		     				JOptionPane.showMessageDialog(null, "Veuillez entrer le Nom magasinier SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				  		     				vidertotalettableau();
				  		     				return;
				  		     			}


				  		     			else
				  		     			{
				  		     				
				  		     				
				  		     				if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null)
				  				    		{
				  				    			String d1=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
				  				    			String d2=((JTextField)dateChooserfin.getDateEditor().getUiComponent()).getText();
				  				    			
				  				    		if(!d1.equals(d2))
				  				    		{
				  				    			if(dateChooserfin.getDate().compareTo(dateChooserdebut.getDate())<0)
				  				    			{
				  				    				JOptionPane.showMessageDialog(null, "date de fin doit etre supÈrieur au date debut SVP !!!");
				  				    				return;
				  				    			}
				  				    			
				  				    		}
				  				    		
				  				    	
				  				    		
				  				    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null)
				  				    		{
				  				    			
				  				    			dateChooserfin.setDate(dateChooserdebut.getDate());
				  				    			
				  				    		}else if(dateChooserdebut.getDate()==null && dateChooserfin.getDate()!=null)
				  				    		{
				  				    			
				  				    			dateChooserdebut.setDate(dateChooserfin.getDate());
				  				    			
				  				    		}else if (dateChooserdebut.getDate()==null && dateChooserfin.getDate()==null)
				  				    		{
				  				    			JOptionPane.showMessageDialog(null, "Veuillez entrer la date  SVP !!!");
			  				    				return;
				  				    		}
				  		     				
				  		     				String d1=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
			  				    			String d2=((JTextField)dateChooserfin.getDateEditor().getUiComponent()).getText();
				  		     				
				  		     				if(comboDepot.getSelectedItem()!=null)
				  		     				{
				  		     					if(!comboDepot.getSelectedItem().equals(""))
				  		     					{
				  		     						Depot depot=mapDepot.get(comboDepot.getSelectedItem().toString());
				  		     								if(depot!=null)
				  		     								{
				  		     									req=req+" and compteMagasinier.depot.id='"+depot.getId()+"' ";
				  		     								}
				  		     					}
				  		     				}
			  				    			
			  				    			
				  		     				if(comboMagasin.getSelectedItem()!=null)
				  		     				{
				  		     					if(!comboMagasin.getSelectedItem().equals(""))
				  		     					{
				  		     						Magasin magasin=mapMagasin.get(comboMagasin.getSelectedItem().toString());
				  		     								if(magasin!=null)
				  		     								{
				  		     									req=req+" and compteMagasinier.magasin.id='"+magasin.getId()+"' ";
				  		     								}
				  		     					}
				  		     				}
				  		     				
				  		     				
				  		     				req=req+" and compteMagasinier.dateoperation between '"+d1+"' and '"+d2+"' ";
				  		     				
				  		     				CompteMagasinier compteMagasinier=mapCodeCompteMagasinier.get(textCodeMagasinier.getText());
				  		     				if(compteMagasinier!=null)
				  		     				{
				  		     			 
				  		     					req=req+" and compteMagasinier.id='"+compteMagasinier.getId()+"' ";
				  		     					
				  		     					
				  		     					listDetailCompteMagasinier =compteMagasinierDAO.findDetailCompteMagasinierByReq(req) ;

				  		     					if(listDetailCompteMagasinier.size()!=0)
				  		     					{
				  		     						int i=0;
				  		     						while(i<listDetailCompteMagasinier.size())
				  		     						{
				  		     							DetailCompteMagasinier detailcomptemagasinier=listDetailCompteMagasinier.get(i);
				  		     							number =new BigDecimal(listDetailCompteMagasinier.get(i).getMontant()+"");
				  		     							number=number.setScale(2,BigDecimal.ROUND_DOWN);
				  		     							MontantTotaltmp=number;
				  		     							detailcomptemagasinier.setMontant(MontantTotaltmp);
				  		     							listDetailCompteMagasinier.set(i, detailcomptemagasinier);
				  		     							MontantTotal=MontantTotal.add(number);
				  		     							i++;
				  		     						}
				  		     						afficher_tableDetailCompteMagasinier(listDetailCompteMagasinier);
				  		     						textTotal.setText(format.format(MontantTotal)+" "+ Constantes.POURCENTAGEDH);
				  		     						
				  		     					}
				  		     					else
				  		     					{
				  		     						JOptionPane.showMessageDialog(null, "Compte Magasinier n'a aucun details au date spÈcifie","Erreur",JOptionPane.ERROR_MESSAGE);
				  		     						vidertotalettableau();
				  		     					}

				  		     				}else
				  		     				{
				  		     					JOptionPane.showMessageDialog(null, "Compte Magasinier Introuvable ","Erreur",JOptionPane.ERROR_MESSAGE);
				  		     					vidertotalettableau();
				  		     				}
				  		     			}
				  		     			
				  		     			
				  		     		}
				  		     	});
				  		     	btnConsulter.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	btnConsulter.setBounds(417, 111, 107, 24);
				  		     	layeredPane.add(btnConsulter);
				  		     	
				  		     	JButton btnInitialiser = new JButton("Initialiser");
				  		     	btnInitialiser.addActionListener(new ActionListener() {
				  		     		public void actionPerformed(ActionEvent arg0) {
				  		     			
				  		     			initialiser();
				  		     		}
				  		     	});
				  		     	btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	btnInitialiser.setBounds(537, 112, 107, 24);
				  		     	btnInitialiser.setIcon(imgInit);
				  		     	layeredPane.add(btnInitialiser);
				  		     	
				  		     	 comboNom = new JComboBox();
				  		     	 comboNom.addItemListener(new ItemListener() {
				  		     	 	public void itemStateChanged(ItemEvent arg0) {
				  		     	 		
				  		     	 	if(!comboNom.getSelectedItem().equals(""))	
				  		     	 	{
				  		     	 		
				  		     	 		CompteMagasinier compteMagasinier=mapNomCompteMagasinier.get(comboNom.getSelectedItem());
				  		     	 		
				  		     	 		if(compteMagasinier!=null)
				  		     	 		{
				  		     	 			
				  		     	 		textCodeMagasinier.setText(compteMagasinier.getCode());
				  		     	 			
				  		     	 			
				  		     	 		}else
				  		     	 		{
				  		     	 		textCodeMagasinier.setText("");
				  		     	 		}
				  		     	 		
				  		     	 	}else
				  		     	 	{
				  		     	 	textCodeMagasinier.setText("");
				  		     	 	}
				  		     	 		
				  		     	 	}
				  		     	 });
				  		     	comboNom.setSelectedIndex(-1);
				  		     	comboNom.setBounds(307, 67, 232, 24);
				  		     	layeredPane.add(comboNom);
				  		     	AutoCompleteDecorator.decorate(comboNom);
				  		     	
				  		     	listMatierePremiere=matierePremierDAO.findAll();
				  		     	int i=0;
				  		     	while(i<listMatierePremiere.size())
				  		     	{
				  		     		MatierePremier mp=listMatierePremiere.get(i);
				  		     		combomp.addItem(mp.getNom());
				  		     		mapMatierPremier.put(mp.getNom(), mp);
				  		     		i++;
				  		     	}
				  		     	
				  		     	 
				  		     	
				  		     	textTotal = new JTextField();
				  		     	textTotal.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
				  		     	textTotal.setEditable(false);
				  		     	textTotal.setColumns(10);
				  		     	textTotal.setBackground(Color.WHITE);
				  		     	textTotal.setBounds(953, 650, 183, 24);
				  		     	add(textTotal);
				  		     	
				  		     	JLabel lblMontantTotal = new JLabel("Montant Total :");
				  		     	lblMontantTotal.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	lblMontantTotal.setBounds(835, 650, 108, 24);
				  		     	add(lblMontantTotal);
				  		     	
				  		     	JButton btnConsulterCompteMagasinier = new JButton("Consulter Compte Magasinier");
				  		     	btnConsulterCompteMagasinier.addActionListener(new ActionListener() {
				  		     		public void actionPerformed(ActionEvent arg0) {
				  		     			if(listDetailCompteMagasinier.size()!=0)
				  		     			{
				  		     				
				  		     				BigDecimal MontantTotal=BigDecimal.ZERO;
				  		     				
				  		     				List<DetailCompteMagasinier> listDetailCompteMagasinierImprimer =new ArrayList<DetailCompteMagasinier>();	
				  		     				
				  		     				mapImprimer.clear();
				  		  	    		if(!remplirMapImprimer())	{
				  		  					JOptionPane.showMessageDialog(null, "Il faut remplir les Comptes Magasinier ‡ imprimer SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
				  		  				} else {
				  		  					
				  		  					
				  		  				if(textCodeMagasinier.getText().equals(""))
				  		     			{
				  		     				JOptionPane.showMessageDialog(null, "Veuillez entrer le code magasinier SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				  		     				vidertotalettableau();
				  		     				return;
				  		     			}else if(comboNom.getSelectedItem().equals(""))
				  		     			{
				  		     				JOptionPane.showMessageDialog(null, "Veuillez entrer le Nom magasinier SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				  		     				vidertotalettableau();
				  		     				return;
				  		     			}
				  		     			
				  		     			else
				  		     			{
				  		     				
				  		     				
				  		     				for(int t=0;t<listDetailCompteMagasinier.size();t++)
				  		     				{
				  		     					
				  		     					if(mapImprimer.containsKey(listDetailCompteMagasinier.get(t).getId()))
				  								{
				  		     						
				  		     						listDetailCompteMagasinierImprimer.add(listDetailCompteMagasinier.get(t));
				  		     						MontantTotal=MontantTotal.add(listDetailCompteMagasinier.get(t).getMontant());
				  		     						
				  								}
				  		     					
				  		     					
				  		     					
				  		     					
				  		     					
				  		     					
				  		     					
				  		     					
				  		     				}
				  		     				
				  		     				
				  		     				
				  		     				
				  		     				
				  		     				if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null)
				  				    		{
				  				    			String d1=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
				  				    			String d2=((JTextField)dateChooserfin.getDateEditor().getUiComponent()).getText();
				  				    			
				  				    		if(!d1.equals(d2))
				  				    		{
				  				    			if(dateChooserfin.getDate().compareTo(dateChooserdebut.getDate())<0)
				  				    			{
				  				    				JOptionPane.showMessageDialog(null, "date de fin doit etre supÈrieur au date debut SVP !!!");
				  				    				return;
				  				    			}
				  				    			
				  				    		}
				  				    		
				  				    	
				  				    		
				  				    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null)
				  				    		{
				  				    			
				  				    			dateChooserfin.setDate(dateChooserdebut.getDate());
				  				    			
				  				    		}else if(dateChooserdebut.getDate()==null && dateChooserfin.getDate()!=null)
				  				    		{
				  				    			
				  				    			dateChooserdebut.setDate(dateChooserfin.getDate());
				  				    			
				  				    		}else if (dateChooserdebut.getDate()==null && dateChooserfin.getDate()==null)
				  				    		{
				  				    			JOptionPane.showMessageDialog(null, "Veuillez entrer la date  SVP !!!");
			  				    				return;
				  				    		}
				  		     				
				  		     				String d1=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
			  				    			String d2=((JTextField)dateChooserfin.getDateEditor().getUiComponent()).getText();
				  		     				
				  		     				Map parameters = new HashMap();
				  		     				parameters.put("code", textCodeMagasinier.getText());
				  		     				parameters.put("nom", comboNom.getSelectedItem());
				  		     				parameters.put("date", "Entre "+d1 +" Et "+d2);
				  		     				parameters.put("total", MontantTotal.toString());
				  		     				JasperUtils.imprimerCompteMagasinier(listDetailCompteMagasinierImprimer,parameters);
				  		     				
				  		     			}
			  		     			
				  		  					
				  		  					
				  		  					
				  		  					
				  		  				}
				  		     				
				  		     				
				  		     		
				  		     				
				  		     				
				  		     			}else
				  		     			{
				  		     			 JOptionPane.showMessageDialog(null, "Il n'existe pas aucun detail pour cet magasinier dans cette pÈriode!! ", "Erreur", JOptionPane.ERROR_MESSAGE); 
				  		     			}
				  		     		
				  		     			
				  		     			
				  		     		}
				  		     	});
				  		     	btnConsulterCompteMagasinier.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	btnConsulterCompteMagasinier.setBounds(458, 650, 206, 23);
				  		     	btnConsulterCompteMagasinier.setIcon(imgImprimer);
				  		     	add(btnConsulterCompteMagasinier);
	
				  		     	
				  		     	comboNom.addItem("");	  		     	
				  		     	
				  		     	 dateChooserfin = new JDateChooser();
				  		     	dateChooserfin.setLocale(Locale.FRANCE);
				  		     	dateChooserfin.setDateFormatString("dd/MM/yyyy");
				  		     	dateChooserfin.setBounds(914, 14, 189, 26);
				  		     	layeredPane.add(dateChooserfin);
				  		     	
				  		     	JLabel lblDateFin = new JLabel("Date Fin :");
				  		     	lblDateFin.setBounds(857, 14, 67, 26);
				  		     	layeredPane.add(lblDateFin);
				  		     	
				  		     	JLabel label = new JLabel("D\u00E9pot :");
				  		     	label.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	label.setBounds(10, 14, 96, 24);
				  		     	layeredPane.add(label);
				  		     	
				  		     	  comboDepot = new JComboBox();
				  		     	  comboDepot.addItemListener(new ItemListener() {
				  		     	  	public void itemStateChanged(ItemEvent e) {
				  		     	  	

					  		     	 	
					  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
					  		     	 	 List<Magasin> listMagasin=new ArrayList<Magasin>();
						  		     	  	 // comboGroupe = new JComboBox();
					  		     	 	comboMagasin.removeAllItems();
					  		     	 Depot depot=new Depot();
					  		     	 	//comboGroupe.addItem("");
					  		     	 	if(!comboDepot.getSelectedItem().equals(""))
						  		     	  	   	 depot = mapDepot.get(comboDepot.getSelectedItem());
								  		       
						  		     	  		listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_MP);
								  		      if(listMagasin!=null){
								  		    	  
								  		    	  int j=0;
									  		      	while(j<listMagasin.size())
									  		      		{	
									  		      			Magasin magasin=listMagasin.get(j);
									  		      			comboMagasin.addItem(magasin.getLibelle());
									  		      			mapMagasin.put(magasin.getLibelle(), magasin);
									  		      			j++;
									  		      		}
								  		      }
					  		     	 	 }
					  		     	 	
				  		     	  		
				  		     	  		
				  		     	  	}
				  		     	  });
				  		     	comboDepot.setBounds(69, 14, 144, 24);
				  		     	layeredPane.add(comboDepot);
				  		     	
				  		     	JLabel label_1 = new JLabel("Magasin :");
				  		     	label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	label_1.setBounds(223, 14, 102, 24);
				  		     	layeredPane.add(label_1);
				  		     	
				  		     	  comboMagasin = new JComboBox();
				  		     	comboMagasin.setBounds(290, 15, 191, 24);
				  		     	layeredPane.add(comboMagasin);
				  		     	
				  		     	JButton btnDeslectionnerTout = new JButton();
				  		     	btnDeslectionnerTout.addActionListener(new ActionListener() {
				  		     		public void actionPerformed(ActionEvent e) {
				  		     			

				  			     		

				  			     		
				  			     		
				  			     		for(int i=0;i<table.getRowCount();i++)
				  			     		{
				  			     			table.setValueAt(false, i, 6);
				  			     		}
				  			     	
				  			     	
				  		     			
				  		     		}
				  		     	});
				  		     	btnDeslectionnerTout.setToolTipText("deselectionner Tout");
				  		     	btnDeslectionnerTout.setBounds(1081, 235, 26, 26);
				  		     	btnDeslectionnerTout.setIcon(imgSelectAll);
				  		     	add(btnDeslectionnerTout);
				  		     	
				  		     	JButton btnSelectionnertout = new JButton();
				  		     	btnSelectionnertout.addActionListener(new ActionListener() {
				  		     		public void actionPerformed(ActionEvent arg0) {
				  		     			


				  			    		
				  			    		for(int i=0;i<table.getRowCount();i++)
				  			     		{
				  			     			table.setValueAt(true, i, 6);
				  			     		}
				  			    	
				  			     		
				  			     	
				  		     			
				  		     		}
				  		     	});
				  		     	btnSelectionnertout.setToolTipText("Selectionner Tout");
				  		     	btnSelectionnertout.setBounds(1110, 235, 26, 26);
				  		     	btnSelectionnertout.setIcon(imgDeselectAll);
				  		     	add(btnSelectionnertout);
		for(int j=0;j<listCompteMagasinier.size();j++)
		{
			
			CompteMagasinier compteMagasinier=listCompteMagasinier.get(j);
			
			comboNom.addItem(compteMagasinier.getNom());
			mapNomCompteMagasinier.put(compteMagasinier.getNom(), compteMagasinier);
			mapCodeCompteMagasinier.put(compteMagasinier.getCode(), compteMagasinier);
			
			
		}
		  comboDepot.addItem("");
		
	      if(!utilisateur.getCodeDepot().equals(CODE_DEPOT_SIEGE)	) {
		    	Depot depot=  depotDAO.findByCode(utilisateur.getCodeDepot());
	    		comboDepot.addItem(depot.getLibelle());
	    		
	    		
	    		List<Magasin> 	listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_MP);
	  		      if(listMagasin!=null){
	  		    	  
	  		    	  int j=0;
		  		      	while(j<listMagasin.size())
		  		      		{	
		  		      			Magasin magasin=listMagasin.get(j);
		  		      			comboMagasin.addItem(magasin.getLibelle());
		  		      			mapMagasin.put(magasin.getLibelle(), magasin);
		  		      			j++;
		  		      		}
	  		      }
	    }else {
	    	
	    	listDepot = depotDAO.findAll();	
		      int j=0;
		      	while(j<listDepot.size())
		      		{	
		      			Depot depot=listDepot.get(j);
		      			mapDepot.put(depot.getLibelle(), depot);
		      			comboDepot.addItem(depot.getLibelle());
		      			i++;
		      		}
	    	
	    }
	      
	    comboDepot.setSelectedItem("");
				  		 
	}

	
	 void initialiser()
	 {
		
		textCodeMagasinier.setText("");
		comboNom.setSelectedItem("");
		dateChooserdebut.setCalendar(null);
		
		textCodeMagasinier.requestDefaultFocus();
		 
	 }
	 
	 
	 void vidertotalettableau()
	 {
		 textTotal.setText("");
		 intialiserTabeleau();
	 }

void intialiserTabeleau(){
	
	modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Date","Designation", "Matiere Premiere", "Quantit\u00E9", "Prix Unitaire", "Montant","Imprimer"
		     	}
		     ) {
		boolean[] columnEditables = new boolean[] {
				false,false,false,false,false,false,true
		};
		Class[] columnTypes = new Class[] {
				Date.class,String.class,String.class,BigDecimal.class,BigDecimal.class,BigDecimal.class, Boolean.class
			};
		  public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}
	};
		     
		   table.setModel(new DefaultTableModel(
		   	new Object[][] {
		   	},
		   	new String[] {
		   			"Date","Designation", "Matiere Premiere", "Quantit\u00E9", "Prix Unitaire", "Montant","Imprimer"
		   	}
		   ));
		   table.getColumnModel().getColumn(0).setPreferredWidth(289);
		   table.getColumnModel().getColumn(1).setPreferredWidth(123);
		   table.getColumnModel().getColumn(2).setPreferredWidth(84);
		   table.getColumnModel().getColumn(3).setPreferredWidth(88);
		  
		   
}


void afficher_tableDetailCompteMagasinier(List<DetailCompteMagasinier> listDetailCompteMagasinier)
{
	modeleMP =new DefaultTableModel(
	     	new Object[][] {
	     	},
	     	new String[] {
	     			"Date","Designation", "Matiere Premiere", "Quantit\u00E9", "Prix Unitaire", "Montant","Imprimer"
	     	}
	     ) {
		boolean[] columnEditables = new boolean[] {
				false,false,false,false,false,false,true
		};
		Class[] columnTypes = new Class[] {
				Date.class,String.class,String.class,BigDecimal.class,BigDecimal.class,BigDecimal.class, Boolean.class
			};
		  public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}
	};
	     table.setModel(modeleMP);
	int i=0;
	BigDecimal montant ; 
	  DecimalFormat format = new DecimalFormat("#0.00");
	while(i<listDetailCompteMagasinier.size())
	{	
		DetailCompteMagasinier detailCompteMagasinier=listDetailCompteMagasinier.get(i);
		MatierePremier matierepremiere=detailCompteMagasinier.getMatierePremier();
		
		String designation=detailCompteMagasinier.getDesignation();
		String mp=matierepremiere.getNom();
		BigDecimal quantite =detailCompteMagasinier.getQuantite();
		BigDecimal prix=detailCompteMagasinier.getPrix();
		
		 montant=new BigDecimal(detailCompteMagasinier.getMontant()+"");
		 montant=montant.setScale(2,BigDecimal.ROUND_DOWN);
		Object []ligne={detailCompteMagasinier.getDateoperation(), designation,mp,NumberFormat.getNumberInstance(Locale.FRANCE).format(quantite),format.format(prix),NumberFormat.getNumberInstance(Locale.FRANCE).format(montant),false};

		modeleMP.addRow(ligne);
		i++;
	}
}



boolean remplirMapImprimer(){
	boolean trouve=false;
	int i=0;
			
	for(int j=0;j<table.getRowCount();j++){
		
		boolean imprimer=(boolean) table.getValueAt(j, 6);
		if(imprimer==true ){
			DetailCompteMagasinier detailCompteMagasinier=listDetailCompteMagasinier.get(j);
			
			mapImprimer.put(detailCompteMagasinier.getId(), detailCompteMagasinier);
			i++;
			trouve=true;
		}
		
	}
	return trouve;
}
}

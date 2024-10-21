package presentation.stockMP;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.NumberFormat;
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
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;

import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargeVariableDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.FraisDepotDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoManager.ChargeProductionDAO;
import dao.daoManager.ChargeVariableDAO;
import dao.daoManager.ChargesDAO;
import dao.daoManager.CompteMagasinierDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.FraisDepotDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.StockMPDAO;
import dao.entity.ChargeProduction;
import dao.entity.ChargeVariable;
import dao.entity.Charges;
import dao.entity.CompteMagasinier;
import dao.entity.Depot;
import dao.entity.DetailChargeVariable;
import dao.entity.DetailCompteMagasinier;
import dao.entity.DetailEstimationPromo;
import dao.entity.DetailFraisDepot;
import dao.entity.FraisDepot;
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


public class AjoutChargeDepot extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;

	private JXTable table;
	private ImageIcon imgModifier;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgRechercher;;
	
	private List<MatierePremier> listMatierePremiere =new ArrayList<MatierePremier>();
	private List<DetailFraisDepot> listDetailFraisDepot =new ArrayList<DetailFraisDepot>();
	private List<DetailChargeVariable> listDetailChargeVariable =new ArrayList<DetailChargeVariable>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private Map< String, MatierePremier> mapMatierPremier = new HashMap<>();
	
	private Map< Integer, String> mapQuantite= new HashMap<>();
	private Map< Integer, String> mapPrixUnitaire= new HashMap<>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	
	private MatierePremiereDAO matierePremierDAO;
	private FraisDepotDAO fraisdepotDAO;;
	private StockMPDAO stockmpDAO;
	private ChargesDAO chargedao;
	private Utilisateur utilisateur;
	private DepotDAO depotdao;
	private ChargeProductionDAO chargeproductiondao;
	private ChargeVariableDAO chargevariableDAO;
	
	private FraisDepot fraisDepot=new FraisDepot();
	ChargeProduction chargeproduction=new ChargeProduction();
	private JTextField textcodedepot;
	private JTextField textcodemp;
	
	private JTextField textdesignation;
	private JTextField textquantite;
	private JComboBox combomp = new JComboBox();
    private	JButton btnAjouter = new JButton("Ajouter");
    private JButton modifbutton = new JButton();
    private JButton suppbutton = new JButton();
   private JComboBox comboMagasin = new JComboBox();
   private JComboBox comboDepot = new JComboBox();
   private JDateChooser dateChooserOperation = new JDateChooser();
   private ChargeVariable chargevariable=new ChargeVariable();
   
   
   
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	@SuppressWarnings("unchecked")
	public AjoutChargeDepot() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 837);
        try{
        	
        	matierePremierDAO= new MatierePremierDAOImpl();
        	fraisdepotDAO= new FraisDepotDAOImpl();
        	stockmpDAO= new StockMPDAOImpl();
        	utilisateur= AuthentificationView.utilisateur;
        	depotdao= new DepotDAOImpl();
        	  chargedao= new ChargesDAOImpl();
        	  chargeproductiondao= new ChargeProductionDAOImpl();
        	  chargevariableDAO= new ChargeVariableDAOImpl();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
        
      
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
            imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
          } catch (Exception exp){exp.printStackTrace();}
				  		     table = new JXTable();
				  		     table.addMouseListener(new MouseAdapter() {
				  		     	@Override
				  		     	public void mouseClicked(MouseEvent arg0) {
				  		     		
				  		     		MatierePremier mp=mapMatierPremier.get(table.getValueAt(table.getSelectedRow(), 0).toString());
				  		     		textcodemp.setText(mp.getCode());
				  		     	    combomp.setSelectedItem(mp.getNom());
				  		     	    textquantite.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
				  		     		btnAjouter.setEnabled(false);
				  	       			modifbutton.setEnabled(true);
				  	       			suppbutton.setEnabled(true);
				  		     		
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
				  		     	scrollPane.setBounds(10, 416, 1136, 367);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  		   
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
				  		     	titledSeparator.setBounds(10, 387, 1136, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
				  		     	GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
				  		     	gridBagLayout.rowWeights = new double[]{0.0};
				  		     	gridBagLayout.rowHeights = new int[]{0};
				  		     	gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
				  		     	gridBagLayout.columnWidths = new int[]{0, 0, 0};
				  		     	titledSeparator_1.setTitle("Frais Depot");
				  		     	titledSeparator_1.setBounds(10, 11, 1136, 30);
				  		     	add(titledSeparator_1);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(10, 47, 1136, 111);
				  		     	add(layeredPane);
				  		     	
				  		     	 dateChooserOperation = new JDateChooser();
				  		     	dateChooserOperation.setLocale(Locale.FRANCE);
				  		     	dateChooserOperation.setDateFormatString("dd/MM/yyyy");
				  		     	dateChooserOperation.setBounds(847, 57, 174, 26);
				  		     	layeredPane.add(dateChooserOperation);
				  		     	
				  		     	JLabel lblDateOpration = new JLabel("Date Op\u00E9ration :");
				  		     	lblDateOpration.setBounds(721, 57, 116, 26);
				  		     	layeredPane.add(lblDateOpration);
				  		     	
				  		     	JLabel lblCode = new JLabel("Code :");
				  		     	lblCode.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	lblCode.setBounds(133, 24, 67, 24);
				  		     	layeredPane.add(lblCode);
				  		     	
				  		     	textcodedepot = new JTextField();
				  		     	textcodedepot.addKeyListener(new KeyAdapter() {
				  		     		@Override
				  		     		public void keyPressed(KeyEvent e) {
				  		     			if(e.getKeyCode()==e.VK_ENTER)
				  			      		{
				  			      			if(!textcodedepot.getText().equals(""))
				  			      			{
				  			      			FraisDepot fraisDepot=fraisdepotDAO.findByCode(textcodedepot.getText());
				  					    		
				  					    		if(fraisDepot!=null)
				  					    		{	
				  					    			textcodedepot.setText(fraisDepot.getCode());
				  					    			textdesignation.setText(fraisDepot.getDesignation());
				  					    			comboMagasin.setSelectedItem(fraisDepot.getMagasin().getLibelle());
				  					    			comboDepot.setSelectedItem(fraisDepot.getDepot().getLibelle());
				  					    			
				  					    		}else
				  					    		{
				  					    			 JOptionPane.showMessageDialog(null, "Code Magasinier Introuvable !!!!", "Erreur", JOptionPane.ERROR_MESSAGE);
				  					    		
				  					    			textcodedepot.requestFocus();
				  					    		}
				  			      				
				  			      				
				  			      		}else
				  			      		{
				  			      			 JOptionPane.showMessageDialog(null, "Veuillez  entrer code magasinier SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
				  			      		
		  					    			textcodedepot.requestFocus();
				  			      		}
				  			      		}
				  		     			
				  		     			
				  		     			
				  		     		}
				  		     	});
				  		     	textcodedepot.setColumns(10);
				  		     	textcodedepot.setBackground(Color.WHITE);
				  		     	textcodedepot.setBounds(189, 23, 174, 24);
				  		     	layeredPane.add(textcodedepot);
				  		     	
				  		     	JLabel lblDesignation = new JLabel("Designation :");
				  		     	lblDesignation.setBounds(390, 24, 77, 24);
				  		     	layeredPane.add(lblDesignation);
				  		     	lblDesignation.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	
				  		     	textdesignation = new JTextField();
				  		     	textdesignation.setBounds(484, 24, 537, 24);
				  		     	layeredPane.add(textdesignation);
				  		     	textdesignation.setColumns(10);
				  		     	textdesignation.setBackground(Color.WHITE);
				  		     	
				  		     	JLabel lblDepot = new JLabel("Depot :");
				  		     	lblDepot.setBounds(133, 59, 56, 24);
				  		     	layeredPane.add(lblDepot);
				  		     	lblDepot.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	
				  		     	 comboDepot = new JComboBox();
				  		     	 comboDepot.setBounds(186, 60, 177, 24);
				  		     	 layeredPane.add(comboDepot);
				  		     	 comboDepot.addItem("");
				  		     	 
				  		     	 	comboDepot.addItemListener(new ItemListener() {
				  		     	 		public void itemStateChanged(ItemEvent e) {
				  		     	 			
				  		     	 		 if(e.getStateChange() == ItemEvent.SELECTED)
				  		     	 		 {
				  		     	 			int i=0;
				  		     	 		
				  		     	 				if(!comboDepot.getSelectedItem().equals(""))
					  		     			{
					  		     				Depot depot=mapDepot.get(comboDepot.getSelectedItem());
					  		     				listMagasin=depotdao.listeMagasinByCategorieinDepot(depot.getId());
					  		     				if(listMagasin.size()!=0)
					  		     				{
					  		     					comboMagasin.removeAllItems();
					  		     					comboMagasin.addItem("");
					  		     					while(i<listMagasin.size())
						  		     				{
						  		     					Magasin magasin=listMagasin.get(i);
						  		     					comboMagasin.addItem(magasin.getLibelle());
						  		     					mapMagasin.put(magasin.getLibelle(), magasin);
						  		     					i++;
						  		     				}
					  		     				}else
					  		     				{
					  		     					comboMagasin.removeAllItems();
					  		     					
					  		     				}
					  		     				
					  		     				
					  		     			}else
					  		     			{
					  		     				comboMagasin.removeAllItems();
					  		     				
					  		     			}
				  		     	 				
				  		     	 			
				  		     	 			 
				  		     	 			 
				  		     	 			 
				  		     	 			 
				  		     	 		 }
				  		     	 			
				  		     	 			
				  		     	 		}
				  		     	 	});
				  		     	 	
				  		     
				  		     	 	comboDepot.setSelectedIndex(-1);
				  		     	 	
				  		     	 	JLabel lblMagasin = new JLabel("Magasin :");
				  		     	 	lblMagasin.setBounds(390, 58, 56, 24);
				  		     	 	layeredPane.add(lblMagasin);
				  		     	 	lblMagasin.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	 	
				  		     	 	   	 comboMagasin = new JComboBox();
				  		     	 	   	 comboMagasin.setBounds(483, 59, 216, 24);
				  		     	 	   	 layeredPane.add(comboMagasin);
				  		     	 	   	 comboMagasin.addItem("");
				  		     	 	   	 comboMagasin.addItemListener(new ItemListener() {
				  		     	 	   	 	public void itemStateChanged(ItemEvent e) {}
				  		     	 	   	 });
				  		     	 	   	 
				  		     	 	   	 
				  		     	 	   	 	comboMagasin.setSelectedIndex(-1);
				  		     	
				  		     	JXTitledSeparator titledSeparator_2 = new JXTitledSeparator();
				  		     	GridBagLayout gridBagLayout_1 = (GridBagLayout) titledSeparator_2.getLayout();
				  		     	gridBagLayout_1.rowWeights = new double[]{0.0};
				  		     	gridBagLayout_1.rowHeights = new int[]{0};
				  		     	gridBagLayout_1.columnWeights = new double[]{0.0, 0.0, 0.0};
				  		     	gridBagLayout_1.columnWidths = new int[]{0, 0, 0};
				  		     	titledSeparator_2.setTitle("Detail Frais Depot");
				  		     	titledSeparator_2.setBounds(10, 211, 1136, 30);
				  		     	add(titledSeparator_2);
				  		     	
				  		     	JLayeredPane layeredPane_1 = new JLayeredPane();
				  		     	layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane_1.setBounds(10, 254, 1136, 72);
				  		     	add(layeredPane_1);
				  		     	
				  		     	textcodemp = new JTextField();
				  		     	textcodemp.addKeyListener(new KeyAdapter() {
				  		     		@Override
				  		     		public void keyPressed(KeyEvent e) {
				  		     			if(e.getKeyCode()==e.VK_ENTER)
				  			      		{
				  			      			if(!textcodemp.getText().equals(""))
				  			      			{
				  			      				MatierePremier matierepremiere=matierePremierDAO.findByCode(textcodemp.getText().toUpperCase());
				  					    		
				  					    		if(matierepremiere!=null)
				  					    		{	
				  					    			combomp.setSelectedItem(matierepremiere.getNom());
				  					    			
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
				  		     	textcodemp.setColumns(10);
				  		     	textcodemp.setBackground(Color.WHITE);
				  		     	textcodemp.setBounds(202, 22, 134, 24);
				  		     	layeredPane_1.add(textcodemp);
				  		     	
				  		     	JLabel lblCodeMp = new JLabel("Code MP  :");
				  		     	lblCodeMp.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	lblCodeMp.setBounds(137, 21, 77, 24);
				  		     	layeredPane_1.add(lblCodeMp);
				  		     	
				  		     	 combomp = new JComboBox();
				  		     	
				  		     	 combomp.addActionListener(new ActionListener() {
				  		     	 	public void actionPerformed(ActionEvent arg0) {
				  		     	 		
				  		     	 	if(combomp.getSelectedIndex()!=-1)
				  			 		{
				  			 			if(!combomp.getSelectedItem().equals(""))
				  				 		{
				  				 			
				  				 		MatierePremier matierepremiere=mapMatierPremier.get(combomp.getSelectedItem());
				  				 		textcodemp.setText(matierepremiere.getCode());
				  				 		
				  				 		
				  				 			
				  				 		}else
				  				 		{
				  				 		 textcodemp.setText(Constantes.CODE_MP);
				  				 		}
				  				 	
				  			 		}else
				  			 		{
				  			 		 textcodemp.setText(Constantes.CODE_MP);
				  			 		}
				  			 		
				  			 		
				  		     	 		
				  		     	 	}
				  		     	 });
				  		     	combomp.setSelectedIndex(-1);
				  		     	combomp.setBounds(477, 22, 265, 24);
				  		     	layeredPane_1.add(combomp);
				  		     	combomp.addItem("");
				  		     	listMatierePremiere=matierePremierDAO.findAll();
				  		     	int i=0;
				  		     	while(i<listMatierePremiere.size())
				  		     	{
				  		     		MatierePremier mp=listMatierePremiere.get(i);
				  		     		combomp.addItem(mp.getNom());
				  		     		mapMatierPremier.put(mp.getNom(), mp);
				  		     		i++;
				  		     	}
				  		     	
				  		     	
				  		     	JLabel lblMatierePremiere = new JLabel("Matiere Premiere :");
				  		     	lblMatierePremiere.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	lblMatierePremiere.setBounds(365, 21, 102, 24);
				  		     	layeredPane_1.add(lblMatierePremiere);
				  		     	
				  		     	JLabel lblQuantit = new JLabel("Quantit\u00E9  :");
				  		     	lblQuantit.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	lblQuantit.setBounds(774, 21, 77, 24);
				  		     	layeredPane_1.add(lblQuantit);
				  		     	
				  		     	textquantite = new JTextField();
				  		     	textquantite.addFocusListener(new FocusAdapter() {
				  		     		@Override
				  		     		public void focusLost(FocusEvent arg0) {}
				  		     	});
				  		     	textquantite.addKeyListener(new KeyAdapter() {});
				  		     	textquantite.setColumns(10);
				  		     	textquantite.setBackground(Color.WHITE);
				  		     	textquantite.setBounds(848, 22, 156, 24);
				  		     	layeredPane_1.add(textquantite);
				  		     	
				  		     	listDepot=depotdao.findAll();
				  		     	int k=0;
				  		     	while (k<listDepot.size())
				  		     	{
				  		     		Depot depot=listDepot.get(k);
				  		     		comboDepot.addItem(depot.getLibelle());
				  		     		mapDepot.put(depot.getLibelle(), depot);
				  		     	
				  		     		k++;
				  		     	}
				  		     	
				  		     	 btnAjouter = new JButton("Ajouter");
				  		     	btnAjouter.addActionListener(new ActionListener() {
				  		     		public void actionPerformed(ActionEvent arg0) {
				  		     			BigDecimal stock=BigDecimal.ZERO;
				  		     			boolean trouve=false;
				  		     			
				  							
				  						
				  						

				  		     			
				  		     			try {
				  		     				
				  		     				
				  		     				if(textcodedepot.getText().equals(""))
					  		     			{
					  		     				JOptionPane.showMessageDialog(null, "Veuillez entrer le code frais depot SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     				return;
					  		     			}
					  		     			else if(dateChooserOperation.getDate()==null)
					  		     			{
					  		     				JOptionPane.showMessageDialog(null, "Veuillez entrer la date d'opération SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     				return;
					  		     			}
					  		     			
					  		     			else if(textdesignation.getText().equals(""))
					  		     			{
					  		     				JOptionPane.showMessageDialog(null, "Veuillez entrer la designation SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     				return;
					  		     			}
					  		     			else if(textcodemp.getText().equals("") || combomp.getSelectedIndex()==-1)
					  		     			{
					  		     				JOptionPane.showMessageDialog(null, "Veuillez entrer la Matiere premiere SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     				return;
					  		     			}
					  		     			else if(comboDepot.getSelectedItem().equals(""))
					  		     			{
					  		     				JOptionPane.showMessageDialog(null, "Veuillez entrer la Depot SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     				return;
					  		     			}
					  		     			else if(comboMagasin.getSelectedItem().equals(""))
					  		     			{
					  		     				JOptionPane.showMessageDialog(null, "Veuillez entrer le Magasin de Stock SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     				return;
					  		     			}
					  		     			else if( textquantite.getText().equals(""))
					  		     			{
					  		     				JOptionPane.showMessageDialog(null, "Veuillez entrer la Quantité SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     				return;
					  		     			}
					  		     			else if(new BigDecimal(textquantite.getText()).compareTo(BigDecimal.ZERO)<=0)
					  		     			{
					  		     				JOptionPane.showMessageDialog(null, "la Quantité doit etre supérieur à 0 SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     				return;
					  		     			}
					  		     		
					  		     			
					  		     			else
					  		     			{
					  		     				int i=0;
							     				while(i<listDetailChargeVariable.size())
							     				{
							     					DetailChargeVariable detailchargevariable=listDetailChargeVariable.get(i);
							     					if(detailchargevariable.getMatierePremier().getCode().equals(textcodemp.getText()))
							     					{
							     						trouve=true;
							     					}
							     					
							     					i++;
							     				}
					  		     				
							     				if(trouve==false)
									    		{
							     					SimpleDateFormat dcn = new SimpleDateFormat("MMyyyy");
							  						 String date = dcn.format(dateChooserOperation.getDate() );
							  						 String code=date+ Constantes.BON_VARIABLE ;
					  		     				
					  		     				Charges charge=chargedao.findByCodeVariable(Constantes.CODE_FRAIS_DEPOT);
					  				    		if(charge!=null)
					  				    		{
					  				    			ChargeVariable chargevariabletmp=chargevariableDAO.findByCode(code);
					  				    			FraisDepot fraisDepotTmp=fraisdepotDAO.findByCode(textcodedepot.getText());
						  		     				MatierePremier matierePremier=mapMatierPremier.get(combomp.getSelectedItem());
						  		     				Magasin magasin=mapMagasin.get(comboMagasin.getSelectedItem());
						  		     				Depot depot=mapDepot.get(comboDepot.getSelectedItem());
						  		     			
						  		     				StockMP stockmp=stockmpDAO.findStockByMagasinMP(matierePremier.getId(), magasin.getId());
						  		     				if(stockmp.getStock().compareTo(new BigDecimal(textquantite.getText())) >=0)
						  		     				{
						  		     					stock= stockmp.getStock().subtract(new BigDecimal(textquantite.getText()));
						  		     					stockmp.setStock(stock);
						  		     					stockmpDAO.edit(stockmp);
						  		     					DetailFraisDepot detailFraisDepot=new DetailFraisDepot();
						  		     					
						  		     					detailFraisDepot.setMatierePremier(matierePremier);
						  		     					detailFraisDepot.setQuantite(new BigDecimal(textquantite.getText()));
						  		     					detailFraisDepot.setPrix(stockmp.getPrixUnitaire());
						  		     					detailFraisDepot.setMontant(stockmp.getPrixUnitaire().multiply(new BigDecimal(textquantite.getText()))  );
						  		     					
							  		     				if(fraisDepotTmp!=null)
							  		     				{
							  		     					detailFraisDepot.setFraisDepot(fraisDepotTmp);
							  		     				}else
							  		     				{
							  		     					fraisDepot.setMagasin(magasin);
							  		     					detailFraisDepot.setFraisDepot(fraisDepot);
							  		     				}
							  		     				
							  		     				listDetailFraisDepot.add(detailFraisDepot);
							  		     				
							  		     			
							  		     				
						  		     				}else
						  		     				{
						  		     					JOptionPane.showMessageDialog(null, "Stock de "+combomp.getSelectedItem() + " insuffisant","Erreur",JOptionPane.ERROR_MESSAGE);
						  		     					return;
						  		     				}
					  				    			
					  				    			
					  				    			
					  				    		DetailChargeVariable detailchargevariable=new DetailChargeVariable();
					  				    		//detailchargevariable.setLibelle(txtlibelle.getText());
					  							detailchargevariable.setMontant(stockmp.getPrixUnitaire().multiply( new BigDecimal(textquantite.getText()))  );
					  				    		detailchargevariable.setMagasin(magasin);
					  				    		detailchargevariable.setDepot(depot);
					  				    		detailchargevariable.setQuantite(new BigDecimal(textquantite.getText()));
					  				    		detailchargevariable.setPrixUnitaire(stockmp.getPrixUnitaire());
					  				    		detailchargevariable.setMatierePremier(matierePremier);
					  				    		if(chargevariabletmp!=null)
				  		     					{
				  		     						detailchargevariable.setChargeVariable(chargevariabletmp);	
				  		     					}else
				  		     					{
				  		     						detailchargevariable.setChargeVariable(chargevariable);
				  		     					}
					  				    		listDetailChargeVariable.add(detailchargevariable);
					  				    		afficher_tableDetailFraisDepot(listDetailFraisDepot);
					  		     				initialiser();
					  				    		
					  				    		}else
					  				    		{
					  				    			 JOptionPane.showMessageDialog(null, "Veuillez creér un frais depot SVP !!!!", "Erreur", JOptionPane.ERROR_MESSAGE);
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
				  		     	btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	btnAjouter.setBounds(391, 352, 107, 24);
				  		     	btnAjouter.setIcon(imgAjouter);
				  		     	add(btnAjouter);
				  		     	
				  		     	JButton btnValider = new JButton("Valider");
				  		     	btnValider.addActionListener(new ActionListener() {
				  		     		public void actionPerformed(ActionEvent e) {
				  		     			BigDecimal MontantTotal=BigDecimal.ZERO;
				  		     			BigDecimal MontantTotalTmp=BigDecimal.ZERO;
				  		     			
				  		     			if(textcodedepot.getText().equals(""))
				  		     			{
				  		     				JOptionPane.showMessageDialog(null, "Veuillez entrer le code frais depot SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				  		     				return;
				  		     			}
				  		     			else if(dateChooserOperation.getDate()==null)
				  		     			{
				  		     				JOptionPane.showMessageDialog(null, "Veuillez entrer la date d'opération SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				  		     				return;
				  		     			}
				  		     			else if(textdesignation.getText().equals(""))
				  		     			{
				  		     				JOptionPane.showMessageDialog(null, "Veuillez entrer la designation !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				  		     				return;
				  		     			}
				  		     			else if(comboDepot.getSelectedItem().equals(""))
				  		     			{
				  		     				JOptionPane.showMessageDialog(null, "Veuillez choisir le depot SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				  		     				return;
				  		     			}
				  		     			else if(comboMagasin.getSelectedItem().equals(""))
				  		     			{
				  		     				JOptionPane.showMessageDialog(null, "Veuillez choisir le magasin SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				  		     				return;
				  		     			}
				  		     			
				  		     			else if(listDetailFraisDepot.size()==0)
				  		     			{
				  		     				JOptionPane.showMessageDialog(null, "Veuillez entrer le detail de Frais Depot SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				  		     				return;
				  		     			}
				  		     			else
				  		     			{
				  		     				SimpleDateFormat dcn = new SimpleDateFormat("MMyyyy");
				  		 				 String date = dcn.format(dateChooserOperation.getDate() );
				  		 				 String code=date+ Constantes.BON_VARIABLE ;
				  		     				int i=0;
				  		     				while(i<listDetailFraisDepot.size())
				  		     				{
				  		     					MontantTotal=MontantTotal.add( listDetailFraisDepot.get(i).getMontant()) ;
				  		     					
				  		     					i++;
				  		     				}
				  		     				
				  		     				FraisDepot fraisDepotTmp=fraisdepotDAO.findByCode(textcodedepot.getText().toUpperCase());
				  		     				if(fraisDepotTmp!=null)
				  		     				{
				  		     					MontantTotalTmp=MontantTotal.add(fraisDepotTmp.getMontant())    ;
				  		     					fraisDepotTmp.setMontant(MontantTotalTmp);
				  		     					fraisDepotTmp.setDetailFraisDepot(listDetailFraisDepot);				  		     					
				  		     					fraisdepotDAO.edit(fraisDepotTmp);
				  		     					
				  		     					AjoutChargeProd(MontantTotal,fraisDepotTmp.getDepot());
				  		     					
				  		     					
				  		     				}else
				  		     				{
				  		     					Depot depot =mapDepot.get(comboDepot.getSelectedItem());
				  		     					Magasin magasin=mapMagasin.get(comboMagasin.getSelectedItem());
				  		     					//compteMagasinier=new CompteMagasinier();
				  		     					fraisDepot.setCode(textcodedepot.getText().toUpperCase());
				  		     					fraisDepot.setDateoperation(dateChooserOperation.getDate());
				  		     					fraisDepot.setDepot(depot);
				  		     					fraisDepot.setMagasin(magasin);
				  		     					fraisDepot.setMontant(MontantTotal);
				  		     					fraisDepot.setDesignation(textdesignation.getText());
				  		     					fraisDepot.setDetailFraisDepot(listDetailFraisDepot);
				  		     					fraisdepotDAO.add(fraisDepot);
				  		     					AjoutChargeProd(MontantTotal,depot);
				  		     					
				  		     				}
				  		     				
				  		     				ChargeVariable chargevariabletmp=new ChargeVariable();
					     					chargevariabletmp.setCode(code);
					     					chargevariabletmp.setDesignation(textdesignation.getText());
					     					chargevariabletmp.setDateoperation(dateChooserOperation.getDate());
					     					chargevariabletmp.setMontant(MontantTotal);
					     					chargevariabletmp.setListdetailChargeVariable(listDetailChargeVariable);
					     					chargevariableDAO.add(chargevariabletmp);
				  		     				
					     					JOptionPane.showMessageDialog(null, "l'ajout effectué avec succée ","Succée",JOptionPane.INFORMATION_MESSAGE);
			  		     					initialiser();
			  		     					intialiserTabeleau();
			  		     					
			  		     					
			  		     					dateChooserOperation.setCalendar(null);
			  		     					textcodedepot.requestDefaultFocus();
			  		     					listDetailFraisDepot.clear();
			  		     					listDetailChargeVariable.clear();
				  		     								  		     				
				  		     			}
				  		     			
				  		     			
				  		     		}
				  		     	});
				  		     	btnValider.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	btnValider.setBounds(407, 794, 107, 24);
				  		     	btnValider.setIcon(imgAjouter);
				  		     	add(btnValider);
				  		     	
				  		     	JButton btnInitialiser = new JButton("Initialiser");
				  		     	btnInitialiser.addActionListener(new ActionListener() {
				  		     		public void actionPerformed(ActionEvent arg0) {
				  		     			
				  		     			
				  		     			initialiser();
				  		     			
				  		     		}
				  		     	});
				  		     	btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	btnInitialiser.setBounds(522, 352, 107, 24);
				  		     	btnInitialiser.setIcon(imgInit);
				  		     	add(btnInitialiser);
				  		     	
				  		     	 modifbutton = new JButton();
				  		     	 modifbutton.addActionListener(new ActionListener() {
				  		     	 	public void actionPerformed(ActionEvent e) {
				  		     	 		
                                    BigDecimal stock=BigDecimal.ZERO;

				  		     			try {
				  		     				
					  		     			if(textcodedepot.getText().equals(""))
					  		     			{
					  		     				JOptionPane.showMessageDialog(null, "Veuillez entrer le code frais depot SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     				return;
					  		     			}
					  		     			else if(dateChooserOperation.getDate()==null)
					  		     			{
					  		     				JOptionPane.showMessageDialog(null, "Veuillez entrer la date d'opération SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     				return;
					  		     			}
					  		     			else if(textdesignation.getText().equals(""))
					  		     			{
					  		     				JOptionPane.showMessageDialog(null, "Veuillez entrer la designation SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     				return;
					  		     			}
					  		     			else if(textcodemp.getText().equals("") || combomp.getSelectedIndex()==-1)
					  		     			{
					  		     				JOptionPane.showMessageDialog(null, "Veuillez entrer la Matiere premiere SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     				return;
					  		     			}
					  		     			else if( textquantite.getText().equals(""))
					  		     			{
					  		     				JOptionPane.showMessageDialog(null, "Veuillez entrer la Quantité SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     				return;
					  		     			}
					  		     			else if(new BigDecimal(textquantite.getText()).compareTo(BigDecimal.ZERO)<=0)
					  		     			{
					  		     				JOptionPane.showMessageDialog(null, "la Quantité doit etre supérieur à 0 SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     				return;
					  		     			}
					  		     		
					  		     			else
					  		     			{
					  		     				
					  		     				MatierePremier matierePremier=mapMatierPremier.get(listDetailFraisDepot.get(table.getSelectedRow()).getMatierePremier().getNom());
					  		     				Magasin magasin=mapMagasin.get(listDetailFraisDepot.get(table.getSelectedRow()).getFraisDepot().getMagasin().getLibelle());
					  		     				StockMP stockmp=stockmpDAO.findStockByMagasinMP(matierePremier.getId(), magasin.getId());	
					  		     			
					  		     				DetailFraisDepot detailFraisDepot=listDetailFraisDepot.get(table.getSelectedRow());
					  		     				if((stockmp.getStock().add( detailFraisDepot.getQuantite()).compareTo(new BigDecimal(textquantite.getText())))>=0)
					  		     				{
					  		     					MatierePremier matierePremiertmp=mapMatierPremier.get(combomp.getSelectedItem());
						  		     				Magasin magasintmp=mapMagasin.get(comboMagasin.getSelectedItem());
					  		     					StockMP stockmpTmp=stockmpDAO.findStockByMagasinMP(matierePremiertmp.getId(),magasintmp.getId());
					  		     					Depot depot=mapDepot.get(comboDepot.getSelectedItem());
					  		     					Charges charge=chargedao.findByCodeVariable(Constantes.CODE_FRAIS_DEPOT);
					  		     		    		if(charge!=null)
					  		     		    		{
					  		     		    			
					  		     		    			Integer row=table.getSelectedRow();
					  		     		    			if(row!=-1)
					  		     		    			{
					  		     		    		DetailChargeVariable detailchargevariable=new DetailChargeVariable();
					  		     		    		//detailchargevariable.setLibelle(txtlibelle.getText());
					  		     					detailchargevariable.setMontant(stockmpTmp.getPrixUnitaire().multiply(new BigDecimal(textquantite.getText()))  );
					  		     					detailchargevariable.setMagasin(magasintmp);
						  				    		detailchargevariable.setDepot(depot);
						  				    		detailchargevariable.setQuantite(new BigDecimal(textquantite.getText()));
						  				    		detailchargevariable.setPrixUnitaire(stockmpTmp.getPrixUnitaire());
						  				    		detailchargevariable.setMatierePremier(matierePremiertmp);
					  		     		    		
					  		     		    		
					  		     		    		listDetailChargeVariable.set(row, detailchargevariable);
					  		     		    		
					  		     		    			}else
					  		     		    			{
					  		     		    				 JOptionPane.showMessageDialog(null, "Veuillez selectionner un enregistrement SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
					  		     		    				 return;
					  		     		    			}
					  		     		    		
					  		     		    		
					  		     		    		
					  		     		    		}else
					  		     		    		{
					  		     		    			 JOptionPane.showMessageDialog(null, "Veuillez Créer un frais depot SVP !!!", "Erreur", JOptionPane.ERROR_MESSAGE);
					  		     		    			 return;
					  		     		    		}
					  		     		    		
					  		     					stock=stockmp.getStock().add(detailFraisDepot.getQuantite())  ;
					  		     					stockmp.setStock(stock);
					  		     					stockmpDAO.edit(stockmp);
					  		     					stock=BigDecimal.ZERO;
					  		     					
					  		     					
					  		     					if(stockmpTmp.getStock().compareTo(new BigDecimal(textquantite.getText()))>=0)
					  		     					{
					  		     						stock=stockmpTmp.getStock().subtract(new BigDecimal(textquantite.getText()))   ;
						  		     					stockmpTmp.setStock(stock);
						  		     					stockmpDAO.edit(stockmpTmp);
						  		     					FraisDepot fraisDepot=listDetailFraisDepot.get(table.getSelectedRow()).getFraisDepot();
						  		     					fraisDepot.setMagasin(magasintmp);				
							  		     				detailFraisDepot.setMatierePremier(matierePremiertmp);
							  		     				detailFraisDepot.setFraisDepot(fraisDepot);
							  		     				detailFraisDepot.setQuantite(new BigDecimal(textquantite.getText()));							  		     				
							  		     				detailFraisDepot.setPrix(stockmpTmp.getPrixUnitaire());							  		     				
							  		     				detailFraisDepot.setMontant(stockmpTmp.getPrixUnitaire().multiply(new BigDecimal(textquantite.getText())));
							  		     				listDetailFraisDepot.set(table.getSelectedRow(), detailFraisDepot);
							  		     				afficher_tableDetailFraisDepot(listDetailFraisDepot);
							  		     				initialiser();
					  		     					}else
					  		     					{
					  		     						JOptionPane.showMessageDialog(null, "Stock de "+combomp.getSelectedItem() + " insuffisant","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     						stock=stockmp.getStock().subtract(detailFraisDepot.getQuantite()) ;
						  		     					stockmp.setStock(stock);
						  		     					stockmpDAO.edit(stockmp);
						  		     					stock=BigDecimal.ZERO;
					  		     						return;
					  		     					}
					  		     					
					  		     					
					  		     				}else
					  		     				{
					  		     					JOptionPane.showMessageDialog(null, "Stock de "+combomp.getSelectedItem() + " insuffisant","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     				}
					  		     				
					  		     				
					  		     				
					  		     			}
					  		     			
				  		     				
				  		     				
											
										} catch (NumberFormatException r) {JOptionPane.showMessageDialog(null, "la Quantité , le Prix Unitaire et le Montant doit etre numérique  ","Erreur",JOptionPane.ERROR_MESSAGE);
											
										}
				  		     			
				  		     	 		
				  		     	 		
				  		     	 		
				  		     	 		
				  		     	 	}
				  		     	 });
				  		     	modifbutton.setEnabled(false);
				  		     	modifbutton.setBounds(1201, 490, 73, 24);
				  		     	modifbutton.setIcon(imgModifier);
				  		     	add(modifbutton);
				  		     	
				  		     	 suppbutton = new JButton();
				  		     	 suppbutton.addActionListener(new ActionListener() {
				  		     	 	public void actionPerformed(ActionEvent e) {
				  		     	 		BigDecimal stock=BigDecimal.ZERO;
				  		     	 	Magasin magasin=mapMagasin.get(listDetailFraisDepot.get(table.getSelectedRow()).getFraisDepot().getMagasin().getLibelle());
				  		     	 	MatierePremier matierepremiere=mapMatierPremier.get(listDetailFraisDepot.get(table.getSelectedRow()).getMatierePremier().getNom());
				  		     	 	StockMP stockmp=stockmpDAO.findStockByMagasinMP(matierepremiere.getId(), magasin.getId()) ; 	 
				  		     	 stock=stockmp.getStock().add(listDetailFraisDepot.get(table.getSelectedRow()).getQuantite());
				  		     	stockmp.setStock(stock);
				  		      stockmpDAO.edit(stockmp);
				  		    listDetailFraisDepot.remove(table.getSelectedRow());
				  		     	
				  		     	 	afficher_tableDetailFraisDepot(listDetailFraisDepot);
				  		     	 	initialiser();
				  		     	 	}
				  		     	 });
				  		     	suppbutton.setEnabled(false);
				  		     	suppbutton.setBounds(1201, 537, 73, 24);
				  		     	suppbutton.setIcon(imgSupprimer);
				  		     	add(suppbutton);
				  		     	
				  		     	JButton button = new JButton("Initialiser");
				  		     	button.setIcon(imgInit);
				  		     	button.addActionListener(new ActionListener() {
				  		     		public void actionPerformed(ActionEvent arg0) {
				  		     			initialiserDepot();
				  		     		}
				  		     	});
				  		     	button.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	button.setBounds(487, 165, 107, 24);
				  		     	add(button);
	
				  		      textcodemp.setText(Constantes.CODE_MP);		  		     
				  		    		 
	}

	 void initialiserDepot()
	 {
		
		textcodedepot.setText("");
		textdesignation.setText("");
		comboDepot.setSelectedItem("");
		comboMagasin.setSelectedItem("");
		dateChooserOperation.setCalendar(null);
		textcodedepot.requestDefaultFocus();
	 }

	
	void AjoutChargeProd(BigDecimal total, Depot depot)
	{

			 SimpleDateFormat dcn = new SimpleDateFormat("MMyyyy");
		 String code = dcn.format(dateChooserOperation.getDate());

	
		BigDecimal totalev=BigDecimal.ZERO;
		
					
						ChargeProduction chargeproductionTmp=chargeproductiondao.findbycodeFix(code,Constantes.CHARGEST_VARIABLE);
						if(chargeproductionTmp!=null)
						{
							int i=0;
							totalev=chargeproductionTmp.getTotale().add(total)   ;
							utilisateur= AuthentificationView.utilisateur;
							Date dateV=new Date();
							
							chargeproductionTmp.setCode(code);
							chargeproductionTmp.setDate(dateV);
							chargeproductionTmp.setDatedesaisi(dateChooserOperation.getDate());
							chargeproductionTmp.setUtilisateurCreation(utilisateur);
							chargeproductionTmp.setUtilisateurMAJ(utilisateur);
							chargeproductionTmp.setDateMiseJours(dateV);
							chargeproductionTmp.setCodeDepot(depot.getCode());
							chargeproductionTmp.setTotale(totalev);
							chargeproductionTmp.setType(Constantes.CHARGEST_VARIABLE);
						
							//chargeproductionTmp.setListdetailChargeVariable(listDetailChargeVariable);
							chargeproductiondao.edit(chargeproductionTmp);
							//JOptionPane.showMessageDialog(null, "Charge Production Variable valider avec succée !!!", "Information", JOptionPane.INFORMATION_MESSAGE);
							
						
							
							
						}else
						{
						utilisateur= AuthentificationView.utilisateur;
						Date date=new Date();
						
						chargeproduction.setCode(code);
						chargeproduction.setDate(date);
						chargeproduction.setDatedesaisi(dateChooserOperation.getDate());
						chargeproduction.setUtilisateurCreation(utilisateur);
						chargeproduction.setUtilisateurMAJ(utilisateur);
						chargeproduction.setDateMiseJours(date);
						chargeproduction.setCodeDepot(depot.getCode());
						chargeproduction.setTotale(total);
						chargeproduction.setType(Constantes.CHARGEST_VARIABLE);
						//chargeproduction.setListdetailChargeVariable(listDetailChargeVariable);
						chargeproductiondao.add(chargeproduction);
					
						}
						
						
						
				
					
					
				
			
		
		
		
		
	
	}
	
	
	
	
	
	 void initialiser()
	 {
		
		 textcodemp.setText(Constantes.CODE_MP);
		 combomp.setSelectedIndex(-1);
		
		 textquantite.setText("");
		 
		 btnAjouter.setEnabled(true);
		    
	     modifbutton.setEnabled(false);
		 suppbutton.setEnabled(false);
		 
	 }

void intialiserTabeleau(){
	
	modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			 "Matiere Premiere", "Quantit\u00E9", "Prix Unitaire", "Montant"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		   table.setModel(new DefaultTableModel(
		   	new Object[][] {
		   	},
		   	new String[] {
		   		"Matiere Premiere", "Quantit\u00E9", "Prix Unitaire", "Montant"
		   	}
		   ));
		   table.getColumnModel().getColumn(0).setPreferredWidth(92);
}


void afficher_tableDetailFraisDepot(List<DetailFraisDepot> listDetailFraisDepot)
{
	modeleMP =new DefaultTableModel(
	     	new Object[][] {
	     	},
	     	new String[] {
	     			 "Matiere Premiere", "Quantit\u00E9", "Prix Unitaire", "Montant"
	     	}
	     ) {
	     	boolean[] columnEditables = new boolean[] {
	     			false,false,false,false
	     	};
	     	public boolean isCellEditable(int row, int column) {
	     		return columnEditables[column];
	     	}
	     };
	     table.setModel(modeleMP);
	int i=0;
	 
	while(i<listDetailFraisDepot.size())
	{	
		DetailFraisDepot detailFraisDepot=listDetailFraisDepot.get(i);
		MatierePremier matierepremiere=detailFraisDepot.getMatierePremier();
		
		
		String mp=matierepremiere.getNom();
		BigDecimal quantite =detailFraisDepot.getQuantite();
		BigDecimal prix=detailFraisDepot.getPrix();
		BigDecimal montant=detailFraisDepot.getMontant();
		Object []ligne={mp,NumberFormat.getNumberInstance(Locale.FRANCE).format(quantite),NumberFormat.getNumberInstance(Locale.FRANCE).format(prix),NumberFormat.getNumberInstance(Locale.FRANCE).format(montant)};

		modeleMP.addRow(ligne);
		i++;
	}
}
}

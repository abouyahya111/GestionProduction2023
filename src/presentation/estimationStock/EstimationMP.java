package presentation.estimationStock;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import main.AuthentificationView;
import main.ProdLauncher;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoManager.DepotDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailTransferStockMP;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.StockMP;
import dao.entity.TransferStockMP;


public class EstimationMP extends JLayeredPane {
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;

	private JXTable table;
	private ImageIcon imgModifier;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private JButton btnAnnulerOF;
	private JButton btnRechercher;
	
	
	private JComboBox<String> comboDepotSource;
	private  JComboBox<String> comboMagasinSource;
	
	private Map< String, String> mapQuantiteMP = new HashMap<>();
	private Map< String, String> mapPrixMP = new HashMap<>();
	private Map< Integer, MatierePremier> mapMatierePremier = new HashMap<>();
	private Map< String, MatierePremier> mapMatierePremierTmp = new HashMap<>();
	
	private Map< String, Magasin> mapMagasinSource = new HashMap<>();
	private Map< String, Magasin> mapMagasinDestination = new HashMap<>();
	
	private Map< String, Integer> mapDepotSource = new HashMap<>();
	private Map< String, Integer> mapDepotDestionation = new HashMap<>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	
	private JComboBox<String> comboMagasinDestination;
	private JLabel lblDpotDestination;
	private JComboBox<String> comboDepotDestination;
	private JLabel lblMagasinDstination;
	
	private DepotDAO depotDAO;
	private StockMPDAO stockMPDAO;
	private TransferStockMPDAO transferStockMPDAO;
	private MatierePremiereDAO matierePremiereDAO;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public EstimationMP() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 565);
        try{
        	
        	
        	depotDAO= new DepotDAOImpl();
        	stockMPDAO= new StockMPDAOImpl();
        	transferStockMPDAO=new TransferStockMPDAOImpl();
        	matierePremiereDAO= new MatierePremierDAOImpl();

       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		
		 	
	
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp.png"));
          } catch (Exception exp){exp.printStackTrace();}
				  		    comboMagasinDestination = new JComboBox<String>();
				  		     btnAnnulerOF = new JButton("Annuler OF");
				  		     btnAnnulerOF.setBounds(244, 417, 112, 23);
				  		     add(btnAnnulerOF);
				  		     btnAnnulerOF.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     	intialiser();
				  		     		
				  		     	}
				  		     });
				  		     btnAnnulerOF.setIcon(imgInit);
				  		     btnAnnulerOF.setFont(new Font("Tahoma", Font.PLAIN, 11));
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
				  		   DefaultCellEditor ce = (DefaultCellEditor) table.getDefaultEditor(Object.class);
				  	        JTextComponent textField = (JTextComponent) ce.getComponent();
				  	        util.Utils.copycollercell(textField);
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(9, 167, 782, 176);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	 
				  		     	comboDepotDestination = new JComboBox<String>();
				  		     	comboDepotDestination.addItem("");
				  		     	comboDepotSource = new JComboBox();	
				  		     	comboDepotSource.addItem("");
				  		     	comboMagasinSource = new JComboBox();
				  		      	comboMagasinSource.addItem("");
				  		     	listDepot = depotDAO.findAll();	
					  		      int i=0;
					  		      	while(i<listDepot.size())
					  		      		{	
					  		      			Depot depot=listDepot.get(i);
					  		      			mapDepotSource.put(depot.getLibelle(), i);
					  		      			mapDepotDestionation.put(depot.getLibelle(), i);
					  		      			comboDepotSource.addItem(depot.getLibelle());
					  		      			comboDepotDestination.addItem(depot.getLibelle());
					  		      			i++;
					  		      		}
					  		      	
					  		      comboDepotSource.addItemListener(new ItemListener() {
					  		     	 	public void itemStateChanged(ItemEvent e) {
					  		     	 	
					  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
					  		     	 	 List<Magasin> listMagasin=new ArrayList<Magasin>();
						  		     	  	 // comboGroupe = new JComboBox();
					  		     	 	comboMagasinSource.removeAllItems();
					  		     	 	//comboGroupe.addItem("");
						  		     	  	   	Depot depot =listDepot.get(mapDepotSource.get(comboDepotSource.getSelectedItem()));
								  		       
						  		     	  	listMagasin = depot.getListMagasin();
								  		      if(listMagasin!=null){
								  		    	  
								  		    	  int j=0;
									  		      	while(j<listMagasin.size())
									  		      		{	
									  		      			Magasin magasin=listMagasin.get(j);
									  		      			comboMagasinSource.addItem(magasin.getLibelle());
									  		      			mapMagasinSource.put(magasin.getLibelle(), magasin);
									  		      			j++;
									  		      		}
								  		      }
					  		     	 	 }
					  		     	 	}
					  		     	 });
					  		      
					  		    comboDepotDestination.addItemListener(new ItemListener() {
				  		     	 	public void itemStateChanged(ItemEvent e) {
				  		     	 	
				  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
				  		     	 	 List<Magasin> listMagasin=new ArrayList<Magasin>();
					  		     	  	 // comboGroupe = new JComboBox();
				  		     	 	comboMagasinDestination.removeAllItems();
				  		     	 	//comboGroupe.addItem("");
					  		     	  	   	Depot depot =listDepot.get(mapDepotDestionation.get(comboDepotDestination.getSelectedItem()));
							  		       
					  		     	  	listMagasin = depot.getListMagasin();
							  		      if(listMagasin!=null){
							  		    	  
							  		    	  int j=0;
								  		      	while(j<listMagasin.size())
								  		      		{	
								  		      			Magasin magasin=listMagasin.get(j);
								  		      			comboMagasinDestination.addItem(magasin.getLibelle());
								  		      			mapMagasinDestination.put(magasin.getLibelle(), magasin);
								  		      			j++;
								  		      		}
							  		      }
				  		     	 	 }
				  		     	 	}
				  		     	 });
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
				  		     	titledSeparator.setBounds(9, 135, 782, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 13, 781, 111);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblMachine = new JLabel("D\u00E9pot Soure");
				  		     	lblMachine.setBounds(10, 34, 114, 24);
				  		     	layeredPane.add(lblMachine);
				  		     	lblMachine.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	
				  		     	 
				  		     	 comboDepotSource.setBounds(134, 35, 144, 24);
				  		     	 layeredPane.add(comboDepotSource);
				  		     	 comboDepotSource.addItem("");
				  		     	 
				  		     	 JLabel lblGroupe = new JLabel("Magasin Source");
				  		     	 lblGroupe.setBounds(10, 73, 102, 24);
				  		     	 layeredPane.add(lblGroupe);
				  		     	 lblGroupe.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	
				  		     	 comboMagasinSource.setBounds(134, 74, 144, 24);
				  		     	 layeredPane.add(comboMagasinSource);
				  		     	 
				  		 
				  		  comboMagasinDestination.setBounds(549, 74, 152, 24);
				  		  layeredPane.add(comboMagasinDestination);
				  		  comboMagasinDestination.addItem("");
				  		  
				  		  JLabel lblEquipe = new JLabel("Magasin Destination");
				  		  lblEquipe.setBounds(431, 73, 108, 26);
				  		  layeredPane.add(lblEquipe);
				  		  
				  		  lblDpotDestination = new JLabel("D\u00E9pot Destination");
				  		  lblDpotDestination.setBounds(431, 34, 108, 26);
				  		  layeredPane.add(lblDpotDestination);
				  		  
				  		  
				  		  comboDepotDestination.setBounds(549, 35, 152, 24);
				  		  layeredPane.add(comboDepotDestination);
				  		  
				  		  lblMagasinDstination = new JLabel("Magasin D\u00E9stination");
				  		  lblMagasinDstination.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
				  		  lblMagasinDstination.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
				  		  lblMagasinDstination.setBounds(431, 11, 268, 14);
				  		  layeredPane.add(lblMagasinDstination);
		
		JButton btnValiderTransfer = new JButton("Valider Transfer MP");
		btnValiderTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(!remplirMapChargeSupp())	{
				JOptionPane.showMessageDialog(null, "Il faut remplir la quantité et le prix", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else {
				TransferStockMP transferStock = new TransferStockMP();
				transferStock.setCodeTransfer("TRANS001");
				transferStock.setCreerPar(AuthentificationView.utilisateur);
				transferStock.setDate(new Date());
				transferStock.setDateTransfer(new Date());
				transferStock.setStatut("Transferé");
				transferStock.setListDetailTransferMP( remplirDetailTransfer());
				
				JOptionPane.showMessageDialog(null, "Stock transféré avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
			}
		  }
		});
		btnValiderTransfer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnValiderTransfer.setBounds(76, 417, 158, 23);
		add(btnValiderTransfer);
		List<MatierePremier> listMatierePremier=matierePremiereDAO.findAll();
		afficher_tableMP(listMatierePremier);
				  		     
				  		 
	}
	
	
	void intialiser()
	{
		comboDepotDestination.setSelectedItem("");
		comboDepotSource.setSelectedItem("");
		comboMagasinDestination.setSelectedItem("");
		comboMagasinSource.setSelectedItem("");
		
	}
	
	void afficher_tableMP(List<MatierePremier> listMatierePremier)
	{

		

		modeleMP =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"Code","Nom MP", "Quantité", "Prix"
	  		     	}
	  		     ) {
	  		     	boolean[] columnEditables = new boolean[] {
	  		     			false,false,true, true
	  		     	};
	  		     	public boolean isCellEditable(int row, int column) {
	  		     		return columnEditables[column];
	  		     	}
	  		     };
	  		   table.setModel(modeleMP); 
	  		 table.getColumnModel().getColumn(0).setPreferredWidth(10);
	         table.getColumnModel().getColumn(1).setPreferredWidth(260);
	         table.getColumnModel().getColumn(2).setPreferredWidth(160);
	        table.getColumnModel().getColumn(3).setPreferredWidth(160);
	        //q table.getColumnModel().getColumn(4).setPreferredWidth(60);
	        
		  int i=0;
			while(i<listMatierePremier.size())
			{	
				
				MatierePremier matierePremier=listMatierePremier.get(i);
				mapMatierePremierTmp.put(matierePremier.getCode(), matierePremier);
				Object []ligne={matierePremier.getCode(),matierePremier.getNom(),"",""};

				modeleMP.addRow(ligne);
				i++;
			}
	}
	


boolean remplirMapChargeSupp(){
	boolean trouve=false;
	int i=0;
			
	for(int j=0;j<table.getRowCount();j++){
		
		if(!table.getValueAt(j, 2).toString().equals("") && !table.getValueAt(j, 3).toString().equals("")){
			mapQuantiteMP.put(table.getValueAt(j, 0).toString(), table.getValueAt(j, 2).toString());
			mapPrixMP.put(table.getValueAt(j, 0).toString(), table.getValueAt(j, 3).toString());
			mapMatierePremier.put(i, mapMatierePremierTmp.get(table.getValueAt(j, 0).toString()));
			i++;
			trouve=true;
		}
		
	}
	return trouve;
}


List<DetailTransferStockMP> remplirDetailTransfer(){
	BigDecimal quantite=BigDecimal.ZERO;
	BigDecimal nouveau_prix=BigDecimal.ZERO;
	BigDecimal prixStock=BigDecimal.ZERO;
	BigDecimal prixMoyen=BigDecimal.ZERO;
	BigDecimal stockSource=BigDecimal.ZERO;
	BigDecimal stockDestination=BigDecimal.ZERO;
	BigDecimal sommeStock=BigDecimal.ZERO;
	
	List<DetailTransferStockMP> listDetailTransferStockMP= new ArrayList<DetailTransferStockMP>();
	
	for(int i=0;i<mapMatierePremier.size();i++){
		
		DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
		Magasin magasinSource =mapMagasinSource.get(comboMagasinSource.getSelectedItem());
		Magasin magasinDestination=mapMagasinDestination.get(comboMagasinDestination.getSelectedItem());
		MatierePremier matierePremier =mapMatierePremier.get(i);
		quantite=new BigDecimal(mapQuantiteMP.get(matierePremier.getCode()));
		nouveau_prix=new BigDecimal(mapPrixMP.get(matierePremier.getCode()));
		StockMP stockMPSource=stockMPDAO.findStockByMagasinMP(matierePremier.getId(), magasinSource.getId());
		StockMP stockMPDestination=stockMPDAO.findStockByMagasinMP(matierePremier.getId(), magasinDestination.getId());
		sommeStock=quantite.add(stockMPDestination.getStock());
		stockSource=stockMPSource.getStock().subtract(quantite);
		stockDestination=stockMPDestination.getStock().add(quantite);
		
		stockMPDestination.setStock(stockDestination);
		stockMPSource.setStock(stockSource);
		
		prixStock=stockMPDestination.getPrixUnitaire();
		
		prixMoyen=prixStock.multiply(stockMPDestination.getStock()).add(nouveau_prix).multiply(quantite);
		
		prixMoyen=prixMoyen.divide(sommeStock, 6, BigDecimal.ROUND_HALF_UP);
		stockMPDestination.setPrixUnitaire(prixMoyen);
		
		stockMPDAO.edit(stockMPDestination);
		stockMPDAO.edit(stockMPSource);
		
		detailTransferStockMP.setMagasinDestination(magasinDestination);
		detailTransferStockMP.setMagasinSouce(magasinSource);
		detailTransferStockMP.setMatierePremier(matierePremier);
		detailTransferStockMP.setQuantite(quantite);
		detailTransferStockMP.setPrixUnitaire(nouveau_prix);
		
		listDetailTransferStockMP.add(detailTransferStockMP);
	}
	
	
	return listDetailTransferStockMP;
	
}


}

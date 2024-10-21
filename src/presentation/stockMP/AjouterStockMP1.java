package presentation.stockMP;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import main.AuthentificationView;
import main.ProdLauncher;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.Utils;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoManager.DepotDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.entity.Depot;
import dao.entity.DetailTransferStockMP;
import dao.entity.Magasin;
import dao.entity.StockMP;
import dao.entity.TransferStockMP;
import dao.entity.Utilisateur;


public class AjouterStockMP1 extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;

	private JXTable table;
	private ImageIcon imgModifier;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgRechercher;
	
	
	private JComboBox<String> comboDepot=new JComboBox();
	private  JComboBox<String> comboMagasin=new JComboBox();;
	

	private Map< String, Integer> mapMagasin = new HashMap<>();
	private Map< String, Depot> mapDepot = new HashMap<>();
	private Map< Integer, String> mapQuantite= new HashMap<>();
	private Map< Integer, String> mapPrixUnitaire= new HashMap<>();
	
	private List<Depot> listDepot =new ArrayList<Depot>();
	List<StockMP> listStockMP =new ArrayList<StockMP>();
	private Utilisateur utilisateur;
	private Depot depot=new Depot();
	
	private DepotDAO depotDAO;
	private StockMPDAO stockMPDAO;
	private TransferStockMPDAO transferStockMPDAO;
	JButton btnAfficherStock ;
	private JTextField txtcode;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjouterStockMP1() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 565);
        try{
        	
        	
        	depotDAO= new DepotDAOImpl();
        	stockMPDAO= new StockMPDAOImpl();
        	transferStockMPDAO= new TransferStockMPDAOImpl();
        	utilisateur= AuthentificationView.utilisateur;

       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion √† la base de donn√©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
        
        if(!utilisateur.getCodeDepot().equals(CODE_DEPOT_SIEGE)	) {
	    		 depot = depotDAO.findByCode(utilisateur.getCodeDepot());
	    		 mapDepot.put(depot.getLibelle(), depot);
	    		comboDepot.addItem(depot.getLibelle());
	    }else {
	    	
	  	listDepot = depotDAO.findAll();	
	      int i=0;
	      	while(i<listDepot.size())
	      		{	
	      			 depot=listDepot.get(i);
	      			mapDepot.put(depot.getLibelle(), depot);
	      			comboDepot.addItem(depot.getLibelle());
	      			i++;
	      		}
	    }
		btnAfficherStock = new JButton("");
	
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp.png"));
             imgRechercher= new ImageIcon(this.getClass().getResource("/img/rechercher.png"));
          } catch (Exception exp){exp.printStackTrace();}
				  		     table = new JXTable();
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
				  		   
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(9, 130, 782, 367);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  		      List<Magasin> 	listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_MP);
				  		      if(listMagasin!=null){
				  		    	  
				  		    	  int j=0;
					  		      	while(j<listMagasin.size())
					  		      		{	
					  		      			Magasin magasin=listMagasin.get(j);
					  		      		comboMagasin.addItem(magasin.getLibelle());
					  		      		mapMagasin.put(magasin.getLibelle(), magasin.getId());
					  		      			j++;
					  		      		}
				  		      }
				  		     	
				  		     
					  		      	
					  		      comboDepot.addItemListener(new ItemListener() {
					  		     	 	public void itemStateChanged(ItemEvent e) {
					  		     	 	btnAfficherStock.setVisible(true);
					  		     	 	
					  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
					  		     	 	 List<Magasin> listMagasin=new ArrayList<Magasin>();
						  		     	  	 // comboGroupe = new JComboBox();
					  		     	 	comboMagasin.removeAllItems();
					  		     	 
					  		     	 	//comboGroupe.addItem("");
					  		     	 	if(!comboDepot.getSelectedItem().equals(""))
						  		     	  	   	 depot =mapDepot.get(comboDepot.getSelectedItem());
								  		       
						  		     	   listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_MP);
								  		      if(listMagasin!=null && listMagasin.size()> 0){
								  		    	  
								  		    	  int j=0;
									  		      	while(j<listMagasin.size())
									  		      		{	
									  		      			Magasin magasin=listMagasin.get(j);
									  		      			comboMagasin.addItem(magasin.getLibelle());
									  		      			mapMagasin.put(magasin.getLibelle(), magasin.getId());
									  		      			j++;
									  		      		}
								  		      }else {
								  		    	//JOptionPane.showMessageDialog(null, "Ce dÈpot ne contient aucun magasin", "Erreur", JOptionPane.ERROR_MESSAGE);
								  		    	btnAfficherStock.setVisible(false);
								  		      }
					  		     	 	 }
					  		     	 	}
					  		     	 });
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
				  		     	titledSeparator.setBounds(9, 101, 782, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 36, 781, 54);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblMachine = new JLabel("D\u00E9pot :");
				  		     	lblMachine.setBounds(10, 11, 96, 24);
				  		     	layeredPane.add(lblMachine);
				  		     	lblMachine.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	
				  		     	 
				  		     	 comboDepot.setBounds(51, 11, 144, 24);
				  		     	 layeredPane.add(comboDepot);
				  		     	
				  		     	 
				  		     	 JLabel lblGroupe = new JLabel("Magasin :");
				  		     	 lblGroupe.setBounds(225, 10, 102, 24);
				  		     	 layeredPane.add(lblGroupe);
				  		     	 lblGroupe.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	
				  		     	 comboMagasin.setBounds(281, 11, 192, 24);
				  		     	 layeredPane.add(comboMagasin);
				  		     	 
				  		     	 txtcode = new JTextField();
				  		     	 txtcode.setBackground(Color.WHITE);
				  		     	 txtcode.setEditable(false);
				  		     	 txtcode.setBounds(582, 10, 189, 24);
				  		     	 layeredPane.add(txtcode);
				  		     	 txtcode.setColumns(10);
				  		     	 
				  		     	 JLabel lblCodeTransfert = new JLabel("Code Transfert:");
				  		     	 lblCodeTransfert.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	 lblCodeTransfert.setBounds(483, 11, 102, 24);
				  		     	 layeredPane.add(lblCodeTransfert);
		
		JButton btnValiderStock = new JButton("Valider Stock");
		btnValiderStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				
				if(!remplirMapStock())	{
					JOptionPane.showMessageDialog(null, "Il faut Saisir le stock avant de valider", "Erreur", JOptionPane.ERROR_MESSAGE);
				} else {
					txtcode.setText(Utils.genererCodeTransfer(mapDepot.get(comboDepot.getSelectedItem()).getCode(),ETAT_TRANSFER_STOCK_ENTRE));
					validerStock(listStockMP);
					
					JOptionPane.showMessageDialog(null, "le stock a ÈtÈ validÈe avec succËs", "SuccËs", JOptionPane.INFORMATION_MESSAGE);
					
					initialierMapStock();
					
				}
			
			}
		});
		btnValiderStock.setBounds(291, 506, 100, 23);
		add(btnValiderStock);
		
		JButton btnInitialiser = new JButton("Initialiser");
		btnInitialiser.setIcon(imgInit);
		btnInitialiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initialiser();
				
			}
		});
		
		btnAfficherStock.setIcon(imgRechercher);
		btnInitialiser.setBounds(412, 506, 100, 23);
		add(btnInitialiser);
		btnAfficherStock.setBounds(797, 36, 36, 36);
		add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(comboDepot.getSelectedItem().equals("") || comboMagasin.getSelectedItem().equals(""))	{
				JOptionPane.showMessageDialog(null, "Il faut choisir un magasin", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else {
				txtcode.setText("");
				//txtcode.setText(Utils.genererCodeTransfer(AuthentificationView.utilisateur.getCodeDepot(),ETAT_TRANSFER_STOCK_ENTRE));
				listStockMP=stockMPDAO.listeStockNouveauMP(mapMagasin.get(comboMagasin.getSelectedItem()));
				remplirMapStock();
				afficher_tableMP(listStockMP);
				
				
			}
		  }
		});
		btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
	
				  		     
				  		 
	}
	
void afficher_tableMP(List<StockMP> listStockMP)
	{
	intialiserTabeleau();
		  int i=0;
			while(i<listStockMP.size())
			{	
				
				StockMP stockMP=listStockMP.get(i);
				
				
				Object []ligne={stockMP.getId(),stockMP.getMatierePremier().getCode(),stockMP.getMatierePremier().getNom(),"",""};

				modeleMP.addRow(ligne);
				i++;
			}
	}

void intialiserTabeleau(){
	
	modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code Stock", "Code MatiËre PremiËre","MatiËre PremiËre", "QuantitÈ", "Prix Unitaire"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,true,true
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		   table.setModel(modeleMP); 
		   table.getColumnModel().getColumn(0).setPreferredWidth(20);
		   table.getColumnModel().getColumn(1).setPreferredWidth(160);
		   table.getColumnModel().getColumn(2).setPreferredWidth(260);
		   table.getColumnModel().getColumn(3).setPreferredWidth(60);
		   table.getColumnModel().getColumn(4).setPreferredWidth(60);
}


boolean remplirMapStock(){
	boolean trouve=false;
	for(int j=0;j<table.getRowCount();j++){
		
		if(!table.getValueAt(j, 3).toString().equals("") && !table.getValueAt(j, 4).toString().equals("")){
			mapQuantite.put(Integer.parseInt(table.getValueAt(j, 0).toString()), table.getValueAt(j, 3).toString());
			mapPrixUnitaire.put(Integer.parseInt(table.getValueAt(j, 0).toString()), table.getValueAt(j, 4).toString());
			trouve=true;
		}else {
			mapQuantite.put(Integer.parseInt(table.getValueAt(j, 0).toString()), "0");
			mapPrixUnitaire.put(Integer.parseInt(table.getValueAt(j, 0).toString()), "0");
		}
		
	}
	return trouve;
}


void validerStock(List<StockMP> listStockMP){/*
	float quantite=0;
	float prixUnitaire=0;
	float prixQuantiteTransfer=0;
	
	float prixStock=0;
	
	float quatiteTotal=0;
	float prixTotal=0;
	TransferStockMP transferStock = new TransferStockMP();
	Magasin magasin=depotDAO.magasinByCode(CODE_MAGASIN_FOURNISSEUR);
	List<DetailTransferStockMP> listDetailTransferStockMP= new ArrayList<DetailTransferStockMP>();
	for(int i=0;i<listStockMP.size();i++){	
		
		StockMP stockMP=listStockMP.get(i);
		DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
		prixStock=stockMP.getStock()*stockMP.getPrixUnitaire();
		
		quantite=Float.parseFloat(mapQuantite.get(stockMP.getId()));
		prixUnitaire=Float.parseFloat(mapPrixUnitaire.get(stockMP.getId()));
		prixQuantiteTransfer=quantite*prixUnitaire;
		
		quatiteTotal=quantite+stockMP.getStock();
		
		
		if(quantite>0){
			prixTotal=(prixStock+prixQuantiteTransfer)/quatiteTotal;
			stockMP.setStock(quatiteTotal);
			stockMP.setPrixUnitaire(prixTotal);
		detailTransferStockMP.setMagasinDestination(stockMP.getMagasin());
		detailTransferStockMP.setMagasinSouce(magasin);
		detailTransferStockMP.setMatierePremier(stockMP.getMatierePremier());
		detailTransferStockMP.setQuantite(quantite);
		detailTransferStockMP.setPrixUnitaire(prixUnitaire);
		detailTransferStockMP.setTransferStockMP(transferStock);
		listDetailTransferStockMP.add(detailTransferStockMP);
		stockMPDAO.edit(stockMP);
		}
		
	}
	
		
	transferStock.setCodeTransfer(txtcode.getText());
	transferStock.setCreerPar(AuthentificationView.utilisateur);
	transferStock.setDate(new Date());
	transferStock.setDateTransfer(new Date());
	transferStock.setStatut(ETAT_TRANSFER_STOCK_AJOUT);
	transferStock.setListDetailTransferMP(listDetailTransferStockMP);
	transferStockMPDAO.add(transferStock);
*/}

void initialiser(){
	intialiserTabeleau();
	comboDepot.setSelectedItem("");
	comboMagasin.setSelectedItem("");
	listStockMP=new ArrayList<StockMP>();
	txtcode.setText("");
	intialiserTabeleau();
}

void initialierMapStock(){
	
	for(int j=0;j<table.getRowCount();j++){
		table.setValueAt("", j, 3);
		table.setValueAt("", j,4);
		
	}
}





}

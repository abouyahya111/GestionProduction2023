package presentation.parametre;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import main.AuthentificationView;
import main.ProdLauncher;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.Utils;
import dao.daoImplManager.ClientDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.PrixClientMPDAOImpl;
import dao.daoManager.ClientDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.PrixClientMPDAO;
import dao.entity.Client;
import dao.entity.Depot;
import dao.entity.DetailTransferStockMP;
import dao.entity.Magasin;
import dao.entity.PrixClientMP;
import dao.entity.StockMP;
import dao.entity.TransferStockMP;
import dao.entity.Utilisateur;


public class AjouterPrixClientMP extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;

	private JXTable table;
	private ImageIcon imgModifier;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgRechercher;
	
	
	private JComboBox<String> comboDepot=new JComboBox();
	private  JComboBox<String> comboClient=new JComboBox();;
	private  JComboBox<String> comboFournisseur = new JComboBox();

	private Map< String, Client> mapClient = new HashMap<>();
	private Map< String, Depot> mapDepot = new HashMap<>();
	private Map< Integer, String> mapPrixUnitaire= new HashMap<>();
	
	private List<Depot> listDepot =new ArrayList<Depot>();
	List<PrixClientMP> listPrixClientMP =new ArrayList<PrixClientMP>();
	private Utilisateur utilisateur;
	private Depot depot=new Depot();
	
	private DepotDAO depotDAO;
	private ClientDAO clientDAO;
	private PrixClientMPDAO prixClientMPDAO;
	private JButton btnAfficherStock ;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjouterPrixClientMP() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 565);
        try{
        	
        	
        	depotDAO= new DepotDAOImpl();
        	prixClientMPDAO= new PrixClientMPDAOImpl();
        	utilisateur= AuthentificationView.utilisateur;
        	clientDAO= new ClientDAOImpl();

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
				  		   DefaultCellEditor ce = (DefaultCellEditor) table.getDefaultEditor(Object.class);
					        JTextComponent textField = (JTextComponent) ce.getComponent();
					        util.Utils.copycollercell(textField);
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(9, 130, 782, 367);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  		      List<Client> 	listClient = clientDAO.findListClientByCodeDepot(depot.getCode());
				  		      if(listClient!=null){
				  		    	  
				  		    	  int j=0;
					  		      	while(j<listClient.size())
					  		      		{	
					  		      			Client client=listClient.get(j);
					  		      			comboClient.addItem(client.getNom());
					  		      			comboFournisseur.addItem(client.getNom());
					  		      			mapClient.put(client.getNom(), client);
					  		      			j++;
					  		      		}
				  		      }
				  		     	
				  		     
					  		      	
					  		      comboDepot.addItemListener(new ItemListener() {
					  		     	 	public void itemStateChanged(ItemEvent e) {
					  		     	 	btnAfficherStock.setVisible(true);
					  		     	 	
					  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
					  		     	 	 List<Magasin> listMagasin=new ArrayList<Magasin>();
						  		     	  	 // comboGroupe = new JComboBox();
					  		     	 	comboClient.removeAllItems();
					  		     	 
					  		     	 	//comboGroupe.addItem("");
					  		     	 	if(!comboDepot.getSelectedItem().equals(""))
						  		     	  	   	 depot =mapDepot.get(comboDepot.getSelectedItem());
								  		       
					  		     	 	List<Client> 	listClient = clientDAO.findListClientByCodeDepot(depot.getCode());
								  		      if(listMagasin!=null && listMagasin.size()> 0){
								  		    	  
								  		    	  int j=0;
									  		      	while(j<listClient.size())
									  		      		{	
									  		      			Client client=listClient.get(j);
									  		      			comboClient.addItem(client.getNom());
									  		      			comboFournisseur.addItem(client.getNom());
									  		      			mapClient.put(client.getNom(), client);
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
				  		     	layeredPane.setBounds(9, 30, 781, 60);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblMachine = new JLabel("D\u00E9pot :");
				  		     	lblMachine.setBounds(10, 11, 96, 24);
				  		     	layeredPane.add(lblMachine);
				  		     	lblMachine.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	
				  		     	 
				  		     	 comboDepot.setBounds(51, 11, 144, 24);
				  		     	 layeredPane.add(comboDepot);
				  		     	
				  		     	 
				  		     	 JLabel lblGroupe = new JLabel("Client :");
				  		     	 lblGroupe.setBounds(521, 10, 102, 24);
				  		     	 layeredPane.add(lblGroupe);
				  		     	 lblGroupe.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	
				  		     	 comboClient.setBounds(579, 11, 192, 24);
				  		     	 layeredPane.add(comboClient);
				  		     	 
				  		     	 JLabel lblFournisseur = new JLabel("Fournisseur : ");
				  		     	 lblFournisseur.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	 lblFournisseur.setBounds(226, 10, 102, 24);
				  		     	 layeredPane.add(lblFournisseur);
				  		     	 
				  		     	
				  		     	 comboFournisseur.setBounds(307, 11, 192, 24);
				  		     	 layeredPane.add(comboFournisseur);
		
		JButton btnValiderStock = new JButton("Valider Stock");
		btnValiderStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				
				if(!remplirMapStock())	{
					JOptionPane.showMessageDialog(null, "Il faut Saisir le prix avant de valider", "Erreur", JOptionPane.ERROR_MESSAGE);
				} else {
					
					validerStock(listPrixClientMP);
					
					JOptionPane.showMessageDialog(null, "le prix a ÈtÈ validÈe avec succËs", "SuccËs", JOptionPane.INFORMATION_MESSAGE);
					
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
		btnInitialiser.setBounds(412, 506, 100, 23);
		add(btnInitialiser);
		btnAfficherStock = new JButton("");
		btnAfficherStock.setBounds(863, 42, 36, 36);
		add(btnAfficherStock);
		
		btnAfficherStock.setIcon(imgRechercher);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(comboDepot.getSelectedItem().equals("") || comboClient.getSelectedItem().equals(""))	{
				JOptionPane.showMessageDialog(null, "Il faut choisir un client", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else {
				
				
				if(comboFournisseur.getSelectedItem().equals(comboClient.getSelectedItem()))	{
					JOptionPane.showMessageDialog(null, "Le client doit etre diffÈrent au Fournisseur", "Erreur", JOptionPane.ERROR_MESSAGE);
				} else {
				//txtcode.setText(Utils.genererCodeTransfer(AuthentificationView.utilisateur.getCodeDepot(),ETAT_TRANSFER_STOCK_ENTRE));
				listPrixClientMP=prixClientMPDAO.findAllByFournisseurClient(mapClient.get(comboFournisseur.getSelectedItem()).getCode(),mapClient.get(comboClient.getSelectedItem()).getCode());
				//remplirMapStock();
				afficher_tableMP(listPrixClientMP);
				
				}
				
				
			}
		  }
		});
		btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
	
				  		     
				  		 
	}
	
void afficher_tableMP(List<PrixClientMP> listPrixClientMP)
	{
	intialiserTabeleau();
		  int i=0;
			while(i<listPrixClientMP.size())
			{	
				PrixClientMP prixClientMP=listPrixClientMP.get(i);
				BigDecimal prixUnitaire=prixClientMP.getPrixUnitaire();
				Object []ligne={prixClientMP.getId(),prixClientMP.getMatierePremier().getCode(),prixClientMP.getMatierePremier().getNom(),prixUnitaire};

				modeleMP.addRow(ligne);
				i++;
			}
	}

void intialiserTabeleau(){
	
	modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code Prix", "Code MatiËre PremiËre","MatiËre PremiËre","Prix Unitaire"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,true
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
		   
}


boolean remplirMapStock(){
	boolean trouve=false;
	for(int j=0;j<table.getRowCount();j++){
		
		if(!table.getValueAt(j, 3).toString().equals("") ){
			mapPrixUnitaire.put(Integer.parseInt(table.getValueAt(j, 0).toString()), table.getValueAt(j, 3).toString());
			trouve=true;
		}else {
			mapPrixUnitaire.put(Integer.parseInt(table.getValueAt(j, 0).toString()), "0");
		}
		
	}
	return trouve;
}


void validerStock(List<PrixClientMP> listPrixClientMP){
	BigDecimal prixUnitaire=BigDecimal.ZERO;
	
	for(int i=0;i<listPrixClientMP.size();i++){	
		
			PrixClientMP prixClientMP=listPrixClientMP.get(i);
			prixUnitaire=new BigDecimal(mapPrixUnitaire.get(prixClientMP.getId()));
		if(prixUnitaire.compareTo(BigDecimal.ZERO)>0){
			prixClientMP.setPrixUnitaire(prixUnitaire);
			prixClientMPDAO.edit(prixClientMP);
		}
		
	}
	
		
	
}

void initialiser(){
	intialiserTabeleau();
	comboDepot.setSelectedItem("");
	comboClient.setSelectedItem("");
	listPrixClientMP=new ArrayList<PrixClientMP>();
	intialiserTabeleau();
}

void initialierMapStock(){
	
	for(int j=0;j<table.getRowCount();j++){
		table.setValueAt("", j, 3);
		
		
	}
}
}

package Production;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import main.AuthentificationView;
import main.ProdLauncher;

import org.jdesktop.swingx.JXTable;

import util.Constantes;
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.ClientDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.FactureProductionDAOImpl;
import dao.daoImplManager.PrixClientMPDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoManager.ClientDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.FactureProductionDAO;
import dao.daoManager.PrixClientMPDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.entity.Client;
import dao.entity.CoutMP;
import dao.entity.DetailFactureProduction;
import dao.entity.DetailTransferStockMP;
import dao.entity.FactureProduction;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.PrixClientMP;
import dao.entity.StockMP;
import dao.entity.TransferStockMP;


public class MatierePremiere extends JFrame implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleEmploye;
	private JXTable  tableEmploye=new JXTable();
	
	private ImageIcon imgModifier;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgSupp1;
	
	private JButton btnImprimer;
	private JButton btnRechercher;
	private JButton btnAjoutChefProd;
	private JButton btnSuppchefprod;
	
	private JComboBox<String> comboMachine;
	private JComboBox<String> comboLigneMachine;
	private JComboBox comboMagasin ;
	private JLabel lblClient ;

	
	private List<Magasin> listeMagasin=new ArrayList<Magasin>();
	private List<StockMP> listeStockMP=new ArrayList<StockMP>();
	private Map< String, String> mapCodeClient = new HashMap<>();
	private Map< String, Magasin> mapMagasin = new HashMap<>();
	private Map< Integer, String> mapQuantite= new HashMap<>();
	private Map< Integer, MatierePremier> mapMatierePremiere= new HashMap<>();
	
	private DepotDAO depotDAO;
	private StockMPDAO stockMPDAO;
	private TransferStockMPDAO transferStockMPDAO;
	private ClientDAO clientDAO;
	private FactureProductionDAO factureProductionDAO;
	private PrixClientMPDAO prixClientMPDAO;
	FactureProduction factureProduction=new FactureProduction();
	
	private  Client client;
	
	private  Client clientFournisseur;
	
	private String matierePremiereNonCharge="";
	private String matierePremiereCharge="";
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	@SuppressWarnings("serial")
	public MatierePremiere(final Map< Integer, StockMP> mapQauntiteMatierePremier) {
		
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(60, 60, 832, 492);
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
        	
        	tableEmploye=new JXTable();
        	 DefaultCellEditor ce = (DefaultCellEditor) tableEmploye.getDefaultEditor(Object.class);
		        JTextComponent textField = (JTextComponent) ce.getComponent();
		        util.Utils.copycollercell(textField);
        	depotDAO= new DepotDAOImpl();
        	stockMPDAO= new StockMPDAOImpl();
        	clientDAO= new ClientDAOImpl();
        	transferStockMPDAO=new TransferStockMPDAOImpl();
        	factureProductionDAO= new FactureProductionDAOImpl();
        	prixClientMPDAO=new PrixClientMPDAOImpl();
        	
        	comboMagasin = new JComboBox();
        	lblClient = new JLabel("");
        	matierePremiereNonCharge="";
        	matierePremiereCharge="";
        	client=new Client();
        	clientFournisseur=new Client();
        	
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
	
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
            imgSupp1= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
          } catch (Exception exp){exp.printStackTrace();}

        initialiserTableauEmploye();
      
			
        listeMagasin=stockMPDAO.findMagasinByCodeSaufEnParametre(mapQauntiteMatierePremier.get(0).getMagasin().getId(),MAGASIN_CODE_CATEGORIE_STOCKAGE,MAGASIN_CODE_TYPE_MP);
        getContentPane().setLayout(null);
        comboMagasin.addItem("");  
        
        client=clientDAO.findClientByCodeClient(mapQauntiteMatierePremier.get(0).getMagasin().getCodeMachine());
        
      for(int i=0; i<listeMagasin.size();i++){
    	  
    	  Magasin magasin =listeMagasin.get(i);
    	  comboMagasin.addItem(magasin.getLibelle());
    	  mapCodeClient.put(magasin.getLibelle(), magasin.getCodeMachine());
    	  mapMagasin.put(magasin.getLibelle(), magasin);
    	  
      }
      
      comboMagasin.addItemListener(new ItemListener() {
	     	 	public void itemStateChanged(ItemEvent e) {
	     	 	
	     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
	     	 		 
	     	 		clientFournisseur =clientDAO.findClientByCodeClient(mapCodeClient.get(comboMagasin.getSelectedItem()));
	     	 				
	     	 		lblClient.setText(clientFournisseur.getNom()); 
	     	 		 
	     	 	 }
	     	 	}
	   });

        JScrollPane scrollPane = new JScrollPane(tableEmploye);
        scrollPane.setBounds(10, 102, 672, 180);
				  		     scrollPane.setPreferredSize(new Dimension(63, 200));
				  		     scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     getContentPane().add(scrollPane);
				  		     
				  		   
				  		     comboMagasin.setBounds(72, 64, 239, 27);
				  		     getContentPane().add(comboMagasin);
				  		     
				  		     JLabel lblNewLabel = new JLabel("Magasin :");
				  		     lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
				  		     lblNewLabel.setBounds(10, 63, 64, 27);
				  		     getContentPane().add(lblNewLabel);
				  		     
				  		     
				  		     lblClient.setOpaque(true);
				  		     lblClient.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     lblClient.setBackground(SystemColor.menu);
				  		     lblClient.setBounds(425, 64, 257, 27);
				  		     getContentPane().add(lblClient);
				  		     
				  		     JLabel lblClient_1 = new JLabel("Client :");
				  		     lblClient_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
				  		     lblClient_1.setBounds(366, 63, 64, 27);
				  		     getContentPane().add(lblClient_1);
				  		     
				  		     JLabel lblVeuillezRemplirLa = new JLabel("                                        Veuillez remplir la quantit\u00E9 \u00E0 charger");
				  		     lblVeuillezRemplirLa.setOpaque(true);
				  		     lblVeuillezRemplirLa.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
				  		     lblVeuillezRemplirLa.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     lblVeuillezRemplirLa.setBounds(10, 11, 672, 27);
				  		     getContentPane().add(lblVeuillezRemplirLa);
				  		     
				  		     JButton btnValider = new JButton("Valider");
				  		     btnValider.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     		if(!remplirMapStock(mapQauntiteMatierePremier))	{
				  						JOptionPane.showMessageDialog(null, "Il faut Saisir le stock avant de valider", "Erreur", JOptionPane.ERROR_MESSAGE);
				  					} else {
				  						
				  						int reponse = JOptionPane.showConfirmDialog(null, "Le Stock va etre transférer "
					  		     				+ "Voulez vous Confirmer la transfert ?", 
												"Satisfaction", JOptionPane.YES_NO_OPTION);
										 
										if(reponse == JOptionPane.YES_OPTION )
											{
											if(!comboMagasin.getSelectedItem().equals("")){
												validerStock(mapQauntiteMatierePremier);
												int nbreMatiereTransfere=mapQauntiteMatierePremier.size();
												afficher_tableEmploye(mapQauntiteMatierePremier);
												 
												if(!matierePremiereCharge.equals("")){
												/*JOptionPane.showMessageDialog(null,"Le stock a été validée avec succès des matières premières suivantes:\n"
														+ matierePremiereCharge, "Succès", JOptionPane.INFORMATION_MESSAGE);*/
												
												int rep = JOptionPane.showConfirmDialog(null,"Le stock a été validée avec succès des matières premières suivantes:\n"
														+ matierePremiereCharge
							  		     				+ "Voulez vous Créer une facture ?", 
														"Satisfaction", JOptionPane.YES_NO_OPTION);
												 
												if(rep == JOptionPane.YES_OPTION ) {
													
													factureProductionDAO.add(factureProduction);
													
													JOptionPane.showMessageDialog(null, "La facture a été enregistrée avec succés", "Succès", JOptionPane.INFORMATION_MESSAGE);
													
													if(!matierePremiereNonCharge.equals(""))
														JOptionPane.showMessageDialog(null, "Le stock des matières suivantes n'existe pas dans ce magasin :\n"+matierePremiereNonCharge+
																"Il faut changer le magasin", "Erreur", JOptionPane.ERROR_MESSAGE);
													
													
													
												}
												}else {
													if(!matierePremiereNonCharge.equals(""))
														JOptionPane.showMessageDialog(null, "Le stock des matières suivantes n'existe pas dans ce magasin :\n"+matierePremiereNonCharge+
																"Il faut changer le magasin", "Erreur", JOptionPane.ERROR_MESSAGE);
													
												}
											}
											else {
												JOptionPane.showMessageDialog(null, "Il faut Choisir un magasin!", "Erreur", JOptionPane.ERROR_MESSAGE);
											}
												
											
											 
											
											}
				  					
				  						
				  						
				  					
				  						
				  					}
				  		     		
				  		     		
				  		     	}
				  		     });
				  		     btnValider.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
				  		     btnValider.setBounds(269, 296, 96, 27);
				  		     getContentPane().add(btnValider);
				  		     
				  		     JButton btnImprimerFacture = new JButton("Imprimer Facture");
				  		     btnImprimerFacture.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     		

						  		  	if(factureProduction.getId()>0){
						  		  	List<DetailFactureProduction> listDetailFactureProduction =new ArrayList<DetailFactureProduction>();
						  		  	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						  		  	String date=dateFormat.format(factureProduction.getDateFacture());
						  		  listDetailFactureProduction=factureProduction.getDetailFactureProduction();
						  		  
						  		BigDecimal tva20=(BigDecimal) (factureProduction.getMontantTotal().multiply(new BigDecimal(0.2)) );
						  		BigDecimal totalTTC=tva20.add(factureProduction.getMontantTotal()) ;
									 
									Map parameters = new HashMap();
									parameters.put("nomClientFour", factureProduction.getClientFournisseurMP().getNom());
									parameters.put("adresseClientFour", factureProduction.getClientFournisseurMP().getAdresse());
									parameters.put("telClienFour", factureProduction.getClientFournisseurMP().getNumTel());
									parameters.put("numFacture", factureProduction.getNumFacture());
									parameters.put("dateFacture", date);
									parameters.put("nomClient", factureProduction.getClientMP().getNom());
									parameters.put("adresseClient", factureProduction.getClientMP().getAdresse());
									parameters.put("telClient", factureProduction.getClientMP().getNumTel());
									parameters.put("totalHorsTaxe", String.valueOf(factureProduction.getMontantTotal()));
									parameters.put("tva20", String.valueOf(tva20));
									parameters.put("totalTTC", String.valueOf(totalTTC));
									
								
									
									
									JasperUtils.imprimerFacutreProduction(listDetailFactureProduction,parameters,factureProduction.getNumFacture());
									
									//JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
						  		  	}else {
						  		  	JOptionPane.showMessageDialog(null, "Facture n'existe pas !!", "Erreur Impression", JOptionPane.ERROR_MESSAGE);
						  		  	}
						  		  	
				  		     	}
				  		     });
				  		     btnImprimerFacture.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
				  		     btnImprimerFacture.setBounds(383, 298, 131, 25);
				  		     getContentPane().add(btnImprimerFacture);
				  		     
				  		     

				  		     afficher_tableEmploye(mapQauntiteMatierePremier);
				  		   
				  		
	}
	
	

	

	

void afficher_tableEmploye(Map< Integer, StockMP> mapQauntiteMatierePremier)
	{
	initialiserTableauEmploye();

		  int i=0;
			while(i<mapQauntiteMatierePremier.size())
			{	
				StockMP	stockMP=mapQauntiteMatierePremier.get(i);
				
				if(stockMP!=null){
						
				Object []ligne={stockMP.getMatierePremier().getId(),stockMP.getMatierePremier().getNom(),stockMP.getStock()};

				modeleEmploye.addRow(ligne);
				
				}
				i++;
			}
	}



void initialiserTableauEmploye(){
	modeleEmploye =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"CODE","Matière Première","Quantité Manquante","Quantité à Charger"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,true,true
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		   tableEmploye.setModel(modeleEmploye); 
		   tableEmploye.getColumnModel().getColumn(0).setPreferredWidth(20);
		   tableEmploye.getColumnModel().getColumn(1).setPreferredWidth(160);
		   tableEmploye.getColumnModel().getColumn(2).setPreferredWidth(20);
		   tableEmploye.getColumnModel().getColumn(3).setPreferredWidth(20);
		 
		  
}





boolean remplirMapStock(Map< Integer, StockMP> mapQauntiteMatierePremier){
	boolean trouve=false;
	int i=0;
	mapMatierePremiere= new HashMap<>();
	for(int j=0;j<modeleEmploye.getRowCount();j++){
		
		if( !modeleEmploye.getValueAt(j, 3).toString().equals("") 
				&& modeleEmploye.getValueAt(j, 3)!=null && !modeleEmploye.getValueAt(j, 3).toString().equals("") ){
			mapQuantite.put(Integer.parseInt(modeleEmploye.getValueAt(j, 0).toString()), modeleEmploye.getValueAt(j, 3).toString());
			StockMP	stockMP=mapQauntiteMatierePremier.get(j);
			mapMatierePremiere.put(i, stockMP.getMatierePremier());
			trouve=true;
			i++;
			
		}else {
			mapQuantite.put(Integer.parseInt(modeleEmploye.getValueAt(j, 0).toString()), "0");
		}
		
	}
	return trouve;
}

void validerStock(Map< Integer, StockMP> mapQauntiteMatierePremier){
	BigDecimal quantite=BigDecimal.ZERO;
	BigDecimal prixUnitaire=BigDecimal.ZERO;
	BigDecimal prixQuantiteTransfer=BigDecimal.ZERO;
	BigDecimal montantGlobalFacture=BigDecimal.ZERO;
	BigDecimal prixStockDestionation=BigDecimal.ZERO;
	
	BigDecimal quatiteTotal=BigDecimal.ZERO;
	BigDecimal prixTotal=BigDecimal.ZERO;
	
	BigDecimal prixFacture=BigDecimal.ZERO;
	
	matierePremiereNonCharge="";
	matierePremiereCharge="";
	TransferStockMP transferStock = new TransferStockMP();
	
	 factureProduction=new FactureProduction();
	List<DetailFactureProduction> listDetailFactureProduction= new ArrayList<DetailFactureProduction>();
	
	Magasin magasinSource=mapMagasin.get(comboMagasin.getSelectedItem());
	Magasin magasinDestination=mapQauntiteMatierePremier.get(0).getMagasin();
	
	List<DetailTransferStockMP> listDetailTransferStockMP= new ArrayList<DetailTransferStockMP>();
	for(int i=0;i<mapMatierePremiere.size();i++){	
		
		
		
		MatierePremier matierePremiere=mapMatierePremiere.get(i);
		
		PrixClientMP prixClientMP=prixClientMPDAO.findPrixByClientMP(matierePremiere.getCode(), client.getCode(),clientFournisseur.getCode());
		
		StockMP stockMPSource=stockMPDAO.findStockByMagasinMP(matierePremiere.getId(), magasinSource.getId());
		StockMP stockMPDestination=stockMPDAO.findStockByMagasinMP(matierePremiere.getId(), magasinDestination.getId());
		
		quantite=new BigDecimal(mapQuantite.get(matierePremiere.getId()));
		
		//prixFacture=BigDecimal.parseBigDecimal(mapPrixUnitaire.get(matierePremiere.getId()));
		if(prixClientMP!=null)
			prixFacture=prixClientMP.getPrixUnitaire();
		
		if(quantite.compareTo(stockMPSource.getStock()) <=0){
			DetailFactureProduction detailFactureProduction= new DetailFactureProduction();
		
		DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
		prixStockDestionation=stockMPDestination.getStock().multiply(stockMPDestination.getPrixUnitaire());
		
		prixQuantiteTransfer=quantite.multiply(stockMPSource.getPrixUnitaire());
		
		quatiteTotal=quantite.add(stockMPDestination.getStock());
		
		BigDecimal quantiteTransfere=stockMPSource.getStock().subtract(quantite);
		/*creation de la facture */
		BigDecimal montantTotal =prixFacture.multiply(quantite);
		detailFactureProduction.setMatierePremier(matierePremiere);
		detailFactureProduction.setPrixUnitaire(prixFacture);
		detailFactureProduction.setQuantite(quantite);
		detailFactureProduction.setMontantTotal(montantTotal);
		detailFactureProduction.setFactureProduction(factureProduction);
		listDetailFactureProduction.add(detailFactureProduction);
		montantGlobalFacture=montantGlobalFacture.add(montantTotal);

		
		/* Fin Création facture */
		
		if(quantite.compareTo(BigDecimal.ZERO) >0){
			prixTotal=(prixStockDestionation.add(prixQuantiteTransfer)).divide(quatiteTotal, 6, BigDecimal.ROUND_HALF_UP);
			stockMPDestination.setStock(quatiteTotal);
			stockMPDestination.setPrixUnitaire(prixTotal);
			stockMPSource.setStock(quantiteTransfere);
		detailTransferStockMP.setMagasinDestination(stockMPDestination.getMagasin());
		detailTransferStockMP.setMagasinSouce(magasinSource);
		detailTransferStockMP.setMatierePremier(matierePremiere);
		detailTransferStockMP.setQuantite(quantite);
		detailTransferStockMP.setPrixUnitaire(prixUnitaire);
		detailTransferStockMP.setTransferStockMP(transferStock);
		listDetailTransferStockMP.add(detailTransferStockMP);
		stockMPDAO.edit(stockMPDestination);
		stockMPDAO.edit(stockMPSource);
		}
		mapQauntiteMatierePremier.remove(i);
		matierePremiereCharge+="-"+matierePremiere.getNom()+"\n";
		
	//	if(i>=mapMatierePremiere.size()){
			
			
		
		//	}

		
		
	}else{
		matierePremiereNonCharge+="-"+matierePremiere.getNom()+"\n";
	}
	
		
		

}
	
	String numFacture=Utils.genererNumFactureProduction(FACTURE_PRODUCTION_LIBELLE);
	
	if (listDetailFactureProduction!=null && listDetailFactureProduction.size()>0){
		/*traitement facture production*/
		factureProduction.setClientFournisseurMP(clientFournisseur);
		factureProduction.setClientMP(client);
		factureProduction.setCreerPar(AuthentificationView.utilisateur);
		factureProduction.setDateFacture(new Date());
		factureProduction.setDetailFactureProduction(listDetailFactureProduction);
		factureProduction.setMontantTotal(montantGlobalFacture);
		factureProduction.setNumFacture(numFacture);
		
	}
	if(listDetailTransferStockMP!=null && listDetailTransferStockMP.size()>0){
	transferStock.setCodeTransfer(numFacture);
	transferStock.setCreerPar(AuthentificationView.utilisateur);
	transferStock.setDate(new Date());
	transferStock.setDateTransfer(new Date());
	transferStock.setStatut(ETAT_TRANSFER_STOCK_TRANSFERE);
	transferStock.setListDetailTransferMP(listDetailTransferStockMP);
	transferStockMPDAO.add(transferStock);
	
	}
}
}

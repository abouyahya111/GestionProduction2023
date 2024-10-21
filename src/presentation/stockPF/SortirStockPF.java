package presentation.stockPF;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
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
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import main.AuthentificationView;
import main.ProdLauncher;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.ClientDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.SequenceurDAOImpl;
import dao.daoImplManager.StockPFDAOImpl;
import dao.daoImplManager.TransferStockPFDAOImpl;
import dao.daoManager.ClientDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.SequenceurDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.TransferStockPFDAO;
import dao.entity.Articles;
import dao.entity.Client;
import dao.entity.Depot;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Magasin;
import dao.entity.Sequenceur;
import dao.entity.StockPF;
import dao.entity.TransferStockPF;
import dao.entity.Utilisateur;
import com.toedter.calendar.JDateChooser;
import java.util.Locale;


public class SortirStockPF extends JLayeredPane implements Constantes {
	public JLayeredPane contentPane;	
	private DefaultTableModel	 modeleMP;

	private JXTable table;

	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private JButton btnIntialiserOF;
	
	
	
	List<DetailTransferProduitFini> listDetailTransferProduitFini= new ArrayList<DetailTransferProduitFini>();
	private Map< String, String> mapQuantiteMP = new HashMap<>();
	private Map< Integer, Articles> mapArticle= new HashMap<>();
	private Map< String, Articles> mapArticleTmp = new HashMap<>();
	
	private Map< String, Magasin> mapMagasinSource = new HashMap<>();
	private Map< String, Magasin> mapMagasinDestination = new HashMap<>();
	private Map< String, Client> mapClient = new HashMap<>();
	
	private Map< String, Depot> mapDepotSource = new HashMap<>();
	private Map< String, Depot> mapDepotDestionation = new HashMap<>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Client> listClient =new ArrayList<Client>();
	
	TransferStockPF transferStock ;
	private JComboBox<String> comboDepotSource=new JComboBox();;
	private  JComboBox<String> comboMagasinSource=new JComboBox();;
	private JComboBox<String> comboClient=new JComboBox();;
	
	private JLabel lblMagasinSource;
	private JLabel labelclient;
	private JLabel lblMagasinDstination;
	private ClientDAO clientDAO;
	private DepotDAO depotDAO;
	private StockPFDAO stockPFDAO;
	private TransferStockPFDAO transferStockPFDAO;
	private Utilisateur utilisateur;
	private Depot depot = new Depot();
	private JTextField txtRefTransfere;
	JDateChooser dateChooser = new JDateChooser();
	SequenceurDAO sequenceurDAO;
	  JComboBox comboTypeSortie = new JComboBox();
	  JComboBox comboDepotDestination = new JComboBox();
	  JComboBox comboMagasinDestination = new JComboBox();
	  JLabel labeldepotdestination = new JLabel("D\u00E9pot Destination");
	  JLabel labelmagasindestination = new JLabel("Magasin Destination");
	  
	  
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public SortirStockPF() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 651);
        try{
        	
        	
        	depotDAO=new DepotDAOImpl();
        	stockPFDAO= new StockPFDAOImpl();
        	transferStockPFDAO= new TransferStockPFDAOImpl();
        	utilisateur= AuthentificationView.utilisateur;
        	clientDAO=new ClientDAOImpl();
        	  sequenceurDAO=new SequenceurDAOImpl();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
  
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgValider=new ImageIcon(this.getClass().getResource("/img/valider.png"));
          
          } catch (Exception exp){exp.printStackTrace();}
				  		     btnIntialiserOF = new JButton("Initialiser");
				  		     btnIntialiserOF.setBounds(380, 602, 112, 23);
				  		     add(btnIntialiserOF);
				  		     btnIntialiserOF.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     	intialiser();
				  		     		
				  		     	}
				  		     });
				  		     btnIntialiserOF.setIcon(imgInit);
				  		     btnIntialiserOF.setFont(new Font("Tahoma", Font.PLAIN, 11));
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
				  		     	scrollPane.setBounds(9, 248, 877, 343);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		      
				  		     
					  		      
					  		      
				  		     
				  		  
					  		      	
					  		    
					  		      


				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Des Produits Fini");
				  		     	titledSeparator.setBounds(9, 207, 877, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 13, 877, 164);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblMachine = new JLabel("D\u00E9pot Soure");
				  		     	lblMachine.setBounds(10, 69, 114, 24);
				  		     	layeredPane.add(lblMachine);
				  		     	lblMachine.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	
				  		      labelmagasindestination = new JLabel("Magasin Destination");
				  			labelmagasindestination.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  			labelmagasindestination.setBounds(289, 108, 121, 24);
				  			layeredPane.add(labelmagasindestination);
				  			
				  			  comboMagasinDestination = new JComboBox();
				  			comboMagasinDestination.setBounds(418, 109, 237, 24);
				  			layeredPane.add(comboMagasinDestination);
				  			
				  			  labeldepotdestination = new JLabel("D\u00E9pot Destination");
				  			labeldepotdestination.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  			labeldepotdestination.setBounds(289, 69, 114, 24);
				  			layeredPane.add(labeldepotdestination);
				  			
				  			 comboDepotDestination = new JComboBox();
				  			 comboDepotDestination.addActionListener(new ActionListener() {
				  			 	public void actionPerformed(ActionEvent e) {
				  			 		
				  			 		if(!comboDepotDestination.getSelectedItem().equals(""))
				  			 		{
				  			 		    Depot depot=mapDepotDestionation.get(comboDepotDestination.getSelectedItem());
				  			 		 comboMagasinDestination.removeAllItems();
				  			 		    if(depot!=null)
				  			 		    {
				  			 		      List<Magasin> listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_PF);
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
				  			 		
				  			 	}
				  			 });
				  			comboDepotDestination.setBounds(418, 69, 214, 24);
				  			layeredPane.add(comboDepotDestination);
				  			
				  			labelclient = new JLabel("Client");
				  			labelclient.setBounds(289, 68, 108, 26);
				  			layeredPane.add(labelclient);
				  			comboClient.setBounds(418, 69, 216, 24);
				  			layeredPane.add(comboClient);
				  		     	 comboDepotSource.setBounds(103, 69, 176, 24);
				  		     	 layeredPane.add(comboDepotSource);
				  		     	
				  		     	 
				  		     	 JLabel lblGroupe = new JLabel("Magasin Source");
				  		     	 lblGroupe.setBounds(10, 108, 102, 24);
				  		     	 layeredPane.add(lblGroupe);
				  		     	 lblGroupe.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	
				  		     	 comboMagasinSource.setBounds(103, 109, 176, 24);
				  		     	 layeredPane.add(comboMagasinSource);
				  		  
				  		  lblMagasinSource = new JLabel("Magasin Source ");
				  		  lblMagasinSource.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
				  		  lblMagasinSource.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
				  		  lblMagasinSource.setBounds(10, 46, 237, 14);
				  		  layeredPane.add(lblMagasinSource);
				  		  
				  		  lblMagasinDstination = new JLabel("Magasin D\u00E9stination");
				  		  lblMagasinDstination.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
				  		  lblMagasinDstination.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
				  		  lblMagasinDstination.setBounds(308, 46, 254, 14);
				  		  layeredPane.add(lblMagasinDstination);
				  		  
				  		  JLabel lblCodeTrnsafer = new JLabel("N\u00B0 de Sortie :");
				  		  lblCodeTrnsafer.setBounds(649, 69, 94, 24);
				  		  layeredPane.add(lblCodeTrnsafer);
				  		  
				  		  txtRefTransfere = new JTextField();
				  		util.Utils.copycoller(txtRefTransfere);
				  		  txtRefTransfere.setBounds(725, 69, 131, 24);
				  		  layeredPane.add(txtRefTransfere);
				  		  txtRefTransfere.setColumns(10);
				  		  
				  		  JLabel lblDateSortie = new JLabel("Date Sortie");
				  		  lblDateSortie.setBounds(327, 9, 77, 26);
				  		  layeredPane.add(lblDateSortie);
				  		  
				  		    dateChooser = new JDateChooser();
				  		  dateChooser.setLocale(Locale.FRANCE);
				  		  dateChooser.setDateFormatString("dd/MM/yyyy");
				  		  dateChooser.setBounds(403, 9, 159, 26);
				  		  layeredPane.add(dateChooser);
				  		  
				  		    comboTypeSortie = new JComboBox();
				  		    comboTypeSortie.addActionListener(new ActionListener() {
				  		    	public void actionPerformed(ActionEvent e) {
				  		    		
				  		    		if(comboTypeSortie.getSelectedItem()!=null)
				  		    		{
				  		    			if(!comboTypeSortie.getSelectedItem().toString().equals(""))
					  		    		{
				  		    				if(comboTypeSortie.getSelectedItem().toString().equals(Constantes.SORTIE_PF_DEPOT))
						  		    		{
				  		    					comboDepotDestination.setSelectedItem("");
				  		    					comboDepotDestination.setVisible(true);
				  		    					comboMagasinDestination.setVisible(true);
				  		    					labeldepotdestination.setVisible(true);
				  		    					labelmagasindestination.setVisible(true);
				  		    					comboClient.setVisible(false);
				  		    					labelclient.setVisible(false);
				  		    					
				  		    					
						  		    		}else if(comboTypeSortie.getSelectedItem().toString().equals(Constantes.SORTIE_PF_CLIENT))
						  		    		{
						  		    			
						  		    			comboDepotDestination.setSelectedItem("");
				  		    					comboDepotDestination.setVisible(false);
				  		    					comboMagasinDestination.setVisible(false);
				  		    					labeldepotdestination.setVisible(false);
				  		    					labelmagasindestination.setVisible(false);
				  		    					comboClient.setVisible(true);
						  		    			labelclient.setVisible(true);
						  		    			
						  		    		}
					  		    		}else
					  		    		{
					  		    			
					  		    			comboClient.setVisible(false);
					  		    			labelclient.setVisible(false);
					  		    			comboDepotDestination.setVisible(false);
					  		    			comboMagasinDestination.setVisible(false);
					  		    			labeldepotdestination.setVisible(false);
					  		    			labelmagasindestination.setVisible(false);
					  		    		}
				  		    			
				  		    			
				  		    			
				  		    		}
				  		    		
				  		    		
				  		    		
				  		    	}
				  		    });
				  		  comboTypeSortie.setBounds(103, 11, 176, 24);
				  		  layeredPane.add(comboTypeSortie);
				  		  
				  		  JLabel lblSortieVers = new JLabel("Sortie Vers :");
				  		  lblSortieVers.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  lblSortieVers.setBounds(10, 11, 114, 24);
				  		  layeredPane.add(lblSortieVers);
		
		JButton btnValiderTransfer = new JButton("Valider Transfer PF");
		btnValiderTransfer.setIcon(imgValider);
		btnValiderTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(!remplirMapChargeSupp())	{
				JOptionPane.showMessageDialog(null, "Il faut remplir la quantité", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else {
				Magasin magasinSource =mapMagasinSource.get(comboMagasinSource.getSelectedItem());
				if(magasinSource==null)
				{
					JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Magasin Source SVP","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}
					
					if(dateChooser.getDate()==null)
					{
						JOptionPane.showMessageDialog(null, "Veuillez entrer la date de sortie SVP !!!!! , Veuillez Afficher Stock SVP");
						return;
					}
					
					if(comboTypeSortie.getSelectedItem()!=null)
					{
						
						if(comboTypeSortie.getSelectedItem().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Veuillez Selectionner Type de Sortie SVP Depot OU Client","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
						}else
						{
							
							if(comboTypeSortie.getSelectedItem().equals(Constantes.SORTIE_PF_DEPOT))
							{
								if(comboDepotDestination.getSelectedItem()!=null)
								{
									if(!comboDepotDestination.getSelectedItem().toString().equals(""))
									{
										
										Depot depot=mapDepotDestionation.get(comboDepotDestination.getSelectedItem().toString());
										if(depot==null)
										{
											JOptionPane.showMessageDialog(null, "Veuillez Selectionner le depot De Dedstination SVP","Erreur",JOptionPane.ERROR_MESSAGE);
											return;
										}
									}else
									{
										JOptionPane.showMessageDialog(null, "Veuillez Selectionner le depot De Dedstination SVP","Erreur",JOptionPane.ERROR_MESSAGE);
										return;
									}
									
								}else
								{

									JOptionPane.showMessageDialog(null, "Veuillez Selectionner le depot De Dedstination SVP","Erreur",JOptionPane.ERROR_MESSAGE);
									return;
								}
								
								if(comboMagasinDestination.getSelectedItem()!=null)
								{
									if(!comboMagasinDestination.getSelectedItem().toString().equals(""))
									{
										
										Magasin magasin=mapMagasinDestination.get(comboMagasinDestination.getSelectedItem().toString());
										if(magasin==null)
										{
											JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Magasin De Dedstination SVP","Erreur",JOptionPane.ERROR_MESSAGE);
											return;
										}else
										{
											
											if(magasinSource.getId()==magasin.getId())
											{
												JOptionPane.showMessageDialog(null, "Magasin Source Doit Etre Different Au Magasin Destination SVP","Erreur",JOptionPane.ERROR_MESSAGE);
												return;
											}
											
										}
									}else
									{
										JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Magasin De Dedstination SVP","Erreur",JOptionPane.ERROR_MESSAGE);
										return;
									}
									
								}else
								{

									JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Magasin De Dedstination SVP","Erreur",JOptionPane.ERROR_MESSAGE);
									return;
								}
								
							}else if(comboTypeSortie.getSelectedItem().equals(Constantes.SORTIE_PF_CLIENT))
							{
								if(comboClient.getSelectedItem()!=null)
								{
									if(!comboClient.getSelectedItem().toString().equals(""))
									{
										
										Client client=mapClient.get(comboClient.getSelectedItem().toString());
										
										if(client==null)
										{
											JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Client SVP","Erreur",JOptionPane.ERROR_MESSAGE);
											return;
										}
										
									}else
									{
										JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Client SVP","Erreur",JOptionPane.ERROR_MESSAGE);
										return;
									}
									
									
								}else
								{
									JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Client SVP","Erreur",JOptionPane.ERROR_MESSAGE);
									return;
								}
								
							}
							
							
							
						}
						
						
					}else
					{
						JOptionPane.showMessageDialog(null, "Veuillez Selectionner Type de Sortie SVP Depot OU Client","Erreur",JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					
					if(listDetailTransferProduitFini.size()!=0)
					{
						JOptionPane.showMessageDialog(null, "Transfert Stock PF deja Sortie !!!!! , Veuillez Afficher Stock SVP");
						return;
					}
					
					
					 
					
					transferStock = new TransferStockPF();
					remplirDetailTransfer();
					String dateyear =""; 
					SimpleDateFormat dcn = new SimpleDateFormat("yyyy");
					dateyear= dcn.format(dateChooser.getDate());
					
					if(listDetailTransferProduitFini.size()!=0)
					{
						String codeTransfert=Utils.genererCodeSortiePF(depot.getCode(),dateyear);
						txtRefTransfere.setText(CODE_NUM_SORTIE_PF+codeTransfert);
						transferStock.setCodeTransfer(txtRefTransfere.getText());
						transferStock.setCreerPar(AuthentificationView.utilisateur);
						transferStock.setDate(new Date());
						transferStock.setDateTransfer(dateChooser.getDate());
						transferStock.setStatut(Constantes.STATUT_TRANSFER_PRODUIT_FINI_SORTIE);
						transferStock.setListDetailTransferProduitFini(listDetailTransferProduitFini);
						transferStockPFDAO.add(transferStock);
						JOptionPane.showMessageDialog(null, "Stock transféré avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
						
						Sequenceur  sequenceur=sequenceurDAO.findByCodeLibelle(dateyear,ETAT_TRANSFER_STOCK_SORTIE_PF+"_"+depot.getCode());
						if(sequenceur!=null)
						{
							int valeur=sequenceur.getValeur()+1;
							sequenceur.setValeur(valeur);
							sequenceurDAO.edit(sequenceur);
						}else
						{
							Sequenceur  sequenceurTmp=new Sequenceur();
							sequenceurTmp.setCode(dateyear);
							sequenceurTmp.setLibelle(ETAT_TRANSFER_STOCK_SORTIE_PF+"_"+depot.getCode());
							sequenceurTmp.setValeur(1);
							sequenceurDAO.add(sequenceurTmp);
						}
						
						
						DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				  		  
						
						 DetailTransferProduitFini detailTransferStockPF=listDetailTransferProduitFini.get(0);
						 
							String date=dateFormat.format(detailTransferStockPF.getTransferStockPF().getDateTransfer());
						Map parameters = new HashMap();
						parameters.put("numTransfer", detailTransferStockPF.getTransferStockPF().getCodeTransfer());
						parameters.put("machineSource", detailTransferStockPF.getMagasinSouce().getLibelle());
						parameters.put("depSource", detailTransferStockPF.getMagasinSouce().getDepot().getLibelle());	
						if(comboTypeSortie.getSelectedItem().equals(Constantes.SORTIE_PF_DEPOT))
						{
							parameters.put("depotdestination", "Depot Destination :");	
							parameters.put("destination", "Magasin Destination :");	
							parameters.put("client", comboDepotDestination.getSelectedItem().toString());	
							parameters.put("magasindestination", comboMagasinDestination.getSelectedItem().toString());	
							
						}else if(comboTypeSortie.getSelectedItem().equals(Constantes.SORTIE_PF_CLIENT))
						{
							parameters.put("depotdestination", "Client :");	
							parameters.put("client", detailTransferStockPF.getClient().getNom());	
						}
										
						parameters.put("dateTransfer", date);
						JasperUtils.imprimerBonSortiePF(listDetailTransferProduitFini,parameters,transferStock.getCodeTransfer());
						
						
						
						
						intialiser();
						
						
					}else
					{
						return;
				
			
				}
			}
		  }
		});
		btnValiderTransfer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnValiderTransfer.setBounds(212, 602, 158, 23);
		add(btnValiderTransfer);
		
		JButton btnAfficherStock = new JButton("Afficher Stock");
		btnAfficherStock.setBounds(933, 48, 102, 24);
		add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
							if(comboDepotSource.getSelectedItem().equals(""))	{
				  					JOptionPane.showMessageDialog(null, "Il faut choisir un magasin", "Erreur", JOptionPane.ERROR_MESSAGE);
				  				} else {
				  					
				  					txtRefTransfere.setText("");
				  					listDetailTransferProduitFini.clear();
				  					List<StockPF> listStockPF=new ArrayList<StockPF>();
				  					listStockPF=stockPFDAO.findSockNonVideByMagasin(mapMagasinSource.get(comboMagasinSource.getSelectedItem()).getId());
				  					
				  					afficher_tableMP(listStockPF);
				  				}
				  			  }
		});
		btnAfficherStock.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JButton btnImprimer = new JButton("Imprimer");
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(listDetailTransferProduitFini.size()!=0){
		  		  	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		  		  
					
					 DetailTransferProduitFini detailTransferStockPF=listDetailTransferProduitFini.get(0);
					 
						String date=dateFormat.format(detailTransferStockPF.getTransferStockPF().getDateTransfer());
					Map parameters = new HashMap();
					parameters.put("numTransfer", detailTransferStockPF.getTransferStockPF().getCodeTransfer());
					parameters.put("machineSource", detailTransferStockPF.getMagasinSouce().getLibelle());
					parameters.put("depSource", detailTransferStockPF.getMagasinSouce().getDepot().getLibelle());
					parameters.put("client", detailTransferStockPF.getClient().getNom());					
					parameters.put("dateTransfer", date);
					JasperUtils.imprimerBonSortiePF(listDetailTransferProduitFini,parameters,transferStock.getCodeTransfer());
					
				//	JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
		  		  	}else {
		  		  	JOptionPane.showMessageDialog(null, "Il faut valider le transfer avant d'imprimer ", "Erreur Impression", JOptionPane.ERROR_MESSAGE);
		  		  	}
			}
		});
		btnImprimer.setBounds(508, 602, 89, 23);
		add(btnImprimer);
		listClient=clientDAO.findAll();
		for(int j=0;j<listClient.size();j++)
		{
			
		Client 	client =listClient.get(j);
		comboClient.addItem(client.getNom());
		mapClient.put(client.getNom(), client);
			
			
		}
				  		     
		
		comboTypeSortie.addItem("");
		comboTypeSortie.addItem(Constantes.SORTIE_PF_DEPOT);
		comboTypeSortie.addItem(Constantes.SORTIE_PF_CLIENT);
		 
		
		
		
		comboClient.addItem("");
		
		comboClient.setVisible(false);
		labelclient.setVisible(false);
		comboDepotDestination.setVisible(false);
		comboMagasinDestination.setVisible(false);
		labeldepotdestination.setVisible(false);
		labelmagasindestination.setVisible(false);
		
		
	       
        if(!utilisateur.getCodeDepot().equals(CODE_DEPOT_SIEGE)	) {
   		 	depot = depotDAO.findByCode(utilisateur.getCodeDepot());
	     		comboDepotSource.addItem(depot.getLibelle());
	     		listDepot = depotDAO.findAll();	
	  	      int i=0;
	  	    comboDepotDestination.addItem("");
	  	      	while(i<listDepot.size())
	  	      		{	
	  	      			Depot depot=listDepot.get(i);
	  	      			mapDepotDestionation.put(depot.getLibelle(), depot);
	  	      			comboDepotDestination.addItem(depot.getLibelle());
	  	      			i++;
	  	      		}	
	     		
	     		
   }else {
   	
   	listDepot = depotDAO.findAll();	
	      int i=0;
	      	while(i<listDepot.size())
	      		{	
	      			Depot depot=listDepot.get(i);
	      			mapDepotSource.put(depot.getLibelle(), depot);
	      			comboDepotSource.addItem(depot.getLibelle());
	      			i++;
	      		}
	      	i=0;
	    	while(i<listDepot.size())
	      		{	
	      			Depot depot=listDepot.get(i);
	      			mapDepotDestionation.put(depot.getLibelle(), depot);
	      			comboDepotDestination.addItem(depot.getLibelle());
	      			i++;  	
	      	
	      	
   	
   }
    		
   }
        
        comboMagasinSource.addItem("");	
        List<Magasin> 	listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_PF);
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
	      comboMagasinSource.setSelectedItem("");    
		
	}
	
	
	void intialiser()
	{
		comboClient.setSelectedItem("");
		comboDepotSource.setSelectedItem("");
		
		comboMagasinSource.setSelectedItem("");
comboDepotDestination.setSelectedItem("");
		
		comboMagasinDestination.setSelectedItem("");
		
		for(int j=0;j<table.getRowCount();j++){
			table.setValueAt("", j, 2);
						
		}
				
		
	}
	
	void afficher_tableMP(List<StockPF> listStockPF)
	{

		
		intialiserTableau();
	
	        
		  int i=0;
			while(i<listStockPF.size())
			{	
				
				StockPF stockPF=listStockPF.get(i);
				mapArticleTmp.put(stockPF.getArticles().getCodeArticle(), stockPF.getArticles());
				Object []ligne={stockPF.getArticles().getCodeArticle(),stockPF.getArticles().getLiblle(),NumberFormat.getNumberInstance(Locale.FRANCE).format(stockPF.getStock()),""};

				modeleMP.addRow(ligne);
				
				i++;
			}
			table.setModel(modeleMP); 
	}
	


boolean remplirMapChargeSupp(){
	boolean trouve=false;
	int i=0;
	mapQuantiteMP.clear();
	mapArticle.clear();
	for(int j=0;j<table.getRowCount();j++){
		
		if(!table.getValueAt(j, 3).toString().equals("") ){
			mapQuantiteMP.put(table.getValueAt(j, 0).toString(), table.getValueAt(j, 3).toString());
			mapArticle.put(i, mapArticleTmp.get(table.getValueAt(j, 0).toString()));
			i++;
			trouve=true;
		}
		
	}
	return trouve;
}


List<DetailTransferProduitFini> remplirDetailTransfer(){
	BigDecimal quantite=BigDecimal.ZERO;
	BigDecimal prixStockDestination=BigDecimal.ZERO;
	BigDecimal prixStockSource=BigDecimal.ZERO;
	BigDecimal prixMoyen=BigDecimal.ZERO;
	BigDecimal stockSource=BigDecimal.ZERO;
	BigDecimal stockDestination=BigDecimal.ZERO;
	boolean erreur=false;
	listDetailTransferProduitFini.clear();
	
 
	
	for(int i=0;i<mapArticle.size();i++){
		
		DetailTransferProduitFini detailTransferStockPF=new DetailTransferProduitFini();
		Magasin magasinSource =mapMagasinSource.get(comboMagasinSource.getSelectedItem());
		
		Articles article =mapArticle.get(i);
		quantite=new BigDecimal(mapQuantiteMP.get(article.getCodeArticle()));
		StockPF stockPFSource=stockPFDAO.findStockByMagasinPF(article.getId(), magasinSource.getId());
	
		
		
		 
		
		if(stockPFSource.getStock().compareTo(quantite)>=0){
			
			
			if(comboTypeSortie.getSelectedItem().equals(Constantes.SORTIE_PF_DEPOT))
			{
			 
				 Depot depot=mapDepotDestionation.get(comboDepotDestination.getSelectedItem().toString());
				 
		   Magasin magasin=mapMagasinDestination.get(comboMagasinDestination.getSelectedItem().toString());
		   detailTransferStockPF.setMagasinDestination(magasin);
				
			}else if(comboTypeSortie.getSelectedItem().equals(Constantes.SORTIE_PF_CLIENT))
			{
				 
			 Client client=mapClient.get(comboClient.getSelectedItem().toString());
						
			 detailTransferStockPF.setClient(client);
				
			}
			
			
	
		
		prixStockSource=stockPFSource.getPrixUnitaire();
	
		detailTransferStockPF.setDateTransfer(new Date());
		detailTransferStockPF.setTypeTransfer(Constantes.STATUT_TRANSFER_PRODUIT_FINI_SORTIE);
		
		detailTransferStockPF.setMagasinSouce(magasinSource);
		detailTransferStockPF.setArticle(article);
		detailTransferStockPF.setQuantite(quantite);
		detailTransferStockPF.setPrixUnitaire(prixStockSource);
		detailTransferStockPF.setTransferStockPF(transferStock);
		listDetailTransferProduitFini.add(detailTransferStockPF);
	}else {
		
		JOptionPane.showMessageDialog(null, "Stock de : «"+article.getLiblle()+"» ne peut Transfére! Quantité en stock et inférireure à la quantité à transférer", "Erreur", JOptionPane.ERROR_MESSAGE);
		erreur=true;
	
	}
		
	}
	
	
	if(erreur==false)
	{
		
	for(int i=0;i<listDetailTransferProduitFini.size();i++)	
	{
		
		DetailTransferProduitFini detailTransferProduitFini=listDetailTransferProduitFini.get(i);
		
		StockPF stockPFSource=stockPFDAO.findStockByMagasinPF(detailTransferProduitFini.getArticle().getId(), detailTransferProduitFini.getMagasinSouce().getId());
		if(stockPFSource!=null)
		{
			stockSource=stockPFSource.getStock().subtract(detailTransferProduitFini.getQuantite());
			
			stockPFSource.setStock(stockSource);
			stockPFDAO.edit(stockPFSource);
		}
		
		
		
	}
		
		
		
		
		
	}else
	{
		
		listDetailTransferProduitFini.clear();
		
	}
	


	
	return listDetailTransferProduitFini;
	
}


void intialiserTableau(){
	
	
	modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code Article","Article","Quantité En Stock","Quantité a Tranférer"
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
		 table.getColumnModel().getColumn(0).setPreferredWidth(10);
     table.getColumnModel().getColumn(1).setPreferredWidth(260);
     table.getColumnModel().getColumn(2).setPreferredWidth(160);
  //  table.getColumnModel().getColumn(3).setPreferredWidth(160);
    //q table.getColumnModel().getColumn(4).setPreferredWidth(60);
}
}

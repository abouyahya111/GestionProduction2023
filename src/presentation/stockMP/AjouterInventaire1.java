package presentation.stockMP;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.JTextComponent;

import main.AuthentificationView;
import main.ProdLauncher;


import org.codehaus.groovy.runtime.callsite.GetEffectivePogoFieldSite;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.DateUtils;
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.ActionMPDAOImpl;
import dao.daoImplManager.ActionPerteMPDAOImpl;
import dao.daoImplManager.CategorieMpDAOImpl;
import dao.daoImplManager.CompteMagasinierDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailActionPerteMPDAOImpl;
import dao.daoImplManager.DetailCompteMagasinierDAOImpl;
import dao.daoImplManager.DetailPerteMPDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoImplManager.InventaireDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.PerteMPDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoManager.ActionMPDAO;
import dao.daoManager.ActionPerteMPDAO;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.CompteMagasinierDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailActionPerteMPDAO;
import dao.daoManager.DetailCompteMagasinierDAO;
import dao.daoManager.DetailPerteMPDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.InventaireDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.PerteMPDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.entity.ActionPerteMP;
import dao.entity.BonDePerte;
import dao.entity.CategorieMp;
import dao.entity.CompteMagasinier;
import dao.entity.Depot;
import dao.entity.DetailActionPerteMP;
import dao.entity.DetailCompteMagasinier;
import dao.entity.DetailPerteMP;
import dao.entity.DetailTransferStockMP;
import dao.entity.EtatStockMP;
import dao.entity.FournisseurMP;
import dao.entity.Inventaire;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.PerteMP;
import dao.entity.StockMP;
import dao.entity.SubCategorieMp;
import dao.entity.TransferStockMP;
import dao.entity.Utilisateur;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;


import com.toedter.calendar.JDateChooser;
import java.util.Locale;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.AncestorEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;






public class AjouterInventaire1 extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;

	private JTable table;
	private ImageIcon imgModifier;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private JButton btnRechercher;
	
	
	private JComboBox<String> comboDepot=new JComboBox();
	private  JComboBox<String> comboMagasin=new JComboBox();

	private Map< String, Magasin> mapMagasin = new HashMap<>();
	
	private Map<String, BigDecimal> mapPrixMP = new HashMap<>();
	private Map<String, MatierePremier> mapMP = new HashMap<>();
	private Map<String, FournisseurMP> mapFournisseurMP = new HashMap<>();
	
	private Map<String, MatierePremier> mapNomMP = new HashMap<>();
	private Map<String, MatierePremier> mapCodeMP = new HashMap<>();
	List<CategorieMp> listecategoriemp =new ArrayList<CategorieMp>();
	List<SubCategorieMp> listsubcategoriemp= new ArrayList<SubCategorieMp>();
	private Map< String, SubCategorieMp> subcatMap = new HashMap<>();
	private Map< String, CategorieMp> catMap = new HashMap<>();
	  List<Magasin> listMagasinDechetMP=new ArrayList<Magasin>();
	  private Map< String, Magasin> MapmagasinDechetMP = new HashMap<>();
	private Map< String, Depot> mapDepot = new HashMap<>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<FournisseurMP> listFournisseur=new ArrayList<FournisseurMP>();
	private List<StockMP> listStockMP=new ArrayList<StockMP>();
	private List<StockMP> listStockMPInventaire=new ArrayList<StockMP>();
	private List<MatierePremier> listeMatierePremiereCombo=new ArrayList<MatierePremier>();
	private Map< String, MatierePremier> MapMatierPremiere = new HashMap<>();
	private Map< String, MatierePremier> MapCodeMatierPremiere = new HashMap<>();
	private List<BonDePerte> listBonDePerte=new ArrayList<BonDePerte>();
	private List<DetailTransferStockMP> listDetailTransfertStockMP=new ArrayList<DetailTransferStockMP>();
	private List<Inventaire> listInventaire=new ArrayList<Inventaire>();
	private List<Inventaire> listInventaireNonValider=new ArrayList<Inventaire>();
	private List<Inventaire> listInventaireValider=new ArrayList<Inventaire>();
	private DepotDAO depotDAO;
	private StockMPDAO stockMPDAO;
	private Utilisateur utilisateur;
	private JTextField txtCodeMP;
	private MatierePremiereDAO matierePremiereDAO;
	private CategorieMpDAO categoriempdao;
	private FournisseurMPDAO fournisseurMPDAO;
	private SubCategorieMPDAO subcategoriempdao;
	JComboBox comboMP = new JComboBox();
	JComboBox soucategoriempcombo = new JComboBox();
	JComboBox categoriempcombo = new JComboBox();
	JComboBox combofournisseur = new JComboBox();
	private InventaireDAO inventaireDAO;
	JDateChooser dateoperation = new JDateChooser();
	JComboBox comboMagasinDechet = new JComboBox();
	private TransferStockMPDAO transferStockMPDAO;
	private DetailTransferMPDAO detailTransferMPDAO;
	private List<CompteMagasinier> listComptaMagasinier =new ArrayList<CompteMagasinier>();
	private CompteMagasinierDAO compteMagasinierDAO;
	private DetailCompteMagasinierDAO detailCompteMagasinierDAO;
	private Map< String, CompteMagasinier> MapCompteMagasinier = new HashMap<>();
	private List<DetailCompteMagasinier> listDetailCompteMagasinier =new ArrayList<DetailCompteMagasinier>();
	private List<DetailCompteMagasinier> listDetailCompteMagasinierImprimer =new ArrayList<DetailCompteMagasinier>();
	private List<DetailActionPerteMP> listDetailAcionPerteMP=new ArrayList<DetailActionPerteMP>();
	private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurReception=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurEntrer=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurSortie=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurCharger=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurRetour=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurAutreSortie=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurResterMachine=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurFabrique=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurExistante=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortiePF=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortieEnAttent=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurAutreSortiePerte=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetour=new ArrayList<Object[]>();
	private List <DetailTransferStockMP> listeEtatStockTransfertEnAttenteThe=new ArrayList<DetailTransferStockMP>();
	private List <DetailTransferStockMP> listeEtatStockTransfertEnAttenteNonThe=new ArrayList<DetailTransferStockMP>();
	private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetourFournisseurAnnule=new ArrayList<Object[]>();
	private List <Object[]> Mindate=new ArrayList<Object[]>();
	private List <Object[]> listeObjectStockInitialGroupByMP=new ArrayList<Object[]>();
	private List <Object[]> listeObjectStockInitialGroupByMPByFournisseur=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStockGroupByMP=new ArrayList<Object[]>();
	private List<EtatStockMP> listEtatStockMP=new ArrayList<EtatStockMP>();
	private List<EtatStockMP> listEtatStockMPAfficher=new ArrayList<EtatStockMP>();
	
	PerteMPDAO perteMPDAO;
	DetailPerteMPDAO detailPerteMPDAO;
	private ActionPerteMPDAO actionPerteMPDAO;
	private DetailActionPerteMPDAO detailActionPerteMPDAO;
	private List <Object[]> listeObjectStockFinale=new ArrayList<Object[]>();
	private List <Object[]> listeObjectStockInitial=new ArrayList<Object[]>();
	private DetailTransferMPDAO detailTransferStockMPDAO;
	private ActionPerteMPDAO ActionPerteMPDAO;
	 private PerteMPDAO PerteMPDAO;
	JButton btnValider = new JButton("Valider");
	private Map< String, Inventaire> MapInventaireNonValider = new HashMap<>();
	

	
	
	
	
	
	
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjouterInventaire1() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1453, 716);
        try{
        	
        	
        	depotDAO= new DepotDAOImpl();
        	stockMPDAO=new StockMPDAOImpl();
        	utilisateur= AuthentificationView.utilisateur;
        	categoriempdao= new CategorieMpDAOImpl();
        	subcategoriempdao= new SubCategorieMPAOImpl();
        	matierePremiereDAO=new MatierePremierDAOImpl();
        	fournisseurMPDAO=new FournisseurMPDAOImpl();
        	listsubcategoriemp=subcategoriempdao.findAll();
        	inventaireDAO=new InventaireDAOImpl();
        	transferStockMPDAO=new TransferStockMPDAOImpl();
        	detailTransferMPDAO=new DetailTransferMPDAOImpl();
        	compteMagasinierDAO=new CompteMagasinierDAOImpl();
        	listComptaMagasinier=compteMagasinierDAO.findAll();
        	detailCompteMagasinierDAO=new DetailCompteMagasinierDAOImpl();
        	perteMPDAO=new PerteMPDAOImpl();
        	detailPerteMPDAO=new DetailPerteMPDAOImpl();
        	actionPerteMPDAO=new ActionPerteMPDAOImpl();
        	detailActionPerteMPDAO=new DetailActionPerteMPDAOImpl();
        	detailTransferStockMPDAO = new DetailTransferMPDAOImpl();
        	ActionPerteMPDAO=new ActionPerteMPDAOImpl();
        	PerteMPDAO=new PerteMPDAOImpl();
        	
        	
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		
        //comboDepot.addItem("");
	
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp.png"));
          } catch (Exception exp){exp.printStackTrace();}
        
   	 table = new JTable(modeleMP) {
			
			

			@Override // Always selectAll()
			    public boolean editCellAt(int row, int column, EventObject e) {
			        boolean result = super.editCellAt(row, column, e);
			        final Component editor = getEditorComponent();
			        if (editor == null || !(editor instanceof JTextComponent)) {
			            return result;
			        }
			        if (e instanceof MouseEvent) {
			           EventQueue.invokeLater(() -> {
			            ((JTextComponent) editor).selectAll();
			            
			     
			            
			            });
			        } else {
			            ((JTextComponent) editor).selectAll();
			

			             
			        }
			        return result;
			    }
			};
				  		     
				  		     
				  		     
				  		     
				  		     table.addPropertyChangeListener(new PropertyChangeListener() {
				  		     	public void propertyChange(PropertyChangeEvent arg0) {
				  		     		
				  		     	}
				  		     });
				  		     table.addFocusListener(new FocusAdapter() {
				  		     	@Override
				  		     	public void focusGained(FocusEvent arg0) {
				  		     		
				  		     		
				  		     		
				  		     		
				  		     		
				  		     		
}
				  		     });
				  		     table.addAncestorListener(new AncestorListener() {
				  		     	public void ancestorAdded(AncestorEvent e) {
				  		     		
				  		     		/*
				  		     		if(table.isCellSelected(table.getSelectedRow(), 3)==true)
				  		     		{
				  		     		//	JOptionPane.showMessageDialog(null, e.getKeyCode());
				  		     			if(e.getKeyCode()==0)
				  		     			{
				  		     				table.getModel().setValueAt("", table.getSelectedRow(), 3);
				  		     			}
				  		     		
				  		     			
				  		     			
				  		     		}
				  		     		*/
				  		     		
				  		     		
				  		     		/*
				  		     		
				  		     		KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
				  		     		table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(enter, "");
				  		     		table.getActionMap().put("", new EnterAction());
				  		     		*/
				  		     		
				  		     	}
				  		     	public void ancestorMoved(AncestorEvent arg0) {
				  		     	}
				  		     	public void ancestorRemoved(AncestorEvent arg0) {
				  		     	}
				  		     });
				  		     table.addKeyListener(new KeyAdapter() {
				  		     	@Override
				  		     	public void keyTyped(KeyEvent e) {
				  		 
				  		     		
				  		     		
				  		     		
				  		     	}
				  		     	@Override
				  		     	public void keyReleased(KeyEvent e) {
				  		     		
				  		     	  int key = e.getKeyCode();
				  		     	  //KeyEvent.VK_ENTER
				                    if (key ==  KeyEvent.VK_ENTER || key ==  KeyEvent.VK_TAB) {
/*				                    	
if(table.getSelectedRow()!=table.getRowCount() && table.getSelectedRow()!=0)
{
*/
		
				                	                    	
				                    	
				                    	
				            	
			                    	
	if(table.getValueAt(table.getSelectedRow(), 3).toString().equals("0") || table.getValueAt(table.getSelectedRow(), 3).toString().equals("0.00"))
	{
		

		
		table.setValueAt("", table.getSelectedRow(), 3);
		
		
	}
	
	
	
	
/*	 
}
*/				                      
				                    }
				  		     		
				  		     		
				  		     	}
				  		     	@Override
				  		     	public void keyPressed(KeyEvent e) {
				  		     	  int key = e.getKeyCode();
				  		     	  //KeyEvent.VK_ENTER
				                    if (key ==  KeyEvent.VK_ENTER || key ==  KeyEvent.VK_TAB) 
				                    {
				                    	
				                    	
				                    	

										if(!table.getValueAt(table.getSelectedRow(), 3).toString().equals(""))
										{
											
											try {
												
												
												
												if(new BigDecimal(table.getValueAt(table.getSelectedRow(), 3).toString()).compareTo(BigDecimal.ZERO)<0)
												{
													
													JOptionPane.showMessageDialog(null, "la Quantite doit etre supérieur à 0 SVP");
													return;
												}else
												{
													
													
													if(!table.getValueAt(table.getSelectedRow(), 2).toString().equals(""))
													{
														
													FournisseurMP	fournisseurMP= fournisseurMPDAO.findByCode(table.getValueAt(table.getSelectedRow(), 2).toString());	
													
													Inventaire inventaire=MapInventaireNonValider.get(table.getValueAt(table.getSelectedRow(), 0).toString()+"_"+fournisseurMP.getCodeFournisseur());
													if(inventaire!=null)
													{
														inventaire.setQuantite(new BigDecimal(table.getValueAt(table.getSelectedRow(), 3).toString()));
														inventaireDAO.edit(inventaire);
													}
													
													
													}else
													{
														
														Inventaire inventaire=MapInventaireNonValider.get(table.getValueAt(table.getSelectedRow(), 0).toString());
														if(inventaire!=null)
														{
															inventaire.setQuantite(new BigDecimal(table.getValueAt(table.getSelectedRow(), 3).toString()));
															inventaireDAO.edit(inventaire);
														}
														
														
													}
													
													
													
													
												}
												
												
												
											} catch (Exception ex) {
												JOptionPane.showMessageDialog(null, "la Quantite doit etre en chiffre SVP");
											}
											
											
											
											
										}
				                    	
				                    }
				  					
				  		     		
				  		     		
				  		     		
				  		     		
				  		     		
				  		     	}
				  		     });
				  		     table.addInputMethodListener(new InputMethodListener() {
				  		     	public void caretPositionChanged(InputMethodEvent arg0) {
				  		     	}
				  		     	public void inputMethodTextChanged(InputMethodEvent e) {
				  		     	
				  		     			if(!table.getValueAt(table.getSelectedRow(), 3).toString().equals("0"))
				  		     			{
				  		     				System.out.println("yes");
				  		     			}
				  		     			
				  		     			
				  		     		
				  		     		
				  		     		
				  		     		
				  		     		
				  		     	}
				  		     });
				  		     
				  		 
				  		     table.setFillsViewportHeight(true);
				  		    
				  		     table.addMouseListener(new MouseAdapter() {
				  		     	@Override
				  		     	public void mouseClicked(MouseEvent e) {
}
				  		     });
				  		     
				  		     
				  		     
				  		   table.getDefaultEditor(String.class).addCellEditorListener(
				  	                new CellEditorListener() {
				  	                  


										@Override
										public void editingCanceled(ChangeEvent arg0) {
											  System.out.println("editingCanceled");
											
										}

										@Override
										public void editingStopped(ChangeEvent arg0) {
											
											
											if(!table.getValueAt(table.getSelectedRow(), 3).toString().equals(""))
											{
												
												try {
													
													
													
													if(new BigDecimal(table.getValueAt(table.getSelectedRow(), 3).toString()).compareTo(BigDecimal.ZERO)<0)
													{
														
														JOptionPane.showMessageDialog(null, "la Quantite doit etre supérieur à 0 SVP");
														return;
													}else
													{
														
														
														if(!table.getValueAt(table.getSelectedRow(), 2).toString().equals(""))
														{
															
														FournisseurMP	fournisseurMP= fournisseurMPDAO.findByCode(table.getValueAt(table.getSelectedRow(), 2).toString());	
														
														Inventaire inventaire=MapInventaireNonValider.get(table.getValueAt(table.getSelectedRow(), 0).toString()+"_"+fournisseurMP.getCodeFournisseur());
														if(inventaire!=null)
														{
															inventaire.setQuantite(new BigDecimal(table.getValueAt(table.getSelectedRow(), 3).toString()));
															inventaireDAO.edit(inventaire);
														}
														
														
														}else
														{
															
															Inventaire inventaire=MapInventaireNonValider.get(table.getValueAt(table.getSelectedRow(), 0).toString());
															if(inventaire!=null)
															{
																inventaire.setQuantite(new BigDecimal(table.getValueAt(table.getSelectedRow(), 3).toString()));
																inventaireDAO.edit(inventaire);
															}
															
															
														}
														
														
														
														
													}
													
													
													
												} catch (Exception e) {
													JOptionPane.showMessageDialog(null, "la Quantite doit etre en chiffre SVP");
												}
												
												
												
												
											}
											 
											
											 
											 
											 
										}
				  	                });
				  		     
				  		     
				  		     
				  		     table.setSelectionBackground(new Color(51, 204, 255));
				  		   
				  		     table.setBackground(new Color(255, 255, 255));
				  		   
				  		     table.setForeground(Color.BLACK);
				  		     table.setGridColor(new Color(0, 0, 255));
				  		     table.setAutoCreateRowSorter(true);
				  		     table.setBounds(2, 27, 411, 198);
				  		     table.setRowHeight(20);				  		
				  		   table.getTableHeader().setReorderingAllowed(false);
				  		 table.getTableHeader().setEnabled(false);
				  		
				  		
				  		     intialiserTableau();
				  		  
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.addKeyListener(new KeyAdapter() {
				  		     		@Override
				  		     		public void keyPressed(KeyEvent e) {
				  		     			
				  		     			
				  		     			
				  		     			
				  		     			
				  		     			
				  		     			
				  		     			
				  		     		}
				  		     	});
				  		     	scrollPane.setBounds(9, 260, 1421, 396);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  
					  		      comboDepot.addItemListener(new ItemListener() {
					  		     	 	public void itemStateChanged(ItemEvent e) {
					  		     	 	
					  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
					  		     	 	 List<Magasin> listMagasin=new ArrayList<Magasin>();
						  		     	  	 // comboGroupe = new JComboBox();
					  		     	 	comboMagasin.removeAllItems();
					  		     	 Depot depot=new Depot();
					  		     	 	//comboGroupe.addItem("");
					  		     	 	if(!comboDepot.getSelectedItem().equals(""))
						  		     	  	   	 depot =mapDepot.get(comboDepot.getSelectedItem());
								  		       
						  		     	  		listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_MP);
						  		     	  	 listMagasinDechetMP= depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), MAGASIN_CODE_TYPE_DECHET_MP);
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
								  		      
								  		    if(listMagasinDechetMP!=null){
								  		    	
								  		    	comboMagasinDechet.addItem("");
								  		    	  
								  		    	  int j=0;
									  		      	while(j<listMagasinDechetMP.size())
									  		      		{	
									  		      			Magasin magasin=listMagasinDechetMP.get(j);
									  		      			comboMagasinDechet.addItem(magasin.getLibelle());
									  		      			MapmagasinDechetMP.put(magasin.getLibelle(), magasin);
									  		      			j++;
									  		      		}
									  		      comboMagasinDechet.setSelectedItem("");
									  		      	
								  		      } 
								  		      
								  		      
								  		      
					  		     	 	 }
					  		     	 	}
					  		     	 });
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
				  		     	titledSeparator.setBounds(9, 231, 1421, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 36, 1421, 150);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblMachine = new JLabel("D\u00E9pot :");
				  		     	lblMachine.setBounds(10, 29, 47, 24);
				  		     	layeredPane.add(lblMachine);
				  		     	lblMachine.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	
				  		     	 
				  		     	 comboDepot.setBounds(52, 29, 144, 24);
				  		     	 layeredPane.add(comboDepot);
				  		     	 
				  		     	 
				  		     	 JLabel lblGroupe = new JLabel("Magasin :");
				  		     	 lblGroupe.setBounds(206, 29, 55, 24);
				  		     	 layeredPane.add(lblGroupe);
				  		     	 lblGroupe.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	
				  		     	 comboMagasin.setBounds(273, 30, 191, 24);
				  		     	 layeredPane.add(comboMagasin);
		
		JLabel label = new JLabel("Code MP:");
		label.setBounds(300, 88, 67, 24);
		layeredPane.add(label);
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		

		
		
		
		
		
		txtCodeMP = new JTextField();
		txtCodeMP.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
	 		  	
	  		  		

					
					if(e.getKeyCode()==e.VK_ENTER)
		      		{
						MatierePremier mp=MapCodeMatierPremiere.get(txtCodeMP.getText().toUpperCase());
						if(mp!=null)
						{
							comboMP.setSelectedItem(mp.getNom());
						}else
						{
							JOptionPane.showMessageDialog(null, "Code MP Introuvable !!!!!");
							return;
						}
						
		      		}
				
					
	  		  		
	  		  
				
				
				
			}
		});
		txtCodeMP.setBounds(360, 88, 118, 26);
		layeredPane.add(txtCodeMP);
		txtCodeMP.setText("");
		txtCodeMP.setColumns(10);
		
		JLabel label_1 = new JLabel("MP :");
		label_1.setBounds(488, 89, 37, 24);
		layeredPane.add(label_1);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		 comboMP = new JComboBox();
		 comboMP.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent arg0) {
	  		 	
  		 		if(comboMP.getSelectedIndex()!=-1)
  		 		{
  		 			
  		 			if(!comboMP.getSelectedItem().equals(""))
  		 			{
  		 				
  		 				MatierePremier matierePremier=MapMatierPremiere.get(comboMP.getSelectedItem());
  		 				txtCodeMP.setText(matierePremier.getCode());
  		 				
  		 					
  		 				
  		 			}else
  		 			{
  		 				txtCodeMP.setText(Constantes.CODE_MP);
  		 			}
  		 			
  		 				
  		 			
  		 		}else
  		 		{
  		 			txtCodeMP.setText(Constantes.CODE_MP);
  		 		}
  		 		
  		 		
  		 		
  		 		
  		 		
  		 	}
		 });
		comboMP.setBounds(522, 89, 232, 24);
		layeredPane.add(comboMP);
		AutoCompleteDecorator.decorate(comboMP);
		comboMP.setSelectedIndex(-1);
		
		 btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					listBonDePerte.clear();
					listDetailTransfertStockMP.clear();
					listDetailAcionPerteMP.clear();
					Magasin magasin=mapMagasin.get(comboMagasin.getSelectedItem());
					
					if(table.getRowCount()==0)
					{
						JOptionPane.showMessageDialog(null, "La Table des MP est Vide !!!!");
						return;
					}
					
					
					
				//	CalculerStockFinale();
					
					boolean casevide=false;
					//////////////////////////////////////////////////////////////// verifier les quantites si en chiffre ////////////////////////////////////////			
								for(int i=0; i<table.getRowCount(); i++){
																		
									
									if(!table.getValueAt(i, 3).toString().equals(""))
									{
										
										
									BigDecimal quantite=new BigDecimal(table.getValueAt(i, 3).toString());
									
									
											
										}else
										{
											casevide=true;
											
										}
									
									}
								
								
								if(casevide==true)
								{
									
									
									JOptionPane.showMessageDialog(null, "Une ou Plusieurs Case est vide Veuillez entrer la Quantite SVP !!!!");
									return;
									
								}
								
								
									
				///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////					
								
								Magasin magasinDechetMP=MapmagasinDechetMP.get(comboMagasinDechet.getSelectedItem());
								
							
								//ActionPerteMP actionPerteMP=actionPerteMPDAO.findByDateByMagasin(dateoperation.getDate(), magasinDechetMP, Constantes.ETAT_VALIDER);
							//	listDetailAcionPerteMP=detailActionPerteMPDAO.listeDetailActionPerteMPByActionPerteMP(actionPerteMP);
								
								boolean existe=false;
								/*
								for(int i=0; i<table.getRowCount(); i++){
									
								
								
								String codeMP = table.getValueAt(i, 0).toString();
								
								MatierePremier matierePremier=mapMP.get(codeMP);
								
								if(table.getValueAt(i, 3).toString().equals(""))
								{
									table.setValueAt(0, i, 3);
								}
								
								
								if(!table.getValueAt(i, 3).toString().equals(""))
								{
									FournisseurMP fournisseurMP=null;
									Inventaire inventaire=null;
									Inventaire inventaireInvalider=null;
									if(!table.getValueAt(i, 2).toString().equals(""))
									{
										
										fournisseurMP= fournisseurMPDAO.findByCode(table.getValueAt(i, 2).toString());
										 inventaire=inventaireDAO.findByDateByMagasinByMPByFournisseur(magasin, dateoperation.getDate(), matierePremier,fournisseurMP,Constantes.CODE_INVENTAIRE_1,ETAT_VALIDER);
										 inventaireInvalider=inventaireDAO.findByDateByMagasinByMPByFournisseur(magasin, dateoperation.getDate(), matierePremier,fournisseurMP,Constantes.CODE_INVENTAIRE_1,ETAT_INVALIDER);

									
									}else
									{
										
										 inventaire=inventaireDAO.findByDateByMagasinByMP(magasin, dateoperation.getDate(), matierePremier,Constantes.CODE_INVENTAIRE_1,ETAT_VALIDER);
										 inventaireInvalider=inventaireDAO.findByDateByMagasinByMP(magasin, dateoperation.getDate(), matierePremier,Constantes.CODE_INVENTAIRE_1,ETAT_INVALIDER);
									}
									
									Date datefin=DateUtils.DateaddDay(dateoperation.getDate(), 1);
									SubCategorieMp subCategorieMp=subcategoriempdao.findByCode(SOUS_CATEGORIE_MATIERE_PREMIERE_THE);
									CategorieMp categorieMp=null;
								
									if(inventaire==null && inventaireInvalider==null)
									{
										existe=true;
										Inventaire inventaireTmp=new Inventaire();
										inventaireTmp.setDateoperation(dateoperation.getDate());
										inventaireTmp.setMagasin(magasin);
										inventaireTmp.setMatierePremier(matierePremier);
										inventaireTmp.setCodeInventaire(Constantes.CODE_INVENTAIRE_1);
										if(fournisseurMP!=null)
										{
											
											inventaireTmp.setFournisseurMP(fournisseurMP);
											
											
											
											
											Boolean trouve=false;

											for(int f=0;f<listEtatStockMPAfficher.size() ; f++)
											{

										

											
											


											if(inventaireTmp.getMatierePremier().getId()==listEtatStockMPAfficher.get(f).getMp().getId()  )
											{


											if(inventaireTmp.getFournisseurMP()!=null)
											{
												
												if(listEtatStockMPAfficher.get(f).getFournisseurMP()!=null)
												{
													
													if(inventaireTmp.getFournisseurMP().getId()==listEtatStockMPAfficher.get(f).getFournisseurMP().getId())
													{

													trouve=true;

													inventaireTmp.setQuantiteStock(listEtatStockMPAfficher.get(f).getQuantiteFinale());

													

													}
												}

										


											}


											}




											}

											if(trouve==false)
											{
											if(inventaireTmp.getFournisseurMP()!=null)
											{
												inventaireTmp.setQuantiteStock(BigDecimal.ZERO);
											
											}

											}
											
	
											
											
											
											
											
											
											
											
											
											
										}else
										{
											






Boolean existex=false;

for(int j=0;j<listEtatStockMPAfficher.size() ; j++)
{




if(inventaireTmp.getMatierePremier().getId()==listEtatStockMPAfficher.get(j).getMp().getId() )
{

if(inventaireTmp.getFournisseurMP()==null)
{
	
if(listEtatStockMPAfficher.get(j).getFournisseurMP()==null)	
{
	
	existex=true;

	inventaireTmp.setQuantiteStock(listEtatStockMPAfficher.get(j).getQuantiteFinale());

}
	

}




}



}

if(existex==false)
{
if(inventaireTmp.getFournisseurMP()==null)
{

	inventaireTmp.setQuantiteStock(BigDecimal.ZERO);

}

}



											
											
											
											
											
											
											
											
										}
										inventaireTmp.setQuantitePerte(BigDecimal.ZERO);
										inventaireTmp.setEtat(ETAT_INVALIDER);
										inventaireTmp.setQuantite(new BigDecimal(table.getValueAt(i, 3).toString()));
										inventaireTmp.setDepot(magasin.getDepot());
										inventaireTmp.setQuantitePerte(new BigDecimal(table.getValueAt(i, 4).toString()));
										inventaireTmp.setDateSaisir(new Date());
										inventaireTmp.setCreerPar(utilisateur);
										inventaireDAO.add(inventaireTmp);
										listInventaire.add(inventaireTmp);
										

*/

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

										
										
										
										
										
										
										
										
										
										
										
										
										
										
										
										
//////////////////////////////////////////////////////////////////////////////////////Le Perte ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*									
if(inventaireTmp.getQuantitePerte().compareTo(BigDecimal.ZERO)!=0)
{
	
	
	
	
	for(int j=0;j<listDetailAcionPerteMP.size();j++)
	{
		
	DetailActionPerteMP detailActionPerteMP=	listDetailAcionPerteMP.get(j);
		
	if(detailActionPerteMP.getMatierePremier().getId()==inventaireTmp.getMatierePremier().getId())	
	{
		
		if(inventaireTmp.getFournisseurMP()!=null)
		{
			
			if(detailActionPerteMP.getFournisseurMP()!=null)
			{
				
				if(inventaireTmp.getFournisseurMP().getId()==detailActionPerteMP.getFournisseurMP().getId())
				{
					
					if(detailActionPerteMP.getActionMP().getAction().equals(TRANSFERT_MAGASIN_DECHET))
					{
						
						

						

						
						
					BonDePerte bonDePerte=new BonDePerte();
					bonDePerte.setDateoperation(inventaireTmp.getDateoperation());
					bonDePerte.setDepot(magasinDechetMP.getDepot());
					
					if(inventaireTmp.getMatierePremier().getPrix()!=null)
					{
					bonDePerte.setPrix(inventaireTmp.getMatierePremier().getPrix());
					
					}else
					{
						
					StockMP stockMP=stockMPDAO.findStockByMagasinMPByFournisseurMP(inventaireTmp.getMatierePremier().getId(), inventaireTmp.getMagasin().getId(), inventaireTmp.getFournisseurMP().getId())	;
					if(stockMP!=null)
					{
						bonDePerte.setPrix(stockMP.getPrixUnitaire());
					}
					
					
					}


					bonDePerte.setFournisseurMP(inventaireTmp.getFournisseurMP());
					
					
					bonDePerte.setMagasin(magasinDechetMP);
					bonDePerte.setMatierePremier(inventaireTmp.getMatierePremier());
					bonDePerte.setQuantite(detailActionPerteMP.getQuantite());
					bonDePerte.setMontant(bonDePerte.getQuantite().multiply(bonDePerte.getPrix()));
					listBonDePerte.add(bonDePerte);	
					
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					
					
					DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
					detailTransferStockMP.setPrixUnitaire(inventaireTmp.getMatierePremier().getPrix());
					if(inventaireTmp.getMatierePremier().getPrix()!=null)
					{
						detailTransferStockMP.setPrixUnitaire(inventaireTmp.getMatierePremier().getPrix());
					}else
					{
						if(inventaireTmp.getFournisseurMP()!=null)
						{
							StockMP stockMP=stockMPDAO.findStockByMagasinMPByFournisseurMP(inventaireTmp.getMatierePremier().getId(), inventaireTmp.getMagasin().getId(), inventaireTmp.getFournisseurMP().getId())	;
							if(stockMP!=null)
							{
								detailTransferStockMP.setPrixUnitaire(stockMP.getPrixUnitaire());
							}else
							{
								detailTransferStockMP.setPrixUnitaire(BigDecimal.ZERO);
							}
							
						}else
						{
							
							StockMP stockMP=stockMPDAO.findStockByMagasinMP(inventaireTmp.getMatierePremier().getId(), inventaireTmp.getMagasin().getId())	;
							if(stockMP!=null)
							{
								detailTransferStockMP.setPrixUnitaire(stockMP.getPrixUnitaire());
							}else
							{
								detailTransferStockMP.setPrixUnitaire(BigDecimal.ZERO);
							}
						}
						

						
					}
					
					detailTransferStockMP.setFournisseur(inventaireTmp.getFournisseurMP());	
					detailTransferStockMP.setMagasinSouce(magasin);
					detailTransferStockMP.setMagasinDestination(magasinDechetMP);
					detailTransferStockMP.setMatierePremier(inventaireTmp.getMatierePremier());
					detailTransferStockMP.setQuantite(detailActionPerteMP.getQuantite());
					detailTransferStockMP.setTransferStockMP(transfererStockMP);
					listDetailTransfertStockMP.add(detailTransferStockMP);
				
					
					
					
					listDetailCompteMagasinier.clear();
					
					CompteMagasinier compteMagasinier=detailActionPerteMP.getCompteMagasinier();
					DetailCompteMagasinier detailCompteMagasinier=new DetailCompteMagasinier();
					detailCompteMagasinier.setCompteMagasinier(compteMagasinier);
					detailCompteMagasinier.setDepot(inventaireTmp.getDepot());
					detailCompteMagasinier.setMagasin(inventaireTmp.getMagasin());
					detailCompteMagasinier.setMatierePremier(inventaireTmp.getMatierePremier());
				
					detailCompteMagasinier.setQuantite(detailActionPerteMP.getQuantite());
					
					
					if(inventaireTmp.getFournisseurMP()!=null)
					{
						if(inventaireTmp.getMatierePremier().getPrix()!=null)
						{
							detailCompteMagasinier.setPrix(inventaireTmp.getMatierePremier().getPrix());
							
						}else
						{
							StockMP stockMP=stockMPDAO.findStockByMagasinMPByFournisseurMP(inventaireTmp.getMatierePremier().getId(), inventaireTmp.getMagasin().getId(), inventaireTmp.getFournisseurMP().getId())	;
							if(stockMP!=null)
							{
								detailCompteMagasinier.setPrix(stockMP.getPrixUnitaire());
							}else
							{
								detailCompteMagasinier.setPrix(BigDecimal.ZERO);
							}
							
							
						}
						

						detailCompteMagasinier.setFournisseurMP(inventaireTmp.getFournisseurMP());
						
					}
					
					detailCompteMagasinier.setMontant(detailCompteMagasinier.getQuantite().multiply(detailCompteMagasinier.getPrix()));
					
					detailCompteMagasinier.setDateoperation(dateoperation.getDate());
					detailCompteMagasinier.setDesignation(TYPE_PERTE);
					compteMagasinier.setMontant(compteMagasinier.getMontant().add(detailCompteMagasinier.getQuantite().multiply(detailCompteMagasinier.getPrix())));
					listDetailCompteMagasinier.add(detailCompteMagasinier);
					listDetailCompteMagasinierImprimer.add(detailCompteMagasinier);
					compteMagasinier.setDetailCompteMagasinier(listDetailCompteMagasinier);
					compteMagasinierDAO.edit(compteMagasinier);
					
					
					
					
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////					

					
					
					
						
						
					}else if(detailActionPerteMP.getActionMP().getAction().equals(AVANCE_SUR_MAGASINIER))
					{
						
						
						listDetailCompteMagasinier.clear();
						
						CompteMagasinier compteMagasinier=detailActionPerteMP.getCompteMagasinier();
						DetailCompteMagasinier detailCompteMagasinier=new DetailCompteMagasinier();
						detailCompteMagasinier.setCompteMagasinier(compteMagasinier);
						detailCompteMagasinier.setDepot(inventaireTmp.getDepot());
						detailCompteMagasinier.setMagasin(inventaireTmp.getMagasin());
						detailCompteMagasinier.setMatierePremier(inventaireTmp.getMatierePremier());
					
						detailCompteMagasinier.setQuantite(detailActionPerteMP.getQuantite());
						
						
						if(inventaireTmp.getFournisseurMP()!=null)
						{
							if(inventaireTmp.getMatierePremier().getPrix()!=null)
							{
								detailCompteMagasinier.setPrix(inventaireTmp.getMatierePremier().getPrix());
								
							}else
							{
								StockMP stockMP=stockMPDAO.findStockByMagasinMPByFournisseurMP(inventaireTmp.getMatierePremier().getId(), inventaireTmp.getMagasin().getId(), inventaireTmp.getFournisseurMP().getId())	;
								if(stockMP!=null)
								{
									detailCompteMagasinier.setPrix(stockMP.getPrixUnitaire());
									
								}else
								{
									detailCompteMagasinier.setPrix(BigDecimal.ZERO);
								}
								
								
							}
							

							detailCompteMagasinier.setFournisseurMP(inventaireTmp.getFournisseurMP());
							
						}
						
						detailCompteMagasinier.setMontant(detailCompteMagasinier.getQuantite().multiply(detailCompteMagasinier.getPrix()));
						
						detailCompteMagasinier.setDateoperation(dateoperation.getDate());
						detailCompteMagasinier.setDesignation(TYPE_PERTE);
						compteMagasinier.setMontant(compteMagasinier.getMontant().add(detailCompteMagasinier.getQuantite().multiply(detailCompteMagasinier.getPrix())));
						listDetailCompteMagasinier.add(detailCompteMagasinier);
						listDetailCompteMagasinierImprimer.add(detailCompteMagasinier);
						compteMagasinier.setDetailCompteMagasinier(listDetailCompteMagasinier);
						compteMagasinierDAO.edit(compteMagasinier);
						
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////					
						
						
DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
detailTransferStockMP.setPrixUnitaire(inventaireTmp.getMatierePremier().getPrix());
if(inventaireTmp.getMatierePremier().getPrix()!=null)
{
detailTransferStockMP.setPrixUnitaire(inventaireTmp.getMatierePremier().getPrix());
}else
{
if(inventaireTmp.getFournisseurMP()!=null)
{
StockMP stockMP=stockMPDAO.findStockByMagasinMPByFournisseurMP(inventaireTmp.getMatierePremier().getId(), inventaireTmp.getMagasin().getId(), inventaireTmp.getFournisseurMP().getId())	;
if(stockMP!=null)
{
detailTransferStockMP.setPrixUnitaire(stockMP.getPrixUnitaire());
}else
{
	detailTransferStockMP.setPrixUnitaire(BigDecimal.ZERO);
}

}else
{

StockMP stockMP=stockMPDAO.findStockByMagasinMP(inventaireTmp.getMatierePremier().getId(), inventaireTmp.getMagasin().getId())	;
if(stockMP!=null)
{
detailTransferStockMP.setPrixUnitaire(stockMP.getPrixUnitaire());
}else
{
	detailTransferStockMP.setPrixUnitaire(BigDecimal.ZERO);
}
}



}

detailTransferStockMP.setFournisseur(inventaireTmp.getFournisseurMP());	
detailTransferStockMP.setMagasinSouce(magasin);

detailTransferStockMP.setMatierePremier(inventaireTmp.getMatierePremier());
detailTransferStockMP.setQuantite(detailActionPerteMP.getQuantite());
detailTransferStockMP.setTransferStockMP(transfererStockMP);
listDetailTransfertStockMP.add(detailTransferStockMP);			

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////					

						
						
						
						
						
					}else if(detailActionPerteMP.getActionMP().getAction().equals(RETOUR_PRODUCTION))
					{
						
						
						
						
						
					}
					
					
					
					
					
					
					
				}
				
				
				
			}
			
			
			
		}else
		{
			

			
			if(detailActionPerteMP.getFournisseurMP()==null)
			{
				
				
					
					if(detailActionPerteMP.getActionMP().getAction().equals(TRANSFERT_MAGASIN_DECHET))
					{
						
						

						

						
						
					BonDePerte bonDePerte=new BonDePerte();
					bonDePerte.setDateoperation(inventaireTmp.getDateoperation());
					bonDePerte.setDepot(magasinDechetMP.getDepot());
					
					if(inventaireTmp.getMatierePremier().getPrix()!=null)
					{
					bonDePerte.setPrix(inventaireTmp.getMatierePremier().getPrix());
					
					}else
					{
						
					StockMP stockMP=stockMPDAO.findStockByMagasinMP(inventaireTmp.getMatierePremier().getId(), inventaireTmp.getMagasin().getId())	;
					if(stockMP!=null)
					{
						bonDePerte.setPrix(stockMP.getPrixUnitaire());
					}else
					{
						bonDePerte.setPrix(BigDecimal.ZERO);
					}
					
					
					}


					
					bonDePerte.setMagasin(magasinDechetMP);
					bonDePerte.setMatierePremier(inventaireTmp.getMatierePremier());
					bonDePerte.setQuantite(detailActionPerteMP.getQuantite());
					bonDePerte.setMontant(bonDePerte.getQuantite().multiply(bonDePerte.getPrix()));
					listBonDePerte.add(bonDePerte);	
					
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					
					
					DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
					detailTransferStockMP.setPrixUnitaire(inventaireTmp.getMatierePremier().getPrix());
					if(inventaireTmp.getMatierePremier().getPrix()!=null)
					{
						detailTransferStockMP.setPrixUnitaire(inventaireTmp.getMatierePremier().getPrix());
					}else
					{
						if(inventaireTmp.getFournisseurMP()!=null)
						{
							StockMP stockMP=stockMPDAO.findStockByMagasinMPByFournisseurMP(inventaireTmp.getMatierePremier().getId(), inventaireTmp.getMagasin().getId(), inventaireTmp.getFournisseurMP().getId())	;
							if(stockMP!=null)
							{
								detailTransferStockMP.setPrixUnitaire(stockMP.getPrixUnitaire());
							}else
							{
								detailTransferStockMP.setPrixUnitaire(BigDecimal.ZERO);
							}
							
						}else
						{
							
							StockMP stockMP=stockMPDAO.findStockByMagasinMP(inventaireTmp.getMatierePremier().getId(), inventaireTmp.getMagasin().getId())	;
							if(stockMP!=null)
							{
								detailTransferStockMP.setPrixUnitaire(stockMP.getPrixUnitaire());
							}else
							{
								detailTransferStockMP.setPrixUnitaire(BigDecimal.ZERO);
							}
						}
						

						
					}
					
					detailTransferStockMP.setFournisseur(inventaireTmp.getFournisseurMP());	
					detailTransferStockMP.setMagasinSouce(magasin);
					detailTransferStockMP.setMagasinDestination(magasinDechetMP);
					detailTransferStockMP.setMatierePremier(inventaireTmp.getMatierePremier());
					detailTransferStockMP.setQuantite(detailActionPerteMP.getQuantite());
					detailTransferStockMP.setTransferStockMP(transfererStockMP);
					listDetailTransfertStockMP.add(detailTransferStockMP);
					
					
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////					
					
					
	
						
						
					}else if(detailActionPerteMP.getActionMP().getAction().equals(AVANCE_SUR_MAGASINIER))
					{
						
						
						listDetailCompteMagasinier.clear();
					
						CompteMagasinier compteMagasinier=detailActionPerteMP.getCompteMagasinier();
						DetailCompteMagasinier detailCompteMagasinier=new DetailCompteMagasinier();
						detailCompteMagasinier.setCompteMagasinier(compteMagasinier);
						detailCompteMagasinier.setDepot(inventaireTmp.getDepot());
						detailCompteMagasinier.setMagasin(inventaireTmp.getMagasin());
						detailCompteMagasinier.setMatierePremier(inventaireTmp.getMatierePremier());
					
						detailCompteMagasinier.setQuantite(detailActionPerteMP.getQuantite());
						
					
							

							if(inventaireTmp.getMatierePremier().getPrix()!=null)
							{
								detailCompteMagasinier.setPrix(inventaireTmp.getMatierePremier().getPrix());
								
							}else
							{
								StockMP stockMP=stockMPDAO.findStockByMagasinMP(inventaireTmp.getMatierePremier().getId(), inventaireTmp.getMagasin().getId())	;
							if(stockMP!=null)
							{
								detailCompteMagasinier.setPrix(stockMP.getPrixUnitaire());
							}else
							{
								detailCompteMagasinier.setPrix(BigDecimal.ZERO);
							}
								
								
							}
							
							
							
						
						
						detailCompteMagasinier.setMontant(detailCompteMagasinier.getQuantite().multiply(detailCompteMagasinier.getPrix()));
						
						detailCompteMagasinier.setDateoperation(dateoperation.getDate());
						detailCompteMagasinier.setDesignation(TYPE_PERTE);
						compteMagasinier.setMontant(compteMagasinier.getMontant().add(detailCompteMagasinier.getQuantite().multiply(detailCompteMagasinier.getPrix())));
						listDetailCompteMagasinier.add(detailCompteMagasinier);
						listDetailCompteMagasinierImprimer.add(detailCompteMagasinier);
						compteMagasinier.setDetailCompteMagasinier(listDetailCompteMagasinier);
						compteMagasinierDAO.edit(compteMagasinier);
						
						
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////					
						
						
						DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
						detailTransferStockMP.setPrixUnitaire(inventaireTmp.getMatierePremier().getPrix());
						if(inventaireTmp.getMatierePremier().getPrix()!=null)
						{
							detailTransferStockMP.setPrixUnitaire(inventaireTmp.getMatierePremier().getPrix());
						}else
						{
							if(inventaireTmp.getFournisseurMP()!=null)
							{
								StockMP stockMP=stockMPDAO.findStockByMagasinMPByFournisseurMP(inventaireTmp.getMatierePremier().getId(), inventaireTmp.getMagasin().getId(), inventaireTmp.getFournisseurMP().getId())	;
								if(stockMP!=null)
								{
									detailTransferStockMP.setPrixUnitaire(stockMP.getPrixUnitaire());
								}else
								{
									detailTransferStockMP.setPrixUnitaire(BigDecimal.ZERO);
								}
								
							}else
							{
								
								StockMP stockMP=stockMPDAO.findStockByMagasinMP(inventaireTmp.getMatierePremier().getId(), inventaireTmp.getMagasin().getId())	;
								if(stockMP!=null)
								{
									detailTransferStockMP.setPrixUnitaire(stockMP.getPrixUnitaire());
								}else
								{
									detailTransferStockMP.setPrixUnitaire(BigDecimal.ZERO);
								}
							}
							

							
						}
						
						detailTransferStockMP.setFournisseur(inventaireTmp.getFournisseurMP());	
						detailTransferStockMP.setMagasinSouce(magasin);
						
						detailTransferStockMP.setMatierePremier(inventaireTmp.getMatierePremier());
						detailTransferStockMP.setQuantite(detailActionPerteMP.getQuantite());
						detailTransferStockMP.setTransferStockMP(transfererStockMP);
						listDetailTransfertStockMP.add(detailTransferStockMP);			
						
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////					
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
					}else if(detailActionPerteMP.getActionMP().getAction().equals(RETOUR_PRODUCTION))
					{
						
						
						
						
						
						
						
						
						
						
					}
					
					
					
					
					
					
					
				
				
				
				
			}
			
			
			
		
			
		}
		
		
		
		
		
		
	}
		
	}
	

}

*/

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

																			
								/*	}
									
								}
								
								}*/
								
								
								
								
								
								/*
								if(listDetailTransfertStockMP.size()!=0)
								{
									String codeTransfert=Utils.genererCodeTransfer(AuthentificationView.utilisateur.getCodeDepot(),TYPE_PERTE);
									
									transfererStockMP.setCodeTransfer(codeTransfert);
									transfererStockMP.setCreerPar(AuthentificationView.utilisateur);
									transfererStockMP.setDate(new Date());
									transfererStockMP.setDateTransfer(dateoperation.getDate());
									transfererStockMP.setDepot(magasinDechetMP.getDepot());
									transfererStockMP.setStatut(TYPE_PERTE);
									transferStockMPDAO.add(transfererStockMP);
									
									
									for(int i=0;i<listDetailTransfertStockMP.size();i++)
									{
										
									
										detailTransferMPDAO.add(listDetailTransfertStockMP.get(i));
										
										
									}
									
									
									
								}
								
								
								actionPerteMP.setEtat(ETAT_VALIDER);
								
								actionPerteMPDAO.edit(actionPerteMP);
								
								
								PerteMP perteMP=perteMPDAO.findByDateByMagasin(dateoperation.getDate(), magasinDechetMP, ETAT_INVALIDER);
								if(perteMP!=null)
								{
									
									perteMP.setEtat(ETAT_VALIDER);
									perteMPDAO.edit(perteMP);
									
								}
								
								
								ActionPerteMP actionperteMPTmp=ActionPerteMPDAO.findByDateByMagasin(dateoperation.getDate(), magasinDechetMP, ETAT_INVALIDER);
								
								if(actionperteMPTmp!=null)
								{
									actionperteMPTmp.setEtat(ETAT_VALIDER);
									ActionPerteMPDAO.edit(actionperteMPTmp);
								}
								
								*/
								
								
							for(int i=0;i<listInventaireNonValider.size();i++)
							{
								
								Inventaire inventaire=listInventaireNonValider.get(i);
								inventaire.setEtat(ETAT_INVALIDER);
								inventaireDAO.edit(inventaire);
								existe=true;
								
							}
								
								
								
								
								
								
								
								
								
								
								
								
								
								if(existe==true)
								{
									JOptionPane.showMessageDialog(null, "L'Inventaire 1 valider avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);	
									
									
	///////////////////////////////////////////////////////////////// Impression /////////////////////////////////////////////////////////////////////////////////////////////////////////////								
									
									
									Map parameters = new HashMap();
									parameters.put("depot", comboDepot.getSelectedItem());
									
									parameters.put("magasin",comboMagasin.getSelectedItem());
									parameters.put("inventaire",Constantes.CODE_INVENTAIRE_1);
									
									
									JasperUtils.imprimerInventaire(listInventaireNonValider, parameters);
									
									//JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
									/*
									if(listBonDePerte.size()!=0)
									{
										
										BonDePerte bonDePerte=listBonDePerte.get(0);
										Map parametersTmp = new HashMap();					             
										parametersTmp.put("magasinDest",  bonDePerte.getMagasin().getLibelle());
										parametersTmp.put("depDest", bonDePerte.getDepot().getLibelle());
										parametersTmp.put("dateTransfer", bonDePerte.getDateoperation());																				
										
										JasperUtils.imprimerBonDePerteMP (listBonDePerte,parametersTmp);
										
									}
									
									
									if(listDetailCompteMagasinierImprimer.size()!=0)
									{
										
										DetailCompteMagasinier detailCompteMagasinier=listDetailCompteMagasinierImprimer.get(0);
										Map parametersTmp = new HashMap();					             
										parametersTmp.put("magasinDest",  detailCompteMagasinier.getMagasin().getLibelle());
										parametersTmp.put("depDest", detailCompteMagasinier.getDepot().getLibelle());
										parametersTmp.put("dateTransfer", detailCompteMagasinier.getDateoperation());
										JasperUtils.imprimerBonDeManque(listDetailCompteMagasinier, parametersTmp);
										
									}
									*/
									
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////									
									
									intialiserTableau();
									dateoperation.setDate(null);
									combofournisseur.setSelectedIndex(-1);
								    comboMP.setSelectedIndex(-1);
									categoriempcombo.setSelectedIndex(-1);
									soucategoriempcombo.setSelectedIndex(-1);
									combofournisseur.setSelectedIndex(-1);
									txtCodeMP.setText("");
									btnValider.setEnabled(false);
									
									
									
								}else
								{
									JOptionPane.showMessageDialog(null, "L'Inventaire 1 des MP deja existant", "Erreur", JOptionPane.ERROR_MESSAGE);	
									return;
								}
							
					
					
				} catch (NumberFormatException e) {
					
					
					JOptionPane.showMessageDialog(null, "Veuillez entrer les quantite en chiffre SVP", "Erreur", JOptionPane.ERROR_MESSAGE);	
				}
				
				
			
				
				
			}
		});
		btnValider.setBounds(638, 667, 89, 23);
		btnValider.setVisible(true);
		add(btnValider);
	
		comboMP.addItem("");
		
		JLabel label_2 = new JLabel("Sous-Categorie Mp");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_2.setBounds(1120, 30, 144, 24);
		layeredPane.add(label_2);
		
		 soucategoriempcombo = new JComboBox();
		 soucategoriempcombo.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		

  		  		int i=0;
  		  		if(soucategoriempcombo.getSelectedIndex()!=-1 )
  		  		{
  		  			if(!soucategoriempcombo.getSelectedItem().equals(""))
  		  			{
  		  			categoriempcombo.removeAllItems();
  		  		categoriempcombo.addItem("");
  		  			listecategoriemp=categoriempdao.findBySubcategorie(subcatMap.get(soucategoriempcombo.getSelectedItem()).getId());
  		  			if(listecategoriemp!=null)
  		  			{
  		  				while(i<listecategoriemp.size())
  		  				{
  		  					catMap.put(listecategoriemp.get(i).getNom(), listecategoriemp.get(i));
  		  					categoriempcombo.addItem(listecategoriemp.get(i).getNom());
  		  					i++;
  		  				}
  		  				
  		  			}
  		  				
  		  			}else
  		  			{
  		  			listecategoriemp.clear();
  		  			categoriempcombo.removeAllItems();
  		  		categoriempcombo.addItem("");
  		  			comboMP.removeAllItems();
  		  			}
  		  	
  		  			
  		  		}else
  		  		{
  		  		listecategoriemp.clear();
  		  		categoriempcombo.removeAllItems();
  		  	categoriempcombo.addItem("");
  		  comboMP.removeAllItems();
  		  		}
  		  		
  		  	
		 		
		 		
		 		
		 	}
		 });
		soucategoriempcombo.setBounds(1227, 30, 184, 24);
		layeredPane.add(soucategoriempcombo);
		AutoCompleteDecorator.decorate(soucategoriempcombo);
		JLabel label_3 = new JLabel("Categorie Mp");
		label_3.setBounds(10, 88, 80, 26);
		layeredPane.add(label_3);
		
		 categoriempcombo = new JComboBox();
		 categoriempcombo.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent arg0) {
		 		

  		  		
  		  		if(categoriempcombo.getSelectedIndex()!=-1)
  		  		{
  		  			
  		  			if(!categoriempcombo.getSelectedItem().equals(""))
  		  			{
  		  				CategorieMp categorieMp=catMap.get(categoriempcombo.getSelectedItem().toString());
  		  				listeMatierePremiereCombo.clear();
  		  			txtCodeMP.setText("");
  		  		comboMP.removeAllItems();
		  			comboMP.addItem("");
		  			
		  		listeMatierePremiereCombo=matierePremiereDAO.listeMatierePremierByidcategorie(categorieMp.getId());
  		  			for(int i=0;i<listeMatierePremiereCombo.size();i++)	
  		  			{
  		  				
  		  				MatierePremier matierePremier=listeMatierePremiereCombo.get(i);
  		  			comboMP.addItem(matierePremier.getNom());
  		  				MapMatierPremiere.put(matierePremier.getNom(), matierePremier);
  		  				MapCodeMatierPremiere.put(matierePremier.getCode(), matierePremier);
  		  				
  		  			}
  		  				
  		  				
  		  				
  		  			}else
  		  			{
  		  			listeMatierePremiereCombo.clear();
  		  				txtCodeMP.setText("");
  		  				comboMP.removeAllItems();
  		  			comboMP.addItem("");
  		  				
  		  			}
  		  			
  		  			
  		  			
  		  			
  		  			
  		  			
  		  			
  		  		}else
  		  		{
  		  		listeMatierePremiereCombo.clear();
	  				txtCodeMP.setText("");
	  				comboMP.removeAllItems();
	  				comboMP.addItem("");
  		  			
  		  		}
  		  		
  		  		
  		  		
  		  		
  		  		
  		  		
  		  	
		 		
		 		
		 		
		 	}
		 });
		categoriempcombo.setBounds(85, 89, 208, 24);
		layeredPane.add(categoriempcombo);
		AutoCompleteDecorator.decorate(categoriempcombo);
		JLabel lblFournisseur = new JLabel("Fournisseur :");
		lblFournisseur.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblFournisseur.setBounds(784, 87, 78, 24);
		layeredPane.add(lblFournisseur);
		
		 combofournisseur = new JComboBox();
		combofournisseur.setSelectedIndex(-1);
		combofournisseur.setBounds(872, 88, 232, 24);
		layeredPane.add(combofournisseur);
		
		JButton btnAfficherStock = new JButton("Afficher Stock");
		btnAfficherStock.setBounds(504, 197, 113, 23);
		add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(comboDepot.getSelectedItem().equals(""))	{
				JOptionPane.showMessageDialog(null, "Il faut choisir un magasin", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else {
				
				MatierePremier matierePremier=MapCodeMatierPremiere.get(txtCodeMP.getText());
				
					listStockMP.clear();
					listInventaireNonValider.clear();
					stockMPDAO.ViderSession();
					
					SubCategorieMp subCategorieMp=subcatMap.get(soucategoriempcombo.getSelectedItem());
  					CategorieMp categorieMp=catMap.get(categoriempcombo.getSelectedItem());
  				
  					Magasin magasin=mapMagasin.get(comboMagasin.getSelectedItem().toString());
  					Magasin magasinDechet=MapmagasinDechetMP.get(comboMagasinDechet.getSelectedItem().toString());
  					if(magasinDechet==null)
  					{
  						JOptionPane.showMessageDialog(null, "veuillez Selectionner le magasin dechet SPV!!!!!");
  						return;
  					}
  					
  					
  					if(magasin==null)
  					{
  						JOptionPane.showMessageDialog(null, "veuillez Selectionner le magasin SPV!!!!!");
  						return;
  					}
  					
  					MatierePremier mp=MapMatierPremiere.get(comboMP.getSelectedItem());
  					
  					FournisseurMP fournisseurMP=mapFournisseurMP.get(combofournisseur.getSelectedItem().toString());
  				
  					
  					PerteMP perteMPInvalider=PerteMPDAO.findByDateByMagasinByNumPerteParEtat("",dateoperation.getDate(), magasinDechet, ETAT_INVALIDER);
	 	 		
	 	 			
	 	 		 //  ActionPerteMP  ActionPerteMPInvalider=ActionPerteMPDAO.findByDateByMagasin(dateoperation.getDate(), magasinDechet, ETAT_INVALIDER);
	 	
  					if(perteMPInvalider!=null)
  					{
  						JOptionPane.showMessageDialog(null, "Veuillez Valider Les Actions des Quantites Petres SVP");
  						return;
  					}
  					
  					
  					listInventaireValider=inventaireDAO.findByDateByMagasin(magasin, dateoperation.getDate(), CODE_INVENTAIRE_1, ETAT_INVALIDER);
  					if(listInventaireValider.size()!=0)
  					{
  						JOptionPane.showMessageDialog(null, "Inventaire de ce jour déja Valider !!!!!");
  						return;
  						
  					}
  					
  					listInventaireValider=inventaireDAO.findByDateByMagasin(magasin, dateoperation.getDate(), CODE_INVENTAIRE_1, ETAT_VALIDER);
  					if(listInventaireValider.size()!=0)
  					{
  						JOptionPane.showMessageDialog(null, "Inventaire de ce jour déja Valider !!!!!");
  						return;
  						
  					}
  					
  					listInventaireValider=inventaireDAO.findByDateByMagasin(magasin, dateoperation.getDate(), CODE_INVENTAIRE_2, ETAT_INVALIDER);
  					if(listInventaireValider.size()!=0)
  					{
  						JOptionPane.showMessageDialog(null, "Inventaire de ce jour déja Valider !!!!!");
  						return;
  						
  					}
  					
  					listInventaireValider=inventaireDAO.findByDateByMagasin(magasin, dateoperation.getDate(), CODE_INVENTAIRE_2, ETAT_VALIDER);
  					if(listInventaireValider.size()!=0)
  					{
  						JOptionPane.showMessageDialog(null, "Inventaire de ce jour déja Valider !!!!!");
  						return;
  						
  					}
  					
  					
  					listInventaireNonValider=inventaireDAO.findByDateByMagasin(magasin, dateoperation.getDate(), CODE_INVENTAIRE_1, ETAT_INVENTAIRE_1_NNONVALIDER);
  					
  					InventaireNonValider();
  					
					 listStockMP=stockMPDAO.findSockNonVideByMagasinBySubCategorieByCategorieByMPByFournisseur(magasin,subCategorieMp , categorieMp,mp,fournisseurMP);
			
	
				
				
				
				intialiserTableau();
				afficher_tableMP(listStockMP);
				// ValiderPerte();
				 
				
			
				 
				 
				 
				 
				 btnValider.setEnabled(true);
			}
		  }
		});
		btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
			listFournisseur=fournisseurMPDAO.findAll();
			combofournisseur.addItem("");
			for(int i=0;i<listFournisseur.size();i++)
			{
				
				FournisseurMP fournisseurMP=listFournisseur.get(i);
				
				combofournisseur.addItem(fournisseurMP.getCodeFournisseur());
				mapFournisseurMP.put(fournisseurMP.getCodeFournisseur(), fournisseurMP);
				
			}
			
			 categoriempcombo.removeAllItems();
	  		  categoriempcombo.addItem("");
	  		soucategoriempcombo.removeAllItems();
	  		soucategoriempcombo.addItem("");
	  		
	  		JLabel label_4 = new JLabel("Date Op\u00E9ration :");
	  		label_4.setBounds(819, 29, 96, 26);
	  		layeredPane.add(label_4);
	  		
	  		 dateoperation = new JDateChooser();
	  		dateoperation.setLocale(Locale.FRANCE);
	  		dateoperation.setDateFormatString("dd/MM/yyyy");
	  		dateoperation.setBounds(925, 30, 174, 26);
	  		layeredPane.add(dateoperation);
	  		
	  		JLabel lblMagasinDechetMp = new JLabel("Magasin Dechet MP :");
	  		lblMagasinDechetMp.setFont(new Font("Tahoma", Font.PLAIN, 12));
	  		lblMagasinDechetMp.setBounds(470, 29, 125, 24);
	  		layeredPane.add(lblMagasinDechetMp);
	  		
	  		 comboMagasinDechet = new JComboBox();
	  		comboMagasinDechet.setBounds(592, 31, 221, 24);
	  		layeredPane.add(comboMagasinDechet);
	  		
	  		JButton btnIprimer = new JButton("Imprimer ");
	  		btnIprimer.addActionListener(new ActionListener() {
	  			public void actionPerformed(ActionEvent arg0) {
	  				

					if(listInventaireNonValider.size()!=0)
					{
						
						 
						Map parameters = new HashMap();
						parameters.put("depot", listInventaireNonValider.get(0).getMagasin().getDepot().getLibelle());
						
						parameters.put("magasin",listInventaireNonValider.get(0).getMagasin().getLibelle());
						parameters.put("inventaire",Constantes.CODE_INVENTAIRE_1);
						
						
						JasperUtils.imprimerInventaire(listInventaireNonValider, parameters);
						
						
					}
				
					
				
	  				
	  				
	  				
	  				
	  				
	  			}
	  		});
	  		btnIprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
	  		btnIprimer.setBounds(504, 667, 113, 23);
	  		add(btnIprimer);
			
			  int i=0;
	  		  while(i<listsubcategoriemp.size())
	  		  {
	  			  subcatMap.put(listsubcategoriemp.get(i).getNom(), listsubcategoriemp.get(i));
	  			  soucategoriempcombo.addItem(listsubcategoriemp.get(i).getNom());
	  			  i++;
	  		  }
	  		  
	  		  
	  		  
		     	comboDepot.addItem("");
	  		      if(!utilisateur.getCodeDepot().equals(CODE_DEPOT_SIEGE)	) {
	  		    	Depot depot=  depotDAO.findByCode(utilisateur.getCodeDepot());
	  	    		comboDepot.addItem(depot.getLibelle());
	  	    		mapDepot.put(depot.getLibelle(), depot);
	  	    		
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
	  		      			j++;
	  		      		}
	  	    	
	  	    }  
	  		  
	  		  
	  		  
				  		 
	}
	
void afficher_tableMP(List<StockMP> listStockMP)
	{
	
	
	

	List<DetailPerteMP> listDetailPerteMP=new ArrayList<DetailPerteMP>();
	Magasin magasinDechet=MapmagasinDechetMP.get(comboMagasinDechet.getSelectedItem());
	PerteMP perteMP=PerteMPDAO.findByDateByMagasinByNumPerteParEtat ("",dateoperation.getDate(), magasinDechet, ETAT_VALIDER);
	BigDecimal QuantitePerte=BigDecimal.ZERO;
	BigDecimal QuantiteInventaire=BigDecimal.ZERO;
	if(perteMP!=null)
	{
		
		listDetailPerteMP=detailPerteMPDAO.listeDetailPerteMPByPerteMP(perteMP);
		
	
		
	}
	
	
	
	
	
	
	
	

	
	
	
	
		  int i=0;
			while(i<listStockMP.size())
			{	
				QuantiteInventaire=BigDecimal.ZERO;
				StockMP StockMP=listStockMP.get(i);
				
				String fournisseur="";
				if(StockMP.getFournisseurMP()!=null)
				{
					fournisseur=StockMP.getFournisseurMP().getCodeFournisseur();
				}
				
				
				
				QuantitePerte=BigDecimal.ZERO;
				
				MatierePremier matierePremier=	StockMP.getMatierePremier();
			
					for(int j=0;j<listDetailPerteMP.size();j++)
					{
						
						
						DetailPerteMP detailPerteMP=listDetailPerteMP.get(j);
						
						if(detailPerteMP.getMatierePremier().getId()==matierePremier.getId())
						{
							
							if(fournisseur!="")
							{
								if(detailPerteMP.getFournisseurMP()!=null)
								{
									
									if(fournisseur.equals(detailPerteMP.getFournisseurMP().getCodeFournisseur()))
									{
										
										QuantitePerte=QuantitePerte.add(detailPerteMP.getQuantite());
										
									}
									
									
									
								}
								
								
								
							}else
							{
								
								
								if(detailPerteMP.getFournisseurMP()==null)
								{
									
									
										
										QuantitePerte=QuantitePerte.add(detailPerteMP.getQuantite());
										
									
									
									
									
								}
								
								
								
								
								
							}
							
							
							
							
						}
						
						
						
						
					}
				
					if(StockMP.getFournisseurMP()!=null)
					{
	Inventaire inventaire=MapInventaireNonValider.get(StockMP.getMatierePremier().getCode()+"_"+StockMP.getFournisseurMP().getCodeFournisseur());
	if(inventaire!=null)
	{
		QuantiteInventaire=inventaire.getQuantite();
	}
	
}else
{
	Inventaire inventaire=MapInventaireNonValider.get(StockMP.getMatierePremier().getCode());
	if(inventaire!=null)
	{
		QuantiteInventaire=inventaire.getQuantite();
	}
	
}
				
				
				
				
				
				
				
				
				
				
				
			//	mapPrixMP.put(stockMP.getMatierePremier().getCode(), stockMP.getPrixUnitaire());
				mapMP.put(StockMP.getMatierePremier().getCode(), StockMP.getMatierePremier());
				Object []ligne={StockMP.getMatierePremier().getCode(),StockMP.getMatierePremier().getNom(),fournisseur,QuantiteInventaire,QuantitePerte};

				modeleMP.addRow(ligne);
				i++;
			}
			
			
			
			
			
			
			
			
			
			
	}
	
void intialiserTableau(){
	
	
	  

	
	
	 modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code Matière Première","Matière Première","Fournisseur", "Quantité","Perte"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,true,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     	
		     		return columnEditables[column];
		     	}
		     	
		     	
		     	
		     };
		     
	
		     
		   table.setModel(modeleMP); 
		  
					 table.getColumnModel().getColumn(0).setPreferredWidth(160);
					   table.getColumnModel().getColumn(1).setPreferredWidth(260);
					   table.getColumnModel().getColumn(2).setPreferredWidth(160);
					   table.getColumnModel().getColumn(3).setPreferredWidth(160);
					   table.getColumnModel().getColumn(4).setPreferredWidth(160);		 
					   table.getTableHeader().setReorderingAllowed(false);
					   
					   
									   


					   
	
								
								
								
					   
}


public void ValiderPerte()
{
	List<DetailPerteMP> listDetailPerteMP=new ArrayList<DetailPerteMP>();
	Magasin magasinDechet=MapmagasinDechetMP.get(comboMagasinDechet.getSelectedItem());
	PerteMP perteMP=PerteMPDAO.findByDateByMagasinByNumPerteParEtat ("",dateoperation.getDate(), magasinDechet, ETAT_VALIDER);
		
	if(perteMP!=null)
	{
		
		listDetailPerteMP=detailPerteMPDAO.listeDetailPerteMPByPerteMP(perteMP);
		BigDecimal QuantitePerte=BigDecimal.ZERO;
		String fournisseur="";
		for(int d=0;d<table.getRowCount();d++)
		{
			QuantitePerte=BigDecimal.ZERO;
		MatierePremier matierePremier=	mapMP.get(table.getValueAt(d, 0));
		fournisseur=table.getValueAt(d, 2).toString();
			for(int j=0;j<listDetailPerteMP.size();j++)
			{
				
				
				DetailPerteMP detailPerteMP=listDetailPerteMP.get(j);
				
				if(detailPerteMP.getMatierePremier().getId()==matierePremier.getId())
				{
					
					if(fournisseur!="")
					{
						if(detailPerteMP.getFournisseurMP()!=null)
						{
							
							if(fournisseur.equals(detailPerteMP.getFournisseurMP().getCodeFournisseur()))
							{
								
								QuantitePerte=QuantitePerte.add(detailPerteMP.getQuantite());
								
							}
							
							
							
						}
						
						
						
					}else
					{
						
						
						if(detailPerteMP.getFournisseurMP()==null)
						{
							
							
								
								QuantitePerte=QuantitePerte.add(detailPerteMP.getQuantite());
								
							
							
							
							
						}
						
						
						
						
						
					}
					
					
					
					
				}
				
				
				
				
			}
			
			
			table.setValueAt(QuantitePerte, d, 4);
			
			
		}
		
		
		
		
		

		
		
		
	}
	
	
	
	
	
	
	
	
}




void intialiserModifier(){
	
	 modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code Matière Première","Matière Première","Fournisseur", "Quantité","Perte"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,true,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		   table.setModel(modeleMP); 
		   table.getColumnModel().getColumn(0).setPreferredWidth(160);
		   table.getColumnModel().getColumn(1).setPreferredWidth(260);
		   table.getColumnModel().getColumn(2).setPreferredWidth(160);
		  
		   table.getTableHeader().setReorderingAllowed(false);
}




public void CalculerStockFinale()
{
	

	if(comboDepot.getSelectedItem().equals(""))	{
		JOptionPane.showMessageDialog(null, "Il faut choisir un magasin", "Erreur", JOptionPane.ERROR_MESSAGE);
	} else {
		
		MatierePremier matierePremier=MapCodeMatierPremiere.get(txtCodeMP.getText());
		
		
			
			SubCategorieMp subCategorieMp=subcatMap.get(soucategoriempcombo.getSelectedItem());
				CategorieMp categorieMp=catMap.get(categoriempcombo.getSelectedItem());
				MatierePremier mp=MapMatierPremiere.get(comboMP.getSelectedItem());
				Magasin magasin=mapMagasin.get(comboMagasin.getSelectedItem().toString());
				
			  	FournisseurMP fournisseurMP=mapFournisseurMP.get(combofournisseur.getSelectedItem().toString());
			
			  	if(magasin==null)
			  	{
			  		JOptionPane.showMessageDialog(null, "veuillez Selectionner le magasin SVP");
			  		return;
			  	}
			  	
			  	
			  	
				if(dateoperation.getDate()==null)
			  	{
			  		JOptionPane.showMessageDialog(null, "veuillez entrer la date de situation SVP");
			  		return;
			  	}
				
				
				
				Date mindate=null;
				
				Mindate=detailTransferStockMPDAO.MinDate(magasin);
				
				for(int i=0;i<Mindate.size();i++)
				{
					
					 Object[] object= Mindate.get(i);
					
					
					if(Mindate.get(i)!=null)
					{
						mindate=(Date)object[0];
					}
					
				}
				
				String patternYear = "yyyy";
				String patternDate = "yyyy-MM-dd";
				SimpleDateFormat simpleDateFormatyear = new SimpleDateFormat(patternYear);
				SimpleDateFormat simpleDateFormatDate = new SimpleDateFormat(patternDate);

				
				
				
				if(mindate!=null)
				{
					
					String date = simpleDateFormatDate.format(mindate);
					
					
					try {
					mindate=simpleDateFormatDate.parse(date);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
				}else
				{
					String year = simpleDateFormatyear.format(dateoperation.getDate());
					
					try {
					mindate=simpleDateFormatDate.parse(year+"-01-01");
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
				}
				
				
				
				SubCategorieMp subCategorieMpthe=subcategoriempdao.findByCode(SOUS_CATEGORIE_MATIERE_PREMIERE_THE);
			// listStockMP=stockMPDAO.findSockNonVideByMagasinBySubCategorieByCategorieByMPByFournisseur(magasin,subCategorieMp , categorieMp,mp,fournisseurMP);
				
				
			  	 
/////////////////////////////////////////////////////////////////////////////////// Les MP Non the //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
				
				//listeObjectStockFinaleGroupByMP=detailTransferStockMPDAO.listeStockFinaleMPByMagasinBySubCategorieByCategorieBayMPNonThe(dateSituation.getDate(), magasin, subCategorieMpthe, null, null);
			  	  				  	
				
					listeObjectStockInitialGroupByMP=detailTransferStockMPDAO.listeStockInitialMPByMagasinBySubCategorieByCategorieBayMPNonThe(mindate, magasin, subCategorieMpthe, null, null) ;

				
					
					
				
				listeObjectEtatStockGroupByMP=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPNonThe(mindate,dateoperation.getDate(), magasin, subCategorieMpthe, null, null);
				listeEtatStockTransfertEnAttenteNonThe=detailTransferStockMPDAO.listeEtatStockMPTransfertEnAttenteNonThe(mindate,dateoperation.getDate(), magasin, subCategorieMpthe, null, null);
			
				
//////////////////////////////////////////////////////////////////////////////////////////////////// Les MP the //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
			//	listeObjectStockFinaleGroupByMPByFournisseur=detailTransferStockMPDAO.listeStockFinaleMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseur(dateSituation.getDate(), magasin, subCategorieMpthe, null, null);

				
				
					listeObjectStockInitialGroupByMPByFournisseur=detailTransferStockMPDAO.listeStockInitialMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseur(mindate, magasin, subCategorieMpthe, null, null) ;
					
				

				//listeObjectEtatStockGroupByMPByFournisseur=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseur(mindate,dateSituation.getDate(), magasin, subCategorieMpthe, null, null);
				listeEtatStockTransfertEnAttenteThe=detailTransferStockMPDAO.listeEtatStockMPTransfertEnAttenteThe(mindate,dateoperation.getDate(), magasin, subCategorieMpthe, null, null);
				
				listeObjectEtatStockGroupByMPByFournisseurReception=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurReception(mindate,dateoperation.getDate(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurEntrer=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurEntrer(mindate,dateoperation.getDate(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurSortie=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurSortie(mindate,dateoperation.getDate(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurCharger=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurCharger(mindate,dateoperation.getDate(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurRetour=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurRetour(mindate,dateoperation.getDate(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurAutreSortie=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortie(mindate,dateoperation.getDate(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurResterMachine=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurResterMachine(mindate,dateoperation.getDate(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurFabrique=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurFabriquer(mindate,dateoperation.getDate(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurExistante=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurExistante(mindate,dateoperation.getDate(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortiePF=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortiePF(mindate,dateoperation.getDate(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortieEnAttent=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortieEnAttente(mindate,dateoperation.getDate(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurAutreSortiePerte=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortiePerte(mindate,dateoperation.getDate(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetour=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieRetour(mindate,dateoperation.getDate(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetourFournisseurAnnule=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieRETOURFOURNISSEURANNULER(mindate,dateoperation.getDate(), magasin, subCategorieMpthe, null, null);

				
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
			
		listEtatStockMP.clear();
		listEtatStockMPAfficher.clear();
		CalculerStockMPThe();	
		CalculerStockMPNonThe();		
			
			
		for(int j=0;j<listEtatStockMP.size();j++)
		{
			
		EtatStockMP etatStockMP=listEtatStockMP.get(j);	
			
		if( subCategorieMp!=null && categorieMp==null && mp==null && fournisseurMP==null)
		{
			
			if(etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId())
			{
				
			listEtatStockMPAfficher.add(etatStockMP);
			}
			
			
			
			
		}else if(subCategorieMp!=null && categorieMp!=null && mp==null && fournisseurMP==null)
		{
			if(etatStockMP.getMp().getCategorieMp().getId()==categorieMp.getId() && etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId())
			{
				
				listEtatStockMPAfficher.add(etatStockMP);	
				
			}
			
			
		}else if(categorieMp!=null && subCategorieMp!=null && mp!=null && fournisseurMP==null)
		{
			
			if(etatStockMP.getMp().getCategorieMp().getId()==categorieMp.getId() && etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId() && etatStockMP.getMp().getId()==mp.getId())
			{
				
				listEtatStockMPAfficher.add(etatStockMP);	
				
			}
			
		}else if(categorieMp!=null && subCategorieMp!=null && mp!=null && fournisseurMP!=null)
		{
			
			if(etatStockMP.getMp().getCategorieMp().getId()==categorieMp.getId() && etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId() && etatStockMP.getMp().getId()==mp.getId() && etatStockMP.getFournisseurMP().getId()==fournisseurMP.getId() )
			{
				
				listEtatStockMPAfficher.add(etatStockMP);	
				
			}
			
		}else if(subCategorieMp !=null && categorieMp==null && mp==null && fournisseurMP!=null)
		{
			
			if(etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId() && etatStockMP.getFournisseurMP().getId()==fournisseurMP.getId() )
			{
				
				listEtatStockMPAfficher.add(etatStockMP);	
				
			}
			
		}else if(categorieMp!=null && subCategorieMp!=null && mp==null && fournisseurMP!=null)
		{
			
			if(etatStockMP.getMp().getCategorieMp().getId()==categorieMp.getId() && etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId() && etatStockMP.getFournisseurMP().getId()==fournisseurMP.getId() )
			{
				
				listEtatStockMPAfficher.add(etatStockMP);	
				
			}
			
		}else
		{
			
			
			listEtatStockMPAfficher.add(etatStockMP);	
			
			
			
		}
			
			
		
		
		
		
		
		
		
			
			
			
			
			
		}
		

		
	
		
		
	}
  
	

	
	
}



public void CalculerStockMPNonThe()
{
	
	
	
	
		
		for(int i=0;i<listeObjectStockInitialGroupByMP.size() ; i++)
		{
			
			 Object[] object=listeObjectStockInitialGroupByMP.get(i);
			EtatStockMP etatStockMP=new EtatStockMP();
			MatierePremier mp= (MatierePremier)object[0];
			etatStockMP.setMp(mp);
									
			etatStockMP.setQuantiteInitial((BigDecimal)object[1]);
			
		
			 etatStockMP.setQuantiteReception(BigDecimal.ZERO);
			  etatStockMP.setTransferEntrer(BigDecimal.ZERO);
			  etatStockMP.setTransferSortie(BigDecimal.ZERO);
			  etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
			  etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
			  etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
			  etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
			  etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
			  etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
			  etatStockMP.setQuantiteExistante(BigDecimal.ZERO);
			etatStockMP.setQuantiteFinale(BigDecimal.ZERO);
			
			//etatStockMP.setQuantiteFinale((BigDecimal)object[10]);
			listEtatStockMP.add(etatStockMP);
			
			
		}
	
	
	boolean existe=false;
	
	  for(int j=0;j<listeObjectEtatStockGroupByMP.size() ; j++) {
	  
		  existe=false;
		  
	  Object[] object=listeObjectEtatStockGroupByMP.get(j);
	  MatierePremier mp=(MatierePremier)object[0];
		  
	  for(int k=0;k<listEtatStockMP.size();k++) {
	  
	  if(listEtatStockMP.get(k).getMp().getId()==mp.getId()) {
		  if(listEtatStockMP.get(k).getFournisseurMP()==null)
		  {
			 
			  
			  
			  existe=true;
			  
			  EtatStockMP etatStockMP=listEtatStockMP.get(k);
			  if(etatStockMP.getMp().getCode().equals("MP_703"))
			  {
				  
				System.out.println(etatStockMP.getMp().getCode());  
				  
			  }
			 
			  if((BigDecimal)object[1] != null)
			  {
				  etatStockMP.setQuantiteReception((BigDecimal)object[1]);
			  }else
			  {
				  etatStockMP.setQuantiteReception(BigDecimal.ZERO);
			  }
			  if(((BigDecimal)object[2]).add((BigDecimal)object[3]) != null)
			  {
				  etatStockMP.setTransferEntrer(((BigDecimal)object[2]).add((BigDecimal)object[3]));
			  }else
			  {
				  etatStockMP.setTransferEntrer(BigDecimal.ZERO);
			  }
			 
			  if(((BigDecimal)object[6]).add((BigDecimal)object[7])!=null)
			  {
				  etatStockMP.setTransferSortie(((BigDecimal)object[6]).add((BigDecimal)object[7]));
			  }else
			  {
				  etatStockMP.setTransferSortie(BigDecimal.ZERO);
			  }
			 if((BigDecimal)object[4]!=null)
			 {
				 etatStockMP.setQuantiteCharger((BigDecimal)object[4]);
			 }else
			 {
				 etatStockMP.setQuantiteCharger(BigDecimal.ZERO); 
			 }
			  
			 if((BigDecimal)object[5]!=null)
			 {
				 etatStockMP.setQuantiteChargerSupp((BigDecimal)object[5]); 
			 }else
			 {
				 etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
			 }
			 
			 if((BigDecimal)object[8]!=null)
			 {
				 etatStockMP.setQuantiteRetour((BigDecimal)object[8]);
			 }else
			 {
				 etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
			 }
			 
			 if(((BigDecimal)object[9]).add((BigDecimal)object[13]).add((BigDecimal)object[14]).add((BigDecimal)object[15]).add((BigDecimal)object[16]).add((BigDecimal)object[17])!=null)
			 {
				  etatStockMP.setQuantiteAutreSortie(((BigDecimal)object[9]).add((BigDecimal)object[13]).add((BigDecimal)object[14]).add((BigDecimal)object[15]).add((BigDecimal)object[16]).add((BigDecimal)object[17]));

			 }else
			 {
				  etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);

			 }
			 if(((BigDecimal)object[10])!=null)
			 {
				  etatStockMP.setQuantiteResterMachine(((BigDecimal)object[10]));
			 }else
			 {
				  etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
			 }
			
			 if((BigDecimal)object[11]!=null)
			 {
				  etatStockMP.setQuantiteFabriquer((BigDecimal)object[11]);
			 }else
			 {
				  etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
			 }
			if((BigDecimal)object[12]!=null)
			{
				 etatStockMP.setQuantiteExistante((BigDecimal)object[12]);
			}else
			{
				 etatStockMP.setQuantiteExistante(BigDecimal.ZERO);
			}
			 
			  
			  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie()).add(etatStockMP.getQuantiteExistante()))));
			 
			  listEtatStockMP.set(k, etatStockMP);
		  }

	  
	  }
	  

	  
	  
	  
	  
	  
	  }
	  
		if(existe==false)
		{
			
			
			
			  EtatStockMP etatStockMP=new EtatStockMP();
			 
			  etatStockMP.setMp(mp);
			  etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
			  
			  if((BigDecimal)object[1] != null)
			  {
				  etatStockMP.setQuantiteReception((BigDecimal)object[1]);
			  }else
			  {
				  etatStockMP.setQuantiteReception(BigDecimal.ZERO);
			  }
			  if(((BigDecimal)object[2]).add((BigDecimal)object[3]) != null)
			  {
				  etatStockMP.setTransferEntrer(((BigDecimal)object[2]).add((BigDecimal)object[3]));
			  }else
			  {
				  etatStockMP.setTransferEntrer(BigDecimal.ZERO);
			  }
			 
			  if(((BigDecimal)object[6]).add((BigDecimal)object[7])!=null)
			  {
				  etatStockMP.setTransferSortie(((BigDecimal)object[6]).add((BigDecimal)object[7]));
			  }else
			  {
				  etatStockMP.setTransferSortie(BigDecimal.ZERO);
			  }
			 if((BigDecimal)object[4]!=null)
			 {
				 etatStockMP.setQuantiteCharger((BigDecimal)object[4]);
			 }else
			 {
				 etatStockMP.setQuantiteCharger(BigDecimal.ZERO); 
			 }
			  
			 if((BigDecimal)object[5]!=null)
			 {
				 etatStockMP.setQuantiteChargerSupp((BigDecimal)object[5]); 
			 }else
			 {
				 etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
			 }
			 
			 if((BigDecimal)object[8]!=null)
			 {
				 etatStockMP.setQuantiteRetour((BigDecimal)object[8]);
			 }else
			 {
				 etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
			 }
			 
			 if(((BigDecimal)object[9]).add((BigDecimal)object[13]).add((BigDecimal)object[14]).add((BigDecimal)object[15]).add((BigDecimal)object[16]).add((BigDecimal)object[17])!=null)
			 {
				  etatStockMP.setQuantiteAutreSortie(((BigDecimal)object[9]).add((BigDecimal)object[13]).add((BigDecimal)object[14]).add((BigDecimal)object[15]).add((BigDecimal)object[16]).add((BigDecimal)object[17]));

			 }else
			 {
				  etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);

			 }
			 if(((BigDecimal)object[10])!=null)
			 {
				  etatStockMP.setQuantiteResterMachine(((BigDecimal)object[10]));
			 }else
			 {
				  etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
			 }
			
			 if((BigDecimal)object[11]!=null)
			 {
				  etatStockMP.setQuantiteFabriquer((BigDecimal)object[11]);
			 }else
			 {
				  etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
			 }
			if((BigDecimal)object[12]!=null)
			{
				 etatStockMP.setQuantiteExistante((BigDecimal)object[12]);
			}else
			{
				 etatStockMP.setQuantiteExistante(BigDecimal.ZERO);
			}
			  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie()).add(etatStockMP.getQuantiteExistante()))));
			 
			  listEtatStockMP.add(etatStockMP);	
			
			
			
		}
	  
	  
	  
	  
	  }
	 
	/////////////////////////////////////////////////////////////////////////////////////////////////////// Sortie En Attente ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
	  
		boolean trouver=false;
		
		  for(int j=0;j<listeEtatStockTransfertEnAttenteNonThe.size() ; j++) {
		  
			  trouver=false;
			  
		 DetailTransferStockMP  detailTransferStockMP=listeEtatStockTransfertEnAttenteNonThe.get(j);
		
			  
		  for(int k=0;k<listEtatStockMP.size();k++) {
		  
		  if(listEtatStockMP.get(k).getMp().getId()==detailTransferStockMP.getMatierePremier().getId()) {
			  if(listEtatStockMP.get(k).getFournisseurMP()==null)
			  {
				 if(detailTransferStockMP.getFournisseur()==null) 
				 {
					 
						if(detailTransferStockMP.getMagasinDestination().getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION) )  
							
							
						{
						if(detailTransferStockMP.getMagasinSouce()!=null)	
						{
							
							if(detailTransferStockMP.getMagasinSouce().getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION) )  
								
							{
								
								
								  trouver=true;
								  
								  EtatStockMP etatStockMP=listEtatStockMP.get(k);
								  
								
								  etatStockMP.setQuantiteResterMachine(etatStockMP.getQuantiteResterMachine().add(detailTransferStockMP.getQuantite()));
								 		  listEtatStockMP.set(k, etatStockMP);
								
								
								
								
							}
							
							
							
						}
							
							
							
							
							
						}
					 
					 
					 
				 }
				 
			  }

		  
		  }
		  

		  
		  
		  
		  
		  
		  }
		  
			if(trouver==false)
			{
				
				 if(detailTransferStockMP.getFournisseur()==null) 
				 {
					 
						if(detailTransferStockMP.getMagasinDestination().getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION) )  
							
							
						{
						if(detailTransferStockMP.getMagasinSouce()!=null)	
						{
							
							if(detailTransferStockMP.getMagasinSouce().getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION) )  
								
							{
								
								
								
								  EtatStockMP etatStockMP=new EtatStockMP();
									 
								  etatStockMP.setMp(detailTransferStockMP.getMatierePremier());
								  etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
								  etatStockMP.setQuantiteReception(BigDecimal.ZERO);
								  etatStockMP.setTransferEntrer(BigDecimal.ZERO);
								  etatStockMP.setTransferSortie(BigDecimal.ZERO);
								  etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
								  etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
								  etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
								  etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
								  etatStockMP.setQuantiteResterMachine(detailTransferStockMP.getQuantite());
								  etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
								  etatStockMP.setQuantiteExistante(BigDecimal.ZERO);
								  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie()).add(etatStockMP.getQuantiteExistante()))));
								 
								  listEtatStockMP.add(etatStockMP);	
								
								
								
								
								
								
								
							}
						}
						}
				 }
				
				
				
				
				
			}
		  
		  
		  
		  
		  }
	  
	  
	  
	  
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	  
	  
	  
	  
	  
	  for(int k=0;k<listEtatStockMP.size();k++) {
		  
		  EtatStockMP etatStockMP=listEtatStockMP.get(k);
		
		  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie()).add(etatStockMP.getQuantiteExistante()))));
		 
		  listEtatStockMP.set(k, etatStockMP);
		  
		  
		  }

	
	
	
}


public void CalculerStockMPThe()
{
	

	
	

	
	
		
		for(int i=0;i<listeObjectStockInitialGroupByMPByFournisseur.size() ; i++)
		{
			
			 Object[] object=listeObjectStockInitialGroupByMPByFournisseur.get(i);
			EtatStockMP etatStockMP=new EtatStockMP();
			MatierePremier mp= (MatierePremier)object[0];
			
			FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];
			
			etatStockMP.setMp(mp);
									
			etatStockMP.setQuantiteInitial((BigDecimal)object[2]);
			
			etatStockMP.setFournisseurMP(fournisseurMP);
			 etatStockMP.setQuantiteReception(BigDecimal.ZERO);
			  etatStockMP.setTransferEntrer(BigDecimal.ZERO);
			  etatStockMP.setTransferSortie(BigDecimal.ZERO);
			  etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
			  etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
			  etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
			  etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
			  etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
			etatStockMP.setQuantiteFinale(BigDecimal.ZERO);
			etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
			etatStockMP.setQuantiteExistante(BigDecimal.ZERO);
			//etatStockMP.setQuantiteFinale((BigDecimal)object[10]);
			listEtatStockMP.add(etatStockMP);
			
			
		}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Reception ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	boolean existe=false;
	
	  for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurReception.size() ; j++) {
	  
		  existe=false;
		  
	  Object[] object=listeObjectEtatStockGroupByMPByFournisseurReception.get(j);
	  MatierePremier mp=(MatierePremier)object[0];
	  FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];
		  
	  for(int k=0;k<listEtatStockMP.size();k++) {
	  
	  if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
		  existe=true;
	  
	  EtatStockMP etatStockMP=listEtatStockMP.get(k);
	  
	 
	  if((BigDecimal)object[2]!=null)
	  {
		  etatStockMP.setQuantiteReception(etatStockMP.getQuantiteReception().add((BigDecimal)object[2]) ); 
	  }
	 
	
	  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
	  listEtatStockMP.set(k, etatStockMP);
	  
	  
	  
	  }
	  

	  
	  
	  
	  
	  
	  }
	  
		if(existe==false)
		{
			
			
			
			  EtatStockMP etatStockMP=new EtatStockMP();
			 
			  etatStockMP.setMp(mp);
			  etatStockMP.setFournisseurMP(fournisseurMP);
			  etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
			  if((BigDecimal)object[2]!=null)
			  {
				  etatStockMP.setQuantiteReception((BigDecimal)object[2]);
			  }else
			  {
				  etatStockMP.setQuantiteReception(BigDecimal.ZERO);
			  }
			 
			  etatStockMP.setTransferEntrer(BigDecimal.ZERO);
			  etatStockMP.setTransferSortie(BigDecimal.ZERO);
			  etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
			  etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
			  etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
			  etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
			  etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
			  etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
			  etatStockMP.setQuantiteExistante(BigDecimal.ZERO);
			  
			  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));
			 
			  listEtatStockMP.add(etatStockMP);	
			
			
		}
	  
	  
	  
	  
	  }

//////////////////////////////////////////////////////////////////////////////////////////////////////// Entrer ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		 existe=false;
		 
		 BigDecimal enter=BigDecimal.ZERO;
		 BigDecimal transfert=BigDecimal.ZERO;
		
		  for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurEntrer.size() ; j++) {
		  
			  existe=false;
			  
		  Object[] object=listeObjectEtatStockGroupByMPByFournisseurEntrer.get(j);
		  MatierePremier mp=(MatierePremier)object[0];
		  FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];
		  
		  enter=BigDecimal.ZERO;
		  transfert=BigDecimal.ZERO;
			  
		  for(int k=0;k<listEtatStockMP.size();k++) {
			  enter=BigDecimal.ZERO;
			  transfert=BigDecimal.ZERO;
		  if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
			  existe=true;
		  
		  EtatStockMP etatStockMP=listEtatStockMP.get(k);
		  
		 
		  if(((BigDecimal)object[2])!=null)
		  {
			  enter=(BigDecimal)object[2];
			 
		  }
		  if(((BigDecimal)object[3])!=null)
		  {
			  transfert=(BigDecimal)object[3];
			 
		  }
		  
		  
		  etatStockMP.setTransferEntrer (etatStockMP.getTransferEntrer().add(enter.add(transfert)));
		 
		
		  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
		  listEtatStockMP.set(k, etatStockMP);
		  
		  
		  
		  }
		  

		  
		  
		  
		  
		  
		  }
		  
			if(existe==false)
			{
				
				
				
				  EtatStockMP etatStockMP=new EtatStockMP();
				 
				  etatStockMP.setMp(mp);
				  etatStockMP.setFournisseurMP(fournisseurMP);
				  etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
				  etatStockMP.setQuantiteReception(BigDecimal.ZERO);
				  if(((BigDecimal)object[2])!=null)
				  {
					  enter=(BigDecimal)object[2];
					 
				  }
				  if(((BigDecimal)object[3])!=null)
				  {
					  transfert=(BigDecimal)object[3];
					 
				  }  
				  
				  
				 
				etatStockMP.setTransferEntrer (enter.add(transfert));
				 
				  etatStockMP.setTransferSortie(BigDecimal.ZERO);
				  etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
				  etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
				  etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
				  etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
				  etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
				  etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
				  etatStockMP.setQuantiteExistante(BigDecimal.ZERO);
				  
				  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));
				 
				  listEtatStockMP.add(etatStockMP);	
				
				
			}
		  
		  
		  
		  
		  }
		   
	  
//////////////////////////////////////////////////////////////////////////////////////////////////////// Sortie  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;

BigDecimal sortie=BigDecimal.ZERO;
 transfert=BigDecimal.ZERO;

for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurSortie.size() ; j++) {

existe=false;
sortie=BigDecimal.ZERO;
transfert=BigDecimal.ZERO;
Object[] object=listeObjectEtatStockGroupByMPByFournisseurSortie.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {
	sortie=BigDecimal.ZERO;
	transfert=BigDecimal.ZERO;

if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;

EtatStockMP etatStockMP=listEtatStockMP.get(k);


if(((BigDecimal)object[2])!=null)
{
	sortie=(BigDecimal)object[2];
	 
}
if(((BigDecimal)object[3])!=null)
{
	  transfert=(BigDecimal)object[3];
	 
}  
etatStockMP.setTransferSortie (etatStockMP.getTransferSortie(). add(sortie.add(transfert)));

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
if(((BigDecimal)object[2])!=null)
{
	sortie=(BigDecimal)object[2];
	 
}
if(((BigDecimal)object[3])!=null)
{
	  transfert=(BigDecimal)object[3];
	 
} 


	etatStockMP.setTransferSortie(sortie.add(transfert));


etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}


//////////////////////////////////////////////////////////////////////////////////////////////////////// Charge et Charge Supp  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;
BigDecimal charge=BigDecimal.ZERO;
BigDecimal chargesupp=BigDecimal.ZERO;
for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurCharger.size() ; j++) {
	 charge=BigDecimal.ZERO;
	 chargesupp=BigDecimal.ZERO;
existe=false;

Object[] object=listeObjectEtatStockGroupByMPByFournisseurCharger.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {
	charge=BigDecimal.ZERO;
	 chargesupp=BigDecimal.ZERO;

if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;

EtatStockMP etatStockMP=listEtatStockMP.get(k);

if((BigDecimal)object[2]!=null)
{
	charge=(BigDecimal)object[2];
	
}
etatStockMP.setQuantiteCharger(etatStockMP.getQuantiteCharger().add(charge));
if((BigDecimal)object[3]!=null)
{
	chargesupp=(BigDecimal)object[3];
}

etatStockMP.setQuantiteChargerSupp(etatStockMP.getQuantiteChargerSupp().add(chargesupp));
etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
etatStockMP.setTransferSortie(BigDecimal.ZERO);

if((BigDecimal)object[2]!=null)
{
	charge=(BigDecimal)object[2];
	
}

if((BigDecimal)object[3]!=null)
{
chargesupp=(BigDecimal)object[3];
}

etatStockMP.setQuantiteCharger(charge);

etatStockMP.setQuantiteChargerSupp(chargesupp);
etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}
	  
//////////////////////////////////////////////////////////////////////////////////////////////////////// Retour  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;
BigDecimal retour=BigDecimal.ZERO;
BigDecimal retourFournisseurAnnule=BigDecimal.ZERO;
for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurRetour.size() ; j++) {
	retour=BigDecimal.ZERO;
existe=false;

Object[] object=listeObjectEtatStockGroupByMPByFournisseurRetour.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {
	retour=BigDecimal.ZERO;
if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;

EtatStockMP etatStockMP=listEtatStockMP.get(k);


if((BigDecimal)object[2]!=null)
{
	retour=(BigDecimal)object[2];
	
}
etatStockMP.setQuantiteRetour (etatStockMP.getQuantiteRetour().add(retour));


etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
etatStockMP.setTransferSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
if((BigDecimal)object[2]!=null)
{
	retour=(BigDecimal)object[2];
	
}

	etatStockMP.setQuantiteRetour(retour);


etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}
	  
//////////////////////////////////////////////////////////////////////////////////////////////////////// Autres Sorties   Sortie ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;
 sortie=BigDecimal.ZERO;


for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurAutreSortie.size() ; j++) {
	 sortie=BigDecimal.ZERO;
	

existe=false;

Object[] object=listeObjectEtatStockGroupByMPByFournisseurAutreSortie.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {
	 sortie=BigDecimal.ZERO;
	

if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;


EtatStockMP etatStockMP=listEtatStockMP.get(k);



if(((BigDecimal)object[2])!=null)
{
sortie=((BigDecimal)object[2]);
}


etatStockMP.setQuantiteAutreSortie(etatStockMP.getQuantiteAutreSortie().add(sortie));


etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
etatStockMP.setTransferSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
if(etatStockMP.getMp().getCode().equals("MP_1126") && etatStockMP.getFournisseurMP().getId()==10 )
{
	
	System.out.println(etatStockMP.getMp().getCode());
}
if(((BigDecimal)object[2])!=null)
{
sortie=((BigDecimal)object[2]);
}

etatStockMP.setQuantiteAutreSortie((sortie));

etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}


////////////////////////////////////////////////////////////////////////////////////////////////////////Autres Sorties   SortiePF ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;

BigDecimal sortiePF=BigDecimal.ZERO;


for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortiePF.size() ; j++) {

sortiePF=BigDecimal.ZERO;


existe=false;

Object[] object=listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortiePF.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {

sortiePF=BigDecimal.ZERO;


if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;


EtatStockMP etatStockMP=listEtatStockMP.get(k);



if(((BigDecimal)object[2])!=null)
{
	sortiePF=((BigDecimal)object[2]);
}


etatStockMP.setQuantiteAutreSortie(etatStockMP.getQuantiteAutreSortie().add(sortiePF));


etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
etatStockMP.setTransferSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
if(etatStockMP.getMp().getCode().equals("MP_1126") && etatStockMP.getFournisseurMP().getId()==10 )
{

System.out.println(etatStockMP.getMp().getCode());
}
if(((BigDecimal)object[2])!=null)
{
	sortiePF=((BigDecimal)object[2]);
}

etatStockMP.setQuantiteAutreSortie((sortiePF));

etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}

////////////////////////////////////////////////////////////////////////////////////////////////////////Autres Sorties   En Attente ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;
BigDecimal sortieEnAttente=BigDecimal.ZERO;


for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortieEnAttent.size() ; j++) {
	sortieEnAttente=BigDecimal.ZERO;


existe=false;

Object[] object=listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortieEnAttent.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {
	sortieEnAttente=BigDecimal.ZERO;


if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;


EtatStockMP etatStockMP=listEtatStockMP.get(k);



if(((BigDecimal)object[2])!=null)
{
	sortieEnAttente=((BigDecimal)object[2]);
}


etatStockMP.setQuantiteAutreSortie(etatStockMP.getQuantiteAutreSortie().add(sortieEnAttente));


etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
etatStockMP.setTransferSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
if(etatStockMP.getMp().getCode().equals("MP_1126") && etatStockMP.getFournisseurMP().getId()==10 )
{

System.out.println(etatStockMP.getMp().getCode());
}
if(((BigDecimal)object[2])!=null)
{
	sortieEnAttente=((BigDecimal)object[2]);
}

etatStockMP.setQuantiteAutreSortie((sortieEnAttente));

etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}

////////////////////////////////////////////////////////////////////////////////////////////////////////Autres Sorties   Perte////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;
BigDecimal perte=BigDecimal.ZERO;


for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurAutreSortiePerte.size() ; j++) {
	perte=BigDecimal.ZERO;


existe=false;

Object[] object=listeObjectEtatStockGroupByMPByFournisseurAutreSortiePerte.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {
	perte=BigDecimal.ZERO;


if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;


EtatStockMP etatStockMP=listEtatStockMP.get(k);



if(((BigDecimal)object[2])!=null)
{
	perte=((BigDecimal)object[2]);
}


etatStockMP.setQuantiteAutreSortie(etatStockMP.getQuantiteAutreSortie().add(perte));


etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
etatStockMP.setTransferSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
if(etatStockMP.getMp().getCode().equals("MP_1126") && etatStockMP.getFournisseurMP().getId()==10 )
{

System.out.println(etatStockMP.getMp().getCode());
}
if(((BigDecimal)object[2])!=null)
{
	perte=((BigDecimal)object[2]);
}

etatStockMP.setQuantiteAutreSortie((perte));

etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}

////////////////////////////////////////////////////////////////////////////////////////////////////////Autres Sorties   Retour////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;
 retour=BigDecimal.ZERO;


for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetour.size() ; j++) {
	retour=BigDecimal.ZERO;


existe=false;

Object[] object=listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetour.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {
	retour=BigDecimal.ZERO;


if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;


EtatStockMP etatStockMP=listEtatStockMP.get(k);



if(((BigDecimal)object[2])!=null)
{
	retour=((BigDecimal)object[2]);
}


etatStockMP.setQuantiteAutreSortie(etatStockMP.getQuantiteAutreSortie().add(retour));


etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
etatStockMP.setTransferSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
if(etatStockMP.getMp().getCode().equals("MP_1126") && etatStockMP.getFournisseurMP().getId()==10 )
{

System.out.println(etatStockMP.getMp().getCode());
}
if(((BigDecimal)object[2])!=null)
{
	retour=((BigDecimal)object[2]);
}

etatStockMP.setQuantiteAutreSortie((retour));

etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}



////////////////////////////////////////////////////////////////////////////////////////////////////////Autres Sorties   Retour Fournisseur Annule////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;
retourFournisseurAnnule=BigDecimal.ZERO;


for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetourFournisseurAnnule.size() ; j++) {
retourFournisseurAnnule=BigDecimal.ZERO;


existe=false;

Object[] object=listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetourFournisseurAnnule.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {
retourFournisseurAnnule=BigDecimal.ZERO;


if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;


EtatStockMP etatStockMP=listEtatStockMP.get(k);



if(((BigDecimal)object[2])!=null)
{
retourFournisseurAnnule=((BigDecimal)object[2]);
}


etatStockMP.setQuantiteAutreSortie(etatStockMP.getQuantiteAutreSortie().add(retourFournisseurAnnule));


etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
etatStockMP.setTransferSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
if(etatStockMP.getMp().getCode().equals("MP_1126") && etatStockMP.getFournisseurMP().getId()==10 )
{

System.out.println(etatStockMP.getMp().getCode());
}
if(((BigDecimal)object[2])!=null)
{
retourFournisseurAnnule=((BigDecimal)object[2]);
}

etatStockMP.setQuantiteAutreSortie((retourFournisseurAnnule));

etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}






//////////////////////////////////////////////////////////////////////////////////////////////////////// Rester Machine ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;
BigDecimal resteMachine=BigDecimal.ZERO;
for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurResterMachine.size() ; j++) {
	resteMachine=BigDecimal.ZERO;
existe=false;

Object[] object=listeObjectEtatStockGroupByMPByFournisseurResterMachine.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {
	resteMachine=BigDecimal.ZERO;
if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;

EtatStockMP etatStockMP=listEtatStockMP.get(k);

if(((BigDecimal)object[2])!=null)
{
	resteMachine=((BigDecimal)object[2]);
	
}
etatStockMP.setQuantiteResterMachine(etatStockMP.getQuantiteResterMachine().add(resteMachine));

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
etatStockMP.setTransferSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
if(((BigDecimal)object[2])!=null)
{
	resteMachine=((BigDecimal)object[2]);
	
}
	etatStockMP.setQuantiteResterMachine(resteMachine);


etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}	  

////////////////////////////////////////////////////////////////////////////////////////////////////////   Fabriquer  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;
BigDecimal fabriquer=BigDecimal.ZERO;
for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurFabrique.size() ; j++) {
	fabriquer=BigDecimal.ZERO;
existe=false;

Object[] object=listeObjectEtatStockGroupByMPByFournisseurFabrique.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {
	fabriquer=BigDecimal.ZERO;
if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;

EtatStockMP etatStockMP=listEtatStockMP.get(k);

if(((BigDecimal)object[2])!=null)
{
	fabriquer=((BigDecimal)object[2]);
	
}
etatStockMP.setQuantiteFabriquer (etatStockMP.getQuantiteFabriquer().add(fabriquer));

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
etatStockMP.setTransferSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
if(((BigDecimal)object[2])!=null)
{
	fabriquer=((BigDecimal)object[2]);
	
}
	etatStockMP.setQuantiteFabriquer(fabriquer);


etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}	

//////////////////////////////////////////////////////////////////////////////////////////////////////// Existante  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;
BigDecimal existante=BigDecimal.ZERO;
for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurExistante.size() ; j++) {
	existante=BigDecimal.ZERO;
existe=false;

Object[] object=listeObjectEtatStockGroupByMPByFournisseurExistante.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {
	existante=BigDecimal.ZERO;
if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;

EtatStockMP etatStockMP=listEtatStockMP.get(k);

if(((BigDecimal)object[2])!=null)
{
	existante=((BigDecimal)object[2]);
	
}

etatStockMP.setQuantiteExistante(etatStockMP.getQuantiteExistante().add(existante));
etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
etatStockMP.setTransferSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
if(((BigDecimal)object[2])!=null)
{
existante=((BigDecimal)object[2]);
	
}
etatStockMP.setQuantiteExistante(existante);


etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}
	  
//////////////////////////////////////////////////////////////////////////////////////////////////////// Sortie En Attente ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	  
		boolean trouver=false;
		
		  for(int j=0;j<listeEtatStockTransfertEnAttenteThe.size() ; j++) {
		  
			  trouver=false;
			  
		  DetailTransferStockMP detailTransferStockMP=listeEtatStockTransfertEnAttenteThe.get(j);
		 
			  
		  for(int k=0;k<listEtatStockMP.size();k++) {
		  
		  if(listEtatStockMP.get(k).getMp().getId()==detailTransferStockMP.getMatierePremier().getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==detailTransferStockMP.getFournisseur().getId()) {
			
			if(detailTransferStockMP.getMagasinDestination().getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION) )  
				
				
			{
			if(detailTransferStockMP.getMagasinSouce()!=null)	
			{
				
				if(detailTransferStockMP.getMagasinSouce().getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION) )  
					
				{
					
					
					  trouver=true;
					  
					  EtatStockMP etatStockMP=listEtatStockMP.get(k);
					  
					 
					  etatStockMP.setQuantiteResterMachine(etatStockMP.getQuantiteResterMachine().add(detailTransferStockMP.getQuantite()));
					 		  listEtatStockMP.set(k, etatStockMP);
					
					
					
					
				}
				
				
				
			}
				
				
				
				
				
			}
			  
		
		  
		  
		  
		  }
		  

		  
		  
		  
		  
		  
		  }
		  
			if(trouver==false)
			{
				
				 if(detailTransferStockMP.getFournisseur()!=null) 
				 {
					 
						if(detailTransferStockMP.getMagasinDestination().getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION) )  
							
							
						{
						if(detailTransferStockMP.getMagasinSouce()!=null)	
						{
							
							if(detailTransferStockMP.getMagasinSouce().getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION) )  
								
							{
								
								  EtatStockMP etatStockMP=new EtatStockMP();
									 
								  etatStockMP.setMp(detailTransferStockMP.getMatierePremier());
								  etatStockMP.setFournisseurMP(detailTransferStockMP.getFournisseur());
								  etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
								  etatStockMP.setQuantiteReception(BigDecimal.ZERO);
								  etatStockMP.setTransferEntrer(BigDecimal.ZERO);
								  etatStockMP.setTransferSortie(BigDecimal.ZERO);
								  etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
								  etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
								  etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
								  etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
								  etatStockMP.setQuantiteResterMachine(detailTransferStockMP.getQuantite());
								  etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
								  etatStockMP.setQuantiteExistante(BigDecimal.ZERO);
								  
								  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));
								 
								  listEtatStockMP.add(etatStockMP);	
								
							}
								
							}
						}
						}
				 
			
				
				
			}
		  
		  
		  
		  
		  }
	  
	  
	  
	  
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	  
	  
	  
	  
	  
	  
	  
	 
	  
	  for(int k=0;k<listEtatStockMP.size();k++) {
		  
		  EtatStockMP etatStockMP=listEtatStockMP.get(k);
		
		  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie()).add(etatStockMP.getQuantiteExistante()))));
		 
		  if(etatStockMP.getFournisseurMP()!=null)
		  {
			  System.out.println(etatStockMP.getMp().getCode() +" *** "+etatStockMP.getFournisseurMP().getCodeFournisseur() + "****"+etatStockMP.getQuantiteInitial()+" *** "+etatStockMP.getQuantiteReception()+" *** "+ etatStockMP.getQuantiteRetour()+" *** "+etatStockMP.getTransferEntrer()+" *** "+etatStockMP.getQuantiteResterMachine()+" *** "+etatStockMP.getQuantiteFabriquer() +" ---- "+ etatStockMP.getQuantiteCharger() +" *****" + etatStockMP.getQuantiteChargerSupp()+" ***** "+etatStockMP.getQuantiteAutreSortie() +" ***** "+ etatStockMP.getTransferSortie() +" ***** "+ etatStockMP.getQuantiteExistante());

		  }
		  
		  
		  listEtatStockMP.set(k, etatStockMP);
		  
		  
		  }
	  
	  
	  
	  
	  

		
	
}


public void InventaireNonValider()
{
	Magasin magasin=mapMagasin.get(comboMagasin.getSelectedItem().toString());
	MapInventaireNonValider.clear();
	
	if(listInventaireNonValider.size()==0)
	{
		
		listStockMPInventaire=stockMPDAO.findSockByMagasin(magasin.getId());
		CalculerStockFinale();
		
		List<DetailPerteMP> listDetailPerteMP=new ArrayList<DetailPerteMP>();
		Magasin magasinDechet=MapmagasinDechetMP.get(comboMagasinDechet.getSelectedItem());
		PerteMP perteMP=PerteMPDAO.findByDateByMagasinByNumPerteParEtat ("",dateoperation.getDate(), magasinDechet, ETAT_VALIDER);
		BigDecimal QuantitePerte=BigDecimal.ZERO;
		if(perteMP!=null)
		{
			
			listDetailPerteMP=detailPerteMPDAO.listeDetailPerteMPByPerteMP(perteMP);
			
		
			
		}
		
		
			  int i=0;
				while(i<listStockMPInventaire.size())
				{	
					
					StockMP StockMP=listStockMPInventaire.get(i);
					
					String fournisseur="";
					if(StockMP.getFournisseurMP()!=null)
					{
						fournisseur=StockMP.getFournisseurMP().getCodeFournisseur();
					}
					
					
					
					QuantitePerte=BigDecimal.ZERO;
					
					MatierePremier matierePremier=	StockMP.getMatierePremier();
				
						for(int j=0;j<listDetailPerteMP.size();j++)
						{
							
							
							DetailPerteMP detailPerteMP=listDetailPerteMP.get(j);
							
							if(detailPerteMP.getMatierePremier().getId()==matierePremier.getId())
							{
								
								if(fournisseur!="")
								{
									if(detailPerteMP.getFournisseurMP()!=null)
									{
										
										if(fournisseur.equals(detailPerteMP.getFournisseurMP().getCodeFournisseur()))
										{
											
											QuantitePerte=QuantitePerte.add(detailPerteMP.getQuantite());
											
										}
										
										
										
									}
									
									
									
								}else
								{
									
									
									if(detailPerteMP.getFournisseurMP()==null)
									{
										
										
											
											QuantitePerte=QuantitePerte.add(detailPerteMP.getQuantite());
											
										
										
										
										
									}
									
									
									
									
									
								}
								
								
								
								
							}
							
							
							
							
						}
					
					
						Inventaire inventaireTmp=new Inventaire();
						inventaireTmp.setDateoperation(dateoperation.getDate());
						inventaireTmp.setMagasin(magasin);
						inventaireTmp.setMatierePremier(matierePremier);
						inventaireTmp.setCodeInventaire(Constantes.CODE_INVENTAIRE_1);
						inventaireTmp.setMagasindechet(magasinDechet);
						if(StockMP.getFournisseurMP()!=null)
						{
							 
							inventaireTmp.setFournisseurMP(StockMP.getFournisseurMP());
							
							
							
							
							Boolean trouve=false;

							for(int f=0;f<listEtatStockMPAfficher.size() ; f++)
							{

						

							
							


							if(inventaireTmp.getMatierePremier().getId()==listEtatStockMPAfficher.get(f).getMp().getId()  )
							{


							if(inventaireTmp.getFournisseurMP()!=null)
							{
								
								if(listEtatStockMPAfficher.get(f).getFournisseurMP()!=null)
								{
									
									if(inventaireTmp.getFournisseurMP().getId()==listEtatStockMPAfficher.get(f).getFournisseurMP().getId())
									{

									trouve=true;

									inventaireTmp.setQuantiteStock(listEtatStockMPAfficher.get(f).getQuantiteFinale());
									inventaireTmp.setQuantiteStockApresDeuxiemInventaire(listEtatStockMPAfficher.get(f).getQuantiteFinale());
									

									}
								}

						


							}


							}




							}

							if(trouve==false)
							{
							if(inventaireTmp.getFournisseurMP()!=null)
							{
								inventaireTmp.setQuantiteStock(BigDecimal.ZERO);
								inventaireTmp.setQuantiteStockApresDeuxiemInventaire(BigDecimal.ZERO);
							}

							}
							

							
							
							
							
							
							
							
							
							
							
						}else
						{
							






Boolean existex=false;

for(int j=0;j<listEtatStockMPAfficher.size() ; j++)
{




if(inventaireTmp.getMatierePremier().getId()==listEtatStockMPAfficher.get(j).getMp().getId() )
{

if(inventaireTmp.getFournisseurMP()==null)
{

if(listEtatStockMPAfficher.get(j).getFournisseurMP()==null)	
{

existex=true;

inventaireTmp.setQuantiteStock(listEtatStockMPAfficher.get(j).getQuantiteFinale());

inventaireTmp.setQuantiteStockApresDeuxiemInventaire(listEtatStockMPAfficher.get(j).getQuantiteFinale());
}


}




}



}

if(existex==false)
{
if(inventaireTmp.getFournisseurMP()==null)
{

inventaireTmp.setQuantiteStock(BigDecimal.ZERO);
inventaireTmp.setQuantiteStockApresDeuxiemInventaire(BigDecimal.ZERO);
}

}



							
							
							
							
							
							
							
							
						}
						inventaireTmp.setQuantitePerte(BigDecimal.ZERO);
						inventaireTmp.setEtat(ETAT_INVENTAIRE_1_NNONVALIDER);
						inventaireTmp.setQuantite(BigDecimal.ZERO);
						inventaireTmp.setDepot(magasin.getDepot());
						inventaireTmp.setQuantitePerte(QuantitePerte);
						inventaireTmp.setDateSaisir(new Date());
						inventaireTmp.setCreerPar(utilisateur);
						inventaireDAO.add(inventaireTmp);
						listInventaireNonValider.add(inventaireTmp);
						
				if(inventaireTmp.getFournisseurMP()!=null)	
				{
					
				
					
					MapInventaireNonValider.put(inventaireTmp.getMatierePremier().getCode()+"_"+inventaireTmp.getFournisseurMP().getCodeFournisseur(), inventaireTmp);
					
				}else
				{
					
					MapInventaireNonValider.put(inventaireTmp.getMatierePremier().getCode(), inventaireTmp);
					
				}
							
					i++;
				}
				
				
			
		
		
		
		
		
		
		
		
		
	}else
	{
		
		for(int t=0;t<listInventaireNonValider.size();t++)
		{
			
			Inventaire inventaireTmp=listInventaireNonValider.get(t);
			
			if(inventaireTmp.getFournisseurMP()!=null)	
			{
				
			
				
				MapInventaireNonValider.put(inventaireTmp.getMatierePremier().getCode()+"_"+inventaireTmp.getFournisseurMP().getCodeFournisseur(), inventaireTmp);
				
			}else
			{
				
				MapInventaireNonValider.put(inventaireTmp.getMatierePremier().getCode(), inventaireTmp);
				
			}
			
			
			
			
			
			
			
			
		}
		
		
		
		
		
	}
	
	



}

	


	
	
	
}









package presentation.stockMP;

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
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
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

import org.hibernate.Session;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.ConverterNumberToWords;

import util.HibernateUtil;
import util.JasperUtils;
import util.NumberUtils;
import util.Utils;
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailMouvementStockDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.MouvementStockGlobalDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoImplManager.TransferStockPFDAOImpl;
import dao.daoImplManager.TypeSortieDAOImpl;
import dao.daoManager.ArticlesDAO;

import dao.daoManager.ChargeProductionDAO;
import dao.daoManager.ChargesDAO;

import dao.daoManager.CompteurProductionDAO;
import dao.daoManager.DepotDAO;

import dao.daoManager.DetailCoutProductionDAO;

import dao.daoManager.DetailMouvementStockDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.MouvementStockGlobalDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.daoManager.TypeSortieDAO;
import dao.entity.Articles;
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
import dao.entity.DetailMouvementStock;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;

import dao.entity.FicheEmploye;
import dao.entity.FournisseurMP;
import dao.entity.FraisDepot;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.MouvementStockGlobal;
import dao.entity.MouvementStockProduitsFini;
import dao.entity.Parametre;
import dao.entity.Production;
import dao.entity.StockMP;
import dao.entity.StockPF;
import dao.entity.SubCategorieMp;
import dao.entity.TransferStockMP;
import dao.entity.TypeSortie;
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

import javax.swing.JRadioButton;

import java.awt.Component;

import javax.swing.JToggleButton;
import javax.swing.JCheckBox;

public class ConsulterMouvementStockDechetManque extends JLayeredPane implements Constantes {
	public JLayeredPane contentPane;

	private DefaultTableModel modeleDetailMouvementStock;
	private DefaultTableModel modeleMouvementStock;

	private JXTable tableDetailMouvement = new JXTable();

	private List<Depot> listDepot = new ArrayList<Depot>();
	private List<Depot> listparDepot = new ArrayList<Depot>();
	private List<Magasin> listMagasin = new ArrayList<Magasin>();
	private List<DetailTransferStockMP> listDetailTransferStockMP = new ArrayList<DetailTransferStockMP>();

	private List<FournisseurMP> listFournisseur = new ArrayList<FournisseurMP>();
	private List<TypeSortie> listTypeSortie = new ArrayList<TypeSortie>();
	private List<MatierePremier> listMP = new ArrayList<MatierePremier>();

	private Map<String, Depot> mapDepot = new HashMap<>();
	private Map<String, Depot> mapparDepot = new HashMap<>();
	private Map<String, Magasin> mapMagasin = new HashMap<>();
	private Map<String, FournisseurMP> mapFournisseur = new HashMap<>();
	private Map<String, MatierePremier> mapMP = new HashMap<>();
	private Map<String, MatierePremier> mapCodeMP = new HashMap<>();
	private Map<String, TypeSortie> mapTypeSortie = new HashMap<>();
	private ImageIcon imgModifierr;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private ImageIcon imgChercher;
	private ImageIcon imgImprimer;
	private ImageIcon imgExcel;
	JComboBox combomp = new JComboBox();
	JComboBox combomagasin = new JComboBox();
	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnRechercher;
	private Utilisateur utilisateur;
	private MouvementStockGlobalDAO mouvementStockGlobaleDAO;
	private DetailMouvementStockDAO detailMouvementStockDAO;
	private TypeSortieDAO typeSortieDAO;
	private FournisseurMPDAO fournisseurMPDAO;
	private DetailTransferMPDAO detailTransferStockMPDAO;
	private TransferStockMPDAO transferStockMPDAO;
	private JTextField txtlibelle = new JTextField();

	private ProductionDAO productionDAO;
	private DepotDAO depotdao;
	JDateChooser dateChooserdebut = new JDateChooser();
	JDateChooser dateChooserfin = new JDateChooser();
	private JDateChooser dateChooser = new JDateChooser();
	JComboBox combodepot = new JComboBox();
	JButton btnSupprimer = new JButton();
	private JRadioButton rdbtnDateFacture;
	private StockPFDAO stockpfDAO;

	private MatierePremiereDAO MatierPremiereDAO;
	private JTextField txtcodemp;
	String titre = "";
	JComboBox combofournisseur = new JComboBox();
	JComboBox combotypesortie = new JComboBox();

	BigDecimal QuantiteTotal = BigDecimal.ZERO;
	private JTextField txtfieldtotal;
	private JTextField txtQuantiteTotal;
	 private SubCategorieMPDAO categorieMPDAO;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ConsulterMouvementStockDechetManque() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, 1485, 1137);

		try {

			
			imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
			imgModifierr = new ImageIcon(this.getClass().getResource("/img/modifier.png"));
			imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp1.png"));
			imgInit = new ImageIcon(this.getClass().getResource("/img/init.png"));
			imgValider = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
			imgChercher = new ImageIcon(this.getClass().getResource("/img/chercher.png"));
			imgImprimer = new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
			utilisateur = AuthentificationView.utilisateur;
			depotdao = new DepotDAOImpl();
			productionDAO = new ProductionDAOImpl();
			mouvementStockGlobaleDAO = new MouvementStockGlobalDAOImpl();
			MatierPremiereDAO = new MatierePremierDAOImpl();
			detailTransferStockMPDAO = new DetailTransferMPDAOImpl();
			transferStockMPDAO = new TransferStockMPDAOImpl();
			listMP = MatierPremiereDAO.findAll();
			detailMouvementStockDAO = new DetailMouvementStockDAOImpl();

			typeSortieDAO = new TypeSortieDAOImpl();
			fournisseurMPDAO = new FournisseurMPDAOImpl();
			
			categorieMPDAO=new SubCategorieMPAOImpl();
        	SubCategorieMp subCategorieMp=categorieMPDAO.findByCode(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE);
        	listFournisseur=fournisseurMPDAO.findByCategorie(subCategorieMp);
			
		

		} catch (Exception exp) {
			exp.printStackTrace();
		}
		tableDetailMouvement.setEditable(false);
		tableDetailMouvement.setAutoStartEditOnKeyStroke(false);

		tableDetailMouvement.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Date", "Matiere Premiere", "Fournisseur", "Quantite" }));
		tableDetailMouvement.getColumnModel().getColumn(0).setPreferredWidth(102);
		tableDetailMouvement.getColumnModel().getColumn(1).setPreferredWidth(260);
		tableDetailMouvement.getColumnModel().getColumn(2).setPreferredWidth(102);
		tableDetailMouvement.getColumnModel().getColumn(3).setPreferredWidth(91);

		tableDetailMouvement.setShowVerticalLines(false);
		tableDetailMouvement.setSelectionBackground(new Color(51, 204, 255));
		tableDetailMouvement.setRowHeightEnabled(true);
		tableDetailMouvement.setBackground(new Color(255, 255, 255));
		tableDetailMouvement.setHighlighters(HighlighterFactory.createSimpleStriping());
		tableDetailMouvement.setColumnControlVisible(true);
		tableDetailMouvement.setForeground(Color.BLACK);
		tableDetailMouvement.setGridColor(new Color(0, 0, 255));
		tableDetailMouvement.setBounds(2, 27, 411, 198);
		tableDetailMouvement.setRowHeight(20);

		JScrollPane scrollPane = new JScrollPane(tableDetailMouvement);
		scrollPane.setBounds(10, 235, 1465, 587);
		add(scrollPane);
		scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		JXTitledSeparator titledSeparator = new JXTitledSeparator();
		titledSeparator.setTitle("Liste Detail Mouvement de Stock");
		titledSeparator.setBounds(10, 194, 1465, 30);
		add(titledSeparator);

		JButton buttonvalider = new JButton("Imprimer");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				  
				  
				  if(listDetailTransferStockMP.size()!=0) {
					  
					  Map parameters = new HashMap(); 
				
				  parameters.put("magasin",listDetailTransferStockMP.get(0).getMagasinSouce().getLibelle());
				  
				  parameters.put("titre", titre);
				  parameters.put("QuantiteTotal", QuantiteTotal);
				  JasperUtils.imprimerEtatMouvementStockDechetManque(listDetailTransferStockMP, parameters); 
				 
				  }
				 
				  else { JOptionPane.showMessageDialog(null,
				  "Il n'existe auccun Mouvement Stock  ", "Erreur", JOptionPane.ERROR_MESSAGE);
				 return; }
				}
		});

		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(576, 833, 112, 32);
		buttonvalider.setIcon(imgImprimer);
		add(buttonvalider);

		JLabel lblConslterLesFactures = new JLabel("           Consulter le Mouvement de Stock :");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(332, 11, 836, 35);
		add(lblConslterLesFactures);
		// Group the radio buttons.
		ButtonGroup group = new ButtonGroup();

		JButton btnAfficher = new JButton("Consulter");
		btnAfficher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				detailTransferStockMPDAO.ViderSession();
				listDetailTransferStockMP.clear();

				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");

				boolean trouve = false;
				MatierePremier mp = mapMP.get(combomp.getSelectedItem());
				Magasin magasin = mapMagasin.get(combodepot.getSelectedItem());
				TypeSortie typeSortie = mapTypeSortie.get(combotypesortie.getSelectedItem());
				FournisseurMP fournisseurMP = mapFournisseur.get(combofournisseur.getSelectedItem());
				String statut = Constantes.ETAT_TRANSFER_STOCK_SORTIE;

				if (typeSortie != null) {

					if (dateChooserdebut.getDate() == null && dateChooserfin.getDate() == null) {
						JOptionPane.showMessageDialog(null, "Veuillez entrer la date SVP !!!");
						return;
					} else {
						if (magasin != null) {
							if (dateChooserdebut.getDate() != null && dateChooserfin.getDate() != null) {
								String d1 = ((JTextField) dateChooserdebut.getDateEditor().getUiComponent()).getText();
								String d2 = ((JTextField) dateChooserfin.getDateEditor().getUiComponent()).getText();

								if (!d1.equals(d2)) {
									if (dateChooserfin.getDate().compareTo(dateChooserdebut.getDate()) < 0) {
										JOptionPane.showMessageDialog(null,
												"date de fin doit etre supérieur au date debut SVP !!!");
										return;
									}

								}
								
								titre="MOUVEMENT DE STOCK "+ typeSortie.getLiblle() + " ENTRE "+d1 +" ET "+d2 ;
								
								
								
								if(mp!=null && fournisseurMP!=null)
								{
									
									titre="MOUVEMENT DE STOCK "+ typeSortie.getLiblle()  + " DE "+mp.getNom() + " ENTRE "+d1 +" ET "+d2 +" PAR FOURNISSEUR : "+fournisseurMP.getNom();
									
									
								}else if(mp==null && fournisseurMP!=null)
								{
									
									titre="MOUVEMENT DE STOCK "+ typeSortie.getLiblle()  + " ENTRE "+d1 +" ET "+d2 +" PAR FOURNISSEUR : "+fournisseurMP.getNom();
									
								}else if(mp!=null && fournisseurMP==null)
								{
									
									titre="MOUVEMENT DE STOCK "+ typeSortie.getLiblle()  + " DE "+mp.getNom() + " ENTRE "+d1 +" ET "+d2 ;
	
								}
								
								
								

							} else if (dateChooserfin.getDate() == null && dateChooserdebut.getDate() != null) {
								dateChooserfin.setDate(dateChooserdebut.getDate());
								
								String d1 = ((JTextField) dateChooserdebut.getDateEditor().getUiComponent()).getText();
								String d2 = ((JTextField) dateChooserfin.getDateEditor().getUiComponent()).getText();

								
                             titre="MOUVEMENT DE STOCK "+ typeSortie.getLiblle() + " ENTRE "+d1 +" ET "+d2 ;
								
								
								
								if(mp!=null && fournisseurMP!=null)
								{
									
									titre="MOUVEMENT DE STOCK "+ typeSortie.getLiblle()  + " DE "+mp.getNom() + " ENTRE "+d1 +" ET "+d2 +" PAR FOURNISSEUR : "+fournisseurMP.getNom();
									
									
								}else if(mp==null && fournisseurMP!=null)
								{
									
									titre="MOUVEMENT DE STOCK "+ typeSortie.getLiblle()  + " ENTRE "+d1 +" ET "+d2 +" PAR FOURNISSEUR : "+fournisseurMP.getNom();
									
								}else if(mp!=null && fournisseurMP==null)
								{
									
									titre="MOUVEMENT DE STOCK "+ typeSortie.getLiblle()  + " DE "+mp.getNom() + " ENTRE "+d1 +" ET "+d2 ;
	
								}
								
								
								
								

							} else if (dateChooserdebut.getDate() == null && dateChooserfin.getDate() != null) {
								dateChooserdebut.setDate(dateChooserfin.getDate());
								
								String d1 = ((JTextField) dateChooserdebut.getDateEditor().getUiComponent()).getText();
								String d2 = ((JTextField) dateChooserfin.getDateEditor().getUiComponent()).getText();

								
                             titre="MOUVEMENT DE STOCK "+ typeSortie.getLiblle() + " ENTRE "+d1 +" ET "+d2 ;
								
								
								
								if(mp!=null && fournisseurMP!=null)
								{
									
									titre="MOUVEMENT DE STOCK "+ typeSortie.getLiblle()  + " DE "+mp.getNom() + " ENTRE "+d1 +" ET "+d2 +" PAR FOURNISSEUR : "+fournisseurMP.getNom();
									
									
								}else if(mp==null && fournisseurMP!=null)
								{
									
									titre="MOUVEMENT DE STOCK "+ typeSortie.getLiblle()  + " ENTRE "+d1 +" ET "+d2 +" PAR FOURNISSEUR : "+fournisseurMP.getNom();
									
								}else if(mp!=null && fournisseurMP==null)
								{
									
									titre="MOUVEMENT DE STOCK "+ typeSortie.getLiblle()  + " DE "+mp.getNom() + " ENTRE "+d1 +" ET "+d2 ;
	
								}
								
								
								
							}

							
							
							
							listDetailTransferStockMP = detailTransferStockMPDAO.ListTransferStockMPDechetmanqueEntreDeuxDatesBYMagasinByMPByFournisseurByType(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, statut, magasin,fournisseurMP, typeSortie);

							afficher_tableDetailTransferStockMP(listDetailTransferStockMP);

						} else {
							JOptionPane.showMessageDialog(null, "Veuillez selectionner un Magasin SVP ", "Erreur",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Veuillez selectionner le Type de Sortie MP SVP ", "Erreur",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

			}
		});
		btnAfficher.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAfficher.setBounds(534, 159, 107, 24);
		btnAfficher.setIcon(imgChercher);
		add(btnAfficher);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane.setBounds(10, 65, 1465, 83);
		add(layeredPane);

		JLabel lblDu = new JLabel("Du :");
		lblDu.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		lblDu.setBounds(10, 11, 45, 24);
		layeredPane.add(lblDu);

		dateChooserdebut = new JDateChooser();
		dateChooserdebut.setLocale(Locale.FRANCE);
		dateChooserdebut.setDateFormatString("dd/MM/yyyy");
		dateChooserdebut.setBounds(44, 9, 163, 26);
		layeredPane.add(dateChooserdebut);

		JLabel lblAu = new JLabel("Au :");
		lblAu.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		lblAu.setBounds(217, 11, 36, 24);
		layeredPane.add(lblAu);

		dateChooserfin = new JDateChooser();
		dateChooserfin.setLocale(Locale.FRANCE);
		dateChooserfin.setDateFormatString("dd/MM/yyyy");
		dateChooserfin.setBounds(247, 9, 169, 26);
		layeredPane.add(dateChooserfin);
		txtcodemp = new JTextField();
		txtcodemp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == e.VK_ENTER) {

					if (!txtcodemp.getText().equals("")) {
						MatierePremier mp = mapCodeMP.get(txtcodemp.getText().toUpperCase());

						if (mp != null) {
							combomp.setSelectedItem(mp.getNom());

						} else {
							JOptionPane.showMessageDialog(null, "Code MP Introuvable !!!!", "Erreur",
									JOptionPane.ERROR_MESSAGE);

						}

					} else {
						JOptionPane.showMessageDialog(null, "Veuillez  entrer code MP SVP", "Erreur",
								JOptionPane.ERROR_MESSAGE);

					}

				}

			}
		});
		txtcodemp.setText("");
		txtcodemp.setColumns(10);
		txtcodemp.setBounds(747, 8, 93, 26);
		layeredPane.add(txtcodemp);
		combomp = new JComboBox();
		combomp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (!combomp.getSelectedItem().equals("")) {
					MatierePremier mp = mapMP.get(combomp.getSelectedItem());
					txtcodemp.setText(mp.getCode());

				} else {
					
					txtcodemp.setText(Constantes.CODE_MP);
					 }

			}
		});
		combomp.setBounds(895, 8, 374, 27);
		layeredPane.add(combomp);
AutoCompleteDecorator.decorate(combomp);
		combomp.addItem("");
		int i = 0;
		while (i < listMP.size()) {
			MatierePremier mp = listMP.get(i);
			combomp.addItem(mp.getNom());
			mapMP.put(mp.getNom(), mp);
			mapCodeMP.put(mp.getCode(), mp);

			i++;

		}
		
		

		JLabel label_2 = new JLabel("Libelle :");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_2.setBounds(850, 8, 45, 26);
		layeredPane.add(label_2);

	

		JLabel lblCodeMp = new JLabel("Code MP :");
		lblCodeMp.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCodeMp.setBounds(693, 11, 74, 26);
		layeredPane.add(lblCodeMp);

		JLabel lblDepot = new JLabel("Magasin  :");
		lblDepot.setBounds(426, 8, 55, 26);
		layeredPane.add(lblDepot);
		lblDepot.setFont(new Font("Tahoma", Font.PLAIN, 11));

		combodepot = new JComboBox();
		combodepot.setBounds(481, 8, 202, 27);
		layeredPane.add(combodepot);
		try {

			Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String) util.DateUtils.getCurrentYear() + "-01-01");
			dateChooserdebut.setDate(date);
			dateChooserfin.setDate(new Date());

			combofournisseur = new JComboBox();
			combofournisseur.setBounds(76, 46, 218, 27);
			layeredPane.add(combofournisseur);
		
			JLabel lblFournisseur = new JLabel("Fournisseur :");
			lblFournisseur.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblFournisseur.setBounds(10, 46, 74, 26);
			layeredPane.add(lblFournisseur);

			combotypesortie = new JComboBox();
			combotypesortie.setBounds(381, 46, 218, 27);
			layeredPane.add(combotypesortie);

			JLabel lblTypeSortie = new JLabel("Type Sortie :");
			lblTypeSortie.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblTypeSortie.setBounds(315, 46, 74, 26);
			layeredPane.add(lblTypeSortie);

		} catch (Exception e) {
			// TODO: handle exception
		}
		JButton button = new JButton("Initialiser");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				combomp.setSelectedItem("");
				txtcodemp.setText(Constantes.CODE_MP);
				dateChooserdebut.setCalendar(null);
				dateChooserfin.setCalendar(null);
				combodepot.setSelectedItem("");
				combofournisseur.setSelectedIndex(-1);
				listDetailTransferStockMP.clear();
				afficher_tableDetailTransferStockMP(listDetailTransferStockMP);
				

			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button.setBounds(668, 159, 107, 24);
		add(button);

		txtfieldtotal = new JTextField();
		txtfieldtotal.setEditable(false);
		txtfieldtotal.setHorizontalAlignment(SwingConstants.LEFT);
		txtfieldtotal.setFont(new Font("Tahoma", Font.PLAIN, 30));
		txtfieldtotal.setForeground(Color.GREEN);
		txtfieldtotal.setBackground(Color.BLACK);
		txtfieldtotal.setText("Quantite Total :");
		txtfieldtotal.setColumns(10);
		txtfieldtotal.setBounds(1011, 837, 258, 49);
		add(txtfieldtotal);

		txtQuantiteTotal = new JTextField();
		txtQuantiteTotal.setEditable(false);
		txtQuantiteTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		txtQuantiteTotal.setFont(new Font("Tahoma", Font.PLAIN, 30));
		txtQuantiteTotal.setForeground(Color.GREEN);
		txtQuantiteTotal.setBackground(Color.BLACK);
		txtQuantiteTotal.setText("00");
		txtQuantiteTotal.setColumns(10);
		txtQuantiteTotal.setBounds(1279, 837, 183, 49);
		add(txtQuantiteTotal);

		if (utilisateur.getLogin().equals("admin")) {
			listMagasin = depotdao.listeMagasinByTypeMagasin(MAGASIN_CODE_TYPE_MP);
			int k = 0;
			combodepot.addItem("");
			while (k < listMagasin.size()) {
				Magasin magasin = listMagasin.get(k);

				combodepot.addItem(magasin.getLibelle());

				mapMagasin.put(magasin.getLibelle(), magasin);

				k++;

			}

		} else {
			Depot depot = depotdao.findByCode(utilisateur.getCodeDepot());
			if (depot != null) {
				listMagasin = depotdao.listeMagasinByTypeMagasinDepot(depot.getId(), MAGASIN_CODE_TYPE_MP);
				int k = 0;
				combodepot.addItem("");
				while (k < listMagasin.size()) {
					Magasin magasin = listMagasin.get(k);

					combodepot.addItem(magasin.getLibelle());

					mapMagasin.put(magasin.getLibelle(), magasin);

					k++;

				}

			}
		}

		listTypeSortie = typeSortieDAO.findAll();
		for (int j = 0; j < listTypeSortie.size(); j++) {
			TypeSortie typesortie = listTypeSortie.get(j);
			if (typesortie.getLiblle().equals(Constantes.TYPE_SORTIE_DECHET)
					|| typesortie.getLiblle().equals(Constantes.TYPE_SORTIE_MANQUE)) {
				combotypesortie.addItem(typesortie.getLiblle());
				mapTypeSortie.put(typesortie.getLiblle(), typesortie);
			}

		}

		for (int k = 0; k < listFournisseur.size(); k++) {
			FournisseurMP fournisseurMP = listFournisseur.get(k);
			combofournisseur.addItem(fournisseurMP.getCodeFournisseur());
			mapFournisseur.put(fournisseurMP.getCodeFournisseur(), fournisseurMP);

		}
		
		
		
		combofournisseur.setSelectedIndex(-1);
		txtcodemp.setText(Constantes.CODE_MP);
	}

	void InitialiseTableauDetailMouvementStock() {
		modeleDetailMouvementStock = new DefaultTableModel(new Object[][] {},
				new String[] { "Date", "Matiere Premiere", "Fournisseur", "Quantite" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tableDetailMouvement.setModel(modeleDetailMouvementStock);
		tableDetailMouvement.getColumnModel().getColumn(0).setPreferredWidth(258);
		tableDetailMouvement.getColumnModel().getColumn(1).setPreferredWidth(102);
		tableDetailMouvement.getColumnModel().getColumn(2).setPreferredWidth(102);
		tableDetailMouvement.getColumnModel().getColumn(3).setPreferredWidth(91);
	}

	/*
	 * void InitialiseTableauMouvementStock() { modeleMouvementStock =new
	 * DefaultTableModel( new Object[][] { }, new String[] { "Date Mouvement",
	 * "Depot", "Magasin" } ) { boolean[] columnEditables = new boolean[] {
	 * false,false,false }; public boolean isCellEditable(int row, int column) {
	 * return columnEditables[column]; } }; table.setModel(modeleMouvementStock);
	 * table.getColumnModel().getColumn(0).setPreferredWidth(121);
	 * table.getColumnModel().getColumn(1).setPreferredWidth(106);
	 * table.getColumnModel().getColumn(2).setPreferredWidth(111);
	 * 
	 * 
	 * 
	 * }
	 */

	void afficher_tableDetailTransferStockMP(List<DetailTransferStockMP> listDetailTransferStockMP) {
		QuantiteTotal = BigDecimal.ZERO;

		modeleDetailMouvementStock = new DefaultTableModel(new Object[][] {},
				new String[] { "Date", "Matiere Premiere", "Fournisseur", "Quantite" }) {

		};
		tableDetailMouvement.setModel(modeleDetailMouvementStock);
		int i = 0;

		while (i < listDetailTransferStockMP.size()) {
			DetailTransferStockMP detaillistDetailTransferStockMP = listDetailTransferStockMP.get(i);
			QuantiteTotal = QuantiteTotal.add(detaillistDetailTransferStockMP.getQuantite());

			if(detaillistDetailTransferStockMP.getFournisseur()!=null)
			{
				Object[] ligne = { detaillistDetailTransferStockMP.getTransferStockMP().getDateTransfer(),
						detaillistDetailTransferStockMP.getMatierePremier().getNom(),
						detaillistDetailTransferStockMP.getFournisseur().getNom(),
						NumberFormat.getNumberInstance(Locale.FRANCE).format(detaillistDetailTransferStockMP.getQuantite()) };

				modeleDetailMouvementStock.addRow(ligne);
				
			}else
			{
				
				Object[] ligne = { detaillistDetailTransferStockMP.getTransferStockMP().getDateTransfer(),
						detaillistDetailTransferStockMP.getMatierePremier().getNom(),
						"",
						NumberFormat.getNumberInstance(Locale.FRANCE).format(detaillistDetailTransferStockMP.getQuantite()) };

				modeleDetailMouvementStock.addRow(ligne);
				
			}
			

			i++;
		}

		txtQuantiteTotal.setText(NumberFormat.getNumberInstance(Locale.FRANCE).format(QuantiteTotal )+ "");

	}
}

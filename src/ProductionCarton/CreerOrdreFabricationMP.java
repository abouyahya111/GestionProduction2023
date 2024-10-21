// 
// Decompiled by Procyon v0.5.36
// 

package ProductionCarton;

import dao.entity.FournisseurMP;
import dao.entity.SubCategorieMp;
import dao.entity.CategorieMp;
import java.text.ParseException;
import dao.entity.CoutMP;
import dao.entity.CompteurProduction;
import java.text.NumberFormat;
import java.util.Collection;
import javax.swing.table.TableModel;
import java.awt.Dimension;
import java.util.Locale;
import org.jdesktop.swingx.JXTitledSeparator;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;
import javax.swing.text.JTextComponent;
import javax.swing.DefaultCellEditor;
import org.jdesktop.swingx.decorator.HighlighterFactory;
import org.jdesktop.swingx.decorator.Highlighter;
import java.util.Date;
import dao.entity.Sequenceur;
import javax.swing.JFrame;
import Production.MatierePremiere;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import util.Utils;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Component;
import javax.swing.JOptionPane;
import dao.daoImplManager.SubCategorieMPAOImpl;
import main.AuthentificationView;
import dao.daoImplManager.DetailEstimationMPDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.StockPFDAOImpl;
import dao.daoImplManager.CompteurResponsableProdDAOImpl;
import dao.daoImplManager.CompteurProductionDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.ProductionMPDAOImpl;
import dao.daoImplManager.EquipeDAOImpl;
import dao.daoImplManager.ArticlesMPDAOImpl;
import java.awt.Toolkit;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import dao.daoImplManager.SequenceurDAOImpl;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.entity.DetailTransferStockMP;
import dao.entity.EtatStockMP;
import com.toedter.calendar.JDateChooser;
import javax.swing.JLabel;
import dao.entity.Utilisateur;
import dao.entity.ProductionMP;
import dao.daoManager.DetailEstimationMPDAO;
import dao.entity.DetailEstimationMP;
import dao.entity.CoutProdMP;
import java.util.List;
import dao.daoManager.StockPFDAO;
import dao.daoManager.CompteurResponsableProdDAO;
import dao.daoManager.CompteurProductionDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.ArticlesMPDAO;
import javax.swing.JComboBox;
import dao.daoManager.SequenceurDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.ProductionMPDAO;
import dao.daoManager.EquipeDAO;
import dao.entity.MatierePremier;
import java.math.BigDecimal;
import dao.entity.StockMP;
import dao.entity.Depot;
import dao.entity.ArticlesMP;
import dao.entity.Magasin;
import java.util.Map;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import org.jdesktop.swingx.JXTable;
import javax.swing.table.DefaultTableModel;
import util.Constantes;
import util.DateUtils;

import javax.swing.JLayeredPane;

public class CreerOrdreFabricationMP extends JLayeredPane implements Constantes
{
    public JLayeredPane contentPane;
    private DefaultTableModel modeleMP;
    private JXTable table;
    private ImageIcon imgModifier;
    private ImageIcon imgImprimer;
    private ImageIcon imgAjouter;
    private ImageIcon imgInit;
    private ImageIcon imgSupp1;
    private JButton btnCalculeMP;
    private JButton btnImprimer;
    private JButton btnInitialiser;
    private JButton btnAjouter;
    private JButton btnRechercher;
    private JTextField quantite;
    private JTextField codeArticleMP;
    private Map<String, Magasin> magasinMap;
    private Map<String, Magasin> magasinPordMap;
    private Map<String, ArticlesMP> mapAricleMP;
    private Map<String, String> mapCodeArticleMP;
    private Map<String, String> mapLibelleAricleMP;
    private Map<String, Depot> depotMap;
    private Map<Integer, StockMP> mapQauntiteMatierePremier;
    private Map<String, BigDecimal> mapQuantiteMP;
    private Map<String, MatierePremier> mapMatierePremier;
    private EquipeDAO equipeDAO;
    private ProductionMPDAO productionMPDAO;
    private StockMPDAO stockMPDAO;
    private MatierePremiereDAO matierePremiereDAO;
    private static SequenceurDAO sequenceurDAO;
    private JComboBox categorieMP;
    private JComboBox comboMagasin;
    private JComboBox comboDepot;
    private JComboBox comboDepotProd;
    private JComboBox comboMagasinProd;
    private String nomMP;
    private ArticlesMPDAO articleMPDAO;
    private DepotDAO depotDAO;
    private CompteurProductionDAO compteurProductionDAO;
    private CompteurResponsableProdDAO compteurResponsableProdDAO;
    private StockPFDAO stockPFDAO;
    private List<Depot> listDepot;
    private List<CoutProdMP> listCoutProdMP;
    private List<ArticlesMP> listArticlesMP;
    private List<DetailEstimationMP> lisDetailEstimationMP;
    private ArticlesMP articlemp;
    private DetailEstimationMPDAO detailestimationMPdao;
    private static ProductionMP productionMP;
    private Utilisateur utilisateur;
    private JTextField txtNumOF;
    private JLabel lblDescriptionOf;
    private JTextField txtDescription;
    private JLabel label;
    private JLabel lblMagasinProd;
    boolean creerOF;
    private JDateChooser datfabrication;
    private List<Object[]> Mindate;
    private List<Object[]> listeObjectStockInitialGroupByMP;
    private List<Object[]> listeObjectStockInitialGroupByMPByFournisseur;
    private List<Object[]> listeObjectEtatStockGroupByMP;
    private List<Object[]> listeObjectEtatStockGroupByMPByFournisseur;
    private List<EtatStockMP> listEtatStockMP;
    private List<EtatStockMP> listEtatStockMPAfficher;
    private List<EtatStockMP> listEtatStockMPAfficherMagasinStockage;
    private List<EtatStockMP> listEtatStockMPAfficherMagasinProduction;
    private List<Object[]> listeObjectEtatStockGroupByMPByFournisseurReception;
    private List<Object[]> listeObjectEtatStockGroupByMPByFournisseurEntrer;
    private List<Object[]> listeObjectEtatStockGroupByMPByFournisseurSortie;
    private List<Object[]> listeObjectEtatStockGroupByMPByFournisseurCharger;
    private List<Object[]> listeObjectEtatStockGroupByMPByFournisseurRetour;
    private List<Object[]> listeObjectEtatStockGroupByMPByFournisseurAutreSortie;
    private List<Object[]> listeObjectEtatStockGroupByMPByFournisseurResterMachine;
    private List<Object[]> listeObjectEtatStockGroupByMPByFournisseurFabrique;
    private List<Object[]> listeObjectEtatStockGroupByMPByFournisseurExistante;
    private List<Object[]> listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortiePF;
    private List<Object[]> listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortieEnAttent;
    private List<Object[]> listeObjectEtatStockGroupByMPByFournisseurAutreSortiePerte;
    private List<Object[]> listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetour;
    private List<DetailTransferStockMP> listeEtatStockTransfertEnAttenteThe;
    private List<DetailTransferStockMP> listeEtatStockTransfertEnAttenteNonThe;
    private List<Object[]> listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetourFournisseurAnnule;
    SubCategorieMPDAO SubCategorieMPDAO;
    private DetailTransferMPDAO detailTransferStockMPDAO;
    
    static {
        CreerOrdreFabricationMP.sequenceurDAO = new SequenceurDAOImpl();
        CreerOrdreFabricationMP.productionMP = new ProductionMP();
    }
    
    public CreerOrdreFabricationMP() {
        this.table = new JXTable();
        this.magasinMap = new HashMap<String, Magasin>();
        this.magasinPordMap = new HashMap<String, Magasin>();
        this.mapAricleMP = new HashMap<String, ArticlesMP>();
        this.mapCodeArticleMP = new HashMap<String, String>();
        this.mapLibelleAricleMP = new HashMap<String, String>();
        this.depotMap = new HashMap<String, Depot>();
        this.mapQauntiteMatierePremier = new HashMap<Integer, StockMP>();
        this.mapQuantiteMP = new HashMap<String, BigDecimal>();
        this.mapMatierePremier = new HashMap<String, MatierePremier>();
        this.comboDepot = new JComboBox();
        this.comboDepotProd = new JComboBox();
        this.comboMagasinProd = new JComboBox();
        this.listDepot = new ArrayList<Depot>();
        this.listCoutProdMP = new ArrayList<CoutProdMP>();
        this.listArticlesMP = new ArrayList<ArticlesMP>();
        this.lisDetailEstimationMP = new ArrayList<DetailEstimationMP>();
        this.articlemp = new ArticlesMP();
        this.creerOF = true;
        this.Mindate = new ArrayList<Object[]>();
        this.listeObjectStockInitialGroupByMP = new ArrayList<Object[]>();
        this.listeObjectStockInitialGroupByMPByFournisseur = new ArrayList<Object[]>();
        this.listeObjectEtatStockGroupByMP = new ArrayList<Object[]>();
        this.listeObjectEtatStockGroupByMPByFournisseur = new ArrayList<Object[]>();
        this.listEtatStockMP = new ArrayList<EtatStockMP>();
        this.listEtatStockMPAfficher = new ArrayList<EtatStockMP>();
        this.listEtatStockMPAfficherMagasinStockage = new ArrayList<EtatStockMP>();
        this.listEtatStockMPAfficherMagasinProduction = new ArrayList<EtatStockMP>();
        this.listeObjectEtatStockGroupByMPByFournisseurReception = new ArrayList<Object[]>();
        this.listeObjectEtatStockGroupByMPByFournisseurEntrer = new ArrayList<Object[]>();
        this.listeObjectEtatStockGroupByMPByFournisseurSortie = new ArrayList<Object[]>();
        this.listeObjectEtatStockGroupByMPByFournisseurCharger = new ArrayList<Object[]>();
        this.listeObjectEtatStockGroupByMPByFournisseurRetour = new ArrayList<Object[]>();
        this.listeObjectEtatStockGroupByMPByFournisseurAutreSortie = new ArrayList<Object[]>();
        this.listeObjectEtatStockGroupByMPByFournisseurResterMachine = new ArrayList<Object[]>();
        this.listeObjectEtatStockGroupByMPByFournisseurFabrique = new ArrayList<Object[]>();
        this.listeObjectEtatStockGroupByMPByFournisseurExistante = new ArrayList<Object[]>();
        this.listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortiePF = new ArrayList<Object[]>();
        this.listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortieEnAttent = new ArrayList<Object[]>();
        this.listeObjectEtatStockGroupByMPByFournisseurAutreSortiePerte = new ArrayList<Object[]>();
        this.listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetour = new ArrayList<Object[]>();
        this.listeEtatStockTransfertEnAttenteThe = new ArrayList<DetailTransferStockMP>();
        this.listeEtatStockTransfertEnAttenteNonThe = new ArrayList<DetailTransferStockMP>();
        this.listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetourFournisseurAnnule = new ArrayList<Object[]>();
        this.setOpaque(true);
        this.setBackground(new Color(248, 248, 255));
        this.setForeground(new Color(248, 248, 255));
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(0, 0, 1424, 565);
        try {
            CreerOrdreFabricationMP.productionMP = new ProductionMP();
            this.articleMPDAO = new ArticlesMPDAOImpl();
            this.equipeDAO = new EquipeDAOImpl();
            this.productionMPDAO = new ProductionMPDAOImpl();
            this.stockMPDAO = new StockMPDAOImpl();
            this.depotDAO = new DepotDAOImpl();
            this.detailTransferStockMPDAO = new DetailTransferMPDAOImpl();
            this.compteurProductionDAO = new CompteurProductionDAOImpl();
            this.compteurResponsableProdDAO = new CompteurResponsableProdDAOImpl();
            this.stockPFDAO = new StockPFDAOImpl();
            this.matierePremiereDAO = new MatierePremierDAOImpl();
            this.detailestimationMPdao = new DetailEstimationMPDAOImpl();
            this.utilisateur = AuthentificationView.utilisateur;
            this.nomMP = "";
            this.mapQauntiteMatierePremier = new HashMap<Integer, StockMP>();
            this.detailTransferStockMPDAO = new DetailTransferMPDAOImpl();
            this.SubCategorieMPDAO = new SubCategorieMPAOImpl();
        }
        catch (Exception exp) {
            exp.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur de connexion a la base de donn\u00e9es", "Erreur", 0);
        }
        this.comboDepotProd.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(final ItemEvent e) {
                if (!CreerOrdreFabricationMP.this.comboDepotProd.getSelectedItem().equals("") && e.getStateChange() == 1) {
                    List<Magasin> listMagasin = new ArrayList<Magasin>();
                    CreerOrdreFabricationMP.this.comboMagasinProd.removeAllItems();
                    final Depot depot = CreerOrdreFabricationMP.this.depotMap.get(CreerOrdreFabricationMP.this.comboDepotProd.getSelectedItem());
                    listMagasin = CreerOrdreFabricationMP.this.depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), "MP");
                    if (listMagasin != null) {
                        for (int j = 0; j < listMagasin.size(); ++j) {
                            final Magasin magasin = listMagasin.get(j);
                            CreerOrdreFabricationMP.this.comboMagasinProd.addItem(magasin.getLibelle());
                            CreerOrdreFabricationMP.this.magasinPordMap.put(magasin.getLibelle(), magasin);
                        }
                    }
                }
            }
        });
        this.comboDepotProd.addItem("");
        this.comboMagasinProd.addItem("");
        this.comboDepot.addItem("");
        final String Codedepot = this.utilisateur.getCodeDepot();
        try {
            this.imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            this.imgInit = new ImageIcon(this.getClass().getResource("/img/init.png"));
            this.imgModifier = new ImageIcon(this.getClass().getResource("/img/modifier.png"));
            this.imgImprimer = new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
            this.imgSupp1 = new ImageIcon(this.getClass().getResource("/img/supp1.png"));
        }
        catch (Exception exp2) {
            exp2.printStackTrace();
        }
        this.intialiserTableau();
        final String codeDepot = AuthentificationView.utilisateur.getCodeDepot();
        Utils.copycoller(this.codeArticleMP = new JTextField());
        (this.categorieMP = new JComboBox()).addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(final ItemEvent e) {
                if (e.getStateChange() == 1) {
                    CreerOrdreFabricationMP.this.codeArticleMP.setText(CreerOrdreFabricationMP.this.mapCodeArticleMP.get(CreerOrdreFabricationMP.this.categorieMP.getSelectedItem()));
                }
            }
        });
        this.categorieMP.addItem("");
        this.listArticlesMP = this.articleMPDAO.findAll();
        for (int i = 0; i < this.listArticlesMP.size(); ++i) {
            final ArticlesMP articlemp = this.listArticlesMP.get(i);
            this.mapCodeArticleMP.put(articlemp.getLiblle(), articlemp.getCodeArticle());
            this.mapLibelleAricleMP.put(articlemp.getCodeArticle(), articlemp.getLiblle());
            this.mapAricleMP.put(articlemp.getLiblle(), articlemp);
            this.categorieMP.addItem(articlemp.getLiblle());
        }
        this.codeArticleMP.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    CreerOrdreFabricationMP.this.categorieMP.setSelectedItem(CreerOrdreFabricationMP.this.mapLibelleAricleMP.get(CreerOrdreFabricationMP.this.codeArticleMP.getText()));
                }
            }
        });
        (this.btnImprimer = new JButton("Bon Sortie MP")).setIcon(this.imgImprimer);
        this.btnImprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (CreerOrdreFabricationMP.this.lisDetailEstimationMP.size() <= 0) {
                    JOptionPane.showMessageDialog(null, "Il faut calculer la mati\u00e8re Premi\u00e8re avant d'imprimer!", "Attention", 1);
                }
                else {
                    final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    final String date = dateFormat.format(CreerOrdreFabricationMP.this.datfabrication.getDate());
                    final Map parameters = new HashMap();
                    parameters.put("numOF", CreerOrdreFabricationMP.this.txtNumOF.getText());
                    parameters.put("magasin", CreerOrdreFabricationMP.this.comboMagasin.getSelectedItem());
                    parameters.put("dateProd", date);
                }
            }
        });
        this.btnImprimer.setFont(new Font("Tahoma", 0, 11));
        this.btnImprimer.setBounds(561, 459, 133, 23);
        this.add(this.btnImprimer);
        (this.comboMagasin = new JComboBox()).addItem("");
        (this.btnCalculeMP = new JButton("Calculer Mati\u00e8re ")).setHorizontalAlignment(10);
        this.btnCalculeMP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                CreerOrdreFabricationMP.access$13(CreerOrdreFabricationMP.this, "");
                if (CreerOrdreFabricationMP.this.categorieMP.getSelectedItem().equals("")) {
                    JOptionPane.showMessageDialog(null, "Il faut choisir l'article!", "Attention", 1);
                }
                else if (CreerOrdreFabricationMP.this.quantite.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Il faut remplir la quantit\u00e9!", "Attention", 1);
                }
                else if (CreerOrdreFabricationMP.this.comboMagasin.getSelectedItem().equals("")) {
                    JOptionPane.showMessageDialog(null, "Il faut choisir un magasin Source!", "Attention", 1);
                }
                else if (CreerOrdreFabricationMP.this.comboMagasinProd.getSelectedItem().equals("")) {
                    JOptionPane.showMessageDialog(null, "Il faut choisir un magasin Production!", "Attention", 1);
                }
                else {
                	articlemp=mapAricleMP.get(categorieMP.getSelectedItem());
                	lisDetailEstimationMP=detailestimationMPdao.findDetilestimationMPActifByArticle(articlemp.getId());
                   
                    
                    txtNumOF.setText(Utils.creerCodeOF( articlemp.getCodeArticle(), magasinPordMap.get( comboMagasinProd.getSelectedItem()).getDepot().getCode()));
                    boolean PrixMPZeroOuNull = false;
                    String Message = "Veuillez Entrer les Prix Des MP Suivantes SVP :\n";
                    for (int k = 0; k < lisDetailEstimationMP.size(); ++k) {
                        final DetailEstimationMP detailEstimation = lisDetailEstimationMP.get(k);
                        if (detailEstimation.getMatierePremier().getType().equals("INTERNE")) {
                            if (detailEstimation.getMatierePremier().getPrixByYear( DateUtils.getAnnee(datfabrication.getDate())) == null) {
                                PrixMPZeroOuNull = true;
                                Message = String.valueOf(Message) + detailEstimation.getMatierePremier().getCode() + "\n";
                            }
                            else if (detailEstimation.getMatierePremier().getPrixByYear( DateUtils.getAnnee(datfabrication.getDate())).compareTo(BigDecimal.ZERO) == 0) {
                                PrixMPZeroOuNull = true;
                                Message = String.valueOf(Message) + detailEstimation.getMatierePremier().getCode() + "\n";
                            }
                        }
                    }
                    if (PrixMPZeroOuNull) {
                        CreerOrdreFabricationMP.this.lisDetailEstimationMP.clear();
                        JOptionPane.showMessageDialog(null, Message, "Erreur Prix", 2);
                        return;
                    }
                    if (!CreerOrdreFabricationMP.this.afficherTableMatierePremiere(CreerOrdreFabricationMP.this.lisDetailEstimationMP)) {
                        final int reponse = JOptionPane.showConfirmDialog(null, "La quantit\u00e9 n'est pas suffaisante de la mati\u00e8re premi\u00e8re:\n" + CreerOrdreFabricationMP.this.nomMP + "\n" + "Voulez vous importer le Stock Supp\u00e9lementaire ?", "Satisfaction", 0);
                        if (reponse == 0) {
                            final JFrame popupJFrame = new MatierePremiere(CreerOrdreFabricationMP.this.mapQauntiteMatierePremier);
                            popupJFrame.setVisible(true);
                        }
                        else {
                            CreerOrdreFabricationMP.this.intialiserTableau();
                        }
                    }
                }
            }
        });
        this.btnCalculeMP.setIcon(new ImageIcon(CreerOrdreFabricationMP.class.getResource("/img/chercher.png")));
        this.btnCalculeMP.setFont(new Font("Tahoma", 0, 11));
        this.btnCalculeMP.setBounds(86, 459, 133, 23);
        this.add(this.btnCalculeMP);
        this.categorieMP.setBounds(250, 12, 257, 26);
        this.add(this.categorieMP);
        final JLabel lblArticle = new JLabel("Article MP :");
        lblArticle.setBounds(187, 11, 102, 26);
        this.add(lblArticle);
        lblArticle.setFont(new Font("Tahoma", 0, 12));
        Utils.copycoller(this.quantite = new JTextField());
        this.quantite.setBounds(579, 12, 157, 26);
        this.add(this.quantite);
        this.quantite.setColumns(10);
        final JLabel lblQuantite = new JLabel("Quantit\u00e9 :");
        lblQuantite.setFont(new Font("Tahoma", 0, 12));
        lblQuantite.setBounds(517, 12, 68, 26);
        this.add(lblQuantite);
        this.codeArticleMP.setBounds(51, 12, 126, 26);
        this.add(this.codeArticleMP);
        this.codeArticleMP.setColumns(10);
        final JLabel lblCodeArticle = new JLabel("Code");
        lblCodeArticle.setFont(new Font("Tahoma", 0, 12));
        lblCodeArticle.setBounds(10, 11, 82, 26);
        this.add(lblCodeArticle);
        this.btnAjouter = new JButton("Cr\u00e9er OF");
        this.btnAjouter.setBounds(266, 459, 107, 23);
        this.add(this.btnAjouter);
        this.btnAjouter.setIcon(this.imgAjouter);
        this.btnAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                CreerOrdreFabricationMP.access$13(CreerOrdreFabricationMP.this, "");
                CreerOrdreFabricationMP.access$23(CreerOrdreFabricationMP.this.productionMPDAO.findByNumOFMP(CreerOrdreFabricationMP.this.txtNumOF.getText(), codeDepot));
                if (CreerOrdreFabricationMP.productionMP == null) {
                    CreerOrdreFabricationMP.access$23(new ProductionMP());
                    if (CreerOrdreFabricationMP.this.lisDetailEstimationMP.size() <= 0) {
                        JOptionPane.showMessageDialog(null, "Il faut calculer la mati\u00e8re Premi\u00e8re!", "Attention", 1);
                    }
                    else if (!CreerOrdreFabricationMP.this.afficherTableMatierePremiereCreerOF(CreerOrdreFabricationMP.this.listCoutProdMP)) {
                        if (CreerOrdreFabricationMP.this.nomMP.equals("")) {
                            JOptionPane.showMessageDialog(null, "OF ne peut pas etre cr\u00e9e !Il faut remplir toutes quantit\u00e9s !!", "Attention", 1);
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "OF ne peut pas etre cr\u00e9e !La quantit\u00e9 : " + CreerOrdreFabricationMP.this.nomMP + "n'est pas suffaisante", "Attention", 1);
                        }
                    }
                    else {
                        final Magasin magasinProd = CreerOrdreFabricationMP.this.magasinPordMap.get(CreerOrdreFabricationMP.this.comboMagasinProd.getSelectedItem().toString());
                        final Magasin magasinStock = CreerOrdreFabricationMP.this.magasinMap.get(CreerOrdreFabricationMP.this.comboMagasin.getSelectedItem().toString());
                        final Sequenceur sequenceur = CreerOrdreFabricationMP.sequenceurDAO.findByLibelle(CreerOrdreFabricationMP.this.magasinPordMap.get(CreerOrdreFabricationMP.this.comboMagasinProd.getSelectedItem().toString()).getDepot().getCode());
                        CreerOrdreFabricationMP.productionMP.setStatut("Cr\u00e9e");
                        CreerOrdreFabricationMP.this.datfabrication.setDateFormatString("dd/MM/yyyy");
                        final Date dateFabrication = CreerOrdreFabricationMP.this.datfabrication.getDate();
                        CreerOrdreFabricationMP.productionMP.setNumOFMP(CreerOrdreFabricationMP.this.txtNumOF.getText());
                        CreerOrdreFabricationMP.productionMP.setCodeDepot(codeDepot);
                        CreerOrdreFabricationMP.productionMP.setQuantiteReel(new BigDecimal(CreerOrdreFabricationMP.this.quantite.getText()));
                        CreerOrdreFabricationMP.productionMP.setUtilisateurCreation(AuthentificationView.utilisateur);
                        CreerOrdreFabricationMP.productionMP.setDateProduction(dateFabrication);
                        CreerOrdreFabricationMP.productionMP.setMagasinProd(magasinProd);
                        CreerOrdreFabricationMP.productionMP.setMagasinStockage(magasinStock);
                        CreerOrdreFabricationMP.productionMP.setArticlesMP(CreerOrdreFabricationMP.this.articlemp);
                        CreerOrdreFabricationMP.this.productionMPDAO.add(CreerOrdreFabricationMP.productionMP);
                        Utils.incrementerValeurSequenceur(CreerOrdreFabricationMP.this.magasinPordMap.get(CreerOrdreFabricationMP.this.comboMagasinProd.getSelectedItem()).getDepot().getCode());
                        JOptionPane.showMessageDialog(null, "Ordre de Fabrication cr\u00e9e avec succ\u00e8s!", "Attention", 1);
                        CreerOrdreFabricationMP.this.btnImprimer.setVisible(true);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Cet Ordre de Fabrication est d\u00e9j\u00e0 cr\u00e9e !", "Attention", 1);
                }
            }
        });
        this.btnAjouter.setFont(new Font("Tahoma", 0, 11));
        (this.btnInitialiser = new JButton("Initialiser")).setBounds(421, 459, 102, 23);
        this.add(this.btnInitialiser);
        this.btnInitialiser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                CreerOrdreFabricationMP.this.intialiser();
            }
        });
        this.btnInitialiser.setIcon(this.imgInit);
        this.btnInitialiser.setFont(new Font("Tahoma", 0, 11));
        this.table.setSortable(false);
        this.table.setShowVerticalLines(false);
        this.table.setSelectionBackground(new Color(51, 204, 255));
        this.table.setRowHeightEnabled(true);
        this.table.setBackground(new Color(255, 255, 255));
        this.table.setHighlighters(new Highlighter[] { HighlighterFactory.createSimpleStriping() });
        this.table.setColumnControlVisible(true);
        this.table.setForeground(Color.BLACK);
        this.table.setGridColor(new Color(0, 0, 255));
        this.table.setAutoCreateRowSorter(true);
        this.table.setBounds(2, 27, 411, 198);
        this.table.setRowHeight(20);
        final DefaultCellEditor ce = (DefaultCellEditor)this.table.getDefaultEditor((Class)Object.class);
        final JTextComponent textField = (JTextComponent)ce.getComponent();
        Utils.copycollercell(textField);
        final JScrollPane scrollPane = new JScrollPane((Component)this.table);
        scrollPane.setBounds(9, 95, 1229, 176);
        this.add(scrollPane);
        scrollPane.setBorder(new EtchedBorder(1, null, null));
        final JXTitledSeparator titledSeparator = new JXTitledSeparator();
        titledSeparator.setTitle("Liste Mati\u00e8res Premi\u00e8res ");
        titledSeparator.setBounds(9, 68, 782, 30);
        this.add((Component)titledSeparator);
        final JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBorder(new EtchedBorder(1, null, null));
        layeredPane.setBounds(10, 272, 1228, 163);
        this.add(layeredPane);
        Utils.copycoller(this.txtNumOF = new JTextField());
        this.txtNumOF.setBounds(78, 25, 144, 26);
        layeredPane.add(this.txtNumOF);
        this.txtNumOF.setColumns(10);
        final JLabel lblNumOF = new JLabel("Num\u00e9ro OF");
        lblNumOF.setBounds(10, 26, 89, 24);
        layeredPane.add(lblNumOF);
        (this.lblDescriptionOf = new JLabel("Description OF")).setBounds(251, 26, 89, 24);
        layeredPane.add(this.lblDescriptionOf);
        Utils.copycoller(this.txtDescription = new JTextField());
        this.txtDescription.setColumns(10);
        this.txtDescription.setBounds(337, 28, 320, 26);
        layeredPane.add(this.txtDescription);
        (this.label = new JLabel("D\u00e9pot")).setFont(new Font("Tahoma", 0, 12));
        this.label.setBounds(258, 75, 49, 24);
        layeredPane.add(this.label);
        this.comboDepotProd.setBounds(317, 76, 166, 24);
        layeredPane.add(this.comboDepotProd);
        (this.lblMagasinProd = new JLabel("Magasin Prod")).setFont(new Font("Tahoma", 0, 12));
        this.lblMagasinProd.setBounds(494, 75, 89, 24);
        layeredPane.add(this.lblMagasinProd);
        this.comboMagasinProd.setBounds(581, 76, 220, 24);
        layeredPane.add(this.comboMagasinProd);
        (this.datfabrication = new JDateChooser()).setLocale(Locale.FRANCE);
        this.datfabrication.setDateFormatString("dd/MM/yyyy");
        this.datfabrication.setBounds(100, 75, 144, 26);
        layeredPane.add((Component)this.datfabrication);
        final JLabel lblDateFabrication = new JLabel("Date Fabrication");
        lblDateFabrication.setBounds(10, 75, 100, 26);
        layeredPane.add(lblDateFabrication);
        this.comboDepot.setBounds(817, 13, 166, 24);
        this.add(this.comboDepot);
        if (!this.utilisateur.getCodeDepot().equals("SIEGE")) {
            final Depot depot = this.depotDAO.findByCode(this.utilisateur.getCodeDepot());
            this.depotMap.put(depot.getLibelle(), depot);
            this.comboDepot.addItem(depot.getLibelle());
            this.comboDepotProd.addItem(depot.getLibelle());
        }
        else {
            this.listDepot = this.depotDAO.findAll();
            for (int i = 0; i < this.listDepot.size(); ++i) {
                final Depot depot = this.listDepot.get(i);
                this.depotMap.put(depot.getLibelle(), depot);
                this.comboDepot.addItem(depot.getLibelle());
                this.comboDepotProd.addItem(depot.getLibelle());
            }
        }
        final JLabel lblDpot = new JLabel("D\u00e9pot");
        lblDpot.setBounds(759, 12, 48, 24);
        this.add(lblDpot);
        lblDpot.setFont(new Font("Tahoma", 0, 12));
        this.comboMagasin.setBounds(1047, 12, 191, 24);
        this.add(this.comboMagasin);
        final JLabel lblMagasin = new JLabel("Magasin");
        lblMagasin.setBounds(993, 12, 77, 24);
        this.add(lblMagasin);
        lblMagasin.setFont(new Font("Tahoma", 0, 12));
        final JButton btnValiderBonSortieMP = new JButton("Valider Bon Sortie MP");
        btnValiderBonSortieMP.setBounds(729, 458, 167, 24);
        this.add(btnValiderBonSortieMP);
        btnValiderBonSortieMP.setIcon(this.imgImprimer);
        final JButton button = new JButton("Activer / Desactiver MP");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                final JFrame popupJFrame = new ActiverDesactiverMPEstimationCarton();
                popupJFrame.setVisible(true);
            }
        });
        button.setFont(new Font("Tahoma", 0, 11));
        button.setBounds(1257, 14, 145, 23);
        this.add(button);
        btnValiderBonSortieMP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (CreerOrdreFabricationMP.productionMP.getId() > 0) {
                    List<CoutProdMP> listCoutProdMP = new ArrayList<CoutProdMP>();
                    final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    final String date = dateFormat.format(CreerOrdreFabricationMP.productionMP.getDateProduction());
                    listCoutProdMP = CreerOrdreFabricationMP.this.productionMPDAO.listeCoutProdMP(CreerOrdreFabricationMP.productionMP.getId());
                    final Map parameters = new HashMap();
                    parameters.put("numOF", CreerOrdreFabricationMP.productionMP.getNumOFMP());
                    parameters.put("magasin", CreerOrdreFabricationMP.productionMP.getMagasinProd().getLibelle());
                    parameters.put("dateProd", date);
                    
                }
                else {
                    JOptionPane.showMessageDialog(null, "Il faut Cr\u00e9er OF avant d'imprimer ", "Erreur Impression", 0);
                }
            }
        });
        this.comboDepot.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(final ItemEvent e) {
                if (e.getStateChange() == 1) {
                    List<Magasin> listMagasin = new ArrayList<Magasin>();
                    CreerOrdreFabricationMP.this.comboMagasin.removeAllItems();
                    final Depot depot = CreerOrdreFabricationMP.this.depotMap.get(CreerOrdreFabricationMP.this.comboDepot.getSelectedItem());
                    listMagasin = CreerOrdreFabricationMP.this.depotDAO.listeMagasinByTypeMagasinDepotMachine(depot.getId(), "MP", "STK");
                    if (listMagasin != null) {
                        for (int j = 0; j < listMagasin.size(); ++j) {
                            final Magasin magasin = listMagasin.get(j);
                            CreerOrdreFabricationMP.this.comboMagasin.addItem(magasin.getLibelle());
                            CreerOrdreFabricationMP.this.magasinMap.put(magasin.getLibelle(), magasin);
                        }
                    }
                }
            }
        });
    }
    
    void intialiser() {
        this.quantite.setText("");
        this.codeArticleMP.setText("");
        this.categorieMP.setSelectedItem("");
    }
    
    void intialiserTableau() {
        this.modeleMP = new DefaultTableModel(new Object[0][], new String[] { "Code", "Nom Mati\u00e8re Premi\u00e8re", "Quantit\u00e9 Calcul\u00e9e", "Quantit\u00e9 Existante", "Quantit\u00e9 Manquante", "Quantit\u00e9 A Charg\u00e9e" }) {
            boolean[] columnEditables = { false, false, false, false, false, true };
            
            @Override
            public boolean isCellEditable(final int row, final int column) {
                return this.columnEditables[column];
            }
        };
        this.table.setModel((TableModel)this.modeleMP);
        this.table.getColumnModel().getColumn(0).setPreferredWidth(60);
        this.table.getColumnModel().getColumn(1).setPreferredWidth(260);
        this.table.getColumnModel().getColumn(2).setPreferredWidth(160);
        this.table.getColumnModel().getColumn(3).setPreferredWidth(160);
        this.table.getColumnModel().getColumn(4).setPreferredWidth(160);
        this.table.getColumnModel().getColumn(5).setPreferredWidth(160);
    }
    
    boolean afficherTableMatierePremiere(final List<DetailEstimationMP> lisDetailEstimationMP) {
        try {
        	
        	
            this.creerOF = true;
            BigDecimal prix_unitaire = BigDecimal.ZERO;
            BigDecimal quantiteExistante = BigDecimal.ZERO;
            BigDecimal quantiteACharge = BigDecimal.ZERO;
            final BigDecimal quantiteAChargeTHE = BigDecimal.ZERO;
            BigDecimal quantiteTotal = BigDecimal.ZERO;
            BigDecimal quantiteTotalCarton = BigDecimal.ZERO;
            BigDecimal quantiteManqaunte = BigDecimal.ZERO;
            BigDecimal quantiteResterCarton = BigDecimal.ZERO;
            BigDecimal quantiteStock = BigDecimal.ZERO;
            this.intialiserTableau();
            this.listCoutProdMP = new ArrayList<CoutProdMP>();
          
            this.mapQauntiteMatierePremier = new HashMap<Integer, StockMP>();
         
            int priorite = 0;
            StockMP stockMPQauantiteManquante = new StockMP();
            CoutProdMP coutProdMP = new CoutProdMP();
            final Magasin magasinStockage = this.magasinMap.get(this.comboMagasin.getSelectedItem());
            final Magasin magasinProd = this.magasinPordMap.get(this.comboMagasinProd.getSelectedItem());
            this.intialiserTableau();
            boolean carton = false;
            this.listEtatStockMPAfficherMagasinProduction.clear();
            this.listEtatStockMPAfficherMagasinStockage.clear();
            final Date dateDebutPrevue = this.datfabrication.getDate();
            this.CalculerStockFinale(magasinProd, dateDebutPrevue);
            this.listEtatStockMPAfficherMagasinProduction.addAll(this.listEtatStockMPAfficher);
            this.CalculerStockFinale(magasinStockage, dateDebutPrevue);
            this.listEtatStockMPAfficherMagasinStockage.addAll(this.listEtatStockMPAfficher);
            for (int i = 0; i < lisDetailEstimationMP.size(); ++i) {
                quantiteStock = BigDecimal.ZERO;
                carton = false;
                coutProdMP = new CoutProdMP();
                stockMPQauantiteManquante = new StockMP();
                final DetailEstimationMP detailEstimationMP = lisDetailEstimationMP.get(i);
                quantiteTotal = detailEstimationMP.getQuantite().multiply(new BigDecimal(this.quantite.getText()));
                
                for (int d = 0; d < this.listEtatStockMPAfficherMagasinProduction.size(); ++d) {
                    if (this.listEtatStockMPAfficherMagasinProduction.get(d).getMp().getId() == detailEstimationMP.getMatierePremier().getId() && this.listEtatStockMPAfficherMagasinProduction.get(d).getFournisseurMP() == null) {
                        quantiteExistante = this.listEtatStockMPAfficherMagasinProduction.get(d).getQuantiteFinale();
                    }
                }
                for (int d = 0; d < this.listEtatStockMPAfficherMagasinStockage.size(); ++d) {
                    if (this.listEtatStockMPAfficherMagasinStockage.get(d).getMp().getId() == detailEstimationMP.getMatierePremier().getId() && this.listEtatStockMPAfficherMagasinStockage.get(d).getFournisseurMP() == null) {
                        quantiteStock = this.listEtatStockMPAfficherMagasinStockage.get(d).getQuantiteFinale();
                    }
                }
                if (detailEstimationMP.getMatierePremier().getPrixByYear( DateUtils.getAnnee(datfabrication.getDate())) != null) {
                    if (detailEstimationMP.getMatierePremier().getPrixByYear( DateUtils.getAnnee(datfabrication.getDate())).compareTo(BigDecimal.ZERO) != 0) {
                        prix_unitaire = detailEstimationMP.getMatierePremier().getPrixByYear( DateUtils.getAnnee(datfabrication.getDate()));
                    }
                    else {
                        prix_unitaire = BigDecimal.ZERO;
                    }
                }
                else {
                    prix_unitaire = BigDecimal.ZERO;
                }
                quantiteACharge = quantiteTotal.subtract(quantiteExistante);
                if (detailEstimationMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals("CA001")) {
                    ++priorite;
                    for (int j = 0; j < lisDetailEstimationMP.size(); ++j) {
                        if (lisDetailEstimationMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals("CA001") && lisDetailEstimationMP.get(j).getPriorite() == priorite) {
                            if (priorite == 1) {
                                quantiteTotalCarton = lisDetailEstimationMP.get(j).getQuantite().multiply(new BigDecimal(this.quantite.getText()));
                                
                                for (int d2 = 0; d2 < this.listEtatStockMPAfficherMagasinProduction.size(); ++d2) {
                                    if (this.listEtatStockMPAfficherMagasinProduction.get(d2).getMp().getId() == lisDetailEstimationMP.get(j).getMatierePremier().getId() && this.listEtatStockMPAfficherMagasinProduction.get(d2).getFournisseurMP() == null) {
                                        quantiteExistante = this.listEtatStockMPAfficherMagasinProduction.get(d2).getQuantiteFinale();
                                    }
                                }
                                for (int d2 = 0; d2 < this.listEtatStockMPAfficherMagasinStockage.size(); ++d2) {
                                    if (this.listEtatStockMPAfficherMagasinStockage.get(d2).getMp().getId() == lisDetailEstimationMP.get(j).getMatierePremier().getId() && this.listEtatStockMPAfficherMagasinStockage.get(d2).getFournisseurMP() == null) {
                                        quantiteStock = this.listEtatStockMPAfficherMagasinStockage.get(d2).getQuantiteFinale();
                                    }
                                }
                                if (detailEstimationMP.getMatierePremier().getPrixByYear( DateUtils.getAnnee(datfabrication.getDate())) != null) {
                                    if (detailEstimationMP.getMatierePremier().getPrixByYear( DateUtils.getAnnee(datfabrication.getDate())).compareTo(BigDecimal.ZERO) != 0) {
                                        prix_unitaire = detailEstimationMP.getMatierePremier().getPrixByYear( DateUtils.getAnnee(datfabrication.getDate()));
                                    }
                                    else {
                                        prix_unitaire = BigDecimal.ZERO;
                                    }
                                }
                                else {
                                    prix_unitaire = BigDecimal.ZERO;
                                }
                                quantiteACharge = quantiteTotalCarton.subtract(quantiteExistante);
                                if (quantiteACharge.compareTo(BigDecimal.ZERO) < 0) {
                                    quantiteACharge = BigDecimal.ZERO;
                                }
                                
                                    if (quantiteStock.compareTo(quantiteACharge) >= 0) {
                                        quantiteResterCarton = quantiteStock.subtract(quantiteACharge);
                                        coutProdMP.setMatierePremier(lisDetailEstimationMP.get(j).getMatierePremier());
                                        coutProdMP.setPrixUnitaire(prix_unitaire);
                                        coutProdMP.setQuantite(quantiteACharge);
                                        coutProdMP.setQuantExistante(quantiteExistante);
                                        coutProdMP.setQuantEstime(quantiteACharge);
                                        coutProdMP.setProdcutionCM(CreerOrdreFabricationMP.productionMP);
                                        this.listCoutProdMP.add(coutProdMP);
                                        final Object[] ligne = { coutProdMP.getMatierePremier().getCode(), coutProdMP.getMatierePremier().getNom(), String.valueOf(NumberFormat.getNumberInstance(Locale.FRANCE).format(quantiteTotal.setScale(6))) + " " + coutProdMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(), NumberFormat.getNumberInstance(Locale.FRANCE).format(coutProdMP.getQuantExistante().setScale(6)), NumberFormat.getNumberInstance(Locale.FRANCE).format(coutProdMP.getQuantEstime().setScale(6)), "" };
                                        this.modeleMP.addRow(ligne);
                                    }
                                    else {
                                        quantiteResterCarton = quantiteStock.subtract(quantiteACharge);
                                        if (quantiteStock.compareTo(BigDecimal.ZERO) != 0) {
                                            coutProdMP.setMatierePremier(lisDetailEstimationMP.get(j).getMatierePremier());
                                            coutProdMP.setPrixUnitaire(prix_unitaire);
                                            coutProdMP.setQuantite(quantiteStock);
                                            coutProdMP.setQuantExistante(quantiteExistante);
                                            coutProdMP.setQuantEstime(quantiteStock);
                                            coutProdMP.setProdcutionCM(CreerOrdreFabricationMP.productionMP);
                                            this.listCoutProdMP.add(coutProdMP);
                                            final Object[] ligne = { coutProdMP.getMatierePremier().getCode(), coutProdMP.getMatierePremier().getNom(), String.valueOf(NumberFormat.getNumberInstance(Locale.FRANCE).format(quantiteTotal.setScale(6))) + " " + coutProdMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(), NumberFormat.getNumberInstance(Locale.FRANCE).format(coutProdMP.getQuantExistante().setScale(6)), NumberFormat.getNumberInstance(Locale.FRANCE).format(coutProdMP.getQuantEstime().setScale(6)), "" };
                                            this.modeleMP.addRow(ligne);
                                        }
                                    }
                                
                            }
                            else if (quantiteResterCarton.compareTo(BigDecimal.ZERO) < 0) {
                                quantiteResterCarton = quantiteResterCarton.multiply(new BigDecimal(-1));
                                quantiteTotalCarton = lisDetailEstimationMP.get(j).getQuantite().multiply(new BigDecimal(this.quantite.getText()));
                                
                                for (int d2 = 0; d2 < this.listEtatStockMPAfficherMagasinProduction.size(); ++d2) {
                                    if (this.listEtatStockMPAfficherMagasinProduction.get(d2).getMp().getId() == lisDetailEstimationMP.get(j).getMatierePremier().getId() && this.listEtatStockMPAfficherMagasinProduction.get(d2).getFournisseurMP() == null) {
                                        quantiteExistante = this.listEtatStockMPAfficherMagasinProduction.get(d2).getQuantiteFinale();
                                    }
                                }
                                for (int d2 = 0; d2 < this.listEtatStockMPAfficherMagasinStockage.size(); ++d2) {
                                    if (this.listEtatStockMPAfficherMagasinStockage.get(d2).getMp().getId() == lisDetailEstimationMP.get(j).getMatierePremier().getId() && this.listEtatStockMPAfficherMagasinStockage.get(d2).getFournisseurMP() == null) {
                                        quantiteStock = this.listEtatStockMPAfficherMagasinStockage.get(d2).getQuantiteFinale();
                                    }
                                }
                                if (detailEstimationMP.getMatierePremier().getPrixByYear( DateUtils.getAnnee(datfabrication.getDate())) != null) {
                                    if (detailEstimationMP.getMatierePremier().getPrixByYear( DateUtils.getAnnee(datfabrication.getDate())).compareTo(BigDecimal.ZERO) != 0) {
                                        prix_unitaire = detailEstimationMP.getMatierePremier().getPrixByYear( DateUtils.getAnnee(datfabrication.getDate()));
                                    }
                                    else {
                                        prix_unitaire = BigDecimal.ZERO;
                                    }
                                }
                                else {
                                    prix_unitaire = BigDecimal.ZERO;
                                }
                                quantiteACharge = quantiteResterCarton.subtract(quantiteExistante);
                                if (quantiteACharge.compareTo(BigDecimal.ZERO) < 0) {
                                    quantiteACharge = BigDecimal.ZERO;
                                }
                                
                                    if (quantiteStock.compareTo(quantiteACharge) >= 0) {
                                        quantiteResterCarton = quantiteStock.subtract(quantiteACharge);
                                        coutProdMP.setMatierePremier(lisDetailEstimationMP.get(j).getMatierePremier());
                                        coutProdMP.setPrixUnitaire(prix_unitaire);
                                        coutProdMP.setQuantite(quantiteACharge);
                                        coutProdMP.setQuantExistante(quantiteExistante);
                                        coutProdMP.setQuantEstime(quantiteACharge);
                                        coutProdMP.setProdcutionCM(CreerOrdreFabricationMP.productionMP);
                                        this.listCoutProdMP.add(coutProdMP);
                                        final Object[] ligne = { coutProdMP.getMatierePremier().getCode(), coutProdMP.getMatierePremier().getNom(), String.valueOf(NumberFormat.getNumberInstance(Locale.FRANCE).format(quantiteTotal.setScale(6))) + " " + coutProdMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(), NumberFormat.getNumberInstance(Locale.FRANCE).format(coutProdMP.getQuantExistante().setScale(6)), NumberFormat.getNumberInstance(Locale.FRANCE).format(coutProdMP.getQuantEstime().setScale(6)), "" };
                                        this.modeleMP.addRow(ligne);
                                    }
                                    else {
                                        quantiteResterCarton = quantiteStock.subtract(quantiteACharge);
                                        if (quantiteStock.compareTo(BigDecimal.ZERO) != 0) {
                                            coutProdMP.setMatierePremier(lisDetailEstimationMP.get(j).getMatierePremier());
                                            coutProdMP.setPrixUnitaire(prix_unitaire);
                                            coutProdMP.setQuantite(quantiteStock);
                                            coutProdMP.setQuantExistante(quantiteExistante);
                                            coutProdMP.setQuantEstime(quantiteStock);
                                            coutProdMP.setProdcutionCM(CreerOrdreFabricationMP.productionMP);
                                            this.listCoutProdMP.add(coutProdMP);
                                            final Object[] ligne = { coutProdMP.getMatierePremier().getCode(), coutProdMP.getMatierePremier().getNom(), String.valueOf(NumberFormat.getNumberInstance(Locale.FRANCE).format(quantiteTotal.setScale(6))) + " " + coutProdMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(), NumberFormat.getNumberInstance(Locale.FRANCE).format(coutProdMP.getQuantExistante().setScale(6)), NumberFormat.getNumberInstance(Locale.FRANCE).format(coutProdMP.getQuantEstime().setScale(6)), "" };
                                            this.modeleMP.addRow(ligne);
                                        }
                                    }
                                
                            }
                        }
                    }
                }
                else {
                    if (quantiteACharge.compareTo(BigDecimal.ZERO) < 0) {
                        quantiteACharge = BigDecimal.ZERO;
                    }
                    
                        if (quantiteStock.compareTo(quantiteACharge) >= 0) {
                            coutProdMP.setMatierePremier(detailEstimationMP.getMatierePremier());
                            coutProdMP.setPrixUnitaire(prix_unitaire);
                            coutProdMP.setQuantite(quantiteTotal);
                            coutProdMP.setQuantExistante(quantiteExistante);
                            coutProdMP.setQuantEstime(quantiteACharge);
                            coutProdMP.setProdcutionCM(CreerOrdreFabricationMP.productionMP);
                            this.listCoutProdMP.add(coutProdMP);
                            final Object[] ligne2 = { coutProdMP.getMatierePremier().getCode(), coutProdMP.getMatierePremier().getNom(), String.valueOf(NumberFormat.getNumberInstance(Locale.FRANCE).format(quantiteTotal.setScale(6))) + " " + coutProdMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(), NumberFormat.getNumberInstance(Locale.FRANCE).format(coutProdMP.getQuantExistante().setScale(6)), NumberFormat.getNumberInstance(Locale.FRANCE).format(coutProdMP.getQuantEstime().setScale(6)), "" };
                            this.modeleMP.addRow(ligne2);
                        }
                        else if (quantiteACharge.compareTo(quantiteStock) > 0) {
                            quantiteManqaunte = quantiteACharge.subtract(quantiteStock);
                            stockMPQauantiteManquante.setMatierePremier(detailEstimationMP.getMatierePremier());
                            stockMPQauantiteManquante.setStock(quantiteManqaunte);
                            stockMPQauantiteManquante.setMagasin(magasinStockage);
                            this.creerOF = false;
                            this.nomMP = String.valueOf(this.nomMP) + "-" + detailEstimationMP.getMatierePremier().getNom() + ":" + quantiteManqaunte + "\n";
                        }
                     
                }
            }
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "la Quantite doit etre en chiffre SVP !!!!");
        }
        return this.creerOF;
    }
    
    boolean afficherTableMatierePremiereCreerOF(final List<CoutProdMP> listCoutProdMP) {
        this.creerOF = true;
        if (!this.remplirMapQuantiteCharge()) {
            this.creerOF = false;
            JOptionPane.showMessageDialog(null, "Il faut remplir toutes les quantit\u00e9 avant de cr\u00e9er l'OF", "Erreur", 0);
        }
        else {
            BigDecimal prix_unitaire = BigDecimal.ZERO;
            BigDecimal quantiteExistante = BigDecimal.ZERO;
            BigDecimal quantiteACharge = BigDecimal.ZERO;
            final BigDecimal quantiteAChargeTHE = BigDecimal.ZERO;
            BigDecimal quantiteTotal = BigDecimal.ZERO;
            BigDecimal quantiteManqaunte = BigDecimal.ZERO;
            BigDecimal quantiteStock = BigDecimal.ZERO;
            this.intialiserTableau();
            final List listCoutProdMPTmp = new ArrayList();
            
            this.mapQauntiteMatierePremier = new HashMap<Integer, StockMP>();
           
            StockMP stockMPQauantiteManquante = new StockMP();
            CoutProdMP coutProdMP = new CoutProdMP();
            final Magasin magasinStockage = this.magasinMap.get(this.comboMagasin.getSelectedItem());
            final Magasin magasinProd = this.magasinPordMap.get(this.comboMagasinProd.getSelectedItem());
            final Date dateDebutPrevue = this.datfabrication.getDate();
            this.CalculerStockFinale(magasinProd, dateDebutPrevue);
            this.listEtatStockMPAfficherMagasinProduction.addAll(this.listEtatStockMPAfficher);
            this.CalculerStockFinale(magasinStockage, dateDebutPrevue);
            this.listEtatStockMPAfficherMagasinStockage.addAll(this.listEtatStockMPAfficher);
            int j = 0;
            for (int i = 0; i < listCoutProdMP.size(); ++i) {
                quantiteStock = BigDecimal.ZERO;
                quantiteExistante = BigDecimal.ZERO;
                coutProdMP = listCoutProdMP.get(i);
                stockMPQauantiteManquante = new StockMP();
                quantiteTotal = this.mapQuantiteMP.get(coutProdMP.getMatierePremier().getCode());
                 
                for (int d = 0; d < this.listEtatStockMPAfficherMagasinProduction.size(); ++d) {
                    if (this.listEtatStockMPAfficherMagasinProduction.get(d).getMp().getId() == coutProdMP.getMatierePremier().getId() && this.listEtatStockMPAfficherMagasinProduction.get(d).getFournisseurMP() == null) {
                        quantiteExistante = this.listEtatStockMPAfficherMagasinProduction.get(d).getQuantiteFinale();
                    }
                }
                for (int d = 0; d < this.listEtatStockMPAfficherMagasinStockage.size(); ++d) {
                    System.out.println(String.valueOf(this.listEtatStockMPAfficherMagasinStockage.get(d).getMp().getCode()) + " *** " + this.listEtatStockMPAfficherMagasinStockage.get(d).getQuantiteFinale());
                    if (this.listEtatStockMPAfficherMagasinStockage.get(d).getMp().getId() == coutProdMP.getMatierePremier().getId() && this.listEtatStockMPAfficherMagasinStockage.get(d).getFournisseurMP() == null) {
                        if (this.listEtatStockMPAfficherMagasinStockage.get(d).getMp().getCode().equals("MP_955")) {
                            System.out.println("MP_955");
                        }
                        quantiteStock = this.listEtatStockMPAfficherMagasinStockage.get(d).getQuantiteFinale();
                    }
                }
                prix_unitaire = listCoutProdMP.get(i).getMatierePremier().getPrixByYear( DateUtils.getAnnee(datfabrication.getDate()));
                quantiteACharge = quantiteTotal.subtract(quantiteExistante);
                if (quantiteACharge.compareTo(BigDecimal.ZERO) < 0) {
                    quantiteACharge = BigDecimal.ZERO;
                }
                if ( quantiteStock.compareTo(quantiteACharge) >= 0) {
                    coutProdMP.setQuantCharge(quantiteACharge);
                    coutProdMP.setProdcutionCM(CreerOrdreFabricationMP.productionMP);
                    listCoutProdMPTmp.add(coutProdMP);
                    final Object[] ligne = { coutProdMP.getMatierePremier().getCode(), coutProdMP.getMatierePremier().getNom(), coutProdMP.getQuantite().setScale(6) + " " + coutProdMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(), coutProdMP.getQuantExistante().setScale(6), coutProdMP.getQuantEstime().setScale(6), quantiteTotal.setScale(6) };
                    this.modeleMP.addRow(ligne);
                }
                else if (  quantiteACharge.compareTo(quantiteStock) > 0) {
                    quantiteManqaunte = quantiteACharge.subtract(quantiteStock);
                    stockMPQauantiteManquante.setMatierePremier(coutProdMP.getMatierePremier());
                    stockMPQauantiteManquante.setStock(quantiteManqaunte);
                    stockMPQauantiteManquante.setMagasin(magasinStockage);
                    this.creerOF = false;
                    this.nomMP = String.valueOf(this.nomMP) + "-" + coutProdMP.getMatierePremier().getNom() + ":" + quantiteManqaunte + "\n";
                    this.mapQauntiteMatierePremier.put(j, stockMPQauantiteManquante);
                    ++j;
                }
            }
            CreerOrdreFabricationMP.productionMP.setListCoutProdMP(listCoutProdMPTmp);
        }
        return this.creerOF;
    }
    
    void compterProduction(final Date dateProd, final String periode) {
        int compteur = 0;
        CompteurProduction compteurProduction = this.compteurProductionDAO.findByDateProdPeriode(dateProd, periode);
        if (compteurProduction != null) {
            compteur = compteurProduction.getCompteur() + 1;
            compteurProduction.setCompteur(compteur);
            this.compteurProductionDAO.edit(compteurProduction);
        }
        else {
            compteurProduction = new CompteurProduction();
            compteurProduction.setDateProd(dateProd);
            compteurProduction.setPeriode(periode);
            compteurProduction.setCompteur(1);
            this.compteurProductionDAO.add(compteurProduction);
        }
    }
    
    boolean remplirMapQuantiteCharge() {
        boolean trouve = false;
        int i = 0;
        int j = 0;
        final BigDecimal quantite = BigDecimal.ZERO;
        for (j = 0; j < this.table.getRowCount(); ++j) {
            if (this.table.getValueAt(j, 5).toString() != null && !this.table.getValueAt(j, 5).toString().equals("")) {
                this.mapQuantiteMP.put(this.table.getValueAt(j, 0).toString(), new BigDecimal(this.table.getValueAt(j, 5).toString()));
                ++i;
            }
        }
        if (i >= j) {
            trouve = true;
        }
        return trouve;
    }
    
    List<CoutMP> remplirQuantiteChargeMP(final List<CoutMP> listCoutMP) {
        BigDecimal quantite = BigDecimal.ZERO;
        final List<CoutMP> listCoutMPTmp = new ArrayList<CoutMP>();
        for (int i = 0; i < listCoutMP.size(); ++i) {
            final CoutMP coutMP = listCoutMP.get(i);
            quantite = this.mapQuantiteMP.get(coutMP.getMatierePremier().getCode());
            coutMP.setQuantCharge(quantite);
            listCoutMPTmp.add(coutMP);
        }
        return listCoutMPTmp;
    }
    
    private static String registerMailBody() {
        return "<HTML><b>OF N° :</b>" + CreerOrdreFabricationMP.productionMP.getNumOFMP() + "<br><br>" + "<br><b>Les d\u00e9tails de l'OF :</b><br>" + "<LI><b>ArticleMP : " + CreerOrdreFabricationMP.productionMP.getArticlesMP().getLiblle() + "</b></LI><br>" + "<LI><b>Code Article : " + CreerOrdreFabricationMP.productionMP.getArticlesMP().getCodeArticle() + "</b></LI><br>" + "<LI><b>Quantit\u00e9  : " + CreerOrdreFabricationMP.productionMP.getQuantiteReel() + "</b></LI><br>" + "<LI><b>Depot Stockage  : " + CreerOrdreFabricationMP.productionMP.getMagasinStockage().getDepot().getLibelle() + "</b></LI><br>" + "<LI><b>Magasin Stockage  : " + CreerOrdreFabricationMP.productionMP.getMagasinStockage().getLibelle() + "</b></LI><br>" + "<LI><b>Depot Production  : " + CreerOrdreFabricationMP.productionMP.getMagasinProd().getDepot().getLibelle() + "</b></LI><br>" + "<LI><b>Magasin Production  : " + CreerOrdreFabricationMP.productionMP.getMagasinProd().getLibelle() + "</b></LI><br>" + "Merci pour votre confiance<br>" + "Service Informatique<br>" + "Syst\u00e8me Production</HTML>";
    }
    
    public void CalculerStockFinale(final Magasin magasin, final Date dat) {
        final SubCategorieMp subCategorieMp = null;
        final CategorieMp categorieMp = null;
        final MatierePremier mp = null;
        final FournisseurMP fournisseurMP = null;
        Date mindate = null;
        this.Mindate = this.detailTransferStockMPDAO.MinDate(magasin);
        for (int i = 0; i < this.Mindate.size(); ++i) {
            final Object[] object = this.Mindate.get(i);
            if (this.Mindate.get(i) != null) {
                mindate = (Date)object[0];
            }
        }
        final String patternYear = "yyyy";
        final String patternDate = "yyyy-MM-dd";
        final SimpleDateFormat simpleDateFormatyear = new SimpleDateFormat(patternYear);
        final SimpleDateFormat simpleDateFormatDate = new SimpleDateFormat(patternDate);
        if (mindate != null) {
            final String date = simpleDateFormatDate.format(mindate);
            try {
                mindate = simpleDateFormatDate.parse(date);
            }
            catch (ParseException e1) {
                e1.printStackTrace();
            }
        }
        else {
            final String year = simpleDateFormatyear.format(dat);
            try {
                mindate = simpleDateFormatDate.parse(String.valueOf(year) + "-01-01");
            }
            catch (ParseException e1) {
                e1.printStackTrace();
            }
        }
        final SubCategorieMp subCategorieMpthe = this.SubCategorieMPDAO.findByCode("TH001");
        this.listeObjectStockInitialGroupByMP = this.detailTransferStockMPDAO.listeStockInitialMPByMagasinBySubCategorieByCategorieBayMPNonThe(mindate, magasin, subCategorieMpthe, null, null);
        this.listeObjectEtatStockGroupByMP = this.detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPNonThe(mindate, dat, magasin, subCategorieMpthe, null, null);
        this.listeEtatStockTransfertEnAttenteNonThe = this.detailTransferStockMPDAO.listeEtatStockMPTransfertEnAttenteNonThe(mindate, dat, magasin, subCategorieMpthe, null, null);
        this.listeObjectStockInitialGroupByMPByFournisseur = this.detailTransferStockMPDAO.listeStockInitialMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseur(mindate, magasin, subCategorieMpthe, null, null);
        this.listeEtatStockTransfertEnAttenteThe = this.detailTransferStockMPDAO.listeEtatStockMPTransfertEnAttenteThe(mindate, dat, magasin, subCategorieMpthe, null, null);
        this.listeObjectEtatStockGroupByMPByFournisseurReception = this.detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurReception(mindate, dat, magasin, subCategorieMpthe, null, null);
        this.listeObjectEtatStockGroupByMPByFournisseurEntrer = this.detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurEntrer(mindate, dat, magasin, subCategorieMpthe, null, null);
        this.listeObjectEtatStockGroupByMPByFournisseurSortie = this.detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurSortie(mindate, dat, magasin, subCategorieMpthe, null, null);
        this.listeObjectEtatStockGroupByMPByFournisseurCharger = this.detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurCharger(mindate, dat, magasin, subCategorieMpthe, null, null);
        this.listeObjectEtatStockGroupByMPByFournisseurRetour = this.detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurRetour(mindate, dat, magasin, subCategorieMpthe, null, null);
        this.listeObjectEtatStockGroupByMPByFournisseurAutreSortie = this.detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortie(mindate, dat, magasin, subCategorieMpthe, null, null);
        this.listeObjectEtatStockGroupByMPByFournisseurResterMachine = this.detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurResterMachine(mindate, dat, magasin, subCategorieMpthe, null, null);
        this.listeObjectEtatStockGroupByMPByFournisseurFabrique = this.detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurFabriquer(mindate, dat, magasin, subCategorieMpthe, null, null);
        this.listeObjectEtatStockGroupByMPByFournisseurExistante = this.detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurExistante(mindate, dat, magasin, subCategorieMpthe, null, null);
        this.listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortiePF = this.detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortiePF(mindate, dat, magasin, subCategorieMpthe, null, null);
        this.listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortieEnAttent = this.detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortieEnAttente(mindate, dat, magasin, subCategorieMpthe, null, null);
        this.listeObjectEtatStockGroupByMPByFournisseurAutreSortiePerte = this.detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortiePerte(mindate, dat, magasin, subCategorieMpthe, null, null);
        this.listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetour = this.detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieRetour(mindate, dat, magasin, subCategorieMpthe, null, null);
        this.listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetourFournisseurAnnule = this.detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieRETOURFOURNISSEURANNULER(mindate, dat, magasin, subCategorieMpthe, null, null);
        this.listEtatStockMP.clear();
        this.listEtatStockMPAfficher.clear();
        this.CalculerStockMPThe();
        this.CalculerStockMPNonThe();
        for (int j = 0; j < this.listEtatStockMP.size(); ++j) {
            final EtatStockMP etatStockMP = this.listEtatStockMP.get(j);
            if (subCategorieMp != null && categorieMp == null && mp == null && fournisseurMP == null) {
                if (etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId() == subCategorieMp.getId()) {
                    this.listEtatStockMPAfficher.add(etatStockMP);
                }
            }
            else if (subCategorieMp != null && categorieMp != null && mp == null && fournisseurMP == null) {
                if (etatStockMP.getMp().getCategorieMp().getId() == categorieMp.getId() && etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId() == subCategorieMp.getId()) {
                    this.listEtatStockMPAfficher.add(etatStockMP);
                }
            }
            else if (categorieMp != null && subCategorieMp != null && mp != null && fournisseurMP == null) {
                if (etatStockMP.getMp().getCategorieMp().getId() == categorieMp.getId() && etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId() == subCategorieMp.getId() && etatStockMP.getMp().getId() == mp.getId()) {
                    this.listEtatStockMPAfficher.add(etatStockMP);
                }
            }
            else if (categorieMp != null && subCategorieMp != null && mp != null && fournisseurMP != null) {
                if (etatStockMP.getMp().getCategorieMp().getId() == categorieMp.getId() && etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId() == subCategorieMp.getId() && etatStockMP.getMp().getId() == mp.getId() && etatStockMP.getFournisseurMP().getId() == fournisseurMP.getId()) {
                    this.listEtatStockMPAfficher.add(etatStockMP);
                }
            }
            else if (subCategorieMp != null && categorieMp == null && mp == null && fournisseurMP != null) {
                if (etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId() == subCategorieMp.getId() && etatStockMP.getFournisseurMP().getId() == fournisseurMP.getId()) {
                    this.listEtatStockMPAfficher.add(etatStockMP);
                }
            }
            else if (categorieMp != null && subCategorieMp != null && mp == null && fournisseurMP != null) {
                if (etatStockMP.getMp().getCategorieMp().getId() == categorieMp.getId() && etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId() == subCategorieMp.getId() && etatStockMP.getFournisseurMP().getId() == fournisseurMP.getId()) {
                    this.listEtatStockMPAfficher.add(etatStockMP);
                }
            }
            else {
                this.listEtatStockMPAfficher.add(etatStockMP);
            }
        }
    }
    
    public void CalculerStockMPThe() {
        for (int i = 0; i < this.listeObjectStockInitialGroupByMPByFournisseur.size(); ++i) {
            final Object[] object = this.listeObjectStockInitialGroupByMPByFournisseur.get(i);
            final EtatStockMP etatStockMP = new EtatStockMP();
            final MatierePremier mp = (MatierePremier)object[0];
            final FournisseurMP fournisseurMP = (FournisseurMP)object[1];
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
            this.listEtatStockMP.add(etatStockMP);
        }
        boolean existe = false;
        for (int j = 0; j < this.listeObjectEtatStockGroupByMPByFournisseurReception.size(); ++j) {
            existe = false;
            final Object[] object2 = this.listeObjectEtatStockGroupByMPByFournisseurReception.get(j);
            final MatierePremier mp = (MatierePremier)object2[0];
            final FournisseurMP fournisseurMP = (FournisseurMP)object2[1];
            for (int k = 0; k < this.listEtatStockMP.size(); ++k) {
                if (this.listEtatStockMP.get(k).getMp().getId() == mp.getId() && this.listEtatStockMP.get(k).getFournisseurMP().getId() == fournisseurMP.getId()) {
                    existe = true;
                    final EtatStockMP etatStockMP2 = this.listEtatStockMP.get(k);
                    if (object2[2] != null) {
                        etatStockMP2.setQuantiteReception(etatStockMP2.getQuantiteReception().add((BigDecimal)object2[2]));
                    }
                    etatStockMP2.setQuantiteFinale(etatStockMP2.getQuantiteInitial().add(etatStockMP2.getQuantiteReception().add(etatStockMP2.getQuantiteRetour()).add(etatStockMP2.getTransferEntrer().add(etatStockMP2.getQuantiteResterMachine().add(etatStockMP2.getQuantiteFabriquer())))).subtract(etatStockMP2.getQuantiteCharger().add(etatStockMP2.getQuantiteChargerSupp().add(etatStockMP2.getQuantiteAutreSortie()).add(etatStockMP2.getTransferSortie().add(etatStockMP2.getQuantiteExistante())))));
                    this.listEtatStockMP.set(k, etatStockMP2);
                }
            }
            if (!existe) {
                final EtatStockMP etatStockMP3 = new EtatStockMP();
                etatStockMP3.setMp(mp);
                etatStockMP3.setFournisseurMP(fournisseurMP);
                etatStockMP3.setQuantiteInitial(BigDecimal.ZERO);
                if (object2[2] != null) {
                    etatStockMP3.setQuantiteReception((BigDecimal)object2[2]);
                }
                else {
                    etatStockMP3.setQuantiteReception(BigDecimal.ZERO);
                }
                etatStockMP3.setTransferEntrer(BigDecimal.ZERO);
                etatStockMP3.setTransferSortie(BigDecimal.ZERO);
                etatStockMP3.setQuantiteCharger(BigDecimal.ZERO);
                etatStockMP3.setQuantiteChargerSupp(BigDecimal.ZERO);
                etatStockMP3.setQuantiteRetour(BigDecimal.ZERO);
                etatStockMP3.setQuantiteAutreSortie(BigDecimal.ZERO);
                etatStockMP3.setQuantiteResterMachine(BigDecimal.ZERO);
                etatStockMP3.setQuantiteFabriquer(BigDecimal.ZERO);
                etatStockMP3.setQuantiteExistante(BigDecimal.ZERO);
                etatStockMP3.setQuantiteFinale(etatStockMP3.getQuantiteInitial().add(etatStockMP3.getQuantiteReception().add(etatStockMP3.getQuantiteRetour()).add(etatStockMP3.getTransferEntrer().add(etatStockMP3.getQuantiteResterMachine().add(etatStockMP3.getQuantiteFabriquer())))).subtract(etatStockMP3.getQuantiteCharger().add(etatStockMP3.getQuantiteChargerSupp().add(etatStockMP3.getQuantiteAutreSortie()).add(etatStockMP3.getTransferSortie().add(etatStockMP3.getQuantiteExistante())))));
                this.listEtatStockMP.add(etatStockMP3);
            }
        }
        existe = false;
        BigDecimal enter = BigDecimal.ZERO;
        BigDecimal transfert = BigDecimal.ZERO;
        for (int l = 0; l < this.listeObjectEtatStockGroupByMPByFournisseurEntrer.size(); ++l) {
            existe = false;
            final Object[] object3 = this.listeObjectEtatStockGroupByMPByFournisseurEntrer.get(l);
            final MatierePremier mp2 = (MatierePremier)object3[0];
            final FournisseurMP fournisseurMP2 = (FournisseurMP)object3[1];
            enter = BigDecimal.ZERO;
            transfert = BigDecimal.ZERO;
            for (int m = 0; m < this.listEtatStockMP.size(); ++m) {
                enter = BigDecimal.ZERO;
                transfert = BigDecimal.ZERO;
                if (this.listEtatStockMP.get(m).getMp().getId() == mp2.getId() && this.listEtatStockMP.get(m).getFournisseurMP().getId() == fournisseurMP2.getId()) {
                    existe = true;
                    final EtatStockMP etatStockMP4 = this.listEtatStockMP.get(m);
                    if (object3[2] != null) {
                        enter = (BigDecimal)object3[2];
                    }
                    if (object3[3] != null) {
                        transfert = (BigDecimal)object3[3];
                    }
                    etatStockMP4.setTransferEntrer(etatStockMP4.getTransferEntrer().add(enter.add(transfert)));
                    etatStockMP4.setQuantiteFinale(etatStockMP4.getQuantiteInitial().add(etatStockMP4.getQuantiteReception().add(etatStockMP4.getQuantiteRetour()).add(etatStockMP4.getTransferEntrer().add(etatStockMP4.getQuantiteResterMachine().add(etatStockMP4.getQuantiteFabriquer())))).subtract(etatStockMP4.getQuantiteCharger().add(etatStockMP4.getQuantiteChargerSupp().add(etatStockMP4.getQuantiteAutreSortie()).add(etatStockMP4.getTransferSortie().add(etatStockMP4.getQuantiteExistante())))));
                    this.listEtatStockMP.set(m, etatStockMP4);
                }
            }
            if (!existe) {
                final EtatStockMP etatStockMP5 = new EtatStockMP();
                etatStockMP5.setMp(mp2);
                etatStockMP5.setFournisseurMP(fournisseurMP2);
                etatStockMP5.setQuantiteInitial(BigDecimal.ZERO);
                etatStockMP5.setQuantiteReception(BigDecimal.ZERO);
                if (object3[2] != null) {
                    enter = (BigDecimal)object3[2];
                }
                if (object3[3] != null) {
                    transfert = (BigDecimal)object3[3];
                }
                etatStockMP5.setTransferEntrer(enter.add(transfert));
                etatStockMP5.setTransferSortie(BigDecimal.ZERO);
                etatStockMP5.setQuantiteCharger(BigDecimal.ZERO);
                etatStockMP5.setQuantiteChargerSupp(BigDecimal.ZERO);
                etatStockMP5.setQuantiteRetour(BigDecimal.ZERO);
                etatStockMP5.setQuantiteAutreSortie(BigDecimal.ZERO);
                etatStockMP5.setQuantiteResterMachine(BigDecimal.ZERO);
                etatStockMP5.setQuantiteFabriquer(BigDecimal.ZERO);
                etatStockMP5.setQuantiteExistante(BigDecimal.ZERO);
                etatStockMP5.setQuantiteFinale(etatStockMP5.getQuantiteInitial().add(etatStockMP5.getQuantiteReception().add(etatStockMP5.getQuantiteRetour()).add(etatStockMP5.getTransferEntrer().add(etatStockMP5.getQuantiteResterMachine().add(etatStockMP5.getQuantiteFabriquer())))).subtract(etatStockMP5.getQuantiteCharger().add(etatStockMP5.getQuantiteChargerSupp().add(etatStockMP5.getQuantiteAutreSortie()).add(etatStockMP5.getTransferSortie().add(etatStockMP5.getQuantiteExistante())))));
                this.listEtatStockMP.add(etatStockMP5);
            }
        }
        existe = false;
        BigDecimal sortie = BigDecimal.ZERO;
        transfert = BigDecimal.ZERO;
        for (int j2 = 0; j2 < this.listeObjectEtatStockGroupByMPByFournisseurSortie.size(); ++j2) {
            existe = false;
            sortie = BigDecimal.ZERO;
            transfert = BigDecimal.ZERO;
            final Object[] object4 = this.listeObjectEtatStockGroupByMPByFournisseurSortie.get(j2);
            final MatierePremier mp3 = (MatierePremier)object4[0];
            final FournisseurMP fournisseurMP3 = (FournisseurMP)object4[1];
            for (int k2 = 0; k2 < this.listEtatStockMP.size(); ++k2) {
                sortie = BigDecimal.ZERO;
                transfert = BigDecimal.ZERO;
                if (this.listEtatStockMP.get(k2).getMp().getId() == mp3.getId() && this.listEtatStockMP.get(k2).getFournisseurMP().getId() == fournisseurMP3.getId()) {
                    existe = true;
                    final EtatStockMP etatStockMP6 = this.listEtatStockMP.get(k2);
                    if (object4[2] != null) {
                        sortie = (BigDecimal)object4[2];
                    }
                    if (object4[3] != null) {
                        transfert = (BigDecimal)object4[3];
                    }
                    etatStockMP6.setTransferSortie(etatStockMP6.getTransferSortie().add(sortie.add(transfert)));
                    etatStockMP6.setQuantiteFinale(etatStockMP6.getQuantiteInitial().add(etatStockMP6.getQuantiteReception().add(etatStockMP6.getQuantiteRetour()).add(etatStockMP6.getTransferEntrer().add(etatStockMP6.getQuantiteResterMachine().add(etatStockMP6.getQuantiteFabriquer())))).subtract(etatStockMP6.getQuantiteCharger().add(etatStockMP6.getQuantiteChargerSupp().add(etatStockMP6.getQuantiteAutreSortie()).add(etatStockMP6.getTransferSortie().add(etatStockMP6.getQuantiteExistante())))));
                    this.listEtatStockMP.set(k2, etatStockMP6);
                }
            }
            if (!existe) {
                final EtatStockMP etatStockMP4 = new EtatStockMP();
                etatStockMP4.setMp(mp3);
                etatStockMP4.setFournisseurMP(fournisseurMP3);
                etatStockMP4.setQuantiteInitial(BigDecimal.ZERO);
                etatStockMP4.setQuantiteReception(BigDecimal.ZERO);
                etatStockMP4.setTransferEntrer(BigDecimal.ZERO);
                if (object4[2] != null) {
                    sortie = (BigDecimal)object4[2];
                }
                if (object4[3] != null) {
                    transfert = (BigDecimal)object4[3];
                }
                etatStockMP4.setTransferSortie(sortie.add(transfert));
                etatStockMP4.setQuantiteCharger(BigDecimal.ZERO);
                etatStockMP4.setQuantiteChargerSupp(BigDecimal.ZERO);
                etatStockMP4.setQuantiteRetour(BigDecimal.ZERO);
                etatStockMP4.setQuantiteAutreSortie(BigDecimal.ZERO);
                etatStockMP4.setQuantiteResterMachine(BigDecimal.ZERO);
                etatStockMP4.setQuantiteFabriquer(BigDecimal.ZERO);
                etatStockMP4.setQuantiteExistante(BigDecimal.ZERO);
                etatStockMP4.setQuantiteFinale(etatStockMP4.getQuantiteInitial().add(etatStockMP4.getQuantiteReception().add(etatStockMP4.getQuantiteRetour()).add(etatStockMP4.getTransferEntrer().add(etatStockMP4.getQuantiteResterMachine().add(etatStockMP4.getQuantiteFabriquer())))).subtract(etatStockMP4.getQuantiteCharger().add(etatStockMP4.getQuantiteChargerSupp().add(etatStockMP4.getQuantiteAutreSortie()).add(etatStockMP4.getTransferSortie().add(etatStockMP4.getQuantiteExistante())))));
                this.listEtatStockMP.add(etatStockMP4);
            }
        }
        existe = false;
        BigDecimal charge = BigDecimal.ZERO;
        BigDecimal chargesupp = BigDecimal.ZERO;
        for (int j3 = 0; j3 < this.listeObjectEtatStockGroupByMPByFournisseurCharger.size(); ++j3) {
            charge = BigDecimal.ZERO;
            chargesupp = BigDecimal.ZERO;
            existe = false;
            final Object[] object5 = this.listeObjectEtatStockGroupByMPByFournisseurCharger.get(j3);
            final MatierePremier mp4 = (MatierePremier)object5[0];
            final FournisseurMP fournisseurMP4 = (FournisseurMP)object5[1];
            for (int k3 = 0; k3 < this.listEtatStockMP.size(); ++k3) {
                charge = BigDecimal.ZERO;
                chargesupp = BigDecimal.ZERO;
                if (this.listEtatStockMP.get(k3).getMp().getId() == mp4.getId() && this.listEtatStockMP.get(k3).getFournisseurMP().getId() == fournisseurMP4.getId()) {
                    existe = true;
                    final EtatStockMP etatStockMP7 = this.listEtatStockMP.get(k3);
                    if (object5[2] != null) {
                        charge = (BigDecimal)object5[2];
                    }
                    etatStockMP7.setQuantiteCharger(etatStockMP7.getQuantiteCharger().add(charge));
                    if (object5[3] != null) {
                        chargesupp = (BigDecimal)object5[3];
                    }
                    etatStockMP7.setQuantiteChargerSupp(etatStockMP7.getQuantiteChargerSupp().add(chargesupp));
                    etatStockMP7.setQuantiteFinale(etatStockMP7.getQuantiteInitial().add(etatStockMP7.getQuantiteReception().add(etatStockMP7.getQuantiteRetour()).add(etatStockMP7.getTransferEntrer().add(etatStockMP7.getQuantiteResterMachine().add(etatStockMP7.getQuantiteFabriquer())))).subtract(etatStockMP7.getQuantiteCharger().add(etatStockMP7.getQuantiteChargerSupp().add(etatStockMP7.getQuantiteAutreSortie()).add(etatStockMP7.getTransferSortie().add(etatStockMP7.getQuantiteExistante())))));
                    this.listEtatStockMP.set(k3, etatStockMP7);
                }
            }
            if (!existe) {
                final EtatStockMP etatStockMP8 = new EtatStockMP();
                etatStockMP8.setMp(mp4);
                etatStockMP8.setFournisseurMP(fournisseurMP4);
                etatStockMP8.setQuantiteInitial(BigDecimal.ZERO);
                etatStockMP8.setQuantiteReception(BigDecimal.ZERO);
                etatStockMP8.setTransferEntrer(BigDecimal.ZERO);
                etatStockMP8.setTransferSortie(BigDecimal.ZERO);
                if (object5[2] != null) {
                    charge = (BigDecimal)object5[2];
                }
                if (object5[3] != null) {
                    chargesupp = (BigDecimal)object5[3];
                }
                etatStockMP8.setQuantiteCharger(charge);
                etatStockMP8.setQuantiteChargerSupp(chargesupp);
                etatStockMP8.setQuantiteRetour(BigDecimal.ZERO);
                etatStockMP8.setQuantiteAutreSortie(BigDecimal.ZERO);
                etatStockMP8.setQuantiteResterMachine(BigDecimal.ZERO);
                etatStockMP8.setQuantiteFabriquer(BigDecimal.ZERO);
                etatStockMP8.setQuantiteExistante(BigDecimal.ZERO);
                etatStockMP8.setQuantiteFinale(etatStockMP8.getQuantiteInitial().add(etatStockMP8.getQuantiteReception().add(etatStockMP8.getQuantiteRetour()).add(etatStockMP8.getTransferEntrer().add(etatStockMP8.getQuantiteResterMachine().add(etatStockMP8.getQuantiteFabriquer())))).subtract(etatStockMP8.getQuantiteCharger().add(etatStockMP8.getQuantiteChargerSupp().add(etatStockMP8.getQuantiteAutreSortie()).add(etatStockMP8.getTransferSortie().add(etatStockMP8.getQuantiteExistante())))));
                this.listEtatStockMP.add(etatStockMP8);
            }
        }
        existe = false;
        BigDecimal retour = BigDecimal.ZERO;
        BigDecimal retourFournisseurAnnule = BigDecimal.ZERO;
        for (int j4 = 0; j4 < this.listeObjectEtatStockGroupByMPByFournisseurRetour.size(); ++j4) {
            retour = BigDecimal.ZERO;
            retourFournisseurAnnule = BigDecimal.ZERO;
            existe = false;
            final Object[] object6 = this.listeObjectEtatStockGroupByMPByFournisseurRetour.get(j4);
            final MatierePremier mp5 = (MatierePremier)object6[0];
            final FournisseurMP fournisseurMP5 = (FournisseurMP)object6[1];
            for (int k4 = 0; k4 < this.listEtatStockMP.size(); ++k4) {
                retour = BigDecimal.ZERO;
                if (this.listEtatStockMP.get(k4).getMp().getId() == mp5.getId() && this.listEtatStockMP.get(k4).getFournisseurMP().getId() == fournisseurMP5.getId()) {
                    existe = true;
                    final EtatStockMP etatStockMP9 = this.listEtatStockMP.get(k4);
                    if (object6[2] != null) {
                        retour = (BigDecimal)object6[2];
                    }
                    etatStockMP9.setQuantiteRetour(etatStockMP9.getQuantiteRetour().add(retour));
                    etatStockMP9.setQuantiteFinale(etatStockMP9.getQuantiteInitial().add(etatStockMP9.getQuantiteReception().add(etatStockMP9.getQuantiteRetour()).add(etatStockMP9.getTransferEntrer().add(etatStockMP9.getQuantiteResterMachine().add(etatStockMP9.getQuantiteFabriquer())))).subtract(etatStockMP9.getQuantiteCharger().add(etatStockMP9.getQuantiteChargerSupp().add(etatStockMP9.getQuantiteAutreSortie()).add(etatStockMP9.getTransferSortie().add(etatStockMP9.getQuantiteExistante())))));
                    this.listEtatStockMP.set(k4, etatStockMP9);
                }
            }
            if (!existe) {
                final EtatStockMP etatStockMP10 = new EtatStockMP();
                etatStockMP10.setMp(mp5);
                etatStockMP10.setFournisseurMP(fournisseurMP5);
                etatStockMP10.setQuantiteInitial(BigDecimal.ZERO);
                etatStockMP10.setQuantiteReception(BigDecimal.ZERO);
                etatStockMP10.setTransferEntrer(BigDecimal.ZERO);
                etatStockMP10.setTransferSortie(BigDecimal.ZERO);
                etatStockMP10.setQuantiteCharger(BigDecimal.ZERO);
                etatStockMP10.setQuantiteChargerSupp(BigDecimal.ZERO);
                if (object6[2] != null) {
                    retour = (BigDecimal)object6[2];
                }
                etatStockMP10.setQuantiteRetour(retour);
                etatStockMP10.setQuantiteAutreSortie(BigDecimal.ZERO);
                etatStockMP10.setQuantiteResterMachine(BigDecimal.ZERO);
                etatStockMP10.setQuantiteFabriquer(BigDecimal.ZERO);
                etatStockMP10.setQuantiteExistante(BigDecimal.ZERO);
                etatStockMP10.setQuantiteFinale(etatStockMP10.getQuantiteInitial().add(etatStockMP10.getQuantiteReception().add(etatStockMP10.getQuantiteRetour()).add(etatStockMP10.getTransferEntrer().add(etatStockMP10.getQuantiteResterMachine().add(etatStockMP10.getQuantiteFabriquer())))).subtract(etatStockMP10.getQuantiteCharger().add(etatStockMP10.getQuantiteChargerSupp().add(etatStockMP10.getQuantiteAutreSortie()).add(etatStockMP10.getTransferSortie().add(etatStockMP10.getQuantiteExistante())))));
                this.listEtatStockMP.add(etatStockMP10);
            }
        }
        existe = false;
        sortie = BigDecimal.ZERO;
        for (int j4 = 0; j4 < this.listeObjectEtatStockGroupByMPByFournisseurAutreSortie.size(); ++j4) {
            sortie = BigDecimal.ZERO;
            existe = false;
            final Object[] object6 = this.listeObjectEtatStockGroupByMPByFournisseurAutreSortie.get(j4);
            final MatierePremier mp5 = (MatierePremier)object6[0];
            final FournisseurMP fournisseurMP5 = (FournisseurMP)object6[1];
            for (int k4 = 0; k4 < this.listEtatStockMP.size(); ++k4) {
                sortie = BigDecimal.ZERO;
                if (this.listEtatStockMP.get(k4).getMp().getId() == mp5.getId() && this.listEtatStockMP.get(k4).getFournisseurMP().getId() == fournisseurMP5.getId()) {
                    existe = true;
                    final EtatStockMP etatStockMP9 = this.listEtatStockMP.get(k4);
                    if (object6[2] != null) {
                        sortie = (BigDecimal)object6[2];
                    }
                    etatStockMP9.setQuantiteAutreSortie(etatStockMP9.getQuantiteAutreSortie().add(sortie));
                    etatStockMP9.setQuantiteFinale(etatStockMP9.getQuantiteInitial().add(etatStockMP9.getQuantiteReception().add(etatStockMP9.getQuantiteRetour()).add(etatStockMP9.getTransferEntrer().add(etatStockMP9.getQuantiteResterMachine().add(etatStockMP9.getQuantiteFabriquer())))).subtract(etatStockMP9.getQuantiteCharger().add(etatStockMP9.getQuantiteChargerSupp().add(etatStockMP9.getQuantiteAutreSortie()).add(etatStockMP9.getTransferSortie().add(etatStockMP9.getQuantiteExistante())))));
                    this.listEtatStockMP.set(k4, etatStockMP9);
                }
            }
            if (!existe) {
                final EtatStockMP etatStockMP10 = new EtatStockMP();
                etatStockMP10.setMp(mp5);
                etatStockMP10.setFournisseurMP(fournisseurMP5);
                etatStockMP10.setQuantiteInitial(BigDecimal.ZERO);
                etatStockMP10.setQuantiteReception(BigDecimal.ZERO);
                etatStockMP10.setTransferEntrer(BigDecimal.ZERO);
                etatStockMP10.setTransferSortie(BigDecimal.ZERO);
                etatStockMP10.setQuantiteCharger(BigDecimal.ZERO);
                etatStockMP10.setQuantiteChargerSupp(BigDecimal.ZERO);
                etatStockMP10.setQuantiteRetour(BigDecimal.ZERO);
                if (etatStockMP10.getMp().getCode().equals("MP_1126") && etatStockMP10.getFournisseurMP().getId() == 10) {
                    System.out.println(etatStockMP10.getMp().getCode());
                }
                if (object6[2] != null) {
                    sortie = (BigDecimal)object6[2];
                }
                etatStockMP10.setQuantiteAutreSortie(sortie);
                etatStockMP10.setQuantiteResterMachine(BigDecimal.ZERO);
                etatStockMP10.setQuantiteFabriquer(BigDecimal.ZERO);
                etatStockMP10.setQuantiteExistante(BigDecimal.ZERO);
                etatStockMP10.setQuantiteFinale(etatStockMP10.getQuantiteInitial().add(etatStockMP10.getQuantiteReception().add(etatStockMP10.getQuantiteRetour()).add(etatStockMP10.getTransferEntrer().add(etatStockMP10.getQuantiteResterMachine().add(etatStockMP10.getQuantiteFabriquer())))).subtract(etatStockMP10.getQuantiteCharger().add(etatStockMP10.getQuantiteChargerSupp().add(etatStockMP10.getQuantiteAutreSortie()).add(etatStockMP10.getTransferSortie().add(etatStockMP10.getQuantiteExistante())))));
                this.listEtatStockMP.add(etatStockMP10);
            }
        }
        existe = false;
        BigDecimal sortiePF = BigDecimal.ZERO;
        for (int j5 = 0; j5 < this.listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortiePF.size(); ++j5) {
            sortiePF = BigDecimal.ZERO;
            existe = false;
            final Object[] object7 = this.listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortiePF.get(j5);
            final MatierePremier mp6 = (MatierePremier)object7[0];
            final FournisseurMP fournisseurMP6 = (FournisseurMP)object7[1];
            for (int k5 = 0; k5 < this.listEtatStockMP.size(); ++k5) {
                sortiePF = BigDecimal.ZERO;
                if (this.listEtatStockMP.get(k5).getMp().getId() == mp6.getId() && this.listEtatStockMP.get(k5).getFournisseurMP().getId() == fournisseurMP6.getId()) {
                    existe = true;
                    final EtatStockMP etatStockMP11 = this.listEtatStockMP.get(k5);
                    if (object7[2] != null) {
                        sortiePF = (BigDecimal)object7[2];
                    }
                    etatStockMP11.setQuantiteAutreSortie(etatStockMP11.getQuantiteAutreSortie().add(sortiePF));
                    etatStockMP11.setQuantiteFinale(etatStockMP11.getQuantiteInitial().add(etatStockMP11.getQuantiteReception().add(etatStockMP11.getQuantiteRetour()).add(etatStockMP11.getTransferEntrer().add(etatStockMP11.getQuantiteResterMachine().add(etatStockMP11.getQuantiteFabriquer())))).subtract(etatStockMP11.getQuantiteCharger().add(etatStockMP11.getQuantiteChargerSupp().add(etatStockMP11.getQuantiteAutreSortie()).add(etatStockMP11.getTransferSortie().add(etatStockMP11.getQuantiteExistante())))));
                    this.listEtatStockMP.set(k5, etatStockMP11);
                }
            }
            if (!existe) {
                final EtatStockMP etatStockMP9 = new EtatStockMP();
                etatStockMP9.setMp(mp6);
                etatStockMP9.setFournisseurMP(fournisseurMP6);
                etatStockMP9.setQuantiteInitial(BigDecimal.ZERO);
                etatStockMP9.setQuantiteReception(BigDecimal.ZERO);
                etatStockMP9.setTransferEntrer(BigDecimal.ZERO);
                etatStockMP9.setTransferSortie(BigDecimal.ZERO);
                etatStockMP9.setQuantiteCharger(BigDecimal.ZERO);
                etatStockMP9.setQuantiteChargerSupp(BigDecimal.ZERO);
                etatStockMP9.setQuantiteRetour(BigDecimal.ZERO);
                if (etatStockMP9.getMp().getCode().equals("MP_1126") && etatStockMP9.getFournisseurMP().getId() == 10) {
                    System.out.println(etatStockMP9.getMp().getCode());
                }
                if (object7[2] != null) {
                    sortiePF = (BigDecimal)object7[2];
                }
                etatStockMP9.setQuantiteAutreSortie(sortiePF);
                etatStockMP9.setQuantiteResterMachine(BigDecimal.ZERO);
                etatStockMP9.setQuantiteFabriquer(BigDecimal.ZERO);
                etatStockMP9.setQuantiteExistante(BigDecimal.ZERO);
                etatStockMP9.setQuantiteFinale(etatStockMP9.getQuantiteInitial().add(etatStockMP9.getQuantiteReception().add(etatStockMP9.getQuantiteRetour()).add(etatStockMP9.getTransferEntrer().add(etatStockMP9.getQuantiteResterMachine().add(etatStockMP9.getQuantiteFabriquer())))).subtract(etatStockMP9.getQuantiteCharger().add(etatStockMP9.getQuantiteChargerSupp().add(etatStockMP9.getQuantiteAutreSortie()).add(etatStockMP9.getTransferSortie().add(etatStockMP9.getQuantiteExistante())))));
                this.listEtatStockMP.add(etatStockMP9);
            }
        }
        existe = false;
        BigDecimal sortieEnAttente = BigDecimal.ZERO;
        for (int j6 = 0; j6 < this.listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortieEnAttent.size(); ++j6) {
            sortieEnAttente = BigDecimal.ZERO;
            existe = false;
            final Object[] object8 = this.listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortieEnAttent.get(j6);
            final MatierePremier mp7 = (MatierePremier)object8[0];
            final FournisseurMP fournisseurMP7 = (FournisseurMP)object8[1];
            for (int k6 = 0; k6 < this.listEtatStockMP.size(); ++k6) {
                sortieEnAttente = BigDecimal.ZERO;
                if (this.listEtatStockMP.get(k6).getMp().getId() == mp7.getId() && this.listEtatStockMP.get(k6).getFournisseurMP().getId() == fournisseurMP7.getId()) {
                    existe = true;
                    final EtatStockMP etatStockMP12 = this.listEtatStockMP.get(k6);
                    if (object8[2] != null) {
                        sortieEnAttente = (BigDecimal)object8[2];
                    }
                    etatStockMP12.setQuantiteAutreSortie(etatStockMP12.getQuantiteAutreSortie().add(sortieEnAttente));
                    etatStockMP12.setQuantiteFinale(etatStockMP12.getQuantiteInitial().add(etatStockMP12.getQuantiteReception().add(etatStockMP12.getQuantiteRetour()).add(etatStockMP12.getTransferEntrer().add(etatStockMP12.getQuantiteResterMachine().add(etatStockMP12.getQuantiteFabriquer())))).subtract(etatStockMP12.getQuantiteCharger().add(etatStockMP12.getQuantiteChargerSupp().add(etatStockMP12.getQuantiteAutreSortie()).add(etatStockMP12.getTransferSortie().add(etatStockMP12.getQuantiteExistante())))));
                    this.listEtatStockMP.set(k6, etatStockMP12);
                }
            }
            if (!existe) {
                final EtatStockMP etatStockMP11 = new EtatStockMP();
                etatStockMP11.setMp(mp7);
                etatStockMP11.setFournisseurMP(fournisseurMP7);
                etatStockMP11.setQuantiteInitial(BigDecimal.ZERO);
                etatStockMP11.setQuantiteReception(BigDecimal.ZERO);
                etatStockMP11.setTransferEntrer(BigDecimal.ZERO);
                etatStockMP11.setTransferSortie(BigDecimal.ZERO);
                etatStockMP11.setQuantiteCharger(BigDecimal.ZERO);
                etatStockMP11.setQuantiteChargerSupp(BigDecimal.ZERO);
                etatStockMP11.setQuantiteRetour(BigDecimal.ZERO);
                if (etatStockMP11.getMp().getCode().equals("MP_1126") && etatStockMP11.getFournisseurMP().getId() == 10) {
                    System.out.println(etatStockMP11.getMp().getCode());
                }
                if (object8[2] != null) {
                    sortieEnAttente = (BigDecimal)object8[2];
                }
                etatStockMP11.setQuantiteAutreSortie(sortieEnAttente);
                etatStockMP11.setQuantiteResterMachine(BigDecimal.ZERO);
                etatStockMP11.setQuantiteFabriquer(BigDecimal.ZERO);
                etatStockMP11.setQuantiteExistante(BigDecimal.ZERO);
                etatStockMP11.setQuantiteFinale(etatStockMP11.getQuantiteInitial().add(etatStockMP11.getQuantiteReception().add(etatStockMP11.getQuantiteRetour()).add(etatStockMP11.getTransferEntrer().add(etatStockMP11.getQuantiteResterMachine().add(etatStockMP11.getQuantiteFabriquer())))).subtract(etatStockMP11.getQuantiteCharger().add(etatStockMP11.getQuantiteChargerSupp().add(etatStockMP11.getQuantiteAutreSortie()).add(etatStockMP11.getTransferSortie().add(etatStockMP11.getQuantiteExistante())))));
                this.listEtatStockMP.add(etatStockMP11);
            }
        }
        existe = false;
        BigDecimal perte = BigDecimal.ZERO;
        for (int j7 = 0; j7 < this.listeObjectEtatStockGroupByMPByFournisseurAutreSortiePerte.size(); ++j7) {
            perte = BigDecimal.ZERO;
            existe = false;
            final Object[] object9 = this.listeObjectEtatStockGroupByMPByFournisseurAutreSortiePerte.get(j7);
            final MatierePremier mp8 = (MatierePremier)object9[0];
            final FournisseurMP fournisseurMP8 = (FournisseurMP)object9[1];
            for (int k7 = 0; k7 < this.listEtatStockMP.size(); ++k7) {
                perte = BigDecimal.ZERO;
                if (this.listEtatStockMP.get(k7).getMp().getId() == mp8.getId() && this.listEtatStockMP.get(k7).getFournisseurMP().getId() == fournisseurMP8.getId()) {
                    existe = true;
                    final EtatStockMP etatStockMP13 = this.listEtatStockMP.get(k7);
                    if (object9[2] != null) {
                        perte = (BigDecimal)object9[2];
                    }
                    etatStockMP13.setQuantiteAutreSortie(etatStockMP13.getQuantiteAutreSortie().add(perte));
                    etatStockMP13.setQuantiteFinale(etatStockMP13.getQuantiteInitial().add(etatStockMP13.getQuantiteReception().add(etatStockMP13.getQuantiteRetour()).add(etatStockMP13.getTransferEntrer().add(etatStockMP13.getQuantiteResterMachine().add(etatStockMP13.getQuantiteFabriquer())))).subtract(etatStockMP13.getQuantiteCharger().add(etatStockMP13.getQuantiteChargerSupp().add(etatStockMP13.getQuantiteAutreSortie()).add(etatStockMP13.getTransferSortie().add(etatStockMP13.getQuantiteExistante())))));
                    this.listEtatStockMP.set(k7, etatStockMP13);
                }
            }
            if (!existe) {
                final EtatStockMP etatStockMP12 = new EtatStockMP();
                etatStockMP12.setMp(mp8);
                etatStockMP12.setFournisseurMP(fournisseurMP8);
                etatStockMP12.setQuantiteInitial(BigDecimal.ZERO);
                etatStockMP12.setQuantiteReception(BigDecimal.ZERO);
                etatStockMP12.setTransferEntrer(BigDecimal.ZERO);
                etatStockMP12.setTransferSortie(BigDecimal.ZERO);
                etatStockMP12.setQuantiteCharger(BigDecimal.ZERO);
                etatStockMP12.setQuantiteChargerSupp(BigDecimal.ZERO);
                etatStockMP12.setQuantiteRetour(BigDecimal.ZERO);
                if (etatStockMP12.getMp().getCode().equals("MP_1126") && etatStockMP12.getFournisseurMP().getId() == 10) {
                    System.out.println(etatStockMP12.getMp().getCode());
                }
                if (object9[2] != null) {
                    perte = (BigDecimal)object9[2];
                }
                etatStockMP12.setQuantiteAutreSortie(perte);
                etatStockMP12.setQuantiteResterMachine(BigDecimal.ZERO);
                etatStockMP12.setQuantiteFabriquer(BigDecimal.ZERO);
                etatStockMP12.setQuantiteExistante(BigDecimal.ZERO);
                etatStockMP12.setQuantiteFinale(etatStockMP12.getQuantiteInitial().add(etatStockMP12.getQuantiteReception().add(etatStockMP12.getQuantiteRetour()).add(etatStockMP12.getTransferEntrer().add(etatStockMP12.getQuantiteResterMachine().add(etatStockMP12.getQuantiteFabriquer())))).subtract(etatStockMP12.getQuantiteCharger().add(etatStockMP12.getQuantiteChargerSupp().add(etatStockMP12.getQuantiteAutreSortie()).add(etatStockMP12.getTransferSortie().add(etatStockMP12.getQuantiteExistante())))));
                this.listEtatStockMP.add(etatStockMP12);
            }
        }
        existe = false;
        retour = BigDecimal.ZERO;
        for (int j7 = 0; j7 < this.listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetour.size(); ++j7) {
            retour = BigDecimal.ZERO;
            existe = false;
            final Object[] object9 = this.listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetour.get(j7);
            final MatierePremier mp8 = (MatierePremier)object9[0];
            final FournisseurMP fournisseurMP8 = (FournisseurMP)object9[1];
            for (int k7 = 0; k7 < this.listEtatStockMP.size(); ++k7) {
                retour = BigDecimal.ZERO;
                if (this.listEtatStockMP.get(k7).getMp().getId() == mp8.getId() && this.listEtatStockMP.get(k7).getFournisseurMP().getId() == fournisseurMP8.getId()) {
                    existe = true;
                    final EtatStockMP etatStockMP13 = this.listEtatStockMP.get(k7);
                    if (object9[2] != null) {
                        retour = (BigDecimal)object9[2];
                    }
                    etatStockMP13.setQuantiteAutreSortie(etatStockMP13.getQuantiteAutreSortie().add(retour));
                    etatStockMP13.setQuantiteFinale(etatStockMP13.getQuantiteInitial().add(etatStockMP13.getQuantiteReception().add(etatStockMP13.getQuantiteRetour()).add(etatStockMP13.getTransferEntrer().add(etatStockMP13.getQuantiteResterMachine().add(etatStockMP13.getQuantiteFabriquer())))).subtract(etatStockMP13.getQuantiteCharger().add(etatStockMP13.getQuantiteChargerSupp().add(etatStockMP13.getQuantiteAutreSortie()).add(etatStockMP13.getTransferSortie().add(etatStockMP13.getQuantiteExistante())))));
                    this.listEtatStockMP.set(k7, etatStockMP13);
                }
            }
            if (!existe) {
                final EtatStockMP etatStockMP12 = new EtatStockMP();
                etatStockMP12.setMp(mp8);
                etatStockMP12.setFournisseurMP(fournisseurMP8);
                etatStockMP12.setQuantiteInitial(BigDecimal.ZERO);
                etatStockMP12.setQuantiteReception(BigDecimal.ZERO);
                etatStockMP12.setTransferEntrer(BigDecimal.ZERO);
                etatStockMP12.setTransferSortie(BigDecimal.ZERO);
                etatStockMP12.setQuantiteCharger(BigDecimal.ZERO);
                etatStockMP12.setQuantiteChargerSupp(BigDecimal.ZERO);
                etatStockMP12.setQuantiteRetour(BigDecimal.ZERO);
                if (etatStockMP12.getMp().getCode().equals("MP_1126") && etatStockMP12.getFournisseurMP().getId() == 10) {
                    System.out.println(etatStockMP12.getMp().getCode());
                }
                if (object9[2] != null) {
                    retour = (BigDecimal)object9[2];
                }
                etatStockMP12.setQuantiteAutreSortie(retour);
                etatStockMP12.setQuantiteResterMachine(BigDecimal.ZERO);
                etatStockMP12.setQuantiteFabriquer(BigDecimal.ZERO);
                etatStockMP12.setQuantiteExistante(BigDecimal.ZERO);
                etatStockMP12.setQuantiteFinale(etatStockMP12.getQuantiteInitial().add(etatStockMP12.getQuantiteReception().add(etatStockMP12.getQuantiteRetour()).add(etatStockMP12.getTransferEntrer().add(etatStockMP12.getQuantiteResterMachine().add(etatStockMP12.getQuantiteFabriquer())))).subtract(etatStockMP12.getQuantiteCharger().add(etatStockMP12.getQuantiteChargerSupp().add(etatStockMP12.getQuantiteAutreSortie()).add(etatStockMP12.getTransferSortie().add(etatStockMP12.getQuantiteExistante())))));
                this.listEtatStockMP.add(etatStockMP12);
            }
        }
        existe = false;
        retourFournisseurAnnule = BigDecimal.ZERO;
        for (int j7 = 0; j7 < this.listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetourFournisseurAnnule.size(); ++j7) {
            retourFournisseurAnnule = BigDecimal.ZERO;
            existe = false;
            final Object[] object9 = this.listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetourFournisseurAnnule.get(j7);
            final MatierePremier mp8 = (MatierePremier)object9[0];
            final FournisseurMP fournisseurMP8 = (FournisseurMP)object9[1];
            for (int k7 = 0; k7 < this.listEtatStockMP.size(); ++k7) {
                retourFournisseurAnnule = BigDecimal.ZERO;
                if (this.listEtatStockMP.get(k7).getMp().getId() == mp8.getId() && this.listEtatStockMP.get(k7).getFournisseurMP().getId() == fournisseurMP8.getId()) {
                    existe = true;
                    final EtatStockMP etatStockMP13 = this.listEtatStockMP.get(k7);
                    if (object9[2] != null) {
                        retourFournisseurAnnule = (BigDecimal)object9[2];
                    }
                    etatStockMP13.setQuantiteAutreSortie(etatStockMP13.getQuantiteAutreSortie().add(retourFournisseurAnnule));
                    etatStockMP13.setQuantiteFinale(etatStockMP13.getQuantiteInitial().add(etatStockMP13.getQuantiteReception().add(etatStockMP13.getQuantiteRetour()).add(etatStockMP13.getTransferEntrer().add(etatStockMP13.getQuantiteResterMachine().add(etatStockMP13.getQuantiteFabriquer())))).subtract(etatStockMP13.getQuantiteCharger().add(etatStockMP13.getQuantiteChargerSupp().add(etatStockMP13.getQuantiteAutreSortie()).add(etatStockMP13.getTransferSortie().add(etatStockMP13.getQuantiteExistante())))));
                    this.listEtatStockMP.set(k7, etatStockMP13);
                }
            }
            if (!existe) {
                final EtatStockMP etatStockMP12 = new EtatStockMP();
                etatStockMP12.setMp(mp8);
                etatStockMP12.setFournisseurMP(fournisseurMP8);
                etatStockMP12.setQuantiteInitial(BigDecimal.ZERO);
                etatStockMP12.setQuantiteReception(BigDecimal.ZERO);
                etatStockMP12.setTransferEntrer(BigDecimal.ZERO);
                etatStockMP12.setTransferSortie(BigDecimal.ZERO);
                etatStockMP12.setQuantiteCharger(BigDecimal.ZERO);
                etatStockMP12.setQuantiteChargerSupp(BigDecimal.ZERO);
                etatStockMP12.setQuantiteRetour(BigDecimal.ZERO);
                if (etatStockMP12.getMp().getCode().equals("MP_1126") && etatStockMP12.getFournisseurMP().getId() == 10) {
                    System.out.println(etatStockMP12.getMp().getCode());
                }
                if (object9[2] != null) {
                    retourFournisseurAnnule = (BigDecimal)object9[2];
                }
                etatStockMP12.setQuantiteAutreSortie(retourFournisseurAnnule);
                etatStockMP12.setQuantiteResterMachine(BigDecimal.ZERO);
                etatStockMP12.setQuantiteFabriquer(BigDecimal.ZERO);
                etatStockMP12.setQuantiteExistante(BigDecimal.ZERO);
                etatStockMP12.setQuantiteFinale(etatStockMP12.getQuantiteInitial().add(etatStockMP12.getQuantiteReception().add(etatStockMP12.getQuantiteRetour()).add(etatStockMP12.getTransferEntrer().add(etatStockMP12.getQuantiteResterMachine().add(etatStockMP12.getQuantiteFabriquer())))).subtract(etatStockMP12.getQuantiteCharger().add(etatStockMP12.getQuantiteChargerSupp().add(etatStockMP12.getQuantiteAutreSortie()).add(etatStockMP12.getTransferSortie().add(etatStockMP12.getQuantiteExistante())))));
                this.listEtatStockMP.add(etatStockMP12);
            }
        }
        existe = false;
        BigDecimal resteMachine = BigDecimal.ZERO;
        for (int j8 = 0; j8 < this.listeObjectEtatStockGroupByMPByFournisseurResterMachine.size(); ++j8) {
            resteMachine = BigDecimal.ZERO;
            existe = false;
            final Object[] object10 = this.listeObjectEtatStockGroupByMPByFournisseurResterMachine.get(j8);
            final MatierePremier mp9 = (MatierePremier)object10[0];
            final FournisseurMP fournisseurMP9 = (FournisseurMP)object10[1];
            for (int k8 = 0; k8 < this.listEtatStockMP.size(); ++k8) {
                resteMachine = BigDecimal.ZERO;
                if (this.listEtatStockMP.get(k8).getMp().getId() == mp9.getId() && this.listEtatStockMP.get(k8).getFournisseurMP().getId() == fournisseurMP9.getId()) {
                    existe = true;
                    final EtatStockMP etatStockMP14 = this.listEtatStockMP.get(k8);
                    if (object10[2] != null) {
                        resteMachine = (BigDecimal)object10[2];
                    }
                    etatStockMP14.setQuantiteResterMachine(etatStockMP14.getQuantiteResterMachine().add(resteMachine));
                    etatStockMP14.setQuantiteFinale(etatStockMP14.getQuantiteInitial().add(etatStockMP14.getQuantiteReception().add(etatStockMP14.getQuantiteRetour()).add(etatStockMP14.getTransferEntrer().add(etatStockMP14.getQuantiteResterMachine().add(etatStockMP14.getQuantiteFabriquer())))).subtract(etatStockMP14.getQuantiteCharger().add(etatStockMP14.getQuantiteChargerSupp().add(etatStockMP14.getQuantiteAutreSortie()).add(etatStockMP14.getTransferSortie().add(etatStockMP14.getQuantiteExistante())))));
                    this.listEtatStockMP.set(k8, etatStockMP14);
                }
            }
            if (!existe) {
                final EtatStockMP etatStockMP13 = new EtatStockMP();
                etatStockMP13.setMp(mp9);
                etatStockMP13.setFournisseurMP(fournisseurMP9);
                etatStockMP13.setQuantiteInitial(BigDecimal.ZERO);
                etatStockMP13.setQuantiteReception(BigDecimal.ZERO);
                etatStockMP13.setTransferEntrer(BigDecimal.ZERO);
                etatStockMP13.setTransferSortie(BigDecimal.ZERO);
                etatStockMP13.setQuantiteCharger(BigDecimal.ZERO);
                etatStockMP13.setQuantiteChargerSupp(BigDecimal.ZERO);
                etatStockMP13.setQuantiteRetour(BigDecimal.ZERO);
                etatStockMP13.setQuantiteAutreSortie(BigDecimal.ZERO);
                if (object10[2] != null) {
                    resteMachine = (BigDecimal)object10[2];
                }
                etatStockMP13.setQuantiteResterMachine(resteMachine);
                etatStockMP13.setQuantiteFabriquer(BigDecimal.ZERO);
                etatStockMP13.setQuantiteExistante(BigDecimal.ZERO);
                etatStockMP13.setQuantiteFinale(etatStockMP13.getQuantiteInitial().add(etatStockMP13.getQuantiteReception().add(etatStockMP13.getQuantiteRetour()).add(etatStockMP13.getTransferEntrer().add(etatStockMP13.getQuantiteResterMachine().add(etatStockMP13.getQuantiteFabriquer())))).subtract(etatStockMP13.getQuantiteCharger().add(etatStockMP13.getQuantiteChargerSupp().add(etatStockMP13.getQuantiteAutreSortie()).add(etatStockMP13.getTransferSortie().add(etatStockMP13.getQuantiteExistante())))));
                this.listEtatStockMP.add(etatStockMP13);
            }
        }
        existe = false;
        BigDecimal fabriquer = BigDecimal.ZERO;
        for (int j9 = 0; j9 < this.listeObjectEtatStockGroupByMPByFournisseurFabrique.size(); ++j9) {
            fabriquer = BigDecimal.ZERO;
            existe = false;
            final Object[] object11 = this.listeObjectEtatStockGroupByMPByFournisseurFabrique.get(j9);
            final MatierePremier mp10 = (MatierePremier)object11[0];
            final FournisseurMP fournisseurMP10 = (FournisseurMP)object11[1];
            for (int k9 = 0; k9 < this.listEtatStockMP.size(); ++k9) {
                fabriquer = BigDecimal.ZERO;
                if (this.listEtatStockMP.get(k9).getMp().getId() == mp10.getId() && this.listEtatStockMP.get(k9).getFournisseurMP().getId() == fournisseurMP10.getId()) {
                    existe = true;
                    final EtatStockMP etatStockMP15 = this.listEtatStockMP.get(k9);
                    if (object11[2] != null) {
                        fabriquer = (BigDecimal)object11[2];
                    }
                    etatStockMP15.setQuantiteFabriquer(etatStockMP15.getQuantiteFabriquer().add(fabriquer));
                    etatStockMP15.setQuantiteFinale(etatStockMP15.getQuantiteInitial().add(etatStockMP15.getQuantiteReception().add(etatStockMP15.getQuantiteRetour()).add(etatStockMP15.getTransferEntrer().add(etatStockMP15.getQuantiteResterMachine().add(etatStockMP15.getQuantiteFabriquer())))).subtract(etatStockMP15.getQuantiteCharger().add(etatStockMP15.getQuantiteChargerSupp().add(etatStockMP15.getQuantiteAutreSortie()).add(etatStockMP15.getTransferSortie().add(etatStockMP15.getQuantiteExistante())))));
                    this.listEtatStockMP.set(k9, etatStockMP15);
                }
            }
            if (!existe) {
                final EtatStockMP etatStockMP14 = new EtatStockMP();
                etatStockMP14.setMp(mp10);
                etatStockMP14.setFournisseurMP(fournisseurMP10);
                etatStockMP14.setQuantiteInitial(BigDecimal.ZERO);
                etatStockMP14.setQuantiteReception(BigDecimal.ZERO);
                etatStockMP14.setTransferEntrer(BigDecimal.ZERO);
                etatStockMP14.setTransferSortie(BigDecimal.ZERO);
                etatStockMP14.setQuantiteCharger(BigDecimal.ZERO);
                etatStockMP14.setQuantiteChargerSupp(BigDecimal.ZERO);
                etatStockMP14.setQuantiteRetour(BigDecimal.ZERO);
                etatStockMP14.setQuantiteAutreSortie(BigDecimal.ZERO);
                etatStockMP14.setQuantiteResterMachine(BigDecimal.ZERO);
                if (object11[2] != null) {
                    fabriquer = (BigDecimal)object11[2];
                }
                etatStockMP14.setQuantiteFabriquer(fabriquer);
                etatStockMP14.setQuantiteExistante(BigDecimal.ZERO);
                etatStockMP14.setQuantiteFinale(etatStockMP14.getQuantiteInitial().add(etatStockMP14.getQuantiteReception().add(etatStockMP14.getQuantiteRetour()).add(etatStockMP14.getTransferEntrer().add(etatStockMP14.getQuantiteResterMachine().add(etatStockMP14.getQuantiteFabriquer())))).subtract(etatStockMP14.getQuantiteCharger().add(etatStockMP14.getQuantiteChargerSupp().add(etatStockMP14.getQuantiteAutreSortie()).add(etatStockMP14.getTransferSortie().add(etatStockMP14.getQuantiteExistante())))));
                this.listEtatStockMP.add(etatStockMP14);
            }
        }
        existe = false;
        BigDecimal existante = BigDecimal.ZERO;
        for (int j10 = 0; j10 < this.listeObjectEtatStockGroupByMPByFournisseurExistante.size(); ++j10) {
            existante = BigDecimal.ZERO;
            existe = false;
            final Object[] object12 = this.listeObjectEtatStockGroupByMPByFournisseurExistante.get(j10);
            final MatierePremier mp11 = (MatierePremier)object12[0];
            final FournisseurMP fournisseurMP11 = (FournisseurMP)object12[1];
            for (int k10 = 0; k10 < this.listEtatStockMP.size(); ++k10) {
                existante = BigDecimal.ZERO;
                if (this.listEtatStockMP.get(k10).getMp().getId() == mp11.getId() && this.listEtatStockMP.get(k10).getFournisseurMP().getId() == fournisseurMP11.getId()) {
                    existe = true;
                    final EtatStockMP etatStockMP16 = this.listEtatStockMP.get(k10);
                    if (object12[2] != null) {
                        existante = (BigDecimal)object12[2];
                    }
                    etatStockMP16.setQuantiteExistante(etatStockMP16.getQuantiteExistante().add(existante));
                    etatStockMP16.setQuantiteFinale(etatStockMP16.getQuantiteInitial().add(etatStockMP16.getQuantiteReception().add(etatStockMP16.getQuantiteRetour()).add(etatStockMP16.getTransferEntrer().add(etatStockMP16.getQuantiteResterMachine().add(etatStockMP16.getQuantiteFabriquer())))).subtract(etatStockMP16.getQuantiteCharger().add(etatStockMP16.getQuantiteChargerSupp().add(etatStockMP16.getQuantiteAutreSortie()).add(etatStockMP16.getTransferSortie().add(etatStockMP16.getQuantiteExistante())))));
                    this.listEtatStockMP.set(k10, etatStockMP16);
                }
            }
            if (!existe) {
                final EtatStockMP etatStockMP15 = new EtatStockMP();
                etatStockMP15.setMp(mp11);
                etatStockMP15.setFournisseurMP(fournisseurMP11);
                etatStockMP15.setQuantiteInitial(BigDecimal.ZERO);
                etatStockMP15.setQuantiteReception(BigDecimal.ZERO);
                etatStockMP15.setTransferEntrer(BigDecimal.ZERO);
                etatStockMP15.setTransferSortie(BigDecimal.ZERO);
                etatStockMP15.setQuantiteCharger(BigDecimal.ZERO);
                etatStockMP15.setQuantiteChargerSupp(BigDecimal.ZERO);
                etatStockMP15.setQuantiteRetour(BigDecimal.ZERO);
                etatStockMP15.setQuantiteAutreSortie(BigDecimal.ZERO);
                etatStockMP15.setQuantiteResterMachine(BigDecimal.ZERO);
                etatStockMP15.setQuantiteFabriquer(BigDecimal.ZERO);
                if (object12[2] != null) {
                    existante = (BigDecimal)object12[2];
                }
                etatStockMP15.setQuantiteExistante(existante);
                etatStockMP15.setQuantiteFinale(etatStockMP15.getQuantiteInitial().add(etatStockMP15.getQuantiteReception().add(etatStockMP15.getQuantiteRetour()).add(etatStockMP15.getTransferEntrer().add(etatStockMP15.getQuantiteResterMachine().add(etatStockMP15.getQuantiteFabriquer())))).subtract(etatStockMP15.getQuantiteCharger().add(etatStockMP15.getQuantiteChargerSupp().add(etatStockMP15.getQuantiteAutreSortie()).add(etatStockMP15.getTransferSortie().add(etatStockMP15.getQuantiteExistante())))));
                this.listEtatStockMP.add(etatStockMP15);
            }
        }
        boolean trouver = false;
        for (int j11 = 0; j11 < this.listeEtatStockTransfertEnAttenteThe.size(); ++j11) {
            trouver = false;
            final DetailTransferStockMP detailTransferStockMP = this.listeEtatStockTransfertEnAttenteThe.get(j11);
            for (int k9 = 0; k9 < this.listEtatStockMP.size(); ++k9) {
                if (this.listEtatStockMP.get(k9).getMp().getId() == detailTransferStockMP.getMatierePremier().getId() && this.listEtatStockMP.get(k9).getFournisseurMP().getId() == detailTransferStockMP.getFournisseur().getId() && detailTransferStockMP.getMagasinDestination().getCatMagasin().equals("PROD") && detailTransferStockMP.getMagasinSouce() != null && detailTransferStockMP.getMagasinSouce().getCatMagasin().equals("PROD")) {
                    trouver = true;
                    final EtatStockMP etatStockMP15 = this.listEtatStockMP.get(k9);
                    etatStockMP15.setQuantiteResterMachine(etatStockMP15.getQuantiteResterMachine().add(detailTransferStockMP.getQuantite()));
                    this.listEtatStockMP.set(k9, etatStockMP15);
                }
            }
            if (!trouver && detailTransferStockMP.getFournisseur() != null && detailTransferStockMP.getMagasinDestination().getCatMagasin().equals("PROD") && detailTransferStockMP.getMagasinSouce() != null && detailTransferStockMP.getMagasinSouce().getCatMagasin().equals("PROD")) {
                final EtatStockMP etatStockMP14 = new EtatStockMP();
                etatStockMP14.setMp(detailTransferStockMP.getMatierePremier());
                etatStockMP14.setFournisseurMP(detailTransferStockMP.getFournisseur());
                etatStockMP14.setQuantiteInitial(BigDecimal.ZERO);
                etatStockMP14.setQuantiteReception(BigDecimal.ZERO);
                etatStockMP14.setTransferEntrer(BigDecimal.ZERO);
                etatStockMP14.setTransferSortie(BigDecimal.ZERO);
                etatStockMP14.setQuantiteCharger(BigDecimal.ZERO);
                etatStockMP14.setQuantiteChargerSupp(BigDecimal.ZERO);
                etatStockMP14.setQuantiteRetour(BigDecimal.ZERO);
                etatStockMP14.setQuantiteAutreSortie(BigDecimal.ZERO);
                etatStockMP14.setQuantiteResterMachine(detailTransferStockMP.getQuantite());
                etatStockMP14.setQuantiteFabriquer(BigDecimal.ZERO);
                etatStockMP14.setQuantiteExistante(BigDecimal.ZERO);
                etatStockMP14.setQuantiteFinale(etatStockMP14.getQuantiteInitial().add(etatStockMP14.getQuantiteReception().add(etatStockMP14.getQuantiteRetour()).add(etatStockMP14.getTransferEntrer().add(etatStockMP14.getQuantiteResterMachine().add(etatStockMP14.getQuantiteFabriquer())))).subtract(etatStockMP14.getQuantiteCharger().add(etatStockMP14.getQuantiteChargerSupp().add(etatStockMP14.getQuantiteAutreSortie()).add(etatStockMP14.getTransferSortie().add(etatStockMP14.getQuantiteExistante())))));
                this.listEtatStockMP.add(etatStockMP14);
            }
        }
        for (int k7 = 0; k7 < this.listEtatStockMP.size(); ++k7) {
            final EtatStockMP etatStockMP13 = this.listEtatStockMP.get(k7);
            etatStockMP13.setQuantiteFinale(etatStockMP13.getQuantiteInitial().add(etatStockMP13.getQuantiteReception().add(etatStockMP13.getQuantiteRetour()).add(etatStockMP13.getTransferEntrer().add(etatStockMP13.getQuantiteResterMachine().add(etatStockMP13.getQuantiteFabriquer())))).subtract(etatStockMP13.getQuantiteCharger().add(etatStockMP13.getQuantiteChargerSupp().add(etatStockMP13.getQuantiteAutreSortie()).add(etatStockMP13.getTransferSortie()).add(etatStockMP13.getQuantiteExistante()))));
            if (etatStockMP13.getFournisseurMP() != null) {
                System.out.println(String.valueOf(etatStockMP13.getMp().getCode()) + " *** " + etatStockMP13.getFournisseurMP().getCodeFournisseur() + "****" + etatStockMP13.getQuantiteInitial() + " *** " + etatStockMP13.getQuantiteReception() + " *** " + etatStockMP13.getQuantiteRetour() + " *** " + etatStockMP13.getTransferEntrer() + " *** " + etatStockMP13.getQuantiteResterMachine() + " *** " + etatStockMP13.getQuantiteFabriquer() + " ---- " + etatStockMP13.getQuantiteCharger() + " *****" + etatStockMP13.getQuantiteChargerSupp() + " ***** " + etatStockMP13.getQuantiteAutreSortie() + " ***** " + etatStockMP13.getTransferSortie() + " ***** " + etatStockMP13.getQuantiteExistante());
            }
            this.listEtatStockMP.set(k7, etatStockMP13);
        }
    }
    
    public void CalculerStockMPNonThe() {
        for (int i = 0; i < this.listeObjectStockInitialGroupByMP.size(); ++i) {
            final Object[] object = this.listeObjectStockInitialGroupByMP.get(i);
            final EtatStockMP etatStockMP = new EtatStockMP();
            final MatierePremier mp = (MatierePremier)object[0];
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
            this.listEtatStockMP.add(etatStockMP);
        }
        boolean existe = false;
        for (int j = 0; j < this.listeObjectEtatStockGroupByMP.size(); ++j) {
            existe = false;
            final Object[] object2 = this.listeObjectEtatStockGroupByMP.get(j);
            final MatierePremier mp = (MatierePremier)object2[0];
            for (int k = 0; k < this.listEtatStockMP.size(); ++k) {
                if (this.listEtatStockMP.get(k).getMp().getId() == mp.getId() && this.listEtatStockMP.get(k).getFournisseurMP() == null) {
                    existe = true;
                    final EtatStockMP etatStockMP2 = this.listEtatStockMP.get(k);
                    if (etatStockMP2.getMp().getCode().equals("MP_703")) {
                        System.out.println(etatStockMP2.getMp().getCode());
                    }
                    if (object2[1] != null) {
                        etatStockMP2.setQuantiteReception((BigDecimal)object2[1]);
                    }
                    else {
                        etatStockMP2.setQuantiteReception(BigDecimal.ZERO);
                    }
                    if (((BigDecimal)object2[2]).add((BigDecimal)object2[3]) != null) {
                        etatStockMP2.setTransferEntrer(((BigDecimal)object2[2]).add((BigDecimal)object2[3]));
                    }
                    else {
                        etatStockMP2.setTransferEntrer(BigDecimal.ZERO);
                    }
                    if (((BigDecimal)object2[6]).add((BigDecimal)object2[7]) != null) {
                        etatStockMP2.setTransferSortie(((BigDecimal)object2[6]).add((BigDecimal)object2[7]));
                    }
                    else {
                        etatStockMP2.setTransferSortie(BigDecimal.ZERO);
                    }
                    if (object2[4] != null) {
                        etatStockMP2.setQuantiteCharger((BigDecimal)object2[4]);
                    }
                    else {
                        etatStockMP2.setQuantiteCharger(BigDecimal.ZERO);
                    }
                    if (object2[5] != null) {
                        etatStockMP2.setQuantiteChargerSupp((BigDecimal)object2[5]);
                    }
                    else {
                        etatStockMP2.setQuantiteChargerSupp(BigDecimal.ZERO);
                    }
                    if (object2[8] != null) {
                        etatStockMP2.setQuantiteRetour((BigDecimal)object2[8]);
                    }
                    else {
                        etatStockMP2.setQuantiteRetour(BigDecimal.ZERO);
                    }
                    if (((BigDecimal)object2[9]).add((BigDecimal)object2[13]).add((BigDecimal)object2[14]).add((BigDecimal)object2[15]).add((BigDecimal)object2[16]).add((BigDecimal)object2[17]) != null) {
                        etatStockMP2.setQuantiteAutreSortie(((BigDecimal)object2[9]).add((BigDecimal)object2[13]).add((BigDecimal)object2[14]).add((BigDecimal)object2[15]).add((BigDecimal)object2[16]).add((BigDecimal)object2[17]));
                    }
                    else {
                        etatStockMP2.setQuantiteAutreSortie(BigDecimal.ZERO);
                    }
                    if (object2[10] != null) {
                        etatStockMP2.setQuantiteResterMachine((BigDecimal)object2[10]);
                    }
                    else {
                        etatStockMP2.setQuantiteResterMachine(BigDecimal.ZERO);
                    }
                    if (object2[11] != null) {
                        etatStockMP2.setQuantiteFabriquer((BigDecimal)object2[11]);
                    }
                    else {
                        etatStockMP2.setQuantiteFabriquer(BigDecimal.ZERO);
                    }
                    if (object2[12] != null) {
                        etatStockMP2.setQuantiteExistante((BigDecimal)object2[12]);
                    }
                    else {
                        etatStockMP2.setQuantiteExistante(BigDecimal.ZERO);
                    }
                    etatStockMP2.setQuantiteFinale(etatStockMP2.getQuantiteInitial().add(etatStockMP2.getQuantiteReception().add(etatStockMP2.getQuantiteRetour()).add(etatStockMP2.getTransferEntrer().add(etatStockMP2.getQuantiteResterMachine().add(etatStockMP2.getQuantiteFabriquer())))).subtract(etatStockMP2.getQuantiteCharger().add(etatStockMP2.getQuantiteChargerSupp().add(etatStockMP2.getQuantiteAutreSortie()).add(etatStockMP2.getTransferSortie()).add(etatStockMP2.getQuantiteExistante()))));
                    this.listEtatStockMP.set(k, etatStockMP2);
                }
            }
            if (!existe) {
                final EtatStockMP etatStockMP3 = new EtatStockMP();
                etatStockMP3.setMp(mp);
                etatStockMP3.setQuantiteInitial(BigDecimal.ZERO);
                if (object2[1] != null) {
                    etatStockMP3.setQuantiteReception((BigDecimal)object2[1]);
                }
                else {
                    etatStockMP3.setQuantiteReception(BigDecimal.ZERO);
                }
                if (((BigDecimal)object2[2]).add((BigDecimal)object2[3]) != null) {
                    etatStockMP3.setTransferEntrer(((BigDecimal)object2[2]).add((BigDecimal)object2[3]));
                }
                else {
                    etatStockMP3.setTransferEntrer(BigDecimal.ZERO);
                }
                if (((BigDecimal)object2[6]).add((BigDecimal)object2[7]) != null) {
                    etatStockMP3.setTransferSortie(((BigDecimal)object2[6]).add((BigDecimal)object2[7]));
                }
                else {
                    etatStockMP3.setTransferSortie(BigDecimal.ZERO);
                }
                if (object2[4] != null) {
                    etatStockMP3.setQuantiteCharger((BigDecimal)object2[4]);
                }
                else {
                    etatStockMP3.setQuantiteCharger(BigDecimal.ZERO);
                }
                if (object2[5] != null) {
                    etatStockMP3.setQuantiteChargerSupp((BigDecimal)object2[5]);
                }
                else {
                    etatStockMP3.setQuantiteChargerSupp(BigDecimal.ZERO);
                }
                if (object2[8] != null) {
                    etatStockMP3.setQuantiteRetour((BigDecimal)object2[8]);
                }
                else {
                    etatStockMP3.setQuantiteRetour(BigDecimal.ZERO);
                }
                if (((BigDecimal)object2[9]).add((BigDecimal)object2[13]).add((BigDecimal)object2[14]).add((BigDecimal)object2[15]).add((BigDecimal)object2[16]).add((BigDecimal)object2[17]) != null) {
                    etatStockMP3.setQuantiteAutreSortie(((BigDecimal)object2[9]).add((BigDecimal)object2[13]).add((BigDecimal)object2[14]).add((BigDecimal)object2[15]).add((BigDecimal)object2[16]).add((BigDecimal)object2[17]));
                }
                else {
                    etatStockMP3.setQuantiteAutreSortie(BigDecimal.ZERO);
                }
                if (object2[10] != null) {
                    etatStockMP3.setQuantiteResterMachine((BigDecimal)object2[10]);
                }
                else {
                    etatStockMP3.setQuantiteResterMachine(BigDecimal.ZERO);
                }
                if (object2[11] != null) {
                    etatStockMP3.setQuantiteFabriquer((BigDecimal)object2[11]);
                }
                else {
                    etatStockMP3.setQuantiteFabriquer(BigDecimal.ZERO);
                }
                if (object2[12] != null) {
                    etatStockMP3.setQuantiteExistante((BigDecimal)object2[12]);
                }
                else {
                    etatStockMP3.setQuantiteExistante(BigDecimal.ZERO);
                }
                etatStockMP3.setQuantiteFinale(etatStockMP3.getQuantiteInitial().add(etatStockMP3.getQuantiteReception().add(etatStockMP3.getQuantiteRetour()).add(etatStockMP3.getTransferEntrer().add(etatStockMP3.getQuantiteResterMachine().add(etatStockMP3.getQuantiteFabriquer())))).subtract(etatStockMP3.getQuantiteCharger().add(etatStockMP3.getQuantiteChargerSupp().add(etatStockMP3.getQuantiteAutreSortie()).add(etatStockMP3.getTransferSortie()).add(etatStockMP3.getQuantiteExistante()))));
                this.listEtatStockMP.add(etatStockMP3);
            }
        }
        boolean trouver = false;
        for (int l = 0; l < this.listeEtatStockTransfertEnAttenteNonThe.size(); ++l) {
            trouver = false;
            final DetailTransferStockMP detailTransferStockMP = this.listeEtatStockTransfertEnAttenteNonThe.get(l);
            for (int k = 0; k < this.listEtatStockMP.size(); ++k) {
                if (this.listEtatStockMP.get(k).getMp().getId() == detailTransferStockMP.getMatierePremier().getId() && this.listEtatStockMP.get(k).getFournisseurMP() == null && detailTransferStockMP.getFournisseur() == null && detailTransferStockMP.getMagasinDestination().getCatMagasin().equals("PROD") && detailTransferStockMP.getMagasinSouce() != null && detailTransferStockMP.getMagasinSouce().getCatMagasin().equals("PROD")) {
                    trouver = true;
                    final EtatStockMP etatStockMP2 = this.listEtatStockMP.get(k);
                    etatStockMP2.setQuantiteResterMachine(etatStockMP2.getQuantiteResterMachine().add(detailTransferStockMP.getQuantite()));
                    this.listEtatStockMP.set(k, etatStockMP2);
                }
            }
            if (!trouver && detailTransferStockMP.getFournisseur() == null && detailTransferStockMP.getMagasinDestination().getCatMagasin().equals("PROD") && detailTransferStockMP.getMagasinSouce() != null && detailTransferStockMP.getMagasinSouce().getCatMagasin().equals("PROD")) {
                final EtatStockMP etatStockMP3 = new EtatStockMP();
                etatStockMP3.setMp(detailTransferStockMP.getMatierePremier());
                etatStockMP3.setQuantiteInitial(BigDecimal.ZERO);
                etatStockMP3.setQuantiteReception(BigDecimal.ZERO);
                etatStockMP3.setTransferEntrer(BigDecimal.ZERO);
                etatStockMP3.setTransferSortie(BigDecimal.ZERO);
                etatStockMP3.setQuantiteCharger(BigDecimal.ZERO);
                etatStockMP3.setQuantiteChargerSupp(BigDecimal.ZERO);
                etatStockMP3.setQuantiteRetour(BigDecimal.ZERO);
                etatStockMP3.setQuantiteAutreSortie(BigDecimal.ZERO);
                etatStockMP3.setQuantiteResterMachine(detailTransferStockMP.getQuantite());
                etatStockMP3.setQuantiteFabriquer(BigDecimal.ZERO);
                etatStockMP3.setQuantiteExistante(BigDecimal.ZERO);
                etatStockMP3.setQuantiteFinale(etatStockMP3.getQuantiteInitial().add(etatStockMP3.getQuantiteReception().add(etatStockMP3.getQuantiteRetour()).add(etatStockMP3.getTransferEntrer().add(etatStockMP3.getQuantiteResterMachine().add(etatStockMP3.getQuantiteFabriquer())))).subtract(etatStockMP3.getQuantiteCharger().add(etatStockMP3.getQuantiteChargerSupp().add(etatStockMP3.getQuantiteAutreSortie()).add(etatStockMP3.getTransferSortie()).add(etatStockMP3.getQuantiteExistante()))));
                this.listEtatStockMP.add(etatStockMP3);
            }
        }
        for (int m = 0; m < this.listEtatStockMP.size(); ++m) {
            final EtatStockMP etatStockMP4 = this.listEtatStockMP.get(m);
            etatStockMP4.setQuantiteFinale(etatStockMP4.getQuantiteInitial().add(etatStockMP4.getQuantiteReception().add(etatStockMP4.getQuantiteRetour()).add(etatStockMP4.getTransferEntrer().add(etatStockMP4.getQuantiteResterMachine().add(etatStockMP4.getQuantiteFabriquer())))).subtract(etatStockMP4.getQuantiteCharger().add(etatStockMP4.getQuantiteChargerSupp().add(etatStockMP4.getQuantiteAutreSortie()).add(etatStockMP4.getTransferSortie()).add(etatStockMP4.getQuantiteExistante()))));
            this.listEtatStockMP.set(m, etatStockMP4);
        }
    }
    
    static /* synthetic */ void access$13(final CreerOrdreFabricationMP creerOrdreFabricationMP, final String nomMP) {
        creerOrdreFabricationMP.nomMP = nomMP;
    }
    
    static /* synthetic */ void access$16(final CreerOrdreFabricationMP creerOrdreFabricationMP, final ArticlesMP articlemp) {
        creerOrdreFabricationMP.articlemp = articlemp;
    }
    
    static /* synthetic */ void access$19(final CreerOrdreFabricationMP creerOrdreFabricationMP, final List lisDetailEstimationMP) {
        creerOrdreFabricationMP.lisDetailEstimationMP = (List<DetailEstimationMP>)lisDetailEstimationMP;
    }
    
    static /* synthetic */ void access$23(final ProductionMP productionMP) {
        CreerOrdreFabricationMP.productionMP = productionMP;
    }
}

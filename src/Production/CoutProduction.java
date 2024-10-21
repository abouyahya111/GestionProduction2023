package Production;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import util.DateUtils;
import util.JasperUtils;
import util.Utils;


import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailChargeVariableDAOImpl;
import dao.daoImplManager.DetailCoutProductionDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoManager.ChargeProductionDAO;
import dao.daoManager.CompteStockMPDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailChargeFixeDAO;
import dao.daoManager.DetailChargeVariableDAO;
import dao.daoManager.DetailCoutProductionDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.ProductionDAO;
import dao.entity.ChargeProduction;
import dao.entity.CompteStockMP;
import dao.entity.Depot;
import dao.entity.DetailChargeFixe;
import dao.entity.DetailChargeVariable;
import dao.entity.DetailCoutProduction;
import dao.entity.FicheEmploye;
import dao.entity.Production;
import dao.entity.Utilisateur;

import java.awt.Component;
import java.awt.GridBagLayout;

import javax.swing.JTable;
import javax.swing.JComboBox;


public class CoutProduction extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 Modelchargefix;
	
	private DefaultTableModel	 Modelchargevariable;
	
	
	private ImageIcon imgValider;
	private ImageIcon imgInit;
	private ImageIcon imgImprimer;
	private ImageIcon imgRechercher;
	private JDateChooser dateDebutChooser = new JDateChooser();
	private JDateChooser dateFinChooser = new JDateChooser();
	private JComboBox comboDepot = new JComboBox();
	private Map< Integer, String> mapAvance= new HashMap<>();
	private Map< String, BigDecimal> mapParametre = new HashMap<>();
	private Map< String, Depot> mapDepot = new HashMap<>();
	private List<Production> listProduction=new ArrayList<Production>();
	private List<ChargeProduction> listChargeProduction=new ArrayList<ChargeProduction>();
	private List<DetailCoutProduction> listChargeFix=new ArrayList<DetailCoutProduction>();
	private List<DetailCoutProduction> listChargeVariable=new ArrayList<DetailCoutProduction>();
	private List< Depot> listDepot = new ArrayList<Depot>();
	private List<DetailCoutProduction> listChargeFixMP=new ArrayList<DetailCoutProduction>();
	private List<DetailCoutProduction> listChargeVariableMP=new ArrayList<DetailCoutProduction>();
	private DetailCoutProductionDAO detailcoutProductiondao;
	
	private DetailChargeVariableDAO detailchargevariabledao;
	private ProductionDAO productionDAO;
	private ChargeProductionDAO chargeproductionDAO;
	private DepotDAO depotDAO;
	private Utilisateur utilisateur;
	private JTextField txtqantitetotale;
	private JTextField txtcouttotale;
	private JTextField txtcouttotalchargefix;
	private JTextField txtcoutchargevariable;
	private JTextField txtsommecouts;
	private JTextField txtcoutunitaire;
	private JTextField txtcoutdechet;
	private JTextField txtcoutemployer;
	private JTextField txtcoutmp;
	private JTextField txtpourentagecoutmp;
	private JTextField txtpourcentagecoutemploye;
	private JTextField txtpourcentagecoutdechet;
	private JTable tablechargefixe;
	private JTable tablechargevariable;
	private JTextField txtcoutunitairemp;
	private JTextField txtcoutunitaireemploye;
	private JTextField txtcoutunitairedechet;
	private JTextField txtpourcentagedhmp;
	private JTextField txtpourcentagedhemploye;
	private JTextField txtpourcentagedhdechet;
	BigDecimal quanititetotal=BigDecimal.ZERO;
	BigDecimal couttotal=BigDecimal.ZERO;
	BigDecimal couttotalchargefix=BigDecimal.ZERO;
	BigDecimal couttotalchargevariable=BigDecimal.ZERO;
	BigDecimal sommecouts=BigDecimal.ZERO;
	BigDecimal coutunitaire=BigDecimal.ZERO;
	BigDecimal coutmp=BigDecimal.ZERO;
	BigDecimal coutemploye=BigDecimal.ZERO;
	BigDecimal coutdechet=BigDecimal.ZERO;
	BigDecimal pourcentagecoutmp=BigDecimal.ZERO;
	BigDecimal pourcentagecoutemploye=BigDecimal.ZERO;
	BigDecimal pourcentagecoutdechet=BigDecimal.ZERO;
	BigDecimal pourcentagefix=BigDecimal.ZERO;
	BigDecimal pourcentagevariable=BigDecimal.ZERO;
	BigDecimal coutunitairemp=BigDecimal.ZERO;
	BigDecimal coutunitaireemploye=BigDecimal.ZERO;
	BigDecimal coutunitairedechet=BigDecimal.ZERO;
	BigDecimal pourcentagedhmp=BigDecimal.ZERO;
	BigDecimal pourcentagedhemploye=BigDecimal.ZERO;
	BigDecimal pourcentagedhdechet=BigDecimal.ZERO;
	BigDecimal coutunitairempfix=BigDecimal.ZERO;
	BigDecimal coutunitairempvariable=BigDecimal.ZERO;
	BigDecimal pourcentagedhmpfix=BigDecimal.ZERO;
	BigDecimal pourcentagedhmpvariable=BigDecimal.ZERO;
	BigDecimal pourcentagecouttotal=BigDecimal.ZERO;
	BigDecimal porcentagequantitechargefix=BigDecimal.ZERO;
	BigDecimal porcentagequantitechargevariable=BigDecimal.ZERO;
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public CoutProduction() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 1026);
        try{
        	
        	chargeproductionDAO= new ChargeProductionDAOImpl();
        	productionDAO= new ProductionDAOImpl();
        	detailcoutProductiondao= new DetailCoutProductionDAOImpl();
        	detailchargevariabledao= new DetailChargeVariableDAOImpl();
        	depotDAO=new DepotDAOImpl();
        	utilisateur=AuthentificationView.utilisateur;

       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
}
        
        try{
        	imgRechercher= new ImageIcon(this.getClass().getResource("/img/rechercher.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgImprimer=new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
            imgValider=new ImageIcon(this.getClass().getResource("/img/valider.png"));
          } catch (Exception exp){exp.printStackTrace();}
		
        mapParametre=Utils.listeParametre();	 	
	
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
          } catch (Exception exp){exp.printStackTrace();}
				  		     
				  		 
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setBackground(Color.RED);
				  		     	titledSeparator.setTitle("Cout Production");
				  		     	titledSeparator.setBounds(9, 64, 782, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 11, 782, 54);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblDateDebut = new JLabel("Date d\u00E9but :");
				  		     	lblDateDebut.setBounds(10, 11, 96, 24);
				  		     	layeredPane.add(lblDateDebut);
				  		     	lblDateDebut.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	 
				  		     	 JLabel lblDateFin = new JLabel("Date Fin :");
				  		     	 lblDateFin.setBounds(262, 10, 102, 24);
				  		     	 layeredPane.add(lblDateFin);
				  		     	 lblDateFin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JButton btnAfficherStock = new JButton();
		btnAfficherStock.setIcon(imgRechercher);
		btnAfficherStock.setBounds(741, 11, 31, 31);
		layeredPane.add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  DecimalFormat format = new DecimalFormat("#0.00");
				 quanititetotal=BigDecimal.ZERO;
				 couttotal=BigDecimal.ZERO;
				 couttotalchargefix=BigDecimal.ZERO;
				 couttotalchargevariable=BigDecimal.ZERO;
				 sommecouts=BigDecimal.ZERO;
				 coutunitaire=BigDecimal.ZERO;
				 coutmp=BigDecimal.ZERO;
				 coutemploye=BigDecimal.ZERO;
				 coutdechet=BigDecimal.ZERO;
				int i=0;
				int j=0;
				int k=0;
				int l=0;
				 pourcentagecoutmp=BigDecimal.ZERO;
				 pourcentagecoutemploye=BigDecimal.ZERO;
				 pourcentagecoutdechet=BigDecimal.ZERO;
				 pourcentagefix=BigDecimal.ZERO;
				 pourcentagevariable=BigDecimal.ZERO;
				 coutunitairemp=BigDecimal.ZERO;
				 coutunitaireemploye=BigDecimal.ZERO;
				 coutunitairedechet=BigDecimal.ZERO;
				 pourcentagedhmp=BigDecimal.ZERO;
				 pourcentagedhemploye=BigDecimal.ZERO;
				 pourcentagedhdechet=BigDecimal.ZERO;
				 coutunitairempfix=BigDecimal.ZERO;
				 coutunitairempvariable=BigDecimal.ZERO;
				 pourcentagedhmpfix=BigDecimal.ZERO;
				 pourcentagedhmpvariable=BigDecimal.ZERO;
				 pourcentagecouttotal=BigDecimal.ZERO;
				 porcentagequantitechargefix=BigDecimal.ZERO;
				 porcentagequantitechargevariable=BigDecimal.ZERO;
			 listChargeProduction.clear();
				 listProduction.clear();
				 listChargeFix.clear();
			     listChargeVariable.clear();
				 listChargeVariableMP.clear();
				 listChargeFixMP.clear();
				 intialiserTableChargeFix();
				 intialiserTableChargeVariable();
				 
				 SimpleDateFormat simpleFormat = new SimpleDateFormat("MMyyyy");
				
				String dateDebut=((JTextField)dateDebutChooser.getDateEditor().getUiComponent()).getText();
				String dateFin=((JTextField)dateFinChooser.getDateEditor().getUiComponent()).getText();
			if(dateDebut.equals(""))	{
				JOptionPane.showMessageDialog(null, "Il faut choisir Date D�but", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else if(dateFin.equals("")){
				JOptionPane.showMessageDialog(null, "Il faut choisir Date Fin", "Erreur", JOptionPane.ERROR_MESSAGE);
				
			}else if(comboDepot.getSelectedItem().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Il faut choisir le depot", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
			else  {
				
			Depot depot=mapDepot.get(comboDepot.getSelectedItem());
					
				String date=simpleFormat.format(dateDebutChooser.getDate());
				
				
				listChargeProduction=chargeproductionDAO.listeChargeProductionbycodeAndDepot(date, depot.getCode());
				listProduction=productionDAO.listeProductionTerminerbyDepotEntreDeuxDate(dateDebutChooser.getDate(), dateFinChooser.getDate(),Constantes.ETAT_OF_TERMINER,depot.getCode());

while (i<listProduction.size())
				
{
	
	Production production=listProduction.get(i);
	
	quanititetotal=quanititetotal.add(production.getQuantiteReel());
	couttotal=couttotal.add(production.getCoutTotal());
	coutmp=coutmp.add(production.getCoutTotalMP());
	coutemploye=coutemploye.add(production.getCoutTotalEmploye()).add(production.getCoutTotalEmployeEmbalage()).add(production.getCoutTotalEmployeGen());
	coutdechet=coutdechet.add(production.getCoutDechet());
	
	
	
	i++;
}

while(j<listChargeProduction.size())	
{
	
	ChargeProduction chargeproduction=listChargeProduction.get(j);
	if(chargeproduction.getType().equals(Constantes.CHARGEST_FIX))
	{
		couttotalchargefix=couttotalchargefix.add(chargeproduction.getTotale());
		//listChargeFix=chargeproduction.getListdetailChargeFixe();
		
		
	}else if(chargeproduction.getType().equals(Constantes.CHARGEST_VARIABLE))
	{
		
		couttotalchargevariable=couttotalchargevariable.add(chargeproduction.getTotale());
		//listChargeVariable=chargeproduction.getListdetailChargeVariable();
	}
	
	
	j++;
}
		
				    sommecouts= couttotal.add(couttotalchargevariable).add(couttotalchargefix) ;
				    if(quanititetotal.compareTo(BigDecimal.ZERO) ==0)
				    {
				    	coutunitaire=BigDecimal.ZERO;
				    	coutunitairemp=BigDecimal.ZERO;
				    	coutunitaireemploye=BigDecimal.ZERO;
				    	coutunitairedechet=BigDecimal.ZERO;
				    }else
				    {
				    	   coutunitaire=sommecouts.divide(quanititetotal, 6, BigDecimal.ROUND_HALF_UP);
				    	   coutunitairemp=coutmp.divide(quanititetotal, 6, BigDecimal.ROUND_HALF_UP);
							coutunitaireemploye=coutemploye.divide(quanititetotal, 6, BigDecimal.ROUND_HALF_UP);
							coutunitairedechet=coutdechet.divide(quanititetotal, 6, BigDecimal.ROUND_HALF_UP);
				    }
				 if(sommecouts.compareTo(BigDecimal.ZERO) ==0)
				 {
					 pourcentagecoutdechet=BigDecimal.ZERO;
					 pourcentagecoutemploye=BigDecimal.ZERO;
					 pourcentagecoutmp=BigDecimal.ZERO;
					 pourcentagecouttotal=BigDecimal.ZERO;
					 porcentagequantitechargefix=BigDecimal.ZERO;
					 porcentagequantitechargevariable=BigDecimal.ZERO;
				 }else
				 {
					 pourcentagecoutdechet=(coutdechet.divide(sommecouts, 6, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
					    pourcentagecoutemploye=(coutemploye.divide(sommecouts, 6, BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal(100)) ;
					    pourcentagecoutmp=(coutmp.divide(sommecouts, 6, BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal(100)) ;
					    
						pourcentagecouttotal=(couttotal.divide(sommecouts, 6, BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal(100)) ;
						 porcentagequantitechargefix=(couttotalchargefix.divide(sommecouts, 6, BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal(100));
						porcentagequantitechargevariable=(couttotalchargevariable.divide(sommecouts, 6, BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal(100)) ;
				 }
				   
					txtqantitetotale.setText(NumberFormat.getNumberInstance(Locale.FRANCE).format(quanititetotal)+"");
					txtcouttotale.setText( NumberFormat.getNumberInstance(Locale.FRANCE).format(couttotal)+"");
					txtcouttotalchargefix.setText(NumberFormat.getNumberInstance(Locale.FRANCE).format(couttotalchargefix)+"");
					txtcoutchargevariable.setText(NumberFormat.getNumberInstance(Locale.FRANCE).format(couttotalchargevariable)+"");
					txtsommecouts.setText(NumberFormat.getNumberInstance(Locale.FRANCE).format(sommecouts)+"");
					txtcoutunitaire.setText(NumberFormat.getNumberInstance(Locale.FRANCE).format(coutunitaire)+"");
					txtcoutmp.setText(NumberFormat.getNumberInstance(Locale.FRANCE).format(coutmp)+"");
					txtcoutemployer.setText(NumberFormat.getNumberInstance(Locale.FRANCE).format(coutemploye)+"");
					txtcoutdechet.setText(NumberFormat.getNumberInstance(Locale.FRANCE).format(coutdechet)+"");
					
					pourcentagedhmp=coutunitairemp.multiply(pourcentagecoutmp.divide(new BigDecimal(100), 6,  BigDecimal.ROUND_HALF_UP));
					pourcentagedhemploye=coutunitaireemploye.multiply(pourcentagecoutemploye.divide(new BigDecimal(100), 6, BigDecimal.ROUND_HALF_UP));
					pourcentagedhdechet=coutunitairedechet.multiply(pourcentagecoutdechet.divide(new BigDecimal(100), 6, BigDecimal.ROUND_HALF_UP));
				
					txtcoutunitairemp.setText(NumberFormat.getNumberInstance(Locale.FRANCE).format(coutunitairemp)+"");
					txtcoutunitaireemploye.setText(NumberFormat.getNumberInstance(Locale.FRANCE).format(coutunitaireemploye)+"");
					txtcoutunitairedechet.setText(NumberFormat.getNumberInstance(Locale.FRANCE).format(coutunitairedechet)+"");
					txtpourcentagedhmp.setText(NumberFormat.getNumberInstance(Locale.FRANCE).format(pourcentagedhmp)+Constantes.POURCENTAGEDH);
					txtpourcentagedhemploye.setText(NumberFormat.getNumberInstance(Locale.FRANCE).format(pourcentagedhemploye)+Constantes.POURCENTAGEDH);
					txtpourcentagedhdechet.setText(NumberFormat.getNumberInstance(Locale.FRANCE).format(pourcentagedhdechet)+Constantes.POURCENTAGEDH);
					txtpourentagecoutmp.setText(NumberFormat.getNumberInstance(Locale.FRANCE).format(pourcentagecoutmp)+Constantes.POURCENTAGE);
					txtpourcentagecoutdechet.setText(NumberFormat.getNumberInstance(Locale.FRANCE).format(pourcentagecoutdechet)+Constantes.POURCENTAGE);
					txtpourcentagecoutemploye.setText(NumberFormat.getNumberInstance(Locale.FRANCE).format(pourcentagecoutemploye)+Constantes.POURCENTAGE);
					
					j=0;
					while(j<listChargeProduction.size())	
					{
						k=0;
						l=0;
						
						ChargeProduction chargeproduction=listChargeProduction.get(j);
						if(chargeproduction.getType().equals(Constantes.CHARGEST_FIX))
						{
							
								listChargeFixMP.addAll(chargeproduction.getListdetailCoutProductions());
								
															
							while(k<listChargeFixMP.size())
							{
								
								DetailCoutProduction detailchargefix=listChargeFixMP.get(k);
								if(sommecouts.compareTo(BigDecimal.ZERO)  ==0)
								{
									pourcentagefix=BigDecimal.ZERO;
								}else
								{
									pourcentagefix=(detailchargefix.getMontant().divide(sommecouts, 6, BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal(100));
								}
								if(quanititetotal.compareTo(BigDecimal.ZERO)  ==0)
								{
									coutunitairempfix=BigDecimal.ZERO;
								}
								else
								{
									coutunitairempfix=detailchargefix.getMontant().divide(quanititetotal, 6, BigDecimal.ROUND_HALF_UP);
								}
								
								pourcentagedhmpfix=(pourcentagefix.divide(new BigDecimal(100), 6, BigDecimal.ROUND_HALF_UP)).multiply(coutunitairempfix);
								//detailchargefix.setLibelle(detailchargefix.getLibelle());
								detailchargefix.setMontant(detailchargefix.getMontant());
								detailchargefix.setCoutunitaire(coutunitairempfix);
								detailchargefix.setPourcentage(pourcentagefix.divide(new BigDecimal(100), 6, BigDecimal.ROUND_HALF_UP));
								detailchargefix.setPourcentagedh(pourcentagedhmpfix);
								detailcoutProductiondao.edit(detailchargefix);
								listChargeFix.add(detailchargefix);
								
								Object []ligne={detailchargefix.getCharges().getLiblle(),NumberFormat.getNumberInstance(Locale.FRANCE).format(detailchargefix.getMontant()),NumberFormat.getNumberInstance(Locale.FRANCE).format(coutunitairempfix),NumberFormat.getNumberInstance(Locale.FRANCE).format(pourcentagefix)+Constantes.POURCENTAGE,NumberFormat.getNumberInstance(Locale.FRANCE).format(pourcentagedhmpfix)+Constantes.POURCENTAGEDH};

							Modelchargefix.addRow( ligne);
							
								k++;
							}
							
							
						}else if(chargeproduction.getType().equals(Constantes.CHARGEST_VARIABLE))
						{
							
							
								listChargeVariableMP.addAll(chargeproduction.getListdetailCoutProductions());
							
							
							
							while(l<listChargeVariableMP.size())
							{
								
								
								DetailCoutProduction detailchargevariable=listChargeVariableMP.get(l);
								if(sommecouts.compareTo(BigDecimal.ZERO)   ==0)
								{
									pourcentagevariable=BigDecimal.ZERO;
								}else
								{
									pourcentagevariable=(detailchargevariable.getMontant().divide(sommecouts, 6, BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal(100) );
								}
								if(quanititetotal.compareTo(BigDecimal.ZERO)  ==0)
								{
									coutunitairempvariable=BigDecimal.ZERO;
								}
								else
								{
									coutunitairempvariable=detailchargevariable.getMontant().divide(quanititetotal, 6, BigDecimal.ROUND_HALF_UP);
								}
								
								pourcentagedhmpvariable=(pourcentagevariable.divide(new BigDecimal(100), 6, BigDecimal.ROUND_HALF_UP)).multiply(coutunitairempvariable);
								//detailchargevariable.setLibelle(detailchargevariable.getLibelle());
								detailchargevariable.setMontant(detailchargevariable.getMontant());
								detailchargevariable.setCoutunitaire(coutunitairempvariable);
								detailchargevariable.setPourcentage(pourcentagevariable.divide(new BigDecimal(100), 6,  BigDecimal.ROUND_HALF_UP));
								detailchargevariable.setPourcentagedh(pourcentagedhmpvariable);
								detailcoutProductiondao.edit(detailchargevariable);
								listChargeVariable.add(detailchargevariable);
								
								Object []ligne={detailchargevariable.getCharges().getLiblle(),NumberFormat.getNumberInstance(Locale.FRANCE).format(detailchargevariable.getMontant()),NumberFormat.getNumberInstance(Locale.FRANCE).format(coutunitairempvariable),NumberFormat.getNumberInstance(Locale.FRANCE).format(pourcentagevariable)+Constantes.POURCENTAGE,NumberFormat.getNumberInstance(Locale.FRANCE).format(pourcentagedhmpvariable)+Constantes.POURCENTAGEDH};

							Modelchargevariable.addRow( ligne);
								l++;
							}
							
							
						}
						
						
						
						j++;
					}
					
					
					
					tablechargefixe.setModel(Modelchargefix);
					
					
					tablechargevariable.setModel(Modelchargevariable);
					
					
					
					
				
					//	afficher_tableMP_Total(listObject);
			}
		  }
		});
		btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		 
		dateDebutChooser.setBounds(76, 11, 130, 24);
		layeredPane.add(dateDebutChooser);
		
		
		dateFinChooser.setBounds(324, 11, 140, 24);
		layeredPane.add(dateFinChooser);
		
		JLabel label = new JLabel("Depot :");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(471, 11, 64, 26);
		layeredPane.add(label);
		
		 comboDepot = new JComboBox();
		comboDepot.setBounds(540, 13, 191, 26);
		layeredPane.add(comboDepot);
		int k=0;
	      
      	if(utilisateur.getNom().equals("admin"))
      	{
      		
      		listDepot=depotDAO.findAll();
      		
      		while(k<listDepot.size())
      		{
      			
      			Depot depot=listDepot.get(k);
      			mapDepot.put(depot.getLibelle(), depot);
      			comboDepot.addItem(depot.getLibelle());
      			k++;
      		}
      		
      		
      	}else
      	{
      		
      		Depot depot= depotDAO.findByCode(utilisateur.getCodeDepot());
      		
      		if(depot!=null)
      		{
      			comboDepot.addItem(depot.getLibelle());
      			mapDepot.put(depot.getLibelle(), depot);
      		}
      	}
		
		JLabel lblNewLabel = new JLabel("Quantit\u00E9 Total :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(67, 105, 122, 23);
		add(lblNewLabel);
		
		JLabel lblCoutTotal = new JLabel("Cout Total :");
		lblCoutTotal.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCoutTotal.setBounds(67, 151, 122, 23);
		add(lblCoutTotal);
		
		JLabel lblQuantitTotalFixe = new JLabel("Cout Total  Charge Fixe :");
		lblQuantitTotalFixe.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblQuantitTotalFixe.setBounds(67, 185, 183, 23);
		add(lblQuantitTotalFixe);
		
		JLabel lblQuantitTotalCharge = new JLabel("Cout Total  Charge Variable:");
		lblQuantitTotalCharge.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblQuantitTotalCharge.setBounds(67, 227, 216, 23);
		add(lblQuantitTotalCharge);
		
		JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
		GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
		gridBagLayout.rowWeights = new double[]{0.0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		titledSeparator_1.setTitle("Somme Cout Production");
		titledSeparator_1.setBackground(Color.RED);
		titledSeparator_1.setBounds(9, 261, 782, 30);
		add(titledSeparator_1);
		
		JLabel lblSommeCouts = new JLabel("Somme Couts  :");
		lblSommeCouts.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSommeCouts.setBounds(67, 302, 122, 23);
		add(lblSommeCouts);
		
		JLabel lblCoutUnitaire = new JLabel("Cout Unitaire :");
		lblCoutUnitaire.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCoutUnitaire.setBounds(67, 336, 122, 23);
		add(lblCoutUnitaire);
		
		JXTitledSeparator titledSeparator_2 = new JXTitledSeparator();
		GridBagLayout gridBagLayout_1 = (GridBagLayout) titledSeparator_2.getLayout();
		gridBagLayout_1.rowWeights = new double[]{0.0};
		gridBagLayout_1.rowHeights = new int[]{0};
		gridBagLayout_1.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout_1.columnWidths = new int[]{0, 0, 0};
		titledSeparator_2.setTitle("Cout Total");
		titledSeparator_2.setBackground(Color.RED);
		titledSeparator_2.setBounds(9, 366, 782, 30);
		add(titledSeparator_2);
		
		JLabel lblCoutMatierePremiere = new JLabel("Cout Matiere Premiere  :");
		lblCoutMatierePremiere.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCoutMatierePremiere.setBounds(67, 437, 183, 23);
		add(lblCoutMatierePremiere);
		
		JLabel lblCoutEmploy = new JLabel("Cout Employ\u00E9  :");
		lblCoutEmploy.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCoutEmploy.setBounds(67, 476, 183, 23);
		add(lblCoutEmploy);
		
		JLabel lblCoutDechet = new JLabel("Cout Dechet :");
		lblCoutDechet.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCoutDechet.setBounds(67, 509, 183, 23);
		add(lblCoutDechet);
		
		JXTitledSeparator titledSeparator_3 = new JXTitledSeparator();
		GridBagLayout gridBagLayout_2 = (GridBagLayout) titledSeparator_3.getLayout();
		gridBagLayout_2.rowWeights = new double[]{0.0};
		gridBagLayout_2.rowHeights = new int[]{0};
		gridBagLayout_2.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout_2.columnWidths = new int[]{0, 0, 0};
		titledSeparator_3.setTitle("Cout Charge Fixe");
		titledSeparator_3.setBackground(Color.RED);
		titledSeparator_3.setBounds(9, 550, 782, 30);
		add(titledSeparator_3);
		 Modelchargefix =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"Libelle","Montant" ,"Cout Unitaire","Pourcentage","Pourcentage En DH"
	  		     			
	  		     	}
	  		  
				   ) {
	  		     	boolean[] columnEditables = new boolean[] {
	  		     		 false,false,false,false,false
	  		     	};
	  		     	public boolean isCellEditable(int row, int column) {
	  		     		return columnEditables[column];
	  		     	}
	  		     };
		
		JXTitledSeparator titledSeparator_4 = new JXTitledSeparator();
		GridBagLayout gridBagLayout_3 = (GridBagLayout) titledSeparator_4.getLayout();
		gridBagLayout_3.rowWeights = new double[]{0.0};
		gridBagLayout_3.rowHeights = new int[]{0};
		gridBagLayout_3.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout_3.columnWidths = new int[]{0, 0, 0};
		titledSeparator_4.setTitle("Cout Charge Variable");
		titledSeparator_4.setBackground(Color.RED);
		titledSeparator_4.setBounds(9, 755, 782, 30);
		add(titledSeparator_4);
		 Modelchargevariable =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"Libelle","Montant" ,"Cout Unitaire","Pourcentage","Pourcentage En DH"
	  		     			
	  		     	}
	  		  
				   ) {
	  		     	boolean[] columnEditables = new boolean[] {
	  		     		 false,false,false,false,false
	  		     	};
	  		     	public boolean isCellEditable(int row, int column) {
	  		     		return columnEditables[column];
	  		     	}
	  		     };
		
		txtqantitetotale = new JTextField();
		txtqantitetotale.setEditable(false);
		txtqantitetotale.setBounds(279, 102, 200, 30);
		add(txtqantitetotale);
		txtqantitetotale.setColumns(10);
		
		txtcouttotale = new JTextField();
		txtcouttotale.setEditable(false);
		txtcouttotale.setColumns(10);
		txtcouttotale.setBounds(279, 141, 200, 30);
		add(txtcouttotale);
		
		txtcouttotalchargefix = new JTextField();
		txtcouttotalchargefix.setEditable(false);
		txtcouttotalchargefix.setColumns(10);
		txtcouttotalchargefix.setBounds(279, 182, 200, 30);
		add(txtcouttotalchargefix);
		
		txtcoutchargevariable = new JTextField();
		txtcoutchargevariable.setEditable(false);
		txtcoutchargevariable.setColumns(10);
		txtcoutchargevariable.setBounds(279, 224, 200, 30);
		add(txtcoutchargevariable);
		
		txtsommecouts = new JTextField();
		txtsommecouts.setEditable(false);
		txtsommecouts.setColumns(10);
		txtsommecouts.setBounds(279, 299, 200, 30);
		add(txtsommecouts);
		
		txtcoutunitaire = new JTextField();
		txtcoutunitaire.setEditable(false);
		txtcoutunitaire.setColumns(10);
		txtcoutunitaire.setBounds(279, 333, 200, 30);
		add(txtcoutunitaire);
		
		txtcoutdechet = new JTextField();
		txtcoutdechet.setEditable(false);
		txtcoutdechet.setColumns(10);
		txtcoutdechet.setBounds(279, 509, 113, 30);
		add(txtcoutdechet);
		
		txtcoutemployer = new JTextField();
		txtcoutemployer.setEditable(false);
		txtcoutemployer.setColumns(10);
		txtcoutemployer.setBounds(279, 473, 113, 30);
		add(txtcoutemployer);
		
		txtcoutmp = new JTextField();
		txtcoutmp.setEditable(false);
		txtcoutmp.setColumns(10);
		txtcoutmp.setBounds(279, 434, 113, 30);
		add(txtcoutmp);
		
		JLabel lblPourcentage = new JLabel("Pourcentage");
		lblPourcentage.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPourcentage.setBounds(533, 407, 97, 23);
		add(lblPourcentage);
		
		JLabel lblCout = new JLabel("Cout Total");
		lblCout.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCout.setBounds(279, 407, 97, 23);
		add(lblCout);
		
		txtpourentagecoutmp = new JTextField();
		txtpourentagecoutmp.setEditable(false);
		txtpourentagecoutmp.setColumns(10);
		txtpourentagecoutmp.setBounds(533, 434, 103, 30);
		add(txtpourentagecoutmp);
		
		txtpourcentagecoutemploye = new JTextField();
		txtpourcentagecoutemploye.setEditable(false);
		txtpourcentagecoutemploye.setColumns(10);
		txtpourcentagecoutemploye.setBounds(533, 473, 103, 30);
		add(txtpourcentagecoutemploye);
		
		txtpourcentagecoutdechet = new JTextField();
		txtpourcentagecoutdechet.setEditable(false);
		txtpourcentagecoutdechet.setColumns(10);
		txtpourcentagecoutdechet.setBounds(533, 509, 103, 30);
		add(txtpourcentagecoutdechet);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 589, 782, 155);
		add(scrollPane);
		
		tablechargefixe = new JTable();
		tablechargefixe.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Libelle","Montant" ,"Cout Unitaire","Pourcentage","Pourcentage En DH"
			}
		));
		tablechargefixe.setFillsViewportHeight(true);
		scrollPane.setViewportView(tablechargefixe);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(9, 793, 782, 167);
		add(scrollPane_1);
		
		tablechargevariable = new JTable();
		tablechargevariable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Libelle","Montant" ,"Cout Unitaire","Pourcentage","Pourcentage En DH"
			}
		));
		tablechargevariable.setFillsViewportHeight(true);
		scrollPane_1.setViewportView(tablechargevariable);
		
		JLabel lblCoutUnitaire_1 = new JLabel("Cout Unitaire");
		lblCoutUnitaire_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCoutUnitaire_1.setBounds(420, 407, 97, 23);
		add(lblCoutUnitaire_1);
		
		txtcoutunitairemp = new JTextField();
		txtcoutunitairemp.setEditable(false);
		txtcoutunitairemp.setColumns(10);
		txtcoutunitairemp.setBounds(402, 434, 113, 30);
		add(txtcoutunitairemp);
		
		txtcoutunitaireemploye = new JTextField();
		txtcoutunitaireemploye.setEditable(false);
		txtcoutunitaireemploye.setColumns(10);
		txtcoutunitaireemploye.setBounds(402, 473, 113, 30);
		add(txtcoutunitaireemploye);
		
		txtcoutunitairedechet = new JTextField();
		txtcoutunitairedechet.setEditable(false);
		txtcoutunitairedechet.setColumns(10);
		txtcoutunitairedechet.setBounds(402, 511, 113, 30);
		add(txtcoutunitairedechet);
		
		JLabel lblPourcentageEnDh = new JLabel("Pourcentage En DH");
		lblPourcentageEnDh.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPourcentageEnDh.setBounds(658, 407, 131, 23);
		add(lblPourcentageEnDh);
		
		txtpourcentagedhmp = new JTextField();
		txtpourcentagedhmp.setEditable(false);
		txtpourcentagedhmp.setColumns(10);
		txtpourcentagedhmp.setBounds(658, 437, 103, 30);
		add(txtpourcentagedhmp);
		
		txtpourcentagedhemploye = new JTextField();
		txtpourcentagedhemploye.setEditable(false);
		txtpourcentagedhemploye.setColumns(10);
		txtpourcentagedhemploye.setBounds(658, 473, 103, 30);
		add(txtpourcentagedhemploye);
		
		txtpourcentagedhdechet = new JTextField();
		txtpourcentagedhdechet.setEditable(false);
		txtpourcentagedhdechet.setColumns(10);
		txtpourcentagedhdechet.setBounds(658, 511, 103, 30);
		add(txtpourcentagedhdechet);
		
		JButton btnCoutProduction = new JButton("Cout Production");
		btnCoutProduction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(listChargeProduction.size()!=0)
				{
					DateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
		  		  	String datedebut=dateFormat.format(dateDebutChooser.getDate());
		  		  String datefin=dateFormat.format(dateFinChooser.getDate());
					Map parameters = new HashMap();
					
					
					parameters.put("quanititetotal",quanititetotal);
					parameters.put("couttotal", couttotal);
					parameters.put("couttotalchargefix", couttotalchargefix);
					parameters.put("couttotalchargevariable", couttotalchargevariable);
					parameters.put("sommecouts", sommecouts);
					parameters.put("coutunitaire", coutunitaire);
					parameters.put("coutmp", coutmp);
					parameters.put("coutemploye", coutemploye);
					parameters.put("coutdechet", coutdechet);			
					parameters.put("pourcentagecoutmp", pourcentagecoutmp.divide(new BigDecimal(100), 6, BigDecimal.ROUND_HALF_UP));
					parameters.put("pourcentagecoutemploye", pourcentagecoutemploye.divide(new BigDecimal(100), 6, BigDecimal.ROUND_HALF_UP));
					parameters.put("pourcentagecoutdechet", pourcentagecoutdechet.divide(new BigDecimal(100), 6, BigDecimal.ROUND_HALF_UP));
					parameters.put("pourcentagecouttotal", pourcentagecouttotal.divide(new BigDecimal(100), 6, BigDecimal.ROUND_HALF_UP));
					parameters.put("pourcentagequantitechargefix", porcentagequantitechargefix.divide(new BigDecimal(100), 6, BigDecimal.ROUND_HALF_UP));
					parameters.put("pourcentagequantitechargevariable", porcentagequantitechargevariable.divide(new BigDecimal(100), 6, BigDecimal.ROUND_HALF_UP));
					parameters.put("coutunitairemp", coutunitairemp);
					parameters.put("coutunitaireemploye", coutunitaireemploye);
					parameters.put("coutunitairedechet", coutunitairedechet);
					parameters.put("pourcentagedhmp", pourcentagedhmp);
					parameters.put("pourcentagedhemploye", pourcentagedhemploye);
					parameters.put("pourcentagedhdechet", pourcentagedhdechet);	
					parameters.put("datedebut", datedebut);
					parameters.put("datefin", datefin);
					parameters.put("listChargeFixMP", listChargeFixMP);
					parameters.put("listChargeVariableMP", listChargeVariableMP);
					parameters.put("depot",comboDepot.getSelectedItem());
				
					JasperUtils.imprimerCoutProduction(listChargeProduction,parameters);
					
					
				}else
				{
					JOptionPane.showMessageDialog(null, "il y a aucun cout de production pour cette periode !!!!! ","Erreur",JOptionPane.ERROR_MESSAGE);
				}
				
			
				
			}
		});
		btnCoutProduction.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCoutProduction.setIcon(imgImprimer);
		btnCoutProduction.setBounds(300, 971, 133, 23);
		add(btnCoutProduction);
		
	
				  		     
				  		 
	}
	
	
	void	intialiserTableChargeFix(){
		 Modelchargefix =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"Libelle","Montant" ,"Cout Unitaire","Pourcentage","Pourcentage En DH"
	  		     	}
	  		     ) ;
	  		     
		 tablechargefixe.setModel(Modelchargefix); 
		 tablechargefixe.getColumnModel().getColumn(0).setPreferredWidth(30);
		 tablechargefixe.getColumnModel().getColumn(1).setPreferredWidth(160);
		 tablechargefixe.getColumnModel().getColumn(2).setPreferredWidth(40);
		 tablechargefixe.getColumnModel().getColumn(3).setPreferredWidth(40);
		 tablechargefixe.getColumnModel().getColumn(4).setPreferredWidth(40);
	  		
	}
	
	
	void	intialiserTableChargeVariable(){
		 Modelchargevariable =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"Libelle","Montant" ,"Cout Unitaire","Pourcentage","Pourcentage En DH"
	  		     	}
	  		     ) ;
	  		     
		 tablechargevariable.setModel(Modelchargevariable); 
		 tablechargevariable.getColumnModel().getColumn(0).setPreferredWidth(30);
		 tablechargevariable.getColumnModel().getColumn(1).setPreferredWidth(160);
		 tablechargevariable.getColumnModel().getColumn(2).setPreferredWidth(40);
		 tablechargevariable.getColumnModel().getColumn(3).setPreferredWidth(40);
		 tablechargevariable.getColumnModel().getColumn(4).setPreferredWidth(40);
	  		
	}
	
	
	
}

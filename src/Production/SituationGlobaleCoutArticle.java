package Production;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
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

import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoManager.CompteStockMPDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.ProductionDAO;
import dao.entity.CompteStockMP;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailProdGen;
import dao.entity.DetailProduction;
import dao.entity.DetailResponsableProd;
import dao.entity.Employe;
import dao.entity.FicheEmploye;
import dao.entity.Magasin;
import dao.entity.Production;
import dao.entity.Utilisateur;

import java.awt.Component;

import javax.swing.JComboBox;

import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.ScrollPane;


public class SituationGlobaleCoutArticle extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleProd;
	private DefaultTableModel	 modeleMP;
	private DefaultTableModel	 modeleEmployeGen;
	private DefaultTableModel	 modeleEmployeProd;
	private DefaultTableModel	 modeleEmployeEmballage;
	private JXTable table;
	

	
	 List<CoutMP> listCoutMP=new ArrayList<CoutMP>();
	 List<DetailResponsableProd> listEmployeGesnerique=new ArrayList<DetailResponsableProd>();
	 List<DetailProdGen> listEmployeEmballage=new ArrayList<DetailProdGen>();
	 List<DetailProduction> listEmployeProduction=new ArrayList<DetailProduction>();
	private ImageIcon imgValider;
	private ImageIcon imgInit;
	private ImageIcon imgImprimer;
	private ImageIcon imgRechercher;
	private JDateChooser dateDebutChooser = new JDateChooser();
	private JDateChooser dateFinChooser = new JDateChooser();
	JComboBox combodepot = new JComboBox();
	private Map< Integer, String> mapAvance= new HashMap<>();
	private Map< String, BigDecimal> mapParametre = new HashMap<>();
	private List<Depot> listDepot=new ArrayList<Depot>();
	private List<Production> listProduction=new ArrayList<Production>();
	private List<Object[]> listObjetSituationCoutProduction =new ArrayList<Object[]>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Utilisateur utilisateur;
	private ProductionDAO productionDAO;
	private DepotDAO depotdao;
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public SituationGlobaleCoutArticle() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1579, 1062);
        try{
        	
        	 utilisateur=AuthentificationView.utilisateur;
        	productionDAO= new ProductionDAOImpl();
        	depotdao=new DepotDAOImpl();

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
				  		     
				  		   modeleProd =new DefaultTableModel(
					  		     	new Object[][] {
					  		     	},
					  		     	new String[] {
					  		     			"Code Article","Article", "Quantite","Cout Total","Prix"
					  		     	}
					  		     ) {
					  		     	boolean[] columnEditables = new boolean[] {
					  		     			false,false,false,false,false
					  		     	};
					  		     	public boolean isCellEditable(int row, int column) {
					  		     		return columnEditables[column];
					  		     	}
					  		     };
					  		     
					  		     
					  		     
					  		 table.setModel(modeleProd); 
					  		 table.getColumnModel().getColumn(0).setPreferredWidth(60);
					         table.getColumnModel().getColumn(1).setPreferredWidth(160);
					         table.getColumnModel().getColumn(2).setPreferredWidth(60);
					      //   intialiserTableau2();
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(9, 65, 1018, 429);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	modeleProd =new DefaultTableModel(
				  			     	new Object[][] {
				  			     	},
				  			     	new String[] {
				  			     			"Code Article","Article", "Quantite","Cout Total","Prix"
				  			     	}
				  			     ) {
				  			     	boolean[] columnEditables = new boolean[] {
				  			     			false,false,false,false,false
				  			     	};
				  			     	public boolean isCellEditable(int row, int column) {
				  			     		return columnEditables[column];
				  			     	}
				  			     };
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("");
				  		     	titledSeparator.setBounds(9, 49, 1018, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 11, 1018, 54);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblDateDebut = new JLabel("Du :");
				  		     	lblDateDebut.setBounds(10, 11, 31, 24);
				  		     	layeredPane.add(lblDateDebut);
				  		     	lblDateDebut.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	 
				  		     	 JLabel lblDateFin = new JLabel("Au :");
				  		     	 lblDateFin.setBounds(179, 11, 51, 24);
				  		     	 layeredPane.add(lblDateFin);
				  		     	 lblDateFin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JButton btnAfficherStock = new JButton();
		btnAfficherStock.setIcon(imgRechercher);
		btnAfficherStock.setBounds(798, 11, 31, 31);
		layeredPane.add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dateDebut=((JTextField)dateDebutChooser.getDateEditor().getUiComponent()).getText();
				String dateFin=((JTextField)dateFinChooser.getDateEditor().getUiComponent()).getText();
			if(dateDebut.equals(""))	{
				JOptionPane.showMessageDialog(null, "Il faut choisir Date D�but", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else if(dateFin.equals("")){
				JOptionPane.showMessageDialog(null, "Il faut choisir Date Fin", "Erreur", JOptionPane.ERROR_MESSAGE);
				
			}else if(combodepot.getSelectedIndex()==-1)
			{
				JOptionPane.showMessageDialog(null, "Il faut choisir le Depot SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
			}else
			
			{
				
				Depot depot=mapDepot.get(combodepot.getSelectedItem());
				listObjetSituationCoutProduction.clear();
				listObjetSituationCoutProduction=productionDAO.listeSituationGlobaleCoutProduction(dateDebutChooser.getDate(), dateFinChooser.getDate(),Constantes.ETAT_OF_TERMINER,depot.getCode());
afficher_tableProd(listObjetSituationCoutProduction);
				
				
				
				
				
				
				
				
			}
		  }
		});
		btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
		dateDebutChooser.setDateFormatString("dd/MM/yyyy");
		
		 
		dateDebutChooser.setBounds(37, 11, 132, 24);
		layeredPane.add(dateDebutChooser);
		dateFinChooser.setDateFormatString("dd/MM/yyyy");
		
		
		dateFinChooser.setBounds(212, 12, 149, 24);
		layeredPane.add(dateFinChooser);
		
		JLabel lblDepot = new JLabel("Depot :");
		lblDepot.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDepot.setBounds(390, 11, 51, 24);
		layeredPane.add(lblDepot);
		
		 combodepot = new JComboBox();
		combodepot.setBounds(438, 12, 209, 24);
		layeredPane.add(combodepot);
		
		JButton btnImprimer = new JButton("Imprimer");
		btnImprimer.setIcon(imgImprimer);
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(table.getRowCount()!=0)
				{
					
					Map parameters = new HashMap();
					String dateDu=((JTextField)dateDebutChooser.getDateEditor().getUiComponent()).getText();
					
					String dateau=((JTextField)dateFinChooser.getDateEditor().getUiComponent()).getText();
					
					parameters.put("date", "Du : "+dateDu +" Au : "+dateau);
					parameters.put("depot", combodepot.getSelectedItem().toString());
				
					JasperUtils.imprimerSituationGlobaleCoutProduction(parameters,table.getModel()); 
					
				}
		
				
				
			}
		});
		btnImprimer.setBounds(410, 505, 174, 24);
		add(btnImprimer);
		
		
		  if(utilisateur.getLogin().equals("admin"))
		  {
			  listDepot=depotdao.findAll();
			  int k=0;
		     	 combodepot.addItem("");
		     	while (k<listDepot.size())
		     	{
		     		Depot depot=listDepot.get(k);
		     		combodepot.addItem(depot.getLibelle());
		     		mapDepot.put(depot.getLibelle(), depot);
		     		k++;
		     		
		     	}
		      
		  }else{
			  Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
			  if(depot!=null)
			  {
				  combodepot.addItem(depot.getLibelle());
				
		     		mapDepot.put(depot.getLibelle(), depot);
			  }
		  }
		 
		 
		  
		  combodepot.setSelectedIndex(-1);
		
		
	
				  		     
				  		 
	}
	
void afficher_tableProd(List<Object[]> listSituationGlobaleCoutProduction)
	{
		intialiserTableau();
		 
			for (int i=0;i<listSituationGlobaleCoutProduction.size();i++)
			{	
				
				Object [] SituationGlobaleCoutProduction=(Object[]) listSituationGlobaleCoutProduction.get(i);

if(SituationGlobaleCoutProduction[0]!=null)
{
	
	Object []ligne={SituationGlobaleCoutProduction[0].toString(),SituationGlobaleCoutProduction[1].toString(),NumberFormat.getNumberInstance(Locale.FRANCE).format(new BigDecimal(SituationGlobaleCoutProduction[2].toString())),NumberFormat.getNumberInstance(Locale.FRANCE).format(new BigDecimal(SituationGlobaleCoutProduction[3].toString())), NumberFormat.getNumberInstance(Locale.FRANCE).format(new BigDecimal(SituationGlobaleCoutProduction[3].toString()).divide(new BigDecimal(SituationGlobaleCoutProduction[2].toString()), 6, RoundingMode.FLOOR)) };

	modeleProd.addRow( ligne);
}
				
			
			
			}
			
		
	}









void intialiserTableau(){
	modeleProd =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code Article","Article", "Quantite","Cout Total","Prix"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		 table.setModel(modeleProd); 
		 table.getColumnModel().getColumn(0).setPreferredWidth(60);
      table.getColumnModel().getColumn(1).setPreferredWidth(160);
      table.getColumnModel().getColumn(2).setPreferredWidth(60);

 
}


}

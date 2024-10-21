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

import main.ProdLauncher;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.DateUtils;
import util.JasperUtils;
import util.Utils;

import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.CompteStockMPDAOImpl;
import dao.daoManager.CompteStockMPDAO;
import dao.daoManager.EmployeDAO;
import dao.entity.CompteStockMP;
import dao.entity.FicheEmploye;

import java.awt.Component;


public class AfficherCompteStockMP extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;

	private JXTable table;
	
	private DefaultTableModel	 modeleMP1;

	private JXTable table1;
	
	
	private ImageIcon imgValider;
	private ImageIcon imgInit;
	private ImageIcon imgImprimer;
	private ImageIcon imgRechercher;
	private JDateChooser dateDebutChooser = new JDateChooser();
	private JDateChooser dateFinChooser = new JDateChooser();
	
	private Map< Integer, String> mapAvance= new HashMap<>();
	private Map< String, BigDecimal> mapParametre = new HashMap<>();
	
	private List<CompteStockMP> listCompteStockMP=new ArrayList<CompteStockMP>();
	
	private CompteStockMPDAO compteStockMPDAO;
	
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AfficherCompteStockMP() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 565);
        try{
        	
        	
        	compteStockMPDAO=new CompteStockMPDAOImpl();
        	

       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
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
				  		     
				  		   modeleMP =new DefaultTableModel(
					  		     	new Object[][] {
					  		     	},
					  		     	new String[] {
					  		     			"Mois","Matière Première", "Quantité"
					  		     	}
					  		     ) {
					  		     	boolean[] columnEditables = new boolean[] {
					  		     			false,false,false
					  		     	};
					  		     	public boolean isCellEditable(int row, int column) {
					  		     		return columnEditables[column];
					  		     	}
					  		     };
					  		     
					  		     
					  		     
					  		 table.setModel(modeleMP); 
					  		 table.getColumnModel().getColumn(0).setPreferredWidth(60);
					         table.getColumnModel().getColumn(1).setPreferredWidth(160);
					         table.getColumnModel().getColumn(2).setPreferredWidth(60);
					      //   intialiserTableau2();
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(9, 65, 782, 417);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	

				  		     	
				  		     		table1 = new JXTable();
				  		     		table1.setShowVerticalLines(false);
				  		     		table1.setSelectionBackground(new Color(51, 204, 255));
				  		     		table1.setRowHeightEnabled(true);
				  		     		table1.setBackground(new Color(255, 255, 255));
				  		     		table1.setHighlighters(HighlighterFactory.createSimpleStriping());
				  		     		table1.setColumnControlVisible(true);
				  		     		table1.setForeground(Color.BLACK);
				  		     		table1.setGridColor(new Color(0, 0, 255));
				  		     		table1.setAutoCreateRowSorter(true);
				  		     		//    table.setBounds(2, 27, 411, 198);
				  		     		table1.setRowHeight(20);
				  		      modeleMP1 =new DefaultTableModel(
				  			     	new Object[][] {
				  			     	},
				  			     	new String[] {
				  			     			"Matière Première", "Quantité"
				  			     	}
				  			     ) {
				  			     	boolean[] columnEditables = new boolean[] {
				  			     			false,false,false
				  			     	};
				  			     	public boolean isCellEditable(int row, int column) {
				  			     		return columnEditables[column];
				  			     	}
				  			     };
				  			     
				  			 table1.setModel(modeleMP1); 
				  			 table1.getColumnModel().getColumn(0).setPreferredWidth(160);
				  	     table1.getColumnModel().getColumn(1).setPreferredWidth(60);
				  				JScrollPane scrollPane_1 = new JScrollPane(table1);
				  				scrollPane_1.setVisible(false);
				  				scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  				scrollPane_1.setBounds(802, 72, 472, 410);
				  				add(scrollPane_1);
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("");
				  		     	titledSeparator.setBounds(9, 49, 782, 30);
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
		btnAfficherStock.setBounds(631, 11, 31, 31);
		layeredPane.add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dateDebut=((JTextField)dateDebutChooser.getDateEditor().getUiComponent()).getText();
				String dateFin=((JTextField)dateFinChooser.getDateEditor().getUiComponent()).getText();
			if(dateDebut.equals(""))	{
				JOptionPane.showMessageDialog(null, "Il faut choisir Date Début", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else if(dateFin.equals("")){
				JOptionPane.showMessageDialog(null, "Il faut choisir Date Fin", "Erreur", JOptionPane.ERROR_MESSAGE);
				
			}else  {
				
			
					
				int moisDebut=DateUtils.getMois(dateDebutChooser.getDate()) ;
				int moisFin =DateUtils.getMois(dateFinChooser.getDate()) ;
				int annee  =DateUtils.getAnnee(dateFinChooser.getDate()) ;;
				
				System.out.println("moisDebut:" +moisDebut);
				System.out.println("moisFin:" +moisFin);
				System.out.println("annee:" +annee);
				
				listCompteStockMP=compteStockMPDAO.findListeByAnneeMois(moisDebut, moisFin, annee);
			//	List<Object> listObject=compteStockMPDAO.findSumByAnneeMois(moisDebut, moisFin, annee);
				
				
				
				
						
						afficher_tableMP(listCompteStockMP);
				
					//	afficher_tableMP_Total(listObject);
			}
		  }
		});
		btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		 
		dateDebutChooser.setBounds(76, 11, 130, 24);
		layeredPane.add(dateDebutChooser);
		
		
		dateFinChooser.setBounds(324, 11, 140, 24);
		layeredPane.add(dateFinChooser);
		
		JButton btnImprimer = new JButton("Imprimer Fiche Compte");
		btnImprimer.setIcon(imgImprimer);
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnImprimer.setBounds(397, 493, 174, 24);
		add(btnImprimer);
		
	
				  		     
				  		 
	}
	
void afficher_tableMP(List<CompteStockMP> listCompteStockMP)
	{
		intialiserTableau();
		 
			for (int i=0;i<listCompteStockMP.size();i++)
			{	
				
				//Object [] ficheEmploye=(Object[]) listFicheEmploye.get(i);
				CompteStockMP compteStockMP=listCompteStockMP.get(i);
				
				
				Object []ligne={compteStockMP.getMois(),compteStockMP.getMatierePremier().getNom(),NumberFormat.getNumberInstance(Locale.FRANCE).format(compteStockMP.getQuantite())};

				modeleMP.addRow( ligne);
			
			}
			
		
	}


void afficher_tableMP_Total(List<Object> listObject)
{
	intialiserTableau2();
	
	String code = "";
	BigDecimal quantite = BigDecimal.ZERO;
	 
		for (int i=0;i<listObject.size();i++)
		{	
			
			//Object [] ficheEmploye=(Object[]) listFicheEmploye.get(i);
			Object object[]=(Object[]) listObject.get(i);
			 if(i%2==0)
				 code= (String) object[i];
			 else 
				 quantite=(BigDecimal) object[i];
			 
			 
			Object []ligne={code,NumberFormat.getNumberInstance(Locale.FRANCE).format(quantite)};

			modeleMP1.addRow( ligne);
		
		}
		
	
}


void intialiserTableau(){
	 modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Mois","Matière Première", "Quantité"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		 table.setModel(modeleMP); 
		 table.getColumnModel().getColumn(0).setPreferredWidth(60);
      table.getColumnModel().getColumn(1).setPreferredWidth(160);
      table.getColumnModel().getColumn(2).setPreferredWidth(60);

 
}

void intialiserTableau2(){
	 modeleMP1 =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Matière Première", "Quantité"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		 table1.setModel(modeleMP1); 
		 table1.getColumnModel().getColumn(0).setPreferredWidth(160);
		 table1.getColumnModel().getColumn(1).setPreferredWidth(60);
  


}
}

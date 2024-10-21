package Equipe;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import main.AuthentificationView;
import main.ProdLauncher;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;

import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.CoutHorsProdEnAttentDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.EmployeDAOImpl;
import dao.daoImplManager.FicheEmployeDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.TavailHorsProdDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.CoutHorsProdEnAttentDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.FicheEmployeDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.TravailHorsProdDAO;
import dao.entity.Articles;
import dao.entity.CoutHorsProdEnAttent;
import dao.entity.Depot;
import dao.entity.Employe;
import dao.entity.FicheEmploye;
import dao.entity.Parametre;
import dao.entity.TravailHorsProd;
import dao.entity.Utilisateur;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


public class AjoutTravailHorsProd extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	private DefaultTableModel modele;
	private DefaultTableModel	 modeleLigneMachine;
	private JXTable table;
	
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgValider;
	
	private JButton btnInitialiser;
	private Utilisateur utilisateur;
	private JTextField txtMatricule;
	private JTextField txtHeureSupp50;
	private JTextField txtDelai;
	private JTextField txtHeureSupp25;
	
	private JDateChooser dateChooser;

	private List<TravailHorsProd> listTravailHorsProd = new ArrayList<TravailHorsProd>();
	private List<FicheEmploye> listFicheEmploye = new ArrayList<FicheEmploye>();
	
	private TravailHorsProdDAO travailHorsPodDAO ;
	private EmployeDAO employeDAO;
	private Employe employe;
	private DepotDAO depotDAO;
	private ParametreDAO parametreDAO;
	private FicheEmployeDAO ficheEmployeDAO;
	  JCheckBox HeuresEnAttent = new JCheckBox("Heures En Attent");
	  private Map< String, Depot> depotMap = new HashMap<>();
	  private CoutHorsProdEnAttentDAO coutHorsProdEnAttentDAO;
	  private JTextField  txtCodeArticle = new JTextField();
	  JComboBox comboArticle = new JComboBox();
	  private ArticlesDAO articleDAO;
	  private Map< String, Articles> mapAricle = new HashMap<>();
	  private Map< String, Articles> mapCodeAricle = new HashMap<>();
	  private List<Articles> listArticle = new ArrayList<Articles>();
	  JComboBox comboNom = new JComboBox();
	  private List<Employe> listEmploye=new ArrayList<Employe>();
	  private Map< String, Employe> mapMatriculeEmploye = new HashMap<>();
		private Map< String, Employe> mapNomEmploye = new HashMap<>();
		 JComboBox comboTypeTravail = new JComboBox();
		 private JTextField txtCommentaire;
		 JLabel labelCommentaire = new JLabel("Commentaire :");
		 JLabel labelCodeArticle = new JLabel("Code Article");
		 JLabel labelArticle = new JLabel("Article:");
		 
	/**
	 * Launch the application.k
	 */


	/**
	 * Create the application.
	 */
	public AjoutTravailHorsProd() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

        setBounds(0, 0, 1284, 701);
        try{
        	
        		txtMatricule=new JTextField();
        		travailHorsPodDAO=new TavailHorsProdDAOImpl();
        		employeDAO=new EmployeDAOImpl();
        		depotDAO=new DepotDAOImpl();
        		parametreDAO=new ParametreDAOImpl();
        		ficheEmployeDAO=new FicheEmployeDAOImpl();
        		utilisateur=AuthentificationView.utilisateur;
        		coutHorsProdEnAttentDAO=new CoutHorsProdEnAttentDAOImpl();
        		articleDAO=new ArticlesDAOImpl();
        		listArticle=articleDAO.findAll();
        		listEmploye=employeDAO.findByDepot(AuthentificationView.utilisateur.getCodeDepot());
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		 	
             
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
             imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             imgValider = new ImageIcon(this.getClass().getResource("/img/valider.png"));
             
          } catch (Exception exp){exp.printStackTrace();}
				  		
				  		   
				  		
				  		
				  			txtMatricule = new JTextField();
				  			txtDelai = new JTextField();
				  			txtHeureSupp25 = new JTextField();
				  			txtHeureSupp50 = new JTextField();
				  			table = new JXTable();
				  			intialiserTableau ();
				  		   JLayeredPane layeredPane = new JLayeredPane();
				  		   layeredPane.setBorder(new MatteBorder(0, 1, 1, 1, (Color) Color.LIGHT_GRAY));
				  		   layeredPane.setBounds(6, 273, 1119, 396);
				  		   add(layeredPane);
				  		   
				  		 txtMatricule.addKeyListener(new KeyAdapter() {
				  			  	@Override
				  			  	public void keyReleased(KeyEvent e)
				  			  	{
				  			  	if(e.getKeyCode()==e.VK_ENTER)
			  		      		{
			  						Employe employe=mapMatriculeEmploye.get(txtMatricule.getText());
			  						if(employe!=null)
			  						{
			  							comboNom.setSelectedItem(employe.getNomafficher());
			  						}else
			  						{
			  							JOptionPane.showMessageDialog(null, "Employe Introuvable !!!!!");
			  							return;
			  						}
			  						
			  		      		}
				  			  	
				  			  	}});
				  		   
				  		   JButton btnSupprimerLigne = new JButton("");
				  		   btnSupprimerLigne.setIcon(imgSupprimer);
				  		   btnSupprimerLigne.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent e) {
				  		   		
				  		   	 try{
								   int row=0;
								   if(table.getSelectedRow()==-1)
									     JOptionPane.showMessageDialog(null, "Il faut sélectionner une Ligne à supprimer!", "Attention", JOptionPane.INFORMATION_MESSAGE);
								   else
								   {
									   
									   int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez Supprimer  cette Ligne?", 
											"Satisfaction", JOptionPane.YES_NO_OPTION);
									 
									   	if(reponse == JOptionPane.YES_OPTION ){
									   
									   		row = table.getSelectedRow();
									   		
									   		TravailHorsProd travailHorsProd =listTravailHorsProd.get(row);
									   		DateFormat dateFormat = new SimpleDateFormat("ddMMyy");
									   		String dateSituationCode =dateFormat.format(dateChooser.getDate());
									   		if(travailHorsProd.isHeuresEnAttente()==true)
									   		{
									   			if(travailHorsProd.getArticles()!=null)
									   			{
									   				CoutHorsProdEnAttent coutHorsProdEnAttentTmp=coutHorsProdEnAttentDAO.findByCodeByEmployeByArticle (dateSituationCode , travailHorsProd.getEmploye(),travailHorsProd.getArticles());		
										   			
										   			if(coutHorsProdEnAttentTmp!=null)
										   			{
										   				
										   				if(coutHorsProdEnAttentTmp.getProduction()!=null)
										   				{
										   					if(coutHorsProdEnAttentTmp.getProduction().getStatut().equals(ETAT_OF_TERMINER))
										   					{
										   						
											   					JOptionPane.showMessageDialog(null, "le Cout d'employer déja entrer dans le cout de la production Num OF "+coutHorsProdEnAttentTmp.getProduction().getNumOF());
	                                                              return;
	                                                              
	                                                              
										   					}else
										   					{
										   						
										   						coutHorsProdEnAttentDAO.delete(coutHorsProdEnAttentTmp);
										   					}
										   					
										   					
										   				}
										   				
										   				
										   				
										   			}
									   			}else
									   			{
									   				CoutHorsProdEnAttent coutHorsProdEnAttentTmp=coutHorsProdEnAttentDAO.findByCodeByEmployeByArticle (dateSituationCode , travailHorsProd.getEmploye(),null);		
if(coutHorsProdEnAttentTmp!=null)
{
	coutHorsProdEnAttentDAO.delete(coutHorsProdEnAttentTmp);
}
									   				
									   			}
									   	
									   			
									   			
									   			
									   			
									   		}
									   		
									   		
									   		travailHorsPodDAO.delete(travailHorsProd);
									   		
									   		listTravailHorsProd.remove(row);
									   		
									   											   		
									   	
									   		JOptionPane.showMessageDialog(null, "Suppression avec succès  ", "Succès", JOptionPane.INFORMATION_MESSAGE);
									   		afficherList(listTravailHorsProd);
									   		table.setRowSorter(null);
									   		modele.removeRow(row);
										}
								   }
					                }catch (Exception e1){
					                	}
				  		   		
				  		   		
				  		   	}
				  		   });
				  		   btnSupprimerLigne.setBounds(1049, 64, 60, 26);
				  		   layeredPane.add(btnSupprimerLigne);
				  		   
				  		   		
				  		   		table.setSelectionBackground(new Color(51, 204, 255));
				  		   		table.setRowHeightEnabled(true);
				  		   		table.setBackground(new Color(255, 255, 255));
				  		   		table.setHighlighters(HighlighterFactory.createSimpleStriping());
				  		   		table.setColumnControlVisible(true);
				  		   		table.setForeground(Color.BLACK);
				  		   		table.setGridColor(new Color(0, 0, 255));
				  		   		table.setAutoCreateRowSorter(true);
				  		   		table.setModel(modeleLigneMachine);
				  		   		table.setBounds(2, 27, 411, 198);
				  		   		table.setRowHeight(20);
				  		   		JScrollPane scrollPane = new JScrollPane(table);
				  		   		scrollPane.setBounds(22, 11, 1004, 337);
				  		   		layeredPane.add(scrollPane);
				  		   		scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   		
				  		   		JButton btnValiderAjoutMachine = new JButton("Valider Ajout");
				  		   		btnValiderAjoutMachine.setBounds(364, 359, 148, 26);
				  		   		layeredPane.add(btnValiderAjoutMachine);
				  		   		btnValiderAjoutMachine.setIcon(imgValider);
				  		   		
				  		   		btnValiderAjoutMachine.addActionListener(new ActionListener() {
				  		   			public void actionPerformed(ActionEvent e) {
				  		   				validerAjout(listTravailHorsProd);
				  		   				
				  		   			JOptionPane.showMessageDialog(null, "Validé avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
				  		   			intialiserAll();
				  		   			
				  		   		 
				  		  		  dateChooser.setDate(null);
				  		   			
				  		   			}
				  		   		});
				  		   
				  		   JLayeredPane layeredPane_1 = new JLayeredPane();
				  		   layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   layeredPane_1.setBounds(6, 11, 1119, 251);
				  		   add(layeredPane_1);
				  		   
				  		    dateChooser = new JDateChooser();
				  		    dateChooser.addPropertyChangeListener(new PropertyChangeListener() {
				  		    	public void propertyChange(PropertyChangeEvent arg0) {
				  		    		if(dateChooser.getDate()!=null)
				  		    		{
				  		    			Articles articles=null;
					  		    		 
					  		    			listTravailHorsProd=travailHorsPodDAO.findByDateSitutaionByArticle(dateChooser.getDate(), dateChooser.getDate(),HeuresEnAttent.isSelected(),articles);
					  		     			
					  		     			afficherList(listTravailHorsProd);	
					  		    	 
				  		    		} 
				  		    	
				  		    		
				  		    		
				  		    	}
				  		    });
				  		   
				  		    dateChooser.setBounds(97, 62, 191, 26);
				  		    layeredPane_1.add(dateChooser);
				  		    dateChooser.setDateFormatString("dd/MM/yyyy");
				  		    
				  		    JLabel lblDateNaissance = new JLabel("Date :");
				  		    lblDateNaissance.setBounds(22, 62, 82, 26);
				  		    layeredPane_1.add(lblDateNaissance);
				  		    lblDateNaissance.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		    
				  		    JLabel lblCodeLigne = new JLabel("Matricule : ");
				  		    lblCodeLigne.setBounds(22, 99, 114, 26);
				  		    layeredPane_1.add(lblCodeLigne);
				  		    lblCodeLigne.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		    
				  		   
				  		    txtMatricule.setBounds(95, 100, 259, 26);
				  		    layeredPane_1.add(txtMatricule);
				  		    util.Utils.copycoller(txtMatricule);
				  		    txtMatricule.setColumns(10);
				  		    
				  		    JLabel lblNom = new JLabel("Nom :");
				  		    lblNom.setBounds(364, 98, 63, 26);
				  		    layeredPane_1.add(lblNom);
				  		    lblNom.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		 
				  		    txtDelai.setBounds(834, 99, 236, 26);
				  		    layeredPane_1.add(txtDelai);
				  		    util.Utils.copycoller(txtDelai);
				  		    txtDelai.setColumns(10);
				  		    
				  		    JLabel lblNCnss = new JLabel("D\u00E9lai Travill\u00E9 :");
				  		    lblNCnss.setBounds(738, 99, 130, 26);
				  		    layeredPane_1.add(lblNCnss);
				  		    lblNCnss.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		
				  		    txtHeureSupp25.setBounds(116, 153, 159, 26);
				  		    layeredPane_1.add(txtHeureSupp25);
				  		    util.Utils.copycoller(txtHeureSupp25);
				  		    txtHeureSupp25.setColumns(10);
				  		    
				  		    JLabel lblLieuNaissance = new JLabel("Heure Supp 25%:");
				  		    lblLieuNaissance.setBounds(10, 153, 114, 26);
				  		    layeredPane_1.add(lblLieuNaissance);
				  		    lblLieuNaissance.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		
				  		    txtHeureSupp50.setBounds(412, 153, 183, 26);
				  		    layeredPane_1.add(txtHeureSupp50);
				  		    util.Utils.copycoller(txtHeureSupp50);
				  		    txtHeureSupp50.setColumns(10);
				  		    
				  		    JLabel lblHeureSupp = new JLabel("Heure Supp 50% :");
				  		    lblHeureSupp.setBounds(285, 152, 144, 26);
				  		    layeredPane_1.add(lblHeureSupp);
				  		    lblHeureSupp.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		    btnInitialiser = new JButton("Initialiser");
				  		    btnInitialiser.setBounds(497, 214, 95, 26);
				  		    layeredPane_1.add(btnInitialiser);
				  		    btnInitialiser.addActionListener(new ActionListener() {
				  		    	public void actionPerformed(ActionEvent e) {
				  		    		
				  		    	intialiserAll();
				  		    		
				  		    	}
				  		    });
				  		    
				  		    btnInitialiser.setIcon(imgInit);
				  		    btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		    
				  		    JButton btnAjoutAligne = new JButton("Ajouter");
				  		    btnAjoutAligne.setBounds(398, 214, 89, 26);
				  		    layeredPane_1.add(btnAjoutAligne);
				  		    btnAjoutAligne.setIcon(imgAjouter);
				  		    
				  		      labelCodeArticle = new JLabel("Code Article");
				  		    labelCodeArticle.setBounds(339, 60, 82, 26);
				  		    layeredPane_1.add(labelCodeArticle);
				  		    labelCodeArticle.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		    
				  		    txtCodeArticle = new JTextField();
				  		    txtCodeArticle.setBounds(412, 60, 102, 26);
				  		    layeredPane_1.add(txtCodeArticle);
				  		    txtCodeArticle.addKeyListener(new KeyAdapter() {
				  		    	@Override
				  		    	public void keyReleased(KeyEvent e) {
				  		    		
				  		    			if (e.getKeyCode() == e.VK_ENTER)
				  		    				  			  		{
				  		    				
				  		    				if(!txtCodeArticle.getText().equals(""))
						  		    		{
				  		    				
				  		    				Articles articles=mapCodeAricle.get(txtCodeArticle.getText());
				  		    				if(articles!=null)
				  		    				{
				  		    					comboArticle.setSelectedItem(articles.getLiblle());
				  		    				}else
				  		    				{
				  		    					comboArticle.setSelectedItem("");
				  		    				}
				  		    				
				  		    				
				  		    				  			  	}else
				  		    	  		    				{
				  		    	  		    					comboArticle.setSelectedItem("");
				  		    	  		    				}
				  		    				
				  		    				  		    			
				  		    			 } 
				  		    			
				  		    		
				  		    		
				  		    		
				  		    		
				  		    	}
				  		    });
				  		    txtCodeArticle.setColumns(10);
				  		    
				  		      labelArticle = new JLabel("Article:");
				  		    labelArticle.setBounds(524, 61, 102, 26);
				  		    layeredPane_1.add(labelArticle);
				  		    labelArticle.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		    
				  		     comboArticle = new JComboBox();
				  		     comboArticle.setBounds(572, 62, 289, 26);
				  		     layeredPane_1.add(comboArticle);
				  		     comboArticle.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent arg0) {
				  		     		if(comboArticle.getSelectedIndex()!=-1)
				  		     		{
				  		     			
				  		     		if(!comboArticle.getSelectedItem().equals(""))	
				  		     		{
				  		     			
				  		     	Articles articles=mapAricle.get(comboArticle.getSelectedItem().toString())	;
				  		     	if(articles!=null)
				  		     	{
				  		     		
				  		     		txtCodeArticle.setText(articles.getCodeArticle());
				  		     		
				  		     		if(dateChooser.getDate()!=null)
				  		     		{
				  		     			listTravailHorsProd=travailHorsPodDAO.findByDateSitutaionByArticle(dateChooser.getDate(), dateChooser.getDate(),HeuresEnAttent.isSelected(),null);
				  		     			
				  		     			afficherList(listTravailHorsProd);	
				  		     		}
				  		     		
				  		     				
				  		     	}else
				  		     	{
				  		     		txtCodeArticle.setText("");
				  		     	}
				  		     			
				  		     		
				  		     		}else
					  		     	{
					  		     		txtCodeArticle.setText("");
					  		     	}
				  		     			
				  		     			
				  		     			
				  		     			
				  		     			
				  		     		}else
					  		     	{
					  		     		txtCodeArticle.setText("");
					  		     	}
				  		     		
				  		     		
				  		     		
				  		     		
				  		     	}
				  		     });
				  		     
				  		     
				  		     comboArticle.addItem("");			  		      
				  		     
				  		     comboArticle.setSelectedItem("");
				  		     
				  		      comboNom = new JComboBox();
				  		      comboNom.addItemListener(new ItemListener() {
				  		      	public void itemStateChanged(ItemEvent arg0) {
				  		      		

				  		      		

				  		   	 		
				  		   	 		if(!comboNom.getSelectedItem().equals(""))
				  		   	 		{
				  		   	 			
				  		   	 			Employe employe=mapNomEmploye.get(comboNom.getSelectedItem());
				  		   	 			if(employe!=null)
				  		   	 			{
				  		   	 				txtMatricule.setText(employe.getMatricule());
				  		   	 			}else
				  		   	 			{
				  		   	 				JOptionPane.showMessageDialog(null, "Employe Introuvable");
				  		   	 				return;
				  		   	 			}
				  		   	 			
				  		   	 			
				  		   	 			
				  		   	 		}
				  		   	 		
				  		   	 	
				  		      		
				  		      		
				  		      		
				  		      		
				  		      	
				  		      		
				  		      	}
				  		      });
				  		     comboNom.setBounds(437, 99, 285, 26);
				  		     layeredPane_1.add(comboNom);
				  		   AutoCompleteDecorator.decorate(comboNom);
				  		    
				  		      btnAjoutAligne.addActionListener(new ActionListener() {
				  		      	public void actionPerformed(ActionEvent e) {
				  		      		if(comboTypeTravail.getSelectedItem()!=null)
				  		      		{
				  		      			
				  		      		if(comboTypeTravail.getSelectedItem().toString().equals(""))
				  		      		{
				  		      		JOptionPane.showMessageDialog(null, "Veuillez Selection Le Type De Travail SVP!", "Attention", JOptionPane.INFORMATION_MESSAGE);
			  		     			return;
				  		      			
				  		      		}
				  		      			
				  		      			
				  		      		}else
				  		      		{
				  		      		JOptionPane.showMessageDialog(null, "Veuillez Selection Le Type De Travail SVP!", "Attention", JOptionPane.INFORMATION_MESSAGE);
			  		     			return;
				  		      		}
				  		      		
				  		      	 if (dateChooser.getDate()==null)
				  		     		{
				  		     			JOptionPane.showMessageDialog(null, "Il faut remplir la date!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     			return;
				  		     		}
				  		      	 
				  		     			
				  		      		BigDecimal heureSupp25=new BigDecimal(0);
				  		      		BigDecimal heureSupp50=new BigDecimal(0);
				  		      		BigDecimal coutHeureSupp25=new BigDecimal(0);
				  		      		BigDecimal coutHeureSupp50=new BigDecimal(0);
				  		      		BigDecimal coutHoraire=new BigDecimal(0);
				  		      		BigDecimal delaiEmploye=new BigDecimal(0);
				  		      		Parametre parametre =parametreDAO.findByCode(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION);
				  		      	Parametre parametreCoutHoraire =parametreDAO.findByDateByLibelle(dateChooser.getDate(), Constantes.PARAMETRE_LIBELLE_COUT_HORAIRE_CNSS);;
				  		     		Articles articles=mapAricle.get(comboArticle.getSelectedItem());
				  		     		
				  		     	if(comboTypeTravail.getSelectedItem().toString().equals(Constantes.TYPE_TRAVAIL_HORS_PRODUCTION_PRODUCTION))	
				  		     	{
				  		     		
				  		     		if(articles==null)
				  		     		{
				  		     			JOptionPane.showMessageDialog(null, "Veuillez Selection l'Artricle De Production SVP!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     			return;
				  		     		}
				  		     	}else if(comboTypeTravail.getSelectedItem().toString().equals(Constantes.TYPE_TRAVAIL_HORS_PRODUCTION_AUTRES))
				  		     	{
				  		     		
				  		     		if(txtCommentaire.getText().equals(""))
				  		     		{
				  		     			JOptionPane.showMessageDialog(null, "Veuillez Entrer le Commentaire SVP!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     			return;
				  		     		}
				  		     	}
				  		     		 
				  		     	
				  		     		
					  		    	if(txtMatricule.getText().equals(""))
			  		     			
					  		    	{
					  		    		JOptionPane.showMessageDialog(null, "Il faut remplir le Matricule!", "Attention", JOptionPane.INFORMATION_MESSAGE);
					  		    		return;
					  		    	}
					  		    	
			  		     		 if (comboNom.getSelectedItem().equals(""))
			  		     		{
			  		     			JOptionPane.showMessageDialog(null, "Il faut Selectionner le nom!", "Attention", JOptionPane.INFORMATION_MESSAGE);
					  		    	return;
			  		     		}
			  		     			 
			  		     		 
			  		     	 
			  		     			
			  		     		 if (txtDelai.getText().equals(""))
			  		     		{
			  		     			JOptionPane.showMessageDialog(null, "Il faut remplir le délai travaillé!", "Attention", JOptionPane.INFORMATION_MESSAGE);
			  		     			return;
			  		     			
			  		     		}
			  		     			 
			  		     		 
			  		     //	Machine machine = new Machine();
			  		     			
			  		     			//************************************/
			  		     			if(!txtHeureSupp25.getText().equals("")){
			  		     				heureSupp25=new BigDecimal(txtHeureSupp25.getText());
			  		     				coutHeureSupp25=heureSupp25.multiply(COUT_HEURE_SUPPLEMENTAIRE_25);
			  		     			}
			  		     			if(!txtHeureSupp50.getText().equals("")){
			  		     				heureSupp50=new BigDecimal(txtHeureSupp50.getText());
			  		     				coutHeureSupp50=heureSupp50.multiply(COUT_HEURE_SUPPLEMENTAIRE_50);
			  		     			}
			  		     			delaiEmploye=new BigDecimal(txtDelai.getText());
			  		     			coutHoraire=delaiEmploye.multiply(parametreCoutHoraire.getValeur());
			  		     			
			  		     			DateFormat dateFormat = new SimpleDateFormat("ddMMyy");
			  		     			String dateSituationCode =dateFormat.format(dateChooser.getDate());
			  		     			employe=mapMatriculeEmploye.get(txtMatricule.getText());
			  		     			TravailHorsProd travailHorsProd = new TravailHorsProd();
			  		     			travailHorsProd.setCode(dateSituationCode);
			  		     			travailHorsProd.setDateCreation(new Date());
			  		     			travailHorsProd.setUtilisateurCreation(utilisateur);
			  		     			travailHorsProd.setDateSituation(dateChooser.getDate());
			  		     			travailHorsProd.setDelai(delaiEmploye);
			  		     			travailHorsProd.setCoutHoraire(parametreCoutHoraire.getValeur());			  		     			 
			  		     			travailHorsProd.setEmploye(employe);
			  		     			travailHorsProd.setTypeResEmploye(employe.getTypeResEmploye());
			  		     			travailHorsProd.setHeureSupp25(heureSupp25);
			  		     			travailHorsProd.setHeureSupp50(heureSupp50);
			  		     			travailHorsProd.setDepot(employe.getDepot());
			  		     			travailHorsProd.setHeuresEnAttente(true);
			  		     			travailHorsProd.setArticles(articles);
			  		     			travailHorsProd.setTypeTravail(comboTypeTravail.getSelectedItem().toString());
			  		     			travailHorsProd.setCommentaire(txtCommentaire.getText());
			  		     			
			  		     			//*************************fiche employe *********///
			  		     			
			  		     			
			  		     			//***********FIN TRAITEMENT FICHE EMPLOYE***********////
			  		     			TravailHorsProd travailHorsProd2 =travailHorsPodDAO.findByEmployeCodeByArticle(employe.getId(), dateSituationCode,articles);
			  		     			
			  		     			
			  		     			if(travailHorsProd2!=null){
			  		     				JOptionPane.showMessageDialog(null, "Employe existe déjà", "Attention", JOptionPane.WARNING_MESSAGE);
			  		     			}
			  		     			else {
			  		     				travailHorsPodDAO.add(travailHorsProd);
			  		     				FicheEmploye ficheEmploye=ficheEmployeDAO.findByPeriodeDateSitutation(dateChooser.getDate(), employe.getId());
			  		     				if(ficheEmploye!=null){
			  		     					
			  		     					ficheEmploye.setHeureSupp25(ficheEmploye.getHeureSupp25().add(heureSupp25));
					  		     			ficheEmploye.setHeureSupp50(ficheEmploye.getHeureSupp50().add(heureSupp50));
					  		     			//ficheEmploye.setCoutSupp25(ficheEmploye.getCoutSupp25().add(coutHeureSupp25));
					  		     			//ficheEmploye.setCoutSupp50(ficheEmploye.getCoutSupp50().add(coutHeureSupp50));
					  		     			ficheEmploye.setDelaiEmploye(ficheEmploye.getDelaiEmploye().add(delaiEmploye));
					  		     			//ficheEmploye.setCoutTotal(ficheEmploye.getCoutTotal().add(coutHoraire));
					  		     			ficheEmploye.setNumOF(ficheEmploye.getNumOF()+"-"+dateSituationCode);
					  		     			ficheEmployeDAO.edit(ficheEmploye);
			  		     					
			  		     				}else {
			  		     					ficheEmploye=new FicheEmploye();
			  		     					ficheEmploye.setDelaiEmploye(delaiEmploye);
			  		     				//	ficheEmploye.setCoutTotal(coutHoraire);
					  		     			ficheEmploye.setDateSituation(dateChooser.getDate());
					  		     			ficheEmploye.setEmploye(employe);
					  		     			ficheEmploye.setHeureSupp25(heureSupp25);
					  		     			ficheEmploye.setHeureSupp50(coutHeureSupp50);
					  		     			//ficheEmploye.setCoutSupp25(coutHeureSupp25);
					  		     		//	ficheEmploye.setCoutSupp50(coutHeureSupp50);
					  		     			ficheEmploye.setRemise(parametre.getValeur());
					  		     			ficheEmploye.setNumOF(dateSituationCode);
					  		     			ficheEmployeDAO.add(ficheEmploye);
			  		     					
			  		     				}
			  		     				
			  		     				
			  		     				listTravailHorsProd=travailHorsPodDAO.findByDateSitutaionByArticle(dateChooser.getDate(), dateChooser.getDate(),true,null);
				  		     			
				  		     			afficherList(listTravailHorsProd);
			  		     				
			  		     			}
			  		     			
			  		     			intialiser();
			  		     	//JOptionPane.showMessageDialog(null, "L'Equipe a été ajoutée avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
			  		     	//intialiser ();
			  		     		 
					  		    		
					  		    	
				  		      	}
						  		     	
				  		      });
	for(int i=0;i<listArticle.size();i++)
	{
		
	Articles articles=	listArticle.get(i);
		
	comboArticle.addItem(articles.getLiblle());	
	mapAricle.put(articles.getLiblle(), articles);
	mapCodeAricle.put(articles.getCodeArticle(), articles);
		
		
	}
	
	comboNom.addItem("");
	  for(int i=0;i<listEmploye.size();i++)
	  {
		  
		Employe employe=listEmploye.get(i);  
		comboNom.addItem(employe.getNomafficher());
		
		  mapMatriculeEmploye.put(employe.getMatricule(), employe);
	 mapNomEmploye.put(employe.getNomafficher(), employe);
	  
	  
	  } 
	  
	  
	  comboNom.setSelectedItem("");
	  
	  JLabel lblTypeTravail = new JLabel("Type Travail");
	  lblTypeTravail.setFont(new Font("Tahoma", Font.PLAIN, 12));
	  lblTypeTravail.setBounds(22, 11, 102, 26);
	  layeredPane_1.add(lblTypeTravail);
	  
	    comboTypeTravail = new JComboBox();
	    comboTypeTravail.addItemListener(new ItemListener() {
	    	public void itemStateChanged(ItemEvent arg0) {
	    		
	    		
	    		if(!comboTypeTravail.getSelectedItem().equals(""))
	    		{
	    			
	    			
	    			if(comboTypeTravail.getSelectedItem().toString().equals(Constantes.TYPE_TRAVAIL_HORS_PRODUCTION_PRODUCTION))
	    			{
	    				
	    				comboArticle.setSelectedItem("");
	    				txtCodeArticle.setText("");
	    				comboArticle.setVisible(true);
	    				txtCodeArticle.setVisible(true);
	    				labelCommentaire.setVisible(false);
	    				txtCommentaire.setText("");
	    				txtCommentaire.setVisible(false);
	    				labelCodeArticle.setVisible(true);
	    				labelArticle.setVisible(true);
	    				labelCommentaire.setVisible(false);
	    			}else if(comboTypeTravail.getSelectedItem().toString().equals(Constantes.TYPE_TRAVAIL_HORS_PRODUCTION_AUTRES))
	    			 {
	    				
	    				comboArticle.setSelectedItem("");
	    				txtCodeArticle.setText("");
	    				comboArticle.setVisible(false);
	    				txtCodeArticle.setVisible(false);
	    				labelCommentaire.setVisible(true);
	    				txtCommentaire.setText("");
	    				txtCommentaire.setVisible(true);
	    				labelCodeArticle.setVisible(false);
	    				labelArticle.setVisible(false);
	    				labelCommentaire.setVisible(true);
	    			 }
	    			
	    		}
	    		
	    		
	    	}
	    });
	  comboTypeTravail.setBounds(107, 12, 252, 26);
	  layeredPane_1.add(comboTypeTravail);
	  
	  
	comboTypeTravail.addItem("");
	comboTypeTravail.addItem(Constantes.TYPE_TRAVAIL_HORS_PRODUCTION_PRODUCTION);
	comboTypeTravail.addItem(Constantes.TYPE_TRAVAIL_HORS_PRODUCTION_AUTRES); 
	
	comboTypeTravail.setSelectedItem("");
	
	  labelCommentaire = new JLabel("Commentaire :");
	labelCommentaire.setFont(new Font("Tahoma", Font.PLAIN, 12));
	labelCommentaire.setBounds(619, 152, 144, 26);
	layeredPane_1.add(labelCommentaire);
	
	txtCommentaire = new JTextField();
	txtCommentaire.setColumns(10);
	txtCommentaire.setBounds(724, 153, 205, 26);
	layeredPane_1.add(txtCommentaire);
		
	}
	

	
	void afficherList(List<TravailHorsProd> listTravailHorsProd)
	{

		intialiserTableau ();
		  int i=0;
			while(i<listTravailHorsProd.size())
			{	
				
				String dateEntre="";
				TravailHorsProd travailHorsProd=listTravailHorsProd.get(i);
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
  				Date dateStituation = travailHorsProd.getDateSituation();	
  				String dateStitua=dateFormat.format(dateStituation);
  				String commentaire="";
if(travailHorsProd.getCommentaire()!=null)
{
	commentaire=travailHorsProd.getCommentaire();
}
  				if(travailHorsProd.getArticles()!=null)
  				{
  					Object []ligne={travailHorsProd.getTypeTravail(), dateStitua,travailHorsProd.getEmploye().getMatricule(),travailHorsProd.getEmploye().getNom(),travailHorsProd.getArticles().getCodeArticle(),travailHorsProd.getArticles().getLiblle(),travailHorsProd.getDelai(),travailHorsProd.getHeureSupp25(),travailHorsProd.getHeureSupp50(),commentaire};

  					modeleLigneMachine.addRow(ligne);
  				}else
  				{
  					Object []ligne={travailHorsProd.getTypeTravail(),dateStitua,travailHorsProd.getEmploye().getMatricule(),travailHorsProd.getEmploye().getNom(),"","",travailHorsProd.getDelai(),travailHorsProd.getHeureSupp25(),travailHorsProd.getHeureSupp50(),commentaire};

  					modeleLigneMachine.addRow(ligne);
  				}
				
				
				i++;
			}

			table.setModel(modeleLigneMachine); 

	}
	void intialiser (){
		
		comboNom.setSelectedItem("");;
		txtMatricule.setText("");
		 //txtDesignation.setText("");
		txtHeureSupp50.setText("");
	//	dateChooser.setDate(null);
		txtDelai.setText("");
		txtHeureSupp25.setText("");
		txtCommentaire.setText("");
	
		
	}
	
void intialiserAll (){
		
	
	listTravailHorsProd= new ArrayList<TravailHorsProd>();
	afficherList(listTravailHorsProd);
	intialiser ();
	intialiserTableau();
	HeuresEnAttent.setSelected(false);
	comboTypeTravail.setSelectedItem("");
	}
void intialiserTableau (){
	
	modeleLigneMachine =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Type Travail","Date","Matricule","Nom","Code Article","Article","Délai","Heure Supp 25","Heure Supp 50","Commentaire"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false,false,false,false,false,false,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		     table.setModel(modeleLigneMachine); 
		     table.getColumnModel().getColumn(0).setPreferredWidth(60);
		     table.getColumnModel().getColumn(1).setPreferredWidth(40);
		     table.getColumnModel().getColumn(2).setPreferredWidth(160);
		     table.getColumnModel().getColumn(3).setPreferredWidth(40);
		     table.getColumnModel().getColumn(4).setPreferredWidth(260);
		     table.getColumnModel().getColumn(5).setPreferredWidth(40);
		     table.getColumnModel().getColumn(6).setPreferredWidth(40);
		     table.getColumnModel().getColumn(7).setPreferredWidth(40);
		     table.getColumnModel().getColumn(8).setPreferredWidth(70);
		     table.getColumnModel().getColumn(9).setPreferredWidth(70);
	 
}

boolean chercherEmploye (List<TravailHorsProd> listTravailHorsProd, TravailHorsProd travailHorsProd){
	
	for(TravailHorsProd tavailHorsProdTmp : listTravailHorsProd){
		
		if(tavailHorsProdTmp.getEmploye().getMatricule().equals(travailHorsProd.getEmploye().getMatricule()))
			return true;
			}
		return false;
	}

void validerAjout (List<TravailHorsProd> listTravailHorsProd){
	DateFormat dateFormat = new SimpleDateFormat("ddMMyy");
		String dateSituationCode =dateFormat.format(dateChooser.getDate());
	BigDecimal coutTotal=BigDecimal.ZERO;
	BigDecimal heuresTotal=BigDecimal.ZERO;
	Parametre heure=parametreDAO.findByDateByLibelle(dateChooser.getDate(), Constantes.PARAMETRE_LIBELLE_COUT_HORAIRE_CNSS);
	 
	
	Depot depot=null;
			for(TravailHorsProd tavailHorsProdTmp : listTravailHorsProd){
					
			 
					tavailHorsProdTmp.setHeuresEnAttente(true);
					coutTotal	=coutTotal.add(tavailHorsProdTmp.getDelai().multiply(heure.getValeur()).add(tavailHorsProdTmp.getHeureSupp25().multiply(COUT_HEURE_SUPPLEMENTAIRE_25)).add(tavailHorsProdTmp.getHeureSupp50().multiply(COUT_HEURE_SUPPLEMENTAIRE_50)));
					heuresTotal=heuresTotal.add(tavailHorsProdTmp.getDelai().add(tavailHorsProdTmp.getHeureSupp25().add(tavailHorsProdTmp.getHeureSupp50())));
				
/////////////////////////////////////////////////////////////////////////////////////////////////  Ajouter Cout Hors Production En Attente   ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						if(tavailHorsProdTmp.getTypeTravail().equals(Constantes.TYPE_TRAVAIL_HORS_PRODUCTION_PRODUCTION))
						{
							CoutHorsProdEnAttent coutHorsProdEnAttentTmp=coutHorsProdEnAttentDAO.findByCodeByEmployeByArticle(dateSituationCode, tavailHorsProdTmp.getEmploye(),tavailHorsProdTmp.getArticles());
							
							if(coutHorsProdEnAttentTmp==null)
							{
								
								CoutHorsProdEnAttent coutHorsProdEnAttent=new CoutHorsProdEnAttent();
								coutHorsProdEnAttent.setDateSituation(dateChooser.getDate());
								coutHorsProdEnAttent.setCode(dateSituationCode);							
								coutHorsProdEnAttent.setDateCreation(new Date());
								coutHorsProdEnAttent.setEtat(COUT_HORS_PRODUCTION_EN_ATTENT);							
								coutHorsProdEnAttent.setUtilisateurCreation(utilisateur);
								coutHorsProdEnAttent.setTypeTravail(tavailHorsProdTmp.getTypeTravail());
								if(tavailHorsProdTmp.getCommentaire()!=null)
								{
									coutHorsProdEnAttent.setCommentaire(tavailHorsProdTmp.getCommentaire());
								}else
								{
									coutHorsProdEnAttent.setCommentaire("");
								}
								
								coutHorsProdEnAttent.setCoutHoraire25( COUT_HEURE_SUPPLEMENTAIRE_25);
								coutHorsProdEnAttent.setCoutHoraire50(COUT_HEURE_SUPPLEMENTAIRE_50);
								
								coutHorsProdEnAttent.setCoutHoraire(heure.getValeur());
								
								
								coutHorsProdEnAttent.setDelaiEmploye(tavailHorsProdTmp.getDelai());
								
								coutHorsProdEnAttent.setHeure25(tavailHorsProdTmp.getHeureSupp25());
								coutHorsProdEnAttent.setHeure50(tavailHorsProdTmp.getHeureSupp50());
								
								coutHorsProdEnAttent.setEmploye(tavailHorsProdTmp.getEmploye());
								coutHorsProdEnAttent.setTypeResEmploye(tavailHorsProdTmp.getEmploye().getTypeResEmploye());
								
								coutHorsProdEnAttent.setCoutSupp25 (tavailHorsProdTmp.getHeureSupp25().multiply(COUT_HEURE_SUPPLEMENTAIRE_25));
								coutHorsProdEnAttent.setCoutSupp50 (tavailHorsProdTmp.getHeureSupp50().multiply(COUT_HEURE_SUPPLEMENTAIRE_50));
								
								coutHorsProdEnAttent.setCoutTotal(coutHorsProdEnAttent.getDelaiEmploye().multiply(coutHorsProdEnAttent.getCoutHoraire()));
								
								coutHorsProdEnAttent.setArticles(tavailHorsProdTmp.getArticles());
								
								
								if(depot!=null)
								{
									coutHorsProdEnAttent.setDepot(depot);
								}else
								{
									Depot depotTmp=depotDAO.findByCode(utilisateur.getCodeDepot());
									coutHorsProdEnAttent.setDepot(depotTmp);
								}
								coutHorsProdEnAttentDAO.add(coutHorsProdEnAttent);
							}
									
								
						
		 
						
						
						depot=tavailHorsProdTmp.getDepot();
						
						TravailHorsProd travailHorsProd =travailHorsPodDAO.findByEmployeCodeByArticle (tavailHorsProdTmp.getEmploye().getId(),dateSituationCode,tavailHorsProdTmp.getArticles());
						if(travailHorsProd==null)
						{
							travailHorsPodDAO.add(tavailHorsProdTmp);
						}
						}else
						{
							
							

							CoutHorsProdEnAttent coutHorsProdEnAttentTmp=coutHorsProdEnAttentDAO.findByCodeByEmployeByArticle(dateSituationCode, tavailHorsProdTmp.getEmploye(),null);
							
							if(coutHorsProdEnAttentTmp==null)
							{
								
								CoutHorsProdEnAttent coutHorsProdEnAttent=new CoutHorsProdEnAttent();
								coutHorsProdEnAttent.setDateSituation(dateChooser.getDate());
								coutHorsProdEnAttent.setCode(dateSituationCode);							
								coutHorsProdEnAttent.setDateCreation(new Date());
								coutHorsProdEnAttent.setEtat(COUT_HORS_PRODUCTION_VALIDER);							
								coutHorsProdEnAttent.setUtilisateurCreation(utilisateur);
								coutHorsProdEnAttent.setTypeTravail(tavailHorsProdTmp.getTypeTravail());
								if(tavailHorsProdTmp.getCommentaire()!=null)
								{
									coutHorsProdEnAttent.setCommentaire(tavailHorsProdTmp.getCommentaire());
								}else
								{
									coutHorsProdEnAttent.setCommentaire("");
								}
								coutHorsProdEnAttent.setCoutHoraire25( COUT_HEURE_SUPPLEMENTAIRE_25);
								coutHorsProdEnAttent.setCoutHoraire50(COUT_HEURE_SUPPLEMENTAIRE_50);
								
								coutHorsProdEnAttent.setCoutHoraire(heure.getValeur());
								
								
								coutHorsProdEnAttent.setDelaiEmploye(tavailHorsProdTmp.getDelai());
								
								coutHorsProdEnAttent.setHeure25(tavailHorsProdTmp.getHeureSupp25());
								coutHorsProdEnAttent.setHeure50(tavailHorsProdTmp.getHeureSupp50());
								
								coutHorsProdEnAttent.setEmploye(tavailHorsProdTmp.getEmploye());
								coutHorsProdEnAttent.setTypeResEmploye(tavailHorsProdTmp.getEmploye().getTypeResEmploye());
								
								coutHorsProdEnAttent.setCoutSupp25 (tavailHorsProdTmp.getHeureSupp25().multiply(COUT_HEURE_SUPPLEMENTAIRE_25));
								coutHorsProdEnAttent.setCoutSupp50 (tavailHorsProdTmp.getHeureSupp50().multiply(COUT_HEURE_SUPPLEMENTAIRE_50));
								
								coutHorsProdEnAttent.setCoutTotal(coutHorsProdEnAttent.getDelaiEmploye().multiply(coutHorsProdEnAttent.getCoutHoraire()));
								
								 
								
								
								if(depot!=null)
								{
									coutHorsProdEnAttent.setDepot(depot);
								}else
								{
									Depot depotTmp=depotDAO.findByCode(utilisateur.getCodeDepot());
									coutHorsProdEnAttent.setDepot(depotTmp);
								}
								coutHorsProdEnAttentDAO.add(coutHorsProdEnAttent);
							}
									
								
						
		 
						
						
						depot=tavailHorsProdTmp.getDepot();
						
						TravailHorsProd travailHorsProd =travailHorsPodDAO.findByEmployeCodeByArticle (tavailHorsProdTmp.getEmploye().getId(),dateSituationCode,tavailHorsProdTmp.getArticles());
						if(travailHorsProd==null)
						{
							travailHorsPodDAO.add(tavailHorsProdTmp);
						}
							
							
							
						}
	
			
		
			}
			
			
			
			
			
			
			
			
			
			
	}
}

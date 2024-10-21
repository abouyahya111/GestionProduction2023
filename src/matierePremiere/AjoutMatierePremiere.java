package matierePremiere;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.DateUtils;
import util.Utils;
import dao.daoImplManager.CategorieMpDAOImpl;
import dao.daoImplManager.ClientDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoImplManager.GrammageBoxDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.MatierePremierModifierDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.TypeOffreDAOImpl;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.ClientDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.GrammageBoxDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.MatierePremiereModifierDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.TypeOffreDAO;
import dao.entity.CategorieMp;
import dao.entity.Client;
import dao.entity.Depot;
import dao.entity.FournisseurMP;
import dao.entity.GrammageBox;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.MatierePremierModifier;
import dao.entity.StockMP;
import dao.entity.TypeOffre;
import dao.entity.Utilisateur;

import javax.swing.JCheckBox;

import com.orsoncharts.util.json.parser.ParseException;
import com.toedter.calendar.JDateChooser;
import java.util.Locale;


public class AjoutMatierePremiere extends JLayeredPane {
	public JLayeredPane contentPane;	
	private DefaultTableModel modele;
	private JXTable table;
	
	private ImageIcon imgModifier;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	
	private JButton btnSupprimer;
	private JButton btnModifier;
	private JButton btnInitialiser;
	private JButton btnAjouter;
	private JButton btnRechercher;
	private JTextField code;
	private JTextField nom;
	
	private Map< String, Integer> catMap = new HashMap<>();
	private Map< String, CategorieMp> MapCategorie = new HashMap<>();
	List<MatierePremier> listeMatierePremiere= new ArrayList<MatierePremier>();
	List<GrammageBox> listeGrammageBox= new ArrayList<GrammageBox>();
	
	private  MatierePremiereDAO dao;
	private  CategorieMpDAO daoCategorie;
	private StockMPDAO stockMPDAO;
	private DepotDAO depotDAO;
	private FournisseurMPDAO fournisseurMPDAO;
	private JComboBox categorie;
	private JLayeredPane layeredPane_1;
	private JComboBox comboCatModif;
	private JTextField txtCodeModif;
	private JTextField txtNomModif;
	private JLabel lblCatModif;
	private JLabel lblNomModif;
	private JLabel lblCodeModif;
	private JButton btnValiderModif;
	private JButton initialiserModif;
	
	private int id_mp;
	private JTextField txtprix;
	private JLabel label_3;
	private JTextField txtprixmodifier;
	JCheckBox checkclient = new JCheckBox("CLIENT");
	private JCheckBox checkclientmodifier;
	private GrammageBoxDAO grammageBoxDAO;
	JComboBox comboUnite = new JComboBox();
	JLabel lblUnite = new JLabel("Unite:");
	private JLabel lblUniteModifier= new JLabel("Unite:");
	private JComboBox comboUniteModifier = new JComboBox();
	JLabel lblTypeOffre = new JLabel("Type Offre :");
	JComboBox comboTypeOffre = new JComboBox();
	JLabel lblTypeOffreModifier = new JLabel("Type Offre :");
	JComboBox comboTypeOffreModifier = new JComboBox();
	private TypeOffreDAO typeOffreDAO;
	List<TypeOffre> listeTypeOffre=new ArrayList<>();
	private Map< String, TypeOffre> MapTypeOffre = new HashMap<>();
	private Map< String, GrammageBox> MapGrammageBox= new HashMap<>();
	JDateChooser dateChooser = new JDateChooser();
	JDateChooser dateChooserModifier = new JDateChooser();
	private Utilisateur utilisateur;
	MatierePremiereModifierDAO matierePremiereModifierDAO;
	JComboBox comboClientModifier = new JComboBox();
	JComboBox comboClient = new JComboBox();
	ClientDAO clientDAO;
	private Map< String, Client> MapClient = new HashMap<>();
	List<Client> listeClient= new ArrayList<Client>();
	JDateChooser annee = new JDateChooser();
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	
	public AjoutMatierePremiere() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 687);
        try{
        	clientDAO=new ClientDAOImpl();
        	matierePremiereModifierDAO=new MatierePremierModifierDAOImpl();
        	utilisateur= AuthentificationView.utilisateur;
        	dao=new MatierePremierDAOImpl();
        	daoCategorie=new CategorieMpDAOImpl();
        	stockMPDAO= new StockMPDAOImpl();
        	depotDAO= new DepotDAOImpl();
        	fournisseurMPDAO=new FournisseurMPDAOImpl();
        	grammageBoxDAO=new GrammageBoxDAOImpl();
        	listeGrammageBox=grammageBoxDAO.findAll();
        	listeClient=clientDAO.findAll();
typeOffreDAO=new TypeOffreDAOImpl();

listeTypeOffre=typeOffreDAO.findAll();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		
		 	
        final String codedepot = AuthentificationView.utilisateur.getCodeDepot(); 	
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp.png"));
          } catch (Exception exp){exp.printStackTrace();}

        
        
        comboCatModif = new JComboBox();
        comboCatModif.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		

 		  		
 		  		if(comboCatModif.getSelectedIndex()!=-1)
 		  		{
 		  			
 		  			if(!comboCatModif.getSelectedItem().equals(""))
 	 		  		{
 		  				
 		  				CategorieMp categorieMp=MapCategorie.get(comboCatModif.getSelectedItem().toString());
 		  				
 		  				if(categorieMp!=null)
 		  				{
 		  					if(categorieMp.getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
 	 		  				{
 	 		  					comboUniteModifier.setSelectedItem("");		     
 							     
 	 							comboUniteModifier.setVisible(true);	
 	 							lblUniteModifier.setVisible(true);	
 	 							
 	 							
 	 							comboTypeOffreModifier.setSelectedItem("");		     
							     
 	 							comboTypeOffreModifier.setVisible(true);	
 	 							lblTypeOffreModifier.setVisible(true);	
 	 							
 	 							
 	 		  					
 	 		  				}else
 	 		  				{
 	 		  					comboUniteModifier.setSelectedItem("");		     
 							     
 	 							comboUniteModifier.setVisible(false);	
 	 							lblUniteModifier.setVisible(false);	
 	 							
 	 							
 	 							comboTypeOffreModifier.setSelectedItem("");		     
							     
 	 							comboTypeOffreModifier.setVisible(false);	
 	 							lblTypeOffreModifier.setVisible(false);	
 	 							
 	 							
 	 							
 	 							
 	 							
 	 							
 	 		  				}
 		  				}
 		  			
 		  				
 		  				
 	 		  		}else
 	 		  		{

		  					comboUniteModifier.setSelectedItem("");		     
						     
							comboUniteModifier.setVisible(false);	
							lblUniteModifier.setVisible(false);	
							
							
							comboTypeOffreModifier.setSelectedItem("");		     
					     
							comboTypeOffreModifier.setVisible(false);	
							lblTypeOffreModifier.setVisible(false);	
							
							
							
							
							
							
		  				
 	 		  		}
 		  			
 		  			
 		  			
 		  			
 		  		}
 		  		
 		  		
 		  		
 		  	
        		
        		
        	}
        });
 		  categorie = new JComboBox();
 		  categorie.addActionListener(new ActionListener() {
 		  	public void actionPerformed(ActionEvent arg0) {
 		  		
 		  		if(categorie.getSelectedIndex()!=-1)
 		  		{
 		  			
 		  			if(!categorie.getSelectedItem().equals(""))
 	 		  		{
 		  				
 		  				CategorieMp categorieMp=MapCategorie.get(categorie.getSelectedItem().toString());
 		  				if(categorieMp!=null)
 		  				{
 		  					if(categorieMp.getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
 	 		  				{
 	 		  					comboUnite.setSelectedItem("");		     
 							     
 	 							comboUnite.setVisible(true);	
 	 							lblUnite.setVisible(true);	
 	 							
 	 							comboTypeOffre.setSelectedItem("");		     
							     
 	 							comboTypeOffre.setVisible(true);	
 	 							lblTypeOffre.setVisible(true);	
 	 							
 	 							
 	 							
 	 							
 	 							
 	 		  					
 	 		  				}else
 	 		  				{
 	 		  					comboUnite.setSelectedItem("");		     
 							     
 	 							comboUnite.setVisible(false);	
 	 							lblUnite.setVisible(false);	
 	 							
 	 							comboTypeOffre.setSelectedItem("");		     
							     
 	 							comboTypeOffre.setVisible(false);	
 	 							lblTypeOffre.setVisible(false);	
 	 							
 	 							
 	 							
 	 		  				}
 		  				}
 		  				
 	 		  		}else
 	 		  		{
 	 		  		comboUnite.setSelectedItem("");		     
				     
						comboUnite.setVisible(false);	
						lblUnite.setVisible(false);	
						
						comboTypeOffre.setSelectedItem("");		     
				     
						comboTypeOffre.setVisible(false);	
						lblTypeOffre.setVisible(false);	
 	 		  		}
 		  			
 		  			
 		  			
 		  			
 		  		}
 		  		
 		  		
 		  		
 		  	}
 		  });
 		 categorie.setBounds(106, 77, 251, 26);
    	categorie.addItem(""); 
    	comboCatModif.addItem(""); 
    	List<CategorieMp> listeCategorie =daoCategorie.findAll();
		
			
			for(int i=0;i<listeCategorie.size();i++)
			{
				
				CategorieMp categorieObject =listeCategorie.get(i);
				catMap.put(categorieObject.getNom(), categorieObject.getId());
				categorie.addItem(categorieObject.getNom());
				comboCatModif.addItem(categorieObject.getNom());
				MapCategorie.put(categorieObject.getNom(), categorieObject);
			}
		

		table = new JXTable();
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
		 table.getTableHeader().setReorderingAllowed(false);
		intialiserTableau();
 
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane.setBounds(10, 391, 913, 285);
		
		this.add(scrollPane);
				  		 
				  		 JXLabel lblListDes = new JXLabel();
				  		 lblListDes.setForeground(new Color(255, 69, 0));
				  		 lblListDes.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
				  		 lblListDes.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
				  		 lblListDes.setText("List des Mati\u00E8res Premi\u00E8res");
				  		 lblListDes.setBounds(10, 363, 913, 24);
				  		 add(lblListDes);
				  		layeredPane_1 = new JLayeredPane();
				  		  btnModifier = new JButton("  Modifier");
				  		  btnModifier.setVisible(true);
				  		  btnModifier.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  
								int row=0;
								   if(table.getSelectedRow()==-1)
									     JOptionPane.showMessageDialog(null, "Il faut sÃ©lectionner un article!", "Attention", JOptionPane.INFORMATION_MESSAGE);
								   else
								   {  
									   
									 
									   
									   
									   row = table.getSelectedRow();
									    MatierePremier matierePremier=listeMatierePremiere.get(row);
							               
							               if(matierePremier.getCode().contains("C"))
							               {
							            	   JOptionPane.showMessageDialog(null, "Veuillez Selectionner Matiere Premiere De Societe SVP !!!!","Information",JOptionPane.ERROR_MESSAGE);
												return;
							               }  
									   
									   
									   layeredPane_1.setVisible(true);
									 
									   id_mp=Integer.parseInt(table.getModel().getValueAt(row, 0).toString());
									   txtCodeModif.setText(table.getModel().getValueAt(row, 1).toString());
									   txtNomModif.setText(table.getModel().getValueAt(row, 2).toString());
									   comboCatModif.setSelectedItem(table.getModel().getValueAt(row,3).toString());
				           
				               
				               
				               txtprixmodifier.setEnabled(true);
				               
				               boolean existe=false;
				               
				               if(matierePremier.getPrix()!=null)
				               {
				            	   
				            	   if(matierePremier.getPrix().compareTo(BigDecimal.ZERO)!=0)
				            	   {
				            		   txtprixmodifier.setEnabled(false);
				            	   }else if(matierePremier.getPrix().compareTo(BigDecimal.ZERO)==0)
				            	   {
				            		   txtprixmodifier.setEnabled(true);
				            	   }
				            	   txtprixmodifier.setText(matierePremier.getPrix()+"");
				            	   existe=true;
				            	   try {
				            		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				            		    Date parsedDate = dateFormat.parse("2023-01-01");
				            		    dateChooserModifier.setDate(parsedDate);
				            		} catch (java.text.ParseException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									} 
				               }
				               
				               if(matierePremier.getPrix2024()!=null)
				               {
				            	   
				            	   if(matierePremier.getPrix2024().compareTo(BigDecimal.ZERO)!=0)
				            	   {
				            		   txtprixmodifier.setEnabled(false);
				            	   }else if(matierePremier.getPrix2024().compareTo(BigDecimal.ZERO)==0)
				            	   {
				            		   txtprixmodifier.setEnabled(true);
				            	   }
				            	   txtprixmodifier.setText(matierePremier.getPrix2024()+"");
				            	   existe=true;
				            	   try {
				            		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				            		    Date parsedDate = dateFormat.parse("2024-01-01");
				            		    dateChooserModifier.setDate(parsedDate);
				            		} catch (java.text.ParseException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
				            	   
				               }
				               
				               if(matierePremier.getPrix2025()!=null)
				               {
				            	   
				            	   if(matierePremier.getPrix2025().compareTo(BigDecimal.ZERO)!=0)
				            	   {
				            		   txtprixmodifier.setEnabled(false);
				            	   }else if(matierePremier.getPrix2025().compareTo(BigDecimal.ZERO)==0)
				            	   {
				            		   txtprixmodifier.setEnabled(true);
				            	   }
				            	   txtprixmodifier.setText(matierePremier.getPrix2025()+"");
				            	   try {
				            		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				            		    Date parsedDate = dateFormat.parse("2025-01-01");
				            		    dateChooserModifier.setDate(parsedDate);
				            		} catch (java.text.ParseException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
				            	   existe=true;
				            	   
				               }
				               
				               if(matierePremier.getPrix2026()!=null)
				               {
				            	   
				            	   if(matierePremier.getPrix2026().compareTo(BigDecimal.ZERO)!=0)
				            	   {
				            		   txtprixmodifier.setEnabled(false);
				            	   }else if(matierePremier.getPrix2026().compareTo(BigDecimal.ZERO)==0)
				            	   {
				            		   txtprixmodifier.setEnabled(true);
				            	   }
				            	   txtprixmodifier.setText(matierePremier.getPrix2026()+"");
				            	   existe=true;
				            	   try {
				            		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				            		    Date parsedDate = dateFormat.parse("2026-01-01");
				            		    dateChooserModifier.setDate(parsedDate);
				            		} catch (java.text.ParseException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
				            	   
				               }
				               
				               
				               
				               if(existe==false)
				               {
				            	   txtprixmodifier.setEnabled(true);
				            	   txtprixmodifier.setText(0+"");
				               }
				               
				            	   
				               
				               
				               if(matierePremier.getType()!=null)
				               {
				            	   
				            	   if(matierePremier.getType().equals(Constantes.MP_CLIENT))
				            	   {
				            		   
				            		   checkclientmodifier.setSelected(true);
				            	   }else
				            	   {
				            		   checkclientmodifier.setSelected(false);
				            	   }
				            	   
				            	   
				            	   
				            	   
				               }
				               
				               
				               if(matierePremier.getUnite()!=null)
				               {
				            	   comboUniteModifier.setSelectedItem(matierePremier.getUnite());
				               }else
				               {
				            	   comboUniteModifier.setSelectedItem("");
				               }
				               
				               
				               if(matierePremier.getTypeOffre()!=null)
				               {
				            	   comboTypeOffreModifier.setSelectedItem(matierePremier.getTypeOffre().getTypeOffre().toString());
				               }else
				               {
				            	   comboTypeOffreModifier.setSelectedItem("");
				               }
				               
				               
				               
				               
				               
								   }
							
				  		  	}
				  		  });
				  		btnModifier.setIcon(imgModifier);
				  		 btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnModifier.setBounds(933, 417, 112, 24);
				  		 add(btnModifier);
				  		 
				  		  btnSupprimer = new JButton("D\u00E9sactiver");
				  		  btnSupprimer.setVisible(false);
				  		  btnSupprimer.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {

								
								 try{
									   int row=0;
									   if(table.getSelectedRow()==-1)
										     JOptionPane.showMessageDialog(null, "Il faut sélectionner une Matière Première!", "Attention", JOptionPane.INFORMATION_MESSAGE);
									   else
									   {
										   
										   int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment désactiver cette Matière Première?", 
												"Satisfaction", JOptionPane.YES_NO_OPTION);
										 
										if(reponse == JOptionPane.YES_OPTION ){
										   
										row = table.getSelectedRow();
									  
									   //stx=con.createStatement();
										int  id=Integer.parseInt(table.getValueAt(row, 0).toString());
									     MatierePremier mp = dao.findById(id);
									     mp.setDeleted(true);
									   	 dao.edit(mp);
									   	 
									   	 listeMatierePremiere = new ArrayList<MatierePremier>();
									  		
									   	listeMatierePremiere=dao.findAll();
									     afficher_table(listeMatierePremiere);  
				                      
											}
									   }
						                }catch (Exception e1){
						                	}
										
							
				  		  	}
				  		  });
				  		btnSupprimer.setIcon(imgSupprimer);
				  		 btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnSupprimer.setBounds(933, 452, 112, 23);
				  		 add(btnSupprimer);
				  		 
				  		 JLayeredPane layeredPane = new JLayeredPane();
				  		 layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		 layeredPane.setBounds(10, 11, 646, 341);
				  		 add(layeredPane);
				  		
				  		 layeredPane.add(categorie);
				  		 
				  		 code = new JTextField();
				  		 code.setFont(new Font("Tahoma", Font.BOLD, 11));
				  		 code.setForeground(Color.BLUE);
				  		 code.setBackground(Color.WHITE);
				  		 code.setEditable(false);
				  		 code.setColumns(10);
				  		 code.setBounds(105, 15, 252, 26);
				  		 layeredPane.add(code);
				  		 
				  		 nom = new JTextField();
				  		 util.Utils.copycoller(nom);
				  		 nom.setColumns(10);
				  		 nom.setBounds(105, 46, 252, 26);
				  		 layeredPane.add(nom);
				  		 
				  		 JLabel label = new JLabel("Catégorie:");
				  		 label.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		 label.setBounds(27, 75, 102, 26);
				  		 layeredPane.add(label);
				  		 
				  		 JLabel label_1 = new JLabel("NOM:");
				  		 label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		 label_1.setBounds(27, 44, 130, 26);
				  		 layeredPane.add(label_1);
				  		 
				  		 JLabel label_2 = new JLabel("CODE:");
				  		 label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		 label_2.setBounds(27, 15, 114, 26);
				  		 layeredPane.add(label_2);
				  		 
				  		  
				  		  btnAjouter = new JButton("Ajouter");
				  		  btnAjouter.setBounds(105, 281, 107, 24);
				  		  layeredPane.add(btnAjouter);
				  		  btnAjouter.setIcon(imgAjouter);
				  		  btnAjouter.addActionListener(new ActionListener() {
				  		   	
				  			  public void actionPerformed(ActionEvent e) {

				  		   		if(nom.getText().equals(""))
				  		   			JOptionPane.showMessageDialog(null, "Il faut saisir le nom de la matière première!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		   		else if (categorie.getSelectedItem().equals(""))
				  		   			JOptionPane.showMessageDialog(null, "Il faut saisir la catégorie de la matière première!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		   		else {
				  		   			
				  		   			if(txtprix.getText().equals(""))
				  		   			{
				  		   			JOptionPane.showMessageDialog(null, "Il faut saisir le prix de la matière première!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		   			return;
				  		   			}
				  		   			
				  		   			try {
				  		   				
				  		   				
				  		   		
										if(new BigDecimal(txtprix.getText()).compareTo(BigDecimal.ZERO)<0)
					  		   			{
					  		   			JOptionPane.showMessageDialog(null, " le prix de la matière première doit etre superieur ou égale à 0!", "Attention", JOptionPane.INFORMATION_MESSAGE);
					  		   			return;
					  		   			}
									
				  		   				
				  		   			
										
									} catch (NumberFormatException e2) {
										JOptionPane.showMessageDialog(null, " le prix de la matière première doit etre en chiffre SVP!", "Attention", JOptionPane.INFORMATION_MESSAGE);
										return;
									}
				  		   		
				  		   			
				  		   			
				  		   			
				  		   			
				  		   			
				  		   		int idCat=catMap.get(categorie.getSelectedItem());
				  		   	CategorieMp  CategorieMp =daoCategorie.findById(idCat);
				  		   	
				  		   
				  		  if(CategorieMp.getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
	 		  				{
				  			  
				  			  if(comboUnite.getSelectedItem().equals("") || comboTypeOffre.getSelectedItem().equals(""))
				  			  {
				  				JOptionPane.showMessageDialog(null, " Veuillez Selectionner l'Unite et le Type D'Offre Cadeau SVP!", "Attention", JOptionPane.INFORMATION_MESSAGE);
								return; 
				  				  
				  				  
				  			  }
				  			  
				  			  
				  			  if(comboUnite.getSelectedItem().equals(Constantes.UNITE_PIECE))
				  			  {
				  				  
				  				if(!comboTypeOffre.getSelectedItem().toString().toUpperCase().equals(Constantes.TYPE_OFFRE_AUTRES))  
				  				{
				  					JOptionPane.showMessageDialog(null, " Veuillez Selectionner le Type D'Offre Autres Pour Unité Piece SVP!", "Attention", JOptionPane.INFORMATION_MESSAGE);
									return; 
				  				}
				  				  
				  			  }
				  			  
				  			if(comboTypeOffre.getSelectedItem().toString().toUpperCase().equals(Constantes.TYPE_OFFRE_AUTRES))  
			  				{
				  			if(!comboUnite.getSelectedItem().equals(Constantes.UNITE_PIECE))
				  			  {
				  				  
				  				
				  					JOptionPane.showMessageDialog(null, " Veuillez Selectionner Unité Piece  Pour Type D'Offre Autres  SVP!", "Attention", JOptionPane.INFORMATION_MESSAGE);
									return; 
				  				}
				  				  
				  			  }
				  			  
				  			  
				  			  
				  			  
	 		  				}
				  		   	
				  		   	
				  		   	
								MatierePremier	p= new MatierePremier();
								p.setCode(code.getText());
								p.setNom(nom.getText());
								if(DateUtils.getAnnee(dateChooser.getDate())==2023)
								{
									p.setPrix(new BigDecimal(txtprix.getText()));
									
								}else
								{
									p.setPrixByYear(DateUtils.getAnnee(dateChooser.getDate()), new BigDecimal(txtprix.getText()));
									
								}
								
								
								
								p.setCategorieMp(CategorieMp);
								
								if(checkclient.isSelected()==true)
								{
									p.setType(Constantes.MP_CLIENT);
									
									
									if(comboClient.getSelectedItem().equals(""))
									{
										JOptionPane.showMessageDialog(null, "Veuillez Selectionner Le Client","Erreur",JOptionPane.ERROR_MESSAGE);
										return;
									}
									
									
									Client client=MapClient.get(comboClient.getSelectedItem().toString());
									if(client==null)
									{
										JOptionPane.showMessageDialog(null, "Veuillez Selectionner Le Client","Erreur",JOptionPane.ERROR_MESSAGE);
										return;
										
									}else
									{

                                  p.setClient(client);
									}
									
									
								}else
								{
									p.setType(Constantes.MP_INTERNE);
									
								}
								
								 if(CategorieMp.getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
			 		  				{
						  			  
									 if(!comboUnite.getSelectedItem().toString().equals(Constantes.UNITE_PIECE))
									 {
										 GrammageBox grammageBox=MapGrammageBox.get(comboUnite.getSelectedItem().toString());
										 p.setGrammageOffre(grammageBox.getGrammageBox());
									 }
									 
									 TypeOffre typeOffre=MapTypeOffre.get(comboTypeOffre.getSelectedItem());
									 p.setTypeOffre(typeOffre);
									 
									 
						  			 p.setUnite(comboUnite.getSelectedItem().toString());
						  			  
			 		  				}else
			 		  				{
			 		  				 p.setUnite("");
			 		  				}
								
								
								dao.add(p);
								
								//////////////////////////// generer Stock Mp //////////////////////////////////
								
							 	//GenererStockMp( codedepot , p,p.getPrix());
								 listeMatierePremiere.add(p);
								
								if(checkclient.isSelected()==true)
								{
									
									if(comboClient.getSelectedItem().equals(""))
									{
										JOptionPane.showMessageDialog(null, "Veuillez Selectionner Le Client","Erreur",JOptionPane.ERROR_MESSAGE);
										return;
									}
									
									
									
									
									
								MatierePremier	pTmp= new MatierePremier();
								pTmp.setCode(code.getText()+"C");
								pTmp.setNom(nom.getText()+"(C)");
								pTmp.setPrix(BigDecimal.ZERO);
								 								
								pTmp.setCategorieMp(CategorieMp);
								pTmp.setType(Constantes.MP_CLIENT);
								
								Client client=MapClient.get(comboClient.getSelectedItem().toString());
								if(client==null)
								{
									JOptionPane.showMessageDialog(null, "Veuillez Selectionner Le Client","Erreur",JOptionPane.ERROR_MESSAGE);
									return;
									
								}else
								{

									pTmp.setClient(client);
								}
								
								if(CategorieMp.getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
		 		  				{
									 if(!comboUnite.getSelectedItem().toString().equals(Constantes.UNITE_PIECE))
									 {
										 GrammageBox grammageBox=MapGrammageBox.get(comboUnite.getSelectedItem().toString());
										 pTmp.setGrammageOffre(grammageBox.getGrammageBox());
									 }
									 
									 TypeOffre typeOffre=MapTypeOffre.get(comboTypeOffre.getSelectedItem());
									 pTmp.setTypeOffre(typeOffre);
									 
									pTmp.setUnite(comboUnite.getSelectedItem().toString());
					  			  
		 		  				}else
		 		  				{
		 		  					pTmp.setUnite("");
		 		  				}
								
								
								
								dao.add(pTmp);
								
								
								
								//////////////////////////// generer Stock Mp //////////////////////////////////
								
								//GenererStockMp( codedepot , pTmp,pTmp.getPrix());
								
								 listeMatierePremiere.add(pTmp);
								}
								
								
							//////////////////////////////////////////////////////////////////////////////////////	
								
								Utils.incrementerValeurSequenceur(Constantes.MATIERE_PREMIERE_LIBELLE);
								// listeMatierePremiere = new ArrayList<MatierePremier>();
								 //listeMatierePremiere=dao.findAll();
								 
								
								 intialiser();
								   	String codegenere =Utils.genererCode(Constantes.MATIERE_PREMIERE_LIBELLE);
						  			code.setText(codegenere);
								     afficher_table(listeMatierePremiere);  
				  		   		}
				  		   	}
				  		   });
				  		  btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  
				  		   btnInitialiser = new JButton("Initialiser");
				  		   btnInitialiser.setBounds(255, 282, 102, 23);
				  		   layeredPane.add(btnInitialiser);
				  		   btnInitialiser.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent e) {
				  		   	intialiser();
				  		   		
				  		   	}
				  		   });
				  		   btnInitialiser.setIcon(imgInit);
				  		   btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   
				  		   JButton btnGenererStock = new JButton("G\u00E9n\u00E9rer Stock");
				  		   btnGenererStock.addActionListener(new ActionListener() { 
				  		   	public void actionPerformed(ActionEvent e) {
				  		   		
				  		   		
				  		   	List<MatierePremier> listMP =new ArrayList<MatierePremier>();
				  		   	List<Magasin> listMagasin= new ArrayList<Magasin>();
				  		 
				  		
				  		  listMP =dao.findAll();
				  		if(codedepot.equals(Constantes.CODE_DEPOT_SIEGE)){
				  			
				  			listMagasin=depotDAO.listeMagasinByTypeMagasin(Constantes.MAGASIN_CODE_TYPE_MP);
				  			
			  		   	} else {
			  		   		
			  		   		Depot depot=depotDAO.findByCode(codedepot);
			  		   		listMagasin=depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_MP);
			  		   		
			  		   	}
				  		  
				  	
				  		  
				  		   		Utils.genererStock(listMP,listMagasin);
				  		   		
				  		   		
				  		   	if(codedepot.equals(Constantes.CODE_DEPOT_SIEGE)){
					  			
					  			listMagasin=depotDAO.listeMagasinByTypeMagasin(Constantes.MAGASIN_CODE_TYPE_DECHET_MP);
					  			
				  		   	} else {
				  		   		
				  		   		Depot depot=depotDAO.findByCode(codedepot);
				  		   		listMagasin=depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_DECHET_MP);
				  		   		
				  		   	}
				  		   		
				  		  Utils.genererStock(listMP,listMagasin);
				  		   		
				  		   		
				  		   		
				  		   		
				  		   		
				  		   	JOptionPane.showMessageDialog(null, "Le stock est généré avec succès", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		 
				  		   	
				  		 
				  		   	
				  		   	}
				  		  
				  		   
				  		   
				  		   });
				  		   btnGenererStock.setBounds(385, 282, 102, 23);
				  		   layeredPane.add(btnGenererStock);
				  		   
				  		 btnGenererStock.setVisible(false);
				  		   
				  		   layeredPane_1.setBackground(new Color(135, 206, 235));
				  		   layeredPane_1.setVisible(false);
				  		   layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(135, 206, 250), new Color(135, 206, 250)));
				  		   layeredPane_1.setBounds(666, 11, 608, 341);
				  		   add(layeredPane_1);
				  		   
				  		
				  		   comboCatModif.setBounds(113, 77, 230, 26);
				  		   layeredPane_1.add(comboCatModif);
				  		   
				  		   txtCodeModif = new JTextField();
				  		 util.Utils.copycoller(txtCodeModif);
				  		   txtCodeModif.setColumns(10);
				  		   txtCodeModif.setBounds(112, 15, 231, 26);
				  		   layeredPane_1.add(txtCodeModif);
				  		   
				  		   txtNomModif = new JTextField();
				  		 util.Utils.copycoller(txtNomModif);
				  		   txtNomModif.setColumns(10);
				  		   txtNomModif.setBounds(112, 46, 231, 26);
				  		   layeredPane_1.add(txtNomModif);
				  		   
				  		   lblCatModif = new JLabel("Cat\u00E9gorie:");
				  		   lblCatModif.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   lblCatModif.setBounds(30, 75, 102, 26);
				  		   layeredPane_1.add(lblCatModif);
				  		   
				  		   lblNomModif = new JLabel("NOM:");
				  		   lblNomModif.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   lblNomModif.setBounds(30, 44, 130, 26);
				  		   layeredPane_1.add(lblNomModif);
				  		   
				  		   lblCodeModif = new JLabel("CODE:");
				  		   lblCodeModif.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   lblCodeModif.setBounds(30, 15, 114, 26);
				  		   layeredPane_1.add(lblCodeModif);
				  		   
				  		   btnValiderModif = new JButton("Valider Modification");
				  		   btnValiderModif.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent e) {
				  		   		
				  		   	if(txtprixmodifier.getText().equals(""))
		  		   			{
		  		   			JOptionPane.showMessageDialog(null, "Il faut saisir le prix de la matière première!", "Attention", JOptionPane.INFORMATION_MESSAGE);
		  		   			return;
		  		   			}
		  		   			
		  		   			try {
		  		   				
		  		   		
		  		   			
		  		   	
							if(new BigDecimal(txtprixmodifier.getText()).compareTo(BigDecimal.ZERO)<0)
		  		   			{
		  		   			JOptionPane.showMessageDialog(null, " le prix de la matière première doit etre superieur ou égale à 0!", "Attention", JOptionPane.INFORMATION_MESSAGE);
		  		   			return;
		  		   			}
							
							} catch (NumberFormatException e2) {
								JOptionPane.showMessageDialog(null, " le prix de la matière première doit etre en chiffre SVP!", "Attention", JOptionPane.INFORMATION_MESSAGE);
								return;
							}
		  		   			
		  		   		int idCat=catMap.get(comboCatModif.getSelectedItem());
				  		
				  		 MatierePremier mp = dao.findById(id_mp);	
				  		CategorieMp  CategorieMp =daoCategorie.findById(idCat);
				  		
				  	  if(CategorieMp.getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
		  				{
			  			  
			  			  if(comboUniteModifier.getSelectedItem().equals("") || comboTypeOffreModifier.getSelectedItem().equals(""))
			  			  {
			  				JOptionPane.showMessageDialog(null, " Veuillez Selectionner l'Unite et le Type d'Offre  Cadeau SVP!", "Attention", JOptionPane.INFORMATION_MESSAGE);
							return; 
			  				  
			  				  
			  			  }
			  			  
			  			  
			  			  if(comboUniteModifier.getSelectedItem().equals(Constantes.UNITE_PIECE))
			  			  {
			  				  
			  				if(!comboTypeOffreModifier.getSelectedItem().toString().toUpperCase().equals(Constantes.TYPE_OFFRE_AUTRES))  
			  				{
			  					JOptionPane.showMessageDialog(null, " Veuillez Selectionner le Type D'Offre Autres Pour Unité Piece SVP!", "Attention", JOptionPane.INFORMATION_MESSAGE);
								return; 
			  				}
			  				  
			  			  }
			  			  
			  			if(comboTypeOffreModifier.getSelectedItem().toString().toUpperCase().equals(Constantes.TYPE_OFFRE_AUTRES))  
		  				{
			  				
			  			if(!comboUniteModifier.getSelectedItem().equals(Constantes.UNITE_PIECE))
			  			  {
			  				  
			  				
			  					JOptionPane.showMessageDialog(null, " Veuillez Selectionner Unité Piece  Pour Type D'Offre Autres  SVP!", "Attention", JOptionPane.INFORMATION_MESSAGE);
								return; 
			  				}
			  				  
			  			  }
			  			  
			  			  
			  			  
			  			  
			  			  
		  				}
				  		
				  		
				  		if(!mp.getCode().contains("C"))
				  		{
				  			if(new BigDecimal(txtprixmodifier.getText()).compareTo(BigDecimal.ZERO)==0)
				  			{
				  				JOptionPane.showMessageDialog(null, "Veuillez Entrer Le Prix SVP");
				  				return;
				  			}
				  		}
				  		
				  		
		  		   		if(checkclientmodifier.isSelected()==false)
						{
		  		   			
		  		   			
		  		   		
		  		   			
		  		   			MatierePremierModifier matierePremierModifier=new MatierePremierModifier();
		  		   			
		  		   		matierePremierModifier.setNom(mp.getNom());
		  		   	matierePremierModifier.setCode(mp.getCode());
		  		  matierePremierModifier.setCategorieMp(mp.getCategorieMp());
		  		matierePremierModifier.setPrix(mp.getPrix());
		  		matierePremierModifier.setPrix2024(mp.getPrix2024());
		  		matierePremierModifier.setPrix2025(mp.getPrix2025());
		  		matierePremierModifier.setPrix2026(mp.getPrix2026());
		  		matierePremierModifier.setPrix2027(mp.getPrix2027());
		  		matierePremierModifier.setPrix2028(mp.getPrix2028());
		  		matierePremierModifier.setPrix2029(mp.getPrix2029());
		  		matierePremierModifier.setPrix2030(mp.getPrix2030());
		  		matierePremierModifier.setModifierPar(utilisateur);
		  		matierePremierModifier.setDateModifier(new Date());
		  		matierePremiereModifierDAO.add(matierePremierModifier);
		  		   					  		
				  		mp.setNom(txtNomModif.getText());
						
						mp.setCategorieMp(CategorieMp);
						
						if(mp.getPrix()!=null)
						{
							 
							if(DateUtils.getAnnee(dateChooserModifier.getDate())==2023)
							{
								mp.setPrix(new BigDecimal(txtprixmodifier.getText()));
								
							}else
							{
								mp.setPrixByYear(DateUtils.getAnnee(dateChooserModifier.getDate()), new BigDecimal(txtprixmodifier.getText()));
								
							}
														 
						}else
						{
							if(DateUtils.getAnnee(dateChooserModifier.getDate())==2023)
							{
								mp.setPrix(new BigDecimal(txtprixmodifier.getText()));
								
							}else
							{
								mp.setPrixByYear(DateUtils.getAnnee(dateChooserModifier.getDate()), new BigDecimal(txtprixmodifier.getText()));
								
							}						
							
						
						
						}
						
					
						
						if(checkclientmodifier.isSelected()==true)
						{
							
							mp.setType(Constantes.MP_CLIENT);
							if(comboClientModifier.getSelectedItem().equals(""))
							{
								JOptionPane.showMessageDialog(null, "Veuillez Selectionner Le Client","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							}
							
							
							Client client=MapClient.get(comboClientModifier.getSelectedItem().toString());
							if(client==null)
							{
								JOptionPane.showMessageDialog(null, "Veuillez Selectionner Le Client","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							}
							mp.setClient(client);
						}else
						{
							mp.setType(Constantes.MP_INTERNE);
							
						}
						
						  if(CategorieMp.getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
			  				{
				  			  
				  			  if(!comboUniteModifier.getSelectedItem().equals(""))
				  			  {
				  				  
				  				  
				  				 if(!comboUniteModifier.getSelectedItem().toString().equals(Constantes.UNITE_PIECE))
								 {
									 GrammageBox grammageBox=MapGrammageBox.get(comboUniteModifier.getSelectedItem().toString());
									 mp.setGrammageOffre(grammageBox.getGrammageBox());
								 }else
								 {
									 mp.setGrammageOffre(BigDecimal.ZERO);
								 }
				  				  
				  				 
				  				mp.setUnite(comboUniteModifier.getSelectedItem().toString()); 
				  				  
				  			  }else
				  			  {
				  				mp.setUnite("");  
				  			  }
				  			  
				  			  
				  			TypeOffre typeOffre=MapTypeOffre.get(comboTypeOffreModifier.getSelectedItem());
							 mp.setTypeOffre(typeOffre);
				  			  
				  			  
				  			  
			  				}else
			  				{
			  					mp.setUnite(""); 
			  				}
						
						
						
						
						
						 dao.edit(mp);
						 
////////////////////////////generer Stock Mp //////////////////////////////////
							
							// GenererStockMp( codedepot , mp,new BigDecimal(txtprixmodifier.getText()));
						 
						}
		  		   		
		  		   		
		  		   		 
							
							if(checkclientmodifier.isSelected()==true)
							{
								
								if(comboClientModifier.getSelectedItem().equals(""))
								{
									JOptionPane.showMessageDialog(null, "Veuillez Selectionner Le Client","Erreur",JOptionPane.ERROR_MESSAGE);
									return;
								}
								
								
								Client client=MapClient.get(comboClientModifier.getSelectedItem().toString());
								if(client==null)
								{
									JOptionPane.showMessageDialog(null, "Veuillez Selectionner Le Client","Erreur",JOptionPane.ERROR_MESSAGE);
									return;
								}
								
								if(!mp.getCode().contains("C"))
								{
									MatierePremier mpTmp=dao.findByCode(mp.getCode()+"C");
									if(mpTmp!=null)
									{

										
										MatierePremierModifier matierePremierModifier=new MatierePremierModifier();
										matierePremierModifier.setClient(client);
						  		   		matierePremierModifier.setNom(mpTmp.getNom());
						  		   	matierePremierModifier.setCode(mpTmp.getCode());
						  		  matierePremierModifier.setCategorieMp(mpTmp.getCategorieMp());
						  		matierePremierModifier.setPrix(mpTmp.getPrix());
						  		matierePremierModifier.setPrix2024(mpTmp.getPrix2024());
						  		matierePremierModifier.setPrix2025(mpTmp.getPrix2025());
						  		matierePremierModifier.setPrix2026(mpTmp.getPrix2026());
						  		matierePremierModifier.setPrix2027(mpTmp.getPrix2027());
						  		matierePremierModifier.setPrix2028(mpTmp.getPrix2028());
						  		matierePremierModifier.setPrix2029(mpTmp.getPrix2029());
						  		matierePremierModifier.setPrix2030(mpTmp.getPrix2030());
						  		matierePremierModifier.setModifierPar(utilisateur);
						  		matierePremierModifier.setDateModifier(new Date());
						  		matierePremiereModifierDAO.add(matierePremierModifier);		

									 mpTmp.setNom(txtNomModif.getText()+"(C)");
										  
										
										 if(DateUtils.getAnnee(dateChooserModifier.getDate())==2023)
											{
											 mpTmp.setPrix(BigDecimal.ZERO);
												
											}else
											{
												mpTmp.setPrixByYear(DateUtils.getAnnee(dateChooserModifier.getDate()), BigDecimal.ZERO);
												
											}										 								
										mpTmp.setCategorieMp(CategorieMp);
										mpTmp.setType(Constantes.MP_CLIENT);
										
										  if(CategorieMp.getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
							  				{
								  			  
								  			  if(!comboUniteModifier.getSelectedItem().equals(""))
								  			  {
								  				 if(!comboUniteModifier.getSelectedItem().toString().equals(Constantes.UNITE_PIECE))
												 {
													 GrammageBox grammageBox=MapGrammageBox.get(comboUniteModifier.getSelectedItem().toString());
													 mpTmp.setGrammageOffre(grammageBox.getGrammageBox());
												 }else
												 {
													 mpTmp.setGrammageOffre(BigDecimal.ZERO);
												 }
								  				mpTmp.setUnite(comboUniteModifier.getSelectedItem().toString()); 
								  				  
								  			  }else
								  			  {
								  				mpTmp.setUnite("");  
								  			  }
								  			  
								  			  
								  			TypeOffre typeOffre=MapTypeOffre.get(comboTypeOffreModifier.getSelectedItem());
											 mpTmp.setTypeOffre(typeOffre);
								  			  
							  				}else
							  				{
							  					mpTmp.setUnite(""); 
							  				}
										
											 
										dao.edit(mpTmp);
										
			//////////////////////////// generer Stock Mp //////////////////////////////////
										
									//	GenererStockMp( codedepot , mpTmp,mpTmp.getPrix());
										
										
										MatierePremierModifier matierePremierModifierTmp=new MatierePremierModifier();
					  		   			
										matierePremierModifierTmp.setNom(mp.getNom());
										matierePremierModifierTmp.setCode(mp.getCode());
										matierePremierModifierTmp.setCategorieMp(mp.getCategorieMp());
										matierePremierModifierTmp.setPrix(mp.getPrix());
										matierePremierModifierTmp.setPrix2024(mp.getPrix2024());
										matierePremierModifierTmp.setPrix2025(mp.getPrix2025());
										matierePremierModifierTmp.setPrix2026(mp.getPrix2026());
										matierePremierModifierTmp.setPrix2027(mp.getPrix2027());
										matierePremierModifierTmp.setPrix2028(mp.getPrix2028());
										matierePremierModifierTmp.setPrix2029(mp.getPrix2029());
										matierePremierModifierTmp.setPrix2030(mp.getPrix2030());
										matierePremierModifierTmp.setModifierPar(utilisateur);
										matierePremierModifierTmp.setDateModifier(new Date());
						  		matierePremiereModifierDAO.add(matierePremierModifierTmp);	
										
										
										
										
										
										
										  
										mp.setNom(txtNomModif.getText());
										
										mp.setCategorieMp(CategorieMp);
										
										if(mp.getPrix()!=null)
										{
											 
											if(DateUtils.getAnnee(dateChooserModifier.getDate())==2023)
											{
												mp.setPrix(new BigDecimal(txtprixmodifier.getText()));
												
											}else
											{
												mp.setPrixByYear(DateUtils.getAnnee(dateChooserModifier.getDate()), new BigDecimal(txtprixmodifier.getText()));
												
											}
																		 
										}else
										{
											if(DateUtils.getAnnee(dateChooserModifier.getDate())==2023)
											{
												mp.setPrix(new BigDecimal(txtprixmodifier.getText()));
												
											}else
											{
												mp.setPrixByYear(DateUtils.getAnnee(dateChooserModifier.getDate()), new BigDecimal(txtprixmodifier.getText()));
												
											}						}
									
										
										if(checkclientmodifier.isSelected()==true)
										{
											
											mp.setType(Constantes.MP_CLIENT);
											
											
											
											
											
											if(comboClientModifier.getSelectedItem().equals(""))
											{
												JOptionPane.showMessageDialog(null, "Veuillez Selectionner Le Client","Erreur",JOptionPane.ERROR_MESSAGE);
												return;
											}
											
											
											Client clientTmp=MapClient.get(comboClientModifier.getSelectedItem().toString());
											if(clientTmp==null)
											{
												JOptionPane.showMessageDialog(null, "Veuillez Selectionner Le Client","Erreur",JOptionPane.ERROR_MESSAGE);
												return;
											}
											
											mp.setClient(clientTmp);
											
										}else
										{
											mp.setType(Constantes.MP_INTERNE);
											
										}
										
										
										 if(CategorieMp.getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
							  				{
								  			  
								  			  if(!comboUniteModifier.getSelectedItem().equals(""))
								  			  {
								  				if(!comboUniteModifier.getSelectedItem().toString().equals(Constantes.UNITE_PIECE))
												 {
													 GrammageBox grammageBox=MapGrammageBox.get(comboUniteModifier.getSelectedItem().toString());
													 mp.setGrammageOffre(grammageBox.getGrammageBox());
												 }else
												 {
													 mp.setGrammageOffre(BigDecimal.ZERO);
												 }
								  				mp.setUnite(comboUniteModifier.getSelectedItem().toString()); 
								  				  
								  			  }else
								  			  {
								  				mp.setUnite("");  
								  			  }
								  			  
								  			TypeOffre typeOffre=MapTypeOffre.get(comboTypeOffreModifier.getSelectedItem());
											 mp.setTypeOffre(typeOffre); 
								  			  
								  			  
								  			  
							  				}else
							  				{
							  					mp.setUnite(""); 
							  				}
										
										 dao.edit(mp);
										 
				////////////////////////////generer Stock Mp //////////////////////////////////
											
										//	GenererStockMp( codedepot , mp,mp.getPrix());
										
										
									}else
									{
										MatierePremier	pTmp= new MatierePremier();
										pTmp.setCode(mp.getCode()+"C");
										pTmp.setNom(txtNomModif.getText()+"(C)");
										 if(DateUtils.getAnnee(dateChooserModifier.getDate())==2023)
											{
											 pTmp.setPrix(BigDecimal.ZERO);
												
											}else
											{
												pTmp.setPrixByYear(DateUtils.getAnnee(dateChooserModifier.getDate()), BigDecimal.ZERO);
												
											}	
										 								
										pTmp.setCategorieMp(CategorieMp);
										pTmp.setType(Constantes.MP_CLIENT);
											 
										 if(CategorieMp.getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
							  				{
								  			  
								  			  if(!comboUniteModifier.getSelectedItem().equals(""))
								  			  {
								  				if(!comboUniteModifier.getSelectedItem().toString().equals(Constantes.UNITE_PIECE))
												 {
													 GrammageBox grammageBox=MapGrammageBox.get(comboUniteModifier.getSelectedItem().toString());
													 pTmp.setGrammageOffre(grammageBox.getGrammageBox());
												 }else
												 {
													 pTmp.setGrammageOffre(BigDecimal.ZERO);
												 }
								  				 
								  				pTmp.setUnite(comboUniteModifier.getSelectedItem().toString()); 
								  				  
								  			  }else
								  			  {
								  				pTmp.setUnite("");  
								  			  }
								  			  
								  			TypeOffre typeOffre=MapTypeOffre.get(comboTypeOffreModifier.getSelectedItem());
											 pTmp.setTypeOffre(typeOffre);
								  			  
							  				}else
							  				{
							  					pTmp.setUnite(""); 
							  				}
										
										dao.add(pTmp);
										
			//////////////////////////// generer Stock Mp //////////////////////////////////
										
										// GenererStockMp( codedepot , pTmp,pTmp.getPrix());
										
										
MatierePremierModifier matierePremierModifierTmp=new MatierePremierModifier();
					  		   			
										matierePremierModifierTmp.setNom(mp.getNom());
										matierePremierModifierTmp.setCode(mp.getCode());
										matierePremierModifierTmp.setCategorieMp(mp.getCategorieMp());
										matierePremierModifierTmp.setPrix(mp.getPrix());
										matierePremierModifierTmp.setPrix2024(mp.getPrix2024());
										matierePremierModifierTmp.setPrix2025(mp.getPrix2025());
										matierePremierModifierTmp.setPrix2026(mp.getPrix2026());
										matierePremierModifierTmp.setPrix2027(mp.getPrix2027());
										matierePremierModifierTmp.setPrix2028(mp.getPrix2028());
										matierePremierModifierTmp.setPrix2029(mp.getPrix2029());
										matierePremierModifierTmp.setPrix2030(mp.getPrix2030());
										matierePremierModifierTmp.setModifierPar(utilisateur);
										matierePremierModifierTmp.setDateModifier(new Date());
						  		matierePremiereModifierDAO.add(matierePremierModifierTmp);	
										
										
										
										
		mp.setNom(txtNomModif.getText());
										
										mp.setCategorieMp(CategorieMp);

										if(mp.getPrix()!=null)
										{
											 
											if(DateUtils.getAnnee(dateChooserModifier.getDate())==2023)
											{
												mp.setPrix(new BigDecimal(txtprixmodifier.getText()));
												
											}else
											{
												mp.setPrixByYear(DateUtils.getAnnee(dateChooserModifier.getDate()), new BigDecimal(txtprixmodifier.getText()));
												
											}
																		 
										}else
										{
											if(DateUtils.getAnnee(dateChooserModifier.getDate())==2023)
											{
												mp.setPrix(new BigDecimal(txtprixmodifier.getText()));
												
											}else
											{
												mp.setPrixByYear(DateUtils.getAnnee(dateChooserModifier.getDate()), new BigDecimal(txtprixmodifier.getText()));
												
											}						}
										
									
										
										if(checkclientmodifier.isSelected()==true)
										{
											
											mp.setType(Constantes.MP_CLIENT);
											if(comboClientModifier.getSelectedItem().equals(""))
											{
												JOptionPane.showMessageDialog(null, "Veuillez Selectionner Le Client","Erreur",JOptionPane.ERROR_MESSAGE);
												return;
											}
											
											
											Client clientTmpT=MapClient.get(comboClientModifier.getSelectedItem().toString());
											if(clientTmpT==null)
											{
												JOptionPane.showMessageDialog(null, "Veuillez Selectionner Le Client","Erreur",JOptionPane.ERROR_MESSAGE);
												return;
											}
											
											mp.setClient(clientTmpT);
										}else
										{
											mp.setType(Constantes.MP_INTERNE);
											
										}
										
										 if(CategorieMp.getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
							  				{
								  			  
								  			  if(!comboUniteModifier.getSelectedItem().equals(""))
								  			  {
								  				if(!comboUniteModifier.getSelectedItem().toString().equals(Constantes.UNITE_PIECE))
												 {
													 GrammageBox grammageBox=MapGrammageBox.get(comboUniteModifier.getSelectedItem().toString());
													 mp.setGrammageOffre(grammageBox.getGrammageBox());
												 }else
												 {
													 mp.setGrammageOffre(BigDecimal.ZERO);
												 }
								  				mp.setUnite(comboUniteModifier.getSelectedItem().toString()); 
								  				  
								  			  }else
								  			  {
								  				mp.setUnite("");  
								  			  }
								  			  
								  			TypeOffre typeOffre=MapTypeOffre.get(comboTypeOffreModifier.getSelectedItem());
											 mp.setTypeOffre(typeOffre);
								  			  
							  				}else
							  				{
							  					mp.setUnite(""); 
							  				}
										
										 dao.edit(mp);
										 
				////////////////////////////generer Stock Mp //////////////////////////////////
											
										//	GenererStockMp( codedepot , mp,mp.getPrix());	
										
										
										 
									}
								}else
								{

								JOptionPane.showMessageDialog(null, "Veuillez Selectionner Matiere Premiere De Societe SVP !!!!","Information",JOptionPane.ERROR_MESSAGE);
								return;
								
								
								
								
								}
								
								
							
							
							
							
							
							}
							
							
							
							
							
							
						   	JOptionPane.showMessageDialog(null, "la Matière Première a été modifiée avec succès!", "Attention", JOptionPane.INFORMATION_MESSAGE);
						   	layeredPane_1.setVisible(false);
						    listeMatierePremiere = new ArrayList<MatierePremier>();
					  		
						   	listeMatierePremiere=dao.findAll();
						     afficher_table(listeMatierePremiere);   
						     
				  		   		
				  		   	}
				  		   });
				  		   btnValiderModif.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   btnValiderModif.setBounds(113, 270, 130, 24);
				  		   layeredPane_1.add(btnValiderModif);
				  		   
				  		   initialiserModif = new JButton("Initialiser");
				  		   initialiserModif.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent arg0) {
				  		   		
				  		   	intialiserModifier();
				  		   		
				  		   		
				  		   		
				  		   		
				  		   	}
				  		   });
				  		  
				  		   initialiserModif.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   initialiserModif.setBounds(264, 271, 102, 23);
				  		   layeredPane_1.add(initialiserModif);
				  		   
				  		   label_3 = new JLabel("PRIX :");
				  		   label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   label_3.setBounds(30, 106, 130, 26);
				  		   layeredPane_1.add(label_3);
				  		   
				  		   txtprixmodifier = new JTextField();
				  		   txtprixmodifier.setColumns(10);
				  		   txtprixmodifier.setBounds(112, 108, 231, 26);
				  		   layeredPane_1.add(txtprixmodifier);
				  		   
				  		   checkclientmodifier = new JCheckBox("CLIENT");
				  		   checkclientmodifier.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent e) {
				  		   		

			  			 		if(checkclientmodifier.isSelected()==true)
			  			 		{
			  			 			comboClientModifier.setVisible(true);
			  			 			comboClientModifier.setSelectedItem("");
			  			 		}else
			  			 		{

			  			 			comboClientModifier.setVisible(false);
			  			 			comboClientModifier.setSelectedItem("");
			  			 		
			  			 		}
			  			 	
				  		   	}
				  		   });
				  		   checkclientmodifier.setBounds(373, 15, 88, 23);
				  		   layeredPane_1.add(checkclientmodifier);
				  		   
				  		   lblUniteModifier = new JLabel("Unite:");
				  		   lblUniteModifier.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   lblUniteModifier.setBounds(30, 177, 102, 26);
				  		   layeredPane_1.add(lblUniteModifier);
				  		   
				  		   comboUniteModifier = new JComboBox();
				  		   comboUniteModifier.setBounds(113, 179, 230, 26);
				  		   layeredPane_1.add(comboUniteModifier);
				  			String codegenere =Utils.genererCode(Constantes.MATIERE_PREMIERE_LIBELLE);
				  			code.setText(codegenere);
				  			
				  			JLabel lblPrix = new JLabel("PRIX :");
				  			lblPrix.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  			lblPrix.setBounds(27, 105, 130, 26);
				  			layeredPane.add(lblPrix);
				  			
				  			txtprix = new JTextField();
				  			txtprix.setColumns(10);
				  			txtprix.setBounds(105, 107, 252, 26);
				  			layeredPane.add(txtprix);
				  			
				  			 checkclient = new JCheckBox("CLIENT");
				  			 checkclient.addActionListener(new ActionListener() {
				  			 	public void actionPerformed(ActionEvent e) {
				  			 		if(checkclient.isSelected()==true)
				  			 		{
				  			 			comboClient.setVisible(true);
				  			 			comboClient.setSelectedItem("");
				  			 		}else
				  			 		{

				  			 			comboClient.setVisible(false);
				  			 			comboClient.setSelectedItem("");
				  			 		
				  			 		}
				  			 	}
				  			 });
				  			checkclient.setBounds(463, 15, 88, 23);
				  			layeredPane.add(checkclient);
				  			
				  			 lblUnite = new JLabel("Unite:");
				  			lblUnite.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  			lblUnite.setBounds(27, 177, 102, 26);
				  			layeredPane.add(lblUnite);
				  			
				  			 comboUnite = new JComboBox();
				  			comboUnite.setBounds(106, 179, 251, 26);
				  			layeredPane.add(comboUnite);
				  		
				  		 
				  		 listeMatierePremiere = new ArrayList<MatierePremier>();
					  		
						   	listeMatierePremiere=dao.findAll();
						     afficher_table(listeMatierePremiere);  
						     comboUnite.addItem("");
						     comboUniteModifier.addItem("");
					for(int i=0;i<listeGrammageBox.size();i++)
					{
						
						GrammageBox grammageBox=listeGrammageBox.get(i);
						comboUnite.addItem(grammageBox.getCodeGrammage());
						comboUniteModifier.addItem(grammageBox.getCodeGrammage());
						MapGrammageBox.put(grammageBox.getCodeGrammage(), grammageBox);
						
					}
					comboUnite.addItem(Constantes.UNITE_PIECE);	
					comboUniteModifier.addItem(Constantes.UNITE_PIECE);	
					
					comboUnite.setSelectedItem("");	
					
					 comboTypeOffre = new JComboBox();
					comboTypeOffre.setBounds(106, 216, 251, 26);
					layeredPane.add(comboTypeOffre);
					
					 lblTypeOffre = new JLabel("Type Offre :");
					lblTypeOffre.setFont(new Font("Tahoma", Font.PLAIN, 12));
					lblTypeOffre.setBounds(27, 214, 80, 26);
					layeredPane.add(lblTypeOffre);
					comboUniteModifier.setSelectedItem("");	
					
					 lblTypeOffreModifier = new JLabel("Type Offre :");
					lblTypeOffreModifier.setFont(new Font("Tahoma", Font.PLAIN, 12));
					lblTypeOffreModifier.setBounds(30, 214, 102, 26);
					layeredPane_1.add(lblTypeOffreModifier);
					
					 comboTypeOffreModifier = new JComboBox();
					comboTypeOffreModifier.setBounds(113, 215, 230, 26);
					layeredPane_1.add(comboTypeOffreModifier);
						     
					comboUnite.setVisible(false);	
					lblUnite.setVisible(false);	
					
					comboUniteModifier.setVisible(false);	
					lblUniteModifier.setVisible(false);	
					
					comboTypeOffre.addItem("");
					comboTypeOffreModifier.addItem("");
					
					for(int i=0;i<listeTypeOffre.size();i++)
					{
						
					TypeOffre typeOffre=listeTypeOffre.get(i);
					comboTypeOffre.addItem(typeOffre.getTypeOffre());
					comboTypeOffreModifier.addItem(typeOffre.getTypeOffre());
					MapTypeOffre.put(typeOffre.getTypeOffre(), typeOffre);
						
					}
					
					comboTypeOffre.setVisible(false);	
					lblTypeOffre.setVisible(false);	
					
					comboTypeOffreModifier.setVisible(false);	
					lblTypeOffreModifier.setVisible(false);	
					
					comboTypeOffre.setSelectedItem("");
					
					JLabel lblDate = new JLabel("Date  :");
					lblDate.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
					lblDate.setBounds(27, 142, 67, 24);
					layeredPane.add(lblDate);
					
					 dateChooser = new JDateChooser();
					dateChooser.setLocale(Locale.FRANCE);
					dateChooser.setDateFormatString("dd/MM/yyyy");
					dateChooser.setBounds(106, 139, 251, 26);
					layeredPane.add(dateChooser);
					
					 comboClient = new JComboBox();
					comboClient.setBounds(385, 43, 235, 33);
					layeredPane.add(comboClient);
					 AutoCompleteDecorator.decorate(comboClient);
					comboTypeOffreModifier.setSelectedItem("");
					
					 dateChooserModifier = new JDateChooser();
					dateChooserModifier.setLocale(Locale.FRANCE);
					dateChooserModifier.setDateFormatString("dd/MM/yyyy");
					dateChooserModifier.setBounds(113, 143, 230, 26);
					layeredPane_1.add(dateChooserModifier);
					
					JLabel lblDate_1 = new JLabel("Date :");
					lblDate_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
					lblDate_1.setBounds(27, 142, 67, 24);
					layeredPane_1.add(lblDate_1);
					
					 comboClientModifier = new JComboBox();
					comboClientModifier.setBounds(363, 47, 235, 33);
					layeredPane_1.add(comboClientModifier);
					 AutoCompleteDecorator.decorate(comboClientModifier);
					comboClient.addItem("");
					comboClientModifier.addItem("");
					for(int t=0;t<listeClient.size();t++)
					{
						
						Client client=listeClient.get(t);
						comboClient.addItem(client.getNom());
						comboClientModifier.addItem(client.getNom());
						MapClient.put(client.getNom(), client);
						
					}
					
					comboClientModifier.setVisible(false);
					comboClient.setVisible(false);
	}
	
	void afficher_table(List<MatierePremier> listeMatierePremiere)
	{
		intialiserTableau();
		
		
			  int i=0;
				while(i<listeMatierePremiere.size())
				{	
					MatierePremier matierePremiere=listeMatierePremiere.get(i);
					
					String categorie="--";
					String subCategorie="--";
					
					if(matierePremiere.getCategorieMp()!=null )
						categorie=matierePremiere.getCategorieMp().getNom();
					
					if(matierePremiere.getCategorieMp()!=null && matierePremiere.getCategorieMp().getSubCategorieMp()!=null)
						subCategorie=matierePremiere.getCategorieMp().getSubCategorieMp().getNom();
					
					Object []ligne={matierePremiere.getId(),matierePremiere.getCode(),matierePremiere.getNom(),categorie,subCategorie};

					modele.addRow(ligne);
					i++;
				}

				table.setModel(modele); 
	
	}
	
	void intialiserTableau(){
		modele=	new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"id","Code","Nom MP", "Catégorie", "Sous Catégorie"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
			table.setModel(modele);
			table.getColumnModel().getColumn(0).setPreferredWidth(10);
		    table.getColumnModel().getColumn(1).setPreferredWidth(60);
		    table.getColumnModel().getColumn(2).setPreferredWidth(260);
		    table.getColumnModel().getColumn(3).setPreferredWidth(60);
		    table.getColumnModel().getColumn(4).setPreferredWidth(60);
		    table.getTableHeader().setReorderingAllowed(false);
			
	}
	void intialiser()
	{
		
		nom.setText("");
		categorie.setSelectedItem("");
		checkclient.setSelected(false);
		txtprix.setText("");
		comboUnite.setVisible(false);	
		 	
		
		
	}
	
	
	void intialiserModifier()
	{
		
		txtNomModif.setText("");
		comboCatModif.setSelectedItem("");
		checkclientmodifier.setSelected(false);
		txtprixmodifier.setText("");
		txtCodeModif.setText("");
		comboUniteModifier.setSelectedItem("");	
		comboUnite.setSelectedItem("");		     
	      
			
		
	}
	
	
	public void  GenererStockMp(String codedepot , MatierePremier matierePremier , BigDecimal prix)
	{

		   	
		   	List<Magasin> listMagasin= new ArrayList<Magasin>();
		  List<FournisseurMP> listFournisseurMP= new ArrayList<FournisseurMP>();
		  if(matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
		  {
			  listFournisseurMP=fournisseurMPDAO.findByCategorie(matierePremier.getCategorieMp().getSubCategorieMp()); 
		  }
		
		
		if(codedepot.equals(Constantes.CODE_DEPOT_SIEGE)){
			listMagasin=depotDAO.listeMagasinByTypeMagasin(Constantes.MAGASIN_CODE_TYPE_MP);
			
	   	} else {
	   		Depot depot=depotDAO.findByCode(codedepot);
	   		listMagasin=depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_MP);
	   		
	   	}
		  
		  
		  
		   		Utils.genererStockByMagasinMP(matierePremier,listMagasin,listFournisseurMP,prix);
		   		
		   		
		   		
		   		if(codedepot.equals(Constantes.CODE_DEPOT_SIEGE)){
					listMagasin=depotDAO.listeMagasinByTypeMagasin(Constantes.MAGASIN_CODE_TYPE_DECHET_MP);
					
			   	} else {
			   		Depot depot=depotDAO.findByCode(codedepot);
			   		listMagasin=depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_DECHET_MP);
			   		
			   	}
				  
				  
				  
				   		Utils.genererStockByMagasinMP(matierePremier,listMagasin,listFournisseurMP,prix);
		   		
		   		
		   		
		   		
		   
		   	
	}
	
	
	
	public static boolean générerStockByMagasinMP(List<MatierePremier> listMP,List<Magasin> listMagasin){ 
	//	  stockMP = new StockMP();
		for(int i=0;i<listMP.size();i++)
			for(int j=0;j<listMagasin.size();j++){
				StockMP	stockMP = new StockMP();
					
					
			}
		
		
		return true ;
		
	}
}

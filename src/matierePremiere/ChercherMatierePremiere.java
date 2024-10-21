package matierePremiere;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import main.ProdLauncher;
import util.Constantes;

import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import dao.daoImplManager.CategorieMpDAOImpl;
import dao.daoImplManager.ClientDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.ClientDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.entity.CategorieMp;
import dao.entity.Client;
import dao.entity.MatierePremier;
import javax.swing.JCheckBox;


public class ChercherMatierePremiere extends JLayeredPane {
	public JLayeredPane contentPane;	

	private ImageIcon imgModifier;
	private ImageIcon imgInit;
	private ImageIcon imgChercher;
	
	private JButton btnInitialiser;
	private JButton btnModifier;
	
	private  MatierePremiereDAO matierePremiereDAO;
	private CategorieMpDAO categorieMpDAO;
	
	
	private JTextField txtCode;
	private JTextField txtNomMP;
	private JTextField codeMatierePremiere;
	
	private Map< String, MatierePremier> mapMatierePremiere = new HashMap<>();
	private Map< String, String> mapCode= new HashMap<>();
	private Map< String, String> mapLibelle = new HashMap<>();
	
	private Map< String, CategorieMp> mapCategorieMp = new HashMap<>();
	
	
	private JComboBox comboMatierePremiere = new JComboBox();
	private JComboBox comboCategorie = new JComboBox();
	
	
	private List<CategorieMp> listCategorieMp = new ArrayList<CategorieMp>();
	private CategorieMp categorieMp=new CategorieMp();
	
	private List<MatierePremier> listMatierePremiere = new ArrayList<MatierePremier>();
	private MatierePremier matierePremier=new MatierePremier();
	JComboBox comboClient = new JComboBox();
	JCheckBox checkclient = new JCheckBox("CLIENT");
	ClientDAO clientDAO;
	private Map< String, Client> MapClient = new HashMap<>();
	List<Client> listeClient= new ArrayList<Client>();
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public ChercherMatierePremiere() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 565);
        try{
        	
        	matierePremiereDAO= new MatierePremierDAOImpl();
        	categorieMpDAO= new CategorieMpDAOImpl();
        	clientDAO=new ClientDAOImpl();
        	listeClient=clientDAO.findAll();
        	
        	
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion √† la base de donn√©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
	
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgChercher= new ImageIcon(this.getClass().getResource("/img/chercher.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
          } catch (Exception exp){exp.printStackTrace();}
        
        comboMatierePremiere.addItem("");
        comboCategorie.addItem("");
        
        listMatierePremiere = matierePremiereDAO.findAll();	
	     	
	      int i=0;
	      	while(i<listMatierePremiere.size())
	      		{	
	      			MatierePremier matierePremiere=listMatierePremiere.get(i);
	      			mapMatierePremiere.put(matierePremiere.getNom(), matierePremiere);
	      			mapLibelle.put(matierePremiere.getNom(), matierePremiere.getCode());
		      		mapCode.put(matierePremiere.getCode(),matierePremiere.getNom());
	      			
	      			comboMatierePremiere.addItem(matierePremiere.getNom());
	      			i++;
	      		}
	      	
	      	 listCategorieMp = categorieMpDAO.findAll();	
		     	
		       i=0;
		      	while(i<listCategorieMp.size())
		      		{	
		      		CategorieMp categorieMp=listCategorieMp.get(i);
		      			mapCategorieMp.put(categorieMp.getNom(), categorieMp);
		      			
		      			comboCategorie.addItem(categorieMp.getNom());
		      			i++;
		      		}
		      	
	      	
	      
        
				  		  btnModifier = new JButton("Modifier ");
				  		  btnModifier.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  		
				  		  		if(matierePremier.getId()<1){
				  		  			
				  		  		JOptionPane.showMessageDialog(null, "Il faut chercher la matiËre premiËre ‡ modifier!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		  		}else {
				  		  			
				  		  			
				  		  		
				  		  			matierePremier.setNom(txtNomMP.getText());
				  		  			matierePremier.setCategorieMp(mapCategorieMp.get(comboCategorie.getSelectedItem()));
				  		  			matierePremiereDAO.edit(matierePremier);
				  		  		
				  		  		JOptionPane.showMessageDialog(null, "la matiËre premiËre a ÈtÈ modifiÈe avec succËs!", "SuccËs", JOptionPane.INFORMATION_MESSAGE);
				  		  		
				  		  		}
				  		  	}
				  		  });
				  		btnModifier.setIcon(imgModifier);
				  		 btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnModifier.setBounds(271, 351, 126, 26);
				  		 add(btnModifier);
				  		 
				  		  btnInitialiser = new JButton("Initialiser");
				  		  btnInitialiser.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  	intialiser ();
				  		  	}
				  		  });
				  		btnInitialiser.setIcon(imgInit);
				  		 btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnInitialiser.setBounds(420, 351, 112, 26);
				  		 add(btnInitialiser);
				  		 
				  		 JLayeredPane layeredPane = new JLayeredPane();
				  		   
				  		   JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		   titledSeparator.setBounds(11, 156, 902, 24);
				  		   add(titledSeparator);
				  		   titledSeparator.setTitle("Informations  Mati\u00E8re Premi\u00E8re");
				  		   
				  		  
				  		   layeredPane.setBorder(new MatteBorder(0, 1, 1, 1, (Color) Color.LIGHT_GRAY));
				  		   layeredPane.setBounds(10, 169, 903, 171);
				  		   add(layeredPane);
				  		   
				  		   JLabel lblNom = new JLabel("Nom :");
				  		   lblNom.setBounds(29, 70, 130, 26);
				  		   layeredPane.add(lblNom);
				  		   lblNom.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   
				  		   txtNomMP = new JTextField();
				  		 util.Utils.copycoller(txtNomMP);
				  		   txtNomMP.setBounds(122, 71, 191, 26);
				  		   layeredPane.add(txtNomMP);
				  		   txtNomMP.setColumns(10);
				  		   txtCode = new JTextField();
				  		 util.Utils.copycoller(txtCode);
				  		   txtCode.setBackground(Color.WHITE);
				  		   txtCode.setDisabledTextColor(Color.BLACK);
				  		   txtCode.setEditable(false);
				  		   txtCode.setBounds(122, 34, 191, 26);
				  		   layeredPane.add(txtCode);
				  		   txtCode.setColumns(10);
				  		   
				  		   JLabel lblCodeLigne = new JLabel("Code :");
				  		   lblCodeLigne.setBounds(29, 33, 114, 26);
				  		   layeredPane.add(lblCodeLigne);
				  		   lblCodeLigne.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		  
				  		   			JLabel lblCatgorie = new JLabel("Cat\u00E9gorie :");
				  		   			lblCatgorie.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblCatgorie.setBounds(29, 108, 114, 26);
				  		   			layeredPane.add(lblCatgorie);
				  		   			
				  		   			 
				  		   			comboCategorie.setBounds(122, 108, 191, 26);
				  		   			layeredPane.add(comboCategorie);
				  		   			
				  		   			JLayeredPane layeredPane_1 = new JLayeredPane();
				  		   			layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   			layeredPane_1.setBounds(10, 11, 901, 107);
				  		   			add(layeredPane_1);
				  		   			
				  		   			JButton btnChercherEmploye = new JButton("Chercher");
				  		   			btnChercherEmploye.setBounds(775, 15, 116, 26);
				  		   			layeredPane_1.add(btnChercherEmploye);
				  		   			btnChercherEmploye.setIcon(imgChercher);
				  		   			
				  		   		
					  		      comboMatierePremiere.addItemListener(new ItemListener() {
					  		     	 	public void itemStateChanged(ItemEvent e) {
					  		     	 	
					  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
					  		     	
						  		     	   	codeMatierePremiere.setText(mapLibelle.get(comboMatierePremiere.getSelectedItem()));
					  	                   
						  		     	   	if(codeMatierePremiere.getText().equals(""))
						  		     	   	{
						  		     	   	codeMatierePremiere.setText(Constantes.CODE_MP);
						  		     	   	}
						  		     	   	
						  		     	   	
						  		     	   	
					  		     	 	 	}
					  		     	 	}
					  		     	 });
							
							
						        
				  		   			comboMatierePremiere.setBounds(471, 15, 263, 26);
				  		   			layeredPane_1.add(comboMatierePremiere);
				  		   		AutoCompleteDecorator.decorate(comboMatierePremiere);
				  		   			codeMatierePremiere = new JTextField();
				  		   		 util.Utils.copycoller(codeMatierePremiere);
				  		   			codeMatierePremiere.setBounds(124, 15, 191, 26);
				  		   			layeredPane_1.add(codeMatierePremiere);
				  		   			codeMatierePremiere.setColumns(10);
				  		   			
				  		   			JLabel lblLibelle = new JLabel("Nom :");
				  		   			lblLibelle.setBounds(384, 14, 147, 26);
				  		   			layeredPane_1.add(lblLibelle);
				  		   			lblLibelle.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			
				  		   			JLabel lblCode = new JLabel("Code :");
				  		   			lblCode.setBounds(35, 14, 139, 26);
				  		   			layeredPane_1.add(lblCode);
				  		   			lblCode.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			
					  		    codeMatierePremiere.addKeyListener(new KeyAdapter() {
								  	@Override
								  	public void keyReleased(KeyEvent e)
								  	{
								  		if (e.getKeyCode() == e.VK_ENTER)
								  		{
								  			
								  			comboMatierePremiere.setSelectedItem(mapCode.get(codeMatierePremiere.getText().toUpperCase()));
								  		}}});
				  		   			btnChercherEmploye.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent e) {
				  		   					
				  		   				if(codeMatierePremiere.getText().equals("") && comboMatierePremiere.getSelectedItem().equals(""))
		  		     			JOptionPane.showMessageDialog(null, "Il faut remplir au critËre de recherche!", "Attention", JOptionPane.INFORMATION_MESSAGE);
		  		     		
		  		     		 else {
		  		     			 
		  		     			 
		  		     				
		  		     				matierePremier = matierePremiereDAO.findByCode(codeMatierePremiere.getText());
		  		     				
		  		     			  
		  		     			
		  		     			  if(codeMatierePremiere!=null){
		  		     				  
		  		     				  
		  		     				  if(checkclient.isSelected()==true)
		  		     				  {
		  		     					  if(comboClient.getSelectedItem().equals(""))
		  		     					  {
		  		     						 JOptionPane.showMessageDialog(null, "Veuillez Selectionner Le Client SVP"); 
		  		     						 return;
		  		     					  }
		  		     				  }
		  		     				  
		  		     			 
		  		     			txtCode.setText(matierePremier.getCode());
		  		     			txtNomMP.setText(matierePremier.getNom());
		  		     			comboCategorie.setSelectedItem(matierePremier.getCategorieMp().getNom());
		  		     			
		  		     			
		  		     			
		  		     			  }else {
		  		     				JOptionPane.showMessageDialog(null, "Il n'existe aucun rÈsultat pour ces critËres de recherche. Merci de vÈrifier votre critËre !", "Attention", JOptionPane.INFORMATION_MESSAGE);
		  		     			  }
		  		     		 }
				  		   					
				  		   				}
				  		   			});
				  		
				  		   		codeMatierePremiere.setText(Constantes.CODE_MP);		  		 
				  		   		
				  		   		  checkclient = new JCheckBox("CLIENT");
				  		   		  checkclient.addActionListener(new ActionListener() {
				  		   		  	public void actionPerformed(ActionEvent arg0) {
				  		   		  		

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
				  		   		checkclient.setBounds(24, 65, 88, 23);
				  		   		layeredPane_1.add(checkclient);
				  		   		
				  		   		  comboClient = new JComboBox();
				  		   		  comboClient.addActionListener(new ActionListener() {
				  		   		  	public void actionPerformed(ActionEvent arg0) {
				  		   		  		
				  		   			comboMatierePremiere.removeAllItems();
				  		   		  		if(comboClient.getSelectedItem()!=null)
				  		   		  		{
				  		   		  		
				  		   		  			Client client=MapClient.get(comboClient.getSelectedItem().toString());
				  		   		  			if(client!=null)
				  		   		  			{
				  		   		  			mapMatierePremiere.clear();
				  			      			mapLibelle.clear();
				  				      		mapCode.clear();
				  			      			
				  			      			comboMatierePremiere.removeAllItems();
				  			      		
				  			      		comboMatierePremiere.addItem("");
				  		   		  			listMatierePremiere=matierePremiereDAO.listeMatierePremierByClient(client);
				  		   		  			
				  		   		  	   	for(int i=0; i<listMatierePremiere.size();i++)
				  			      		{	
				  			      			MatierePremier matierePremiere=listMatierePremiere.get(i);
				  			      			mapMatierePremiere.put(matierePremiere.getNom(), matierePremiere);
				  			      			mapLibelle.put(matierePremiere.getNom(), matierePremiere.getCode());
				  				      		mapCode.put(matierePremiere.getCode(),matierePremiere.getNom());
				  			      			
				  			      			comboMatierePremiere.addItem(matierePremiere.getNom());
				  			      			 
				  			      		}
				  		   		  			
				  		   		  	comboMatierePremiere.setSelectedItem("");
				  		   		  			
				  		   		  			}else
				  		   		  			{
				  		   		  				
				  		   		  				
				  		   		  			listMatierePremiere=matierePremiereDAO.findAll();
				  		   		  			
				  		   		  		mapMatierePremiere.clear();
			  			      			mapLibelle.clear();
			  				      		mapCode.clear();
			  			      			
			  			      			comboMatierePremiere.removeAllItems();
			  			      		comboMatierePremiere.addItem("");
			  			      			
			  			      		for(int i=0; i<listMatierePremiere.size();i++)
			  			      		{	
			  			      			MatierePremier matierePremiere=listMatierePremiere.get(i);
			  			      			mapMatierePremiere.put(matierePremiere.getNom(), matierePremiere);
			  			      			mapLibelle.put(matierePremiere.getNom(), matierePremiere.getCode());
			  				      		mapCode.put(matierePremiere.getCode(),matierePremiere.getNom());
			  			      			
			  			      			comboMatierePremiere.addItem(matierePremiere.getNom());
			  			      			 
			  			      		}
			  			      	comboMatierePremiere.setSelectedItem("");
				  		   		  			}
				  		   		  			
				  		   		  			
				  		   		  		}else
				  		   		  		{
				  		   		  			listMatierePremiere=matierePremiereDAO.findAll();
				  		   		  			
				  		   		  		mapMatierePremiere.clear();
			  			      			mapLibelle.clear();
			  				      		mapCode.clear();
			  			      			
			  			      			comboMatierePremiere.removeAllItems();
			  			      		comboMatierePremiere.addItem("");
			  			      		for(int i=0; i<listMatierePremiere.size();i++)
			  			      		{	
			  			      			MatierePremier matierePremiere=listMatierePremiere.get(i);
			  			      			mapMatierePremiere.put(matierePremiere.getNom(), matierePremiere);
			  			      			mapLibelle.put(matierePremiere.getNom(), matierePremiere.getCode());
			  				      		mapCode.put(matierePremiere.getCode(),matierePremiere.getNom());
			  			      			
			  			      			comboMatierePremiere.addItem(matierePremiere.getNom());
			  			      			 
			  			      		}
			  			      		
			  			      	comboMatierePremiere.setSelectedItem("");
			  			      		
				  		   		  		}
				  		   		  		
				  		   		  	}
				  		   		  });
				  		   		comboClient.setBounds(124, 66, 440, 26);
				  		   		layeredPane_1.add(comboClient);
                             AutoCompleteDecorator.decorate(comboClient);
                             
                             
								comboClient.addItem("");
								 
								for(int t=0;t<listeClient.size();t++)
								{
									
									Client client=listeClient.get(t);
									comboClient.addItem(client.getNom());
									 
									MapClient.put(client.getNom(), client);
									
								}
								
								 
								comboClient.setVisible(false);	
								
								comboClient.setSelectedItem("");
				  		   		
				  		   		
	}
	
	
	

	
void intialiser (){
		
		txtCode.setText("");
		txtNomMP.setText("");
		comboCategorie.setSelectedItem("");
		
		comboMatierePremiere.setSelectedItem("");
		codeMatierePremiere.setText(Constantes.CODE_MP);
	}
}

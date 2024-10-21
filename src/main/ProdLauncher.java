package main;

import java.io.File;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;

import util.Constantes;
import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.ArticlesMPDAOImpl;
import dao.daoImplManager.CategorieMpDAOImpl;
import dao.daoImplManager.ChargeFixeDAOImpl;
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargeVariableDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.ClientDAOImpl;
import dao.daoImplManager.CompteMagasinierDAOImpl;
import dao.daoImplManager.CompteStockMPDAOImpl;
import dao.daoImplManager.CompteurAbsenceEmployeDAOImpl;
import dao.daoImplManager.CompteurEmployeProdDAOImpl;
import dao.daoImplManager.CompteurNumDossierDAOImpl;
import dao.daoImplManager.CompteurProductionDAOImpl;
import dao.daoImplManager.CompteurResponsableProdDAOImpl;
import dao.daoImplManager.CompteurTransferMPDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailChargeFixeDAOImpl;
import dao.daoImplManager.DetailChargeVariableDAOImpl;
import dao.daoImplManager.DetailCoutProductionDAOImpl;
import dao.daoImplManager.DetailEstimationDAOImpl;
import dao.daoImplManager.DetailEstimationMPDAOImpl;
import dao.daoImplManager.DetailEstimationPromoDAOImpl;
import dao.daoImplManager.DetailMouvementStockDAOImpl;
import dao.daoImplManager.DetailProdGenDAOImpl;
import dao.daoImplManager.DetailProductionDAOImpl;
import dao.daoImplManager.DetailProductionMPDAOImpl;
import dao.daoImplManager.DetailResponsableProdDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.EmployeBloqueDAOImpl;
import dao.daoImplManager.EmployeDAOImpl;
import dao.daoImplManager.EquipeDAOImpl;
import dao.daoImplManager.FactureProductionDAOImpl;
import dao.daoImplManager.FicheEmployeDAOImpl;
import dao.daoImplManager.FicheEmployeGlobaleDAOImpl;
import dao.daoImplManager.FraisDepotDAOImpl;
import dao.daoImplManager.HabilitationDAOImpl;
import dao.daoImplManager.MachineDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.MenuDAOImpl;
import dao.daoImplManager.MouvementStockGlobalDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.PrixClientMPDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.ProductionMPDAOImpl;
import dao.daoImplManager.SequenceurDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.StockPFDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoImplManager.TavailHorsProdDAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoImplManager.TransferStockPFDAOImpl;
import dao.daoImplManager.TypeEquipeDAOImpl;
import dao.daoImplManager.TypeResEmployeDAOImpl;
import dao.daoImplManager.TypeSortieDAOImpl;
import dao.daoImplManager.UtilisateurDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.ArticlesMPDAO;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.ChargeFixeDAO;
import dao.daoManager.ChargeProductionDAO;
import dao.daoManager.ChargeVariableDAO;
import dao.daoManager.ChargesDAO;
import dao.daoManager.ClientDAO;
import dao.daoManager.CompteMagasinierDAO;
import dao.daoManager.CompteStockMPDAO;
import dao.daoManager.CompteurAbsenceEmployeDAO;
import dao.daoManager.CompteurEmployeProdDAO;
import dao.daoManager.CompteurNumDossierDAO;
import dao.daoManager.CompteurProductionDAO;
import dao.daoManager.CompteurResponsableProdDAO;
import dao.daoManager.CompteurTransferMPDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailChargeFixeDAO;
import dao.daoManager.DetailChargeVariableDAO;
import dao.daoManager.DetailCoutProductionDAO;
import dao.daoManager.DetailEstimationDAO;
import dao.daoManager.DetailEstimationMPDAO;
import dao.daoManager.DetailEstimationPromoDAO;
import dao.daoManager.DetailMouvementStockDAO;
import dao.daoManager.DetailProdGenDAO;
import dao.daoManager.DetailProductionDAO;
import dao.daoManager.DetailProductionMPDAO;
import dao.daoManager.DetailResponsableProdDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.EmployeBloqueDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.EquipeDAO;
import dao.daoManager.FactureProductionDAO;
import dao.daoManager.FicheEmployeDAO;
import dao.daoManager.FicheEmployeGlobaleDAO;
import dao.daoManager.FraisDepotDAO;
import dao.daoManager.HabilitationDAO;
import dao.daoManager.MachineDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.MenuDAO;
import dao.daoManager.MouvementStockGlobalDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.PrixClientMPDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.ProductionMPDAO;
import dao.daoManager.SequenceurDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.daoManager.TransferStockPFDAO;
import dao.daoManager.TravailHorsProdDAO;
import dao.daoManager.TypeEquipeDAO;
import dao.daoManager.TypeResEmployeDAO;
import dao.daoManager.TypeSortieDAO;
import dao.daoManager.UtilisateurDAO;
import dao.entity.Utilisateur;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class ProdLauncher extends javax.swing.JFrame {
	
 
	
	 
	
	
	/**
	 * Creates new form 
	 */
	public ProdLauncher() {
		// ce code affiche les looks installés, leurs noms et leurs classes
		/*
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			 
		} catch (Exception e) {
			System.err.println("Problème dans l'installation L&F système.");
		}
		*/
		initComponents();
		
		setLocationRelativeTo(null);
		new Thread(new thread1()).start();
	}

	public void moveToTheNewWindow() {
		
		this.dispose();
		//authentification authentification = new authentification();
		AuthentificationView authentification = new AuthentificationView();
		authentification.setVisible(true);
		authentification.setLocationRelativeTo(null);
		}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {
 /*
		jPanel1 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		dinningRommProgressBar = new javax.swing.JProgressBar();
		labelLoad = new javax.swing.JLabel();
		labelChangeLoader = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Bienvenue dans Application de Gestion Production V1.0");
		setResizable(false);
		jPanel1.setBackground(new java.awt.Color(255, 255, 255));
		 
		dinningRommProgressBar.setForeground(new java.awt.Color(204, 0, 0));
		dinningRommProgressBar.setOpaque(true);
		dinningRommProgressBar.setStringPainted(true);

		labelLoad.setText("Chargement");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1Layout.setHorizontalGroup(
			jPanel1Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup()
							.addGap(208)
							.addComponent(labelLoad, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(labelChangeLoader, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE))
						.addGroup(jPanel1Layout.createSequentialGroup()
							.addGap(175)
							.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(dinningRommProgressBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addContainerGap(118, Short.MAX_VALUE))
		);
		jPanel1Layout.setVerticalGroup(
			jPanel1Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addComponent(dinningRommProgressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelLoad)
						.addComponent(labelChangeLoader))
					.addContainerGap(94, Short.MAX_VALUE))
		);
		jPanel1.setLayout(jPanel1Layout);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.PREFERRED_SIZE));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		pack();
		utilisaeurDAO=new UtilisateurDAOImpl();
		 */
		
		
		
	}// </editor-fold>

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				/*
				File dir = new File ("C:\\Edition\\EquipeProducion");
				File dir1 = new File ("C:\\Edition\\EquipeGenerique");
				File dir2 = new File ("C:\\Edition\\OrdreFabrication");
				File dir3 = new File ("C:\\Edition\\BonSortieMatierePremiere");
				File dir4 = new File ("C:\\Edition\\BonSortieChargeSupp");
				File dir5 = new File ("C:\\Edition\\FicheDechetMatierePremiere");
				File dir6 = new File ("C:\\Edition\\FicheEmploye");
				File dir7 = new File ("C:\\Edition\\BonSortieMPDeplace");
				File dir8 = new File ("C:\\log");
				
				dir.mkdirs();
				dir1.mkdirs();
				dir2.mkdirs();
				dir3.mkdirs();
				dir4.mkdirs();
				dir5.mkdirs();
				dir6.mkdirs();
				dir7.mkdirs();
				dir8.mkdirs();
			
				dinningRommProgressBar = new JProgressBar(0, 50);
				*/
				new ProdLauncher().setVisible(true);
			}
		});
	}

	class thread1 implements Runnable {
		protected volatile boolean running = true;

		public void run() {

			while (running) {
				
				/*
				for (int i = 0; i <= 50; i++) {  
													 
					dinningRommProgressBar.setValue(i);  
					dinningRommProgressBar.repaint();  
					
					try {
							Thread.sleep(10);
							
					} catch (InterruptedException e) {
					 
						e.printStackTrace();
					}
					if (dinningRommProgressBar.getValue() == 0)  {
						labelChangeLoader
								.setText("modules Graphiques Production...");
					}
					if (dinningRommProgressBar.getValue() == 10){
						labelChangeLoader
								.setText("dépendances métiers Production...");
					}
					if (dinningRommProgressBar.getValue() == 20){
						labelChangeLoader.setText("module de sécurité...");
						}
					if (dinningRommProgressBar.getValue() == 30){
						labelChangeLoader
								.setText("modules simulation et reporting...");
					}
					if (dinningRommProgressBar.getValue() == 40){
						labelChangeLoader
								.setText("dépendances base de données...");
					}
					
				 
					


 
					if (dinningRommProgressBar.getValue() == 49) {
						utilisaeurDAO=new UtilisateurDAOImpl();
						 
						
					}

				}
				*/
				
				
				running = false;
				
				moveToTheNewWindow();
				
				 
			}
		}
	}

	class threadLoadSession implements Runnable {
		//@Override
		public void run() {
			// TODO Auto-generated method stub
		//	Session session=HibernateUtil.openSession();
		}
	}

	// Variables declaration - do not modify
	private javax.swing.JLabel jLabel1;
	private javax.swing.JPanel jPanel1;
	private static javax.swing.JProgressBar dinningRommProgressBar;
	private javax.swing.JLabel labelChangeLoader;
	private javax.swing.JLabel labelLoad;
	// End of variables declaration
}

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.hibernate.Session;

import util.EmailUtil;
import util.HibernateUtil;
import util.NumberUtils;
import dao.daoImplManager.EmployeDAOImpl;
import dao.daoManager.EmployeDAO;
import dao.entity.Employe;


public abstract class Test1 {
	//static Employe employe = new Employe();

//	static Session session = HibernateUtil.openSession();

	public static void main(String[] args) {
		
		//SubCategorieMP subCategorieMP = new SubCategorieMP ("NOM","CODE");
		
		
		

		/*	MatierePremiereDAO dao= new MatierePremierDAOImpl();
		
		ArticlesDAO articlesDAO = new ArticlesDAOImpl();
		
		Articles article = new Articles();
		
		//article=articlesDAO.findById(101);
		
	article.setCodeArticle("1308");
		article.setLiblle("EL NASS 4011 200G");
		
		List<DetailEstimation> lisDetailEstimation = new ArrayList<DetailEstimation>() ;
		
		
		
		
		MatierePremier p1 = dao.findById(22);
		MatierePremier p2 = dao.findById(24);
		MatierePremier p3 = dao.findById(25);
		MatierePremier p4 = dao.findById(26);
		MatierePremier p5 = dao.findById(27);
		MatierePremier p6 = dao.findById(28);
		MatierePremier p7 = dao.findById(29);
		
		DetailEstimation detailEstimation1 = new DetailEstimation();
		DetailEstimation detailEstimation2 = new DetailEstimation();
		DetailEstimation detailEstimation3 = new DetailEstimation();
		DetailEstimation detailEstimation4 = new DetailEstimation();
		DetailEstimation detailEstimation5 = new DetailEstimation();
		DetailEstimation detailEstimation6 = new DetailEstimation();
		DetailEstimation detailEstimation7 = new DetailEstimation();
		
		detailEstimation1.setMatierePremier(p1);
		detailEstimation1.setArticles(article);
		detailEstimation1.setQuantite(1);
		
		detailEstimation2.setMatierePremier(p2);
		detailEstimation2.setArticles(article);
		detailEstimation2.setQuantite(5);
		
		detailEstimation3.setMatierePremier(p3);
		detailEstimation3.setArticles(article);
		detailEstimation3.setQuantite(2);
		
		detailEstimation4.setMatierePremier(p4);
		detailEstimation4.setArticles(article);
		detailEstimation4.setQuantite(1);
		
		detailEstimation5.setMatierePremier(p5);
		detailEstimation5.setArticles(article);
		detailEstimation5.setQuantite((float) 0.1);
		
		detailEstimation6.setMatierePremier(p6);
		detailEstimation6.setArticles(article);
		detailEstimation6.setQuantite((float) 0.2);
		
		detailEstimation7.setMatierePremier(p7);
		detailEstimation7.setArticles(article);
		detailEstimation7.setQuantite((float) 0.24);
		
		lisDetailEstimation.add(detailEstimation1);
		lisDetailEstimation.add(detailEstimation2);
		lisDetailEstimation.add(detailEstimation3);
		lisDetailEstimation.add(detailEstimation4);
		lisDetailEstimation.add(detailEstimation5);
		lisDetailEstimation.add(detailEstimation6);
		lisDetailEstimation.add(detailEstimation7);
		
		article.setDetailEstimation(lisDetailEstimation);
		
		DetailCommandeDAO detailCommandeDAO= new DetailCommandeDAOImpl();
		
		List detailCommande=detailCommandeDAO.findByMP(9);
		
		float prix =(float) detailCommande.get(0);
	//	articlesDAO.add(article);
		
		
		
		MatierePremier p = dao.findByCode("MP1000");
		
		p.getCategorieMp();
		p.getCategorieMp().getSubCategorieMp();
		
		/*CategorieMpDAO daoCategorie= new CategorieMpDAOImpl();
		CommandeDAO commandeDAO= new CommandeDAOImpl();
	 	FournisseurDAO fournisseurDAO= new FournisseurDAOImpl();
	 	Fournisseur fournisseur =fournisseurDAO.findById(1);
		MatierePremier p = dao.findById(9);
		List<RipFournisseur> listRipFournisseur= new ArrayList<RipFournisseur>();
		RipFournisseur ripFournisseur = new RipFournisseur();*/
		/*ripFournisseur.setRip("000000");
		ripFournisseur.setCode("KA001");
		
		RipFournisseur ripFournisseur1 = new RipFournisseur();
		ripFournisseur1.setRip("11111");
		ripFournisseur.setCode("KA002");
		
		listRipFournisseur.add(ripFournisseur);
		listRipFournisseur.add(ripFournisseur1);*/
		
	//	Fournisseur fournisseur = new Fournisseur();
		/*if(listRipFournisseur.size()!=0)
		fournisseur.setRipFournisseurs(listRipFournisseur);
		fournisseur.setAdresse("N° 11 RUE 7");
		fournisseur.setEmail("kal@.hotmail.fr");
		fournisseur.setNom("HAMA");
		fournisseur.setCode("FR001");
		//fournisseur.setRipFournisseurs("123456789");
		fournisseur.setTel("232323");
		
		ripFournisseur.setFournisseur(fournisseur);
	//	ripFournisseur1.setFournisseur(fournisseur);
		fournisseurDAO.add(fournisseur);*/
		
	
	 
		
//bolck matière première 		
	/*	p.setCode("1324");
		p.setNom("EL NASS 4011");
		CategorieMp  CategorieMp =daoCategorie.findById(9);
		
		p.setCategorieMp(CategorieMp);
		dao.add(p);*/
	// MatierePremier	p=dao.findById(9);
		
		
		
//bolck commande ça marche très bien 
		
		/*Commande commande = new Commande();
	//	Fournisseur fournisseur = fournisseurDAO.findById(7);
		UtilisateurDAO utilisateurDAO = new UtilisateurDAOImpl();
		DetailCommandeDAO detailCommandeDAO= new DetailCommandeDAOImpl();
		Utilisateur utilisateur=utilisateurDAO.findById(1);
		
		DetailCommande detailCommande= new DetailCommande();
		
		detailCommande.setMatierePremier(p);
		detailCommande.setPrixUnitaire(10);
		detailCommande.setQuantite(20);
		detailCommande.setCommande(commande);
		
		
		
		List<DetailCommande> listCommande= new ArrayList<DetailCommande>();
		listCommande.add(detailCommande);
		
		commande.setDate(new Date());
		commande.setDateLaivraison(new Date());
		commande.setMontant(1000);
		commande.setDetailCommandes(listCommande);
		commande.setFournisseur(fournisseur);
		commande.setStatut("Crée");
		commande.setCreerPar(utilisateur);
		commande.setModifierPar(utilisateur);
		commande.setAnnulerPar(utilisateur);
		commande.setMontant(13000);
		detailCommande.setCommande(commande);
		commandeDAO.add(commande);*/
		
		
	 /*Commande commandeRecuperer = commandeDAO.findById(6);
	 DetailCommande	 detailCommande =commandeRecuperer.getDetailCommandes().get(0);*/
		/*UtilisateurDAO daoUtilisateur= new UtilisateurDAOImpl();
		
		Utilisateur utilisateur = daoUtilisateur.findUtilisateurByLoginMotPasse("admin", "admin");
		
		System.out.print("Le login est "+utilisateur.getLogin());
		
		EmployeDAO employeDAO = new EmployeDAOImpl();
		
		 employe =employeDAO.findById(1);
		
		
		try {
			EmailUtil.sendEmailSSL("logiciel0687@gmail.com",
					"Inscription effectuée avec succès",
					registerMailBody());
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	private static String registerMailBody() {
		return "<HTML><b>Votre profil a été créé avec succès</b><br><br>"
				+ "<br>Vos Paramètres de connexion à l'application<br>"
				+ "<UL><LI>Nom Utilisateur: "
				+ "<b>"
				+ employe.getNom()
				+ "</b></LI><br>"
				+ "<LI>Addresse email: "
				+ "<b>"
				+ "a.hamaina"
				+ "</b></LI><br>"
				+ "<LI>Mot de passe: "
				+ "<b>"
				+ "Test1234"
				+ "</b></LI></UL><br>"
				+ "<p>Pour compléter votre profil,connectez vous sur l'application Dinning Room et profitez des autres services et fonctionnalités de notre plateforme</p><br><br><br>"
				+ "Merci pour votre confiance<br>"
				+ "Service Informatique<br>"
				+"Système Production</HTML>";*/
		
	//System.out.println("la valeur est :"	+Math.ceil(4000*0.0005882));
	//System.out.println("la valeur est :"	+Math.floor(4000*0.0005882));
	
	for(int i=0;i<10;i++){
	System.out.println("la valeur up est 9.45555 i="+i+" :"	+NumberUtils.roundUp(0.9995,i ));
	System.out.println("------------------------------------------------------");
	}
	System.out.println("------------------------------------------------------");
	//for(int i=0;i<10;i++)
	//	System.out.println("la valeur down est 9.45555 i="+i+" :"	+NumberUtils.roundDown(0.00006,i ));
	
	
	
	}

}

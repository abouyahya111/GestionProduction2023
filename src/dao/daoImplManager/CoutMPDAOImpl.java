package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.Constantes;
import util.HibernateUtil;
import dao.daoManager.ActionMPDAO;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.CoutMPDAO;
import dao.daoManager.MotifPerteMPDAO;
import dao.entity.ActionMP;
import dao.entity.Articles;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailEstimation;
import dao.entity.FournisseurMP;
import dao.entity.MatierePremier;
import dao.entity.MotifPerteMP;
import dao.entity.Production;

public class CoutMPDAOImpl implements CoutMPDAO {
	Session session=HibernateUtil.openSession();

	public void add(CoutMP e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public CoutMP edit(CoutMP e) {
		
	session.beginTransaction();
	CoutMP p= (CoutMP)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		CoutMP p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<CoutMP> findAll() {
		return session.createQuery("select c from CoutMP c").list();
		}

	public CoutMP findById(int id) {
		return (CoutMP)session.get(CoutMP.class, id);
		}
	
	
	
	@Override
	public List<Object[]> listeCoutProductionParArticle(Date dateDebut,Date dateFin,String statut,String depot , Articles article) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
		//	query= session.createQuery("select p.date,(select sum(dp.delaiEmploye) from DetailProduction dp where dp.production.id=p.id and dp.absent=0) +(select  sum(dg.delaiEmploye) from DetailProdGen dg where p.id=dg.productionGen.id and dg.absent=0), 14.13,(select count(*) from DetailProduction dp where dp.production.id=p.id and dp.absent=0) + (select count(*) from DetailProdGen dg where  p.id=dg.productionGen.id and dg.absent=0 ),sum(p.quantiteReel),l.machine.nom , p.articles.liblle FROM Production p  ,LigneMachine l where  p.ligneMachine.id=l.id and p.date between :dateDebut and :dateFin and p.statut =:statut and p.codeDepot=:depot group by p.date ,l.machine.nom");
		query= session.createQuery("select c.prodcutionCM.articles,sum(c.prixUnitaire * c.quantConsomme),sum(c.prodcutionCM.coutTotalEmployeGen), sum(dp.coutTotal)+sum(dp.coutSupp25)+sum(dp.coutSupp50),sum(de.coutTotal)+sum(de.coutSupp25)+sum(de.coutSupp50),sum(c.prodcutionCM.quantiteReel)  FROM CoutMP c , DetailProduction dp , DetailProdGen de  where  c.prodcutionCM.id=dp.production.id and c.prodcutionCM.id=de.productionGen.id and c.prodcutionCM.date between :dateDebut and :dateFin and c.prodcutionCM.statut=:statut and c.prodcutionCM.codeDepot=:depot and c.prodcutionCM.articles.id=:article");

			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("statut", statut);
			query.setParameter("depot", depot);
			query.setParameter("article", article.getId());
		return query.list();

	}
	
	
	@Override
	public List<Object[]> listeCoutMPParArticle(Date dateDebut,Date dateFin,String statut,String depot , Articles article) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
		//	query= session.createQuery("select p.date,(select sum(dp.delaiEmploye) from DetailProduction dp where dp.production.id=p.id and dp.absent=0) +(select  sum(dg.delaiEmploye) from DetailProdGen dg where p.id=dg.productionGen.id and dg.absent=0), 14.13,(select count(*) from DetailProduction dp where dp.production.id=p.id and dp.absent=0) + (select count(*) from DetailProdGen dg where  p.id=dg.productionGen.id and dg.absent=0 ),sum(p.quantiteReel),l.machine.nom , p.articles.liblle FROM Production p  ,LigneMachine l where  p.ligneMachine.id=l.id and p.date between :dateDebut and :dateFin and p.statut =:statut and p.codeDepot=:depot group by p.date ,l.machine.nom");
		
		if(dateDebut!=null && dateFin!=null && !statut.equals("") && !depot.equals("") && article!=null)
		{
			query= session.createQuery("select  c.prodcutionCM.articles,((sum(c.quantConsomme * c.prixUnitaire) + sum(c.quantDechet * c.prixUnitaire) + sum(c.quantiteOffre * c.prixUnitaire) + sum(c.quantDechetFournisseur * c.prixUnitaire) + sum(c.quantiteManquante * c.prixUnitaire)) - sum(c.quantiteManquanteFrPlus * c.prixUnitaire)) FROM CoutMP c   where c.prodcutionCM.date between :dateDebut and :dateFin and c.prodcutionCM.statut=:statut and c.prodcutionCM.codeDepot=:depot and c.prodcutionCM.articles.id=:article group by c.prodcutionCM.articles");

			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("statut", statut);
			query.setParameter("depot", depot);
			query.setParameter("article", article.getId());
		}else if(dateDebut!=null && dateFin!=null && !statut.equals("") && !depot.equals("") && article==null)
		{
			query= session.createQuery("select  c.prodcutionCM.articles,((sum(c.quantConsomme * c.prixUnitaire) + sum(c.quantDechet * c.prixUnitaire) + sum(c.quantiteOffre * c.prixUnitaire) + sum(c.quantDechetFournisseur * c.prixUnitaire) + sum(c.quantiteManquante * c.prixUnitaire)) - sum(c.quantiteManquanteFrPlus * c.prixUnitaire)) FROM CoutMP c   where c.prodcutionCM.date between :dateDebut and :dateFin and c.prodcutionCM.statut=:statut and c.prodcutionCM.codeDepot=:depot group by c.prodcutionCM.articles");

			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("statut", statut);
			query.setParameter("depot", depot);
			
		}
	
		return query.list();

	}
	
	@Override
	public List<Object[]> listeCoutDetailProdResParArticle(Date dateDebut,Date dateFin,String statut,String depot , Articles article) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
		//	query= session.createQuery("select p.date,(select sum(dp.delaiEmploye) from DetailProduction dp where dp.production.id=p.id and dp.absent=0) +(select  sum(dg.delaiEmploye) from DetailProdGen dg where p.id=dg.productionGen.id and dg.absent=0), 14.13,(select count(*) from DetailProduction dp where dp.production.id=p.id and dp.absent=0) + (select count(*) from DetailProdGen dg where  p.id=dg.productionGen.id and dg.absent=0 ),sum(p.quantiteReel),l.machine.nom , p.articles.liblle FROM Production p  ,LigneMachine l where  p.ligneMachine.id=l.id and p.date between :dateDebut and :dateFin and p.statut =:statut and p.codeDepot=:depot group by p.date ,l.machine.nom");
		
		if(dateDebut!=null && dateFin!=null && !statut.equals("") && !depot.equals("") && article!=null)
		{
			query= session.createQuery("select  p.articles ,sum(d.coutTotal),sum(d.coutSupp25),sum(d.coutSupp50)  FROM DetailProdRes d, Production p   where  p.date=d.dateProduction and  p.date between :dateDebut and :dateFin and p.statut=:statut and p.codeDepot=:depot and d.absent=0 and p.articles.id=:article group by p.articles ");

			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("statut", statut);
			query.setParameter("depot", depot);
			query.setParameter("article", article.getId());
		}else if(dateDebut!=null && dateFin!=null && !statut.equals("") && !depot.equals("") && article==null)
		{
			query= session.createQuery("select  p.articles ,sum(d.coutTotal),sum(d.coutSupp25),sum(d.coutSupp50)  FROM DetailProdRes d, Production p   where  p.date=d.dateProduction and  p.date between :dateDebut and :dateFin and p.statut=:statut and p.codeDepot=:depot and d.absent=0  group by p.articles ");

			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("statut", statut);
			query.setParameter("depot", depot);
			
		}
	
		return query.list();

	}
	
	
	@Override
	public List<Object[]> listeCoutDetailProductionParArticle(Date dateDebut,Date dateFin,String statut,String depot , Articles article) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
		//	query= session.createQuery("select p.date,(select sum(dp.delaiEmploye) from DetailProduction dp where dp.production.id=p.id and dp.absent=0) +(select  sum(dg.delaiEmploye) from DetailProdGen dg where p.id=dg.productionGen.id and dg.absent=0), 14.13,(select count(*) from DetailProduction dp where dp.production.id=p.id and dp.absent=0) + (select count(*) from DetailProdGen dg where  p.id=dg.productionGen.id and dg.absent=0 ),sum(p.quantiteReel),l.machine.nom , p.articles.liblle FROM Production p  ,LigneMachine l where  p.ligneMachine.id=l.id and p.date between :dateDebut and :dateFin and p.statut =:statut and p.codeDepot=:depot group by p.date ,l.machine.nom");
		
		if(dateDebut!=null && dateFin!=null && !statut.equals("") && !depot.equals("") && article!=null)
		{
			query= session.createQuery("select  p.articles ,sum(d.coutTotal),sum(d.coutSupp25),sum(d.coutSupp50)  FROM DetailProduction d, Production p   where  p.id=d.production.id and  p.date between :dateDebut and :dateFin and p.statut=:statut and p.codeDepot=:depot and d.absent=0 and p.articles.id=:article group by p.articles ");

			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("statut", statut);
			query.setParameter("depot", depot);
			query.setParameter("article", article.getId());
		}else if(dateDebut!=null && dateFin!=null && !statut.equals("") && !depot.equals("") && article==null)
		{
			query= session.createQuery("select  p.articles ,sum(d.coutTotal),sum(d.coutSupp25),sum(d.coutSupp50)  FROM DetailProduction d, Production p   where  p.id=d.production.id and  p.date between :dateDebut and :dateFin and p.statut=:statut and p.codeDepot=:depot and d.absent=0   group by p.articles ");

			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("statut", statut);
			query.setParameter("depot", depot);
			
		}
	
		return query.list();

	}
	
	
	
	@Override
	public List<Object[]> listeCoutDetailProdGenParArticle(Date dateDebut,Date dateFin,String statut,String depot , Articles article) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
		//	query= session.createQuery("select p.date,(select sum(dp.delaiEmploye) from DetailProduction dp where dp.production.id=p.id and dp.absent=0) +(select  sum(dg.delaiEmploye) from DetailProdGen dg where p.id=dg.productionGen.id and dg.absent=0), 14.13,(select count(*) from DetailProduction dp where dp.production.id=p.id and dp.absent=0) + (select count(*) from DetailProdGen dg where  p.id=dg.productionGen.id and dg.absent=0 ),sum(p.quantiteReel),l.machine.nom , p.articles.liblle FROM Production p  ,LigneMachine l where  p.ligneMachine.id=l.id and p.date between :dateDebut and :dateFin and p.statut =:statut and p.codeDepot=:depot group by p.date ,l.machine.nom");
		
		if(dateDebut!=null && dateFin!=null && !statut.equals("") && !depot.equals("") && article!=null)
		{
			query= session.createQuery("select  p.articles ,sum(d.coutTotal),sum(d.coutSupp25),sum(d.coutSupp50)  FROM DetailProdGen d, Production p   where  p.id=d.productionGen.id and  p.date between :dateDebut and :dateFin and p.statut=:statut and p.codeDepot=:depot and d.absent=0 and p.articles.id=:article group by p.articles ");

			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("statut", statut);
			query.setParameter("depot", depot);
			query.setParameter("article", article.getId());
		}else if(dateDebut!=null && dateFin!=null && !statut.equals("") && !depot.equals("") && article==null)
		{
			query= session.createQuery("select  p.articles ,sum(d.coutTotal),sum(d.coutSupp25),sum(d.coutSupp50)  FROM DetailProdGen d, Production p   where  p.id=d.productionGen.id and  p.date between :dateDebut and :dateFin and p.statut=:statut and p.codeDepot=:depot and d.absent=0   group by p.articles ");

			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("statut", statut);
			query.setParameter("depot", depot);
			
		}
	
		return query.list();

	}
	
	
	@Override
	public List<Object[]> listeSumQuantiteReelParArticle(Date dateDebut,Date dateFin,String statut,String depot , Articles article) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
		//	query= session.createQuery("select p.date,(select sum(dp.delaiEmploye) from DetailProduction dp where dp.production.id=p.id and dp.absent=0) +(select  sum(dg.delaiEmploye) from DetailProdGen dg where p.id=dg.productionGen.id and dg.absent=0), 14.13,(select count(*) from DetailProduction dp where dp.production.id=p.id and dp.absent=0) + (select count(*) from DetailProdGen dg where  p.id=dg.productionGen.id and dg.absent=0 ),sum(p.quantiteReel),l.machine.nom , p.articles.liblle FROM Production p  ,LigneMachine l where  p.ligneMachine.id=l.id and p.date between :dateDebut and :dateFin and p.statut =:statut and p.codeDepot=:depot group by p.date ,l.machine.nom");
		
		if(dateDebut!=null && dateFin!=null && !statut.equals("") && !depot.equals("") && article!=null)
		{
			query= session.createQuery("select  p.articles,sum(p.quantiteReel)  FROM Production p   where p.date between :dateDebut and :dateFin and p.statut=:statut and p.codeDepot=:depot and p.articles.id=:article group by p.articles");

			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("statut", statut);
			query.setParameter("depot", depot);
			query.setParameter("article", article.getId());
		}else if(dateDebut!=null && dateFin!=null && !statut.equals("") && !depot.equals("") && article==null)
		{
			query= session.createQuery("select  p.articles,sum(p.quantiteReel)  FROM Production p   where p.date between :dateDebut and :dateFin and p.statut=:statut and p.codeDepot=:depot   group by p.articles");

			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("statut", statut);
			query.setParameter("depot", depot);
			
		}
	
		return query.list();

	}
	
	
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////    les couts par production  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	


	
	
	@Override
	public List<Object[]> listeCoutMPParProduction(Date dateDebut,Date dateFin,String statut,String depot , Articles article) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
		//	query= session.createQuery("select p.date,(select sum(dp.delaiEmploye) from DetailProduction dp where dp.production.id=p.id and dp.absent=0) +(select  sum(dg.delaiEmploye) from DetailProdGen dg where p.id=dg.productionGen.id and dg.absent=0), 14.13,(select count(*) from DetailProduction dp where dp.production.id=p.id and dp.absent=0) + (select count(*) from DetailProdGen dg where  p.id=dg.productionGen.id and dg.absent=0 ),sum(p.quantiteReel),l.machine.nom , p.articles.liblle FROM Production p  ,LigneMachine l where  p.ligneMachine.id=l.id and p.date between :dateDebut and :dateFin and p.statut =:statut and p.codeDepot=:depot group by p.date ,l.machine.nom");
		
		if(dateDebut!=null && dateFin!=null && !statut.equals("") && !depot.equals("") && article!=null)
		{
			query= session.createQuery("select  c.prodcutionCM ,((sum(c.quantConsomme * c.prixUnitaire) + sum(c.quantDechet * c.prixUnitaire) + sum(c.quantiteOffre * c.prixUnitaire) + sum(c.quantDechetFournisseur * c.prixUnitaire) + sum(c.quantiteManquante * c.prixUnitaire)) - sum(c.quantiteManquanteFrPlus * c.prixUnitaire))  FROM CoutMP c   where c.prodcutionCM.date between :dateDebut and :dateFin and c.prodcutionCM.statut=:statut and c.prodcutionCM.codeDepot=:depot and c.prodcutionCM.articles.id=:article group by c.prodcutionCM ");

			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("statut", statut);
			query.setParameter("depot", depot);
			query.setParameter("article", article.getId());
		}else if(dateDebut!=null && dateFin!=null && !statut.equals("") && !depot.equals("") && article==null)
		{
			query= session.createQuery("select  c.prodcutionCM ,((sum(c.quantConsomme * c.prixUnitaire) + sum(c.quantDechet * c.prixUnitaire) + sum(c.quantiteOffre * c.prixUnitaire) + sum(c.quantDechetFournisseur * c.prixUnitaire) + sum(c.quantiteManquante * c.prixUnitaire)) - sum(c.quantiteManquanteFrPlus * c.prixUnitaire))  FROM CoutMP c   where c.prodcutionCM.date between :dateDebut and :dateFin and c.prodcutionCM.statut=:statut and c.prodcutionCM.codeDepot=:depot group by c.prodcutionCM ");

			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("statut", statut);
			query.setParameter("depot", depot);
			
		}
	
		return query.list();

	}
	
	@Override
	public List<Object[]> listeCoutDetailProdResParProduction(Date dateDebut,Date dateFin,String statut,String depot , Articles article) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
		//	query= session.createQuery("select p.date,(select sum(dp.delaiEmploye) from DetailProduction dp where dp.production.id=p.id and dp.absent=0) +(select  sum(dg.delaiEmploye) from DetailProdGen dg where p.id=dg.productionGen.id and dg.absent=0), 14.13,(select count(*) from DetailProduction dp where dp.production.id=p.id and dp.absent=0) + (select count(*) from DetailProdGen dg where  p.id=dg.productionGen.id and dg.absent=0 ),sum(p.quantiteReel),l.machine.nom , p.articles.liblle FROM Production p  ,LigneMachine l where  p.ligneMachine.id=l.id and p.date between :dateDebut and :dateFin and p.statut =:statut and p.codeDepot=:depot group by p.date ,l.machine.nom");
		
		if(dateDebut!=null && dateFin!=null && !statut.equals("") && !depot.equals("") && article!=null)
		{
			query= session.createQuery("select  p.id ,sum(d.coutTotal),sum(d.coutSupp25),sum(d.coutSupp50)  FROM DetailProdRes d, Production p   where  p.date=d.dateProduction and  p.date between :dateDebut and :dateFin and p.statut=:statut and p.codeDepot=:depot and d.absent=0 and p.articles.id=:article group by p.id ");

			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("statut", statut);
			query.setParameter("depot", depot);
			query.setParameter("article", article.getId());
		}else if(dateDebut!=null && dateFin!=null && !statut.equals("") && !depot.equals("") && article==null)
		{
			query= session.createQuery("select  p.id ,sum(d.coutTotal),sum(d.coutSupp25),sum(d.coutSupp50)  FROM DetailProdRes d, Production p   where  p.date=d.dateProduction and  p.date between :dateDebut and :dateFin and p.statut=:statut and p.codeDepot=:depot and d.absent=0  group by p.id ");

			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("statut", statut);
			query.setParameter("depot", depot);
			
		}
	
		return query.list();

	}
	
	
	@Override
	public List<Object[]> listeCoutDetailProductionParProduction(Date dateDebut,Date dateFin,String statut,String depot , Articles article) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
		//	query= session.createQuery("select p.date,(select sum(dp.delaiEmploye) from DetailProduction dp where dp.production.id=p.id and dp.absent=0) +(select  sum(dg.delaiEmploye) from DetailProdGen dg where p.id=dg.productionGen.id and dg.absent=0), 14.13,(select count(*) from DetailProduction dp where dp.production.id=p.id and dp.absent=0) + (select count(*) from DetailProdGen dg where  p.id=dg.productionGen.id and dg.absent=0 ),sum(p.quantiteReel),l.machine.nom , p.articles.liblle FROM Production p  ,LigneMachine l where  p.ligneMachine.id=l.id and p.date between :dateDebut and :dateFin and p.statut =:statut and p.codeDepot=:depot group by p.date ,l.machine.nom");
		
		if(dateDebut!=null && dateFin!=null && !statut.equals("") && !depot.equals("") && article!=null)
		{
			query= session.createQuery("select  p.id ,sum(d.coutTotal),sum(d.coutSupp25),sum(d.coutSupp50)  FROM DetailProduction d, Production p   where  p.id=d.production.id and  p.date between :dateDebut and :dateFin and p.statut=:statut and p.codeDepot=:depot and d.absent=0 and p.articles.id=:article group by p.id ");

			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("statut", statut);
			query.setParameter("depot", depot);
			query.setParameter("article", article.getId());
		}else if(dateDebut!=null && dateFin!=null && !statut.equals("") && !depot.equals("") && article==null)
		{
			query= session.createQuery("select  p.id ,sum(d.coutTotal),sum(d.coutSupp25),sum(d.coutSupp50)  FROM DetailProduction d, Production p   where  p.id=d.production.id and  p.date between :dateDebut and :dateFin and p.statut=:statut and p.codeDepot=:depot and d.absent=0   group by p.id ");

			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("statut", statut);
			query.setParameter("depot", depot);
			
		}
	
		return query.list();

	}
	
	
	
	@Override
	public List<Object[]> listeCoutDetailProdGenParProduction(Date dateDebut,Date dateFin,String statut,String depot , Articles article) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
		//	query= session.createQuery("select p.date,(select sum(dp.delaiEmploye) from DetailProduction dp where dp.production.id=p.id and dp.absent=0) +(select  sum(dg.delaiEmploye) from DetailProdGen dg where p.id=dg.productionGen.id and dg.absent=0), 14.13,(select count(*) from DetailProduction dp where dp.production.id=p.id and dp.absent=0) + (select count(*) from DetailProdGen dg where  p.id=dg.productionGen.id and dg.absent=0 ),sum(p.quantiteReel),l.machine.nom , p.articles.liblle FROM Production p  ,LigneMachine l where  p.ligneMachine.id=l.id and p.date between :dateDebut and :dateFin and p.statut =:statut and p.codeDepot=:depot group by p.date ,l.machine.nom");
		
		if(dateDebut!=null && dateFin!=null && !statut.equals("") && !depot.equals("") && article!=null)
		{
			query= session.createQuery("select  p.id ,sum(d.coutTotal),sum(d.coutSupp25),sum(d.coutSupp50)  FROM DetailProdGen d, Production p   where  p.id=d.productionGen.id and  p.date between :dateDebut and :dateFin and p.statut=:statut and p.codeDepot=:depot and d.absent=0 and p.articles.id=:article group by p.id ");

			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("statut", statut);
			query.setParameter("depot", depot);
			query.setParameter("article", article.getId());
		}else if(dateDebut!=null && dateFin!=null && !statut.equals("") && !depot.equals("") && article==null)
		{
			query= session.createQuery("select  p.id ,sum(d.coutTotal),sum(d.coutSupp25),sum(d.coutSupp50)  FROM DetailProdGen d, Production p   where  p.id=d.productionGen.id and  p.date between :dateDebut and :dateFin and p.statut=:statut and p.codeDepot=:depot and d.absent=0   group by p.id ");

			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("statut", statut);
			query.setParameter("depot", depot);
			
		}
	
		return query.list();

	}
	
	
	@Override
	public List<Production> listeSumQuantiteReelParProduction(Date dateDebut,Date dateFin,String statut,String depot , Articles article) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
		//	query= session.createQuery("select p.date,(select sum(dp.delaiEmploye) from DetailProduction dp where dp.production.id=p.id and dp.absent=0) +(select  sum(dg.delaiEmploye) from DetailProdGen dg where p.id=dg.productionGen.id and dg.absent=0), 14.13,(select count(*) from DetailProduction dp where dp.production.id=p.id and dp.absent=0) + (select count(*) from DetailProdGen dg where  p.id=dg.productionGen.id and dg.absent=0 ),sum(p.quantiteReel),l.machine.nom , p.articles.liblle FROM Production p  ,LigneMachine l where  p.ligneMachine.id=l.id and p.date between :dateDebut and :dateFin and p.statut =:statut and p.codeDepot=:depot group by p.date ,l.machine.nom");
		
		if(dateDebut!=null && dateFin!=null && !statut.equals("") && !depot.equals("") && article!=null)
		{
			query= session.createQuery("select  p FROM Production p   where p.date between :dateDebut and :dateFin and p.statut=:statut and p.codeDepot=:depot and p.articles.id=:article  order by p.date");

			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("statut", statut);
			query.setParameter("depot", depot);
			query.setParameter("article", article.getId());
		}else if(dateDebut!=null && dateFin!=null && !statut.equals("") && !depot.equals("") && article==null)
		{
			query= session.createQuery("select  p  FROM Production p   where p.date between :dateDebut and :dateFin and p.statut=:statut and p.codeDepot=:depot  order by p.date");

			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("statut", statut);
			query.setParameter("depot", depot);
			
		}
	
		return query.list();

	}
	
	
	
	@Override
	public List<Object[]>  QuantiteConsommeOffre(MatierePremier mp , String offre, FournisseurMP fournisseur,String depot) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
		//	query= session.createQuery("select p.date,(select sum(dp.delaiEmploye) from DetailProduction dp where dp.production.id=p.id and dp.absent=0) +(select  sum(dg.delaiEmploye) from DetailProdGen dg where p.id=dg.productionGen.id and dg.absent=0), 14.13,(select count(*) from DetailProduction dp where dp.production.id=p.id and dp.absent=0) + (select count(*) from DetailProdGen dg where  p.id=dg.productionGen.id and dg.absent=0 ),sum(p.quantiteReel),l.machine.nom , p.articles.liblle FROM Production p  ,LigneMachine l where  p.ligneMachine.id=l.id and p.date between :dateDebut and :dateFin and p.statut =:statut and p.codeDepot=:depot group by p.date ,l.machine.nom");
		
		if(mp!=null && offre!=null && fournisseur!=null)
		{
			if(!offre.equals(""))
			{
				query= session.createQuery("select  c.matierePremier, sum(c.quantiteOffre)   FROM CoutMP c   where   c.prodcutionCM.statut=:statut and c.prodcutionCM.codeDepot=:depot and c.prodcutionCM.offre=:offre and c.matierePremier.id=:mp and c.fournisseurMP.id=:fournisseur ");

 
				query.setParameter("mp", mp.getId());
				query.setParameter("offre", offre);
				query.setParameter("statut", Constantes.ETAT_OF_TERMINER);
				query.setParameter("fournisseur", fournisseur.getId());
				query.setParameter("depot", depot);
			}
			
		}else if(mp!=null && offre!=null)
		{
			if(!offre.equals(""))
			{
				query= session.createQuery("select  c.matierePremier, sum(c.quantiteOffre)   FROM CoutMP c   where   c.prodcutionCM.statut=:statut and c.prodcutionCM.codeDepot=:depot and c.prodcutionCM.offre=:offre and c.matierePremier.id=:mp ");

				query.setParameter("mp", mp.getId());
				query.setParameter("offre", offre);
				query.setParameter("statut", Constantes.ETAT_OF_TERMINER);
				query.setParameter("depot", depot);
				 
			}
		
			
		}
	
		return query.list();

	}
		
	
	
	public void ViderSession()
	{
		if(session!=null)
		{
			session.clear();
		}
	}
	
	
	
	

	
	
	
	
	

}

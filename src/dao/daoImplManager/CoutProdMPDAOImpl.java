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
import dao.daoManager.CoutProductionMPDAO;
import dao.daoManager.MotifPerteMPDAO;
import dao.entity.ActionMP;
import dao.entity.Articles;
import dao.entity.CoutMP;
import dao.entity.CoutProdMP;
import dao.entity.Depot;
import dao.entity.DetailEstimation;
import dao.entity.FournisseurMP;
import dao.entity.MatierePremier;
import dao.entity.MotifPerteMP;
import dao.entity.Production;
import dao.entity.ProductionMP;

public class CoutProdMPDAOImpl implements CoutProductionMPDAO {
	Session session=HibernateUtil.openSession();

	public void add(CoutProdMP e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public CoutProdMP edit(CoutProdMP e) {
		
	session.beginTransaction();
	CoutProdMP p= (CoutProdMP)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		CoutProdMP p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<CoutProdMP> findAll() {
		return session.createQuery("select c from CoutProdMP c").list();
		}

	public CoutProdMP findById(int id) {
		return (CoutProdMP)session.get(CoutProdMP.class, id);
		}
	
 
 
	 
	public List<CoutProdMP> listeCoutProdMPParProduction(ProductionMP productionMP) {
		
		Query query=null;
		
		
		//	query= session.createQuery("select p.date,(select sum(dp.delaiEmploye) from DetailProduction dp where dp.production.id=p.id and dp.absent=0) +(select  sum(dg.delaiEmploye) from DetailProdGen dg where p.id=dg.productionGen.id and dg.absent=0), 14.13,(select count(*) from DetailProduction dp where dp.production.id=p.id and dp.absent=0) + (select count(*) from DetailProdGen dg where  p.id=dg.productionGen.id and dg.absent=0 ),sum(p.quantiteReel),l.machine.nom , p.articles.liblle FROM Production p  ,LigneMachine l where  p.ligneMachine.id=l.id and p.date between :dateDebut and :dateFin and p.statut =:statut and p.codeDepot=:depot group by p.date ,l.machine.nom");
		
		 
			query= session.createQuery("select  p FROM CoutProdMP p   where p.prodcutionCM.id=:productionMP ");

			query.setParameter("productionMP", productionMP.getId());
			 
		return  query.list();
		
		
	}
	
	
 	
	
	
	public void ViderSession()
	{
		if(session!=null)
		{
			session.clear();
		}
	}
	
	
	
	

	
	
	
	
	

}

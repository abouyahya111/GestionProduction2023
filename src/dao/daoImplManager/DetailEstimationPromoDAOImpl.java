package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;

import dao.daoManager.DetailEstimationPromoDAO;

import dao.entity.DetailEstimation;
import dao.entity.DetailEstimationPromo;

public class DetailEstimationPromoDAOImpl implements DetailEstimationPromoDAO {
	Session session=HibernateUtil.openSession();

	public void add(DetailEstimationPromo e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public DetailEstimationPromo edit(DetailEstimationPromo e) {
		
	session.beginTransaction();
	DetailEstimationPromo p= (DetailEstimationPromo)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailEstimationPromo p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<DetailEstimationPromo> findAll() {
		return session.createQuery("select c from DetailEstimationPromo c").list();
		}

	public DetailEstimationPromo findById(int id) {
		return (DetailEstimationPromo)session.get(DetailEstimationPromo.class, id);
		}

	public List<DetailEstimationPromo>  findByIdPromo(int id) {
		Query query= session.createQuery("select c from DetailEstimationPromo c where id_promotion=:idpromotion");
		query.setParameter("idpromotion", id);
		
		return query.list();
		
		
		
		
		}
	
	
	public List<DetailEstimationPromo>  findByReq(String req) {
		Query query= session.createQuery("select c from DetailEstimationPromo c"+req);
	 
		
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

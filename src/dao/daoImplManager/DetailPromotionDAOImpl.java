package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;

import dao.daoManager.DetailEstimationPromoDAO;
import dao.daoManager.DetailPromotionDAO;
import dao.entity.DetailEstimation;
import dao.entity.DetailEstimationPromo;
import dao.entity.DetailPromotion;

public class DetailPromotionDAOImpl implements DetailPromotionDAO {
	Session session=HibernateUtil.openSession();

	public void add(DetailPromotion e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public DetailPromotion edit(DetailPromotion e) {
		
	session.beginTransaction();
	DetailPromotion p= (DetailPromotion)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailPromotion p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<DetailPromotion> findAll() {
		return session.createQuery("select c from DetailPromotion c").list();
		}

	public DetailPromotion findById(int id) {
		return (DetailPromotion)session.get(DetailPromotion.class, id);
		}

	public List<DetailPromotion>  findByIdPromo(int id) {
		Query query= session.createQuery("select c from DetailPromotion c where id_promotion=:idpromotion");
		query.setParameter("idpromotion", id);
		
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

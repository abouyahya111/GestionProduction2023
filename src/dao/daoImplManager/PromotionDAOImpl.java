package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.PromotionDAO;
import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.DetailEstimationPromo;
import dao.entity.Promotion;

public class PromotionDAOImpl implements PromotionDAO {
	Session session=HibernateUtil.openSession();

	public void add(Promotion e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public Promotion edit(Promotion e) {
		
	session.beginTransaction();
	Promotion p= (Promotion)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		Promotion p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<Promotion> findAll() {
		return session.createQuery("select c from Promotion c").list();
		}

	public Promotion findById(int id) {
		return (Promotion)session.get(Promotion.class, id);
		}

	
	
	public int maxIdPromotion() {
		// TODO Auto-generated method stub
		int id =0;
		Query query= session.createQuery("select max(id) from Promotion");
		
		if(query.uniqueResult()==null)
			id=1;
		else 
			id= (int)query.uniqueResult()+1;
		
		return id;
	}
	
	@Override
	public Promotion findByCode(String code) {
		// TODO Auto-generated method stub
		Promotion articles= new Promotion();
		Query query= session.createQuery("select c from Promotion c where code=:code");
		query.setParameter("code", code);
		
		articles= (Promotion) query.uniqueResult();
		
		return articles;
	}
	
	@Override
	public List<Promotion> findByArticle(int code) {
		Query query= session.createQuery("select c from Promotion c where ID_ARTICLE=:code");
		query.setParameter("code", code);
		return query.list();
		
		
		}
	
	@Override
	public List<Promotion> findByArticleActif(int code) {
		Query query= session.createQuery("select c from Promotion c where ID_ARTICLE=:code and ACTIF=true");
		query.setParameter("code", code);
		return query.list();
		
		
		}
	

	@Override
	public List<Promotion> findByReq(String req) {
		Query query= session.createQuery("select c from Promotion c  "+req);
		 
		return query.list();
		
		
		}
	
	


}

package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.DetailEstimationMPDAO;
import dao.daoManager.DetailEstimationPromoDAO;
import dao.entity.DetailEstimation;
import dao.entity.DetailEstimationMP;
import dao.entity.DetailEstimationPromo;

public class DetailEstimationMPDAOImpl implements DetailEstimationMPDAO {
	Session session=HibernateUtil.openSession();

	public void add(DetailEstimationMP e) {
		
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		
		//return p;
	}

	public DetailEstimationMP edit(DetailEstimationMP e) {
		
	session.beginTransaction();
	DetailEstimationMP p= (DetailEstimationMP)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailEstimationMP p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
	
		
	}

	public List<DetailEstimationMP> findAll() {
		
		Query query= session.createQuery("select c from DetailEstimationMP c");
		List<DetailEstimationMP> list=query.list();
		
		return list;
		}

	public DetailEstimationMP findById(int id) {
		
		DetailEstimationMP detailEstimationMP= (DetailEstimationMP)session.get(DetailEstimationMP.class, id);
		
		return detailEstimationMP;
		}

	public List<DetailEstimationMP>  findByIdMP(int id) {
		
		Query query= session.createQuery("select c from DetailEstimationMP c where id_mat_pre=:idmatiere");
		query.setParameter("idmatiere", id);
		List<DetailEstimationMP> list=query.list();
		
		return list;
		
		
		
		
		}
	
	@Override
	public List<DetailEstimationMP> findDetilestimationMPByArticle(int idarticle) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select d from DetailEstimationMP d where articlesMP.id=:idarticle");
		query.setParameter("idarticle", idarticle);
		List<DetailEstimationMP> list=query.list();
		return list;
		
	}
	
	@Override
	public List<DetailEstimationMP> findDetilestimationMPActifByArticle(int idarticle) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select d from DetailEstimationMP d where articlesMP.id=:idarticle and actif=true ");
		query.setParameter("idarticle", idarticle);
		List<DetailEstimationMP> list=query.list();
		return list;
		
	}
	
	
	public void ViderSession()
	{
		if(session!=null)
		{
			session.clear();
		}
	}


}

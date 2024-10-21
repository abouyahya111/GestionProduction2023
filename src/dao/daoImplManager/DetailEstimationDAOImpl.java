package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.DetailEstimationDAO;
import dao.daoManager.DetailEstimationMPDAO;
import dao.daoManager.DetailEstimationPromoDAO;
import dao.entity.DetailEstimation;
import dao.entity.DetailEstimationMP;
import dao.entity.DetailEstimationPromo;
import dao.entity.Production;

public class DetailEstimationDAOImpl implements DetailEstimationDAO {
	Session session=HibernateUtil.openSession();

	public void add(DetailEstimation e) {
	
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		
		//return p;
	}

	public DetailEstimation edit(DetailEstimation e) {
		
		session.beginTransaction();
	DetailEstimation p= (DetailEstimation)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailEstimation p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<DetailEstimation> findAll() {
		
		Query query= session.createQuery("select c from DetailEstimation c");
		List<DetailEstimation> list=query.list();
	
		return list;
				
		}

	public DetailEstimation findById(int id) {
		
		DetailEstimation detailEstimation= (DetailEstimation)session.get(DetailEstimation.class, id);
		
		return detailEstimation;
		}

	public List<DetailEstimation>  findByIdMP(int id) {
		
		Query query= session.createQuery("select c from DetailEstimation c where id_mat_pre=:idmatiere");
		query.setParameter("idmatiere", id);
		List<DetailEstimation> list=query.list();
		
		return list;
		
		
		
		
		}
	
	@Override
	public List<DetailEstimation> findDetilestimationByCategorie(int idarticle) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select d from DetailEstimation d where articles.id=:idarticle and matierePremier.categorieMp.subCategorieMp.code=:code");
		query.setParameter("idarticle", idarticle);
		query.setParameter("code", "TH001");
		List<DetailEstimation> list=query.list();
		return list;
		
	}
	
	
	@Override
	public List<DetailEstimation> findDetilestimationByArticle(int idarticle) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select d from DetailEstimation d where articles.id=:idarticle");
		query.setParameter("idarticle", idarticle);
		List<DetailEstimation> list=query.list();
		return list;
		
	}
	
	
	@Override
	public List<DetailEstimation> findDetilestimationActifByArticle(int idarticle) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select d from DetailEstimation d where articles.id=:idarticle and actif=true  group by id_mat_pre order by priorite ");
		
		query.setParameter("idarticle", idarticle);
		List<DetailEstimation> list=query.list();
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

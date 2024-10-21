package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.DetailChargeFixeDAO;
import dao.daoManager.DetailCoutProductionDAO;
import dao.entity.Articles;
import dao.entity.Charges;
import dao.entity.DetailChargeFixe;
import dao.entity.DetailCoutProduction;
import dao.entity.DetailEstimation;

public class DetailCoutProductionDAOImpl implements DetailCoutProductionDAO {
	Session session=HibernateUtil.openSession();

	public void add(DetailCoutProduction e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public DetailCoutProduction edit(DetailCoutProduction e) {
		
	session.beginTransaction();
	DetailCoutProduction p= (DetailCoutProduction)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailCoutProduction p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<DetailCoutProduction> findAll() {
		return session.createQuery("select c from DetailCoutProduction c").list();
		}

	public DetailCoutProduction findById(int id) {
		return (DetailCoutProduction)session.get(DetailCoutProduction.class, id);
		}
	
	
	public DetailCoutProduction findByCodeCharge(String code,Charges charges) {
		Query query= session.createQuery("select v from DetailCoutProduction v where v.code=:code and charges=:charges");
		query.setParameter("code", code.toUpperCase());
		query.setParameter("charges", charges);
		return (DetailCoutProduction)query.uniqueResult();
		}

	


}

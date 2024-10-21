package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ActionMPDAO;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.MotifPerteMPDAO;
import dao.daoManager.OffreProductionDAO;
import dao.entity.ActionMP;
import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.MotifPerteMP;
import dao.entity.OffreProduction;
import dao.entity.Production;

public class OffreProductionDAOImpl implements OffreProductionDAO {
	Session session=HibernateUtil.openSession();

	public void add(OffreProduction e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public OffreProduction edit(OffreProduction e) {
		
	session.beginTransaction();
	OffreProduction p= (OffreProduction)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		OffreProduction p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<OffreProduction> findAll() {
		return session.createQuery("select c from OffreProduction c").list();
		}

	public OffreProduction findById(int id) {
		return (OffreProduction)session.get(OffreProduction.class, id);
		}

	
	public List<OffreProduction> findByProduction(Production production) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from OffreProduction c where prodcutionCM.id=:production");
		query.setParameter("production", production.getId());
		
		
		return query.list();
	}
	

}

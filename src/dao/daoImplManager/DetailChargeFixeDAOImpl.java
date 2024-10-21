package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.DetailChargeFixeDAO;
import dao.entity.Articles;
import dao.entity.DetailChargeFixe;
import dao.entity.DetailEstimation;

public class DetailChargeFixeDAOImpl implements DetailChargeFixeDAO {
	Session session=HibernateUtil.openSession();

	public void add(DetailChargeFixe e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public DetailChargeFixe edit(DetailChargeFixe e) {
		
	session.beginTransaction();
	DetailChargeFixe p= (DetailChargeFixe)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailChargeFixe p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<DetailChargeFixe> findAll() {
		return session.createQuery("select c from DetailChargeFixe c").list();
		}

	public DetailChargeFixe findById(int id) {
		return (DetailChargeFixe)session.get(DetailChargeFixe.class, id);
		}

	


}

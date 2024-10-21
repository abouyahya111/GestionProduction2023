package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.CompteurProductionDAO;
import dao.entity.CompteurProduction;

public class CompteurProductionDAOImpl implements CompteurProductionDAO {
	Session session=HibernateUtil.openSession();

	public void add(CompteurProduction e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public CompteurProduction edit(CompteurProduction e) {
		
	session.beginTransaction();
	CompteurProduction p= (CompteurProduction)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		CompteurProduction p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	@SuppressWarnings("unchecked")
	public List<CompteurProduction> findAll() {
		return session.createQuery("select c from CompteurProduction c").list();
		}

	public CompteurProduction findById(int id) {
		return (CompteurProduction)session.get(CompteurProduction.class, id);
		}
	

	@Override
	public CompteurProduction findByDateProdPeriode(Date dateProd,String periode) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select d from CompteurProduction d where dateProd=:dateProd and periode=:periode");
				query.setParameter("dateProd", dateProd);
				query.setParameter("periode", periode);
				
				return (CompteurProduction) query.uniqueResult();
}

	

}

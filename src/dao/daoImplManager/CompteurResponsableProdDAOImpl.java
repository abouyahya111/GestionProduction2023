package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.CompteurResponsableProdDAO;
import dao.entity.CompteurProduction;
import dao.entity.CompteurResponsableProd;

public class CompteurResponsableProdDAOImpl implements CompteurResponsableProdDAO {
	Session session=HibernateUtil.openSession();

	public void add(CompteurResponsableProd e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public CompteurResponsableProd edit(CompteurResponsableProd e) {
		
	session.beginTransaction();
	CompteurResponsableProd p= (CompteurResponsableProd)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		CompteurResponsableProd p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	@SuppressWarnings("unchecked")
	public List<CompteurResponsableProd> findAll() {
		return session.createQuery("select c from CompteurResponsableProd c").list();
		}

	public CompteurResponsableProd findById(int id) {
		return (CompteurResponsableProd)session.get(CompteurResponsableProd.class, id);
		}
	

	@Override
	public CompteurResponsableProd findByDateProdPeriode(Date dateProd,String periode,int idEmploye) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select d from CompteurResponsableProd d where dateProd=:dateProd and periode=:periode and employe.id=:idEmploye ");
				query.setParameter("dateProd", dateProd);
				query.setParameter("periode", periode);
				query.setParameter("idEmploye", idEmploye);
				
				return (CompteurResponsableProd) query.uniqueResult();
}

	

}

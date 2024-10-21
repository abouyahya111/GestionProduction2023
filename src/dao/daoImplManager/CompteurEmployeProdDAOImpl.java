package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.CompteurEmployeProdDAO;
import dao.entity.CompteurEmployeProd;

public class CompteurEmployeProdDAOImpl implements CompteurEmployeProdDAO {
	Session session=HibernateUtil.openSession();

	public void add(CompteurEmployeProd e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public CompteurEmployeProd edit(CompteurEmployeProd e) {
		
	session.beginTransaction();
	CompteurEmployeProd p= (CompteurEmployeProd)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		CompteurEmployeProd p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	@SuppressWarnings("unchecked")
	public List<CompteurEmployeProd> findAll() {
		return session.createQuery("select c from CompteurEmployeProd c").list();
		}

	public CompteurEmployeProd findById(int id) {
		return (CompteurEmployeProd)session.get(CompteurEmployeProd.class, id);
		}
	

	@Override
	public CompteurEmployeProd findByDateProdPeriode(Date dateProd,String periode,int idEmploye) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select d from CompteurEmployeProd d where dateProd=:dateProd and periode=:periode and employe.id=:idEmploye ");
				query.setParameter("dateProd", dateProd);
				query.setParameter("periode", periode);
				query.setParameter("idEmploye", idEmploye);
				
				return (CompteurEmployeProd) query.uniqueResult();
}

	

}

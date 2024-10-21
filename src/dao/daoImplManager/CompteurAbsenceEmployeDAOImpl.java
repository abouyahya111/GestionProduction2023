package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.CompteurAbsenceEmployeDAO;
import dao.entity.CompteurAbsenceEmploye;

public class CompteurAbsenceEmployeDAOImpl implements CompteurAbsenceEmployeDAO {
	Session session=HibernateUtil.openSession();

	public void add(CompteurAbsenceEmploye e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public CompteurAbsenceEmploye edit(CompteurAbsenceEmploye e) {
		
	session.beginTransaction();
	CompteurAbsenceEmploye p= (CompteurAbsenceEmploye)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		CompteurAbsenceEmploye p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	@SuppressWarnings("unchecked")
	public List<CompteurAbsenceEmploye> findAll() {
		return session.createQuery("select c from CompteurAbsenceEmploye c").list();
		}

	public CompteurAbsenceEmploye findById(int id) {
		return (CompteurAbsenceEmploye)session.get(CompteurAbsenceEmploye.class, id);
		}
	

	@Override
	public CompteurAbsenceEmploye findByDateAbsencePeriode(String code,int idEmploye) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select d from CompteurAbsenceEmploye d where code=:code and employe.id=:idEmploye ");
				query.setParameter("code", code);
				query.setParameter("idEmploye", idEmploye);
				
				return (CompteurAbsenceEmploye) query.uniqueResult();
}

	

}

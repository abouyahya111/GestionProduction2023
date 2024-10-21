package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.EmployeBloqueDAO;
import dao.entity.EmployeBloque;

public class EmployeBloqueDAOImpl implements EmployeBloqueDAO {
	Session session=HibernateUtil.openSession();

	public void add(EmployeBloque e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public EmployeBloque edit(EmployeBloque e) {
		
	session.beginTransaction();
	EmployeBloque p= (EmployeBloque)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		EmployeBloque p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<EmployeBloque> findAll() {
		return session.createQuery("select c from EmployeBloque c").list();
		}

	public EmployeBloque findById(int id) {
		return (EmployeBloque)session.get(EmployeBloque.class, id);
		}

	@Override
	public EmployeBloque findByCode(String code) {
		// TODO Auto-generated method stub
		EmployeBloque employeBloque= new EmployeBloque();
		Query query= session.createQuery("select c from EmployeBloque c where matricule=:code");
		query.setParameter("code", code);
		
		employeBloque= (EmployeBloque) query.uniqueResult();
		
		return employeBloque;
	}


}

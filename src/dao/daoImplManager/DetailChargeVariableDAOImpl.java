package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;

import dao.daoManager.DetailChargeVariableDAO;


import dao.entity.DetailChargeVariable;


public class DetailChargeVariableDAOImpl implements DetailChargeVariableDAO {
	Session session=HibernateUtil.openSession();

	public void add(DetailChargeVariable e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public DetailChargeVariable edit(DetailChargeVariable e) {
		
	session.beginTransaction();
	DetailChargeVariable p= (DetailChargeVariable)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailChargeVariable p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<DetailChargeVariable> findAll() {
		return session.createQuery("select c from DetailChargeVariable c").list();
		}

	public DetailChargeVariable findById(int id) {
		return (DetailChargeVariable)session.get(DetailChargeVariable.class, id);
		}

	


}

package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.TypeResEmployeDAO;
import dao.entity.Articles;
import dao.entity.TypeResEmploye;

public class TypeResEmployeDAOImpl implements TypeResEmployeDAO {
	Session session=HibernateUtil.openSession();

	public void add(TypeResEmploye e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public TypeResEmploye edit(TypeResEmploye e) {
		
	session.beginTransaction();
	TypeResEmploye p= (TypeResEmploye)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		TypeResEmploye p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<TypeResEmploye> findAll() {
		return session.createQuery("select c from TypeResEmploye c").list();
		}

	public TypeResEmploye findById(int id) {
		return (TypeResEmploye)session.get(TypeResEmploye.class, id);
		}

	@Override
	public TypeResEmploye findByCode(String code) {
		// TODO Auto-generated method stub
		TypeResEmploye typeResEmploye= new TypeResEmploye();
		Query query= session.createQuery("select c from TypeResEmploye c where code=:code");
		query.setParameter("code", code);
		
		typeResEmploye= (TypeResEmploye) query.uniqueResult();
		
		return typeResEmploye;
	}


}

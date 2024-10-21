package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.TypeEquipeDAO;
import dao.entity.TypeEquipe;

public class TypeEquipeDAOImpl implements TypeEquipeDAO {
	Session session=HibernateUtil.openSession();

	public void add(TypeEquipe e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public TypeEquipe edit(TypeEquipe e) {
		
	session.beginTransaction();
	TypeEquipe p= (TypeEquipe)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		TypeEquipe p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<TypeEquipe> findAll() {
		return session.createQuery("select c from TypeEquipe c").list();
		}

	public TypeEquipe findById(int id) {
		return (TypeEquipe)session.get(TypeEquipe.class, id);
		}

	@Override
	public TypeEquipe findByCode(String code) {
		// TODO Auto-generated method stub
		TypeEquipe typeEquipe= new TypeEquipe();
		Query query= session.createQuery("select c from TypeEquipe c where code=:code");
		query.setParameter("code", code);
		
		typeEquipe= (TypeEquipe) query.uniqueResult();
		
		return typeEquipe;
	}


}

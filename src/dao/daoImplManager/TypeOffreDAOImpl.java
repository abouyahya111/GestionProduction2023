package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.TypeOffreDAO;
import dao.daoManager.TypeSortieDAO;
import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.TypeOffre;
import dao.entity.TypeSortie;

public class TypeOffreDAOImpl implements TypeOffreDAO {
	Session session=HibernateUtil.openSession();

	public void add(TypeOffre e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public TypeOffre edit(TypeOffre e) {
		
	session.beginTransaction();
	TypeOffre p= (TypeOffre)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		TypeOffre p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<TypeOffre> findAll() {
		return session.createQuery("select c from TypeOffre c").list();
		}

	public TypeOffre findById(int id) {
		return (TypeOffre)session.get(TypeOffre.class, id);
		}

	public TypeOffre findBytypeOffre(String typeOffre) {
	
		Query query= session.createQuery("select c from TypeOffre c where typeOffre=:typeOffre");
		query.setParameter("typeOffre", typeOffre);
		
		return (TypeOffre) query.uniqueResult();
		
		
	}

}

package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.TypeSortieDAO;
import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.TypeSortie;

public class TypeSortieDAOImpl implements TypeSortieDAO {
	Session session=HibernateUtil.openSession();

	public void add(TypeSortie e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public TypeSortie edit(TypeSortie e) {
		
	session.beginTransaction();
	TypeSortie p= (TypeSortie)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		TypeSortie p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<TypeSortie> findAll() {
		return session.createQuery("select c from TypeSortie c").list();
		}

	public TypeSortie findById(int id) {
		return (TypeSortie)session.get(TypeSortie.class, id);
		}

	public TypeSortie findByLibelle(String libelle) {
	
		Query query= session.createQuery("select c from TypeSortie c where liblle=:libelle");
		query.setParameter("libelle", libelle);
		
		return (TypeSortie) query.uniqueResult();
		
		
	}

}

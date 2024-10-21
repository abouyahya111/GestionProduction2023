package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.MotifPerteMPDAO;
import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.MotifPerteMP;

public class MotifPerteMPDAOImpl implements MotifPerteMPDAO {
	Session session=HibernateUtil.openSession();

	public void add(MotifPerteMP e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public MotifPerteMP edit(MotifPerteMP e) {
		
	session.beginTransaction();
	MotifPerteMP p= (MotifPerteMP)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		MotifPerteMP p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<MotifPerteMP> findAll() {
		return session.createQuery("select c from MotifPerteMP c").list();
		}

	public MotifPerteMP findById(int id) {
		return (MotifPerteMP)session.get(Articles.class, id);
		}

	
	public MotifPerteMP findByCode(String code) {
		// TODO Auto-generated method stub
		MotifPerteMP articles= new MotifPerteMP();
		Query query= session.createQuery("select c from MotifPerteMP c where codeMotif=:code");
		query.setParameter("code", code);
		
		articles= (MotifPerteMP) query.uniqueResult();
		
		return articles;
	}
	

}

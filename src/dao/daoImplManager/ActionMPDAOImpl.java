package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ActionMPDAO;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.MotifPerteMPDAO;
import dao.entity.ActionMP;
import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.MotifPerteMP;

public class ActionMPDAOImpl implements ActionMPDAO {
	Session session=HibernateUtil.openSession();

	public void add(ActionMP e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public ActionMP edit(ActionMP e) {
		
	session.beginTransaction();
	ActionMP p= (ActionMP)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		ActionMP p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<ActionMP> findAll() {
		return session.createQuery("select c from ActionMP c").list();
		}

	public ActionMP findById(int id) {
		return (ActionMP)session.get(Articles.class, id);
		}

	
	public ActionMP findByCode(String code) {
		// TODO Auto-generated method stub
		ActionMP articles= new ActionMP();
		Query query= session.createQuery("select c from ActionMP c where codeAction=:code");
		query.setParameter("code", code);
		
		articles= (ActionMP) query.uniqueResult();
		
		return articles;
	}
	

}

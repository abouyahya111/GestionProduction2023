package dao.daoImplManager;

import java.util.List;

import main.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.GrammageBoxDAO;
import dao.daoManager.GrammageCartonDAO;
import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.GrammageBox;
import dao.entity.GrammageCarton;


public class GrammageCartonDAOImpl implements GrammageCartonDAO {
	Session session=HibernateUtil.openSession();
	//Session session=ProdLauncher.session;

	public void add(GrammageCarton e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public GrammageCarton edit(GrammageCarton e) {
		
	session.beginTransaction();
	GrammageCarton p= (GrammageCarton)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		GrammageCarton p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<GrammageCarton> findAll() {
		return session.createQuery("select c from GrammageCarton c").list();
		}

	public GrammageCarton findById(int id) {
		return (GrammageCarton)session.get(GrammageCarton.class, id);
		}

	@Override
	public GrammageCarton findByCode(String code) {
		// TODO Auto-generated method stub
		GrammageCarton articles= new GrammageCarton();
		Query query= session.createQuery("select c from GrammageCarton c where codeGrammage=:code");
		query.setParameter("code", code);
		
		articles= (GrammageCarton) query.uniqueResult();
		
		return articles;
	}
	
	


}

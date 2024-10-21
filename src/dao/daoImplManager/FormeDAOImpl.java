package dao.daoImplManager;

import java.util.List;

import main.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.FormeDAO;
import dao.daoManager.GrammageBoxDAO;
import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.GrammageBox;
import dao.entity.forme;


public class FormeDAOImpl implements FormeDAO {
	Session session=HibernateUtil.openSession();
		

	public void add(forme e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public forme edit(forme e) {
		
	session.beginTransaction();
	forme p= (forme)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		forme p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<forme> findAll() {
		return session.createQuery("select c from forme c").list();
		}

	public forme findById(int id) {
		return (forme)session.get(forme.class, id);
		}

	@Override
	public forme findByLibelle(String libelle) {
		// TODO Auto-generated method stub
		forme forme= new forme();
		Query query= session.createQuery("select c from forme c where libelle=:libelle");
		query.setParameter("libelle", libelle);
		
		forme= (forme) query.uniqueResult();
		
		return forme;
	}
	
	


}

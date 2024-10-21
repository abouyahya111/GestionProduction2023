package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.ManqueDechetFournisseurCartonDAO;
import dao.daoManager.ManqueDechetFournisseurDAO;
import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.ManqueDechetFournisseur;
import dao.entity.ManqueDechetFournisseurCarton;
import dao.entity.MatierePremier;

public class ManqueDechetFournisseurCartonDAOImpl implements ManqueDechetFournisseurCartonDAO {
	Session session=HibernateUtil.openSession();

	public void add(ManqueDechetFournisseurCarton e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public ManqueDechetFournisseurCarton edit(ManqueDechetFournisseurCarton e) {
		
	session.beginTransaction();
	ManqueDechetFournisseurCarton p= (ManqueDechetFournisseurCarton)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		ManqueDechetFournisseurCarton p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<ManqueDechetFournisseurCarton> findAll() {
		return session.createQuery("select c from ManqueDechetFournisseurCarton c").list();
		}

	public ManqueDechetFournisseurCarton findById(int id) {
		return (ManqueDechetFournisseurCarton)session.get(ManqueDechetFournisseurCarton.class, id);
		}

	@Override
	public ManqueDechetFournisseurCarton findByCode(String code) {
		// TODO Auto-generated method stub
		ManqueDechetFournisseurCarton manqueDechetFournisseurCarton= new ManqueDechetFournisseurCarton();
		Query query= session.createQuery("select c from ManqueDechetFournisseurCarton c where numOF=:code");
		query.setParameter("code", code);
		
		manqueDechetFournisseurCarton= (ManqueDechetFournisseurCarton) query.uniqueResult();
		
		return manqueDechetFournisseurCarton;
	}
	
	@Override
	public List<ManqueDechetFournisseurCarton> listeManqueDechetFournisseurByEtat(String etat) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from ManqueDechetFournisseurCarton c where etat=:etat");
		query.setParameter("etat", etat);
		
		return query.list();
		
	}


}

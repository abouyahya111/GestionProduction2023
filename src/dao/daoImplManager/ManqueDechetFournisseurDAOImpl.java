package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.ManqueDechetFournisseurDAO;
import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.ManqueDechetFournisseur;
import dao.entity.MatierePremier;
import dao.entity.TransferStockPF;

public class ManqueDechetFournisseurDAOImpl implements ManqueDechetFournisseurDAO {
	Session session=HibernateUtil.openSession();

	public void add(ManqueDechetFournisseur e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public ManqueDechetFournisseur edit(ManqueDechetFournisseur e) {
		
	session.beginTransaction();
	ManqueDechetFournisseur p= (ManqueDechetFournisseur)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		ManqueDechetFournisseur p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<ManqueDechetFournisseur> findAll() {
		return session.createQuery("select c from ManqueDechetFournisseur c").list();
		}

	public ManqueDechetFournisseur findById(int id) {
		return (ManqueDechetFournisseur)session.get(ManqueDechetFournisseur.class, id);
		}

	@Override
	public ManqueDechetFournisseur findByCode(String code,String type) {
		// TODO Auto-generated method stub
		ManqueDechetFournisseur articles= new ManqueDechetFournisseur();
		Query query= session.createQuery("select c from ManqueDechetFournisseur c where numOF=:code and type=:type");
		query.setParameter("code", code);
		query.setParameter("type", type);
		
		articles= (ManqueDechetFournisseur) query.uniqueResult();
		
		return articles;
	}
	
	@Override
	public List<ManqueDechetFournisseur> listeManqueDechetFournisseurByEtat(String etat) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from ManqueDechetFournisseur c where etat=:etat");
		query.setParameter("etat", etat);
		
		return query.list();
		
	}
	
	
	@Override
	public List<ManqueDechetFournisseur> listeManqueDechetFournisseurByCode(String code) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from ManqueDechetFournisseur c where numOF=:code");
		query.setParameter("code", code);
		
		return query.list();
		
	}
	

	public void deleteObject(ManqueDechetFournisseur p) {
		
		session.beginTransaction();
		session.delete(p);
		session.getTransaction().commit();
		
	}
	
	
	

}

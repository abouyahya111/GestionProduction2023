package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.FournisseurAdhesiveDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.FournisseurAdhesive;
import dao.entity.FournisseurMP;

public class FournisseurAdhesiveDAOImpl implements FournisseurAdhesiveDAO {
	Session session=HibernateUtil.openSession();

	public void add(FournisseurAdhesive e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public FournisseurAdhesive edit(FournisseurAdhesive e) {
		
	session.beginTransaction();
	FournisseurAdhesive p= (FournisseurAdhesive)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		FournisseurAdhesive p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<FournisseurAdhesive> findAll() {
		return session.createQuery("select c from FournisseurAdhesive c").list();
		}

	public FournisseurAdhesive findById(int id) {
		return (FournisseurAdhesive)session.get(FournisseurAdhesive.class, id);
		}

	@Override
	public FournisseurAdhesive findByCode(String code) {
		// TODO Auto-generated method stub
		FournisseurAdhesive fournisseurAdhesive= new FournisseurAdhesive();
		Query query= session.createQuery("select c from FournisseurAdhesive c where codeFournisseur=:code");
		query.setParameter("code", code);
		
		fournisseurAdhesive= (FournisseurAdhesive) query.uniqueResult();
		
		return fournisseurAdhesive;
	}
	



}

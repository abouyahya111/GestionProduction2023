package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.PrixClientMPDAO;
import dao.entity.Magasin;
import dao.entity.PrixClientMP;
import dao.entity.StockMP;

public class PrixClientMPDAOImpl implements PrixClientMPDAO {
	Session session=HibernateUtil.openSession();

	public void add(PrixClientMP e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public PrixClientMP edit(PrixClientMP e) {
		
	session.beginTransaction();
	PrixClientMP p= (PrixClientMP)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		PrixClientMP p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	@SuppressWarnings("unchecked")
	public List<PrixClientMP> findAll() {
		return session.createQuery("select c from StockMP c").list();
		}

	public PrixClientMP findById(int id) {
		return (PrixClientMP)session.get(PrixClientMP.class, id);
		}

	public PrixClientMP findPrixClientByMatierePremier(String codeMP) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from PrixClientMP c where matierePremier.code=:codeMP");
		query.setParameter("codeMP", codeMP);
		
		return (PrixClientMP) query.uniqueResult();
		
	}

	@Override
	public PrixClientMP findPrixByClientMP(String codeMP,String codeClient,String codefournisseur) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from PrixClientMP c where matierePremier.code=:codeMP and client.code=:codeClient and fournisseur.code=:codefournisseur");
		query.setParameter("codeClient", codeClient);
		query.setParameter("codeMP", codeMP);
		query.setParameter("codefournisseur", codefournisseur);
		
		return  (PrixClientMP) query.uniqueResult();
		
	}

	@Override
	public List<PrixClientMP> findAllByClient(String codeClient) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from PrixClientMP c where client.code=:codeClient");
		query.setParameter("codeClient", codeClient);
		
		
		return   query.list();
	}

	@Override
	public List<PrixClientMP> findAllByFournisseurClient(String codefournisseur,String codeClient) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from PrixClientMP c where client.code=:codeClient and fournisseur.code=:codefournisseur");
		query.setParameter("codeClient", codeClient);
		query.setParameter("codefournisseur", codefournisseur);
		
		return   query.list();
	}


}

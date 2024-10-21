package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.SequenceurDAO;
import dao.entity.Sequenceur;

public class SequenceurDAOImpl implements SequenceurDAO {
	Session session=HibernateUtil.openSession();

	public void add(Sequenceur e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public Sequenceur edit(Sequenceur e) {
		
	session.beginTransaction();
	Sequenceur p= (Sequenceur)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		Sequenceur p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	@SuppressWarnings("unchecked")
	public List<Sequenceur> findAll() {
		return session.createQuery("select c from Sequenceur c").list();
		}

	public Sequenceur findById(int id) {
		return (Sequenceur)session.get(Sequenceur.class, id);
		}


	
	@Override
	public Sequenceur findByLibelle(String libelle) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from Sequenceur c where libelle=:libelle");
		query.setParameter("libelle", libelle);
		
		return (Sequenceur) query.uniqueResult();
		
	}
	
	@Override
	public Sequenceur findByCodeLibelle(String code,String libelle) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from Sequenceur c where code=:code and libelle=:libelle");
		query.setParameter("libelle", libelle);
		query.setParameter("code", code);
		
		return (Sequenceur) query.uniqueResult();
		
	}
	@Override
	public Sequenceur findByCode(String code) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from Sequenceur c where code=:code");
	
		query.setParameter("code", code);
		
		return (Sequenceur) query.uniqueResult();
		
	}


}

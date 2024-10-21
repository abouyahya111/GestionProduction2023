package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.ChargesDAO;
import dao.entity.Articles;
import dao.entity.Charges;
import dao.entity.DetailEstimation;

public class ChargesDAOImpl implements ChargesDAO {
	Session session=HibernateUtil.openSession();

	public void add(Charges e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public Charges edit(Charges e) {
		
	session.beginTransaction();
	Charges p= (Charges)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		Charges p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
			}

	public List<Charges> findAll() {
		return session.createQuery("select c from Charges c").list();
		}

	public Charges findById(int id) {
		return (Charges)session.get(Charges.class, id);
		}
	
	public Charges findByCodeVariable(String code) {
		Query query= session.createQuery("select v from Charges v where v.code=:code and type='Variable'");
		query.setParameter("code", code.toUpperCase());
		return (Charges)query.uniqueResult();
		}
	public Charges findByCodeFixe(String code) {
		Query query= session.createQuery("select v from Charges v where v.code=:code and type='Fixe'");
		query.setParameter("code", code.toUpperCase());
		return (Charges)query.uniqueResult();
		}
	
	public List<Charges> findAllFixe() {
		return session.createQuery("select c from Charges c where  type='Fixe'").list();
		}
	
	public List<Charges> findAllVariable() {
		return session.createQuery("select c from Charges c where  type='Variable'").list();
		}

}

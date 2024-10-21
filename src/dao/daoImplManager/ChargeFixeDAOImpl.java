package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.ChargeFixeDAO;
import dao.daoManager.ChargesDAO;
import dao.entity.Articles;
import dao.entity.Charges;
import dao.entity.ChargeFixe;
import dao.entity.DetailEstimation;

public class ChargeFixeDAOImpl implements ChargeFixeDAO {
	Session session=HibernateUtil.openSession();

	public void add(ChargeFixe e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public ChargeFixe edit(ChargeFixe e) {
		
	session.beginTransaction();
	ChargeFixe p= (ChargeFixe)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		ChargeFixe p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
			}

	public List<ChargeFixe> findAll() {
		return session.createQuery("select c from ChargeFixe c").list();
		}

	public ChargeFixe findById(int id) {
		return (ChargeFixe)session.get(ChargeFixe.class, id);
		}
	
	public ChargeFixe findByCode(String code) {
		Query query= session.createQuery("select v from ChargeFixe v where v.code=:code ");
		query.setParameter("code", code.toUpperCase());
		return (ChargeFixe)query.uniqueResult();
		}
	
}

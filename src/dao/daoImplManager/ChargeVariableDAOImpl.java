package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.ChargeFixeDAO;
import dao.daoManager.ChargeVariableDAO;
import dao.daoManager.ChargesDAO;
import dao.entity.Articles;
import dao.entity.ChargeVariable;
import dao.entity.Charges;
import dao.entity.ChargeFixe;
import dao.entity.DetailEstimation;

public class ChargeVariableDAOImpl implements ChargeVariableDAO {
	Session session=HibernateUtil.openSession();

	public void add(ChargeVariable e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public ChargeVariable edit(ChargeVariable e) {
		
	session.beginTransaction();
	ChargeVariable p= (ChargeVariable)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		ChargeVariable p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
			}

	public List<ChargeVariable> findAll() {
		return session.createQuery("select c from ChargeVariable c").list();
		}

	public ChargeVariable findById(int id) {
		return (ChargeVariable)session.get(ChargeVariable.class, id);
		}
	
	public ChargeVariable findByCode(String code) {
		Query query= session.createQuery("select v from ChargeVariable v where v.code=:code ");
		query.setParameter("code", code.toUpperCase());
		return (ChargeVariable)query.uniqueResult();
		}
	
}

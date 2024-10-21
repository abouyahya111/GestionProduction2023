package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.FactureProductionDAO;
import dao.entity.Depot;
import dao.entity.FactureProduction;

public class FactureProductionDAOImpl implements FactureProductionDAO {
	Session session=HibernateUtil.openSession();

	public void add(FactureProduction e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public FactureProduction edit(FactureProduction e) {
		
	session.beginTransaction();
	FactureProduction p= (FactureProduction)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		FactureProduction p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<FactureProduction> findAll() {
		return session.createQuery("select c from FactureProduction c").list();
		}

	public FactureProduction findById(int id) {
		return (FactureProduction)session.get(FactureProduction.class, id);
		}
	
	public FactureProduction findFactureProductionByNumOF(String numOF) {
		Query query= session.createQuery("select c from FactureProduction c where numOF=:numOF");
		query.setParameter("numOF", numOF);
		
		return (FactureProduction) query.uniqueResult();
		
		}


}

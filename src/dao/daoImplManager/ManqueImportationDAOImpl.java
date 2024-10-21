package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.Constantes;
import util.HibernateUtil;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.ManqueImportationDAO;
import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.ManqueImportation;

public class ManqueImportationDAOImpl implements ManqueImportationDAO {
	Session session=HibernateUtil.openSession();

	public void add(ManqueImportation e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public ManqueImportation edit(ManqueImportation e) {
		
	session.beginTransaction();
	ManqueImportation p= (ManqueImportation)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		ManqueImportation p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<ManqueImportation> findAll() {
		return session.createQuery("select c from ManqueImportation c").list();
		}

	public ManqueImportation findById(int id) {
		return (ManqueImportation)session.get(ManqueImportation.class, id);
		}

	public List<ManqueImportation> findByRequete(String req) {
		
		Query query= session.createQuery("select m from ManqueImportation m "+req +"  ");
		
		
		
		return query.list();
		
		
		
	}
	
	
public List<ManqueImportation> findByEtatGroupByNumReception(String etat) {
		
		Query query= session.createQuery("select m from ManqueImportation m where m.etat=:etat group by numReception ");
		
		query.setParameter("etat", etat);

		
		return query.list();
		
		
		
	}
	
public List<ManqueImportation> findByEtatByNumReception(String etat,String numReception) {
	
	Query query= session.createQuery("select m from ManqueImportation m where m.etat=:etat and numReception=:numReception ");
	
	query.setParameter("etat", etat);
	query.setParameter("numReception", numReception);
	
	return query.list();
	
	
	
}
	
	
	 
	
	


}

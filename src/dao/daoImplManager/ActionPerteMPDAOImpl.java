package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ActionPerteMPDAO;
import dao.daoManager.FactureProductionDAO;
import dao.daoManager.FactureVenteMPDAO;
import dao.daoManager.PerteMPDAO;
import dao.entity.ActionPerteMP;
import dao.entity.Depot;

import dao.entity.FactureProduction;
import dao.entity.FactureVenteMP;
import dao.entity.Magasin;
import dao.entity.PerteMP;

public class ActionPerteMPDAOImpl implements ActionPerteMPDAO {
	Session session=HibernateUtil.openSession();

	public void add(ActionPerteMP e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public ActionPerteMP edit(ActionPerteMP e) {
		
	session.beginTransaction();
	ActionPerteMP p= (ActionPerteMP)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		ActionPerteMP p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<ActionPerteMP> findAll() {
		return session.createQuery("select c from ActionPerteMP c").list();
		}
	

	public ActionPerteMP findById(int id) {
		return (ActionPerteMP)session.get(ActionPerteMP.class, id);
		}
	
	
	
	public ActionPerteMP findByDateByMagasin(Date date, Magasin magasin , String etat) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from ActionPerteMP c where c.dateoperation=:date and c.magasin.id=:magasin and c.etat=:etat ");
				query.setParameter("date", date);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("etat", etat);
				
				return  (ActionPerteMP) query.uniqueResult();
}
	
	

	
	
	
	
	public void ViderSession()
	{
		
		if (session!=null)
		{
			session.clear();
			
		}
	
	}


}

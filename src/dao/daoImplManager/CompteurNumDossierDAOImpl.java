package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.CompteurNumDossierDAO;
import dao.entity.CompteurNumDossier;

public class CompteurNumDossierDAOImpl implements CompteurNumDossierDAO {
	Session session=HibernateUtil.openSession();

	public void add(CompteurNumDossier e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public CompteurNumDossier edit(CompteurNumDossier e) {
		
	session.beginTransaction();
	CompteurNumDossier p= (CompteurNumDossier)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		CompteurNumDossier p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	@SuppressWarnings("unchecked")
	public List<CompteurNumDossier> findAll() {
		return session.createQuery("select c from CompteurNumDossier c").list();
		}

	public CompteurNumDossier findById(int id) {
		return (CompteurNumDossier)session.get(CompteurNumDossier.class, id);
		}


	@Override
	public CompteurNumDossier findNumByAnnee(String annee) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select d from CompteurNumDossier d where annee=:annee");
				query.setParameter("annee", annee);
				
				return (CompteurNumDossier) query.uniqueResult();
}

	

}

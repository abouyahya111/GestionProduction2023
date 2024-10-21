package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.CompteurTransferMPDAO;
import dao.entity.CompteurNumDossier;
import dao.entity.CompteurTransferMP;

public class CompteurTransferMPDAOImpl implements CompteurTransferMPDAO {
	Session session=HibernateUtil.openSession();

	public void add(CompteurTransferMP e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public CompteurTransferMP edit(CompteurTransferMP e) {
		
	session.beginTransaction();
	CompteurTransferMP p= (CompteurTransferMP)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		CompteurTransferMP p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	@SuppressWarnings("unchecked")
	public List<CompteurTransferMP> findAll() {
		return session.createQuery("select c from CompteurTransferMP c").list();
		}

	public CompteurTransferMP findById(int id) {
		return (CompteurTransferMP)session.get(CompteurTransferMP.class, id);
		}


	@Override
	public CompteurTransferMP findByDepot(String codeDepot) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select d from CompteurTransferMP d where codeDepot=:codeDepot");
				query.setParameter("codeDepot", codeDepot);
				
				return (CompteurTransferMP) query.uniqueResult();
}

	

}

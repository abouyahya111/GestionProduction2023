package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailCompteMagasinierDAO;
import dao.entity.CompteMagasinier;
import dao.entity.Depot;
import dao.entity.DetailCompteMagasinier;
import dao.entity.Magasin;

public class DetailCompteMagasinierDAOImpl implements DetailCompteMagasinierDAO {
	Session session=HibernateUtil.openSession();

	public void add(DetailCompteMagasinier e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public DetailCompteMagasinier edit(DetailCompteMagasinier e) {
		
	session.beginTransaction();
	DetailCompteMagasinier p= (DetailCompteMagasinier)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailCompteMagasinier p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	@SuppressWarnings("unchecked")
	public List<DetailCompteMagasinier> findAll() {
		return session.createQuery("select c from DetailCompteMagasinier c").list();
		}

	public DetailCompteMagasinier findById(int id) {
		return (DetailCompteMagasinier)session.get(DetailCompteMagasinier.class, id);
		}




	@Override
	public List<DetailCompteMagasinier> listeDetailCompteMagasinierByCompteMagasinier (CompteMagasinier compteMagasinier) {
		Query query= session.createQuery("select c from DetailCompteMagasinier c where compteMagasinier.id=:compteMagasinier");
		query.setParameter("compteMagasinier", compteMagasinier.getId());
		return query.list();
	}




}

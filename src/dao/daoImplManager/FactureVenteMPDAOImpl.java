package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.FactureProductionDAO;
import dao.daoManager.FactureVenteMPDAO;
import dao.entity.Depot;

import dao.entity.FactureProduction;
import dao.entity.FactureVenteMP;
import dao.entity.Magasin;

public class FactureVenteMPDAOImpl implements FactureVenteMPDAO {
	Session session=HibernateUtil.openSession();

	public void add(FactureVenteMP e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public FactureVenteMP edit(FactureVenteMP e) {
		
	session.beginTransaction();
	FactureVenteMP p= (FactureVenteMP)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		FactureVenteMP p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<FactureVenteMP> findAll() {
		return session.createQuery("select c from FactureVenteMP c").list();
		}
	

	public FactureVenteMP findById(int id) {
		return (FactureVenteMP)session.get(FactureVenteMP.class, id);
		}
	
	@Override
	public FactureVenteMP findByNumFacture(String NumFacture, Magasin magasin) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureVenteMP c where c.numFacture=:NumFacture and c.magasin.id=:magasin");
				query.setParameter("NumFacture", NumFacture);
				query.setParameter("magasin", magasin.getId());
				
				
				return  (FactureVenteMP) query.uniqueResult();
}
	
	
	@Override
	public List<FactureVenteMP> findByRequete(String requete) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureVenteMP c where  "+requete);

				return   query.list();
}
	
	
	
	
	public void ViderSession()
	{
		
		if (session!=null)
		{
			session.clear();
			
		}
	
	}


}

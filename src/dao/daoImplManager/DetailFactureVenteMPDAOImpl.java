package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import main.ProdLauncher;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

import util.Constantes;
import util.HibernateUtil;
import dao.daoManager.DetailFactureVenteMPDAO;

import dao.daoManager.FactureProductionDAO;
import dao.entity.Articles;

import dao.entity.Depot;
import dao.entity.DetailEstimation;

import dao.entity.DetailFactureVenteMP;

import dao.entity.FactureProduction;

import dao.entity.Magasin;


public class DetailFactureVenteMPDAOImpl implements DetailFactureVenteMPDAO {
	Session session=HibernateUtil.openSession();

	public void add(DetailFactureVenteMP e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public DetailFactureVenteMP edit(DetailFactureVenteMP e) {
		
	session.beginTransaction();
	DetailFactureVenteMP p= (DetailFactureVenteMP)session.merge(e);
	session.getTransaction().commit();
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailFactureVenteMP p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<DetailFactureVenteMP> findAll() {
		return session.createQuery("select c from DetailFactureVenteMP c").list();
		}

	public DetailFactureVenteMP findById(int id) {
		return (DetailFactureVenteMP)session.get(DetailFactureVenteMP.class, id);
		}
	
	
	
	@Override
	public List<DetailFactureVenteMP> listeDetailFactureMPByFacture(int idFacture) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from DetailFactureVenteMP c where factureVenteMP.id=:idFacture");
		query.setParameter("idFacture", idFacture);
		
		return query.list();
		
		
	}
	
	@Override
	public List<DetailFactureVenteMP> listeDetailFactureMPGroupByMP() {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from DetailFactureVenteMP c  group by matierePremier.id ");
		
		
		return query.list();
		
		
	}
	
	
	
	
	
	public void ViderSession()
	{
		
		if (session!=null)
		{
			session.clear();
			
		}
	
	}
	
	
	
	
	
	
	
	
	
	

}

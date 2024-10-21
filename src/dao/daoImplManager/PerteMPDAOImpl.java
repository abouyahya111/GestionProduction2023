package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.FactureProductionDAO;
import dao.daoManager.FactureVenteMPDAO;
import dao.daoManager.PerteMPDAO;
import dao.entity.Depot;

import dao.entity.FactureProduction;
import dao.entity.FactureVenteMP;
import dao.entity.Magasin;
import dao.entity.PerteMP;

public class PerteMPDAOImpl implements PerteMPDAO {
	Session session=HibernateUtil.openSession();

	public void add(PerteMP e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public PerteMP edit(PerteMP e) {
		
	session.beginTransaction();
	PerteMP p= (PerteMP)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		PerteMP p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<PerteMP> findAll() {
		return session.createQuery("select c from PerteMP c").list();
		}
	

	public PerteMP findById(int id) {
		return (PerteMP)session.get(PerteMP.class, id);
		}
	
	
	
	public PerteMP findByDateByMagasin(Date date, Magasin magasin , String etat) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from PerteMP c where c.dateoperation=:date and c.magasin.id=:magasin and c.etat=:etat ");
				query.setParameter("date", date);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("etat", etat);
				
				return  (PerteMP) query.uniqueResult();
}
	
	
	public PerteMP findByDateByMagasinByNumPerte(String numPerte, Date date, Magasin magasin) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(!numPerte.equals("") && date!=null && magasin !=null )
		{
			
			 query= session.createQuery("select c from PerteMP c where c.numPerte=:numPerte and c.dateoperation=:date and c.magasin.id=:magasin  ");
			query.setParameter("date", date);
			query.setParameter("magasin", magasin.getId());
			
			query.setParameter("numPerte", numPerte);
		}else if(!numPerte.equals("") && date==null && magasin ==null )
		{
			 query= session.createQuery("select c from PerteMP c where c.numPerte=:numPerte ");
				
			
				query.setParameter("numPerte", numPerte);
			
			
		}else if(!numPerte.equals("") && date!=null && magasin ==null )
		{
			 query= session.createQuery("select c from PerteMP c where c.numPerte=:numPerte and c.dateoperation=:date  ");
				query.setParameter("date", date);		
				
				query.setParameter("numPerte", numPerte);
			
			
		}else if(!numPerte.equals("") && date==null && magasin !=null )
		{
			 query= session.createQuery("select c from PerteMP c where c.numPerte=:numPerte  and c.magasin.id=:magasin  ");
				
				query.setParameter("magasin", magasin.getId());
			
				query.setParameter("numPerte", numPerte);
			
			
		}else if(numPerte.equals("") && date!=null && magasin !=null )
		{
			
			 query= session.createQuery("select c from PerteMP c where c.dateoperation=:date and c.magasin.id=:magasin ");
			query.setParameter("date", date);
			query.setParameter("magasin", magasin.getId());
		
			
		}
				
				
				return  (PerteMP) query.uniqueResult();
}
	
	
	public PerteMP findByDateByMagasinByNumPerteParEtat(String numPerte, Date date, Magasin magasin , String etat) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(!numPerte.equals("") && date!=null && magasin !=null && !etat.equals(""))
		{
			
			 query= session.createQuery("select c from PerteMP c where c.numPerte=:numPerte and c.dateoperation=:date and c.magasin.id=:magasin and c.etat=:etat ");
			query.setParameter("date", date);
			query.setParameter("magasin", magasin.getId());
			query.setParameter("etat", etat);
			query.setParameter("numPerte", numPerte);
		}else if(!numPerte.equals("") && date==null && magasin ==null && !etat.equals(""))
		{
			 query= session.createQuery("select c from PerteMP c where c.numPerte=:numPerte and c.etat=:etat ");
				
				query.setParameter("etat", etat);
				query.setParameter("numPerte", numPerte);
			
			
		}else if(!numPerte.equals("") && date!=null && magasin ==null && !etat.equals(""))
		{
			 query= session.createQuery("select c from PerteMP c where c.numPerte=:numPerte and c.dateoperation=:date and c.etat=:etat ");
				query.setParameter("date", date);		
				query.setParameter("etat", etat);
				query.setParameter("numPerte", numPerte);
			
			
		}else if(!numPerte.equals("") && date==null && magasin !=null && !etat.equals(""))
		{
			 query= session.createQuery("select c from PerteMP c where c.numPerte=:numPerte  and c.magasin.id=:magasin and c.etat=:etat ");
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("etat", etat);
				query.setParameter("numPerte", numPerte);
			
			
		}else if(numPerte.equals("") && date!=null && magasin !=null && !etat.equals(""))
		{
			
			 query= session.createQuery("select c from PerteMP c where c.dateoperation=:date and c.magasin.id=:magasin and c.etat=:etat ");
			query.setParameter("date", date);
			query.setParameter("magasin", magasin.getId());
			query.setParameter("etat", etat);
			
		}
				
				
				return  (PerteMP) query.uniqueResult();
}
	
	

	public List<PerteMP> listDesPertesValiderParDepot(String Codedepot , String etat) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from PerteMP c where c.depot.code=:Codedepot and c.etat=:etat ");
				query.setParameter("Codedepot", Codedepot);
				query.setParameter("etat", etat);
				
				return   query.list();
}
	

	public List<PerteMP> listDesPertesParDepot(String Codedepot ) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from PerteMP c where c.depot.code=:Codedepot ");
				query.setParameter("Codedepot", Codedepot);
			
				
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

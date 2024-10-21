package dao.daoImplManager;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import util.Constantes;
import util.HibernateUtil;
import dao.daoManager.StockMPDAO;
import dao.entity.CategorieMp;
import dao.entity.FournisseurMP;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.StockMP;
import dao.entity.SubCategorieMp;

public class StockMPDAOImpl implements StockMPDAO {
	
	Session session=HibernateUtil.openSession();
	

	public void add(StockMP e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public StockMP edit(StockMP e) {
		
	session.beginTransaction();
	StockMP p= (StockMP)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		StockMP p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	@SuppressWarnings("unchecked")
	public List<StockMP> findAll() {
		return session.createQuery("select c from StockMP c").list();
		}

	public StockMP findById(int id) {
		return (StockMP)session.get(StockMP.class, id);
		}

	public StockMP findStockByMatierePremier(int id) {
		// TODO Auto-generated method stub
		StockMP stockMP= new StockMP();
		Query query= session.createQuery("select c from StockMP c where matierePremier.id=:id");
		query.setParameter("id", id);
		
		stockMP= (StockMP) query.uniqueResult();
		
		return stockMP;
	}

	@Override
	public StockMP findStockByMagasinMP(int idMP, int idMA) {
		// TODO Auto-generated method stub
		StockMP stockMP= new StockMP();
		Query query= session.createQuery("select c from StockMP c where matierePremier.id=:idMP and magasin.id=:idMA and c.fournisseurMP is null");
		query.setParameter("idMA", idMA);
		query.setParameter("idMP", idMP);
		
		
		stockMP= (StockMP) query.uniqueResult();
		
		return stockMP;
	}
	
	
	@Override
	public StockMP findStockByMagasinMPByFournisseurMP(int idMP, int idMA, int idfournisseurmp) {
		// TODO Auto-generated method stub
		StockMP stockMP= new StockMP();
		Query query= session.createQuery("select c from StockMP c where matierePremier.id=:idMP and magasin.id=:idMA and c.fournisseurMP.id=:idfournisseurmp");
		query.setParameter("idMA", idMA);
		query.setParameter("idMP", idMP);
		query.setParameter("idfournisseurmp", idfournisseurmp);
		
		stockMP= (StockMP) query.uniqueResult();
		
		return stockMP;
	}
	
	
	
	
	
	

	@SuppressWarnings("unchecked")
	@Override
	public List<StockMP> findAllByMagasin(int idMagasin) {

		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from StockMP c where magasin.id=:idMagasin");
		query.setParameter("idMagasin", idMagasin);
		
		
		return query.list();
		
	
	}

	@Override
	public List<StockMP> listeStockNouveauMP(int idMagasin) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from StockMP c where magasin.id=:idMagasin");
		query.setParameter("idMagasin", idMagasin);
		return query.list();
	}

	@Override
	public List<StockMP> findSockNonVideByMagasin(int idMagasin) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from StockMP c where stock <> 0 and magasin.id=:idMagasin order by c.matierePremier.id");
				query.setParameter("idMagasin", idMagasin);
				return query.list();
	}
	
	
	@Override
	public List<StockMP> findSockByMagasin(int idMagasin) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from StockMP c where   magasin.id=:idMagasin order by c.matierePremier.id");
				query.setParameter("idMagasin", idMagasin);
				return query.list();
	}
	
	
	@Override
	public List<StockMP> findSockNonVideByMagasinBySubCategorieByCategorieByMPByFournisseur(Magasin magasin , SubCategorieMp subcategorie, CategorieMp categorie , MatierePremier mp , FournisseurMP fournisseur) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Query query=null;
		if(magasin!=null && subcategorie==null && categorie==null && mp==null && fournisseur==null )
		{
			query= session.createQuery("select c from StockMP c where  magasin.id=:idMagasin order by c.matierePremier.id");
			query.setParameter("idMagasin", magasin.getId());
		}else if(magasin!=null && subcategorie!=null && categorie==null && mp==null && fournisseur==null )
		{
			
			query= session.createQuery("select c from StockMP c where  magasin.id=:idMagasin and c.matierePremier.categorieMp.subCategorieMp.id=:subcategorie order by c.matierePremier.id");
			query.setParameter("idMagasin", magasin.getId());
			query.setParameter("subcategorie", subcategorie.getId());
			
		}else if(magasin!=null && subcategorie!=null && categorie!=null && mp==null && fournisseur==null )
		{
			
			query= session.createQuery("select c from StockMP c where  magasin.id=:idMagasin and c.matierePremier.categorieMp.id=:categorie order by c.matierePremier.id");
			query.setParameter("idMagasin", magasin.getId());
			query.setParameter("categorie", categorie.getId());
			
		}else if(magasin!=null && subcategorie!=null && categorie!=null && mp!=null && fournisseur==null )
		{
			query= session.createQuery("select c from StockMP c where  magasin.id=:idMagasin and c.matierePremier.categorieMp.id=:categorie and c.matierePremier.id=:mp order by c.matierePremier.id");
			query.setParameter("idMagasin", magasin.getId());
			query.setParameter("categorie", categorie.getId());
			query.setParameter("mp", mp.getId());
		}else if(magasin!=null && subcategorie!=null && categorie!=null && mp!=null && fournisseur!=null )
		{
			
			query= session.createQuery("select c from StockMP c where magasin.id=:idMagasin and c.matierePremier.categorieMp.id=:categorie and c.matierePremier.id=:mp and c.fournisseurMP.id=:fournisseur order by c.matierePremier.id");
			query.setParameter("idMagasin", magasin.getId());
			query.setParameter("categorie", categorie.getId());
			query.setParameter("mp", mp.getId());
			query.setParameter("fournisseur", fournisseur.getId());
		}else if(magasin!=null && subcategorie!=null && categorie==null && mp!=null && fournisseur==null )
		{
			query= session.createQuery("select c from StockMP c where  magasin.id=:idMagasin and c.matierePremier.categorieMp.subCategorieMp.id=:subcategorie and c.matierePremier.id=:mp  order by c.matierePremier.id");
			query.setParameter("idMagasin", magasin.getId());
			query.setParameter("subcategorie", subcategorie.getId());
			query.setParameter("mp", mp.getId());
			
		}else if(magasin!=null && subcategorie!=null && categorie==null && mp!=null && fournisseur!=null )
		{
			query= session.createQuery("select c from StockMP c where  magasin.id=:idMagasin and c.matierePremier.categorieMp.subCategorieMp.id=:subcategorie and c.matierePremier.id=:mp and c.fournisseurMP.id=:fournisseur  order by c.matierePremier.id");
			query.setParameter("idMagasin", magasin.getId());
			query.setParameter("subcategorie", subcategorie.getId());
			query.setParameter("mp", mp.getId());
			query.setParameter("fournisseur", fournisseur.getId());
		}else if(magasin!=null && subcategorie!=null && categorie==null && mp==null && fournisseur!=null )
		{
			query= session.createQuery("select c from StockMP c where  magasin.id=:idMagasin and c.matierePremier.categorieMp.subCategorieMp.id=:subcategorie and  c.fournisseurMP.id=:fournisseur  order by c.matierePremier.id");
			query.setParameter("idMagasin", magasin.getId());
			query.setParameter("subcategorie", subcategorie.getId());
			
			query.setParameter("fournisseur", fournisseur.getId());
		}else if(magasin!=null && subcategorie!=null && categorie!=null && mp==null && fournisseur!=null )
		{
			query= session.createQuery("select c from StockMP c where  magasin.id=:idMagasin and c.matierePremier.categorieMp.id=:categorie  and c.fournisseurMP.id=:fournisseur order by c.matierePremier.id");
			query.setParameter("idMagasin", magasin.getId());
			query.setParameter("categorie", categorie.getId());
			
			query.setParameter("fournisseur", fournisseur.getId());
		}
		
		
		
		
		
				 
				return query.list();
	}
	
	
	
	
	
	@Override
	public List<Magasin> findMagasinByCodeSaufEnParametre(int idMagasin,String codeCatMagasin,String typeMagasin) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select distinct c.magasin from StockMP c where c.magasin.id <>:idMagasin and c.magasin.catMagasin =:codeCatMagasin and c.magasin.typeMagasin=:typeMagasin");
		query.setParameter("idMagasin", idMagasin);
		query.setParameter("codeCatMagasin", codeCatMagasin);
		query.setParameter("typeMagasin",typeMagasin);
		
		
		return query.list();
	}
	
	@Override
	public List<StockMP> findStockMin(int  idDepot) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from StockMP c where sum(stock) <= stockMin and magasin.depot.id=:idDepot ");
				query.setParameter("idDepot", idDepot);
				return query.list();
	}
	
	
	


	
	
	

	
	
	public void ViderSession()
	{
		if(session!=null)
		{
			session.clear();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*@Override
	public Map< Integer, Float> findStockTotalByMagasin(int  idDepot) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
		
				Query query= session.createQuery("select c.matierePremier.id,sum(c.stock) from StockMP c where c.magasin.depot.id=:idDepot and sum(c.stock) <= c.stockMin GROUP BY (c.matierePremier.id)");
				
				query.setParameter("idDepot", idDepot);
				
				List<List<Object>> permission= query.setResultTransformer(Transformers.TO_LIST).list();
				
				//now you just expect two columns 
				 Map< Integer, Float> mapStockTotalByMp= new HashMap<>();
				for(List<Object> x: permission){ 
					mapStockTotalByMp.put((Integer)x.get(0),(Float)x.get(1));
				}
				
				return mapStockTotalByMp;
	}*/
	
	
	
	
	
	


}

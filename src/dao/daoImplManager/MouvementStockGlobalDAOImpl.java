package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.MouvementStockGlobalDAO;
import dao.entity.Articles;
import dao.entity.Depot;
import dao.entity.DetailEstimation;
import dao.entity.Magasin;
import dao.entity.MouvementStockGlobal;

public class MouvementStockGlobalDAOImpl implements MouvementStockGlobalDAO{
    
    Session session=HibernateUtil.openSession();

  public MouvementStockGlobal findById(int id) {

return (MouvementStockGlobal)session.get(MouvementStockGlobal.class, id);    }

  
  public void add(MouvementStockGlobal mouvementStockGlobal) {

             session.beginTransaction();
		session.save(mouvementStockGlobal);
		
		session.getTransaction().commit();    }
  
  


  
  public MouvementStockGlobal edit(MouvementStockGlobal e) {

      session.beginTransaction();
	MouvementStockGlobal p= (MouvementStockGlobal)session.merge(e);
	session.getTransaction().commit();
		return p;

  }

  
  public void delete(MouvementStockGlobal e) {

              session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
  }

  
  public List<MouvementStockGlobal> findAll() {

return session.createQuery("select c from MouvementStockGlobal c").list();

  }
  
        public MouvementStockGlobal findMouvementStockGlobalByDetailMouvementStock(Date date,int depot,int magasin) {
        	
	Query query = session.createQuery("select c from MouvementStockGlobal c where c.dateMouvement =:date and c.depot.id =:depot and c.magasin.id =:magasin ");
              query.setParameter("date",date);
              query.setParameter("depot",depot);
              query.setParameter("magasin",magasin);
 
               return (MouvementStockGlobal)query.uniqueResult();
  
        }
        
          public List<MouvementStockGlobal> findMouvementStockGlobalByDetailMouvementStock(Date date) {
	Query query = session.createQuery("select c from MouvementStockGlobal c where c.dateMouvement =:date");
              query.setParameter("date",date);

 
               return query.list();
  
        }
          
          
          public List<MouvementStockGlobal> ConsulterMouvementStockGlobalByDetailMouvementStock(Date date,Depot depot,Magasin magasin) {
        	  Query query=null;
        	  
        	  if(date!=null && depot!=null && magasin!=null)
        		  {
        		  
        		   query = session.createQuery("select c from MouvementStockGlobal c where c.dateMouvement =:date and c.depot.id =:depot and c.magasin.id =:magasin ");
	              query.setParameter("date",date);
	              query.setParameter("depot",depot.getId());
	              query.setParameter("magasin",magasin.getId());
        		  
        		  }else if(date!=null && depot!=null && magasin==null) 
        		  {
        			  query = session.createQuery("select c from MouvementStockGlobal c where c.dateMouvement =:date and c.depot.id =:depot ");
    	              query.setParameter("date",date);
    	              query.setParameter("depot",depot.getId()); 
        			  
        		  }else if(depot!=null && magasin!=null && date==null)
        		  {
        			  query = session.createQuery("select c from MouvementStockGlobal c where  c.depot.id =:depot and c.magasin.id =:magasin ");
    	            
    	              query.setParameter("depot",depot.getId());
    	              query.setParameter("magasin",magasin.getId());
        			  
        		  }else if(date!=null && depot==null && magasin==null)
        		  {
        			  
        			  query = session.createQuery("select c from MouvementStockGlobal c where c.dateMouvement =:date ");
    	              query.setParameter("date",date);
    	         
        		  }else if(date==null && depot!=null && magasin==null)
        		  {
        			  query = session.createQuery("select c from MouvementStockGlobal c where  c.depot.id =:depot");
    	            
    	              query.setParameter("depot",depot.getId());
    	             
        			  
        		  }
        		 
        	 
        		  return query.list();
        	  
        	
        	 
        	              
        	  
        	        }
          
          
          
          
          
    
   
}

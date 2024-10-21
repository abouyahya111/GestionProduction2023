package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.DetailMouvementStockDAO;
import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.DetailMouvementStock;

public class DetailMouvementStockDAOImpl implements DetailMouvementStockDAO{
    
    Session session=HibernateUtil.openSession();

  public DetailMouvementStock findById(int id) {

return (DetailMouvementStock)session.get(DetailMouvementStock.class, id);    }

  public void add(DetailMouvementStock detailMouvementStock) {

             session.beginTransaction();
		session.save(detailMouvementStock);
		
		session.getTransaction().commit();    }

  public DetailMouvementStock edit(DetailMouvementStock e) {

session.beginTransaction();
	DetailMouvementStock p= (DetailMouvementStock)session.merge(e);
	session.getTransaction().commit();
		return p;

  }

  public void delete(DetailMouvementStock e) {

              session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
  }

  public List<DetailMouvementStock> findAll() {

return session.createQuery("select c from DetailMouvementStock c").list();

  }
  
     public List<DetailMouvementStock> findMouvementStockGlobalByDetailMouvementStock(int mov) {
	Query query = session.createQuery("select c from DetailMouvementStock c where c.mouvementStockGlobal.id=:mov");
              query.setParameter("mov",mov);
 
               return  query.list();
     }
}

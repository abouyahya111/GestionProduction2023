package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.TravailHorsProdDAO;
import dao.entity.Articles;
import dao.entity.TravailHorsProd;

public class TavailHorsProdDAOImpl implements TravailHorsProdDAO {
	Session session=HibernateUtil.openSession();

	public void add(TravailHorsProd e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public TravailHorsProd edit(TravailHorsProd e) {
		
	session.beginTransaction();
	TravailHorsProd p= (TravailHorsProd)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(TravailHorsProd e) {
		
		session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
		
	}

	@SuppressWarnings("unchecked")
	public List<TravailHorsProd> findAll() {
		return session.createQuery("select c from TravailHorsProd c").list();
		}

	public TravailHorsProd findById(int id) {
		return (TravailHorsProd)session.get(TravailHorsProd.class, id);
		}
	


	@Override
	public List<TravailHorsProd> findByDateSitutaionByArticle(Date dateDebut,Date dateFin, boolean heuresEnAttent , Articles articles) {
		
		// TODO Auto-generated method stub
		Query query=null;
		if(dateDebut!=null && dateFin!=null   && articles!=null)
		{
			  query= session.createQuery("select d from TravailHorsProd d where heuresEnAttente=:heuresEnAttent and dateSituation between :dateDebut and :dateFin and articles.id=:articles");
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("heuresEnAttent", heuresEnAttent);
			query.setParameter("articles", articles.getId());
		}else if(dateDebut!=null && dateFin!=null   && articles==null)
		{
			  query= session.createQuery("select d from TravailHorsProd d where heuresEnAttente=:heuresEnAttent and dateSituation between :dateDebut and :dateFin  ");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("heuresEnAttent", heuresEnAttent);
			 
		}else if(dateDebut==null && dateFin==null   && articles!=null)
		{
			query= session.createQuery("select d from TravailHorsProd d where heuresEnAttente=:heuresEnAttent   and articles.id=:articles");
			 
			query.setParameter("heuresEnAttent", heuresEnAttent);
			query.setParameter("articles", articles.getId());
			
		}
		
				
				return  query.list();
}
	


public  List<TravailHorsProd>  findByEmploye(int idEmploye) {
	
	// TODO Auto-generated method stub
	Query query= session.createQuery("select  c from TravailHorsProd c  where employe.id=:idEmploye");
	query.setParameter("idEmploye", idEmploye);
	return  query.list();
}	

public TravailHorsProd  findByEmployeCodeByArticle(int idEmploye,String code, Articles articles) {
	
	// TODO Auto-generated method stub
	Query query=null;
	if(articles!=null)
	{
		  query= session.createQuery("select  c from TravailHorsProd c  where c.employe.id=:idEmploye and c.code=:code and c.articles.id=:articles");
		query.setParameter("idEmploye", idEmploye);
		query.setParameter("code", code);
		query.setParameter("articles", articles.getId());
	}else
	{
		  query= session.createQuery("select  c from TravailHorsProd c  where c.employe.id=:idEmploye and c.code=:code ");
		query.setParameter("idEmploye", idEmploye);
		query.setParameter("code", code);
		 
	}

	return (TravailHorsProd) query.uniqueResult();
}



}

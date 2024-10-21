package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.CoutHorsProdEnAttentDAO;
import dao.daoManager.TravailHorsProdDAO;
import dao.entity.Articles;
import dao.entity.CoutHorsProdEnAttent;
import dao.entity.Depot;
import dao.entity.DetailProduction;
import dao.entity.Employe;
import dao.entity.Production;
import dao.entity.TravailHorsProd;

public class CoutHorsProdEnAttentDAOImpl implements CoutHorsProdEnAttentDAO {
	Session session=HibernateUtil.openSession();

	public void add(CoutHorsProdEnAttent e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public CoutHorsProdEnAttent edit(CoutHorsProdEnAttent e) {
		
	session.beginTransaction();
	CoutHorsProdEnAttent p= (CoutHorsProdEnAttent)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(CoutHorsProdEnAttent e) {
		
		session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
		
	}

	@SuppressWarnings("unchecked")
	public List<CoutHorsProdEnAttent> findAll() {
		return session.createQuery("select c from CoutHorsProdEnAttent c").list();
		}

	public CoutHorsProdEnAttent findById(int id) {
		return (CoutHorsProdEnAttent)session.get(CoutHorsProdEnAttent.class, id);
		}
	



	



public CoutHorsProdEnAttent  findByCodeByEmployeByArticle(String code , Employe employe,Articles articles) {
	
	// TODO Auto-generated method stub
	Query query= null;
	if(articles!=null)
	{
		 query= session.createQuery("select  c from CoutHorsProdEnAttent c  where  code=:code and employe.id=:employe and c.articles.id=:article");	
			query.setParameter("code", code);
			query.setParameter("employe", employe.getId());
			query.setParameter("article", articles.getId());
	}else
	{
		query= session.createQuery("select  c from CoutHorsProdEnAttent c  where  code=:code and employe.id=:employe ");	
		query.setParameter("code", code);
		query.setParameter("employe", employe.getId());
		 
		
	}
	 
	return (CoutHorsProdEnAttent) query.uniqueResult();
}


public List<CoutHorsProdEnAttent>  findByEtat(String etat , Depot depot) {
	
	// TODO Auto-generated method stub
	Query query= session.createQuery("select  c from CoutHorsProdEnAttent c  where depot.id=:depot and etat=:etat");
	
	query.setParameter("etat", etat);
	query.setParameter("depot", depot.getId());
	return  query.list();
	
	
}
 
public List<CoutHorsProdEnAttent>  findByEtatByDepotByDateByArticle(String etat , Depot depot,Date dateSituation,Articles articles) {
	
	// TODO Auto-generated method stub
	Query query= session.createQuery("select  c from CoutHorsProdEnAttent c  where c.depot.id=:depot and c.etat=:etat and c.dateSituation<=:dateSituation and c.articles.id=:article");
	
	query.setParameter("etat", etat);
	query.setParameter("depot", depot.getId());
	query.setParameter("article", articles.getId());
	query.setParameter("dateSituation", dateSituation);
	return  query.list();
}

public List<CoutHorsProdEnAttent>  findByProduction(Production production) {
	
	// TODO Auto-generated method stub
	Query query= session.createQuery("select  c from CoutHorsProdEnAttent c  where  production.id=:production");
	
	query.setParameter("production", production.getId());
	
	return  query.list();
}


public List<CoutHorsProdEnAttent>  findBydate(Date datedebut , Date dateFin , Depot depot) {
	
	// TODO Auto-generated method stub
	Query query= session.createQuery("select  c from CoutHorsProdEnAttent c  where depot.id=:depot and dateSituation between :datedebut and :dateFin");
	
	query.setParameter("datedebut", datedebut);
	query.setParameter("dateFin", dateFin);
	query.setParameter("depot", depot.getId());
	return  query.list();
}

public List<CoutHorsProdEnAttent>  findBydateByTypeTravail(Date datedebut , Date dateFin , Depot depot,String typeTravail) {
	
	// TODO Auto-generated method stub
	Query query=null;
	if(!typeTravail.equals(""))
	{
		query= session.createQuery("select  c from CoutHorsProdEnAttent c  where depot.id=:depot and dateSituation between :datedebut and :dateFin and typeTravail=:typeTravail");
		
		query.setParameter("datedebut", datedebut);
		query.setParameter("dateFin", dateFin);
		query.setParameter("depot", depot.getId());
		query.setParameter("typeTravail", typeTravail);
	}else
	{
		query= session.createQuery("select  c from CoutHorsProdEnAttent c  where depot.id=:depot and dateSituation between :datedebut and :dateFin ");
		
		query.setParameter("datedebut", datedebut);
		query.setParameter("dateFin", dateFin);
		query.setParameter("depot", depot.getId());
		 
	}
	
	return  query.list();
}


public List<CoutHorsProdEnAttent> ListHeursCoutHorsProdEnAttent(Date dateDebut,Date dateFin, String matricule  ,String statut) {
	
	// TODO Auto-generated method stub
			Query query= session.createQuery("select c from CoutHorsProdEnAttent c where c.production.date between :dateDebut and :dateFin and c.employe.matricule=:matricule  and c.production.statut=:statut order by c.production.date");
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("matricule", matricule);
			query.setParameter("statut", statut);
			return query.list();
}

public List<CoutHorsProdEnAttent> ListHeursCoutHorsProdEnAttentByDate(Date dateDebut,Date dateFin, String matricule) {
	
	// TODO Auto-generated method stub
			Query query= session.createQuery("select c from CoutHorsProdEnAttent c where c.dateSituation between :dateDebut and :dateFin and c.employe.matricule=:matricule  order by c.dateSituation");
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("matricule", matricule);
			 
			return query.list();
}


public List<CoutHorsProdEnAttent> ListHeursCoutHorsProdEnAttentParDepot(Date dateDebut,Date dateFin, int depot,String statut , String requet) {
	
	// TODO Auto-generated method stub
			Query query= session.createQuery("select c from CoutHorsProdEnAttent c where c.production.date between :dateDebut and :dateFin and c.employe.depot.id=:depot and c.production.statut=:statut "+requet);
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("depot", depot);
			query.setParameter("statut", statut);
			return query.list();
}

public List<CoutHorsProdEnAttent> ListHeursCoutHorsProdEnAttentParDepotParDate(Date dateDebut,Date dateFin, int depot, String requet) {
	
	// TODO Auto-generated method stub
			Query query= session.createQuery("select c from CoutHorsProdEnAttent c where c.dateSituation between :dateDebut and :dateFin and c.employe.depot.id=:depot  "+requet);
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("depot", depot);
			 
			return query.list();
}

}

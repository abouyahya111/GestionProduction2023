package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.CoutHorsProdEnAttentDAO;
import dao.daoManager.DetailCoutHorsProdEnAttentDAO;
import dao.daoManager.TravailHorsProdDAO;
import dao.entity.Articles;
import dao.entity.CoutHorsProdEnAttent;
import dao.entity.Depot;
import dao.entity.DetailCoutHorsProdEnAttent;
import dao.entity.DetailProduction;
import dao.entity.Employe;
import dao.entity.Production;
import dao.entity.TravailHorsProd;

public class DetailCoutHorsProdEnAttentDAOImpl implements DetailCoutHorsProdEnAttentDAO {
	Session session=HibernateUtil.openSession();

	public void add(DetailCoutHorsProdEnAttent e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public DetailCoutHorsProdEnAttent edit(DetailCoutHorsProdEnAttent e) {
		
	session.beginTransaction();
	DetailCoutHorsProdEnAttent p= (DetailCoutHorsProdEnAttent)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(DetailCoutHorsProdEnAttent e) {
		
		session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
		
	}

	@SuppressWarnings("unchecked")
	public List<DetailCoutHorsProdEnAttent> findAll() {
		return session.createQuery("select c from DetailCoutHorsProdEnAttent c").list();
		}

	public DetailCoutHorsProdEnAttent findById(int id) {
		return (DetailCoutHorsProdEnAttent)session.get(DetailCoutHorsProdEnAttent.class, id);
		}
	



	



public DetailCoutHorsProdEnAttent  findByCodeByEmployeByArticle(String code , Employe employe,Articles articles) {
	
	// TODO Auto-generated method stub
	Query query= session.createQuery("select  c from DetailCoutHorsProdEnAttent c  where  code=:code and employe.id=:employe and c.articles.id=:article");	
	query.setParameter("code", code);
	query.setParameter("employe", employe.getId());
	query.setParameter("article", articles.getId());
	return (DetailCoutHorsProdEnAttent) query.uniqueResult();
}


public List<DetailCoutHorsProdEnAttent>  findByEtat(String etat , Depot depot) {
	
	// TODO Auto-generated method stub
	Query query= session.createQuery("select  c from DetailCoutHorsProdEnAttent c  where depot.id=:depot and etat=:etat");
	
	query.setParameter("etat", etat);
	query.setParameter("depot", depot.getId());
	return  query.list();
}
 
public List<DetailCoutHorsProdEnAttent>  findByEtatByDepotByDateByArticle(String etat , Depot depot,Date dateSituation,Articles articles) {
	
	// TODO Auto-generated method stub
	Query query= session.createQuery("select  c from DetailCoutHorsProdEnAttent c  where c.depot.id=:depot and c.etat=:etat and c.dateSituation<=:dateSituation and c.articles.id=:article");
	
	query.setParameter("etat", etat);
	query.setParameter("depot", depot.getId());
	query.setParameter("article", articles.getId());
	query.setParameter("dateSituation", dateSituation);
	return  query.list();
}

public List<DetailCoutHorsProdEnAttent>  findByProduction(Production production) {
	
	// TODO Auto-generated method stub
	Query query= session.createQuery("select  c from DetailCoutHorsProdEnAttent c  where  production.id=:production");
	
	query.setParameter("production", production.getId());
	
	return  query.list();
}


public List<DetailCoutHorsProdEnAttent>  findBydate(Date datedebut , Date dateFin , Depot depot) {
	
	// TODO Auto-generated method stub
	Query query= session.createQuery("select  c from DetailCoutHorsProdEnAttent c  where depot.id=:depot and dateSituation between :datedebut and :dateFin");
	
	query.setParameter("datedebut", datedebut);
	query.setParameter("dateFin", dateFin);
	query.setParameter("depot", depot.getId());
	return  query.list();
}

public List<DetailCoutHorsProdEnAttent>  findByCoutHorsProductionEnAttente(CoutHorsProdEnAttent coutHorsProdEnAttent) {
	
	// TODO Auto-generated method stub
	Query query= session.createQuery("select  c from DetailCoutHorsProdEnAttent c  where coutHorsProductionEnAttente.id=:coutHorsProdEnAttent ");
	
	query.setParameter("coutHorsProdEnAttent", coutHorsProdEnAttent.getId());
	 
	return  query.list();
}


public List<DetailCoutHorsProdEnAttent> ListHeursCoutHorsProdEnAttent(Date dateDebut,Date dateFin, String matricule  ,String statut) {
	
	// TODO Auto-generated method stub
			Query query= session.createQuery("select c from DetailCoutHorsProdEnAttent c where c.production.date between :dateDebut and :dateFin and c.employe.matricule=:matricule  and c.production.statut=:statut order by c.production.date");
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("matricule", matricule);
			query.setParameter("statut", statut);
			return query.list();
}


public List<DetailCoutHorsProdEnAttent> ListHeursCoutHorsProdEnAttentParDepot(Date dateDebut,Date dateFin, int depot,String statut , String requet) {
	
	// TODO Auto-generated method stub
			Query query= session.createQuery("select c from DetailCoutHorsProdEnAttent c where c.production.date between :dateDebut and :dateFin and c.employe.depot.id=:depot and c.production.statut=:statut "+requet);
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("depot", depot);
			query.setParameter("statut", statut);
			return query.list();
}



}

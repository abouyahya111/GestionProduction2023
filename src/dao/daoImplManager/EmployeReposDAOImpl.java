package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.EmployeDAO;
import dao.daoManager.EmployeReposDAO;
import dao.entity.Depot;
import dao.entity.Employe;
import dao.entity.EmployeRepos;
import dao.entity.TypeResEmploye;

public class EmployeReposDAOImpl implements EmployeReposDAO {
	Session session=HibernateUtil.openSession();

	public void add(EmployeRepos e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public EmployeRepos edit(EmployeRepos e) {
		
	session.beginTransaction();
	EmployeRepos p= (EmployeRepos)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		EmployeRepos p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<EmployeRepos> findAll() {
		Query query= session.createQuery("select c from EmployeRepos c");
		query.setParameter("actif", true);
		
		return query.list();
		}
	

	

	public EmployeRepos findById(int id) {
		return (EmployeRepos)session.get(EmployeRepos.class, id);
		}
	
	
	public List<EmployeRepos> findByDepotByDate(Date datesituation, String depot) {
		Query query= session.createQuery("select c from EmployeRepos c where dateSituation=:datesituation and employe.actif=:actif and employe.depot.code=:depot order by employe.nom ,employe.matricule ");
		query.setParameter("actif", true);
		query.setParameter("depot", depot);
		query.setParameter("datesituation", datesituation);
		return query.list();
		}
	
	public List<EmployeRepos> findByRequete(Date datedebut,Date datefin, Depot depot,String  requete) {
		Query query= session.createQuery("select c from EmployeRepos c where dateSituation between :datedebut and :datefin  and employe.depot.code=:depot "+requete);
		query.setParameter("datedebut", datedebut);
		query.setParameter("datefin", datefin);
		query.setParameter("depot", depot.getCode());
		//query.setParameter("actif", true);
		return query.list();
		}

	


}

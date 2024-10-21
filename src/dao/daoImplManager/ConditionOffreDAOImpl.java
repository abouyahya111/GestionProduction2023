package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.ConditionOffreDAO;
import dao.daoManager.TypeOffreDAO;
import dao.daoManager.TypeSortieDAO;
import dao.entity.Articles;
import dao.entity.ConditionOffre;
import dao.entity.DetailEstimation;
import dao.entity.TypeOffre;
import dao.entity.TypeSortie;

public class ConditionOffreDAOImpl implements ConditionOffreDAO {
	Session session=HibernateUtil.openSession();

	public void add(ConditionOffre e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public ConditionOffre edit(ConditionOffre e) {
		
	session.beginTransaction();
	ConditionOffre p= (ConditionOffre)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		ConditionOffre p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<ConditionOffre> findAll() {
		return session.createQuery("select c from ConditionOffre c order by c.valeur").list();
		}

	public ConditionOffre findById(int id) {
		return (ConditionOffre)session.get(ConditionOffre.class, id);
		}

	public ConditionOffre findByconditionOffre(String conditionOffre) {
	
		Query query= session.createQuery("select c from ConditionOffre c where conditionOffre=:conditionOffre");
		query.setParameter("conditionOffre", conditionOffre);
		
		return (ConditionOffre) query.uniqueResult();
		
		
	}

}

package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ParametreModifierDAO;
import dao.entity.Parametre;
import dao.entity.ParametreModifier;

public class ParametreModifierDAOImpl implements ParametreModifierDAO {
	Session session=HibernateUtil.openSession();

	public void add(ParametreModifier e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public ParametreModifier edit(ParametreModifier e) {
		
	session.beginTransaction();
	ParametreModifier p= (ParametreModifier)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		ParametreModifier p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	@SuppressWarnings("unchecked")
	public List<ParametreModifier> findAll() {
		return session.createQuery("select c from ParametreModifier c").list();
		}
	
	@SuppressWarnings("unchecked")
	public List<ParametreModifier> findAllGroupByLibelle() {
		return session.createQuery("select c from ParametreModifier c group by c.libelle").list();
		}

	public ParametreModifier findById(int id) {
		return (ParametreModifier)session.get(ParametreModifier.class, id);
		}

	 
	public ParametreModifier findByCode(String code) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from ParametreModifier c where code=:code");
		query.setParameter("code", code);
		
		return (ParametreModifier) query.uniqueResult();
		
	}
	
	 
	public ParametreModifier findByLibelle(String libelle) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from ParametreModifier c where libelle=:libelle");
		query.setParameter("libelle", libelle);
		
		return (ParametreModifier) query.uniqueResult();
		
	}
	
	 
	public ParametreModifier findByDateByLibelle(Date date, String libelle) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from ParametreModifier c where c.date in (select max(p.date) from ParametreModifier p where p.libelle=:libelle and p.date<=:date) and c.libelle=:libelle");
		query.setParameter("libelle", libelle);
		query.setParameter("date", date);
		
		return (ParametreModifier) query.uniqueResult();
		
	}
 
	public ParametreModifier findUniqueParametreByDateByLibelle(Date date, String libelle) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from ParametreModifier c where c.date =:date and c.libelle=:libelle");
		query.setParameter("libelle", libelle);
		query.setParameter("date", date);
		
		return (ParametreModifier) query.uniqueResult();
		
	}

}

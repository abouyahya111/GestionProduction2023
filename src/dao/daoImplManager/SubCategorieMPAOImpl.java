package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.SubCategorieMPDAO;
import dao.entity.SubCategorieMp;

public class SubCategorieMPAOImpl implements SubCategorieMPDAO {
	
	Session session=HibernateUtil.openSession();

	public void add(SubCategorieMp e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public SubCategorieMp edit(SubCategorieMp e) {
		
	session.beginTransaction();
	SubCategorieMp p= (SubCategorieMp)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		SubCategorieMp p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<SubCategorieMp> findAll() {
		return session.createQuery("select c from SubCategorieMp c").list();
		}

	public SubCategorieMp findById(int id) {
		return (SubCategorieMp)session.get(SubCategorieMp.class, id);
		}
	
	
	public List<SubCategorieMp> findAllSauf(String Cat) {
		Query query= session.createQuery("select c from SubCategorieMp c where c.code!=:Cat");
		
        query.setParameter("Cat", Cat);
		
		return query.list();
		
		}
	
	
	public SubCategorieMp findByCode(String Cat) {
		Query query= session.createQuery("select c from SubCategorieMp c where c.code=:Cat");
		
        query.setParameter("Cat", Cat);
		
		return (SubCategorieMp) query.uniqueResult();
		
		}

}

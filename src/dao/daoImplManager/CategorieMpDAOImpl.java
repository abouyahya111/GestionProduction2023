package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.CategorieMpDAO;
import dao.entity.CategorieMp;
import dao.entity.SubCategorieMp;

public class CategorieMpDAOImpl implements CategorieMpDAO {
	
	Session session=HibernateUtil.openSession();
	
	

	public void add(CategorieMp e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public CategorieMp edit(CategorieMp e) {
		
	session.beginTransaction();
	CategorieMp p= (CategorieMp)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		CategorieMp p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	

	public  CategorieMp findById(int id) {
		return ( CategorieMp)session.get( CategorieMp.class, id);
		}

	@Override
	public List<CategorieMp> findAll() {
		return session.createQuery("select c from CategorieMp c").list();
		}
	@Override
	public List<CategorieMp> findBySubcategorie(int id) {
		Query query= session.createQuery("select c from CategorieMp c where id_sub_cat=:id");
		query.setParameter("id", id);
		return query.list();
		}
	
	@Override
	public CategorieMp findByCode(String code) {
		Query query= session.createQuery("select c from CategorieMp c where code=:code");
		query.setParameter("code", code);
		return (CategorieMp) query.uniqueResult();
		}


}

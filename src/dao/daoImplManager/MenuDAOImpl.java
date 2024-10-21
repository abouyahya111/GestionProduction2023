package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.MenuDAO;
import dao.entity.Articles;
import dao.entity.Menu;

public class MenuDAOImpl implements MenuDAO {
	Session session=HibernateUtil.openSession();

	public void add(Menu e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public Menu edit(Menu e) {
		
	session.beginTransaction();
	Menu p= (Menu)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		Menu p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<Menu> findAll() {
		return session.createQuery("select c from Menu c").list();
		}

	public Menu findById(int id) {
		return (Menu)session.get(Menu.class, id);
		}

	@Override
	public Menu findByCode(String code) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from Menu c where code=:code");
		query.setParameter("code", code);
		
		return (Menu) query.uniqueResult();
		
	}


}

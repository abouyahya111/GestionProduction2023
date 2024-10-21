package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.HabilitationDAO;
import dao.entity.Habilitation;

public class HabilitationDAOImpl implements HabilitationDAO {
	Session session=HibernateUtil.openSession();

	public void add(Habilitation e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public Habilitation edit(Habilitation e) {
		
	session.beginTransaction();
	Habilitation p= (Habilitation)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		Habilitation p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<Habilitation> findAll() {
		return session.createQuery("select c from Habilitation c").list();
		}

	public Habilitation findById(int id) {
		return (Habilitation)session.get(Habilitation.class, id);
		}

	@Override
	public Habilitation findByMenuUtilisateur(int idMenu,int idUser) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from Habilitation c where menu.id=:idMenu and utilisateur.id=:idUser");
		query.setParameter("idUser", idUser);
		query.setParameter("idMenu", idMenu);
		
		return  (Habilitation) query.uniqueResult();
		
	}
	
	@Override
	public List<Habilitation> findHabilitationByUtilisateur(int idUser) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from Habilitation c where utilisateur.id=:idUser");
		query.setParameter("idUser", idUser);
		
		return  query.list();
		
	}


}

package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.UtilisateurDAO;
import dao.entity.Utilisateur;

public class UtilisateurDAOImpl implements UtilisateurDAO {
	
	Session session=HibernateUtil.openSession();

	public Utilisateur findUtilisateurByLoginMotPasse(String login,
			String motPasse) {
		
		Query query = session.createQuery("select u from Utilisateur u where  u.login=:login and u.password=:motPasse");
		query.setParameter("login", login);
		query.setParameter("motPasse", motPasse);
	return (Utilisateur) query.uniqueResult();
	      

				}

	public Utilisateur findById(int id) {
		return (Utilisateur)session.get(Utilisateur.class, id);
		}

	@Override
	public void add(Utilisateur utilisateur) {
		session.beginTransaction();
		session.save(utilisateur);
		
		session.getTransaction().commit();
		//return p;
	}

	@Override
	public Utilisateur edit(Utilisateur e) {
		
	session.beginTransaction();
	Utilisateur p= (Utilisateur)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	@Override
	public void delete(int id) {
		
		session.beginTransaction();
		Utilisateur p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	@Override
	public List<Utilisateur> findAll() {
		return session.createQuery("select c from Utilisateur c").list();
		}
	
	@Override
	public List<Utilisateur> findAllSaufAdmin() {
		return session.createQuery("select c from Utilisateur c where id<>1").list();
		}

}

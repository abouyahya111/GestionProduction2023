package dao.daoImplManager;

import java.util.List;

import main.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.FormeDAO;
import dao.daoManager.GrammageBoxDAO;
import dao.daoManager.ServiceDAO;
import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.GrammageBox;
import dao.entity.forme;
import dao.entity.service;


public class ServiceDAOImpl implements ServiceDAO {
	Session session=HibernateUtil.openSession();
		

	public void add(service e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public service edit(service e) {
		
	session.beginTransaction();
	service p= (service)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		service p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<service> findAll() {
		return session.createQuery("select c from service c").list();
		}

	public service findById(int id) {
		return (service)session.get(service.class, id);
		}

	@Override
	public service findByLibelle(String libelle) {
		// TODO Auto-generated method stub
		service service= new service();
		Query query= session.createQuery("select c from service c where libelle=:libelle");
		query.setParameter("libelle", libelle);
		
		service= (service) query.uniqueResult();
		
		return service;
	}
	
	


}

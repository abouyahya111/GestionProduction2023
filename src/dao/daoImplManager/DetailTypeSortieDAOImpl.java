package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.DetailTypeSortieDAO;
import dao.daoManager.ManqueDechetFournisseurDAO;
import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.DetailTypeSortie;
import dao.entity.ManqueDechetFournisseur;
import dao.entity.MatierePremier;
import dao.entity.TypeSortie;

public class DetailTypeSortieDAOImpl implements DetailTypeSortieDAO {
	Session session=HibernateUtil.openSession();

	public void add(DetailTypeSortie e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public DetailTypeSortie edit(DetailTypeSortie e) {
		
	session.beginTransaction();
	DetailTypeSortie p= (DetailTypeSortie)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailTypeSortie p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<DetailTypeSortie> findAll() {
		return session.createQuery("select c from DetailTypeSortie c").list();
		}

	public DetailTypeSortie findById(int id) {
		return (DetailTypeSortie)session.get(DetailTypeSortie.class, id);
		}

	@Override
	public DetailTypeSortie DetailTypeSortieByTypeSortie(String type) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from DetailTypeSortie c where sousType=:type");
		query.setParameter("type", type);
		
		return (DetailTypeSortie) query.uniqueResult();
		
	}
	
	
	

	
	@Override
	public List<DetailTypeSortie> listeDetailTypeSortieByTypeSortie(TypeSortie type) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from DetailTypeSortie c where typesortie.id=:type");
		query.setParameter("type", type.getId());
		
		return query.list();
		
	}


}

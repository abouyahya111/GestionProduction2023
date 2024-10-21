package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.DetailPerteMPDAO;
import dao.daoManager.DetailTypeSortieDAO;
import dao.daoManager.ManqueDechetFournisseurDAO;
import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.DetailPerteMP;
import dao.entity.DetailTypeSortie;
import dao.entity.ManqueDechetFournisseur;
import dao.entity.MatierePremier;
import dao.entity.PerteMP;
import dao.entity.TypeSortie;

public class DetailPerteMPDAOImpl implements DetailPerteMPDAO {
	Session session=HibernateUtil.openSession();

	public void add(DetailPerteMP e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public DetailPerteMP edit(DetailPerteMP e) {
		
	session.beginTransaction();
	DetailPerteMP p= (DetailPerteMP)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailPerteMP p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<DetailPerteMP> findAll() {
		return session.createQuery("select c from DetailPerteMP c").list();
		}

	public DetailPerteMP findById(int id) {
		return (DetailPerteMP)session.get(DetailPerteMP.class, id);
		}


	
	@Override
	public List<DetailPerteMP> listeDetailPerteMPByPerteMP(PerteMP perteMP) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from DetailPerteMP c where perteMP.id=:perteMP");
		query.setParameter("perteMP", perteMP.getId());
		
		return query.list();
		
	}


}

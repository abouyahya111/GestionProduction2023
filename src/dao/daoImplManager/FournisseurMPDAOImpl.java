package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.FournisseurMP;
import dao.entity.SubCategorieMp;

public class FournisseurMPDAOImpl implements FournisseurMPDAO {
	Session session=HibernateUtil.openSession();

	public void add(FournisseurMP e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public FournisseurMP edit(FournisseurMP e) {
		
	session.beginTransaction();
	FournisseurMP p= (FournisseurMP)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		FournisseurMP p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<FournisseurMP> findAll() {
		return session.createQuery("select c from FournisseurMP c").list();
		}

	public FournisseurMP findById(int id) {
		return (FournisseurMP)session.get(FournisseurMP.class, id);
		}

	@Override
	public FournisseurMP findByCode(String code) {
		// TODO Auto-generated method stub
		FournisseurMP fournisseurMP= new FournisseurMP();
		Query query= session.createQuery("select c from FournisseurMP c where codeFournisseur=:code");
		query.setParameter("code", code);
		
		fournisseurMP= (FournisseurMP) query.uniqueResult();
		
		return fournisseurMP;
	}
	
	@Override
	public List<FournisseurMP> findByCategorie(SubCategorieMp subcategorie) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from FournisseurMP c where subCategorieMp.id=:subcategorie");
		query.setParameter("subcategorie", subcategorie.getId());
		
		
		
		return query.list();
	}


}

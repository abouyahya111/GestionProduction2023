package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.DetailActionPerteMPDAO;
import dao.daoManager.DetailPerteMPDAO;
import dao.daoManager.DetailTypeSortieDAO;
import dao.daoManager.ManqueDechetFournisseurDAO;
import dao.entity.ActionPerteMP;
import dao.entity.Articles;
import dao.entity.DetailActionPerteMP;
import dao.entity.DetailEstimation;
import dao.entity.DetailPerteMP;
import dao.entity.DetailTypeSortie;
import dao.entity.ManqueDechetFournisseur;
import dao.entity.MatierePremier;
import dao.entity.PerteMP;
import dao.entity.TypeSortie;

public class DetailActionPerteMPDAOImpl implements DetailActionPerteMPDAO {
	Session session=HibernateUtil.openSession();

	public void add(DetailActionPerteMP e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public DetailActionPerteMP edit(DetailActionPerteMP e) {
		
	session.beginTransaction();
	DetailActionPerteMP p= (DetailActionPerteMP)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailActionPerteMP p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<DetailActionPerteMP> findAll() {
		return session.createQuery("select c from DetailActionPerteMP c").list();
		}

	public DetailActionPerteMP findById(int id) {
		return (DetailActionPerteMP)session.get(DetailActionPerteMP.class, id);
		}


	
	@Override
	public List<DetailActionPerteMP> listeDetailActionPerteMPByActionPerteMP(ActionPerteMP ActionperteMP) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from DetailActionPerteMP c where actionperteMP.id=:ActionperteMP");
		query.setParameter("ActionperteMP", ActionperteMP.getId());
		
		return query.list();
		
	}

	
	@Override
	public List<DetailActionPerteMP> listeDetailActionPerteMPByDetailPerteMP(DetailPerteMP DetailnperteMP) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from DetailActionPerteMP c where detailPerteMP.id=:DetailnperteMP");
		query.setParameter("DetailnperteMP", DetailnperteMP.getId());
		
		return query.list();
		
	}
	
	
	
	public void ViderSession()
	{
		if(session!=null)
		{
			session.clear();
		}
	}
		
	
	
	

}

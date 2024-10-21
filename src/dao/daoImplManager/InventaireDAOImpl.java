package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.InventaireDAO;
import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.FournisseurMP;
import dao.entity.Inventaire;
import dao.entity.Magasin;
import dao.entity.MatierePremier;

public class InventaireDAOImpl implements InventaireDAO {
	Session session=HibernateUtil.openSession();

	public void add(Inventaire e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public Inventaire edit(Inventaire e) {
		
	session.beginTransaction();
	Inventaire p= (Inventaire)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		Inventaire p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<Inventaire> findAll() {
		return session.createQuery("select c from Inventaire c").list();
		}

	public Inventaire findById(int id) {
		return (Inventaire)session.get(Inventaire.class, id);
		}

	@Override
	public List<Inventaire> findByDateByMagasin(Magasin magasin , Date DateOperation,String code,String etat) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from Inventaire c where c.magasin.id=:magasin and c.dateoperation=:DateOperation and codeInventaire=:code and etat=:etat");
		query.setParameter("magasin", magasin.getId());
		query.setParameter("DateOperation", DateOperation);
		query.setParameter("code", code);
		query.setParameter("etat",etat );
		
		return  query.list();
	}
	
	
	
	
	@Override
	public Inventaire findByDateByMagasinByMP(Magasin magasin , Date DateOperation , MatierePremier mp,String code,String etat) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from Inventaire c where c.magasin.id=:magasin and c.dateoperation=:DateOperation and c.matierePremier.id=:mp and codeInventaire=:code and etat=:etat");
		query.setParameter("magasin", magasin.getId());
		query.setParameter("DateOperation", DateOperation);
		query.setParameter("mp", mp.getId());
		query.setParameter("code", code);
		query.setParameter("etat",etat );
		
		return  (Inventaire)query.uniqueResult();
	}
	
	
	@Override
	public Inventaire findByDateByMagasinByMPByFournisseur(Magasin magasin , Date DateOperation , MatierePremier mp,FournisseurMP fournisseurMP,String code,String etat) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from Inventaire c where c.magasin.id=:magasin and c.dateoperation=:DateOperation and c.matierePremier.id=:mp and fournisseurMP.id=:fournisseurMP and codeInventaire=:code and etat=:etat");
		query.setParameter("magasin", magasin.getId());
		query.setParameter("DateOperation", DateOperation);
		query.setParameter("mp", mp.getId());
		query.setParameter("fournisseurMP", fournisseurMP.getId());
		query.setParameter("code", code);
		query.setParameter("etat",etat );
		return  (Inventaire)query.uniqueResult();
	}
	


}

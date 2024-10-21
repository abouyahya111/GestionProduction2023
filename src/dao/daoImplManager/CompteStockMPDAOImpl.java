package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.CompteStockMPDAO;
import dao.entity.CompteStockMP;

public class CompteStockMPDAOImpl implements CompteStockMPDAO {
	Session session=HibernateUtil.openSession();

	public void add(CompteStockMP e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public CompteStockMP edit(CompteStockMP e) {
		
	session.beginTransaction();
	CompteStockMP p= (CompteStockMP)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		CompteStockMP p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<CompteStockMP> findAll() {
		return session.createQuery("select c from CompteStockMP c").list();
		}

	public CompteStockMP findById(int id) {
		return (CompteStockMP)session.get(CompteStockMP.class, id);
		}
	
	
	public CompteStockMP findByCodeMPAnneeMois(String code,int mois,int annee) {
		Query query= session.createQuery("select d from CompteStockMP d where matierePremier.code=:code and mois=:mois and annee=:annee");
		query.setParameter("code", code);
		query.setParameter("mois", mois);
		query.setParameter("annee", annee);
		
		return (CompteStockMP) query.uniqueResult();
		}
	
	public  List<CompteStockMP> findListeByAnneeMois(int moisDebut,int moisFin,int annee) {
		Query query= session.createQuery("select d from CompteStockMP d where mois>=:moisDebut and mois<=:moisFin and annee=:annee order by mois");
		query.setParameter("moisDebut", moisDebut);
		query.setParameter("moisFin", moisFin);
		query.setParameter("annee", annee);
		
		return  query.list();
		}

	
	public  List<Object> findSumByAnneeMois(int moisDebut,int moisFin,int annee) {
		Query query= session.createQuery("select matierePremier.code, sum(d.quantite)  from CompteStockMP  where mois>=:moisDebut and mois<=:moisFin and annee=:annee group by matierePremier.code");
		query.setParameter("moisDebut", moisDebut);
		query.setParameter("moisFin", moisFin);
		query.setParameter("annee", annee);
		
		return  query.list();
		}

}

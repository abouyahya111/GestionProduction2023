package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.Constantes;
import util.HibernateUtil;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.StatistiqueFinanciereMPDAO;
import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.StatistiqueFinanciaireMP;

public class StatistiqueFinanciereMPDAOImpl implements StatistiqueFinanciereMPDAO {
	Session session=HibernateUtil.openSession();

	public void add(StatistiqueFinanciaireMP e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public StatistiqueFinanciaireMP edit(StatistiqueFinanciaireMP e) {
		
	session.beginTransaction();
	StatistiqueFinanciaireMP p= (StatistiqueFinanciaireMP)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		StatistiqueFinanciaireMP p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<StatistiqueFinanciaireMP> findAll() {
		return session.createQuery("select c from StatistiqueFinanciaireMP c").list();
		}
	
	@Override
	public List<StatistiqueFinanciaireMP> listeStatistiqueFinanciereMPByCodeTransfert( String code) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from StatistiqueFinanciaireMP c where  c.CodeTransfer=:code ");
		query.setParameter("code", code);
		return query.list();
		
		
	}

	public StatistiqueFinanciaireMP findById(int id) {
		return (StatistiqueFinanciaireMP)session.get(StatistiqueFinanciaireMP.class, id);
		}

	@Override
	public StatistiqueFinanciaireMP StatistiqueFinanciereMPByEtatInventaire( String etatInventaire) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from StatistiqueFinanciaireMP c where  c.etat=:etatInventaire ");
		query.setParameter("etatInventaire", etatInventaire);
		return (StatistiqueFinanciaireMP)query.uniqueResult();
		
		
	} 


}

package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.CompteMagasinierDAO;
import dao.daoManager.FraisDepotDAO;
import dao.daoManager.ProductionDAO;
import dao.entity.CompteMagasinier;
import dao.entity.CoutMP;
import dao.entity.DetailCompteMagasinier;
import dao.entity.DetailFraisDepot;
import dao.entity.DetailProdGen;
import dao.entity.DetailProduction;
import dao.entity.DetailResponsableProd;
import dao.entity.FraisDepot;
import dao.entity.MatierePremier;
import dao.entity.Production;

public class FraisDepotDAOImpl implements FraisDepotDAO {
	Session session=HibernateUtil.openSession();

	public void add(FraisDepot e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public FraisDepot edit(FraisDepot e) {
		
	session.beginTransaction();
	FraisDepot p= (FraisDepot)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		FraisDepot p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<FraisDepot> findAll() {
		return session.createQuery("select c from FraisDepot c").list();
		}

	public FraisDepot findById(int id) {
		return (FraisDepot)session.get(FraisDepot.class, id);
		}


	
	public FraisDepot findByCode(String code) {
		// TODO Auto-generated method stub
		FraisDepot FraisDepot= new FraisDepot();
		Query query= session.createQuery("select c from FraisDepot c where code=:code");
		query.setParameter("code", code);
		
		FraisDepot= (FraisDepot) query.uniqueResult();
		
		return FraisDepot;
	}
	
	public List<DetailFraisDepot> findDetailFraisDepotByFraisDepotAndDate(int code,Date date) {
		
		Query query= session.createQuery("select c from DetailFraisDepot c where fraisDepot.id=:code and dateoperation=:date");
		query.setParameter("code", code);
		query.setParameter("date", date);
		return query.list();
		
		}
	
	

	



}

package dao.daoImplManager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import main.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.CoutMachineDAO;
import dao.daoManager.FormeDAO;
import dao.daoManager.GrammageBoxDAO;
import dao.entity.Articles;
import dao.entity.CategorieMp;
import dao.entity.CoutMachine;
import dao.entity.DetailEstimation;
import dao.entity.GrammageBox;
import dao.entity.forme;


public class CoutMachineDAOImpl implements CoutMachineDAO {
	Session session=HibernateUtil.openSession();
		

	public void add(CoutMachine e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public CoutMachine edit(CoutMachine e) {
		
	session.beginTransaction();
	CoutMachine p= (CoutMachine)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		CoutMachine p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<CoutMachine> findAll() {
		return session.createQuery("select c from CoutMachine c").list();
		}

	public CoutMachine findById(int id) {
		return (CoutMachine)session.get(CoutMachine.class, id);
		}

	
	public CoutMachine CoutMachineParDateParTonnageParForme(Date date , BigDecimal tonnage,forme forme, CategorieMp categorie)
	{
		
		Query query= session.createQuery("select c from CoutMachine c where c.date in (select max(p.date) from CoutMachine p where p.formeParBox.forme.id=:forme and p.date<=:date) and c.tonnage in (select min(d.tonnage) from CoutMachine d where d.tonnage>=:tonnage and d.categorie.id=:categorie and d.formeParBox.forme.id=:forme) and c.categorie.id=:categorie and c.formeParBox.forme.id=:forme");
		query.setParameter("tonnage", tonnage);
		query.setParameter("date", date);
		query.setParameter("forme", forme.getId());
		query.setParameter("categorie", categorie.getId());
		
		return (CoutMachine) query.uniqueResult();	
		
		
	}
	
	public CoutMachine CoutMachineParDateParMaxTonnageParForme(Date date , BigDecimal tonnage,forme forme,CategorieMp categorie )
	{
		
		Query query= session.createQuery("select c from CoutMachine c where c.date in (select max(p.date) from CoutMachine p where p.formeParBox.forme.id=:forme and p.date<=:date) and c.tonnage in (select max(d.tonnage) from CoutMachine d where d.tonnage<=:tonnage and d.categorie.id=:categorie and d.formeParBox.forme.id=:forme) and c.categorie.id=:categorie and c.formeParBox.forme.id=:forme");
		query.setParameter("tonnage", tonnage);
		query.setParameter("date", date);
		query.setParameter("forme", forme.getId());
		query.setParameter("categorie", categorie.getId());
		return (CoutMachine) query.uniqueResult();	
		
		
	}

}

package dao.daoImplManager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import main.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.GrammageBoxDAO;
import dao.daoManager.PrimeAnciennteDAO;
import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.GrammageBox;
import dao.entity.PrimeAnciennte;
import dao.entity.Production;


public class PrimeAnciennteDAOImpl implements PrimeAnciennteDAO {
	Session session=HibernateUtil.openSession();
		

	public void add(PrimeAnciennte e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public PrimeAnciennte edit(PrimeAnciennte e) {
		
	session.beginTransaction();
	PrimeAnciennte p= (PrimeAnciennte)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		PrimeAnciennte p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<PrimeAnciennte> findAll() {
		return session.createQuery("select c from PrimeAnciennte c").list();
		}

	public PrimeAnciennte findById(int id) {
		return (PrimeAnciennte)session.get(PrimeAnciennte.class, id);
		}

 
	
 
	
	public long maxId() {
        long id =0;
		Query query= session.createQuery("select count(*) from PrimeAnciennte");
		
		if(query.uniqueResult()==null)
			id=0;
		else 
			id=(Long) query.uniqueResult();
		
		return id;
    }
	
	
public 	PrimeAnciennte PrimeByMinByMaxByDatePrim(BigDecimal annee , Date datePrime  )
{
	
	
	Query query= session.createQuery("select p from PrimeAnciennte p where p.anneMax>=:annee and p.anneMin<:annee  and p.datePrime in (select max(pd.datePrime) from PrimeAnciennte pd where pd.datePrime<=:datePrime)");
	query.setParameter("annee", annee);
	query.setParameter("datePrime", datePrime);
	
	return (PrimeAnciennte) query.uniqueResult();
	
	
}
	
	


}

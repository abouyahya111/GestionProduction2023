package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.Constantes;
import util.HibernateUtil;
import dao.daoManager.DetailEmployeMenageDAO;
import dao.daoManager.DetailProdGenDAO;
import dao.entity.CategorieMp;
import dao.entity.DetailEmployeMenage;
import dao.entity.DetailProdGen;
import dao.entity.DetailProduction;
import dao.entity.DetailProductionMP;
import dao.entity.Employe;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.SubCategorieMp;

public class DetailEmployeMenageDAOImpl implements DetailEmployeMenageDAO {
	
	Session session=HibernateUtil.openSession();

	public void add(DetailEmployeMenage e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public DetailEmployeMenage edit(DetailEmployeMenage e) {
		
	session.beginTransaction();
	DetailEmployeMenage p= (DetailEmployeMenage)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailEmployeMenage p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	@SuppressWarnings("unchecked")
	public List<DetailEmployeMenage> findAll() {
		return session.createQuery("select c from DetailEmployeMenage c").list();
		}

	public DetailEmployeMenage findById(int id) {
		return (DetailEmployeMenage)session.get(DetailEmployeMenage.class, id);
		}


	@Override
	public DetailEmployeMenage findByDateByEmploye(Date dateTravail,Employe employe) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select d from DetailEmployeMenage d where employe.id=:employe and dateTravail=:dateTravail");
				query.setParameter("employe", employe.getId());
				query.setParameter("dateTravail", dateTravail);
				
				return (DetailEmployeMenage) query.uniqueResult();
}

	@Override
	public List<Object[]> MaxDateTravailMenege() 
	{
		
		Query query= session.createQuery("select id, max(d.dateTravail) from DetailEmployeMenage d ");
		
		
		return query.list();
		
		
	}
	
	
	@Override
	public List<DetailEmployeMenage> ListHeursDetailEmployeMenageParDepot(Date dateDebut,Date dateFin, int depot , String requet) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from DetailEmployeMenage c where c.dateTravail between :dateDebut and :dateFin and c.employe.depot.id=:depot "+requet);
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("depot", depot);
				return query.list();
}
	
	public List<DetailEmployeMenage> ListHeursDetailEmployeMenage(Date dateDebut,Date dateFin, String matricule) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from DetailEmployeMenage c where c.dateTravail between :dateDebut and :dateFin and c.employe.matricule=:matricule order by c.dateTravail");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("matricule", matricule);
				
				return query.list();
	}	

}

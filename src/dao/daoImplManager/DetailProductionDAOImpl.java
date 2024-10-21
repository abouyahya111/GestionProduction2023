package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.Constantes;
import util.HibernateUtil;
import dao.daoManager.DetailProdGenDAO;
import dao.daoManager.DetailProductionDAO;
import dao.entity.DetailProdGen;
import dao.entity.DetailProduction;
import dao.entity.Employe;
import dao.entity.Production;

public class DetailProductionDAOImpl implements DetailProductionDAO {
	Session session=HibernateUtil.openSession();

	public void add(DetailProduction e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public DetailProduction edit(DetailProduction e) {
		
	session.beginTransaction();
	DetailProduction p= (DetailProduction)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailProduction p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	@SuppressWarnings("unchecked")
	public List<DetailProduction> findAll() {
		return session.createQuery("select c from DetailProduction c").list();
		}

	public DetailProduction findById(int id) {
		return (DetailProduction)session.get(DetailProduction.class, id);
		}
	
	public List<DetailProduction> ListHeursDetailProduction(Date dateDebut,Date dateFin, String matricule  ,String statut) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from DetailProduction c where c.production.date between :dateDebut and :dateFin and c.employe.matricule=:matricule  and c.production.statut=:statut order by c.production.date");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("matricule", matricule);
				query.setParameter("statut", statut);
				return query.list();
	}
	
	
	public DetailProduction EmployeDetailProduction(Date dateDebut, String matricule  ,String statut) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from DetailProduction c where c.production.date =:dateDebut and c.employe.matricule=:matricule  and c.production.statut=:statut order by c.production.date");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("matricule", matricule);
				query.setParameter("statut", statut);
				return (DetailProduction) query.uniqueResult();
	}
	
	
	public List<DetailProduction> ListEmployeDetailProductionByProduction(Production production) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from DetailProduction c where c.production.id =:production");
				query.setParameter("production", production.getId());
			
				return query.list();
	}
	
	public DetailProduction  DetailProductionByProductionByEmploye(Production production, Employe employe) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from DetailProduction c where c.production.id=:production and c.employe.id=:employe");
				query.setParameter("production", production.getId());
				query.setParameter("employe", employe.getId());
			 
				return (DetailProduction) query.uniqueResult();
	}
	
	public void  SupprimerDetailProductionByProduction(Production production) {
		
		// TODO Auto-generated method stub
		session.beginTransaction();
				Query query= session.createQuery("delete from DetailProduction c where c.production.id=:production");
				query.setParameter("production", production.getId());
				int i=query.executeUpdate();
				System.out.println(i);
		 session.getTransaction().commit();
	}
	
	
	
	public List<DetailProduction> ListHeursDetailProductionParDepot(Date dateDebut,Date dateFin, int depot,String statut , String requet) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from DetailProduction c where c.production.date between :dateDebut and :dateFin and c.employe.depot.id=:depot and c.production.statut=:statut "+requet);
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("depot", depot);
				query.setParameter("statut", statut);
				return query.list();
	}
	
	public List<DetailProduction> ListHeursDetailProductionParDepotparJour(Date dateDebut,Date dateFin, String matricule, int depot,String statut) {
		
		// TODO Auto-generated method stub
		
		Query query=null;
		
		if(matricule.equals(""))
		{
			
				 query= session.createQuery("select c from DetailProduction c where c.production.date between :dateDebut and :dateFin and c.employe.depot.id=:depot and c.production.statut=:statut");
					query.setParameter("dateDebut", dateDebut);
					query.setParameter("dateFin", dateFin);
					query.setParameter("depot", depot);
					query.setParameter("statut", statut);
			
			
			
			
		}else
		{
			
				
				 query= session.createQuery("select c from DetailProduction c where c.production.date between :dateDebut and :dateFin and c.employe.matricule=:matricule and c.employe.depot.id=:depot and c.production.statut=:statut");
					query.setParameter("dateDebut", dateDebut);
					query.setParameter("dateFin", dateFin);
					query.setParameter("depot", depot);
					query.setParameter("statut", statut);
					query.setParameter("matricule", matricule);
			
			
		}
		
		
		
				
				return query.list();
	}
	

}

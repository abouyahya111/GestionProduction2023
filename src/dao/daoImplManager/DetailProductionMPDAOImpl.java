package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.Constantes;
import util.HibernateUtil;
import dao.daoManager.DetailProdGenDAO;
import dao.daoManager.DetailProductionDAO;
import dao.daoManager.DetailProductionMPDAO;
import dao.entity.DetailProdGen;
import dao.entity.DetailProdRes;
import dao.entity.DetailProduction;
import dao.entity.DetailProductionMP;
import dao.entity.ProductionMP;

public class DetailProductionMPDAOImpl implements DetailProductionMPDAO {
	Session session=HibernateUtil.openSession();

	public void add(DetailProductionMP e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public DetailProductionMP edit(DetailProductionMP e) {
		
	session.beginTransaction();
	DetailProductionMP p= (DetailProductionMP)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailProductionMP p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}



	public DetailProductionMP findById(int id) {
		return (DetailProductionMP)session.get(DetailProductionMP.class, id);
		}
	
	public List<DetailProductionMP> ListHeursDetailProductionMP(Date dateDebut,Date dateFin, String matricule,String statut) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from DetailProductionMP c where c.productionMP.dateProduction between :dateDebut and :dateFin and c.employe.matricule=:matricule and c.productionMP.statut=:statut order by c.productionMP.dateProduction");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("matricule", matricule);
				query.setParameter("statut", statut);
				return query.list();
	}
	
	public DetailProductionMP EmployeDetailProductionMP(Date dateDebut, String matricule,String statut) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from DetailProductionMP c where c.productionMP.dateProduction between =:dateDebut and c.employe.matricule=:matricule and c.productionMP.statut=:statut order by c.productionMP.dateProduction");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("matricule", matricule);
				query.setParameter("statut", statut);
				return (DetailProductionMP) query.uniqueResult();
	}
	
	public List<DetailProductionMP> ListHeursDetailProductionMPParDepot(Date dateDebut,Date dateFin, int depot  ,String statut , String requet) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from DetailProductionMP c where c.productionMP.dateProduction between :dateDebut and :dateFin and c.employe.depot.id=:depot and c.productionMP.statut=:statut"+requet);
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("depot", depot);
				query.setParameter("statut", statut);
				
				return query.list();
	}
	
	
	
	public List<DetailProductionMP> ListHeursDetailProductionMPParDepotParJour(Date dateDebut,Date dateFin,String matricule, int depot  ,String statut) {
		
		// TODO Auto-generated method stub
		Query query=null;
		if(matricule.equals(""))
		{
			
				
				query=session.createQuery("select c from DetailProductionMP c where c.productionMP.dateProduction between :dateDebut and :dateFin and c.employe.depot.id=:depot and c.productionMP.statut=:statut");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("depot", depot);
				query.setParameter("statut", statut);
			
			 
			
		}else
		{
			
				
				 query=session.createQuery("select c from DetailProductionMP c where c.productionMP.dateProduction between :dateDebut and :dateFin and c.employe.matricule=:matricule and c.employe.depot.id=:depot and c.productionMP.statut=:statut ");
					query.setParameter("dateDebut", dateDebut);
					query.setParameter("dateFin", dateFin);
					query.setParameter("depot", depot);
					query.setParameter("statut", statut);
					query.setParameter("matricule", matricule);
			
			
			
		}
		
				
				return query.list();
	}
	
	
	
	public List<DetailProductionMP> ListHeursDetailProductionMPParProductionMP(ProductionMP productionMP) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from DetailProductionMP c where c.productionMP.id=:productionMP");
				query.setParameter("productionMP", productionMP.getId());
				
				
				return query.list();
	}
	
	
	
	

}

package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.Constantes;
import util.HibernateUtil;
import dao.daoManager.DetailProdGenDAO;
import dao.entity.DetailProdGen;
import dao.entity.DetailProduction;
import dao.entity.Employe;
import dao.entity.Production;

public class DetailProdGenDAOImpl implements DetailProdGenDAO {
	Session session=HibernateUtil.openSession();

	public void add(DetailProdGen e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public DetailProdGen edit(DetailProdGen e) {
		
	session.beginTransaction();
	DetailProdGen p= (DetailProdGen)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailProdGen p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	@SuppressWarnings("unchecked")
	public List<DetailProdGen> findAll() {
		return session.createQuery("select c from DetailProdGen c").list();
		}

	public DetailProdGen findById(int id) {
		return (DetailProdGen)session.get(DetailProdGen.class, id);
		}
	

	@Override
	public List<DetailProdGen> findByDateProdPeriode(Date dateProd,String periode) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select d from DetailProdGen d where dateProd=:dateProd and periode=:periode");
				query.setParameter("dateProd", dateProd);
				query.setParameter("periode", periode);
				
				return query.list();
}

	@Override
	public DetailProdGen findByDateProdPeriodeEmploye(Date dateProd,
			String periode, int idEmploye) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select d from DetailProdGen d where dateProd=:dateProd and periode=:periode and employe.id=:idEmploye");
				query.setParameter("dateProd", dateProd);
				query.setParameter("periode", periode);
				query.setParameter("idEmploye", idEmploye);
				
				return (DetailProdGen) query.uniqueResult();
}

	public List<DetailProdGen> ListHeursDetailProdGen(Date dateDebut,Date dateFin, String matricule,String statut) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from DetailProdGen c where c.productionGen.date between :dateDebut and :dateFin and c.employe.matricule=:matricule and c.productionGen.statut=:statut order by c.productionGen.date");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("matricule", matricule);
				query.setParameter("statut", statut);
				return query.list();
	}
	
	public DetailProdGen EmployeDetailProdGen(Date dateDebut, String matricule,String statut) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from DetailProdGen c where c.productionGen.date =:dateDebut  and c.employe.matricule=:matricule and c.productionGen.statut=:statut order by c.productionGen.date");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("matricule", matricule);
				query.setParameter("statut", statut);
				return (DetailProdGen) query.uniqueResult();
	}
	
	
	
	public DetailProdGen  DetailProdGenByProductionByEmploye(Production production, Employe employe) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from DetailProdGen c where c.productionGen.id=:production and c.employe.id=:employe");
				query.setParameter("production", production.getId());
				query.setParameter("employe", employe.getId());
			 
				return (DetailProdGen) query.uniqueResult();
	}
	
	

	public List<DetailProdGen> ListHeursDetailProdGenParDepot(Date dateDebut,Date dateFin,  int depot ,String statut, String requet) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from DetailProdGen c where c.productionGen.date between :dateDebut and :dateFin and c.employe.depot.id=:depot and c.productionGen.statut=:statut"+ requet);
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("depot", depot);
				query.setParameter("statut", statut);
				return query.list();
	}
	
	public List<DetailProdGen> ListHeursDetailProdGenParDepotParJour(Date dateDebut,Date dateFin, String matricule,  int depot ,String statut) {
		
		// TODO Auto-generated method stub
		Query query=null;
		if(matricule.equals(""))
		{
			
				 query= session.createQuery("select c from DetailProdGen c where c.productionGen.date between :dateDebut and :dateFin and c.employe.depot.id=:depot and c.productionGen.statut=:statut");
					query.setParameter("dateDebut", dateDebut);
					query.setParameter("dateFin", dateFin);
					query.setParameter("depot", depot);
					query.setParameter("statut", statut);
				
			
			
			
		}else
		{
			
			
				
				query= session.createQuery("select c from DetailProdGen c where c.productionGen.date between :dateDebut and :dateFin and c.employe.matricule=:matricule and c.employe.depot.id=:depot and c.productionGen.statut=:statut");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("depot", depot);
				query.setParameter("statut", statut);
				query.setParameter("matricule", matricule);
			
			 
				
		}
		
		
		
		return query.list();
				
	}
	
	
	public void  SupprimerDetailProductionEmballageByProduction(Production production) {
		
		// TODO Auto-generated method stub
		session.beginTransaction();
				Query query= session.createQuery("delete from DetailProdGen c where c.productionGen.id=:production");
				query.setParameter("production", production.getId());
				int i=query.executeUpdate();
				System.out.println(i);
		 session.getTransaction().commit();
	}
	
	
	

}

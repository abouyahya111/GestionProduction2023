package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.EmployeDAO;
import dao.entity.Employe;
import dao.entity.TypeResEmploye;

public class EmployeDAOImpl implements EmployeDAO {
	Session session=HibernateUtil.openSession();

	public void add(Employe e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public Employe edit(Employe e) {
		
	session.beginTransaction();
	Employe p= (Employe)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		Employe p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<Employe> findAll() {
		Query query= session.createQuery("select c from Employe c where actif=:actif");
		query.setParameter("actif", true);
		
		return query.list();
		}
	
	
	
	public List<Employe> findAllEmploye() {
		Query query= session.createQuery("select c from Employe c");
		
		
		return query.list();
		}

	
	
	public List<Employe> findByDepot(String depot) {
		Query query= session.createQuery("select c from Employe c where actif=:actif and c.depot.code=:depot order by nom ,matricule ");
		query.setParameter("actif", true);
		query.setParameter("depot", depot);
		return query.list();
		}
	
	
	
	public List<Employe> findByDepotByResponsabilite(String depot , TypeResEmploye typeresemploye) {
		Query query= session.createQuery("select c from Employe c where actif=:actif and c.depot.code=:depot and c.typeResEmploye.id=:typeresemploye order by nom ,matricule ");
		query.setParameter("actif", true);
		query.setParameter("depot", depot);
		query.setParameter("typeresemploye", typeresemploye.getId());
		return query.list();
		}
	
	
	
	
	public List<Employe> findByDepotActifEtSalarie(String depot) {
		Query query= session.createQuery("select c from Employe c where c.depot.code=:depot order by nom ,matricule ");
		
		query.setParameter("depot", depot);
		return query.list();
		}
	
	
	
	public List<Employe> findByDepotByNomByResponsabilteByActif(String depot , String requete) {
		
		Query query=null;
		
		
			 query= session.createQuery("select c from Employe c where  c.depot.code=:depot "+requete+" order by nom ,matricule ");
			//	query.setParameter("actif", true);
				query.setParameter("depot", depot);
				
		
	
		return query.list();
		
		
		}
	

	public Employe findById(int id) {
		return (Employe)session.get(Employe.class, id);
		}

	@Override
	public Employe findByCode(String code, String numDossier,int depot) {
		// TODO Auto-generated method stub
		
		Employe articles= new Employe();
		Query query= session.createQuery("select c from Employe c where (numDossier=:numDossier or matricule=:code) and actif=:actif and ID_DEPOT=:depot");
		query.setParameter("code", code);
		query.setParameter("numDossier", numDossier);
		query.setParameter("actif", true);
		query.setParameter("depot", depot);
		
		articles= (Employe) query.uniqueResult();
		
		return articles;
	}
	
	
	
	
	@Override
	public Employe findByCodeEmploye(String code, String numDossier,int depot) {
		// TODO Auto-generated method stub
		
		Employe articles= new Employe();
		Query query= session.createQuery("select c from Employe c where (numDossier=:numDossier or matricule=:code)  and ID_DEPOT=:depot");
		query.setParameter("code", code);
		query.setParameter("numDossier", numDossier);
		//query.setParameter("actif", true);
		query.setParameter("depot", depot);
		
		articles= (Employe) query.uniqueResult();
		
		return articles;
	}
	
	
	
	
	@Override
	public Employe findByCINE(String cine,int depot) {
		// TODO Auto-generated method stub
		
		Employe employe= new Employe();
		Query query= session.createQuery("select c from Employe c where c.cin=:cine and ID_DEPOT=:depot");
		query.setParameter("cine", cine);		
		//query.setParameter("actif", true);
		query.setParameter("depot", depot);		
		employe= (Employe) query.uniqueResult();
		
		return employe;
	}
	
	
	
	
	public List<Employe> findEmployeByType(String codeType,String depot) {
		Query query= session.createQuery("select c from Employe c where typeResEmploye.code=:codeType and actif=:actif and Employe.Depot.code=:depot");
		query.setParameter("actif", true);
		query.setParameter("codeType",codeType);
		query.setParameter("depot", depot);
		return query.list();
		}
	
	public List<Employe> findAutreEmployeByType(String codeType) {
		Query query= session.createQuery("select c from Employe c where typeResEmploye.code=:codeType and actif=:actif and c.equipe is null");
		query.setParameter("actif", true);
		query.setParameter("codeType",codeType);
		
		return query.list();
		}
	
	public List<Employe> findAutreEmploye() {
		Query query= session.createQuery("select c from Employe c where actif=:actif and c.equipe is null");
		query.setParameter("actif", true);
		
		return query.list();
		}
	
	
	
	public List<Employe> findByNUmDossier_Matricule_Nom(String numdossier,String matricule,String nom) {
		Query query= session.createQuery("select c from Employe c where numDossier like:numdossier and matricule like:matricule and nom like:nom and actif=:actif");
		query.setParameter("numdossier", numdossier.trim()+"%");
		query.setParameter("matricule", matricule.trim()+"%");
		query.setParameter("nom", nom.trim()+"%");
		query.setParameter("actif", true);
		return query.list();
		}
	
	public int maxIdEmploye() {
        int id =0;
		Query query= session.createQuery("select max(id) from Employe");
		
		if(query.uniqueResult()==null)
			id=1;
		else 
			id= (int)query.uniqueResult();
		
		return id;
    }


}

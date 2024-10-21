package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.DetailEstimationDAO;
import dao.daoManager.DetailEstimationMPDAO;
import dao.daoManager.DetailEstimationPromoDAO;
import dao.daoManager.DetailManqueDechetFournisseurCartonDAO;
import dao.daoManager.DetailManqueDechetFournisseurDAO;

import dao.entity.DetailEstimation;
import dao.entity.DetailEstimationMP;
import dao.entity.DetailEstimationPromo;
import dao.entity.DetailManqueDechetFournisseur;
import dao.entity.DetailManqueDechetFournisseurCarton;
import dao.entity.FournisseurAdhesive;
import dao.entity.FournisseurMP;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.Production;

public class DetailManqueDechetFournisseurCartonDAOImpl implements DetailManqueDechetFournisseurCartonDAO {
	Session session=HibernateUtil.openSession();

	public void add(DetailManqueDechetFournisseurCarton e) {
	
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		
		//return p;
	}

	public DetailManqueDechetFournisseurCarton edit(DetailManqueDechetFournisseurCarton e) {
		
		session.beginTransaction();
		DetailManqueDechetFournisseurCarton p= (DetailManqueDechetFournisseurCarton)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailManqueDechetFournisseurCarton p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<DetailManqueDechetFournisseurCarton> findAll() {
		
		Query query= session.createQuery("select c from DetailManqueDechetFournisseurCarton c");
		List<DetailManqueDechetFournisseurCarton> list=query.list();
	
		return list;
				
		}

	public DetailManqueDechetFournisseurCarton findById(int id) {
		
		DetailManqueDechetFournisseurCarton detailManqueDechetFournisseurCarton= (DetailManqueDechetFournisseurCarton)session.get(DetailManqueDechetFournisseurCarton.class, id);
		
		return detailManqueDechetFournisseurCarton;
		}

	public List<DetailManqueDechetFournisseurCarton>  findByIdMP(int id) {
		
		Query query= session.createQuery("select c from DetailManqueDechetFournisseurCarton c where id_mat_pre=:idmatiere");
		query.setParameter("idmatiere", id);
		List<DetailManqueDechetFournisseurCarton> list=query.list();
		
		return list;
		
		
		
		
		}
	public List<DetailManqueDechetFournisseurCarton>  findByIdMPByManqueDechetFournisseur(int idmanquedechetfournisseur ,int idmp) {
		
		Query query= session.createQuery("select c from DetailManqueDechetFournisseurCarton c where id_mat_pre=:idmatiere and c.manquedechetfournisseurcarton.id=:idmanquedechetfournisseur");
		query.setParameter("idmatiere", idmp);
		query.setParameter("idmanquedechetfournisseur", idmanquedechetfournisseur);
		
		
		return query.list();
		
		
		
		
		}
	
	public List<DetailManqueDechetFournisseurCarton>  findByManqueDechetFournisseur(int idmanquedechetfournisseur) {
		
		Query query= session.createQuery("select c from DetailManqueDechetFournisseurCarton c where c.manquedechetfournisseurcarton.id=:idmanquedechetfournisseur");
	
		query.setParameter("idmanquedechetfournisseur", idmanquedechetfournisseur);
		
		
		return query.list();
		
		
		}
	
	
	@Override
	public List<Object[]> listeDetailManqueDechetFournisseurGroupByMPByFournisseur( String requete) {
		// TODO Auto-generated method stub
		Query query=null;
		
		 
				query= session.createQuery("select matierePremier.code,matierePremier.nom,fourniseurAdhesive.codeFournisseur, sum(d.quantiteManque), sum(d.quantiteDechet) FROM DetailManqueDechetFournisseurCarton d where d.id>0 "+requete+ "  GROUP BY matierePremier.code ,fourniseurAdhesive.codeFournisseur");
 
			 
		
		return query.list();

	}
	
	
	@Override
	public List<DetailManqueDechetFournisseurCarton>listeDetailManqueDechetFournisseurByMPByFournisseur(MatierePremier mp , FournisseurAdhesive fournisseur , String etat) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
				query= session.createQuery("select d FROM DetailManqueDechetFournisseurCarton d where d.matierePremier.id=:mp and d.fourniseurAdhesive.id=:fournisseur and d.manquedechetfournisseurcarton.etat=:etat ");

				query.setParameter("mp", mp.getId());
				query.setParameter("fournisseur", fournisseur.getId());
				query.setParameter("etat", etat);
	
			
			
		
		
		return query.list();

	}
	
	
	
	public void ViderSession()
	{
		if(session!=null)
		{
			session.clear();
		}
	}


}

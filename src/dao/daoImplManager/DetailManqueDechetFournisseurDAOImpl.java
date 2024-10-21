package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.Constantes;
import util.HibernateUtil;
import dao.daoManager.DetailEstimationDAO;
import dao.daoManager.DetailEstimationMPDAO;
import dao.daoManager.DetailEstimationPromoDAO;
import dao.daoManager.DetailManqueDechetFournisseurDAO;

import dao.entity.DetailEstimation;
import dao.entity.DetailEstimationMP;
import dao.entity.DetailEstimationPromo;
import dao.entity.DetailManqueDechetFournisseur;
import dao.entity.FournisseurMP;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.Production;

public class DetailManqueDechetFournisseurDAOImpl implements DetailManqueDechetFournisseurDAO {
	Session session=HibernateUtil.openSession();

	public void add(DetailManqueDechetFournisseur e) {
	
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		
		//return p;
	}

	public DetailManqueDechetFournisseur edit(DetailManqueDechetFournisseur e) {
		
		session.beginTransaction();
		DetailManqueDechetFournisseur p= (DetailManqueDechetFournisseur)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailManqueDechetFournisseur p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<DetailManqueDechetFournisseur> findAll() {
		
		Query query= session.createQuery("select c from DetailManqueDechetFournisseur c");
		List<DetailManqueDechetFournisseur> list=query.list();
	
		return list;
				
		}

	public DetailManqueDechetFournisseur findById(int id) {
		
		DetailManqueDechetFournisseur detailManqueDechetFournisseur= (DetailManqueDechetFournisseur)session.get(DetailManqueDechetFournisseur.class, id);
		
		return detailManqueDechetFournisseur;
		}

	public List<DetailManqueDechetFournisseur>  findByIdMP(int id) {
		
		Query query= session.createQuery("select c from DetailManqueDechetFournisseur c where id_mat_pre=:idmatiere");
		query.setParameter("idmatiere", id);
		List<DetailManqueDechetFournisseur> list=query.list();
		
		return list;
		
		
		
		
		}
	public List<DetailManqueDechetFournisseur>  findByIdMPByManqueDechetFournisseur(int idmanquedechetfournisseur ,int idmp) {
		
		Query query= session.createQuery("select c from DetailManqueDechetFournisseur c where id_mat_pre=:idmatiere and c.manquedechetfournisseur.id=:idmanquedechetfournisseur");
		query.setParameter("idmatiere", idmp);
		query.setParameter("idmanquedechetfournisseur", idmanquedechetfournisseur);
		
		
		return query.list();
		
		
		
		
		}
	
	public List<DetailManqueDechetFournisseur>  findByIdMPByManqueDechetFournisseurByFournisseur(int idmanquedechetfournisseur ,int idmp, FournisseurMP fournisseurMP) {
		
		if(idmanquedechetfournisseur==36)
		{
			System.out.println("ok");
		}
		
		Query query= session.createQuery("select c from DetailManqueDechetFournisseur c where id_mat_pre=:idmatiere and c.manquedechetfournisseur.id=:idmanquedechetfournisseur and fourniseur.id=:idfournisseur");
		query.setParameter("idmatiere", idmp);
		query.setParameter("idmanquedechetfournisseur", idmanquedechetfournisseur);
		query.setParameter("idfournisseur", fournisseurMP.getId());
		
		
		return query.list();
		
		
		
		
		}
	
	
 
	
	public List<DetailManqueDechetFournisseur>  findByManqueDechetFournisseur(int idmanquedechetfournisseur) {
		
		Query query= session.createQuery("select c from DetailManqueDechetFournisseur c where c.manquedechetfournisseur.id=:idmanquedechetfournisseur");
	
		query.setParameter("idmanquedechetfournisseur", idmanquedechetfournisseur);
		
		
		return query.list();
		
		
		}
	
	
	
	
	public List<DetailManqueDechetFournisseur>  findByManqueDechetFournisseurByCategorieEmballage(int idmanquedechetfournisseur) {
		
		Query query= session.createQuery("select c from DetailManqueDechetFournisseur c where c.manquedechetfournisseur.id=:idmanquedechetfournisseur and c.matierePremier.categorieMp.subCategorieMp.code<>:subcategoriethe");
	
		query.setParameter("idmanquedechetfournisseur", idmanquedechetfournisseur);
		query.setParameter("subcategoriethe", Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE);
		
		
		return query.list();
		
		
		}
	
	public List<DetailManqueDechetFournisseur>  findByManqueDechetFournisseurByCategorieThe(int idmanquedechetfournisseur) {
		
		Query query= session.createQuery("select c from DetailManqueDechetFournisseur c where c.manquedechetfournisseur.id=:idmanquedechetfournisseur and c.matierePremier.categorieMp.subCategorieMp.code=:subcategoriethe");
	
		query.setParameter("idmanquedechetfournisseur", idmanquedechetfournisseur);
		query.setParameter("subcategoriethe", Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE);
		
		
		return query.list();
		
		
		}
	
	
	@Override
	public List<Object[]> listeDetailManqueDechetFournisseurGroupByMPByFournisseur(MatierePremier mp , FournisseurMP fournisseur , String etat ,String type,Magasin magasin ) {
		// TODO Auto-generated method stub
		Query query=null;
		
			if(mp!=null && fournisseur !=null)
			{
				query= session.createQuery("select matierePremier.code,matierePremier.nom,fourniseur.codeFournisseur, sum(d.quantiteManque), sum(d.quantiteDechet), sum(d.quantitePlus) FROM DetailManqueDechetFournisseur d where d.matierePremier.id=:mp and d.fourniseur.id=:fournisseur and magasinDechet.id=:magasin and d.manquedechetfournisseur.etat=:etat and d.manquedechetfournisseur.type=:type GROUP BY matierePremier.code ,fourniseur.codeFournisseur");

				query.setParameter("mp", mp.getId());
				query.setParameter("fournisseur", fournisseur.getId());
				query.setParameter("etat", etat);
				query.setParameter("type", type);
				query.setParameter("magasin", magasin.getId());
			
			}else if(mp==null && fournisseur !=null)
			{
				
				query= session.createQuery("select matierePremier.code,matierePremier.nom,fourniseur.codeFournisseur, sum(d.quantiteManque), sum(d.quantiteDechet), sum(d.quantitePlus) FROM DetailManqueDechetFournisseur d where  d.fourniseur.id=:fournisseur and magasinDechet.id=:magasin and d.manquedechetfournisseur.etat=:etat and d.manquedechetfournisseur.type=:type  GROUP BY matierePremier.code ,fourniseur.codeFournisseur");

				query.setParameter("etat", etat);
				query.setParameter("fournisseur", fournisseur.getId());
				query.setParameter("type", type);
				query.setParameter("magasin", magasin.getId());
			}else if(mp!=null && fournisseur ==null)
			{
				
				query= session.createQuery("select matierePremier.code,matierePremier.nom,fourniseur.codeFournisseur, sum(d.quantiteManque), sum(d.quantiteDechet), sum(d.quantitePlus) FROM DetailManqueDechetFournisseur d where d.matierePremier.id=:mp and magasinDechet.id=:magasin and d.manquedechetfournisseur.etat=:etat and d.manquedechetfournisseur.type=:type GROUP BY matierePremier.code ,fourniseur.codeFournisseur");
				query.setParameter("etat", etat);
				query.setParameter("mp", mp.getId());
				query.setParameter("type", type);
				query.setParameter("magasin", magasin.getId());
				
			}
			
			
		
		
		return query.list();

	}
	
	
	
	public List<DetailManqueDechetFournisseur>listeDetailManqueDechetFournisseurByMPByFournisseur(MatierePremier mp , FournisseurMP fournisseur , String etat ,String type,Magasin magasin) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
				query= session.createQuery("select d FROM DetailManqueDechetFournisseur d where d.matierePremier.id=:mp and magasinDechet.id=:magasin and d.fourniseur.id=:fournisseur and d.manquedechetfournisseur.etat=:etat and d.manquedechetfournisseur.type=:type");

				query.setParameter("mp", mp.getId());
				query.setParameter("fournisseur", fournisseur.getId());
				query.setParameter("etat", etat);
				query.setParameter("type", type);
				query.setParameter("magasin", magasin.getId());
			
		
		
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

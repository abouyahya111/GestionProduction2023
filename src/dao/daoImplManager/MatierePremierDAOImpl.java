package dao.daoImplManager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.Constantes;
import util.HibernateUtil;
import dao.daoManager.MatierePremiereDAO;
import dao.entity.CategorieMp;
import dao.entity.Client;
import dao.entity.MatierePremier;
import dao.entity.SubCategorieMp;

public class MatierePremierDAOImpl implements MatierePremiereDAO {
	Session session=HibernateUtil.openSession();

	public void add(MatierePremier e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public MatierePremier edit(MatierePremier e) {
		
	session.beginTransaction();
	MatierePremier p= (MatierePremier)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		MatierePremier p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<MatierePremier> findAll() {
		return session.createQuery("select c from MatierePremier c where c.code not like '"+Constantes.CODE_AUTRES_DECHET_MATIERE_PREMIERE_LIBELLE+"%' ").list();
		}
	
	public List<MatierePremier> findAllDechetMP() {
		return session.createQuery("select c from MatierePremier c where c.code like '"+Constantes.CODE_AUTRES_DECHET_MATIERE_PREMIERE_LIBELLE+"%' order by c.nom").list();
		}

	public MatierePremier findById(int id) {
		return (MatierePremier)session.get(MatierePremier.class, id);
		}

	@Override
	public MatierePremier findByCode(String code) {
		// TODO Auto-generated method stub
		MatierePremier matierPremiere= new MatierePremier();
		Query query= session.createQuery("select c from MatierePremier c where code=:code");
		query.setParameter("code", code);
		
		matierPremiere= (MatierePremier) query.uniqueResult();
		
		return matierPremiere;
	}

	@Override
	public List<MatierePremier> findMatierePremierSaufCatTHE() {
		// TODO Auto-generated method stub
		return session.createQuery("select c from MatierePremier c where categorieMp.id <>1 and categorieMp.id <> 2").list();
	}

	@Override
	public List<CategorieMp> listeCategorieTHE() {
		// TODO Auto-generated method stub
		return session.createQuery("select c from CategorieMp c where code ='CH001' or code = 'HB001'").list();
	}

	@Override
	public List<MatierePremier> listeMatierePremierByCategorie(String codeCat) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from MatierePremier c where categorieMp.code=:codeCat");
		query.setParameter("codeCat", codeCat);
		
		return query.list();
		
	}
	
	
	@Override
	public List<MatierePremier> listeMatierePremierByClient(Client client) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from MatierePremier c where client.id=:client");
		query.setParameter("client", client.getId());
		
		return query.list();
		
	}
	
	@Override
	public List<MatierePremier> listeMatierePremierByidcategorie(int idCat) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from MatierePremier c where id_cat=:idCat");
		query.setParameter("idCat", idCat);
		
		return query.list();
		
	}
	
	
	@Override
	public List<MatierePremier> listeMatierePremierByidSubcategorie(int idsubCat) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from MatierePremier c where categorieMp.subCategorieMp.id=:idsubCat");
		query.setParameter("idsubCat", idsubCat);
		
		return query.list();
		
	}
	
	
	public List<MatierePremier> listeMatierePremierByReq(String req) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from MatierePremier c where c.id>0  "+req);
	
		
		return query.list();
		
	}
	
	@Override
	public List<MatierePremier> listeMatierePremierBycategorieBySubCategorieByMP(SubCategorieMp subcategorie , CategorieMp categorie , MatierePremier mp) {
		// TODO Auto-generated method stub
		
		Query query=null;
		if(subcategorie!=null && categorie== null && mp==null)
		{
			query= session.createQuery("select c from MatierePremier c where categorieMp.subCategorieMp.id=:subcategorie");
			query.setParameter("subcategorie", subcategorie.getId());
		}else if(subcategorie!=null && categorie!= null && mp==null)
		{
			
			query= session.createQuery("select c from MatierePremier c where categorieMp.id=:categorie");
			query.setParameter("categorie", categorie.getId());
			
		}else if(subcategorie!=null && categorie!= null && mp!=null)
		{
			query= session.createQuery("select c from MatierePremier c where id=:mp");
			query.setParameter("mp", mp.getId());
		}
		 if(query==null)
		 {
			 query = session.createQuery("select c from MatierePremier c ");
		 }
		
		return query.list();
		
	}
	
	@Override
	public List<MatierePremier> listeMatierePremierNonUtiliser(String req,Date dateDu,Date dateAu,String depot,String statut) {
		// TODO Auto-generated method stub
		
		Query query=null;
	 
			query= session.createQuery("select c from MatierePremier c where c.id not in (select p.matierePremier.id from CoutMP p  where p.prodcutionCM.date between :dateDu and :dateAu and p.prodcutionCM.codeDepot=:depot and p.prodcutionCM.statut =:statut) and c.id not in (select cmp.matierePremier.id from CoutProdMP cmp  where cmp.prodcutionCM.dateProduction between :dateDu and :dateAu and cmp.prodcutionCM.codeDepot=:depot and cmp.prodcutionCM.statut =:statut) "+req);
			query.setParameter("dateDu", dateDu);
			query.setParameter("dateAu", dateAu);
			query.setParameter("depot", depot);
			query.setParameter("statut", statut);
		return query.list();
		
	}
	
	
	@Override
	public List<MatierePremier> listeMatiereCadeauAutres(SubCategorieMp subcategorie) {
		// TODO Auto-generated method stub
		
		Query query=null;
	 
			query= session.createQuery("select c from MatierePremier c where c.categorieMp.subCategorieMp.id=:subcategorie and c.unite=:unite and c.typeOffre.typeOffre=:autres" );
					query.setParameter("subcategorie", subcategorie.getId());
					query.setParameter("unite", Constantes.UNITE_PIECE);
					query.setParameter("autres", Constantes.TYPE_OFFRE_AUTRES);
		return query.list();
		
	}
	
	@Override
	public List<MatierePremier> listeMatiereCadeauPF(SubCategorieMp subcategorie,BigDecimal unite) {
		// TODO Auto-generated method stub
		
		Query query=null;
	 
			query= session.createQuery("select c from MatierePremier c where c.categorieMp.subCategorieMp.id=:subcategorie and c.unite <>:unite and c.typeOffre.typeOffre=:pf and c.grammageOffre<=:grammage" );
					query.setParameter("subcategorie", subcategorie.getId());
					query.setParameter("unite", Constantes.UNITE_PIECE);
					query.setParameter("pf", Constantes.TYPE_OFFRE_PF);
					query.setParameter("grammage", unite);
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

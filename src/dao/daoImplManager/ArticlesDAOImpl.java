package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.Constantes;
import util.HibernateUtil;
import dao.daoManager.ArticlesDAO;
import dao.entity.Articles;
import dao.entity.DetailEstimation;

public class ArticlesDAOImpl implements ArticlesDAO {
	Session session=HibernateUtil.openSession();

	public void add(Articles e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public Articles edit(Articles e) {
		
	session.beginTransaction();
	Articles p= (Articles)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		Articles p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<Articles> findAll() {
		return session.createQuery("select c from Articles c").list();
		}
	
	@Override
	public List<Articles> listeArticlesByReq( String requete) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from Articles c where  c.id>0 "+requete);
		
		return query.list();
		
		
	}

	public Articles findById(int id) {
		return (Articles)session.get(Articles.class, id);
		}

	@Override
	public Articles findByCode(String code) {
		// TODO Auto-generated method stub
		Articles articles= new Articles();
		Query query= session.createQuery("select c from Articles c where codeArticle=:code");
		query.setParameter("code", code);
		
		articles= (Articles) query.uniqueResult();
		
		return articles;
	}
	
	@Override
	public Articles findByLibelle(String libelle) {
		// TODO Auto-generated method stub
		Articles articles= new Articles();
		Query query= session.createQuery("select c from Articles c where liblle=:libelle");
		query.setParameter("libelle", libelle);
		
		articles= (Articles) query.uniqueResult();
		
		return articles;
	}
	
	
	
	
	
	
	@Override
	public List<DetailEstimation> listeMatierePremierByArticleByMP(int id,String codeSubCat, int priorite) {
		// TODO Auto-generated method stub
		Articles articles= new Articles();
		Query query= session.createQuery("select c from DetailEstimation c where articles.id=:id and matierePremier.categorieMp.subCategorieMp.code=:codeSubCat and priorite=:priorite");
		query.setParameter("id", id);
		query.setParameter("codeSubCat", codeSubCat);
		query.setParameter("priorite", priorite);
		return query.list();
		
		
	}
	
	@Override
	public List<DetailEstimation> listeMatierePremierByArticleByCategorie(int id,String codeSubCat) {
		// TODO Auto-generated method stub
		Articles articles= new Articles();
		Query query= session.createQuery("select c from DetailEstimation c where articles.id=:id and matierePremier.categorieMp.subCategorieMp.code=:codeSubCat group by matierePremier.id");
		query.setParameter("id", id);
		query.setParameter("codeSubCat", codeSubCat);
		 
		return query.list();
		
		
	}
	
	
	@Override
	public List<Articles> listeArticlesNonFabriquer( String requete) {
		// TODO Auto-generated method stub
		Articles articles= new Articles();
		Query query= session.createQuery("select c from Articles c where  c.id not in (select p.articles.id from Production p where p.statut=:terminer "+ requete+")");
		query.setParameter("terminer", Constantes.ETAT_OF_TERMINER);
		
		return query.list();
		
		
	}
	
	
	


}

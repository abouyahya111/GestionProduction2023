package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.ArticlesMPDAO;
import dao.entity.Articles;
import dao.entity.ArticlesMP;
import dao.entity.DetailEstimation;
import dao.entity.DetailEstimationMP;

public class ArticlesMPDAOImpl implements ArticlesMPDAO {
	Session session=HibernateUtil.openSession();

	public void add(ArticlesMP e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public ArticlesMP edit(ArticlesMP e) {
		
	session.beginTransaction();
	ArticlesMP p= (ArticlesMP)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		ArticlesMP p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<ArticlesMP> findAll() {
		return session.createQuery("select c from ArticlesMP c").list();
		}

	public ArticlesMP findById(int id) {
		return (ArticlesMP)session.get(ArticlesMP.class, id);
		}

	public ArticlesMP findByCode(String code) {
		// TODO Auto-generated method stub
		ArticlesMP articles= new ArticlesMP();
		Query query= session.createQuery("select c from ArticlesMP c where codeArticle=:code");
		query.setParameter("code", code);
		
		articles= (ArticlesMP) query.uniqueResult();
		
		return articles;
	}
	
	
	public ArticlesMP findByNom(String nom) {
		// TODO Auto-generated method stub
		ArticlesMP articles= new ArticlesMP();
		Query query= session.createQuery("select c from ArticlesMP c where liblle=:nom");
		query.setParameter("nom", nom);
		
		articles= (ArticlesMP) query.uniqueResult();
		
		return articles;
	}
	
	
	public List<DetailEstimationMP> listeMatierePremierByArticleMPByMP(int id,String codeSubCat) {
		// TODO Auto-generated method stub
		ArticlesMP articlesMP= new ArticlesMP();
		Query query= session.createQuery("select c from DetailEstimationMP c where articles.id=:id and matierePremier.categorieMp.subCategorieMp.code=:codeSubCat");
		query.setParameter("id", id);
		query.setParameter("codeSubCat", codeSubCat);
		return query.list();
		
		
	}
	
	
	public List<DetailEstimationMP> listeDetailEstimationMPActifByArticle(int idarticle) {
		// TODO Auto-generated method stub
		ArticlesMP articlesMP= new ArticlesMP();
		Query query= session.createQuery("select c from DetailEstimationMP c where articlesMP.id =:idarticle and actif=true");
		query.setParameter("idarticle", idarticle);
		
		return query.list();
		
		
	}
	


}

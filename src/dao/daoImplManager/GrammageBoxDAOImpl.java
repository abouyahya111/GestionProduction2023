package dao.daoImplManager;

import java.util.List;

import main.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.GrammageBoxDAO;
import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.GrammageBox;


public class GrammageBoxDAOImpl implements GrammageBoxDAO {
	Session session=HibernateUtil.openSession();
		

	public void add(GrammageBox e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public GrammageBox edit(GrammageBox e) {
		
	session.beginTransaction();
	GrammageBox p= (GrammageBox)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		GrammageBox p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<GrammageBox> findAll() {
		return session.createQuery("select c from GrammageBox c").list();
		}

	public GrammageBox findById(int id) {
		return (GrammageBox)session.get(GrammageBox.class, id);
		}

	@Override
	public GrammageBox findByCode(String code) {
		// TODO Auto-generated method stub
		GrammageBox articles= new GrammageBox();
		Query query= session.createQuery("select c from GrammageBox c where codeGrammage=:code");
		query.setParameter("code", code);
		
		articles= (GrammageBox) query.uniqueResult();
		
		return articles;
	}
	
	


}

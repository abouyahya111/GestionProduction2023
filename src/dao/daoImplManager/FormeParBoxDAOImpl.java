package dao.daoImplManager;

import java.util.List;

import main.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.FormeDAO;
import dao.daoManager.FormeParBoxDAO;
import dao.daoManager.GrammageBoxDAO;
import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.FormeParBox;
import dao.entity.GrammageBox;
import dao.entity.SubCategorieMp;
import dao.entity.forme;


public class FormeParBoxDAOImpl implements FormeParBoxDAO {
	Session session=HibernateUtil.openSession();
		

	public void add(FormeParBox e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public FormeParBox edit(FormeParBox e) {
		
	session.beginTransaction();
	FormeParBox p= (FormeParBox)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		FormeParBox p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<FormeParBox> findAll() {
		return session.createQuery("select c from FormeParBox c").list();
		}

	public FormeParBox findById(int id) {
		return (FormeParBox)session.get(FormeParBox.class, id);
		}

	@Override
	public FormeParBox findByFormeBySubCategorie(forme forme,SubCategorieMp subCategorieMp) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from FormeParBox c where forme.id=:forme and subCategorieMp.id=:subCategorieMp");
		query.setParameter("forme", forme.getId());
		query.setParameter("subCategorieMp", subCategorieMp.getId());

		
		
		return (FormeParBox) query.uniqueResult();
	}
	
	@Override
	public List<FormeParBox> findBySubCategorie(SubCategorieMp subCategorieMp) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from FormeParBox c where  subCategorieMp.id=:subCategorieMp");
		query.setParameter("subCategorieMp", subCategorieMp.getId());

		
		
		return  query.list();
	}


}

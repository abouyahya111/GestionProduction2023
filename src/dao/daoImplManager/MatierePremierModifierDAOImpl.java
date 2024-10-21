package dao.daoImplManager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.Constantes;
import util.HibernateUtil;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.MatierePremiereModifierDAO;
import dao.entity.CategorieMp;
import dao.entity.MatierePremier;
import dao.entity.MatierePremierModifier;
import dao.entity.SubCategorieMp;

public class MatierePremierModifierDAOImpl implements MatierePremiereModifierDAO {
	Session session=HibernateUtil.openSession();

	public void add(MatierePremierModifier e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public MatierePremierModifier edit(MatierePremierModifier e) {
		
	session.beginTransaction();
	MatierePremierModifier p= (MatierePremierModifier)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		MatierePremierModifier p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<MatierePremierModifier> findAll() {
		return session.createQuery("select c from MatierePremierModifier c where c.code not like '"+Constantes.CODE_AUTRES_DECHET_MATIERE_PREMIERE_LIBELLE+"%' ").list();
		}
	
	

	public MatierePremierModifier findById(int id) {
		return (MatierePremierModifier)session.get(MatierePremierModifier.class, id);
		}

	@Override
	public List<MatierePremierModifier> findByCode(String code) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from MatierePremierModifier c where code=:code");
		query.setParameter("code", code);
		
		
		
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

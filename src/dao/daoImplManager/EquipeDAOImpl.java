package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.EquipeDAO;
import dao.entity.Depot;
import dao.entity.Employe;
import dao.entity.Equipe;

public class EquipeDAOImpl implements EquipeDAO {
	Session session=HibernateUtil.openSession();

	public void add(Equipe e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public Equipe edit(Equipe e) {
		
	session.beginTransaction();
	Equipe p= (Equipe)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		Equipe p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	@SuppressWarnings("unchecked")
	public List<Equipe> findAll() {
		return session.createQuery("select c from Equipe c").list();
		}

	public Equipe findById(int id) {
		return (Equipe)session.get(Equipe.class, id);
		}

	@Override
	public Equipe findByCodeNom(String code, String nom) {
		// TODO Auto-generated method stub
		Equipe equipe= new Equipe();
		Query query= session.createQuery("select c from Equipe c where codeEquipe=:code or nomEquipe=:nom ");
		query.setParameter("code", code);
		query.setParameter("nom", nom);
		
		equipe= (Equipe) query.uniqueResult();
		
		return equipe;
	}
	
	@Override
	public Equipe findByCodeNomDepot(String code, String nom,int depot) {
		// TODO Auto-generated method stub
		Equipe equipe= new Equipe();
		Query query= session.createQuery("select c from Equipe c where id_depot=:depot and (codeEquipe=:code or nomEquipe=:nom) ");
		query.setParameter("code", code);
		query.setParameter("nom", nom);
		query.setParameter("depot", depot);
		
		equipe= (Equipe) query.uniqueResult();
		
		return equipe;
	}

	@Override
	public List<Equipe> findListeEquipeByType(String typeEquipe, String codeDepot) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from Equipe c where typeEquipe.code=:typeEquipe and depot.code=:codeDepot");
		query.setParameter("typeEquipe", typeEquipe);
		query.setParameter("codeDepot", codeDepot);
		
		return query.list();
		
		
	}

	
	@Override
	public List<Employe> findListeEmployeByEquipe(int idEquipe) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from Employe c where equipe.id=:idEquipe");
		query.setParameter("idEquipe", idEquipe);
		
		return query.list();
		
		
	}

}

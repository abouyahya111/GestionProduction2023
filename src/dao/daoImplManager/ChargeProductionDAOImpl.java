package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ChargeProductionDAO;
import dao.daoManager.ProductionDAO;
import dao.entity.ChargeProduction;
import dao.entity.CoutMP;
import dao.entity.DetailChargeFixe;
import dao.entity.DetailChargeVariable;
import dao.entity.DetailProdGen;
import dao.entity.DetailProduction;
import dao.entity.Production;

public class ChargeProductionDAOImpl implements ChargeProductionDAO {
	Session session=HibernateUtil.openSession();

	public void add(ChargeProduction e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public ChargeProduction edit(ChargeProduction e) {
		
	session.beginTransaction();
	ChargeProduction p= (ChargeProduction)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		ChargeProduction p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<ChargeProduction> findAll() {
		return session.createQuery("select c from ChargeProduction c").list();
		}

	public ChargeProduction findById(int id) {
		return (ChargeProduction)session.get(ChargeProduction.class, id);
		}

	@Override
	public ChargeProduction findbycode(String code) {
		
		Query query= session.createQuery("select p from ChargeProduction p where code =:code");
		query.setParameter("code", code);
		return (ChargeProduction)query.uniqueResult();

	}
	
	@Override
	public List<DetailChargeFixe> listeDetailhargeFixe(int idChargeFixe) {
		
		Query query= session.createQuery("select p from DetailChargeFixe p where charge.id =:idChargeFixe");
		query.setParameter("idChargeFixe", idChargeFixe);
		
		return query.list();

	}
	
	@Override
	public List<DetailChargeVariable> listeDetailhargeVariable(int idChargeVariable) {
		
		Query query= session.createQuery("select p from DetailChargeVariable p where charge.id =:idChargeVariable");
		query.setParameter("idChargeVariable", idChargeVariable);
		
		return query.list();

	}
	
	@Override
	public List<ChargeProduction> listeChargeProductionbycode(String code) {
	
		Query query= session.createQuery("select p from ChargeProduction p where code=:code");
		query.setParameter("code", code);
		
		List<ChargeProduction> list=query.list();
	
		return list;

	}
	
	@Override
	public List<ChargeProduction> listeChargeProductionbycodeAndDepot(String code,String depot) {
	
		Query query= session.createQuery("select p from ChargeProduction p where code=:code and code_depot=:depot");
		query.setParameter("code", code);
		query.setParameter("depot", depot);
		List<ChargeProduction> list=query.list();
	
		return list;

	}
	
	@Override
	public ChargeProduction findbycodeFix(String code,String fix) {
		
		Query query= session.createQuery("select p from ChargeProduction p where code =:code and type=:fix");
		query.setParameter("code", code);
		query.setParameter("fix", fix);
		ChargeProduction chargeProduction=(ChargeProduction)query.uniqueResult();
	
		return chargeProduction;

	}
	

	
	
}

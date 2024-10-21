package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.MachineDAO;
import dao.entity.Machine;

public class MachineDAOImpl implements MachineDAO {
	Session session=HibernateUtil.openSession();

	public void add(Machine e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public Machine edit(Machine e) {
		
	session.beginTransaction();
	Machine p= (Machine)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		Machine p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	@SuppressWarnings("unchecked")
	public List<Machine> findAll() {
		return session.createQuery("select c from Machine c").list();
		}

	public Machine findById(int id) {
		return (Machine)session.get(Machine.class, id);
		}

	@Override
	public Machine findByCodeNom(String code) {
		// TODO Auto-generated method stub
		Machine machine= new Machine();
		Query query= session.createQuery("select c from Machine c where matricule=:code");
		query.setParameter("code", code);
		
		
		machine= (Machine) query.uniqueResult();
		
		return machine;
	}
	
	@Override
	public  List<Machine> findListMachineByCodeDepot(String codeDepot) {
		// TODO Auto-generated method stub
		Machine machine= new Machine();
		Query query= session.createQuery("select c from Machine c where codeDepot=:codeDepot");
		query.setParameter("codeDepot", codeDepot);
		
		
		return query.list();
	}


}

package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ClientDAO;
import dao.entity.Client;
import dao.entity.Machine;

public class ClientDAOImpl implements ClientDAO {
	Session session=HibernateUtil.openSession();

	public void add(Client e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public Client edit(Client e) {
		
	session.beginTransaction();
	Client p= (Client)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		Client p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<Client> findAll() {
		return session.createQuery("select c from Client c").list();
		}

	public Client findById(int id) {
		return (Client)session.get(Client.class, id);
		}
	
	@Override
	public  List<Client> findListClientByCodeDepot(String codeDepot) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from Client c where codeDepot=:codeDepot");
		query.setParameter("codeDepot", codeDepot);
		
		
		return query.list();
	}

	
	@Override
	public  Client findClientByCodeClient(String codeClient) {
		// TODO Auto-generated method stub
		Machine machine= new Machine();
		Query query= session.createQuery("select c from Client c where code=:codeClient");
		query.setParameter("codeClient", codeClient);
		
		
		return (Client) query.uniqueResult();
	}



}

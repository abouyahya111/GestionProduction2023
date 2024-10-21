package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.TransferStockPFDAO;
import dao.entity.TransferStockPF;

public class TransferStockPFDAOImpl implements TransferStockPFDAO {
	Session session=HibernateUtil.openSession();

	public void add(TransferStockPF e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public TransferStockPF edit(TransferStockPF e) {
		
	session.beginTransaction();
	TransferStockPF p= (TransferStockPF)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		TransferStockPF p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<TransferStockPF> findAll() {
		return session.createQuery("select c from TransferStockPF c").list();
		}

	public TransferStockPF findById(int id) {
		return (TransferStockPF)session.get(TransferStockPF.class, id);
		}
	
	public TransferStockPF findByCodeTransfert(String codeTransfert) {
		Query query= session.createQuery("select c from TransferStockPF c where CodeTransfer=:codeTransfert");
		query.setParameter("codeTransfert", codeTransfert);
		
		return (TransferStockPF)query.uniqueResult();
		}
	
	
	public void deleteObject(TransferStockPF p) {
		
		session.beginTransaction();
		session.delete(p);
		session.getTransaction().commit();
		
	}
	
	public void ViderSession()
	{
		if(session!=null)
		{
			session.clear();
		}
	}
	
	
	


}

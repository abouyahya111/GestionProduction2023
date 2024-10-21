package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.Constantes;
import util.HibernateUtil;
import dao.daoManager.TransferStockMPDAO;
import dao.entity.Depot;
import dao.entity.DetailTypeSortie;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.TransferStockMP;
import dao.entity.TransferStockPF;
import dao.entity.TypeSortie;
import dao.entity.Utilisateur;
import main.AuthentificationView;

public class TransferStockMPDAOImpl implements TransferStockMPDAO {
	Session session=HibernateUtil.openSession();
	
	

	public void add(TransferStockMP e) {
		
		
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public TransferStockMP edit(TransferStockMP e) {
		
	session.beginTransaction();
	TransferStockMP p= (TransferStockMP)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		TransferStockMP p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<TransferStockMP> findAll() {
		return session.createQuery("select c from TransferStockMP c").list();
		}

	public TransferStockMP findById(int id) {
		return (TransferStockMP)session.get(TransferStockMP.class, id);
		}
	
	@Override
	public int maxIdStock() {
		// TODO Auto-generated method stub
		int id =0;
		Query query= session.createQuery("select max(id) from TransferStockMP");
		
		if(query.uniqueResult()==null)
			return id;
		else 
			id= (int)query.uniqueResult();
		
		return id;
	}
	
	
	public TransferStockMP findTransferByCode(String codeTransfer) {
		
		
		Query query= session.createQuery("select c from TransferStockMP c where CodeTransfer=:codeTransfer");
		query.setParameter("codeTransfer", codeTransfer);
		
		return (TransferStockMP)query.uniqueResult();
		}
	
	
	public List<TransferStockMP> listTransferByCode(String codeTransfer ,Date dateDebut , Date dateFin) {
		
		
		Query query= session.createQuery("select c from TransferStockMP c where CodeTransfer=:codeTransfer and dateTransfer between :dateDebut and :dateFin");
		query.setParameter("codeTransfer", codeTransfer);
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		return query.list();
		}
	
	public List<TransferStockMP> listTransferByStatut(String statut) {
		
		
		Query query= session.createQuery("select c from TransferStockMP c where statut=:statut");
		query.setParameter("statut", statut);
	 
		return query.list();
		}
	
	
public List<TransferStockMP> listTransferByStatutByLikeCodeTransfert(String statut, String likeCodeTransfert) {
		
		
		Query query= session.createQuery("select c from TransferStockMP c where statut=:statut and CodeTransfer like :likeCodeTransfert");
		query.setParameter("statut", statut);
		query.setParameter("likeCodeTransfert", likeCodeTransfert + "%");
	 
		return query.list();
		}
	
	public TransferStockMP findTransferByCodeStatut(String codeTransfer , String statut) {
		
		
		Query query= session.createQuery("select c from TransferStockMP c where CodeTransfer=:codeTransfer and statut=:statut");
		query.setParameter("codeTransfer", codeTransfer);
		query.setParameter("statut", statut);
		
		return (TransferStockMP)query.uniqueResult();
		}
	
	
public List<TransferStockMP> findListeTransferByCodeStatut(String codeTransfer , String statut) {
		
		
		Query query= session.createQuery("select c from TransferStockMP c where CodeTransfer=:codeTransfer and statut=:statut");
		query.setParameter("codeTransfer", codeTransfer);
		query.setParameter("statut", statut);
		
		return  query.list();
		}

public List<TransferStockMP> findListeTransferByDateByStatut(Date date , String statut) {
	
	
	Query query= session.createQuery("select c from TransferStockMP c where dateTransfer=:date and statut=:statut");
	query.setParameter("date", date);
	query.setParameter("statut", statut);
	
	return  query.list();
	}

public  List<TransferStockMP> findListeTransferByCodeStatutByNumBLReception(String codeTransfer , String statut,String NumBL) {
	
	
	Query query= session.createQuery("select c from TransferStockMP c where CodeTransfer=:codeTransfer and statut=:statut and c.id in (select d.transferStockMP.id from DetailTransferStockMP d where d.numBLReception=:NumBL)");
	query.setParameter("codeTransfer", codeTransfer);
	query.setParameter("statut", statut);
	query.setParameter("NumBL", NumBL);
	
	return   query.list();
	
	}

public  TransferStockMP findListeTransferByCodeStatutByNumBLReceptionUnique(String codeTransfer , String statut,String NumBL) {
	
	
	Query query= session.createQuery("select c from TransferStockMP c where CodeTransfer=:codeTransfer and statut=:statut and c.id in (select d.transferStockMP.id from DetailTransferStockMP d where d.numBLReception=:NumBL)");
	query.setParameter("codeTransfer", codeTransfer);
	query.setParameter("statut", statut);
	query.setParameter("NumBL", NumBL);
	
	return   (TransferStockMP)query.uniqueResult();
	
	}

public  TransferStockMP findListeTransferByCodeStatutByNumBLReceptionByMP(String codeTransfer , String statut,String NumBL, MatierePremier mp) {
	
	
	Query query= session.createQuery("select c from TransferStockMP c where CodeTransfer=:codeTransfer and statut=:statut and c.id in (select d.transferStockMP.id from DetailTransferStockMP d where d.numBLReception=:NumBL and d.matierePremier.id=:mp)");
	query.setParameter("codeTransfer", codeTransfer);
	query.setParameter("statut", statut);
	query.setParameter("NumBL", NumBL);
	query.setParameter("mp", mp.getId());
	
	return   (TransferStockMP)query.uniqueResult();
	
	}
	
	
	public List<TransferStockMP> findTransferByStatut(String statut,Date dateDebut , Date dateFin , Depot depot) {
		Query query=null;
		if(dateDebut==null && dateFin==null )
		{
			
			 query= session.createQuery("select c from TransferStockMP c where statut=:statut and depot=:depot");
				query.setParameter("statut", statut);
				query.setParameter("depot", depot);
			
		}else if(dateDebut!=null && dateFin!=null )
		{
			 query= session.createQuery("select c from TransferStockMP c where statut=:statut and dateTransfer between :dateDebut and :dateFin and depot=:depot");
				query.setParameter("statut", statut);
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("depot", depot);
			
			
		}
		
		
		return query.list();
		
		
		}
	
	public List<TransferStockMP> findReceptionEnAttenteNonImporter(String statut,Date dateDebut , Date dateFin , Depot depot) {
		Query query=null;
	 
			 query= session.createQuery("select c from TransferStockMP c where statut=:statut and dateTransfer between :dateDebut and :dateFin and depot=:depot and ImporterViaGestionCommande is null order by dateTransfer");
				query.setParameter("statut", statut);
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("depot", depot);
			
			
		 
		
		
		return query.list();
		
		
		}
	
	
	public List<TransferStockMP> ListeCodeTransfertEnAttente(String statut, Depot depot) {
		Query query=null;
		if( statut.equals(Constantes.ETAT_TRANSFER_STOCK_ENTRE_ENATTENT) )
		{
			
			    query= session.createQuery("select c from TransferStockMP c where statut=:statut and depot.id=:depot");
				query.setParameter("statut", statut);
				query.setParameter("depot", depot.getId());
			
		}else if( statut.equals(Constantes.ETAT_SORTIE_ENATTENT) )
		{
			 query= session.createQuery("select c from TransferStockMP c where statut=:statut  and depotDestination.id=:depot");
			 query.setParameter("statut", statut);
			 query.setParameter("depot", depot.getId());
			
			
		}
		
		
		return query.list();
		
		
		}
	
	
	
	
	public List<TransferStockMP> findTransferConsultationBonSortieMP( Depot depot ,  String requet) {
		
		
		Query query= session.createQuery("select c from TransferStockMP c where depot=:depot"+requet);
		
		query.setParameter("depot", depot);
		
		return query.list();
		
		 
		
		}

	
	
	
	
public List<TransferStockMP> findTransferByStatutSaufTransfer(String statut,Date dateDebut , Date dateFin , Depot depot) {
		
		
		Query query= session.createQuery("select c from TransferStockMP c where statut=:statut and dateTransfer between :dateDebut and :dateFin and depot=:depot and CodeTransfer not in ('T_1109_14_1719','T_1308_14_1784','T_1308_14_1849','T_1593_1717')");
		query.setParameter("statut", statut);
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		query.setParameter("depot", depot);
		
		return query.list();
		
		
		}


public List<TransferStockMP> findTransferByStatutSaufTransferAllRegion(String statut,Date dateDebut , Date dateFin ) {
	
	
	Query query= session.createQuery("select c from TransferStockMP c where statut=:statut and dateTransfer between :dateDebut and :dateFin ");
	query.setParameter("statut", statut);
	query.setParameter("dateDebut", dateDebut);
	query.setParameter("dateFin", dateFin);
	
	
	return query.list();
	}


	
public List<TransferStockMP> findTransferByStatutChargeouService(String charge,String service) {
		
		
		Query query= session.createQuery("select c from TransferStockMP c where statut=:charge or statut=:service");
		query.setParameter("charge", charge);
		query.setParameter("service", service);
		
		return query.list();
		}

public List<TransferStockMP> listTransferMPProduction(String req) {
	
	
	Query query= session.createQuery("select c from TransferStockMP c where  c.id in (select d.transferStockMP.id from DetailTransferStockMP d where d.magasinDestination is not null  and d.magasinSouce is not null and ( (d.magasinSouce.catMagasin=:stockage and d.magasinDestination.catMagasin=:stockage) or (d.magasinSouce.catMagasin=:production and d.magasinDestination.catMagasin=:production) or (d.magasinSouce.catMagasin=:production and d.magasinDestination.catMagasin=:stockage) )   and d.magasinSouce.code<>:EnAttente  and d.magasinDestination.code<>:EnAttente and d.magasinSouce.typeMagasin=:TypeMP  and d.magasinDestination.typeMagasin=:TypeMP "+req+" )");
	query.setParameter("stockage", Constantes.MAGASIN_CODE_CATEGORIE_STOCKAGE);
	query.setParameter("production", Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION);
	query.setParameter("EnAttente", Constantes.CODE_MAGASIN_STOCKAGE_EN_ATTENTE);
	query.setParameter("TypeMP", Constantes.MAGASIN_CODE_TYPE_MP);
 
	 
	return query.list();
	}

 

public List<TransferStockMP> listTransferMarchandiseDeplacer(String req) {
	
	
	Query query= session.createQuery("select c from TransferStockMP c where  c.id in (select d.transferStockMP.id from DetailTransferStockMP d where "+req+" )");
	 
 
	 
	return query.list();
	}


public void ViderSession()
{
	if(session!=null)
	{
		session.clear();
	}
}
	


public void deleteObject(TransferStockMP p) {
	
	session.beginTransaction();
	session.delete(p);
	session.getTransaction().commit();
	
}
	


}

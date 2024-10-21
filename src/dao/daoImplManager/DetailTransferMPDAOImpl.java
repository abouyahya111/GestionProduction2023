package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Query;
import org.hibernate.Session;

import util.Constantes;
import util.HibernateUtil;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.entity.Articles;
import dao.entity.CategorieMp;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.FournisseurMP;
import dao.entity.Magasin;
import dao.entity.MatierePremier;

import dao.entity.SubCategorieMp;
import dao.entity.TransferStockMP;
import dao.entity.TypeSortie;

public class DetailTransferMPDAOImpl implements DetailTransferMPDAO {
	Session session=HibernateUtil.openSession();

	public void add(DetailTransferStockMP e) {
		session.beginTransaction();
		session.save(e);
		session.getTransaction().commit();
		
		//return p;
	}

	public DetailTransferStockMP edit(DetailTransferStockMP e) {
		
	session.beginTransaction();
	DetailTransferStockMP p= (DetailTransferStockMP)session.merge(e);
	
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailTransferStockMP p= findById(id);
		session.delete(p);
		
		session.getTransaction().commit();

		
	}

	@SuppressWarnings("unchecked")
	public List<DetailTransferStockMP> findAll() {
		return session.createQuery("select c from DetailTransferStockMP c").list();
		
		}

	public DetailTransferStockMP findById(int id) {
		return (DetailTransferStockMP)session.get(DetailTransferStockMP.class, id);
		}
	

	


	public List<DetailTransferStockMP> findByMP(String codeMP) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select d from DetailTransferStockMP d where matierePremier.code=:codeMP");
				query.setParameter("codeMP", codeMP);
			
				
				return query.list();
}

	public List<DetailTransferStockMP> findByTransferStockMP(int idtransferStockMP) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.id=:idtransferStockMP ");
				query.setParameter("idtransferStockMP", idtransferStockMP);
			
				
				return query.list();
}
	
	
	
	
	public List<DetailTransferStockMP> findByNumBlReceptionEnAttente(String numblReception,String statut ) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select d from DetailTransferStockMP d where numBLReception=:numblReception and d.transferStockMP.statut=:statut");
				query.setParameter("numblReception", numblReception);
				query.setParameter("statut", statut);

				
				return query.list();
}
	

	public List<DetailTransferStockMP> ListHistoriqueReception(String requete) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select d from DetailTransferStockMP d "+requete +" and matierePremier.code like '"+Constantes.CODE_MP+"%' order by transferStockMP.dateTransfer ");
				
			
				
				return query.list();
}	
	
	
	public List<DetailTransferStockMP> ListHistoriqueReceptionDechet(String requete) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select d from DetailTransferStockMP d "+requete +" and matierePremier.code like '"+Constantes.CODE_AUTRES_DECHET_MATIERE_PREMIERE_LIBELLE+"%' order by transferStockMP.dateTransfer ");
				
			
				
				return query.list();
}

	
	
	public DetailTransferStockMP DetailTransfertMPByMPBYCodeTransfertBYNumBLReceptionByEtat(MatierePremier mp, String codeTransfert,String NumBlReception,String statut ) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select d from DetailTransferStockMP d  where d.transferStockMP.CodeTransfer=:codeTransfert and d.matierePremier.id=:mp and d.numBLReception=:NumBlReception and d.transferStockMP.statut=:statut");
				
				query.setParameter("mp", mp.getId());
				query.setParameter("codeTransfert", codeTransfert);
				query.setParameter("NumBlReception", NumBlReception);
				query.setParameter("statut", statut);
				return (DetailTransferStockMP)query.uniqueResult();
}
	
	
	
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMP(Date dateDebut , Date dateFin , MatierePremier mp) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(dateDebut!=null && dateFin!=null && mp!=null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer between :dateDebut and :dateFin and matierePremier.id =:mp");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("mp", mp.getId());
			
		}else if(dateDebut!=null && dateFin!=null && mp==null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where  transferStockMP.dateTransfer between :dateDebut and :dateFin");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				
			
		}else if(dateDebut==null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  matierePremier.id =:mp");
			
			 query.setParameter("mp", mp.getId());
			
		}else if(dateDebut!=null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  transferStockMP.dateTransfer =:dateDebut and matierePremier.id =:mp");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("mp", mp.getId());
				
		}else if(dateDebut!=null && dateFin==null && mp==null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  transferStockMP.dateTransfer =:dateDebut");
				query.setParameter("dateDebut", dateDebut);
			
			
		}
				
			
				
				return query.list();
}
	
public List<DetailTransferStockMP> ListTransferStockMPReceptionGroupByCodeTransfertByNumBL(Date dateDebut , Date dateFin , String statut) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		 
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer between :dateDebut and :dateFin and transferStockMP.statut =:statut and ImporterViaGestionCommande=:importer group by numBLReception order by transferStockMP.dateTransfer");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("statut", statut);
				query.setParameter("importer", Constantes.CODE_IMPORTER);
			
		 
				
			
				
				return query.list();
}
	
	
	// afficher list transfer stock produit fini contient les article nn redoublé ( groupe by article)
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPDistinct(Date dateDebut , Date dateFin , MatierePremier mp) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(dateDebut!=null && dateFin!=null && mp!=null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer between :dateDebut and :dateFin and matierePremier.id  =:mp group by mp,transferStockMP.dateTransfer");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("mp", mp.getId());
			
		}else if(dateDebut!=null && dateFin!=null && mp==null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer between :dateDebut and :dateFin group by matierePremier,transferStockMP.dateTransfer");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				
			
		}else if(dateDebut==null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  matierePremier.id=:mp group by matierePremier,transferStockMP.dateTransfer");
			
			 query.setParameter("mp", mp.getId());
			
		}else if(dateDebut!=null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer =:dateDebut and matierePremier.id=:mp group by matierePremier,transferStockMP.dateTransfer");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("mp", mp.getId());
				
		}else if(dateDebut!=null && dateFin==null && mp==null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer =:dateDebut group by matierePremier,transferStockMP.dateTransfer");
				query.setParameter("dateDebut", dateDebut);
			
			
		}
				
			
				
				return query.list();
}
	


	public List<DetailTransferStockMP> findBytypetransfer(String type,Magasin magasin) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select d from DetailTransferStockMP  d where transferStockMP.statut=:type and magasinSouce.id=:magasin");
				query.setParameter("type", type);
				 query.setParameter("magasin",magasin.getId());
				
				return query.list();
}
	
	
	public List<DetailTransferStockMP> findAllTransferStockMPOrderByDateTransfer(Magasin magasin) {
		
		// TODO Auto-generated method stub
		
		Query query=  session.createQuery("select d from DetailTransferStockMP d where (magasinSouce.id=:magasin or magasinDestination.id=:magasin) order by transferStockMP.dateTransfer");
		 query.setParameter("magasin",magasin.getId());
		 return query.list();
				
}
	
	public List<DetailTransferStockMP> findAllTransferStockMPOrderByDateTransferByMP(Magasin magasin , Date dateDebut , Date dateFin , String statut) {
		
		// TODO Auto-generated method stub
		
		Query query=  session.createQuery("select sum(d.quantite) , d.matierePremier ,d.transferStockMP,d.magasinSouce,d.magasinDestination  from DetailTransferStockMP d where magasinSouce.id=:magasin or magasinDestination.id=:magasin order by transferStockMP.dateTransfer,matierePremier");
		 query.setParameter("magasin",magasin.getId());
		 query.setParameter("datedebut",dateDebut);
		 query.setParameter("dateFin",dateFin);
		 query.setParameter("statut",statut);
		 return query.list();
				
}
	
	
	
	public List<DetailTransferStockMP> findAllTransferStockMPGroupeByDateTransferByMP(Magasin magasin) {
		
		// TODO Auto-generated method stub
		Query query= session.createQuery("select d from DetailTransferStockMP d where (magasinSouce.id=:magasin or magasinDestination.id=:magasin)  group by matierePremier,transferStockMP.dateTransfer");
		 query.setParameter("magasin",magasin.getId());
		 return query.list();
				
}
	
	
	public List<DetailTransferStockMP> findAllTransferStockMPGroupeByMP(Magasin magasin) {
		
		// TODO Auto-generated method stub
		Query query= session.createQuery("select d from DetailTransferStockMP d where magasinSouce.id=:magasin or magasinDestination.id=:magasin  group by matierePremier");
		 query.setParameter("magasin",magasin.getId());
		 return query.list();
				
}
	

	
	public DetailTransferStockMP findAllTransferStockMPByMPInitialiser(MatierePremier mp,String statut,Depot depot,Magasin magasin) {
		
		// TODO Auto-generated method stub
		Query query= session.createQuery("select d from DetailTransferStockMP d  where matierePremier.id=:mp and  transferStockMP.statut=:statut and transferStockMP.depot.id =:depot and (magasinSouce.id=:magasin or magasinDestination.id=:magasin)");
		 query.setParameter("mp", mp.getId());
		 query.setParameter("statut",statut);
		 query.setParameter("depot",depot.getId());
		 query.setParameter("magasin",magasin.getId());
		 return (DetailTransferStockMP) query.uniqueResult();
				
}
	
	
	public DetailTransferStockMP findAllTransferStockMPByMPByFournisseurInitialiser(MatierePremier mp,String statut,Depot depot,Magasin magasin,FournisseurMP fournisseurMP) {
		
		// TODO Auto-generated method stub
		Query query= session.createQuery("select d from DetailTransferStockMP d  where matierePremier.id=:mp and  transferStockMP.statut=:statut and fournisseur.id=:fournisseurMP and transferStockMP.depot.id =:depot and (magasinSouce.id=:magasin or magasinDestination.id=:magasin)");
		 query.setParameter("mp", mp.getId());
		 query.setParameter("statut",statut);
		 query.setParameter("depot",depot.getId());
		 query.setParameter("magasin",magasin.getId());
		 query.setParameter("fournisseurMP",fournisseurMP.getId());
		 return (DetailTransferStockMP) query.uniqueResult();
				
}


	
	public DetailTransferStockMP findDetailTransferStockMPByMPByTransferMP(MatierePremier mp,TransferStockMP transferMP) {
		
		// TODO Auto-generated method stub
		Query query= session.createQuery("select d from DetailTransferStockMP d  where matierePremier.id=:mp and  transferStockMP.id=:transferMP");
		 query.setParameter("mp", mp.getId());
		 query.setParameter("transferMP",transferMP.getId());
		 return (DetailTransferStockMP) query.uniqueResult();
				
}
	
	
	public List<DetailTransferStockMP> findDetailTransferStockMPByMPByTransferMPList(MatierePremier mp,TransferStockMP transferMP) {
		
		// TODO Auto-generated method stub
		Query query= session.createQuery("select d from DetailTransferStockMP d  where matierePremier.id=:mp and  transferStockMP.id=:transferMP");
		 query.setParameter("mp", mp.getId());
		 query.setParameter("transferMP",transferMP.getId());
		 return  query.list();
				
}
	
	
	public DetailTransferStockMP findDetailTransferStockMPByMPByTransferMPByFournisseur(MatierePremier mp,TransferStockMP transferMP,FournisseurMP fournisseurMP) {
		
		// TODO Auto-generated method stub
		Query query= session.createQuery("select d from DetailTransferStockMP d  where matierePremier.id=:mp and  transferStockMP.id=:transferMP and fournisseur.id=:fournisseurMP");
		 query.setParameter("mp", mp.getId());
		 query.setParameter("transferMP",transferMP.getId());
		 query.setParameter("fournisseurMP",fournisseurMP.getId());
		 return (DetailTransferStockMP) query.uniqueResult();
				
}
	
	
	public List<DetailTransferStockMP> listDetailTransferStockMPEnPFByReq(String req) {
		
		// TODO Auto-generated method stub
		Query query= session.createQuery("select d from DetailTransferStockMP d where d.transferStockMP.CodeTransfer in (select d.CodeTransfer from TransferStockPF d )  "+req+" group by d.transferStockMP.CodeTransfer");
		
		 return  query.list();
				
}
	
	
	
	
	public List<DetailTransferStockMP> listDetailTransferStockMPByMPByTransferMPByFournisseur() {
		
		// TODO Auto-generated method stub
		Query query= session.createQuery("select d from DetailTransferStockMP d  group by d.matierePremier,d.fournisseur");
		
		 return  query.list();
				
}
	
	
	
	// liste des MP de Statut x entre deux date
	
	
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPStatutAchat(Date dateDebut , Date dateFin , MatierePremier mp,String statut,Magasin magasin) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(dateDebut!=null && dateFin!=null && mp!=null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer between :dateDebut and :dateFin and matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
		}else if(dateDebut!=null && dateFin!=null && mp==null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where  transferStockMP.dateTransfer between :dateDebut and :dateFin and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut==null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin");
			
			 query.setParameter("mp", mp.getId());
			 query.setParameter("statut",statut);
			 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut!=null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  transferStockMP.dateTransfer =:dateDebut and matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
				
		}else if(dateDebut!=null && dateFin==null && mp==null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  transferStockMP.dateTransfer =:dateDebut and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}
				
			
				
				return query.list();
}
	
	
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPStatutCharge(Date dateDebut , Date dateFin , MatierePremier mp,String statut,Magasin magasin) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(dateDebut!=null && dateFin!=null && mp!=null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer between :dateDebut and :dateFin and matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
		}else if(dateDebut!=null && dateFin!=null && mp==null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where  transferStockMP.dateTransfer between :dateDebut and :dateFin and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut==null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin");
			
			 query.setParameter("mp", mp.getId());
			 query.setParameter("statut",statut);
			 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut!=null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  transferStockMP.dateTransfer =:dateDebut and matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
				
		}else if(dateDebut!=null && dateFin==null && mp==null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  transferStockMP.dateTransfer =:dateDebut and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}
				
			
				
				return query.list();
}
	
	
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPStatutVente(Date dateDebut , Date dateFin , MatierePremier mp,String statut,Magasin magasin) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(dateDebut!=null && dateFin!=null && mp!=null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer between :dateDebut and :dateFin and matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
		}else if(dateDebut!=null && dateFin!=null && mp==null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where  transferStockMP.dateTransfer between :dateDebut and :dateFin and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut==null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin");
			
			 query.setParameter("mp", mp.getId());
			 query.setParameter("statut",statut);
			 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut!=null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  transferStockMP.dateTransfer =:dateDebut and matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
				
		}else if(dateDebut!=null && dateFin==null && mp==null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  transferStockMP.dateTransfer =:dateDebut and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}
				
			
				
				return query.list();
}
	
	
	
	
	
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPStatutAvoir(Date dateDebut , Date dateFin , MatierePremier mp,String statut,Magasin magasin) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(dateDebut!=null && dateFin!=null && mp!=null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer between :dateDebut and :dateFin and matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
		}else if(dateDebut!=null && dateFin!=null && mp==null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where  transferStockMP.dateTransfer between :dateDebut and :dateFin and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut==null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin");
			
			 query.setParameter("mp", mp.getId());
			 query.setParameter("statut",statut);
			 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut!=null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  transferStockMP.dateTransfer =:dateDebut and matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
				
		}else if(dateDebut!=null && dateFin==null && mp==null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  transferStockMP.dateTransfer =:dateDebut and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}
				
			
				
				return query.list();
}
	
	
	
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPStatutChargeSupp(Date dateDebut , Date dateFin , MatierePremier mp,String statut,Magasin magasin) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(dateDebut!=null && dateFin!=null && mp!=null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer between :dateDebut and :dateFin and matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
		}else if(dateDebut!=null && dateFin!=null && mp==null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where  transferStockMP.dateTransfer between :dateDebut and :dateFin and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut==null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin");
			
			 query.setParameter("mp", mp.getId());
			 query.setParameter("statut",statut);
			 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut!=null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  transferStockMP.dateTransfer =:dateDebut and matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
				
		}else if(dateDebut!=null && dateFin==null && mp==null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  transferStockMP.dateTransfer =:dateDebut and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}
				
			
				
				return query.list();
}	


	
	// Transfer Stock MP Service 
	

	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPStatutService(Date dateDebut , Date dateFin , MatierePremier mp,String statut,Magasin magasin) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(dateDebut!=null && dateFin!=null && mp!=null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer between :dateDebut and :dateFin and matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
		}else if(dateDebut!=null && dateFin!=null && mp==null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where  transferStockMP.dateTransfer between :dateDebut and :dateFin and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut==null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin");
			
			 query.setParameter("mp", mp.getId());
			 query.setParameter("statut",statut);
			 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut!=null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  transferStockMP.dateTransfer =:dateDebut and matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
				
		}else if(dateDebut!=null && dateFin==null && mp==null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  transferStockMP.dateTransfer =:dateDebut and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}
				
			
				
				return query.list();
}
	
	
	
	
	
	
	//list des MP de Statut X et entre deux date distinct
	
	
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutInitial(Date dateDebut , Date dateFin , MatierePremier mp,String statut, Magasin magasin) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(dateDebut!=null && dateFin!=null && mp!=null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer between :dateDebut and :dateFin and matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut!=null && dateFin!=null && mp==null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.statut=:statut and  transferStockMP.dateTransfer between :dateDebut and :dateFin and  magasinDestination.id=:magasin  group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut==null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  matierePremier.id=:mp and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin  group by matierePremier");
			
			 query.setParameter("mp", mp.getId());
			 query.setParameter("statut",statut);
			 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut!=null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer =:dateDebut and matierePremier.id=:mp and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin  group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
				
		}else if(dateDebut!=null && dateFin==null && mp==null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer =:dateDebut and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin  group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}
				
			
				
				return query.list();
}
	

	
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutAchat(Date dateDebut , Date dateFin , MatierePremier mp,String statut, Magasin magasin) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(dateDebut!=null && dateFin!=null && mp!=null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer between :dateDebut and :dateFin and matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut!=null && dateFin!=null && mp==null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.statut=:statut and  transferStockMP.dateTransfer between :dateDebut and :dateFin and  magasinDestination.id=:magasin  group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut==null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  matierePremier.id=:mp and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin  group by matierePremier");
			
			 query.setParameter("mp", mp.getId());
			 query.setParameter("statut",statut);
			 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut!=null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer =:dateDebut and matierePremier.id=:mp and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin  group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
				
		}else if(dateDebut!=null && dateFin==null && mp==null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer =:dateDebut and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin  group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}
				
			
				
				return query.list();
}
		
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutCharge(Date dateDebut , Date dateFin , MatierePremier mp,String statut, Magasin magasin) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(dateDebut!=null && dateFin!=null && mp!=null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer between :dateDebut and :dateFin and matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut!=null && dateFin!=null && mp==null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.statut=:statut and  transferStockMP.dateTransfer between :dateDebut and :dateFin and  magasinSouce.id=:magasin  group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut==null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  matierePremier.id=:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin  group by matierePremier");
			
			 query.setParameter("mp", mp.getId());
			 query.setParameter("statut",statut);
			 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut!=null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer =:dateDebut and matierePremier.id=:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin  group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
				
		}else if(dateDebut!=null && dateFin==null && mp==null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer =:dateDebut and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin  group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}
				
			
				
				return query.list();
}
	
	
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutVente(Date dateDebut , Date dateFin , MatierePremier mp,String statut, Magasin magasin) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(dateDebut!=null && dateFin!=null && mp!=null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer between :dateDebut and :dateFin and matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut!=null && dateFin!=null && mp==null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.statut=:statut and  transferStockMP.dateTransfer between :dateDebut and :dateFin and  magasinSouce.id=:magasin  group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut==null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  matierePremier.id=:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin  group by matierePremier");
			
			 query.setParameter("mp", mp.getId());
			 query.setParameter("statut",statut);
			 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut!=null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer =:dateDebut and matierePremier.id=:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin  group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
				
		}else if(dateDebut!=null && dateFin==null && mp==null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer =:dateDebut and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin  group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}
				
			
				
				return query.list();
}

	
	
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutChargeSupp(Date dateDebut , Date dateFin , MatierePremier mp,String statut, Magasin magasin) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(dateDebut!=null && dateFin!=null && mp!=null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer between :dateDebut and :dateFin and matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut!=null && dateFin!=null && mp==null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.statut=:statut and  transferStockMP.dateTransfer between :dateDebut and :dateFin and  magasinSouce.id=:magasin  group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut==null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  matierePremier.id=:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin  group by matierePremier");
			
			 query.setParameter("mp", mp.getId());
			 query.setParameter("statut",statut);
			 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut!=null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer =:dateDebut and matierePremier.id=:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin  group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
				
		}else if(dateDebut!=null && dateFin==null && mp==null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer =:dateDebut and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin  group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}
				
			
				
				return query.list();
}
	
	
	// transfer Stock MP Service Distinct
	

	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutService(Date dateDebut , Date dateFin , MatierePremier mp,String statut, Magasin magasin) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(dateDebut!=null && dateFin!=null && mp!=null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer between :dateDebut and :dateFin and matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut!=null && dateFin!=null && mp==null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.statut=:statut and  transferStockMP.dateTransfer between :dateDebut and :dateFin and  magasinSouce.id=:magasin  group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut==null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  matierePremier.id=:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin  group by matierePremier");
			
			 query.setParameter("mp", mp.getId());
			 query.setParameter("statut",statut);
			 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut!=null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer =:dateDebut and matierePremier.id=:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin  group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
				
		}else if(dateDebut!=null && dateFin==null && mp==null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer =:dateDebut and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin  group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}
				
			
				
				return query.list();
}
	
	
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutAvoir(Date dateDebut , Date dateFin , MatierePremier mp,String statut, Magasin magasin) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(dateDebut!=null && dateFin!=null && mp!=null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer between :dateDebut and :dateFin and matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut!=null && dateFin!=null && mp==null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.statut=:statut and  transferStockMP.dateTransfer between :dateDebut and :dateFin and  magasinDestination.id=:magasin  group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut==null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  matierePremier.id=:mp and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin  group by matierePremier");
			
			 query.setParameter("mp", mp.getId());
			 query.setParameter("statut",statut);
			 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut!=null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer =:dateDebut and matierePremier.id=:mp and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin  group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
				
		}else if(dateDebut!=null && dateFin==null && mp==null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer =:dateDebut and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin  group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}
				
			
				
				return query.list();
}
	
	
	
	// list detail transfer stock MP 
	
	public List<DetailTransferStockMP> findDetailTransferMPByNumOFStatut(String NUmOF , Magasin magasin,String statut) {
		
		// TODO Auto-generated method stub
		Query query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.CodeTransfer=:NUmOF and (magasinSouce.id=:magasin or magasinDestination.id=:magasin) and transferStockMP.statut=:statut ");
		 query.setParameter("magasin",magasin.getId());
		 query.setParameter("NUmOF",NUmOF);
		 query.setParameter("statut",statut);
		 return query.list();
				
}
	
	
	public DetailTransferStockMP findDetailTransferMPByCodeByMP(String codetransfert , MatierePremier mp,String statut) {
		
		// TODO Auto-generated method stub
		Query query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.CodeTransfer=:codetransfert and  matierePremier.id=:mp and transferStockMP.statut=:statut");
		 query.setParameter("codetransfert",codetransfert);
		 query.setParameter("mp",mp.getId());
		 query.setParameter("statut",statut);
		 return (DetailTransferStockMP) query.uniqueResult();
				
}
	
	
	
	
	
	

	// list detail transfer stock MP charge ou cherge service
	public List<DetailTransferStockMP> findDetailTransferMPchargeouchargeservice(String charge , String  chargeservice) {
		
		// TODO Auto-generated method stub
		Query query= session.createQuery("select d from DetailTransferStockMP d where  transferStockMP.statut=:charge or transferStockMP.statut=:chargeservice");
		 query.setParameter("charge",charge);
		 query.setParameter("chargeservice",chargeservice);
		 return query.list();
				
}
	
	
	@Override
	public List<Object[]> listeStockFinaleMPByMagasinBySubCategorieByCategorieBayMP(Date dateDebut,  Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp ) {
		// TODO Auto-generated method stub
		Query query=null;
		//+ sum( case when d.transferStockMP.statut=:TRANSFER then d.quantite else 0 end)
//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
			
		if(dateDebut!=null && magasin!=null && subCategorieMp==null && categorieMp==null && mp==null)
		{
			query= session.createQuery("select  d.matierePremier , sum( case when t.statut=:Initial and d.magasinDestination.id=:magasin  then d.quantite else 0 end), sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin   then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin  then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:SORTIE and t.soustype.id is null and d.magasinSouce.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when (t.statut=:SORTIE and d.magasinSouce.id=:magasin  and t.soustype.id <> null) then d.quantite else 0 end) , sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) ,  sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin  then d.quantite else 0 end) ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end)  from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and t.dateTransfer <:dateDebut  group by d.matierePremier ");
			//query.setParameter("Cheque", "Chèque");
			// query.setParameter("Virement", "Virement");
			 query.setParameter("dateDebut", dateDebut);
			 
			 query.setParameter("Initial", Constantes.ETAT_TRANSFER_STOCK_INITIAL);
			 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			 query.setParameter("PERTE", Constantes.TYPE_PERTE);
		  query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
		  query.setParameter("CHARGE_SUPP",Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP); 
		  query.setParameter("SORTIE", Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
		  query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
		  query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
		  query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
		  query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
		  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
		  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
		  query.setParameter("magasin",magasin.getId());
		  
		}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp==null)
		{
			
			query= session.createQuery("select  d.matierePremier , sum( case when t.statut=:Initial and d.magasinDestination.id=:magasin  then d.quantite else 0 end), sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin   then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin  then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:SORTIE and t.soustype.id is null and d.magasinSouce.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when (t.statut=:SORTIE and d.magasinSouce.id=:magasin  and t.soustype.id <> null) then d.quantite else 0 end) , sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) ,  sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin  then d.quantite else 0 end) ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end)  from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and  d.matierePremier.categorieMp.subCategorieMp.id=:subCategorieMp and   d.transferStockMP.dateTransfer <:dateDebut  group by d.matierePremier ");
			//query.setParameter("Cheque", "Chèque"); 
			// query.setParameter("Virement", "Virement");
			 query.setParameter("dateDebut", dateDebut);
			 query.setParameter("Initial", Constantes.ETAT_TRANSFER_STOCK_INITIAL);
			 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			 query.setParameter("PERTE", Constantes.TYPE_PERTE);
		  query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
		  query.setParameter("CHARGE_SUPP",Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP); 
		  query.setParameter("SORTIE", Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
		  query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
		  query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
		  query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
		  query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
		  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
		  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
		  query.setParameter("magasin",magasin.getId());
		  query.setParameter("subCategorieMp",subCategorieMp.getId());
		  
			
		}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp==null)
		{
			
			query= session.createQuery("select  d.matierePremier , sum( case when t.statut=:Initial and d.magasinDestination.id=:magasin  then d.quantite else 0 end), sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin   then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin  then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:SORTIE and t.soustype.id is null and d.magasinSouce.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when (t.statut=:SORTIE and d.magasinSouce.id=:magasin  and t.soustype.id <> null) then d.quantite else 0 end) , sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) ,  sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end)  from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and  d.matierePremier.categorieMp.id=:categorieMp and   d.transferStockMP.dateTransfer <:dateDebut  group by d.matierePremier ");
			//query.setParameter("Cheque", "Chèque");
			// query.setParameter("Virement", "Virement");
			 query.setParameter("dateDebut", dateDebut);
			 query.setParameter("Initial", Constantes.ETAT_TRANSFER_STOCK_INITIAL);
			 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			 query.setParameter("PERTE", Constantes.TYPE_PERTE);
		  query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
		  query.setParameter("CHARGE_SUPP",Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP); 
		  query.setParameter("SORTIE", Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
		  query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
		  query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
		  query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
		  query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
		  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
		  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
		  query.setParameter("magasin",magasin.getId());
		  query.setParameter("categorieMp",categorieMp.getId());
		 
			
		}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp!=null)
		{
			
			query= session.createQuery("select  d.matierePremier , sum( case when t.statut=:Initial and d.magasinDestination.id=:magasin  then d.quantite else 0 end), sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin   then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin  then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:SORTIE and t.soustype.id is null and d.magasinSouce.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when (t.statut=:SORTIE and d.magasinSouce.id=:magasin  and t.soustype.id <> null) then d.quantite else 0 end) , sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) ,  sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end)   from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and  d.matierePremier.categorieMp.id=:categorieMp and d.matierePremier.id=:mp and   d.transferStockMP.dateTransfer <:dateDebut  group by d.matierePremier ");
			//query.setParameter("Cheque", "Chèque");
			// query.setParameter("Virement", "Virement");
			 query.setParameter("dateDebut", dateDebut);
			 query.setParameter("Initial", Constantes.ETAT_TRANSFER_STOCK_INITIAL);
			 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			 query.setParameter("PERTE", Constantes.TYPE_PERTE);
		  query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
		  query.setParameter("CHARGE_SUPP",Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP); 
		  query.setParameter("SORTIE", Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
		  query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
		  query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
		  query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
		  query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
		  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
		  query.setParameter("magasin",magasin.getId());
		  query.setParameter("categorieMp",categorieMp.getId());
		  query.setParameter("mp",mp.getId());
		  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			
		}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp!=null)
		{
			
			query= session.createQuery("select  d.matierePremier , sum( case when t.statut=:Initial and d.magasinDestination.id=:magasin  then d.quantite else 0 end), sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin   then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin  then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:SORTIE and t.soustype.id is null and d.magasinSouce.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when (t.statut=:SORTIE and d.magasinSouce.id=:magasin  and t.soustype.id <> null) then d.quantite else 0 end) , sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) ,  sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and   d.matierePremier.categorieMp.subCategorieMp.id=:subcategorieMp and d.matierePremier.id=:mp and   d.transferStockMP.dateTransfer <:dateDebut  group by d.matierePremier ");
			//query.setParameter("Cheque", "Chèque");
			// query.setParameter("Virement", "Virement");
			 query.setParameter("dateDebut", dateDebut);
			 query.setParameter("Initial", Constantes.ETAT_TRANSFER_STOCK_INITIAL);
			 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			
			 query.setParameter("PERTE", Constantes.TYPE_PERTE);
		  query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
		  query.setParameter("CHARGE_SUPP",Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP); 
		  query.setParameter("SORTIE", Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
		  query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
		  query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
		  query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
		  query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
		  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
		  query.setParameter("magasin",magasin.getId());
		  query.setParameter("subcategorieMp",subCategorieMp.getId());
		  query.setParameter("mp",mp.getId());
		  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
		}
		
		
		 
		return query.list();

	}
	
	
	
	
	

	
	
	@Override
	public List<Object[]> listeStockFinaleMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseur(Date dateDebut,  Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp ) {
		// TODO Auto-generated method stub
		Query query=null;
		//+ sum( case when d.transferStockMP.statut=:TRANSFER then d.quantite else 0 end)
//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
			
		if(dateDebut!=null && magasin!=null && subCategorieMp==null && categorieMp==null && mp==null)
		{
			
			query= session.createQuery("select  d.matierePremier ,d.fournisseur,  sum( case when t.statut=:Initial and d.magasinDestination.id=:magasin  then d.quantite else 0 end), sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin   then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin  then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:SORTIE and t.soustype.id is null and d.magasinSouce.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when (t.statut=:SORTIE and d.magasinSouce.id=:magasin  and t.soustype.id <> null) then d.quantite else 0 end) , sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) ,  sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end)   from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id  and   d.transferStockMP.dateTransfer <:dateDebut  group by d.matierePremier,d.fournisseur ");
			//query.setParameter("Cheque", "Chèque");
			// query.setParameter("Virement", "Virement");
			 query.setParameter("dateDebut", dateDebut);
			 query.setParameter("Initial", Constantes.ETAT_TRANSFER_STOCK_INITIAL);
			 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			 query.setParameter("PERTE", Constantes.TYPE_PERTE);
		  query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
		  query.setParameter("CHARGE_SUPP",Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP); 
		  query.setParameter("SORTIE", Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
		  query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
		  query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
		  query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
		  query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
		  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
		  query.setParameter("magasin",magasin.getId());
		 
		  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);

		  
		  
		  
		  
		  
		}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp==null)
		{
			
			query= session.createQuery("select  d.matierePremier ,d.fournisseur,  sum( case when t.statut=:Initial and d.magasinDestination.id=:magasin  then d.quantite else 0 end), sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin   then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin  then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:SORTIE and t.soustype.id is null and d.magasinSouce.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when (t.statut=:SORTIE and d.magasinSouce.id=:magasin  and t.soustype.id <> null) then d.quantite else 0 end) , sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) ,  sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end)   from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and  d.matierePremier.categorieMp.subCategorieMp.id=:subcategorieMp  and   d.transferStockMP.dateTransfer <:dateDebut  group by d.matierePremier,d.fournisseur ");
			//query.setParameter("Cheque", "Chèque");
			// query.setParameter("Virement", "Virement");
			 query.setParameter("dateDebut", dateDebut);
			 query.setParameter("Initial", Constantes.ETAT_TRANSFER_STOCK_INITIAL);
			 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			 query.setParameter("PERTE", Constantes.TYPE_PERTE);
		  query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
		  query.setParameter("CHARGE_SUPP",Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP); 
		  query.setParameter("SORTIE", Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
		  query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
		  query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
		  query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
		  query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
		  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
		  query.setParameter("magasin",magasin.getId());
		  query.setParameter("subcategorieMp",subCategorieMp.getId());		
		  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			
		}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp==null)
		{
			
			query= session.createQuery("select  d.matierePremier ,d.fournisseur,  sum( case when t.statut=:Initial and d.magasinDestination.id=:magasin  then d.quantite else 0 end), sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin   then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin  then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:SORTIE and t.soustype.id is null and d.magasinSouce.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when (t.statut=:SORTIE and d.magasinSouce.id=:magasin  and t.soustype.id <> null) then d.quantite else 0 end) , sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) ,  sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end)   from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and  d.matierePremier.categorieMp.id=:categorieMp  and   d.transferStockMP.dateTransfer <:dateDebut  group by d.matierePremier,d.fournisseur ");
			//query.setParameter("Cheque", "Chèque");
			// query.setParameter("Virement", "Virement");
			 query.setParameter("dateDebut", dateDebut);
			 query.setParameter("Initial", Constantes.ETAT_TRANSFER_STOCK_INITIAL);
			 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			 query.setParameter("PERTE", Constantes.TYPE_PERTE);
		  query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
		  query.setParameter("CHARGE_SUPP",Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP); 
		  query.setParameter("SORTIE", Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
		  query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
		  query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
		  query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
		  query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
		  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
		  query.setParameter("magasin",magasin.getId());
		  query.setParameter("categorieMp",categorieMp.getId());
		
		  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
		  
		  
		  
		}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp!=null)
		{
			
			query= session.createQuery("select  d.matierePremier ,d.fournisseur,  sum( case when t.statut=:Initial and d.magasinDestination.id=:magasin  then d.quantite else 0 end), sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin   then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin  then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:SORTIE and t.soustype.id is null and d.magasinSouce.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when (t.statut=:SORTIE and d.magasinSouce.id=:magasin  and t.soustype.id <> null) then d.quantite else 0 end) , sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) ,  sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end)   from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and  d.matierePremier.categorieMp.id=:categorieMp and d.matierePremier.id=:mp and   d.transferStockMP.dateTransfer <:dateDebut  group by d.matierePremier,d.fournisseur ");
			//query.setParameter("Cheque", "Chèque");
			// query.setParameter("Virement", "Virement");
			 query.setParameter("dateDebut", dateDebut);
			 query.setParameter("Initial", Constantes.ETAT_TRANSFER_STOCK_INITIAL);
			 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			 query.setParameter("PERTE", Constantes.TYPE_PERTE);
		  query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
		  query.setParameter("CHARGE_SUPP",Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP); 
		  query.setParameter("SORTIE", Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
		  query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
		  query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
		  query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
		  query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
		  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
		  query.setParameter("magasin",magasin.getId());
		  query.setParameter("categorieMp",categorieMp.getId());
		  query.setParameter("mp",mp.getId());
		  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			
		}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp!=null)
		{
			
		
		  
		  
			query= session.createQuery("select  d.matierePremier ,d.fournisseur,  sum( case when t.statut=:Initial and d.magasinDestination.id=:magasin  then d.quantite else 0 end), sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin   then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin  then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:SORTIE and t.soustype.id is null and d.magasinSouce.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when (t.statut=:SORTIE and d.magasinSouce.id=:magasin  and t.soustype.id <> null) then d.quantite else 0 end) , sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) ,  sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end)   from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and   d.matierePremier.categorieMp.subCategorieMp.id=:subcategorieMp and d.matierePremier.id=:mp and   d.transferStockMP.dateTransfer <:dateDebut  group by d.matierePremier,d.fournisseur ");
			//query.setParameter("Cheque", "Chèque");
			// query.setParameter("Virement", "Virement");
			 query.setParameter("dateDebut", dateDebut);
			 query.setParameter("Initial", Constantes.ETAT_TRANSFER_STOCK_INITIAL);
			 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			 query.setParameter("PERTE", Constantes.TYPE_PERTE);
		  query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
		  query.setParameter("CHARGE_SUPP",Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP); 
		  query.setParameter("SORTIE", Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
		  query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
		  query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
		  query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
		  query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
		  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
		  query.setParameter("magasin",magasin.getId());
		  query.setParameter("subcategorieMp",subCategorieMp.getId());
		  query.setParameter("mp",mp.getId());
		  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
		  
		  
		  
		  
			
		}
		
		
		 
		return query.list();

	}
	
	
	
	@Override
	public List<Object[]> listeStockFinaleMPByMagasinBySubCategorieByCategorieBayMPNonThe(Date dateDebut,  Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp ) {
		// TODO Auto-generated method stub
		Query query=null;
		//+ sum( case when d.transferStockMP.statut=:TRANSFER then d.quantite else 0 end)
//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
			
		if(dateDebut!=null && magasin!=null && subCategorieMp==null && categorieMp==null && mp==null)
		{
			query= session.createQuery("select  d.matierePremier ,  sum( case when t.statut=:Initial  then d.quantite else 0 end), sum( case when t.statut=:AJOUT  then d.quantite else 0 end),sum( case when t.statut=:ENTRE  then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge  then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when (t.statut=:SORTIE and t.soustype.id is null) then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR ) then d.QuantiteRetour else 0 end),(sum( case when (t.statut=:SORTIE  and t.soustype.id <> null) then d.quantite else 0 end) + sum( case when t.statut=:SORTIE_PF  then d.quantite else 0 end) + sum( case when t.statut=:SORTIEENATTENT  then d.quantite else 0 end) +  sum( case when t.statut=:PERTE  then d.quantite else 0 end)) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and t.dateTransfer <:dateDebut and ( d.magasinSouce.id=:magasin or d.magasinDestination.id=:magasin) and  group by d.matierePremier ");
			//query.setParameter("Cheque", "Chèque");
			// query.setParameter("Virement", "Virement");
			 query.setParameter("dateDebut", dateDebut);
			 
			 query.setParameter("Initial", Constantes.ETAT_TRANSFER_STOCK_INITIAL);
			 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			 query.setParameter("PERTE", Constantes.TYPE_PERTE);
		  query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
		  query.setParameter("CHARGE_SUPP",Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP); 
		  query.setParameter("SORTIE", Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
		  query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
		  query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
		  query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
		  query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
		  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
		  query.setParameter("magasin",magasin.getId());
		}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp==null)
		{
			
			query= session.createQuery("select  d.matierePremier ,sum( case when t.statut=:Initial  then d.quantite else 0 end), sum( case when t.statut=:AJOUT  then d.quantite else 0 end),sum( case when t.statut=:ENTRE  then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge  then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when (t.statut=:SORTIE and t.soustype.id is null) then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR ) then d.QuantiteRetour else 0 end),(sum( case when (t.statut=:SORTIE  and t.soustype.id <> null) then d.quantite else 0 end) + sum( case when t.statut=:SORTIE_PF  then d.quantite else 0 end) + sum( case when t.statut=:SORTIEENATTENT  then d.quantite else 0 end)+  sum( case when t.statut=:PERTE  then d.quantite else 0 end)) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and  d.matierePremier.categorieMp.subCategorieMp.id !=:subCategorieMp and   d.transferStockMP.dateTransfer <:dateDebut and ( d.magasinSouce.id=:magasin or d.magasinDestination.id=:magasin) group by d.matierePremier ");
			//query.setParameter("Cheque", "Chèque"); 
			// query.setParameter("Virement", "Virement");
			 query.setParameter("dateDebut", dateDebut);
			 query.setParameter("Initial", Constantes.ETAT_TRANSFER_STOCK_INITIAL);
			 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			 query.setParameter("PERTE", Constantes.TYPE_PERTE);
		  query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
		  query.setParameter("CHARGE_SUPP",Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP); 
		  query.setParameter("SORTIE", Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
		  query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
		  query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
		  query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
		  query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
		  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
		  query.setParameter("magasin",magasin.getId());
		  query.setParameter("subCategorieMp",subCategorieMp.getId());
			
		}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp==null)
		{
			
			query= session.createQuery("select  d.matierePremier ,sum( case when t.statut=:Initial  then d.quantite else 0 end), sum( case when t.statut=:AJOUT  then d.quantite else 0 end),sum( case when t.statut=:ENTRE  then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge  then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when (t.statut=:SORTIE and t.soustype.id is null) then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR ) then d.QuantiteRetour else 0 end),(sum( case when (t.statut=:SORTIE  and t.soustype.id <> null) then d.quantite else 0 end) + sum( case when t.statut=:SORTIE_PF  then d.quantite else 0 end) + sum( case when t.statut=:SORTIEENATTENT  then d.quantite else 0 end) +  sum( case when t.statut=:PERTE  then d.quantite else 0 end)) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and  d.matierePremier.categorieMp.id=:categorieMp and   d.transferStockMP.dateTransfer <:dateDebut and ( d.magasinSouce.id=:magasin or d.magasinDestination.id=:magasin) group by d.matierePremier ");
			//query.setParameter("Cheque", "Chèque");
			// query.setParameter("Virement", "Virement");
			 query.setParameter("dateDebut", dateDebut);
			 query.setParameter("Initial", Constantes.ETAT_TRANSFER_STOCK_INITIAL);
			 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			 query.setParameter("PERTE", Constantes.TYPE_PERTE);
		  query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
		  query.setParameter("CHARGE_SUPP",Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP); 
		  query.setParameter("SORTIE", Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
		  query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
		  query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
		  query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
		  query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
		  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
		  query.setParameter("magasin",magasin.getId());
		  query.setParameter("categorieMp",categorieMp.getId());
			
		}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp!=null)
		{
			
			query= session.createQuery("select  d.matierePremier ,sum( case when t.statut=:Initial  then d.quantite else 0 end), sum( case when t.statut=:AJOUT  then d.quantite else 0 end),sum( case when t.statut=:ENTRE  then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge  then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when (t.statut=:SORTIE and t.soustype.id is null) then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR ) then d.QuantiteRetour else 0 end),(sum( case when (t.statut=:SORTIE  and t.soustype.id <> null) then d.quantite else 0 end) + sum( case when t.statut=:SORTIE_PF  then d.quantite else 0 end) + sum( case when t.statut=:SORTIEENATTENT  then d.quantite else 0 end) +  sum( case when t.statut=:PERTE  then d.quantite else 0 end)) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and  d.matierePremier.categorieMp.id=:categorieMp and d.matierePremier.id=:mp and   d.transferStockMP.dateTransfer <:dateDebut and ( d.magasinSouce.id=:magasin or d.magasinDestination.id=:magasin) group by d.matierePremier ");
			//query.setParameter("Cheque", "Chèque");
			// query.setParameter("Virement", "Virement");
			 query.setParameter("dateDebut", dateDebut);
			 query.setParameter("Initial", Constantes.ETAT_TRANSFER_STOCK_INITIAL);
			 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			 query.setParameter("PERTE", Constantes.TYPE_PERTE);
		  query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
		  query.setParameter("CHARGE_SUPP",Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP); 
		  query.setParameter("SORTIE", Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
		  query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
		  query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
		  query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
		  query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
		  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
		  query.setParameter("magasin",magasin.getId());
		  query.setParameter("categorieMp",categorieMp.getId());
		  query.setParameter("mp",mp.getId());
			
		}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp!=null)
		{
			
			query= session.createQuery("select  d.matierePremier ,sum( case when t.statut=:Initial  then d.quantite else 0 end), sum( case when t.statut=:AJOUT  then d.quantite else 0 end),sum( case when t.statut=:ENTRE  then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge  then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when (t.statut=:SORTIE and t.soustype.id is null) then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR ) then d.QuantiteRetour else 0 end),(sum( case when (t.statut=:SORTIE  and t.soustype.id <> null) then d.quantite else 0 end) + sum( case when t.statut=:SORTIE_PF  then d.quantite else 0 end) + sum( case when t.statut=:SORTIEENATTENT  then d.quantite else 0 end) +  sum( case when t.statut=:PERTE  then d.quantite else 0 end)) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and   d.matierePremier.categorieMp.subCategorieMp.id !=:subcategorieMp and d.matierePremier.id=:mp and   d.transferStockMP.dateTransfer <:dateDebut and ( d.magasinSouce.id=:magasin or d.magasinDestination.id=:magasin) group by d.matierePremier ");
			//query.setParameter("Cheque", "Chèque");
			// query.setParameter("Virement", "Virement");
			 query.setParameter("dateDebut", dateDebut);
			 query.setParameter("Initial", Constantes.ETAT_TRANSFER_STOCK_INITIAL);
			 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			
			 query.setParameter("PERTE", Constantes.TYPE_PERTE);
		  query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
		  query.setParameter("CHARGE_SUPP",Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP); 
		  query.setParameter("SORTIE", Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
		  query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
		  query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
		  query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
		  query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
		  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
		  query.setParameter("magasin",magasin.getId());
		  query.setParameter("subcategorieMp",subCategorieMp.getId());
		  query.setParameter("mp",mp.getId());
			
		}
		
		
		 
		return query.list();

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public List<Object[]> listeStockInitialMPByMagasinBySubCategorieByCategorieBayMP(Date dateDebut,  Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp ) {
		// TODO Auto-generated method stub
		Query query=null;
		//+ sum( case when d.transferStockMP.statut=:TRANSFER then d.quantite else 0 end)
//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
			
		if(dateDebut!=null && magasin!=null && subCategorieMp==null && categorieMp==null && mp==null)
		{
			query= session.createQuery("select d.matierePremier , sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=:magasin  then d.quantite else 0 end) ,  d.prixUnitaire  from DetailTransferStockMP d  where  d.transferStockMP.dateTransfer =:dateDebut group by d.matierePremier ");
			//query.setParameter("Cheque", "Chèque");
			// query.setParameter("Virement", "Virement");
			 query.setParameter("dateDebut", dateDebut);
			 
			 query.setParameter("Initial", Constantes.ETAT_TRANSFER_STOCK_INITIAL);
			
		  query.setParameter("magasin",magasin.getId());
		}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp==null)
		{
			
			query= session.createQuery("select d.matierePremier , sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=:magasin  then d.quantite else 0 end) , d.prixUnitaire  from DetailTransferStockMP d  where  d.matierePremier.categorieMp.subCategorieMp.id=:subCategorieMp and   d.transferStockMP.dateTransfer =:dateDebut group by d.matierePremier ");
			//query.setParameter("Cheque", "Chèque");
			// query.setParameter("Virement", "Virement");
			 query.setParameter("dateDebut", dateDebut);
			 query.setParameter("Initial", Constantes.ETAT_TRANSFER_STOCK_INITIAL);
			
		  query.setParameter("magasin",magasin.getId());
		  query.setParameter("subCategorieMp",subCategorieMp.getId());
			
		}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp==null)
		{
			
			query= session.createQuery("select d.matierePremier , sum( case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=:magasin  then d.quantite else 0 end) ,  d.prixUnitaire from DetailTransferStockMP d  where  d.matierePremier.categorieMp.id=:categorieMp and   d.transferStockMP.dateTransfer =:dateDebut group by d.matierePremier ");
			//query.setParameter("Cheque", "Chèque");
			// query.setParameter("Virement", "Virement");
			 query.setParameter("dateDebut", dateDebut);
			 query.setParameter("Initial", Constantes.ETAT_TRANSFER_STOCK_INITIAL);
			
		  query.setParameter("magasin",magasin.getId());
		  query.setParameter("categorieMp",categorieMp.getId());
			
		}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp!=null)
		{
			
			query= session.createQuery("select d.matierePremier , sum( case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=:magasin  then d.quantite else 0 end) ,  d.prixUnitaire from DetailTransferStockMP d  where  d.matierePremier.categorieMp.id=:categorieMp and d.matierePremier.id=:mp and   d.transferStockMP.dateTransfer =:dateDebut group by d.matierePremier ");
			//query.setParameter("Cheque", "Chèque");
			// query.setParameter("Virement", "Virement");
			 query.setParameter("dateDebut", dateDebut);
			 query.setParameter("Initial", Constantes.ETAT_TRANSFER_STOCK_INITIAL);
			
		  query.setParameter("magasin",magasin.getId());
		  query.setParameter("categorieMp",categorieMp.getId());
		  query.setParameter("mp",mp.getId());
			
		}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp!=null)
		{
			
			query= session.createQuery("select d.matierePremier , sum( case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=:magasin  then d.quantite else 0 end) ,d.prixUnitaire  from DetailTransferStockMP d  where  d.matierePremier.categorieMp.subCategorieMp.id=:subcategorieMp and d.matierePremier.id=:mp and   d.transferStockMP.dateTransfer =:dateDebut group by d.matierePremier ");
			//query.setParameter("Cheque", "Chèque");
			// query.setParameter("Virement", "Virement");
			 query.setParameter("dateDebut", dateDebut);
			 query.setParameter("Initial", Constantes.ETAT_TRANSFER_STOCK_INITIAL);
			 query.setParameter("magasin",magasin.getId());
		  query.setParameter("subcategorieMp",subCategorieMp.getId());
		  query.setParameter("mp",mp.getId());
			
		}
		
		
		 
		return query.list();

	}
	
	
	
	@Override
	public List<Object[]> listeStockInitialMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseur(Date dateDebut,  Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp ) {
		// TODO Auto-generated method stub
		Query query=null;
		//+ sum( case when d.transferStockMP.statut=:TRANSFER then d.quantite else 0 end)
//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
			
		if(dateDebut!=null && magasin!=null && subCategorieMp==null && categorieMp==null && mp==null)
		{
			query= session.createQuery("select d.matierePremier ,d.fournisseur, sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=:magasin  then d.quantite else 0 end) , avg(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=:magasin  then d.prixUnitaire else 0 end) from DetailTransferStockMP d  where  d.transferStockMP.dateTransfer =:dateDebut group by d.matierePremier,d.fournisseur ");
			//query.setParameter("Cheque", "Chèque");
			// query.setParameter("Virement", "Virement");
			 query.setParameter("dateDebut", dateDebut);
			 
			 query.setParameter("Initial", Constantes.ETAT_TRANSFER_STOCK_INITIAL);
			
		  query.setParameter("magasin",magasin.getId());
		}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp==null)
		{
			
			query= session.createQuery("select d.matierePremier ,d.fournisseur, sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=:magasin  then d.quantite else 0 end) , avg(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=:magasin  then d.prixUnitaire else 0 end)  from DetailTransferStockMP d  where  d.matierePremier.categorieMp.subCategorieMp.id=:subCategorieMp and   d.transferStockMP.dateTransfer =:dateDebut group by d.matierePremier,d.fournisseur ");
			//query.setParameter("Cheque", "Chèque");
			// query.setParameter("Virement", "Virement");
			 query.setParameter("dateDebut", dateDebut);
			 query.setParameter("Initial", Constantes.ETAT_TRANSFER_STOCK_INITIAL);
			
		  query.setParameter("magasin",magasin.getId());
		  query.setParameter("subCategorieMp",subCategorieMp.getId());
			
		}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp==null)
		{
			
			query= session.createQuery("select d.matierePremier ,d.fournisseur, sum( case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=:magasin  then d.quantite else 0 end) , avg(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=:magasin  then d.prixUnitaire else 0 end) from DetailTransferStockMP d  where  d.matierePremier.categorieMp.id=:categorieMp and   d.transferStockMP.dateTransfer =:dateDebut group by d.matierePremier,d.fournisseur ");
			//query.setParameter("Cheque", "Chèque");
			// query.setParameter("Virement", "Virement");
			 query.setParameter("dateDebut", dateDebut);
			 query.setParameter("Initial", Constantes.ETAT_TRANSFER_STOCK_INITIAL);
			
		  query.setParameter("magasin",magasin.getId());
		  query.setParameter("categorieMp",categorieMp.getId());
			
		}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp!=null)
		{
			
			query= session.createQuery("select d.matierePremier ,d.fournisseur, sum( case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=:magasin  then d.quantite else 0 end) , avg(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=:magasin  then d.prixUnitaire else 0 end) from DetailTransferStockMP d  where  d.matierePremier.categorieMp.id=:categorieMp and d.matierePremier.id=:mp and   d.transferStockMP.dateTransfer =:dateDebut group by d.matierePremier,d.fournisseur ");
			//query.setParameter("Cheque", "Chèque");
			// query.setParameter("Virement", "Virement");
			 query.setParameter("dateDebut", dateDebut);
			 query.setParameter("Initial", Constantes.ETAT_TRANSFER_STOCK_INITIAL);
			
		  query.setParameter("magasin",magasin.getId());
		  query.setParameter("categorieMp",categorieMp.getId());
		  query.setParameter("mp",mp.getId());
			
		}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp!=null)
		{
			
			query= session.createQuery("select d.matierePremier ,d.fournisseur, sum( case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=:magasin  then d.quantite else 0 end)  from DetailTransferStockMP d  where  d.matierePremier.categorieMp.subCategorieMp.id=:subcategorieMp and d.matierePremier.id=:mp and   d.transferStockMP.dateTransfer =:dateDebut group by d.matierePremier,d.fournisseur ");
			//query.setParameter("Cheque", "Chèque");
			// query.setParameter("Virement", "Virement");
			 query.setParameter("dateDebut", dateDebut);
			 query.setParameter("Initial", Constantes.ETAT_TRANSFER_STOCK_INITIAL);
			 query.setParameter("magasin",magasin.getId());
		  query.setParameter("subcategorieMp",subCategorieMp.getId());
		  query.setParameter("mp",mp.getId());
			
		}
		
		
		 
		return query.list();

	}
	
	
	@Override
	public List<Object[]> listeStockInitialMPByMagasinBySubCategorieByCategorieBayMPNonThe(Date dateDebut,  Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp ) {
		// TODO Auto-generated method stub
		Query query=null;
		//+ sum( case when d.transferStockMP.statut=:TRANSFER then d.quantite else 0 end)
//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
			
		if(dateDebut!=null && magasin!=null && subCategorieMp==null && categorieMp==null && mp==null)
		{
			query= session.createQuery("select d.matierePremier , sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=:magasin  then d.quantite else 0 end) , avg(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=:magasin  then d.prixUnitaire else 0 end) from DetailTransferStockMP d  where  d.transferStockMP.dateTransfer =:dateDebut group by d.matierePremier ");
			//query.setParameter("Cheque", "Chèque");
			// query.setParameter("Virement", "Virement");
			 query.setParameter("dateDebut", dateDebut);
			 
			 query.setParameter("Initial", Constantes.ETAT_TRANSFER_STOCK_INITIAL);
			
		  query.setParameter("magasin",magasin.getId());
		}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp==null)
		{
			
			query= session.createQuery("select d.matierePremier , sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=:magasin  then d.quantite else 0 end) , avg(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=:magasin  then d.prixUnitaire else 0 end)  from DetailTransferStockMP d  where  d.matierePremier.categorieMp.subCategorieMp.id !=:subCategorieMp and   d.transferStockMP.dateTransfer =:dateDebut group by d.matierePremier");
			//query.setParameter("Cheque", "Chèque");
			// query.setParameter("Virement", "Virement");
			 query.setParameter("dateDebut", dateDebut);
			 query.setParameter("Initial", Constantes.ETAT_TRANSFER_STOCK_INITIAL);
			
		  query.setParameter("magasin",magasin.getId());
		  query.setParameter("subCategorieMp",subCategorieMp.getId());
			
		}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp==null)
		{
			
			query= session.createQuery("select d.matierePremier , sum( case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=:magasin  then d.quantite else 0 end) , avg(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=:magasin  then d.prixUnitaire else 0 end) from DetailTransferStockMP d  where  d.matierePremier.categorieMp.id=:categorieMp and   d.transferStockMP.dateTransfer =:dateDebut group by d.matierePremier ");
			//query.setParameter("Cheque", "Chèque");
			// query.setParameter("Virement", "Virement");
			 query.setParameter("dateDebut", dateDebut);
			 query.setParameter("Initial", Constantes.ETAT_TRANSFER_STOCK_INITIAL);
			
		  query.setParameter("magasin",magasin.getId());
		  query.setParameter("categorieMp",categorieMp.getId());
			
		}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp!=null)
		{
			
			query= session.createQuery("select d.matierePremier ,sum( case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=:magasin  then d.quantite else 0 end) , avg(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=:magasin  then d.prixUnitaire else 0 end) from DetailTransferStockMP d  where  d.matierePremier.categorieMp.id=:categorieMp and d.matierePremier.id=:mp and   d.transferStockMP.dateTransfer =:dateDebut group by d.matierePremier ");
			//query.setParameter("Cheque", "Chèque");
			// query.setParameter("Virement", "Virement");
			 query.setParameter("dateDebut", dateDebut);
			 query.setParameter("Initial", Constantes.ETAT_TRANSFER_STOCK_INITIAL);
			
		  query.setParameter("magasin",magasin.getId());
		  query.setParameter("categorieMp",categorieMp.getId());
		  query.setParameter("mp",mp.getId());
			
		}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp!=null)
		{
			
			query= session.createQuery("select d.matierePremier , sum( case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=:magasin  then d.quantite else 0 end)  from DetailTransferStockMP d  where  d.matierePremier.categorieMp.subCategorieMp.id!=:subcategorieMp and d.matierePremier.id=:mp and   d.transferStockMP.dateTransfer =:dateDebut group by d.matierePremier ");
			//query.setParameter("Cheque", "Chèque");
			// query.setParameter("Virement", "Virement");
			 query.setParameter("dateDebut", dateDebut);
			 query.setParameter("Initial", Constantes.ETAT_TRANSFER_STOCK_INITIAL);
			 query.setParameter("magasin",magasin.getId());
		  query.setParameter("subcategorieMp",subCategorieMp.getId());
		  query.setParameter("mp",mp.getId());
			
		}
		
		
		 
		return query.list();

	}
	
	
	
	
	
	@Override
	public List<Object[]> listeEtatStockMP(Date dateDebut, Magasin magasin  ) {
		// TODO Auto-generated method stub
		Query query=null;
		
		
		
		
		
//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
			query= session.createQuery("select d.matierePremier ,sum( case when d.transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:SORTIE then d.quantite else 0 end) + sum( case when d.transferStockMP.statut=:SORTIE_PF then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:RETOUR then d.quantite else 0 end) ,sum( case when d.transferStockMP.statut=:TRANSFER then d.quantite else 0 end) from DetailTransferStockMP d  where (d.magasinDestination.id=:magasin or d.magasinSouce.id=:magasin) and d.transferStockMP.dateTransfer =:dateDebut group by d.matierePremier ");
			//query.setParameter("Cheque", "Chèque");
			// query.setParameter("Virement", "Virement");
			 query.setParameter("dateDebut", dateDebut);
			 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
		     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
		     query.setParameter("CHARGE_SUPP",
		    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
		     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
		    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
		    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
		    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
			  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
		    query.setParameter("magasin",magasin.getId());
		    
		    
		 
		return query.list();

	}
	
	
	@Override
	public List<Object[]> listeSituationTransfert(Magasin magasin, Date dateDebut,Date dateFin , MatierePremier mp) {
		// TODO Auto-generated method stub
		Query query=null;
		
		
		
		
		
//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
			if(mp!=null)
			{
				
				query= session.createQuery("select d.matierePremier ,sum( case when (d.transferStockMP.statut=:ENTRE and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=:magasin) then d.quantite else 0 end) ,sum( case when (d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin) then d.quantite else 0 end),sum( case when (d.transferStockMP.statut=:SORTIEENATTENTVALIDER and d.magasinSouce.id=:magasin) then d.quantite else 0 end) from DetailTransferStockMP d  where d.transferStockMP.dateTransfer  between :dateDebut and :dateFin and d.matierePremier.id=:mp group by d.matierePremier" );
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("SORTIEENATTENTVALIDER", Constantes.ETAT_SORTIE_ENATTENT_VALIDER); 
			    
			    query.setParameter("magasin", magasin.getId()); 
			    query.setParameter("dateDebut", dateDebut);
			    query.setParameter("dateFin", dateFin);
			    query.setParameter("mp", mp.getId());
			    
				
				
			}else
			{
				
				query= session.createQuery("select d.matierePremier ,sum( case when (d.transferStockMP.statut=:ENTRE and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=:magasin) then d.quantite else 0 end) ,sum( case when (d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin) then d.quantite else 0 end),sum( case when (d.transferStockMP.statut=:SORTIEENATTENTVALIDER and d.magasinSouce.id=:magasin) then d.quantite else 0 end) from DetailTransferStockMP d  where d.transferStockMP.dateTransfer between :dateDebut and :dateFin group by d.matierePremier" );
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("SORTIEENATTENTVALIDER", Constantes.ETAT_SORTIE_ENATTENT_VALIDER); 
			    query.setParameter("magasin", magasin.getId()); 
			    query.setParameter("dateDebut", dateDebut);
			    query.setParameter("dateFin", dateFin);
				
			}
		
		
		
		   
		    
		    
		 
		return query.list();

	}
	
	@Override
	public List<Object[]> listeDeclarationReceptionMagasinier(Magasin magasin, Date dateDebut,Date dateFin ) {
		// TODO Auto-generated method stub
		Query query=null;
		
		
		
		
		
//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
			
				
				query= session.createQuery("select d.transferStockMP.dateTransfer,d.matierePremier.id,sum(d.quantite) from DetailTransferStockMP d  where d.transferStockMP.statut=:etat and d.transferStockMP.dateTransfer  between :dateDebut and :dateFin and  d.transferStockMP.ImporterViaGestionCommande=:importer and d.magasinDestination.id =:magasin group by d.transferStockMP.dateTransfer,d.matierePremier.id" );
			     query.setParameter("etat",  Constantes.ETAT_RECEPTION_ENATTENT); 
			    query.setParameter("magasin", magasin.getId()); 
			    query.setParameter("dateDebut", dateDebut);
			    query.setParameter("dateFin", dateFin);
			    query.setParameter("importer", Constantes.CODE_IMPORTER);
			System.out.println(query.toString());	
				 
		 
		return query.list();

	}
	
	public List<Object[]> listeDetailSituationTransfert(Magasin magasin, Date dateDebut,Date dateFin , MatierePremier mp) {
		// TODO Auto-generated method stub
		Query query=null;
		
		
		
		
		
//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
			
				
				query= session.createQuery("select d.matierePremier ,d.transferStockMP.dateTransfer,d.transferStockMP.CodeTransfer,d.transferStockMP.statut,d.quantite,d.magasinSouce.id,d.magasinDestination.id from DetailTransferStockMP d  where d.transferStockMP.dateTransfer between :dateDebut and :dateFin and (d.magasinDestination.id =:magasin or d.magasinSouce.id =:magasin)  and  d.matierePremier.id=:mp and (d.transferStockMP.statut =:SORTIE or  d.transferStockMP.statut =:ENTRE or d.transferStockMP.statut =:SORTIEENATTENT or d.transferStockMP.statut =:SORTIEENATTENTVALIDER)   order by d.transferStockMP.dateTransfer" );
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("SORTIEENATTENTVALIDER", Constantes.ETAT_SORTIE_ENATTENT_VALIDER); 
			    query.setParameter("magasin", magasin.getId()); 
			   query.setParameter("dateDebut", dateDebut);
			    query.setParameter("dateFin", dateFin);
			    query.setParameter("mp", mp.getId());
			   // d.transferStockMP.dateTransfer  between :dateDebut and :dateFin and
		 
		return query.list();

	}
	
	public List<Object[]> listeDetailDeclarationReceptionMagasinier(Magasin magasin, Date dateDebut,MatierePremier mp) {
		// TODO Auto-generated method stub
		Query query=null;
		
		
		
		 
		
//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
			
				
				query= session.createQuery("select d.transferStockMP.CodeTransfer,d.numBLReception ,d.matierePremier.id ,d.quantite from DetailTransferStockMP d  where d.transferStockMP.dateTransfer=:dateDebut and d.magasinDestination.id =:magasin  and  d.matierePremier.id=:mp and  d.transferStockMP.statut =:etat and  d.transferStockMP.ImporterViaGestionCommande=:importer  group by d.transferStockMP.CodeTransfer,d.numBLReception, d.matierePremier.id" );
			       
			    query.setParameter("magasin", magasin.getId()); 
			   query.setParameter("dateDebut", dateDebut);
			   query.setParameter("etat",  Constantes.ETAT_RECEPTION_ENATTENT); 
			    query.setParameter("mp", mp.getId());
			    query.setParameter("importer", Constantes.CODE_IMPORTER);
			  
		 
		return query.list();

	}
	
	
	
	
	
	@Override
	public List<Object[]> listeEtatStockMPByMagasinBySubCategorieByCategorieBayMP(Date dateDebut,Date dateFin, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp ) {
		// TODO Auto-generated method stub
		Query query=null;
		
		    
		    
			
			if(dateDebut!=null && magasin!=null && subCategorieMp==null && categorieMp==null && mp==null)
			{

				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier , sum( case when t.statut=:AJOUT   and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end), sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin  then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:SORTIE and d.magasinSouce.id=:magasin  and t.soustype.id is null) then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR   and d.magasinDestination.id=:magasin) then d.quantite else 0 end),(sum( case when (t.statut=:SORTIE and d.magasinSouce.id=:magasin  and t.soustype.id <> null) then d.quantite else 0 end) + sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin  then d.quantite else 0 end) + sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin then d.quantite else 0 end) + sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin  then d.quantite else 0 end) + sum( case when t.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=:magasin  then d.quantite else 0 end) ) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and t.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP", Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE);
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
			    query.setParameter("magasin",magasin.getId());
			    query.setParameter("PERTE", Constantes.TYPE_PERTE);
			    query.setParameter("RETOURFOURNISSEURANNULER",Constantes.SOUS_TYPE_SORTIE_RETOUR_FOURNISSEUR_ANNULER); 
			    query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp==null)
			{

				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier , sum( case when t.statut=:AJOUT   and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin  then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:SORTIE and d.magasinSouce.id=:magasin  and t.soustype.id is null) then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR   and d.magasinDestination.id=:magasin) then d.quantite else 0 end),(sum( case when (t.statut=:SORTIE and d.magasinSouce.id=:magasin  and t.soustype.id <> null) then d.quantite else 0 end) + sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin  then d.quantite else 0 end) + sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin then d.quantite else 0 end)+ sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin  then d.quantite else 0 end) + sum( case when t.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=:magasin  then d.quantite else 0 end) ) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and   d.matierePremier.categorieMp.subCategorieMp.id=:subCategorieMp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
				
				
			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("subCategorieMp",subCategorieMp.getId());
			  query.setParameter("PERTE", Constantes.TYPE_PERTE);
			  query.setParameter("RETOURFOURNISSEURANNULER",Constantes.SOUS_TYPE_SORTIE_RETOUR_FOURNISSEUR_ANNULER); 
			  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp==null)
			{
				
				
				
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier , sum( case when t.statut=:AJOUT   and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end) ,  sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end) ,sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin  then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:SORTIE and d.magasinSouce.id=:magasin  and t.soustype.id is null) then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR   and d.magasinDestination.id=:magasin) then d.quantite else 0 end),(sum( case when (t.statut=:SORTIE and d.magasinSouce.id=:magasin  and t.soustype.id <> null) then d.quantite else 0 end) + sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin  then d.quantite else 0 end) + sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin then d.quantite else 0 end)+ sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin  then d.quantite else 0 end) + sum( case when t.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=:magasin  then d.quantite else 0 end) ) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and  d.matierePremier.categorieMp.id=:categorieMp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
								
			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("categorieMp",categorieMp.getId());
			  query.setParameter("PERTE", Constantes.TYPE_PERTE);
			  query.setParameter("RETOURFOURNISSEURANNULER",Constantes.SOUS_TYPE_SORTIE_RETOUR_FOURNISSEUR_ANNULER); 
			  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp!=null)
			{
			
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier , sum( case when t.statut=:AJOUT   and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),  sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end) ,sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin  then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:SORTIE and d.magasinSouce.id=:magasin  and t.soustype.id is null) then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR   and d.magasinDestination.id=:magasin) then d.quantite else 0 end),(sum( case when (t.statut=:SORTIE and d.magasinSouce.id=:magasin  and t.soustype.id <> null) then d.quantite else 0 end) + sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin  then d.quantite else 0 end) + sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin then d.quantite else 0 end)+ sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin  then d.quantite else 0 end) + sum( case when t.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=:magasin  then d.quantite else 0 end) ) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and d.matierePremier.categorieMp.id=:categorieMp and d.matierePremier.id=:mp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);

			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("categorieMp",categorieMp.getId());
			  query.setParameter("mp",mp.getId());
			  query.setParameter("PERTE", Constantes.TYPE_PERTE);
			  query.setParameter("RETOURFOURNISSEURANNULER",Constantes.SOUS_TYPE_SORTIE_RETOUR_FOURNISSEUR_ANNULER); 
			  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp!=null)
			{
				
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier , sum( case when t.statut=:AJOUT   and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),  sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end) ,sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin  then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:SORTIE and d.magasinSouce.id=:magasin  and t.soustype.id is null) then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR   and d.magasinDestination.id=:magasin) then d.quantite else 0 end),(sum( case when (t.statut=:SORTIE and d.magasinSouce.id=:magasin  and t.soustype.id <> null) then d.quantite else 0 end) + sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin  then d.quantite else 0 end) + sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin then d.quantite else 0 end)+ sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin  then d.quantite else 0 end) + sum( case when t.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=:magasin  then d.quantite else 0 end) ) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and  d.matierePremier.categorieMp.subCategorieMp.id=:subcategorieMp and d.matierePremier.id=:mp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("subcategorieMp",subCategorieMp.getId());
			  query.setParameter("mp",mp.getId());
			  query.setParameter("PERTE", Constantes.TYPE_PERTE);
			  query.setParameter("RETOURFOURNISSEURANNULER",Constantes.SOUS_TYPE_SORTIE_RETOUR_FOURNISSEUR_ANNULER); 
			  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			}
			
		    
		
		return query.list();
		
		

	}
	
	
	
	
	
	@Override
	public List<Object[]> listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurReception (Date dateDebut,Date dateFin, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp ) {
		// TODO Auto-generated method stub
		Query query=null;
		
		    
		    
			
			if(dateDebut!=null && magasin!=null && subCategorieMp==null && categorieMp==null && mp==null)
			{

				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end) from DetailTransferStockMP d where  t.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE);
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
			    query.setParameter("magasin",magasin.getId());
			    query.setParameter("PERTE", Constantes.TYPE_PERTE);
			    query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			  
			   
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp==null)
			{

				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select d.matierePremier ,d.fournisseur, sum( case when d.transferStockMP.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end)   from   DetailTransferStockMP d where   d.transferStockMP.dateTransfer between :dateDebut and :dateFin and   d.matierePremier.categorieMp.subCategorieMp.id =:subCategorieMp  group by d.matierePremier.id,d.fournisseur.id");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				  query.setParameter("magasin",magasin.getId());				  
				  query.setParameter("subCategorieMp",subCategorieMp.getId());
				
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
				
				
				
			
			 
			 
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp==null)
			{
				
				
				
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null  then d.quantite else 0 end) ,sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end), sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and  d.matierePremier.categorieMp.id=:categorieMp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
				  query.setParameter("PERTE", Constantes.TYPE_PERTE);
				  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("categorieMp",categorieMp.getId());
			
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp!=null)
			{
			
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null  then d.quantite else 0 end) ,sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and d.matierePremier.categorieMp.id=:categorieMp and d.matierePremier.id=:mp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);


			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("categorieMp",categorieMp.getId());
			  query.setParameter("mp",mp.getId());
			  query.setParameter("PERTE", Constantes.TYPE_PERTE);
			  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp!=null)
			{
				
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null then d.quantite else 0 end) , sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and  d.matierePremier.categorieMp.subCategorieMp.id=:subcategorieMp and d.matierePremier.id=:mp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("subcategorieMp",subCategorieMp.getId());
			  query.setParameter("mp",mp.getId());
			  query.setParameter("PERTE", Constantes.TYPE_PERTE);
			  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			 
			 
			}
			
		    
		
		return query.list();
		
		

	}
	
	
	@Override
	public List<Object[]> listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurEntrer (Date dateDebut,Date dateFin, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp ) {
		// TODO Auto-generated method stub
		Query query=null;
		
		    
		    
			
			if(dateDebut!=null && magasin!=null && subCategorieMp==null && categorieMp==null && mp==null)
			{

				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null  then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end), sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) ,sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and t.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE);
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
			    query.setParameter("magasin",magasin.getId());
			    query.setParameter("PERTE", Constantes.TYPE_PERTE);
			    query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			  
			   
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp==null)
			{

				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select d.matierePremier ,d.fournisseur, sum( case when d.transferStockMP.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end)   from   DetailTransferStockMP d where   d.transferStockMP.dateTransfer between :dateDebut and :dateFin and   d.matierePremier.categorieMp.subCategorieMp.id =:subCategorieMp  group by d.matierePremier.id,d.fournisseur.id");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				  query.setParameter("magasin",magasin.getId());				  
				  query.setParameter("subCategorieMp",subCategorieMp.getId());
				  query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
				
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
				
			    
			  
				
			
			 
			 
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp==null)
			{
				
				
				
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null  then d.quantite else 0 end) ,sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end), sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and  d.matierePremier.categorieMp.id=:categorieMp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
				  query.setParameter("PERTE", Constantes.TYPE_PERTE);
				  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("categorieMp",categorieMp.getId());
			
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp!=null)
			{
			
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null  then d.quantite else 0 end) ,sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and d.matierePremier.categorieMp.id=:categorieMp and d.matierePremier.id=:mp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);


			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("categorieMp",categorieMp.getId());
			  query.setParameter("mp",mp.getId());
			  query.setParameter("PERTE", Constantes.TYPE_PERTE);
			  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp!=null)
			{
				
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null then d.quantite else 0 end) , sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and  d.matierePremier.categorieMp.subCategorieMp.id=:subcategorieMp and d.matierePremier.id=:mp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("subcategorieMp",subCategorieMp.getId());
			  query.setParameter("mp",mp.getId());
			  query.setParameter("PERTE", Constantes.TYPE_PERTE);
			  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			 
			 
			}
			
		    
		
		return query.list();
		
		

	}
	
	@Override
	public List<Object[]> listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurSortie (Date dateDebut,Date dateFin, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp ) {
		// TODO Auto-generated method stub
		Query query=null;
		
		    
		    
			
			if(dateDebut!=null && magasin!=null && subCategorieMp==null && categorieMp==null && mp==null)
			{

				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur,sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and t.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE);
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
			    query.setParameter("magasin",magasin.getId());
			    query.setParameter("PERTE", Constantes.TYPE_PERTE);
			    query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			  
			   
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp==null)
			{

				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur,sum( case when d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=:magasin and d.transferStockMP.soustype.id is null then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end) from   DetailTransferStockMP d where   d.transferStockMP.dateTransfer between :dateDebut and :dateFin and   d.matierePremier.categorieMp.subCategorieMp.id =:subCategorieMp  group by d.matierePremier.id,d.fournisseur.id");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				  query.setParameter("magasin",magasin.getId());				  
				  query.setParameter("subCategorieMp",subCategorieMp.getId());
				 
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
				
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			    
				
			
			 
			 
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp==null)
			{
				
				
				
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null  then d.quantite else 0 end) ,sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end), sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and  d.matierePremier.categorieMp.id=:categorieMp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
				  query.setParameter("PERTE", Constantes.TYPE_PERTE);
				  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("categorieMp",categorieMp.getId());
			
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp!=null)
			{
			
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null  then d.quantite else 0 end) ,sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and d.matierePremier.categorieMp.id=:categorieMp and d.matierePremier.id=:mp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);


			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("categorieMp",categorieMp.getId());
			  query.setParameter("mp",mp.getId());
			  query.setParameter("PERTE", Constantes.TYPE_PERTE);
			  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp!=null)
			{
				
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null then d.quantite else 0 end) , sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and  d.matierePremier.categorieMp.subCategorieMp.id=:subcategorieMp and d.matierePremier.id=:mp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("subcategorieMp",subCategorieMp.getId());
			  query.setParameter("mp",mp.getId());
			  query.setParameter("PERTE", Constantes.TYPE_PERTE);
			  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			 
			 
			}
			
		    
		
		return query.list();
		
		

	}
	
	@Override
	public List<Object[]> listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurCharger (Date dateDebut,Date dateFin, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp ) {
		// TODO Auto-generated method stub
		Query query=null;
		
		    
		    
			
			if(dateDebut!=null && magasin!=null && subCategorieMp==null && categorieMp==null && mp==null)
			{

				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null  then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end), sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) ,sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and t.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE);
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
			    query.setParameter("magasin",magasin.getId());
			    query.setParameter("PERTE", Constantes.TYPE_PERTE);
			    query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			  
			   
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp==null)
			{

				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select d.matierePremier ,d.fournisseur,sum( case when d.transferStockMP.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end)  from   DetailTransferStockMP d where   d.transferStockMP.dateTransfer between :dateDebut and :dateFin and   d.matierePremier.categorieMp.subCategorieMp.id =:subCategorieMp  group by d.matierePremier.id,d.fournisseur.id");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				  query.setParameter("magasin",magasin.getId());				  
				  query.setParameter("subCategorieMp",subCategorieMp.getId());
				
				 query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP", Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			    
				
				
			
			 
			 
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp==null)
			{
				
				
				
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null  then d.quantite else 0 end) ,sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end), sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and  d.matierePremier.categorieMp.id=:categorieMp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
				  query.setParameter("PERTE", Constantes.TYPE_PERTE);
				  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("categorieMp",categorieMp.getId());
			
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp!=null)
			{
			
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null  then d.quantite else 0 end) ,sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and d.matierePremier.categorieMp.id=:categorieMp and d.matierePremier.id=:mp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);


			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("categorieMp",categorieMp.getId());
			  query.setParameter("mp",mp.getId());
			  query.setParameter("PERTE", Constantes.TYPE_PERTE);
			  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp!=null)
			{
				
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null then d.quantite else 0 end) , sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and  d.matierePremier.categorieMp.subCategorieMp.id=:subcategorieMp and d.matierePremier.id=:mp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("subcategorieMp",subCategorieMp.getId());
			  query.setParameter("mp",mp.getId());
			  query.setParameter("PERTE", Constantes.TYPE_PERTE);
			  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			 
			 
			}
			
		    
		
		return query.list();
		
		

	}
	
	
	
	@Override
	public List<Object[]> listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurRetour(Date dateDebut,Date dateFin, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp ) {
		// TODO Auto-generated method stub
		Query query=null;
		
		    
		    
			
			if(dateDebut!=null && magasin!=null && subCategorieMp==null && categorieMp==null && mp==null)
			{

				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null  then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end), sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) ,sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and t.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE);
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
			    query.setParameter("magasin",magasin.getId());
			    query.setParameter("PERTE", Constantes.TYPE_PERTE);
			    query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			  
			   
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp==null)
			{

				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select d.matierePremier ,d.fournisseur,sum( case when (d.transferStockMP.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end) from   DetailTransferStockMP d where   d.transferStockMP.dateTransfer between :dateDebut and :dateFin and   d.matierePremier.categorieMp.subCategorieMp.id =:subCategorieMp  group by d.matierePremier.id,d.fournisseur.id");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				  query.setParameter("magasin",magasin.getId());				  
				  query.setParameter("subCategorieMp",subCategorieMp.getId());
				 
			     query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    
				
				
			
			 
			 
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp==null)
			{
				
				
				
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null  then d.quantite else 0 end) ,sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end), sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and  d.matierePremier.categorieMp.id=:categorieMp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
				  query.setParameter("PERTE", Constantes.TYPE_PERTE);
				  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("categorieMp",categorieMp.getId());
			
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp!=null)
			{
			
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null  then d.quantite else 0 end) ,sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and d.matierePremier.categorieMp.id=:categorieMp and d.matierePremier.id=:mp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);


			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("categorieMp",categorieMp.getId());
			  query.setParameter("mp",mp.getId());
			  query.setParameter("PERTE", Constantes.TYPE_PERTE);
			  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp!=null)
			{
				
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null then d.quantite else 0 end) , sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and  d.matierePremier.categorieMp.subCategorieMp.id=:subcategorieMp and d.matierePremier.id=:mp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("subcategorieMp",subCategorieMp.getId());
			  query.setParameter("mp",mp.getId());
			  query.setParameter("PERTE", Constantes.TYPE_PERTE);
			  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			 
			 
			}
			
		    
		
		return query.list();
		
		

	}
	
	@Override
	public List<Object[]> listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortie(Date dateDebut,Date dateFin, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp ) {
		// TODO Auto-generated method stub
		Query query=null;
		
		    
		    
			
			if(dateDebut!=null && magasin!=null && subCategorieMp==null && categorieMp==null && mp==null)
			{

				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null  then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end), sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) ,sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and t.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE);
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
			    query.setParameter("magasin",magasin.getId());
			    query.setParameter("PERTE", Constantes.TYPE_PERTE);
			    query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			  
			   
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp==null)
			{

				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select d.matierePremier ,d.fournisseur,sum( case when d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=:magasin and d.transferStockMP.soustype <> null  then d.quantite else 0 end) from   DetailTransferStockMP d where   d.transferStockMP.dateTransfer between :dateDebut and :dateFin and   d.matierePremier.categorieMp.subCategorieMp.id =:subCategorieMp  group by d.matierePremier.id,d.fournisseur.id");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				  query.setParameter("magasin",magasin.getId());				  
				  query.setParameter("subCategorieMp",subCategorieMp.getId());
				
				
				
			    
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			    
			    
			   
				
				
			
			 
			 
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp==null)
			{
				
				
				
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null  then d.quantite else 0 end) ,sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end), sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and  d.matierePremier.categorieMp.id=:categorieMp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
				  query.setParameter("PERTE", Constantes.TYPE_PERTE);
				  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("categorieMp",categorieMp.getId());
			
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp!=null)
			{
			
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null  then d.quantite else 0 end) ,sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and d.matierePremier.categorieMp.id=:categorieMp and d.matierePremier.id=:mp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);


			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("categorieMp",categorieMp.getId());
			  query.setParameter("mp",mp.getId());
			  query.setParameter("PERTE", Constantes.TYPE_PERTE);
			  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp!=null)
			{
				
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null then d.quantite else 0 end) , sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and  d.matierePremier.categorieMp.subCategorieMp.id=:subcategorieMp and d.matierePremier.id=:mp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("subcategorieMp",subCategorieMp.getId());
			  query.setParameter("mp",mp.getId());
			  query.setParameter("PERTE", Constantes.TYPE_PERTE);
			  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			 
			 
			}
			
		    
		
		return query.list();
		
		

	}
	
	
	@Override
	public List<Object[]> listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortiePF(Date dateDebut,Date dateFin, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp ) {
		// TODO Auto-generated method stub
		Query query=null;
		
		    
		    
			
			if(dateDebut!=null && magasin!=null && subCategorieMp==null && categorieMp==null && mp==null)
			{

				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null  then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end), sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) ,sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and t.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE);
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
			    query.setParameter("magasin",magasin.getId());
			    query.setParameter("PERTE", Constantes.TYPE_PERTE);
			    query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			  
			   
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp==null)
			{

				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select d.matierePremier ,d.fournisseur, sum( case when d.transferStockMP.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end)  from   DetailTransferStockMP d where   d.transferStockMP.dateTransfer between :dateDebut and :dateFin and   d.matierePremier.categorieMp.subCategorieMp.id =:subCategorieMp  group by d.matierePremier.id,d.fournisseur.id");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				  query.setParameter("magasin",magasin.getId());				  
				  query.setParameter("subCategorieMp",subCategorieMp.getId());
				
				
				
			 
			   
			    
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				
				 
				
				
			
			 
			 
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp==null)
			{
				
				
				
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null  then d.quantite else 0 end) ,sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end), sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and  d.matierePremier.categorieMp.id=:categorieMp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
				  query.setParameter("PERTE", Constantes.TYPE_PERTE);
				  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("categorieMp",categorieMp.getId());
			
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp!=null)
			{
			
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null  then d.quantite else 0 end) ,sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and d.matierePremier.categorieMp.id=:categorieMp and d.matierePremier.id=:mp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);


			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("categorieMp",categorieMp.getId());
			  query.setParameter("mp",mp.getId());
			  query.setParameter("PERTE", Constantes.TYPE_PERTE);
			  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp!=null)
			{
				
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null then d.quantite else 0 end) , sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and  d.matierePremier.categorieMp.subCategorieMp.id=:subcategorieMp and d.matierePremier.id=:mp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("subcategorieMp",subCategorieMp.getId());
			  query.setParameter("mp",mp.getId());
			  query.setParameter("PERTE", Constantes.TYPE_PERTE);
			  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			 
			 
			}
			
		    
		
		return query.list();
		
		

	}
	
	@Override
	public List<Object[]> listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortieEnAttente(Date dateDebut,Date dateFin, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp ) {
		// TODO Auto-generated method stub
		Query query=null;
		
		    
		    
			
			if(dateDebut!=null && magasin!=null && subCategorieMp==null && categorieMp==null && mp==null)
			{

				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null  then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end), sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) ,sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and t.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE);
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
			    query.setParameter("magasin",magasin.getId());
			    query.setParameter("PERTE", Constantes.TYPE_PERTE);
			    query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			  
			   
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp==null)
			{

				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select d.matierePremier ,d.fournisseur, sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end)  from   DetailTransferStockMP d where   d.transferStockMP.dateTransfer between :dateDebut and :dateFin and   d.matierePremier.categorieMp.subCategorieMp.id =:subCategorieMp  group by d.matierePremier.id,d.fournisseur.id");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				  query.setParameter("magasin",magasin.getId());				  
				  query.setParameter("subCategorieMp",subCategorieMp.getId());
				
				
				
			    
			    
			    
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			 // query.setParameter("magProduction",  Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION);
			   
			    
			  
				
			
			 
			 
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp==null)
			{
				
				
				
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null  then d.quantite else 0 end) ,sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end), sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and  d.matierePremier.categorieMp.id=:categorieMp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
				  query.setParameter("PERTE", Constantes.TYPE_PERTE);
				  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("categorieMp",categorieMp.getId());
			
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp!=null)
			{
			
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null  then d.quantite else 0 end) ,sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and d.matierePremier.categorieMp.id=:categorieMp and d.matierePremier.id=:mp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);


			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("categorieMp",categorieMp.getId());
			  query.setParameter("mp",mp.getId());
			  query.setParameter("PERTE", Constantes.TYPE_PERTE);
			  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp!=null)
			{
				
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null then d.quantite else 0 end) , sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and  d.matierePremier.categorieMp.subCategorieMp.id=:subcategorieMp and d.matierePremier.id=:mp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("subcategorieMp",subCategorieMp.getId());
			  query.setParameter("mp",mp.getId());
			  query.setParameter("PERTE", Constantes.TYPE_PERTE);
			  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			 
			 
			}
			
		    
		
		return query.list();
		
		

	}
	
	@Override
	public List<Object[]> listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortiePerte(Date dateDebut,Date dateFin, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp ) {
		// TODO Auto-generated method stub
		Query query=null;
		
		    
		    
			
			if(dateDebut!=null && magasin!=null && subCategorieMp==null && categorieMp==null && mp==null)
			{

				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null  then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end), sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) ,sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and t.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE);
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
			    query.setParameter("magasin",magasin.getId());
			    query.setParameter("PERTE", Constantes.TYPE_PERTE);
			    query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			  
			   
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp==null)
			{

				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select d.matierePremier ,d.fournisseur, sum( case when d.transferStockMP.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end)  from   DetailTransferStockMP d where   d.transferStockMP.dateTransfer between :dateDebut and :dateFin and   d.matierePremier.categorieMp.subCategorieMp.id =:subCategorieMp  group by d.matierePremier.id,d.fournisseur.id");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				  query.setParameter("magasin",magasin.getId());				  
				  query.setParameter("subCategorieMp",subCategorieMp.getId());
				
				
				
			    
				
				  query.setParameter("PERTE", Constantes.TYPE_PERTE);
				
				
			
			 
			 
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp==null)
			{
				
				
				
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null  then d.quantite else 0 end) ,sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end), sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and  d.matierePremier.categorieMp.id=:categorieMp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
				  query.setParameter("PERTE", Constantes.TYPE_PERTE);
				  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("categorieMp",categorieMp.getId());
			
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp!=null)
			{
			
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null  then d.quantite else 0 end) ,sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and d.matierePremier.categorieMp.id=:categorieMp and d.matierePremier.id=:mp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);


			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("categorieMp",categorieMp.getId());
			  query.setParameter("mp",mp.getId());
			  query.setParameter("PERTE", Constantes.TYPE_PERTE);
			  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp!=null)
			{
				
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null then d.quantite else 0 end) , sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and  d.matierePremier.categorieMp.subCategorieMp.id=:subcategorieMp and d.matierePremier.id=:mp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("subcategorieMp",subCategorieMp.getId());
			  query.setParameter("mp",mp.getId());
			  query.setParameter("PERTE", Constantes.TYPE_PERTE);
			  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			 
			 
			}
			
		    
		
		return query.list();
		
		

	}
	
	@Override
	public List<Object[]> listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieRetour(Date dateDebut,Date dateFin, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp ) {
		// TODO Auto-generated method stub
		Query query=null;
		
		    
		    
			
			if(dateDebut!=null && magasin!=null && subCategorieMp==null && categorieMp==null && mp==null)
			{

				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null  then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end), sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) ,sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and t.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE);
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
			    query.setParameter("magasin",magasin.getId());
			    query.setParameter("PERTE", Constantes.TYPE_PERTE);
			    query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			  
			   
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp==null)
			{

				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select d.matierePremier ,d.fournisseur,sum( case when d.transferStockMP.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from   DetailTransferStockMP d where   d.transferStockMP.dateTransfer between :dateDebut and :dateFin and   d.matierePremier.categorieMp.subCategorieMp.id =:subCategorieMp  group by d.matierePremier.id,d.fournisseur.id");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				  query.setParameter("magasin",magasin.getId());				  
				  query.setParameter("subCategorieMp",subCategorieMp.getId());
				
				
				
			     query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			   
				
			
			 
			 
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp==null)
			{
				
				
				
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null  then d.quantite else 0 end) ,sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end), sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and  d.matierePremier.categorieMp.id=:categorieMp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
				  query.setParameter("PERTE", Constantes.TYPE_PERTE);
				  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("categorieMp",categorieMp.getId());
			
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp!=null)
			{
			
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null  then d.quantite else 0 end) ,sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and d.matierePremier.categorieMp.id=:categorieMp and d.matierePremier.id=:mp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);


			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("categorieMp",categorieMp.getId());
			  query.setParameter("mp",mp.getId());
			  query.setParameter("PERTE", Constantes.TYPE_PERTE);
			  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp!=null)
			{
				
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null then d.quantite else 0 end) , sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and  d.matierePremier.categorieMp.subCategorieMp.id=:subcategorieMp and d.matierePremier.id=:mp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("subcategorieMp",subCategorieMp.getId());
			  query.setParameter("mp",mp.getId());
			  query.setParameter("PERTE", Constantes.TYPE_PERTE);
			  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			 
			 
			}
			
		    
		
		return query.list();
		
		

	}
	
	
	
	@Override
	public List<Object[]> listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieRETOURFOURNISSEURANNULER(Date dateDebut,Date dateFin, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp ) {
		// TODO Auto-generated method stub
		Query query=null;
		
		    
		    
			
			if(dateDebut!=null && magasin!=null && subCategorieMp==null && categorieMp==null && mp==null)
			{

				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null  then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end), sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) ,sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and t.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE);
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
			    query.setParameter("magasin",magasin.getId());
			    query.setParameter("PERTE", Constantes.TYPE_PERTE);
			    query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			  
			   
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp==null)
			{

				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select d.matierePremier ,d.fournisseur,sum( case when d.transferStockMP.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=:magasin then d.quantite else 0 end) from   DetailTransferStockMP d where   d.transferStockMP.dateTransfer between :dateDebut and :dateFin and   d.matierePremier.categorieMp.subCategorieMp.id =:subCategorieMp  group by d.matierePremier.id,d.fournisseur.id");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				  query.setParameter("magasin",magasin.getId());				  
				  query.setParameter("subCategorieMp",subCategorieMp.getId());
				
				
				
			     query.setParameter("RETOURFOURNISSEURANNULER",Constantes.SOUS_TYPE_SORTIE_RETOUR_FOURNISSEUR_ANNULER); 
			   
				
			
			 
			 
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp==null)
			{
				
				
				
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null  then d.quantite else 0 end) ,sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end), sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and  d.matierePremier.categorieMp.id=:categorieMp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
				  query.setParameter("PERTE", Constantes.TYPE_PERTE);
				  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("categorieMp",categorieMp.getId());
			
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp!=null)
			{
			
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null  then d.quantite else 0 end) ,sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and d.matierePremier.categorieMp.id=:categorieMp and d.matierePremier.id=:mp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);


			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("categorieMp",categorieMp.getId());
			  query.setParameter("mp",mp.getId());
			  query.setParameter("PERTE", Constantes.TYPE_PERTE);
			  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp!=null)
			{
				
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null then d.quantite else 0 end) , sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and  d.matierePremier.categorieMp.subCategorieMp.id=:subcategorieMp and d.matierePremier.id=:mp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("subcategorieMp",subCategorieMp.getId());
			  query.setParameter("mp",mp.getId());
			  query.setParameter("PERTE", Constantes.TYPE_PERTE);
			  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			 
			 
			}
			
		    
		
		return query.list();
		
		

	}
	
	
	
	@Override
	public List<Object[]> listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurResterMachine (Date dateDebut,Date dateFin, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp ) {
		// TODO Auto-generated method stub
		Query query=null;
		
		    
		    
			
			if(dateDebut!=null && magasin!=null && subCategorieMp==null && categorieMp==null && mp==null)
			{

				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null  then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end), sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) ,sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and t.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE);
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
			    query.setParameter("magasin",magasin.getId());
			    query.setParameter("PERTE", Constantes.TYPE_PERTE);
			    query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			  
			   
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp==null)
			{

				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select d.matierePremier ,d.fournisseur,sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end) from   DetailTransferStockMP d where   d.transferStockMP.dateTransfer between :dateDebut and :dateFin and   d.matierePremier.categorieMp.subCategorieMp.id =:subCategorieMp  group by d.matierePremier.id,d.fournisseur.id");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				  query.setParameter("magasin",magasin.getId());				  
				  query.setParameter("subCategorieMp",subCategorieMp.getId());
				 
				 query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			    
				
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp==null)
			{
				
				
				
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null  then d.quantite else 0 end) ,sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end), sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and  d.matierePremier.categorieMp.id=:categorieMp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
				  query.setParameter("PERTE", Constantes.TYPE_PERTE);
				  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("categorieMp",categorieMp.getId());
			
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp!=null)
			{
			
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null  then d.quantite else 0 end) ,sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and d.matierePremier.categorieMp.id=:categorieMp and d.matierePremier.id=:mp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);


			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("categorieMp",categorieMp.getId());
			  query.setParameter("mp",mp.getId());
			  query.setParameter("PERTE", Constantes.TYPE_PERTE);
			  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp!=null)
			{
				
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null then d.quantite else 0 end) , sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and  d.matierePremier.categorieMp.subCategorieMp.id=:subcategorieMp and d.matierePremier.id=:mp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("subcategorieMp",subCategorieMp.getId());
			  query.setParameter("mp",mp.getId());
			  query.setParameter("PERTE", Constantes.TYPE_PERTE);
			  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			 
			 
			}
			
		    
		
		return query.list();
		
		

	}
	
	
	@Override
	public List<Object[]> listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurFabriquer (Date dateDebut,Date dateFin, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp ) {
		// TODO Auto-generated method stub
		Query query=null;
		
		    
		    
			
			if(dateDebut!=null && magasin!=null && subCategorieMp==null && categorieMp==null && mp==null)
			{

				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null  then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end), sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) ,sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and t.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE);
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
			    query.setParameter("magasin",magasin.getId());
			    query.setParameter("PERTE", Constantes.TYPE_PERTE);
			    query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			  
			   
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp==null)
			{

				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select d.matierePremier ,d.fournisseur,sum( case when d.transferStockMP.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end) from   DetailTransferStockMP d where   d.transferStockMP.dateTransfer between :dateDebut and :dateFin and   d.matierePremier.categorieMp.subCategorieMp.id =:subCategorieMp  group by d.matierePremier.id,d.fournisseur.id");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				  query.setParameter("magasin",magasin.getId());				  
				  query.setParameter("subCategorieMp",subCategorieMp.getId());
				 
			     query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			    
			     
			
			 
			 
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp==null)
			{
				
				
				
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null  then d.quantite else 0 end) ,sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end), sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and  d.matierePremier.categorieMp.id=:categorieMp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
				  query.setParameter("PERTE", Constantes.TYPE_PERTE);
				  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("categorieMp",categorieMp.getId());
			
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp!=null)
			{
			
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null  then d.quantite else 0 end) ,sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and d.matierePremier.categorieMp.id=:categorieMp and d.matierePremier.id=:mp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);


			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("categorieMp",categorieMp.getId());
			  query.setParameter("mp",mp.getId());
			  query.setParameter("PERTE", Constantes.TYPE_PERTE);
			  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp!=null)
			{
				
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null then d.quantite else 0 end) , sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and  d.matierePremier.categorieMp.subCategorieMp.id=:subcategorieMp and d.matierePremier.id=:mp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("subcategorieMp",subCategorieMp.getId());
			  query.setParameter("mp",mp.getId());
			  query.setParameter("PERTE", Constantes.TYPE_PERTE);
			  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			 
			 
			}
			
		    
		
		return query.list();
		
		

	}
	
	
	@Override
	public List<Object[]> listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurExistante (Date dateDebut,Date dateFin, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp ) {
		// TODO Auto-generated method stub
		Query query=null;
		
		    
		    
			
			if(dateDebut!=null && magasin!=null && subCategorieMp==null && categorieMp==null && mp==null)
			{

				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null  then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end), sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) ,sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and t.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE);
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
			    query.setParameter("magasin",magasin.getId());
			    query.setParameter("PERTE", Constantes.TYPE_PERTE);
			    query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			  
			   
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp==null)
			{

				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select d.matierePremier ,d.fournisseur,sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) from   DetailTransferStockMP d where   d.transferStockMP.dateTransfer between :dateDebut and :dateFin and   d.matierePremier.categorieMp.subCategorieMp.id =:subCategorieMp  group by d.matierePremier.id,d.fournisseur.id");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				  query.setParameter("magasin",magasin.getId());				  
				  query.setParameter("subCategorieMp",subCategorieMp.getId());
				
				 query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			    
				
			
			 
			 
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp==null)
			{
				
				
				
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null  then d.quantite else 0 end) ,sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end), sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and  d.matierePremier.categorieMp.id=:categorieMp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
				  query.setParameter("PERTE", Constantes.TYPE_PERTE);
				  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("categorieMp",categorieMp.getId());
			
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp!=null)
			{
			
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null  then d.quantite else 0 end) ,sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and d.matierePremier.categorieMp.id=:categorieMp and d.matierePremier.id=:mp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);


			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("categorieMp",categorieMp.getId());
			  query.setParameter("mp",mp.getId());
			  query.setParameter("PERTE", Constantes.TYPE_PERTE);
			  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp!=null)
			{
				
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier ,d.fournisseur, sum( case when t.statut=:AJOUT and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin) then d.quantite else 0 end),sum( case when t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype <> null then d.quantite else 0 end) , sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and  d.matierePremier.categorieMp.subCategorieMp.id=:subcategorieMp and d.matierePremier.id=:mp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier,d.fournisseur ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("subcategorieMp",subCategorieMp.getId());
			  query.setParameter("mp",mp.getId());
			  query.setParameter("PERTE", Constantes.TYPE_PERTE);
			  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			 
			 
			}
			
		    
		
		return query.list();
		
		

	}
	
	
	@Override
	public List<DetailTransferStockMP> listeEtatStockMPTransfertEnAttenteThe(Date dateDebut,Date dateFin, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp ) {
		// TODO Auto-generated method stub
		Query query=null;
		
		    
		    
			
			if(dateDebut!=null && magasin!=null && subCategorieMp==null && categorieMp==null && mp==null)
			{

				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d from  DetailTransferStockMP d where  d.transferStockMP.statut=:SORTIEENATTENT and d.magasinDestination.id=:magasin  and  d.transferStockMP.dateTransfer between :dateDebut and :dateFin ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			   
			    query.setParameter("magasin",magasin.getId());
			   
			   
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp==null)
			{

				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select d  from  DetailTransferStockMP d where d.transferStockMP.statut=:SORTIEENATTENT and d.magasinDestination.id=:magasin  and  d.matierePremier.categorieMp.subCategorieMp.id =:subCategorieMp and  d.transferStockMP.dateTransfer between :dateDebut and :dateFin ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				  query.setParameter("magasin",magasin.getId());				  
				  query.setParameter("subCategorieMp",subCategorieMp.getId());
				 
			    
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
		
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp==null)
			{
				
				
				
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d  from  DetailTransferStockMP d where   d.transferStockMP.statut=:SORTIEENATTENT and d.magasinDestination.id=:magasin  and d.matierePremier.categorieMp.id=:categorieMp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			  
			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("categorieMp",categorieMp.getId());
			
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp!=null)
			{
			
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d  from   DetailTransferStockMP d where d.transferStockMP.statut=:SORTIEENATTENT and d.magasinDestination.id=:magasin   and d.matierePremier.categorieMp.id=:categorieMp and d.matierePremier.id=:mp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			


			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("categorieMp",categorieMp.getId());
			  query.setParameter("mp",mp.getId());
			 
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp!=null)
			{
				
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d  from   DetailTransferStockMP d where d.transferStockMP.statut=:SORTIEENATTENT and d.magasinDestination.id=:magasin   and  d.matierePremier.categorieMp.subCategorieMp.id=:subcategorieMp and d.matierePremier.id=:mp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				
			  
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			 
			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("subcategorieMp",subCategorieMp.getId());
			  query.setParameter("mp",mp.getId());
			 
			 
			}
			
		    
		
		return query.list();
		
		

	}
	
	@Override
	public List<DetailTransferStockMP> listeEtatStockMPTransfertEnAttenteNonThe(Date dateDebut,Date dateFin, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp ) {
		// TODO Auto-generated method stub
		Query query=null;
		
		    
		    
			
			if(dateDebut!=null && magasin!=null && subCategorieMp==null && categorieMp==null && mp==null)
			{

				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d from  DetailTransferStockMP d where  d.transferStockMP.statut=:SORTIEENATTENT and d.magasinDestination.id=:magasin  and  d.transferStockMP.dateTransfer between :dateDebut and :dateFin ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			   
			    query.setParameter("magasin",magasin.getId());
			   
			   
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp==null)
			{

				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select d  from  DetailTransferStockMP d where d.transferStockMP.statut=:SORTIEENATTENT and d.magasinDestination.id=:magasin  and  d.matierePremier.categorieMp.subCategorieMp.id !=:subCategorieMp and  d.transferStockMP.dateTransfer between :dateDebut and :dateFin ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				  query.setParameter("magasin",magasin.getId());				  
				  query.setParameter("subCategorieMp",subCategorieMp.getId());
				 
			    
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
		
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp==null)
			{
				
				
				
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d  from  DetailTransferStockMP d where   d.transferStockMP.statut=:SORTIEENATTENT and d.magasinDestination.id=:magasin  and d.matierePremier.categorieMp.id !=:categorieMp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			  
			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("categorieMp",categorieMp.getId());
			
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp!=null)
			{
			
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d  from   DetailTransferStockMP d where d.transferStockMP.statut=:SORTIEENATTENT and d.magasinDestination.id=:magasin   and d.matierePremier.categorieMp.id !=:categorieMp and d.matierePremier.id=:mp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			


			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("categorieMp",categorieMp.getId());
			  query.setParameter("mp",mp.getId());
			 
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp!=null)
			{
				
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d  from   DetailTransferStockMP d where d.transferStockMP.statut=:SORTIEENATTENT  and d.magasinDestination.id=:magasin  and  d.matierePremier.categorieMp.subCategorieMp.id !=:subcategorieMp and d.matierePremier.id=:mp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				
			  
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			 
			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("subcategorieMp",subCategorieMp.getId());
			  query.setParameter("mp",mp.getId());
			 
			 
			}
			
		    
		
		return query.list();
		
		

	}
	
	@Override
	public List<Object[]> listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPNonThe(Date dateDebut,Date dateFin, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp ) {
		// TODO Auto-generated method stub
		Query query=null;
		
		    
		    
			
	 if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp==null)
			{

				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  d.matierePremier , sum( case when t.statut=:AJOUT  and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:ENTRE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:CHARGE_SUPP and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:SORTIE and d.magasinSouce.id=:magasin and t.soustype.id is null) then d.quantite else 0 end),sum( case when t.statut=:TRANSFER and d.magasinSouce.id=:magasin then d.quantite else 0 end),sum( case when (t.statut=:RETOUR and d.magasinDestination.id=:magasin ) then d.quantite else 0 end),sum( case when (t.statut=:SORTIE and d.magasinSouce.id=:magasin  and t.soustype.id <> null) then d.quantite else 0 end)  ,sum( case when t.statut=:charge and d.magasinDestination.id=:magasin then d.QuantiteRetour else 0 end)  ,sum( case when t.statut=:FABRIQUE and d.magasinDestination.id=:magasin then d.quantite else 0 end),sum( case when t.statut=:charge and d.magasinDestination.id=:magasin and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end), sum( case when t.statut=:SORTIE_PF and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:SORTIEENATTENT and d.magasinSouce.id=:magasin  then d.quantite else 0 end) , sum( case when t.statut=:PERTE and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:RETOUR and d.magasinSouce.id=:magasin then d.quantite else 0 end) , sum( case when t.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=:magasin then d.quantite else 0 end) from TransferStockMP t , DetailTransferStockMP d where t.id=d.transferStockMP.id and   d.matierePremier.categorieMp.subCategorieMp.id !=:subCategorieMp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
			     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
			     query.setParameter("CHARGE_SUPP",
			    Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
			     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
			     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
			    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
			    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
			    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
				  query.setParameter("PERTE", Constantes.TYPE_PERTE);
				  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
				  query.setParameter("RETOURFOURNISSEURANNULER",Constantes.SOUS_TYPE_SORTIE_RETOUR_FOURNISSEUR_ANNULER); 
				 
				
			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("subCategorieMp",subCategorieMp.getId());
				
			}
			
		    
		
		return query.list();
		
		

	}
	
	
	
	@Override
	public List<Object[]> MinDate( Magasin magasin ) {
		// TODO Auto-generated method stub
		Query query=null;
		
		    
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select  min(d.transferStockMP.dateTransfer) , sum(d.quantite) from   DetailTransferStockMP d   where  (d.magasinDestination.id=:magasin or d.magasinSouce.id=:magasin)  ");
				
			   
				
				
			  query.setParameter("magasin",magasin.getId());
			 
				
		
			
		    
		
		return query.list();
		
		

	}
	
	
	
	
	
	
	@Override
	public List<Object[]> listetransfertSortieAncienneByMagasinBySubCategorieByCategorieBayMP(Date dateDebut, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp ) {
		// TODO Auto-generated method stub
		Query query=null;
		

		 
		    
		    
			
					if(dateDebut!=null && magasin!=null && subCategorieMp==null && categorieMp==null && mp==null)
					{

						//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
						query= session.createQuery("select d.matierePremier ,sum( case when d.transferStockMP.statut=:TRANSFER then d.quantite else 0 end) from DetailTransferStockMP d  where d.magasinSouce.id=:magasin and d.transferStockMP.dateTransfer <:dateDebut group by d.matierePremier ");
						//query.setParameter("Cheque", "Chèque");
						// query.setParameter("Virement", "Virement");
						 query.setParameter("dateDebut", dateDebut);
						
						  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
					    query.setParameter("magasin",magasin.getId());
						
						
					}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp==null)
					{

						
						
						//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
						query= session.createQuery("select d.matierePremier ,sum( case when d.transferStockMP.statut=:TRANSFER then d.quantite else 0 end) from DetailTransferStockMP d  where d.magasinSouce.id=:magasin and d.matierePremier.categorieMp.subCategorieMp.id=:subCategorieMp and d.transferStockMP.dateTransfer <:dateDebut group by d.matierePremier ");
						//query.setParameter("Cheque", "Chèque");
						// query.setParameter("Virement", "Virement");
						 query.setParameter("dateDebut", dateDebut);
						
						  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
					    query.setParameter("magasin",magasin.getId());
						
						
						
						
					  query.setParameter("subCategorieMp",subCategorieMp.getId());
						
					}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp==null)
					{
						
						//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
						query= session.createQuery("select d.matierePremier ,sum( case when d.transferStockMP.statut=:TRANSFER then d.quantite else 0 end) from DetailTransferStockMP d  where d.magasinSouce.id=:magasin and d.matierePremier.categorieMp.id=:categorieMp and d.transferStockMP.dateTransfer <:dateDebut group by d.matierePremier ");
						//query.setParameter("Cheque", "Chèque");
						// query.setParameter("Virement", "Virement");
						 query.setParameter("dateDebut", dateDebut);
						
						  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
					   
						
										
					  query.setParameter("magasin",magasin.getId());
					  query.setParameter("categorieMp",categorieMp.getId());
						
					}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp!=null)
					{
					
						//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
						query= session.createQuery("select d.matierePremier ,sum( case when d.transferStockMP.statut=:TRANSFER then d.quantite else 0 end) from DetailTransferStockMP d  where d.magasinSouce.id=:magasin and d.matierePremier.categorieMp.id=:categorieMp and d.matierePremier.id=:mp and d.transferStockMP.dateTransfer <:dateDebut group by d.matierePremier ");
						//query.setParameter("Cheque", "Chèque");
						// query.setParameter("Virement", "Virement");
						 query.setParameter("dateDebut", dateDebut);
						
						  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);	
						
						
						  query.setParameter("magasin",magasin.getId());
					  query.setParameter("categorieMp",categorieMp.getId());
					  query.setParameter("mp",mp.getId());
						
					}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp!=null)
					{
						
						//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
						query= session.createQuery("select d.matierePremier ,sum( case when d.transferStockMP.statut=:TRANSFER then d.quantite else 0 end) from DetailTransferStockMP d  where d.magasinSouce.id=:magasin and d.matierePremier.categorieMp.subCategorieMp.id=:subcategorieMp and d.matierePremier.id=:mp and d.transferStockMP.dateTransfer <:dateDebut group by d.matierePremier ");
						//query.setParameter("Cheque", "Chèque");
						// query.setParameter("Virement", "Virement");
						 query.setParameter("dateDebut", dateDebut);
						
						  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
						  query.setParameter("magasin",magasin.getId());
					  query.setParameter("subcategorieMp",subCategorieMp.getId());
					  query.setParameter("mp",mp.getId());
						
					}
		    
		    
		    
		    
		    
		    
		    
		 
		return query.list();

	}
	
	@Override
	public List<Object[]> listetransfertSortieAncienne(Date dateDebut, Magasin magasin ) {
		// TODO Auto-generated method stub
		Query query=null;
		
//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
			query= session.createQuery("select d.matierePremier ,sum( case when d.transferStockMP.statut=:TRANSFER then d.quantite else 0 end) from DetailTransferStockMP d  where d.magasinSouce.id=:magasin and d.transferStockMP.dateTransfer <:dateDebut group by d.matierePremier ");
			//query.setParameter("Cheque", "Chèque");
			// query.setParameter("Virement", "Virement");
			 query.setParameter("dateDebut", dateDebut);
			
			  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
		    query.setParameter("magasin",magasin.getId());
		 
		 
		return query.list();

	}
	
	@Override
	public List<Object[]> listetransfertSortieActuel(Date dateDebut, Magasin magasin ) {
		// TODO Auto-generated method stub
		Query query=null;
		
//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
			query= session.createQuery("select d.matierePremier ,sum( case when d.transferStockMP.statut=:TRANSFER then d.quantite else 0 end) from DetailTransferStockMP d  where d.magasinSouce.id=:magasin and d.transferStockMP.dateTransfer =:dateDebut group by d.matierePremier ");
			//query.setParameter("Cheque", "Chèque");
			// query.setParameter("Virement", "Virement");
			 query.setParameter("dateDebut", dateDebut);
			
			  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
		    query.setParameter("magasin",magasin.getId());
		 
		 
		return query.list();

	}
	
	
	@Override
	public List<Object[]> listetransfertSortieActuelByMagasinBySubCategorieByCategorieBayMP(Date dateDebut,Date dateFin, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp ) {
		// TODO Auto-generated method stub
		Query query=null;
		

		 
		 
		    
			
			if(dateDebut!=null && magasin!=null && subCategorieMp==null && categorieMp==null && mp==null)
			{


				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select d.matierePremier ,sum( case when d.transferStockMP.statut=:TRANSFER then d.quantite else 0 end) from DetailTransferStockMP d  where d.magasinSouce.id=:magasin and d.transferStockMP.dateTransfer between :dateDebut and :dateFin group by d.matierePremier ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
			    query.setParameter("magasin",magasin.getId());
				
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp==null)
			{

				


				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select d.matierePremier ,sum( case when d.transferStockMP.statut=:TRANSFER then d.quantite else 0 end) from DetailTransferStockMP d  where d.magasinSouce.id=:magasin and d.matierePremier.categorieMp.subCategorieMp.id=:subCategorieMp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin group by d.matierePremier ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
				 
			    query.setParameter("magasin",magasin.getId());
				
				
			  query.setParameter("subCategorieMp",subCategorieMp.getId());
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp==null)
			{
				

				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select d.matierePremier ,sum( case when d.transferStockMP.statut=:TRANSFER then d.quantite else 0 end) from DetailTransferStockMP d  where d.magasinSouce.id=:magasin and d.matierePremier.categorieMp.id=:categorieMp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin group by d.matierePremier ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
			   
				
								
			  query.setParameter("magasin",magasin.getId());
			  query.setParameter("categorieMp",categorieMp.getId());
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp!=null)
			{
			

				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select d.matierePremier ,sum( case when d.transferStockMP.statut=:TRANSFER then d.quantite else 0 end) from DetailTransferStockMP d  where d.magasinSouce.id=:magasin and d.matierePremier.categorieMp.id=:categorieMp and d.matierePremier.id=:mp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin group by d.matierePremier ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
				  query.setParameter("magasin",magasin.getId());
				
			
			  query.setParameter("categorieMp",categorieMp.getId());
			  query.setParameter("mp",mp.getId());
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp!=null)
			{
				
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select d.matierePremier ,sum( case when d.transferStockMP.statut=:TRANSFER then d.quantite else 0 end) from DetailTransferStockMP d  where d.magasinSouce.id=:magasin  and d.matierePremier.categorieMp.subCategorieMp.id=:subcategorieMp and d.matierePremier.id=:mp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin group by d.matierePremier ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
			    query.setParameter("magasin",magasin.getId());

			
			  query.setParameter("subcategorieMp",subCategorieMp.getId());
			  query.setParameter("mp",mp.getId());
				
			}
    
		    
		    
		    
		    
		    
		    
		    
		return query.list();

	}
	
	
	
	
	
	@Override
	public List<Object[]> listetransfertEntrerAncienne(Date dateDebut, Magasin magasin ) {
		// TODO Auto-generated method stub
		Query query=null;
		
//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
			query= session.createQuery("select d.matierePremier ,sum( case when d.transferStockMP.statut=:TRANSFER then d.quantite else 0 end) from DetailTransferStockMP d  where d.magasinDestination.id=:magasin and d.transferStockMP.dateTransfer <:dateDebut group by d.matierePremier ");
			//query.setParameter("Cheque", "Chèque");
			// query.setParameter("Virement", "Virement");
			 query.setParameter("dateDebut", dateDebut);
			
			  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
		    query.setParameter("magasin",magasin.getId());
		 
		    
		return query.list();

	}
	
	
	@Override
	public List<Object[]> listetransfertEntrerAncienneByMagasinBySubCategorieByCategorieBayMP(Date dateDebut, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp ) {
		// TODO Auto-generated method stub
		Query query=null;
		

				if(dateDebut!=null && magasin!=null && subCategorieMp==null && categorieMp==null && mp==null)
				{


					//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
					query= session.createQuery("select d.matierePremier ,sum( case when d.transferStockMP.statut=:TRANSFER then d.quantite else 0 end) from DetailTransferStockMP d  where d.magasinDestination.id=:magasin and d.transferStockMP.dateTransfer <:dateDebut group by d.matierePremier ");
					//query.setParameter("Cheque", "Chèque");
					// query.setParameter("Virement", "Virement");
					 query.setParameter("dateDebut", dateDebut);
					
					  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
				    query.setParameter("magasin",magasin.getId());

					
				}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp==null)
				{

					
					//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
					query= session.createQuery("select d.matierePremier ,sum( case when d.transferStockMP.statut=:TRANSFER then d.quantite else 0 end) from DetailTransferStockMP d  where d.magasinDestination.id=:magasin and d.matierePremier.categorieMp.subCategorieMp.id=:subCategorieMp and d.transferStockMP.dateTransfer <:dateDebut group by d.matierePremier ");
					//query.setParameter("Cheque", "Chèque");
					// query.setParameter("Virement", "Virement");
					 query.setParameter("dateDebut", dateDebut);
					
					  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
				    query.setParameter("magasin",magasin.getId());


					
					
				  query.setParameter("subCategorieMp",subCategorieMp.getId());
					
				}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp==null)
				{
					
					//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
					query= session.createQuery("select d.matierePremier ,sum( case when d.transferStockMP.statut=:TRANSFER then d.quantite else 0 end) from DetailTransferStockMP d  where d.magasinDestination.id=:magasin and d.matierePremier.categorieMp.id=:categorieMp and d.transferStockMP.dateTransfer <:dateDebut group by d.matierePremier ");
					//query.setParameter("Cheque", "Chèque");
					// query.setParameter("Virement", "Virement");
					 query.setParameter("dateDebut", dateDebut);
					
					  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
				    query.setParameter("magasin",magasin.getId());

				  query.setParameter("categorieMp",categorieMp.getId());
					
				}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp!=null)
				{
				

					//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
					query= session.createQuery("select d.matierePremier ,sum( case when d.transferStockMP.statut=:TRANSFER then d.quantite else 0 end) from DetailTransferStockMP d  where d.magasinDestination.id=:magasin and d.matierePremier.categorieMp.id=:categorieMp and d.matierePremier.id=:mp and  d.transferStockMP.dateTransfer <:dateDebut group by d.matierePremier ");
					//query.setParameter("Cheque", "Chèque");
					// query.setParameter("Virement", "Virement");
					 query.setParameter("dateDebut", dateDebut);
					
					  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
				    query.setParameter("magasin",magasin.getId());

					
				
				  query.setParameter("categorieMp",categorieMp.getId());
				  query.setParameter("mp",mp.getId());
					
				}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp!=null)
				{


					//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
					query= session.createQuery("select d.matierePremier ,sum( case when d.transferStockMP.statut=:TRANSFER then d.quantite else 0 end) from DetailTransferStockMP d  where d.magasinDestination.id=:magasin and d.matierePremier.categorieMp.subCategorieMp.id=:subcategorieMp and d.matierePremier.id=:mp and d.transferStockMP.dateTransfer <:dateDebut group by d.matierePremier ");
					//query.setParameter("Cheque", "Chèque");
					// query.setParameter("Virement", "Virement");
					 query.setParameter("dateDebut", dateDebut);
					
					  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
				    query.setParameter("magasin",magasin.getId());

				  query.setParameter("subcategorieMp",subCategorieMp.getId());
				  query.setParameter("mp",mp.getId());
					
				}
	    
		    
		    
		    
		    
		 
		    
		return query.list();

	}
	
	
	
	@Override
	public List<Object[]> listetransfertEntrerActuel(Date dateDebut, Magasin magasin ) {
		// TODO Auto-generated method stub
		Query query=null;
		
//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
			query= session.createQuery("select d.matierePremier ,sum( case when d.transferStockMP.statut=:TRANSFER then d.quantite else 0 end) from DetailTransferStockMP d  where d.magasinDestination.id=:magasin and d.transferStockMP.dateTransfer =:dateDebut group by d.matierePremier ");
			//query.setParameter("Cheque", "Chèque");
			// query.setParameter("Virement", "Virement");
			 query.setParameter("dateDebut", dateDebut);
			
			  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
		    query.setParameter("magasin",magasin.getId());
		 
		 
		return query.list();

	}
	
	
	@Override
	public List<Object[]> listetransfertEntrerActuelByMagasinBySubCategorieByCategorieBayMP(Date dateDebut,Date dateFin, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp ) {
		// TODO Auto-generated method stub
		Query query=null;
		

		 
		    

			if(dateDebut!=null && magasin!=null && subCategorieMp==null && categorieMp==null && mp==null)
			{

				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select d.matierePremier ,sum( case when d.transferStockMP.statut=:TRANSFER then d.quantite else 0 end) from DetailTransferStockMP d  where d.magasinDestination.id=:magasin and d.transferStockMP.dateTransfer between :dateDebut and :dateFin group by d.matierePremier ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
			    query.setParameter("magasin",magasin.getId());

				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp==null)
			{

				
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select d.matierePremier ,sum( case when d.transferStockMP.statut=:TRANSFER then d.quantite else 0 end) from DetailTransferStockMP d  where d.magasinDestination.id=:magasin and d.matierePremier.categorieMp.subCategorieMp.id=:subCategorieMp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin group by d.matierePremier ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
			    query.setParameter("magasin",magasin.getId());
				
			  query.setParameter("subCategorieMp",subCategorieMp.getId());
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp==null)
			{
				
				
				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select d.matierePremier ,sum( case when d.transferStockMP.statut=:TRANSFER then d.quantite else 0 end) from DetailTransferStockMP d  where d.magasinDestination.id=:magasin and d.matierePremier.categorieMp.id=:categorieMp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin group by d.matierePremier ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
			    query.setParameter("magasin",magasin.getId());

			  query.setParameter("categorieMp",categorieMp.getId());
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp!=null && mp!=null)
			{
			

				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select d.matierePremier ,sum( case when d.transferStockMP.statut=:TRANSFER then d.quantite else 0 end) from DetailTransferStockMP d  where d.magasinDestination.id=:magasin and d.matierePremier.categorieMp.id=:categorieMp and d.matierePremier.id=:mp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin group by d.matierePremier ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
			    query.setParameter("magasin",magasin.getId());
				
			
			  query.setParameter("categorieMp",categorieMp.getId());
			  query.setParameter("mp",mp.getId());
				
			}else if(dateDebut!=null && magasin!=null && subCategorieMp!=null && categorieMp==null && mp!=null)
			{

				//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
				query= session.createQuery("select d.matierePremier ,sum( case when d.transferStockMP.statut=:TRANSFER then d.quantite else 0 end) from DetailTransferStockMP d  where d.magasinDestination.id=:magasin and d.matierePremier.categorieMp.subCategorieMp.id=:subcategorieMp and d.matierePremier.id=:mp and d.transferStockMP.dateTransfer between :dateDebut and :dateFin group by d.matierePremier ");
				//query.setParameter("Cheque", "Chèque");
				// query.setParameter("Virement", "Virement");
				 query.setParameter("dateDebut", dateDebut);
				 query.setParameter("dateFin", dateFin);
				  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
			    query.setParameter("magasin",magasin.getId());
				query.setParameter("subcategorieMp",subCategorieMp.getId());
			  query.setParameter("mp",mp.getId());
				
			}
		    
		    
		    
		    
		    
		    
		    
		 
		return query.list();

	}
	
	
	///// Mouvement de Stock MP Dechet et Manque 
	
	public List<DetailTransferStockMP> ListTransferStockMPDechetmanqueEntreDeuxDatesBYMagasinByMPByFournisseurByType(Date dateDebut , Date dateFin , MatierePremier mp,String statut, Magasin magasin ,FournisseurMP fournisseurMP , TypeSortie typeSortie) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(mp!=null && fournisseurMP!=null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer between :dateDebut and :dateFin and matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin and fournisseur.id=:fournisseurMP and transferStockMP.type.id=:typeSortie order by matierePremier,transferStockMP.dateTransfer ");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
				 query.setParameter("fournisseurMP",fournisseurMP.getId());
				 query.setParameter("typeSortie",typeSortie.getId());
				 
				 
			
		}else if(mp==null && fournisseurMP!=null)
			
			
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer between :dateDebut and :dateFin  and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin and fournisseur.id=:fournisseurMP and transferStockMP.type.id=:typeSortie order by matierePremier,transferStockMP.dateTransfer");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
				 query.setParameter("fournisseurMP",fournisseurMP.getId());
				 query.setParameter("typeSortie",typeSortie.getId());
			
			
		}else if(mp!=null && fournisseurMP==null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer between :dateDebut and :dateFin and matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin and transferStockMP.type.id=:typeSortie order by matierePremier,transferStockMP.dateTransfer");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
				 query.setParameter("typeSortie",typeSortie.getId());
			
			
		}else if(mp==null && fournisseurMP==null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer between :dateDebut and :dateFin and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin and transferStockMP.type.id=:typeSortie order by matierePremier,transferStockMP.dateTransfer");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
				 query.setParameter("typeSortie",typeSortie.getId());
			
			
		}
				
			
				
				return query.list();
}
	
	
	@Override
	public List<Object[]> listeInitialEnVrac(int year , String statut , Magasin magasin , int categorie) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
			query= session.createQuery(" select d.matierePremier.code , d.matierePremier.nom ,sum(d.quantite)  from DetailTransferStockMP d where  YEAR(transferStockMP.dateTransfer )= :year and transferStockMP.statut=:statut and d.matierePremier.categorieMp.subCategorieMp.id=:categorie and d.magasinDestination.id=:magasin group by d.matierePremier.code order by d.matierePremier.id");
			query.setParameter("year", year);
			query.setParameter("statut", statut);
			query.setParameter("magasin", magasin.getId());
			query.setParameter("categorie", categorie);
		
		return query.list();

	}
	
	
	
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesByMagasinByStatutByCodeTransferEntrer(Date dateDebut , Date dateFin , String code,String statut,Magasin magasin) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(dateDebut!=null && dateFin!=null && !code.equals("") && !statut.equals("") && magasin!=null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer between :dateDebut and :dateFin and transferStockMP.CodeTransfer =:code and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("code", code);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
		}else if(dateDebut!=null && dateFin!=null && !code.equals("") && !statut.equals("") && magasin==null)
		{
			query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer between :dateDebut and :dateFin and transferStockMP.CodeTransfer =:code and  transferStockMP.statut=:statut");
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("code", code);
			 query.setParameter("statut",statut);
			
		}else if(dateDebut!=null && dateFin!=null && !code.equals("") && statut.equals("") && magasin==null)
		{
			query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer between :dateDebut and :dateFin and transferStockMP.CodeTransfer =:code ");
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("code", code);
		
		}else if(dateDebut!=null && dateFin!=null && code.equals("") && statut.equals("") && magasin==null)
		{
			query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer between :dateDebut and :dateFin ");
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
		
		}else if(dateDebut!=null && dateFin!=null && !code.equals("") && statut.equals("") && magasin==null)
		{
			query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer between :dateDebut and :dateFin and transferStockMP.CodeTransfer =:code ");
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("code", code);
			 query.setParameter("statut",statut);
			 query.setParameter("magasin",magasin.getId());
		}
			
				
				return query.list();
}	
	
	
	/*
	@Override
	public List<Object[]> listeSituationEnVrac(Date dateDebut,Date dateFin,  SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp ) {
		// TODO Auto-generated method stub
		Query query=null;
		//+ sum( case when d.transferStockMP.statut=:TRANSFER then d.quantite else 0 end)
//"select matierePremier , sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end)  ,sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end),sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end),sum( case when transferStockMP.statut=:charge then d.quantite else 0 end),sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end),sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end),(sum( case when transferStockMP.statut=:Initial then d.quantite else 0 end) + sum( case when transferStockMP.statut=:AJOUT then d.quantite else 0 end) + sum( case when transferStockMP.statut=:ENTRE then d.quantite else 0 end))-(sum( case when transferStockMP.statut=:charge then d.quantite else 0 end) +  sum( case when transferStockMP.statut=:CHARGE_SUPP then d.quantite else 0 end) + sum( case when transferStockMP.statut=:SORTIE then d.quantite else 0 end)) from DetailTransferStockMP d  where (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and transferStockMP.dateTransfer <=: dateDebut group by matierePremier"			
	
		if(dateDebut!=null  && subCategorieMp==null && categorieMp==null && mp==null)
		{
			query= session.createQuery("select d.matierePremier ,sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=1  then d.quantite else 0 end),sum(case when d.transferStockMP.statut=:Initial and  d.magasinDestination.id=1   then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:AJOUT  and d.magasinDestination.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:ENTRE and d.magasinDestination.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinDestination.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:CHARGE_SUPP and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=1 and d.transferStockMP.soustype.id is null) then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:RETOUR and d.magasinDestination.id=1 ) then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=1  and d.transferStockMP.soustype.id <> null) then d.quantite else 0 end)  , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=1 then d.QuantiteRetour else 0 end)  , sum( case when d.transferStockMP.statut=:FABRIQUE and d.magasinDestination.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=1 and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when d.transferStockMP.statut=:SORTIE_PF and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=1 and d.magasinDestination.id=18  then d.quantite else 0 end) ,sum( case when d.transferStockMP.statut=:PERTE and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOUR and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=1 then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=12 and d.magasinSouce.depot.id <> d.magasinDestination.depot.id and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end), sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=12  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:AJOUT  and d.magasinDestination.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:ENTRE and d.magasinDestination.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinDestination.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:CHARGE_SUPP and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=12 and d.transferStockMP.soustype.id is null) then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:RETOUR and d.magasinDestination.id=12 ) then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=12  and d.transferStockMP.soustype.id <> null) then d.quantite else 0 end)  , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=12 then d.QuantiteRetour else 0 end)  , sum( case when d.transferStockMP.statut=:FABRIQUE and d.magasinDestination.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=12 and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when d.transferStockMP.statut=:SORTIE_PF and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=12 and d.magasinDestination.id=18  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:PERTE and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOUR and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=12 then d.quantite else 0 end) ,sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=16 and d.magasinSouce.depot.id <> d.magasinDestination.depot.id and  d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end),sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=16  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:AJOUT  and d.magasinDestination.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:ENTRE and d.magasinDestination.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinDestination.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinSouce.id=16 then d.quantite else 0 end) ,sum( case when d.transferStockMP.statut=:CHARGE_SUPP and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=16 and d.transferStockMP.soustype.id is null) then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:RETOUR and d.magasinDestination.id=16 ) then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=16  and d.transferStockMP.soustype.id <> null) then d.quantite else 0 end)  , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=16 then d.QuantiteRetour else 0 end)  , sum( case when d.transferStockMP.statut=:FABRIQUE and d.magasinDestination.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=16 and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when d.transferStockMP.statut=:SORTIE_PF and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=16 and d.magasinDestination.id=18  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:PERTE and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOUR and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=16 then d.quantite else 0 end)  ,sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=20 and d.magasinSouce.depot.id <> d.magasinDestination.depot.id and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end), sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=20  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:AJOUT  and d.magasinDestination.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:ENTRE and d.magasinDestination.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinDestination.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:CHARGE_SUPP and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=20 and d.transferStockMP.soustype.id is null) then d.quantite else 0 end) ,sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinSouce.id=20 then d.quantite else 0 end) ,sum( case when (d.transferStockMP.statut=:RETOUR and d.magasinDestination.id=20 ) then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=20  and d.transferStockMP.soustype.id <> null) then d.quantite else 0 end)  ,sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=20 then d.QuantiteRetour else 0 end)  , sum( case when d.transferStockMP.statut=:FABRIQUE and d.magasinDestination.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=20 and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when d.transferStockMP.statut=:SORTIE_PF and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=20 and d.magasinDestination.id=18 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:PERTE and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOUR and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=20 then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:RECEPTION_ENATTENT and d.magasinDestination.id=1 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:RECEPTION_ENATTENT and d.magasinDestination.id=12 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end)sum( case when d.transferStockMP.statut=:RECEPTION_ENATTENT and d.magasinDestination.id=16 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:RECEPTION_ENATTENT and d.magasinDestination.id=20 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end) from DetailTransferStockMP d  where  d.transferStockMP.dateTransfer between :dateDebut and :dateFin group by d.matierePremier order by d.matierePremier.categorieMp.subCategorieMp.id ");
			//query.setParameter("Cheque", "Chèque");
			// query.setParameter("Virement", "Virement");
			 query.setParameter("dateDebut", dateDebut);
			 query.setParameter("dateFin", dateFin);
			 query.setParameter("Initial", Constantes.ETAT_TRANSFER_STOCK_INITIAL);
			 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
		     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
		     query.setParameter("CHARGE_SUPP",  Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
		     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
		     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
		    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
		    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
		    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
			  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
			  query.setParameter("PERTE", Constantes.TYPE_PERTE);
			  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			  query.setParameter("RETOURFOURNISSEURANNULER", Constantes.SOUS_TYPE_SORTIE_RETOUR_FOURNISSEUR_ANNULER);
			  query.setParameter("RECEPTION_ENATTENT", Constantes.ETAT_RECEPTION_ENATTENT);
			    
			  
			  
		
		}else if(dateDebut!=null  && subCategorieMp!=null && categorieMp==null && mp==null)
		{
			
			query= session.createQuery("select d.matierePremier ,sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=1   then d.quantite else 0 end),sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=1  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:AJOUT  and d.magasinDestination.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:ENTRE and d.magasinDestination.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinDestination.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:CHARGE_SUPP and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=1 and d.transferStockMP.soustype.id is null) then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:RETOUR and d.magasinDestination.id=1 ) then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=1  and d.transferStockMP.soustype.id <> null) then d.quantite else 0 end)  , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=1 then d.QuantiteRetour else 0 end)  , sum( case when d.transferStockMP.statut=:FABRIQUE and d.magasinDestination.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=1 and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when d.transferStockMP.statut=:SORTIE_PF and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=1 and d.magasinDestination.id=18 then d.quantite else 0 end) ,sum( case when d.transferStockMP.statut=:PERTE and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOUR and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=1 then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=12 and d.magasinSouce.depot.id <> d.magasinDestination.depot.id and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end), sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=12  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:AJOUT  and d.magasinDestination.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:ENTRE and d.magasinDestination.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinDestination.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:CHARGE_SUPP and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=12 and d.transferStockMP.soustype.id is null) then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:RETOUR and d.magasinDestination.id=12 ) then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=12  and d.transferStockMP.soustype.id <> null) then d.quantite else 0 end)  , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=12 then d.QuantiteRetour else 0 end)  , sum( case when d.transferStockMP.statut=:FABRIQUE and d.magasinDestination.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=12 and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when d.transferStockMP.statut=:SORTIE_PF and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=12 and d.magasinDestination.id=18 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:PERTE and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOUR and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=12 then d.quantite else 0 end) ,sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=16 and d.magasinSouce.depot.id <> d.magasinDestination.depot.id and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end),sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=16  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:AJOUT  and d.magasinDestination.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:ENTRE and d.magasinDestination.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinDestination.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinSouce.id=16 then d.quantite else 0 end) ,sum( case when d.transferStockMP.statut=:CHARGE_SUPP and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=16 and d.transferStockMP.soustype.id is null) then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:RETOUR and d.magasinDestination.id=16 ) then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=16  and d.transferStockMP.soustype.id <> null) then d.quantite else 0 end)  , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=16 then d.QuantiteRetour else 0 end)  , sum( case when d.transferStockMP.statut=:FABRIQUE and d.magasinDestination.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=16 and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when d.transferStockMP.statut=:SORTIE_PF and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=16 and d.magasinDestination.id=18 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:PERTE and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOUR and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=16 then d.quantite else 0 end)  ,sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=20 and d.magasinSouce.depot.id <> d.magasinDestination.depot.id and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end), sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=20  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:AJOUT  and d.magasinDestination.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:ENTRE and d.magasinDestination.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinDestination.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:CHARGE_SUPP and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=20 and d.transferStockMP.soustype.id is null) then d.quantite else 0 end) ,sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinSouce.id=20 then d.quantite else 0 end) ,sum( case when (d.transferStockMP.statut=:RETOUR and d.magasinDestination.id=20 ) then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=20  and d.transferStockMP.soustype.id <> null) then d.quantite else 0 end)  ,sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=20 then d.QuantiteRetour else 0 end)  , sum( case when d.transferStockMP.statut=:FABRIQUE and d.magasinDestination.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=20 and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when d.transferStockMP.statut=:SORTIE_PF and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=20 and d.magasinDestination.id=18 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:PERTE and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOUR and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=20 then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:RECEPTION_ENATTENT and d.magasinDestination.id=1 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:RECEPTION_ENATTENT and d.magasinDestination.id=12 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:RECEPTION_ENATTENT and d.magasinDestination.id=16 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:RECEPTION_ENATTENT and d.magasinDestination.id=20 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end)  from DetailTransferStockMP d  where  d.matierePremier.categorieMp.subCategorieMp.id=:subCategorieMp and   d.transferStockMP.dateTransfer between :dateDebut and :dateFin group by d.matierePremier order by d.matierePremier.categorieMp.subCategorieMp.id");
			//query.setParameter("Cheque", "Chèque");
			// query.setParameter("Virement", "Virement");
			 query.setParameter("dateDebut", dateDebut);
			 query.setParameter("dateFin", dateFin);
			 query.setParameter("Initial", Constantes.ETAT_TRANSFER_STOCK_INITIAL);
			 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
		     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
		     query.setParameter("CHARGE_SUPP",  Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
		     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
		     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
		    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
		    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
		    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
			  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
			  query.setParameter("PERTE", Constantes.TYPE_PERTE);
			  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			  query.setParameter("RETOURFOURNISSEURANNULER", Constantes.SOUS_TYPE_SORTIE_RETOUR_FOURNISSEUR_ANNULER);
		  
		  query.setParameter("subCategorieMp",subCategorieMp.getId());
		  query.setParameter("RECEPTION_ENATTENT", Constantes.ETAT_RECEPTION_ENATTENT);
			
		}else if(dateDebut!=null  && subCategorieMp!=null && categorieMp!=null && mp==null)
		{
			
			query= session.createQuery("select d.matierePremier ,sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=1   then d.quantite else 0 end),sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=1  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:AJOUT  and d.magasinDestination.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:ENTRE and d.magasinDestination.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinDestination.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:CHARGE_SUPP and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=1 and d.transferStockMP.soustype.id is null) then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:RETOUR and d.magasinDestination.id=1 ) then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=1  and d.transferStockMP.soustype.id <> null) then d.quantite else 0 end)  , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=1 then d.QuantiteRetour else 0 end)  , sum( case when d.transferStockMP.statut=:FABRIQUE and d.magasinDestination.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=1 and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when d.transferStockMP.statut=:SORTIE_PF and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=1 and d.magasinDestination.id=18 then d.quantite else 0 end) ,sum( case when d.transferStockMP.statut=:PERTE and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOUR and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=1 then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=12 and d.magasinSouce.depot.id <> d.magasinDestination.depot.id and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end), sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=12  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:AJOUT  and d.magasinDestination.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:ENTRE and d.magasinDestination.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinDestination.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:CHARGE_SUPP and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=12 and d.transferStockMP.soustype.id is null) then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:RETOUR and d.magasinDestination.id=12 ) then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=12  and d.transferStockMP.soustype.id <> null) then d.quantite else 0 end)  , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=12 then d.QuantiteRetour else 0 end)  , sum( case when d.transferStockMP.statut=:FABRIQUE and d.magasinDestination.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=12 and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when d.transferStockMP.statut=:SORTIE_PF and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=12 and d.magasinDestination.id=18 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:PERTE and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOUR and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=12 then d.quantite else 0 end) ,sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=16 and d.magasinSouce.depot.id <> d.magasinDestination.depot.id and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end),sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=16  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:AJOUT  and d.magasinDestination.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:ENTRE and d.magasinDestination.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinDestination.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinSouce.id=16 then d.quantite else 0 end) ,sum( case when d.transferStockMP.statut=:CHARGE_SUPP and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=16 and d.transferStockMP.soustype.id is null) then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:RETOUR and d.magasinDestination.id=16 ) then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=16  and d.transferStockMP.soustype.id <> null) then d.quantite else 0 end)  , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=16 then d.QuantiteRetour else 0 end)  , sum( case when d.transferStockMP.statut=:FABRIQUE and d.magasinDestination.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=16 and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when d.transferStockMP.statut=:SORTIE_PF and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=16 and d.magasinDestination.id=18 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:PERTE and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOUR and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=16 then d.quantite else 0 end)  ,sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=20 and d.magasinSouce.depot.id <> d.magasinDestination.depot.id and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end), sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=20  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:AJOUT  and d.magasinDestination.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:ENTRE and d.magasinDestination.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinDestination.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:CHARGE_SUPP and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=20 and d.transferStockMP.soustype.id is null) then d.quantite else 0 end) ,sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinSouce.id=20 then d.quantite else 0 end) ,sum( case when (d.transferStockMP.statut=:RETOUR and d.magasinDestination.id=20 ) then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=20  and d.transferStockMP.soustype.id <> null) then d.quantite else 0 end)  ,sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=20 then d.QuantiteRetour else 0 end)  , sum( case when d.transferStockMP.statut=:FABRIQUE and d.magasinDestination.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=20 and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when d.transferStockMP.statut=:SORTIE_PF and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=20 and d.magasinDestination.id=18 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:PERTE and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOUR and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=20 then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:RECEPTION_ENATTENT and d.magasinDestination.id=1 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:RECEPTION_ENATTENT and d.magasinDestination.id=12 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:RECEPTION_ENATTENT and d.magasinDestination.id=16 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:RECEPTION_ENATTENT and d.magasinDestination.id=20 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end) from DetailTransferStockMP d  where  d.matierePremier.categorieMp.id=:categorieMp and   d.transferStockMP.dateTransfer between :dateDebut and :dateFin group by d.matierePremier order by d.matierePremier.categorieMp.subCategorieMp.id");
			//query.setParameter("Cheque", "Chèque");
			// query.setParameter("Virement", "Virement");
			 query.setParameter("dateDebut", dateDebut);
			 query.setParameter("dateFin", dateFin);
			 query.setParameter("Initial", Constantes.ETAT_TRANSFER_STOCK_INITIAL);
			 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
		     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
		     query.setParameter("CHARGE_SUPP",  Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
		     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
		     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
		    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
		    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
		    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
			  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
			  query.setParameter("PERTE", Constantes.TYPE_PERTE);
			  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			  query.setParameter("RETOURFOURNISSEURANNULER", Constantes.SOUS_TYPE_SORTIE_RETOUR_FOURNISSEUR_ANNULER);
		  query.setParameter("categorieMp",categorieMp.getId());
		  query.setParameter("RECEPTION_ENATTENT", Constantes.ETAT_RECEPTION_ENATTENT);
			
		}else if(dateDebut!=null  && subCategorieMp!=null && categorieMp!=null && mp!=null)
		{
			
			query= session.createQuery("select d.matierePremier ,sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=1  then d.quantite else 0 end),sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=1  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:AJOUT  and d.magasinDestination.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:ENTRE and d.magasinDestination.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinDestination.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:CHARGE_SUPP and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=1 and d.transferStockMP.soustype.id is null) then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:RETOUR and d.magasinDestination.id=1 ) then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=1  and d.transferStockMP.soustype.id <> null) then d.quantite else 0 end)  , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=1 then d.QuantiteRetour else 0 end)  , sum( case when d.transferStockMP.statut=:FABRIQUE and d.magasinDestination.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=1 and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when d.transferStockMP.statut=:SORTIE_PF and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=1 and d.magasinDestination.id=18 then d.quantite else 0 end) ,sum( case when d.transferStockMP.statut=:PERTE and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOUR and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=1 then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=12 and d.magasinSouce.depot.id <> d.magasinDestination.depot.id and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end), sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=12  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:AJOUT  and d.magasinDestination.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:ENTRE and d.magasinDestination.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinDestination.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:CHARGE_SUPP and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=12 and d.transferStockMP.soustype.id is null) then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:RETOUR and d.magasinDestination.id=12 ) then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=12  and d.transferStockMP.soustype.id <> null) then d.quantite else 0 end)  , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=12 then d.QuantiteRetour else 0 end)  , sum( case when d.transferStockMP.statut=:FABRIQUE and d.magasinDestination.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=12 and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when d.transferStockMP.statut=:SORTIE_PF and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=12 and d.magasinDestination.id=18 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:PERTE and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOUR and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=12 then d.quantite else 0 end) ,sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=16 and d.magasinSouce.depot.id <> d.magasinDestination.depot.id and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end),sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=16  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:AJOUT  and d.magasinDestination.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:ENTRE and d.magasinDestination.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinDestination.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinSouce.id=16 then d.quantite else 0 end) ,sum( case when d.transferStockMP.statut=:CHARGE_SUPP and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=16 and d.transferStockMP.soustype.id is null) then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:RETOUR and d.magasinDestination.id=16 ) then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=16  and d.transferStockMP.soustype.id <> null) then d.quantite else 0 end)  , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=16 then d.QuantiteRetour else 0 end)  , sum( case when d.transferStockMP.statut=:FABRIQUE and d.magasinDestination.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=16 and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when d.transferStockMP.statut=:SORTIE_PF and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=16 and d.magasinDestination.id=18 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:PERTE and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOUR and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=16 then d.quantite else 0 end)  ,sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=20 and d.magasinSouce.depot.id <> d.magasinDestination.depot.id and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end), sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=20  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:AJOUT  and d.magasinDestination.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:ENTRE and d.magasinDestination.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinDestination.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:CHARGE_SUPP and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=20 and d.transferStockMP.soustype.id is null) then d.quantite else 0 end) ,sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinSouce.id=20 then d.quantite else 0 end) ,sum( case when (d.transferStockMP.statut=:RETOUR and d.magasinDestination.id=20 ) then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=20  and d.transferStockMP.soustype.id <> null) then d.quantite else 0 end)  ,sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=20 then d.QuantiteRetour else 0 end)  , sum( case when d.transferStockMP.statut=:FABRIQUE and d.magasinDestination.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=20 and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when d.transferStockMP.statut=:SORTIE_PF and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=20 and d.magasinDestination.id=18 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:PERTE and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOUR and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=20 then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:RECEPTION_ENATTENT and d.magasinDestination.id=1 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:RECEPTION_ENATTENT and d.magasinDestination.id=12 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:RECEPTION_ENATTENT and d.magasinDestination.id=16 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:RECEPTION_ENATTENT and d.magasinDestination.id=20 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end) from DetailTransferStockMP d  where  d.matierePremier.categorieMp.id=:categorieMp and d.matierePremier.id=:mp and   d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier order by d.matierePremier.categorieMp.subCategorieMp.id");
			//query.setParameter("Cheque", "Chèque");
			// query.setParameter("Virement", "Virement");
			 query.setParameter("dateDebut", dateDebut);
			 query.setParameter("dateFin", dateFin);
			 query.setParameter("Initial", Constantes.ETAT_TRANSFER_STOCK_INITIAL);
			 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
		     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
		     query.setParameter("CHARGE_SUPP",  Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
		     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
		     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
		    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
		    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
		    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
			  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
			  query.setParameter("PERTE", Constantes.TYPE_PERTE);
			  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			  query.setParameter("RETOURFOURNISSEURANNULER", Constantes.SOUS_TYPE_SORTIE_RETOUR_FOURNISSEUR_ANNULER);
		  query.setParameter("categorieMp",categorieMp.getId());
		  query.setParameter("mp",mp.getId());
		  query.setParameter("RECEPTION_ENATTENT", Constantes.ETAT_RECEPTION_ENATTENT);
			
		}else if(dateDebut!=null  && subCategorieMp!=null && categorieMp==null && mp!=null)
		{
			
			query= session.createQuery("select d.matierePremier ,sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=1  then d.quantite else 0 end),sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=1  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:AJOUT  and d.magasinDestination.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:ENTRE and d.magasinDestination.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinDestination.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:CHARGE_SUPP and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=1 and d.transferStockMP.soustype.id is null) then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:RETOUR and d.magasinDestination.id=1 ) then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=1  and d.transferStockMP.soustype.id <> null) then d.quantite else 0 end)  , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=1 then d.QuantiteRetour else 0 end)  , sum( case when d.transferStockMP.statut=:FABRIQUE and d.magasinDestination.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=1 and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when d.transferStockMP.statut=:SORTIE_PF and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=1 and d.magasinDestination.id=18 then d.quantite else 0 end) ,sum( case when d.transferStockMP.statut=:PERTE and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOUR and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=1 then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=12 and d.magasinSouce.depot.id <> d.magasinDestination.depot.id and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end), sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=12  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:AJOUT  and d.magasinDestination.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:ENTRE and d.magasinDestination.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinDestination.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:CHARGE_SUPP and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=12 and d.transferStockMP.soustype.id is null) then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:RETOUR and d.magasinDestination.id=12 ) then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=12  and d.transferStockMP.soustype.id <> null) then d.quantite else 0 end)  , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=12 then d.QuantiteRetour else 0 end)  , sum( case when d.transferStockMP.statut=:FABRIQUE and d.magasinDestination.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=12 and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when d.transferStockMP.statut=:SORTIE_PF and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=12 and d.magasinDestination.id=18 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:PERTE and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOUR and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=12 then d.quantite else 0 end) ,sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=16 and d.magasinSouce.depot.id <> d.magasinDestination.depot.id and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end),sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=16  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:AJOUT  and d.magasinDestination.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:ENTRE and d.magasinDestination.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinDestination.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinSouce.id=16 then d.quantite else 0 end) ,sum( case when d.transferStockMP.statut=:CHARGE_SUPP and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=16 and d.transferStockMP.soustype.id is null) then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:RETOUR and d.magasinDestination.id=16 ) then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=16  and d.transferStockMP.soustype.id <> null) then d.quantite else 0 end)  , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=16 then d.QuantiteRetour else 0 end)  , sum( case when d.transferStockMP.statut=:FABRIQUE and d.magasinDestination.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=16 and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when d.transferStockMP.statut=:SORTIE_PF and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=16 and d.magasinDestination.id=18 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:PERTE and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOUR and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=16 then d.quantite else 0 end)  ,sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=20 and d.magasinSouce.depot.id <> d.magasinDestination.depot.id and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end), sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=20  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:AJOUT  and d.magasinDestination.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:ENTRE and d.magasinDestination.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinDestination.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:CHARGE_SUPP and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=20 and d.transferStockMP.soustype.id is null) then d.quantite else 0 end) ,sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinSouce.id=20 then d.quantite else 0 end) ,sum( case when (d.transferStockMP.statut=:RETOUR and d.magasinDestination.id=20 ) then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=20  and d.transferStockMP.soustype.id <> null) then d.quantite else 0 end)  ,sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=20 then d.QuantiteRetour else 0 end)  , sum( case when d.transferStockMP.statut=:FABRIQUE and d.magasinDestination.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=20 and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when d.transferStockMP.statut=:SORTIE_PF and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=20 and d.magasinDestination.id=18 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:PERTE and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOUR and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=20 then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:RECEPTION_ENATTENT and d.magasinDestination.id=1 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:RECEPTION_ENATTENT and d.magasinDestination.id=12 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:RECEPTION_ENATTENT and d.magasinDestination.id=16 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:RECEPTION_ENATTENT and d.magasinDestination.id=20 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end)  from DetailTransferStockMP d  where  d.matierePremier.categorieMp.subCategorieMp.id=:subcategorieMp and d.matierePremier.id=:mp and   d.transferStockMP.dateTransfer between :dateDebut and :dateFin group by d.matierePremier order by d.matierePremier.categorieMp.subCategorieMp.id");
			//query.setParameter("Cheque", "Chèque");
			// query.setParameter("Virement", "Virement");
			 query.setParameter("dateDebut", dateDebut);
			 query.setParameter("dateFin", dateFin);
			 query.setParameter("Initial", Constantes.ETAT_TRANSFER_STOCK_INITIAL);
			 query.setParameter("AJOUT", Constantes.ETAT_TRANSFER_STOCK_AJOUT);
		     query.setParameter("charge", Constantes.ETAT_TRANSFER_STOCK_CHARGE);
		     query.setParameter("CHARGE_SUPP",  Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
		     query.setParameter("SORTIE",  Constantes.ETAT_TRANSFER_STOCK_SORTIE); 
		     query.setParameter("SORTIEENATTENT", Constantes.ETAT_SORTIE_ENATTENT); 
		    query.setParameter("ENTRE",Constantes.ETAT_TRANSFER_STOCK_ENTRE); 
		    query.setParameter("RETOUR",Constantes.ETAT_TRANSFER_RETOUR); 
		    query.setParameter("SORTIE_PF", Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF);
			  query.setParameter("TRANSFER", Constantes.ETAT_TRANSFER_STOCK_TRANSFERE);
			  query.setParameter("PERTE", Constantes.TYPE_PERTE);
			  query.setParameter("FABRIQUE", Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
			  query.setParameter("RETOURFOURNISSEURANNULER", Constantes.SOUS_TYPE_SORTIE_RETOUR_FOURNISSEUR_ANNULER);
		  query.setParameter("subcategorieMp",subCategorieMp.getId());
		  query.setParameter("mp",mp.getId());
		  query.setParameter("RECEPTION_ENATTENT", Constantes.ETAT_RECEPTION_ENATTENT);
			
		}
		
		
		 
		return query.list();

	}	
	
	*/
	
	
	
	
	  public List<Object[]> listeSituationEnVrac(final Date dateDebut, final Date dateFin, final SubCategorieMp subCategorieMp, final CategorieMp categorieMp, final MatierePremier mp) {
	        Query query = null;
	        if (dateDebut != null && subCategorieMp == null && categorieMp == null && mp == null) {
	            query = this.session.createQuery("select d.matierePremier ,sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=1 and d.magasinDestination.id=18 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end),sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=1  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:AJOUT  and d.magasinDestination.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:ENTRE and d.magasinDestination.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinDestination.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:CHARGE_SUPP and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=1 and d.transferStockMP.soustype.id is null) then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:RETOUR and d.magasinDestination.id=1 ) then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=1  and d.transferStockMP.soustype.id <> null) then d.quantite else 0 end)  , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=1 then d.QuantiteRetour else 0 end)  , sum( case when d.transferStockMP.statut=:FABRIQUE and d.magasinDestination.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=1 and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when d.transferStockMP.statut=:SORTIE_PF and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=1 and d.magasinDestination.id<>18   then d.quantite else 0 end) ,sum( case when d.transferStockMP.statut=:PERTE and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOUR and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=1 then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=12 and d.magasinDestination.id=18 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end), sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=12  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:AJOUT  and d.magasinDestination.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:ENTRE and d.magasinDestination.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinDestination.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:CHARGE_SUPP and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=12 and d.transferStockMP.soustype.id is null) then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:RETOUR and d.magasinDestination.id=12 ) then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=12  and d.transferStockMP.soustype.id <> null) then d.quantite else 0 end)  , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=12 then d.QuantiteRetour else 0 end)  , sum( case when d.transferStockMP.statut=:FABRIQUE and d.magasinDestination.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=12 and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when d.transferStockMP.statut=:SORTIE_PF and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=12 and d.magasinDestination.id<>18  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:PERTE and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOUR and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=12 then d.quantite else 0 end) ,sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=16 and d.magasinDestination.id=18 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end),sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=16  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:AJOUT  and d.magasinDestination.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:ENTRE and d.magasinDestination.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinDestination.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinSouce.id=16 then d.quantite else 0 end) ,sum( case when d.transferStockMP.statut=:CHARGE_SUPP and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=16 and d.transferStockMP.soustype.id is null) then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:RETOUR and d.magasinDestination.id=16 ) then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=16  and d.transferStockMP.soustype.id <> null) then d.quantite else 0 end)  , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=16 then d.QuantiteRetour else 0 end)  , sum( case when d.transferStockMP.statut=:FABRIQUE and d.magasinDestination.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=16 and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when d.transferStockMP.statut=:SORTIE_PF and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=16 and d.magasinDestination.id<>18  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:PERTE and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOUR and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=16 then d.quantite else 0 end)  ,sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=20 and d.magasinDestination.id=18 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end), sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=20  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:AJOUT  and d.magasinDestination.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:ENTRE and d.magasinDestination.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinDestination.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:CHARGE_SUPP and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=20 and d.transferStockMP.soustype.id is null) then d.quantite else 0 end) ,sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinSouce.id=20 then d.quantite else 0 end) ,sum( case when (d.transferStockMP.statut=:RETOUR and d.magasinDestination.id=20 ) then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=20  and d.transferStockMP.soustype.id <> null) then d.quantite else 0 end)  ,sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=20 then d.QuantiteRetour else 0 end)  , sum( case when d.transferStockMP.statut=:FABRIQUE and d.magasinDestination.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=20 and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when d.transferStockMP.statut=:SORTIE_PF and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=20 and d.magasinDestination.id<>18 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:PERTE and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOUR and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=20 then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:RECEPTION_ENATTENT and d.magasinDestination.id=1 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:RECEPTION_ENATTENT and d.magasinDestination.id=12 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end)sum( case when d.transferStockMP.statut=:RECEPTION_ENATTENT and d.magasinDestination.id=16 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:RECEPTION_ENATTENT and d.magasinDestination.id=20 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end) from DetailTransferStockMP d  where  d.transferStockMP.dateTransfer between :dateDebut and :dateFin group by d.matierePremier order by d.matierePremier.categorieMp.subCategorieMp.id ");
	            query.setParameter("dateDebut", (Object)dateDebut);
	            query.setParameter("dateFin", (Object)dateFin);
	            query.setParameter("Initial", (Object)"INITIAL");
	            query.setParameter("AJOUT", (Object)"AJOUT");
	            query.setParameter("charge", (Object)"CHARGE");
	            query.setParameter("CHARGE_SUPP", (Object)"CHARGE_SUPP");
	            query.setParameter("SORTIE", (Object)"SORTIE");
	            query.setParameter("SORTIEENATTENT", (Object)"SORTIE_ENATTENT");
	            query.setParameter("ENTRE", (Object)"ENTRE");
	            query.setParameter("RETOUR", (Object)"RETOUR");
	            query.setParameter("SORTIE_PF", (Object)"SORTIE_PF");
	            query.setParameter("TRANSFER", (Object)"TRANSFER");
	            query.setParameter("PERTE", (Object)"PERTE");
	            query.setParameter("FABRIQUE", (Object)"FABRIQUE");
	            query.setParameter("RETOURFOURNISSEURANNULER", (Object)"RETOUR_FOURNISSEUR_ANNULER");
	            query.setParameter("RECEPTION_ENATTENT", (Object)"RECEPTION_ENATTENT");
	        }
	        else if (dateDebut != null && subCategorieMp != null && categorieMp == null && mp == null) {
	            query = this.session.createQuery("select d.matierePremier ,sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=1 and d.magasinDestination.id=18 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end),sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=1  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:AJOUT  and d.magasinDestination.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:ENTRE and d.magasinDestination.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinDestination.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:CHARGE_SUPP and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=1 and d.transferStockMP.soustype.id is null) then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:RETOUR and d.magasinDestination.id=1 ) then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=1  and d.transferStockMP.soustype.id <> null) then d.quantite else 0 end)  , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=1 then d.QuantiteRetour else 0 end)  , sum( case when d.transferStockMP.statut=:FABRIQUE and d.magasinDestination.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=1 and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when d.transferStockMP.statut=:SORTIE_PF and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=1 and d.magasinDestination.id<>18 then d.quantite else 0 end) ,sum( case when d.transferStockMP.statut=:PERTE and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOUR and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=1 then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=12 and d.magasinDestination.id=18 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end), sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=12  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:AJOUT  and d.magasinDestination.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:ENTRE and d.magasinDestination.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinDestination.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:CHARGE_SUPP and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=12 and d.transferStockMP.soustype.id is null) then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:RETOUR and d.magasinDestination.id=12 ) then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=12  and d.transferStockMP.soustype.id <> null) then d.quantite else 0 end)  , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=12 then d.QuantiteRetour else 0 end)  , sum( case when d.transferStockMP.statut=:FABRIQUE and d.magasinDestination.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=12 and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when d.transferStockMP.statut=:SORTIE_PF and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=12 and d.magasinDestination.id<>18  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:PERTE and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOUR and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=12 then d.quantite else 0 end) ,sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=16 and d.magasinDestination.id=18 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end),sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=16  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:AJOUT  and d.magasinDestination.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:ENTRE and d.magasinDestination.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinDestination.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinSouce.id=16 then d.quantite else 0 end) ,sum( case when d.transferStockMP.statut=:CHARGE_SUPP and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=16 and d.transferStockMP.soustype.id is null) then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:RETOUR and d.magasinDestination.id=16 ) then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=16  and d.transferStockMP.soustype.id <> null) then d.quantite else 0 end)  , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=16 then d.QuantiteRetour else 0 end)  , sum( case when d.transferStockMP.statut=:FABRIQUE and d.magasinDestination.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=16 and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when d.transferStockMP.statut=:SORTIE_PF and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=16 and d.magasinDestination.id<>18  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:PERTE and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOUR and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=16 then d.quantite else 0 end)  ,sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=20 and d.magasinDestination.id=18 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end), sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=20  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:AJOUT  and d.magasinDestination.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:ENTRE and d.magasinDestination.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinDestination.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:CHARGE_SUPP and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=20 and d.transferStockMP.soustype.id is null) then d.quantite else 0 end) ,sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinSouce.id=20 then d.quantite else 0 end) ,sum( case when (d.transferStockMP.statut=:RETOUR and d.magasinDestination.id=20 ) then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=20  and d.transferStockMP.soustype.id <> null) then d.quantite else 0 end)  ,sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=20 then d.QuantiteRetour else 0 end)  , sum( case when d.transferStockMP.statut=:FABRIQUE and d.magasinDestination.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=20 and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when d.transferStockMP.statut=:SORTIE_PF and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=20 and d.magasinDestination.id<>18 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:PERTE and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOUR and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=20 then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:RECEPTION_ENATTENT and d.magasinDestination.id=1 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:RECEPTION_ENATTENT and d.magasinDestination.id=12 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:RECEPTION_ENATTENT and d.magasinDestination.id=16 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:RECEPTION_ENATTENT and d.magasinDestination.id=20 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end)  from DetailTransferStockMP d  where  d.matierePremier.categorieMp.subCategorieMp.id=:subCategorieMp and   d.transferStockMP.dateTransfer between :dateDebut and :dateFin group by d.matierePremier order by d.matierePremier.categorieMp.subCategorieMp.id");
	            query.setParameter("dateDebut", (Object)dateDebut);
	            query.setParameter("dateFin", (Object)dateFin);
	            query.setParameter("Initial", (Object)"INITIAL");
	            query.setParameter("AJOUT", (Object)"AJOUT");
	            query.setParameter("charge", (Object)"CHARGE");
	            query.setParameter("CHARGE_SUPP", (Object)"CHARGE_SUPP");
	            query.setParameter("SORTIE", (Object)"SORTIE");
	            query.setParameter("SORTIEENATTENT", (Object)"SORTIE_ENATTENT");
	            query.setParameter("ENTRE", (Object)"ENTRE");
	            query.setParameter("RETOUR", (Object)"RETOUR");
	            query.setParameter("SORTIE_PF", (Object)"SORTIE_PF");
	            query.setParameter("TRANSFER", (Object)"TRANSFER");
	            query.setParameter("PERTE", (Object)"PERTE");
	            query.setParameter("FABRIQUE", (Object)"FABRIQUE");
	            query.setParameter("RETOURFOURNISSEURANNULER", (Object)"RETOUR_FOURNISSEUR_ANNULER");
	            query.setParameter("subCategorieMp", (Object)subCategorieMp.getId());
	            query.setParameter("RECEPTION_ENATTENT", (Object)"RECEPTION_ENATTENT");
	        }
	        else if (dateDebut != null && subCategorieMp != null && categorieMp != null && mp == null) {
	            query = this.session.createQuery("select d.matierePremier ,sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=1 and d.magasinDestination.id=18 and  d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end),sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=1  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:AJOUT  and d.magasinDestination.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:ENTRE and d.magasinDestination.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinDestination.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:CHARGE_SUPP and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=1 and d.transferStockMP.soustype.id is null) then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:RETOUR and d.magasinDestination.id=1 ) then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=1  and d.transferStockMP.soustype.id <> null) then d.quantite else 0 end)  , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=1 then d.QuantiteRetour else 0 end)  , sum( case when d.transferStockMP.statut=:FABRIQUE and d.magasinDestination.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=1 and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when d.transferStockMP.statut=:SORTIE_PF and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=1 and d.magasinDestination.id<>18 then d.quantite else 0 end) ,sum( case when d.transferStockMP.statut=:PERTE and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOUR and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=1 then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=12 and d.magasinDestination.id=18 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end), sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=12  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:AJOUT  and d.magasinDestination.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:ENTRE and d.magasinDestination.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinDestination.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:CHARGE_SUPP and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=12 and d.transferStockMP.soustype.id is null) then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:RETOUR and d.magasinDestination.id=12 ) then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=12  and d.transferStockMP.soustype.id <> null) then d.quantite else 0 end)  , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=12 then d.QuantiteRetour else 0 end)  , sum( case when d.transferStockMP.statut=:FABRIQUE and d.magasinDestination.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=12 and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when d.transferStockMP.statut=:SORTIE_PF and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=12 and d.magasinDestination.id<>18  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:PERTE and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOUR and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=12 then d.quantite else 0 end) ,sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=16 and d.magasinDestination.id=18 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end),sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=16  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:AJOUT  and d.magasinDestination.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:ENTRE and d.magasinDestination.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinDestination.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinSouce.id=16 then d.quantite else 0 end) ,sum( case when d.transferStockMP.statut=:CHARGE_SUPP and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=16 and d.transferStockMP.soustype.id is null) then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:RETOUR and d.magasinDestination.id=16 ) then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=16  and d.transferStockMP.soustype.id <> null) then d.quantite else 0 end)  , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=16 then d.QuantiteRetour else 0 end)  , sum( case when d.transferStockMP.statut=:FABRIQUE and d.magasinDestination.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=16 and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when d.transferStockMP.statut=:SORTIE_PF and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=16 and d.magasinDestination.id<>18  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:PERTE and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOUR and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=16 then d.quantite else 0 end)  ,sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=20 and d.magasinDestination.id=18 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end), sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=20  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:AJOUT  and d.magasinDestination.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:ENTRE and d.magasinDestination.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinDestination.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:CHARGE_SUPP and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=20 and d.transferStockMP.soustype.id is null) then d.quantite else 0 end) ,sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinSouce.id=20 then d.quantite else 0 end) ,sum( case when (d.transferStockMP.statut=:RETOUR and d.magasinDestination.id=20 ) then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=20  and d.transferStockMP.soustype.id <> null) then d.quantite else 0 end)  ,sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=20 then d.QuantiteRetour else 0 end)  , sum( case when d.transferStockMP.statut=:FABRIQUE and d.magasinDestination.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=20 and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when d.transferStockMP.statut=:SORTIE_PF and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=20 and d.magasinDestination.id<>18 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:PERTE and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOUR and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=20 then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:RECEPTION_ENATTENT and d.magasinDestination.id=1 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:RECEPTION_ENATTENT and d.magasinDestination.id=12 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:RECEPTION_ENATTENT and d.magasinDestination.id=16 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:RECEPTION_ENATTENT and d.magasinDestination.id=20 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end) from DetailTransferStockMP d  where  d.matierePremier.categorieMp.id=:categorieMp and   d.transferStockMP.dateTransfer between :dateDebut and :dateFin group by d.matierePremier order by d.matierePremier.categorieMp.subCategorieMp.id");
	            query.setParameter("dateDebut", (Object)dateDebut);
	            query.setParameter("dateFin", (Object)dateFin);
	            query.setParameter("Initial", (Object)"INITIAL");
	            query.setParameter("AJOUT", (Object)"AJOUT");
	            query.setParameter("charge", (Object)"CHARGE");
	            query.setParameter("CHARGE_SUPP", (Object)"CHARGE_SUPP");
	            query.setParameter("SORTIE", (Object)"SORTIE");
	            query.setParameter("SORTIEENATTENT", (Object)"SORTIE_ENATTENT");
	            query.setParameter("ENTRE", (Object)"ENTRE");
	            query.setParameter("RETOUR", (Object)"RETOUR");
	            query.setParameter("SORTIE_PF", (Object)"SORTIE_PF");
	            query.setParameter("TRANSFER", (Object)"TRANSFER");
	            query.setParameter("PERTE", (Object)"PERTE");
	            query.setParameter("FABRIQUE", (Object)"FABRIQUE");
	            query.setParameter("RETOURFOURNISSEURANNULER", (Object)"RETOUR_FOURNISSEUR_ANNULER");
	            query.setParameter("categorieMp", (Object)categorieMp.getId());
	            query.setParameter("RECEPTION_ENATTENT", (Object)"RECEPTION_ENATTENT");
	        }
	        else if (dateDebut != null && subCategorieMp != null && categorieMp != null && mp != null) {
	            query = this.session.createQuery("select d.matierePremier ,sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=1 and d.magasinDestination.id=18 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end),sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=1  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:AJOUT  and d.magasinDestination.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:ENTRE and d.magasinDestination.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinDestination.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:CHARGE_SUPP and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=1 and d.transferStockMP.soustype.id is null) then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:RETOUR and d.magasinDestination.id=1 ) then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=1  and d.transferStockMP.soustype.id <> null) then d.quantite else 0 end)  , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=1 then d.QuantiteRetour else 0 end)  , sum( case when d.transferStockMP.statut=:FABRIQUE and d.magasinDestination.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=1 and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when d.transferStockMP.statut=:SORTIE_PF and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=1 and d.magasinDestination.id<>18 then d.quantite else 0 end) ,sum( case when d.transferStockMP.statut=:PERTE and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOUR and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=1 then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=12 and d.magasinDestination.id=18 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end), sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=12  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:AJOUT  and d.magasinDestination.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:ENTRE and d.magasinDestination.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinDestination.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:CHARGE_SUPP and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=12 and d.transferStockMP.soustype.id is null) then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:RETOUR and d.magasinDestination.id=12 ) then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=12  and d.transferStockMP.soustype.id <> null) then d.quantite else 0 end)  , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=12 then d.QuantiteRetour else 0 end)  , sum( case when d.transferStockMP.statut=:FABRIQUE and d.magasinDestination.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=12 and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when d.transferStockMP.statut=:SORTIE_PF and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=12 and d.magasinDestination.id <> 18  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:PERTE and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOUR and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=12 then d.quantite else 0 end) ,sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=16 and d.magasinDestination.id=18 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end),sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=16  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:AJOUT  and d.magasinDestination.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:ENTRE and d.magasinDestination.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinDestination.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinSouce.id=16 then d.quantite else 0 end) ,sum( case when d.transferStockMP.statut=:CHARGE_SUPP and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=16 and d.transferStockMP.soustype.id is null) then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:RETOUR and d.magasinDestination.id=16 ) then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=16  and d.transferStockMP.soustype.id <> null) then d.quantite else 0 end)  , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=16 then d.QuantiteRetour else 0 end)  , sum( case when d.transferStockMP.statut=:FABRIQUE and d.magasinDestination.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=16 and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when d.transferStockMP.statut=:SORTIE_PF and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=16 and d.magasinDestination.id<>18  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:PERTE and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOUR and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=16 then d.quantite else 0 end)  ,sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=20 and d.magasinDestination.id=18 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end), sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=20  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:AJOUT  and d.magasinDestination.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:ENTRE and d.magasinDestination.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinDestination.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:CHARGE_SUPP and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=20 and d.transferStockMP.soustype.id is null) then d.quantite else 0 end) ,sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinSouce.id=20 then d.quantite else 0 end) ,sum( case when (d.transferStockMP.statut=:RETOUR and d.magasinDestination.id=20 ) then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=20  and d.transferStockMP.soustype.id <> null) then d.quantite else 0 end)  ,sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=20 then d.QuantiteRetour else 0 end)  , sum( case when d.transferStockMP.statut=:FABRIQUE and d.magasinDestination.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=20 and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when d.transferStockMP.statut=:SORTIE_PF and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=20 and d.magasinDestination.id<>18 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:PERTE and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOUR and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=20 then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:RECEPTION_ENATTENT and d.magasinDestination.id=1 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:RECEPTION_ENATTENT and d.magasinDestination.id=12 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:RECEPTION_ENATTENT and d.magasinDestination.id=16 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:RECEPTION_ENATTENT and d.magasinDestination.id=20 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end) from DetailTransferStockMP d  where  d.matierePremier.categorieMp.id=:categorieMp and d.matierePremier.id=:mp and   d.transferStockMP.dateTransfer between :dateDebut and :dateFin  group by d.matierePremier order by d.matierePremier.categorieMp.subCategorieMp.id");
	            query.setParameter("dateDebut", (Object)dateDebut);
	            query.setParameter("dateFin", (Object)dateFin);
	            query.setParameter("Initial", (Object)"INITIAL");
	            query.setParameter("AJOUT", (Object)"AJOUT");
	            query.setParameter("charge", (Object)"CHARGE");
	            query.setParameter("CHARGE_SUPP", (Object)"CHARGE_SUPP");
	            query.setParameter("SORTIE", (Object)"SORTIE");
	            query.setParameter("SORTIEENATTENT", (Object)"SORTIE_ENATTENT");
	            query.setParameter("ENTRE", (Object)"ENTRE");
	            query.setParameter("RETOUR", (Object)"RETOUR");
	            query.setParameter("SORTIE_PF", (Object)"SORTIE_PF");
	            query.setParameter("TRANSFER", (Object)"TRANSFER");
	            query.setParameter("PERTE", (Object)"PERTE");
	            query.setParameter("FABRIQUE", (Object)"FABRIQUE");
	            query.setParameter("RETOURFOURNISSEURANNULER", (Object)"RETOUR_FOURNISSEUR_ANNULER");
	            query.setParameter("categorieMp", (Object)categorieMp.getId());
	            query.setParameter("mp", (Object)mp.getId());
	            query.setParameter("RECEPTION_ENATTENT", (Object)"RECEPTION_ENATTENT");
	        }
	        else if (dateDebut != null && subCategorieMp != null && categorieMp == null && mp != null) {
	            query = this.session.createQuery("select d.matierePremier ,sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=1 and d.magasinDestination.id=18 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end),sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=1  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:AJOUT  and d.magasinDestination.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:ENTRE and d.magasinDestination.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinDestination.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:CHARGE_SUPP and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=1 and d.transferStockMP.soustype.id is null) then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:RETOUR and d.magasinDestination.id=1 ) then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=1  and d.transferStockMP.soustype.id <> null) then d.quantite else 0 end)  , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=1 then d.QuantiteRetour else 0 end)  , sum( case when d.transferStockMP.statut=:FABRIQUE and d.magasinDestination.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=1 and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when d.transferStockMP.statut=:SORTIE_PF and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=1 and d.magasinDestination.id<>18 then d.quantite else 0 end) ,sum( case when d.transferStockMP.statut=:PERTE and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOUR and d.magasinSouce.id=1 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=1 then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=12 and d.magasinDestination.id=18 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end), sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=12  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:AJOUT  and d.magasinDestination.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:ENTRE and d.magasinDestination.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinDestination.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:CHARGE_SUPP and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=12 and d.transferStockMP.soustype.id is null) then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:RETOUR and d.magasinDestination.id=12 ) then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=12  and d.transferStockMP.soustype.id <> null) then d.quantite else 0 end)  , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=12 then d.QuantiteRetour else 0 end)  , sum( case when d.transferStockMP.statut=:FABRIQUE and d.magasinDestination.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=12 and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when d.transferStockMP.statut=:SORTIE_PF and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=12 and d.magasinDestination.id<>18  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:PERTE and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOUR and d.magasinSouce.id=12 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=12 then d.quantite else 0 end) ,sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=16 and d.magasinDestination.id=18 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end),sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=16  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:AJOUT  and d.magasinDestination.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:ENTRE and d.magasinDestination.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinDestination.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinSouce.id=16 then d.quantite else 0 end) ,sum( case when d.transferStockMP.statut=:CHARGE_SUPP and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=16 and d.transferStockMP.soustype.id is null) then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:RETOUR and d.magasinDestination.id=16 ) then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=16  and d.transferStockMP.soustype.id <> null) then d.quantite else 0 end)  , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=16 then d.QuantiteRetour else 0 end)  , sum( case when d.transferStockMP.statut=:FABRIQUE and d.magasinDestination.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=16 and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when d.transferStockMP.statut=:SORTIE_PF and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=16 and d.magasinDestination.id<>18 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:PERTE and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOUR and d.magasinSouce.id=16 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=16 then d.quantite else 0 end)  ,sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=20 and d.magasinDestination.id=18 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end), sum(case when d.transferStockMP.statut=:Initial and d.magasinDestination.id=20  then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:AJOUT  and d.magasinDestination.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:ENTRE and d.magasinDestination.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinDestination.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:CHARGE_SUPP and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=20 and d.transferStockMP.soustype.id is null) then d.quantite else 0 end) ,sum( case when d.transferStockMP.statut=:TRANSFER and d.magasinSouce.id=20 then d.quantite else 0 end) ,sum( case when (d.transferStockMP.statut=:RETOUR and d.magasinDestination.id=20 ) then d.quantite else 0 end) , sum( case when (d.transferStockMP.statut=:SORTIE and d.magasinSouce.id=20  and d.transferStockMP.soustype.id <> null) then d.quantite else 0 end)  ,sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=20 then d.QuantiteRetour else 0 end)  , sum( case when d.transferStockMP.statut=:FABRIQUE and d.magasinDestination.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:charge and d.magasinDestination.id=20 and d.QuantiteExistante <> null then d.QuantiteExistante else 0 end) , sum( case when d.transferStockMP.statut=:SORTIE_PF and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:SORTIEENATTENT and d.magasinSouce.id=20 and d.magasinDestination.id<>18 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:PERTE and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOUR and d.magasinSouce.id=20 then d.quantite else 0 end) , sum( case when d.transferStockMP.statut=:RETOURFOURNISSEURANNULER and d.magasinSouce.id=20 then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:RECEPTION_ENATTENT and d.magasinDestination.id=1 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:RECEPTION_ENATTENT and d.magasinDestination.id=12 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:RECEPTION_ENATTENT and d.magasinDestination.id=16 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end),sum( case when d.transferStockMP.statut=:RECEPTION_ENATTENT and d.magasinDestination.id=20 and d.transferStockMP.dateTransfer between :dateDebut and :dateFin   then d.quantite else 0 end)  from DetailTransferStockMP d  where  d.matierePremier.categorieMp.subCategorieMp.id=:subcategorieMp and d.matierePremier.id=:mp and   d.transferStockMP.dateTransfer between :dateDebut and :dateFin group by d.matierePremier order by d.matierePremier.categorieMp.subCategorieMp.id");
	            query.setParameter("dateDebut", (Object)dateDebut);
	            query.setParameter("dateFin", (Object)dateFin);
	            query.setParameter("Initial", (Object)"INITIAL");
	            query.setParameter("AJOUT", (Object)"AJOUT");
	            query.setParameter("charge", (Object)"CHARGE");
	            query.setParameter("CHARGE_SUPP", (Object)"CHARGE_SUPP");
	            query.setParameter("SORTIE", (Object)"SORTIE");
	            query.setParameter("SORTIEENATTENT", (Object)"SORTIE_ENATTENT");
	            query.setParameter("ENTRE", (Object)"ENTRE");
	            query.setParameter("RETOUR", (Object)"RETOUR");
	            query.setParameter("SORTIE_PF", (Object)"SORTIE_PF");
	            query.setParameter("TRANSFER", (Object)"TRANSFER");
	            query.setParameter("PERTE", (Object)"PERTE");
	            query.setParameter("FABRIQUE", (Object)"FABRIQUE");
	            query.setParameter("RETOURFOURNISSEURANNULER", (Object)"RETOUR_FOURNISSEUR_ANNULER");
	            query.setParameter("subcategorieMp", (Object)subCategorieMp.getId());
	            query.setParameter("mp", (Object)mp.getId());
	            query.setParameter("RECEPTION_ENATTENT", (Object)"RECEPTION_ENATTENT");
	        }
	        return (List<Object[]>)query.list();
	    }
	
	
	
	
	// Stock Finale Magasin Dechet MP
	public List<Object[]> StockFinaleMPMagasinDechet(Date dateDebut,Date dateFin , Magasin magasin , MatierePremier mp , FournisseurMP fournisseur) {
		// TODO Auto-generated method stub
		Query query=null;
		
			// query= session.createQuery("select case when facturePF.modeReglement is null  then 'Reglement Espece' else facturePF.modeReglement end, sum(d.montantHT) , sum(d.montantTVA) , sum(d.montantTTC), sum( case when facturePF.modeReglement=:especes or facturePF.modeReglement is null then d.montantTTC else 0 end)*(0.25/100) from DetailFacturePF d where  facturePF.dateFacture between :dateDebut and :dateFin and facturePF.magasin.id=:magasin and (facturePF.modeReglement=:especes or facturePF.modeReglement=:Cheque or facturePF.modeReglement=:Virement or facturePF.modeReglement is null ) group by facturePF.modeReglement");
			if(fournisseur!=null)
			{
				
				query= session.createQuery("select d.matierePremier.id ,d.fournisseur.id, (sum( case when d.transferStockMP.statut='Initial' then d.quantite else 0 end) + sum( case when d.transferStockMP.statut='AJOUT' then d.quantite else 0 end)+ sum( case when d.transferStockMP.statut='DECHET' then d.QuantiteDechet else 0 end)+ sum( case when d.transferStockMP.statut='DECHET_FOURNISSEUR' then d.QuantiteDechet else 0 end) + sum( case when d.transferStockMP.statut='RETOUR_FOURNISSEUR_ANNULER' then d.quantite else 0 end)+ sum( case when d.transferStockMP.statut='PERTE' then d.quantite else 0 end))- (sum( case when d.transferStockMP.statut='VENTE' then d.quantite else 0 end) ) from DetailTransferStockMP d  where d.transferStockMP.dateTransfer between :dateDebut and :dateFin and (d.magasinDestination.id=:magasin or d.magasinSouce.id=:magasin) and d.matierePremier.id=:mp  and d.fournisseur.id=:fournisseur  group by d.matierePremier,d.fournisseur ");
				
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("magasin", magasin.getId());
			query.setParameter("mp", mp.getId());	
			query.setParameter("fournisseur", fournisseur.getId());
				
			}else
			{
				query= session.createQuery("select d.matierePremier.id , (sum( case when d.transferStockMP.statut='Initial' then d.quantite else 0 end) + sum( case when d.transferStockMP.statut='AJOUT' then d.quantite else 0 end)+ sum( case when d.transferStockMP.statut='DECHET' then d.QuantiteDechet else 0 end)+ sum( case when d.transferStockMP.statut='DECHET_FOURNISSEUR' then d.QuantiteDechet else 0 end) + sum( case when d.transferStockMP.statut='RETOUR_FOURNISSEUR_ANNULER' then d.quantite else 0 end)+ sum( case when d.transferStockMP.statut='PERTE' then d.quantite else 0 end))- (sum( case when d.transferStockMP.statut='VENTE' then d.quantite else 0 end) ) from DetailTransferStockMP d  where d.transferStockMP.dateTransfer between :dateDebut and :dateFin and (d.magasinDestination.id=:magasin or d.magasinSouce.id=:magasin) and d.matierePremier.id=:mp group by d.matierePremier  ");
				
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("magasin", magasin.getId());
			query.setParameter("mp", mp.getId());
			
			
			}
		
		
		
		return query.list();

	}
	
	
	// Situation Stock  Magasin Dechet MP Avec Fournisseur
	@Override
	public List<DetailTransferStockMP> SituationStockFinaleMPMagasinDechet(Date dateDebut,Date dateFin , Magasin magasin , MatierePremier mp , FournisseurMP fournisseur) {
		// TODO Auto-generated method stub
		Query query=null;
		
			// 				query= session.createQuery("select d.matierePremier ,d.fournisseur, sum( case when d.transferStockMP.statut='Initial' then d.quantite else 0 end) , sum( case when d.transferStockMP.statut='AJOUT' then d.quantite else 0 end), sum( case when d.transferStockMP.statut='DECHET' then d.QuantiteDechet else 0 end), sum( case when d.transferStockMP.statut='DECHET_FOURNISSEUR' then d.QuantiteDechet else 0 end), sum( case when d.transferStockMP.statut='PERTE' then d.quantite else 0 end), sum( case when d.transferStockMP.statut='VENTE' then d.quantite else 0 end) , sum( case when d.transferStockMP.statut='TRANSFER' then d.quantite else 0 end) , sum( case when d.transferStockMP.statut='SORTIE' and d.transferStockMP.soustype !=null then d.quantite else 0 end) from DetailTransferStockMP d  where d.transferStockMP.dateTransfer between :dateDebut and :dateFin and (d.magasinDestination.id=:magasin or d.magasinSouce.id=:magasin) and d.matierePremier.id=:mp  and d.fournisseur.id=:fournisseur  group by d.matierePremier,d.fournisseur ");

			if(fournisseur!=null && dateDebut!=null && dateFin!=null && magasin!=null && mp!=null )
			{
				
				query= session.createQuery("select d from DetailTransferStockMP d  where d.transferStockMP.dateTransfer between :dateDebut and :dateFin and  (d.magasinDestination.id=:magasin or d.magasinSouce.id=:magasin) and d.matierePremier.id=:mp  and d.fournisseur.id=:fournisseur   ");
				
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("magasin", magasin.getId());
			query.setParameter("mp", mp.getId());	
			query.setParameter("fournisseur", fournisseur.getId());
			
				
			}else 	if(fournisseur!=null && dateDebut!=null && dateFin!=null && magasin!=null && mp==null )
			{
				
				query= session.createQuery("select d from DetailTransferStockMP d  where d.transferStockMP.dateTransfer between :dateDebut and :dateFin and (d.magasinDestination.id=:magasin or d.magasinSouce.id=:magasin) and d.fournisseur.id=:fournisseur  ");
				
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("magasin", magasin.getId());
			query.setParameter("fournisseur", fournisseur.getId());
			
				
			}else 	if(fournisseur==null && dateDebut!=null && dateFin!=null && magasin!=null && mp!=null )
			{
				
				query= session.createQuery("select d from DetailTransferStockMP d  where d.transferStockMP.dateTransfer between :dateDebut and :dateFin and (d.magasinDestination.id=:magasin or d.magasinSouce.id=:magasin) and d.matierePremier.id=:mp    ");
				
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("magasin", magasin.getId());
			query.setParameter("mp", mp.getId());	
			
				
			}else 	if(fournisseur==null && dateDebut!=null && dateFin!=null && magasin!=null && mp==null )
			{
				
				query= session.createQuery("select  d  from DetailTransferStockMP d  where   d.transferStockMP.dateTransfer between :dateDebut and :dateFin and (d.magasinDestination.id=:magasin or d.magasinSouce.id=:magasin)   ");
				
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("magasin", magasin.getId());
				
			
				
			}
			
			System.out.println(query);
		return query.list();

	}
	
	
	// Situation Stock  Magasin Dechet MP Sans Fournisseur
	@Override
	public List<Object[]> SituationStockFinaleMPMagasinDechetSansFournisseur(Date dateDebut,Date dateFin , Magasin magasin , MatierePremier mp , FournisseurMP fournisseur) {
		// TODO Auto-generated method stub
		Query query=null;
		
		 	if(fournisseur==null && dateDebut!=null && dateFin!=null && magasin!=null && mp!=null )
			{
				
				query= session.createQuery("select d.matierePremier , sum( case when d.transferStockMP.statut='Initial' then d.quantite else 0 end) , sum( case when d.transferStockMP.statut='AJOUT' then d.quantite else 0 end), sum( case when d.transferStockMP.statut='DECHET' then d.QuantiteDechet else 0 end), sum( case when d.transferStockMP.statut='DECHET_FOURNISSEUR' then d.QuantiteDechet else 0 end), sum( case when d.transferStockMP.statut='PERTE' then d.quantite else 0 end), sum( case when d.transferStockMP.statut='VENTE' then d.quantite else 0 end) , sum( case when d.transferStockMP.statut='TRANSFER' then d.quantite else 0 end) , sum( case when d.transferStockMP.statut='SORTIE' and d.transferStockMP.soustype !=null then d.quantite else 0 end) from DetailTransferStockMP d  where d.transferStockMP.dateTransfer between :dateDebut and :dateFin and (d.magasinDestination.id=:magasin or d.magasinSouce.id=:magasin) and d.matierePremier.id=:mp and d.fournisseur is null   group by d.matierePremier ");
				
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("magasin", magasin.getId());
			query.setParameter("mp", mp.getId());	
			
				
			}else 	if(fournisseur==null && dateDebut!=null && dateFin!=null && magasin!=null && mp==null )
			{
				
				query= session.createQuery("select d.matierePremier , sum( case when d.transferStockMP.statut='Initial' then d.quantite else 0 end) , sum( case when d.transferStockMP.statut='AJOUT' then d.quantite else 0 end), sum( case when d.transferStockMP.statut='DECHET' then d.QuantiteDechet else 0 end), sum( case when d.transferStockMP.statut='DECHET_FOURNISSEUR' then d.QuantiteDechet else 0 end), sum( case when d.transferStockMP.statut='PERTE' then d.quantite else 0 end), sum( case when d.transferStockMP.statut='VENTE' then d.quantite else 0 end) , sum( case when d.transferStockMP.statut='TRANSFER' then d.quantite else 0 end) , sum( case when d.transferStockMP.statut='SORTIE' and d.transferStockMP.soustype !=null then d.quantite else 0 end) from DetailTransferStockMP d  where d.transferStockMP.dateTransfer between :dateDebut and :dateFin and (d.magasinDestination.id=:magasin or d.magasinSouce.id=:magasin) and d.fournisseur is null  group by d.matierePremier");
				
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("magasin", magasin.getId());
				
			
				
			}
			
			
		return query.list();

	}
	

	@Override
	public List<Object[]> MontantTotalInitialDesMP(String statut) {
		// TODO Auto-generated method stub
		Query query=null;
		
		 
				
				query= session.createQuery("select d.id,  sum( d.quantite * d.prixUnitaire ) from DetailTransferStockMP d  where d.transferStockMP.statut=:statut ");
				
			query.setParameter("statut", statut);
			 
				
			
				
			 
			
			
		return query.list();

	}
	
	@Override
	public List<Object[]> MontantTotalInitialDesEnVrac(String statut) {
		// TODO Auto-generated method stub
		Query query=null;
		
		 
				
				query= session.createQuery("select d.id,  sum( d.quantite * d.prixUnitaire ) from DetailTransferStockMP d  where d.transferStockMP.statut=:statut and d.matierePremier.categorieMp.subCategorieMp.id=1");
				
			query.setParameter("statut", statut);
			 
				
			
				
			 
			
			
		return query.list();

	}	
	
	@Override
	public List<Object[]> MontantTotalInitialDesEmballages(String statut) {
		// TODO Auto-generated method stub
		Query query=null;
		
		 
				
				query= session.createQuery("select d.id,  sum( d.quantite * d.prixUnitaire ) from DetailTransferStockMP d  where d.transferStockMP.statut=:statut and d.matierePremier.categorieMp.subCategorieMp.id<>1");
				
			query.setParameter("statut", statut);
			 
				
			
				
			 
			
			
		return query.list();

	}	
	
	
	
public void ViderSession()
{
	if(session!=null)
	{
		session.clear();
	}
}
	
public List<DetailTransferStockMP> findDetailTransferMPByCodeTransferByRequete(String requete) {
	
	// TODO Auto-generated method stub
	Query query= session.createQuery("select d from DetailTransferStockMP d "+requete+" group by d.transferStockMP.CodeTransfer order by d.transferStockMP.dateTransfer");
	
	 return query.list();
			
}


public List<DetailTransferStockMP> findDetailTransferMPByRequete(String requete) {
	
	// TODO Auto-generated method stub
	Query query= session.createQuery("select d from DetailTransferStockMP d "+requete);
	
	 return query.list();
			
}


public  List<DetailTransferStockMP> findDetailTransferMPByCodeByStatut(String codetransfert , String statut) {
	
	// TODO Auto-generated method stub
	Query query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.CodeTransfer=:codetransfert and  transferStockMP.statut=:statut");
	 query.setParameter("codetransfert",codetransfert);
	 query.setParameter("statut",statut);
	 return   query.list();
			
}

	

}

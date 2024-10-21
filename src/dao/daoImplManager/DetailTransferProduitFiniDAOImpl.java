package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.Constantes;
import util.HibernateUtil;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.entity.Articles;
import dao.entity.Client;
import dao.entity.DetailTransferProduitFini;
import dao.entity.Magasin;
import dao.entity.TransferStockPF;

public class DetailTransferProduitFiniDAOImpl implements DetailTransferProduitFiniDAO {
	Session session=HibernateUtil.openSession();

	public void add(DetailTransferProduitFini e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		
		//return p;
	}

	public DetailTransferProduitFini edit(DetailTransferProduitFini e) {
		
	session.beginTransaction();
	DetailTransferProduitFini p= (DetailTransferProduitFini)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailTransferProduitFini p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	@SuppressWarnings("unchecked")
	public List<DetailTransferProduitFini> findAll() {
		return session.createQuery("select c from DetailTransferProduitFini c").list();
		}

	public DetailTransferProduitFini findById(int id) {
		return (DetailTransferProduitFini)session.get(DetailTransferProduitFini.class, id);
		}
	

	


	@Override
	public List<DetailTransferProduitFini> findByArticle(String codeArticle) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select d from DetailTransferProduitFini d where article.codeArticle=:codeArticle");
				query.setParameter("codeArticle", codeArticle);
			
				
				return query.list();
}

	@Override
	public List<DetailTransferProduitFini> findByTransferStockPF(int idtransferStockPF) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select d from DetailTransferProduitFini d where transferStockPF.id=:idtransferStockPF");
				query.setParameter("idtransferStockPF", idtransferStockPF);
			
				
				return query.list();
}
	
	@Override
	public List<DetailTransferProduitFini> findByClient(int idclient) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select d from DetailTransferProduitFini d where client.id=:idclient");
				query.setParameter("idclient", idclient);
			
				
				return query.list();
}
	
	

	
	@Override
	public List<DetailTransferProduitFini> ListTransferStockPFEntreDeuxDatesBYArticle(Date dateDebut , Date dateFin , Articles article) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(dateDebut!=null && dateFin!=null && article!=null)
		{
			 query= session.createQuery("select d from DetailTransferProduitFini d where dateTransfer between :dateDebut and :dateFin and article.id =:article");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("article", article.getId());
			
		}else if(dateDebut!=null && dateFin!=null && article==null)
		{
			 query= session.createQuery("select d from DetailTransferProduitFini d where dateTransfer between :dateDebut and :dateFin");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				
			
		}else if(dateDebut==null && dateFin==null && article!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferProduitFini d where  article.id =:article");
			
				query.setParameter("article", article.getId());
			
		}else if(dateDebut!=null && dateFin==null && article!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferProduitFini d where dateTransfer =:dateDebut and article.id =:article");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("article", article.getId());
				
		}else if(dateDebut!=null && dateFin==null && article==null)
		{
			
			 query= session.createQuery("select d from DetailTransferProduitFini d where dateTransfer =:dateDebut");
				query.setParameter("dateDebut", dateDebut);
			
			
		}
				
			
				
				return query.list();
}
	
	
	// afficher list transfer stock produit fini contient les article nn redoublé ( groupe by article)
	@Override
	public List<DetailTransferProduitFini> ListTransferStockPFEntreDeuxDatesBYArticleDistinct(Date dateDebut , Date dateFin , Articles article) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(dateDebut!=null && dateFin!=null && article!=null)
		{
			 query= session.createQuery("select d from DetailTransferProduitFini d where dateTransfer between :dateDebut and :dateFin and article.id =:article group by article,dateTransfer");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("article", article.getId());
			
		}else if(dateDebut!=null && dateFin!=null && article==null)
		{
			 query= session.createQuery("select d from DetailTransferProduitFini d where dateTransfer between :dateDebut and :dateFin group by article,dateTransfer");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				
			
		}else if(dateDebut==null && dateFin==null && article!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferProduitFini d where  article.id =:article group by article,dateTransfer");
			
				query.setParameter("article", article.getId());
			
		}else if(dateDebut!=null && dateFin==null && article!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferProduitFini d where dateTransfer =:dateDebut and article.id =:article group by article,dateTransfer");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("article", article.getId());
				
		}else if(dateDebut!=null && dateFin==null && article==null)
		{
			
			 query= session.createQuery("select d from DetailTransferProduitFini d where dateTransfer =:dateDebut group by article,dateTransfer");
				query.setParameter("dateDebut", dateDebut);
			
			
		}
				
			
				
				return query.list();
}
	

	
	@Override
	public List<DetailTransferProduitFini> findBytypetransfer(String type,Magasin magasin) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select d from DetailTransferProduitFini d where typeTransfer=:type and magasinDestination.id=:magasin");
				query.setParameter("type", type);
				query.setParameter("magasin", magasin.getId());
				
				return query.list();
}
	
	
	public List<DetailTransferProduitFini> findAllTransferStockPFOrderByDateTransfer(Magasin magasin , Articles articles) {
		
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select d from DetailTransferProduitFini d where magasinDestination.id=:magasin and article.id=:articles order by transferStockPF.dateTransfer");
		query.setParameter("magasin", magasin.getId());
		query.setParameter("articles", articles.getId());
		return query.list();
				
}
	
	public List<DetailTransferProduitFini> findAllTransferStockPFOrderByDateTransferSortie(Magasin magasin , Articles articles) {
		
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select d from DetailTransferProduitFini d where magasinSouce.id=:magasin and article.id=:articles order by transferStockPF.dateTransfer");
		query.setParameter("magasin", magasin.getId());
		query.setParameter("articles", articles.getId());
		return query.list();
				
}
	
	
	
	public List<DetailTransferProduitFini> findAllTransferStockPFGroupeByDateTransferByArticle(Magasin magasin , Articles articles) {
		
		// TODO Auto-generated method stub
		Query query=  session.createQuery("select d from DetailTransferProduitFini d where magasinDestination.id=:magasin and article.id=:articles group by article,transferStockPF.dateTransfer");
		query.setParameter("articles", articles.getId());
		query.setParameter("magasin", magasin.getId());
		return query.list();	
}
	
	public List<DetailTransferProduitFini> findAllTransferStockPFGroupeByDateTransferByArticleSortie(Magasin magasin , Articles articles) {
		
		// TODO Auto-generated method stub
		Query query=  session.createQuery("select d from DetailTransferProduitFini d where magasinSouce.id=:magasin and article.id=:articles  group by article,transferStockPF.dateTransfer");
		query.setParameter("articles", articles.getId());
		query.setParameter("magasin", magasin.getId());
		return query.list();	
}
	
	public List<DetailTransferProduitFini> findAllTransferStockPFGroupeByByArticleIdSouFamille(Magasin magasin) {
		
		// TODO Auto-generated method stub
		Query query=  session.createQuery("select d from DetailTransferProduitFini d where magasinDestination.id=:magasin or magasinSouce.id=:magasin  group by article");
		
		query.setParameter("magasin", magasin.getId());
		return query.list();	
}
	
	

	public DetailTransferProduitFini findTransferStockPFByArticleBytypetransfer(Articles article ,String type,Magasin magasin) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select d from DetailTransferProduitFini d where article.id =:article and typeTransfer=:type and magasinDestination.id=:magasin");
				query.setParameter("type", type);
				query.setParameter("article", article.getId());
				query.setParameter("magasin", magasin.getId());
				
				
				return (DetailTransferProduitFini) query.uniqueResult();
}

	
	
	
	// liste des Articles de Statut x entre deux date
	
/*	@Override
	public List<DetailTransferProduitFini> ListTransferStockPFEntreDeuxDatesBYPFStatutX(Date dateDebut , Date dateFin , Articles article,String statut,Magasin magasin) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
	 if(dateDebut!=null && dateFin!=null && article!=null)
		{
			 query= session.createQuery("select d from DetailTransferProduitFini d where  transferStockPF.dateTransfer between :dateDebut and :dateFin and article.id =:article and  transferStockPF.statut=:statut and magasinDestination.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
				 query.setParameter("article", article.getId());
		}
		
		
		else if(dateDebut!=null && dateFin!=null && article==null )
		{
			 query= session.createQuery("select d from DetailTransferProduitFini d where  transferStockPF.dateTransfer between :dateDebut and :dateFin and  transferStockPF.statut=:statut and magasinDestination.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
				
			
		}else if(dateDebut==null && dateFin==null && article!=null )
		{
			
			 query= session.createQuery("select d from DetailTransferProduitFini d where  article.id =:article and  transferStockPF.statut=:statut and magasinDestination.id=:magasin");
			
			 query.setParameter("article", article.getId());
			 query.setParameter("statut",statut);
			 query.setParameter("magasin", magasin.getId());
			
		}else if(dateDebut!=null && dateFin==null && article!=null )
		{
			
			 query= session.createQuery("select d from DetailTransferProduitFini d where  transferStockPF.dateTransfer =:dateDebut and article.id =:article and  transferStockPF.statut=:statut and magasinDestination.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("article", article.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
				
		}else if(dateDebut!=null && dateFin==null && article==null)
		{
			
			 query= session.createQuery("select d from DetailTransferProduitFini d where  transferStockPF.dateTransfer =:dateDebut and  transferStockPF.statut=:statut and magasinDestination.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
		}
				
			
				
				return query.list();
}*/
	
	@Override
	public List<DetailTransferProduitFini> ListTransferStockPFEntreDeuxDatesBYPFStatutX(Date dateDebut , Date dateFin , Articles article,String statut,Magasin magasin, Client client) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		 if(dateDebut!=null && dateFin!=null )
		{
			 if(statut.equals(Constantes.ETAT_TRANSFER_STOCK_SORTIE))
			 {
				 
				 if(client!=null)
				 {
					 
					 if(article==null)
					 {
						 query= session.createQuery("select d from DetailTransferProduitFini d where transferStockPF.dateTransfer between :dateDebut and :dateFin and  transferStockPF.statut=:statut and magasinSouce.id=:magasin and client.id=:client");

						 query.setParameter("client", client.getId());
					 }else
					 {
						 query= session.createQuery("select d from DetailTransferProduitFini d where transferStockPF.dateTransfer between :dateDebut and :dateFin and  transferStockPF.statut=:statut and magasinSouce.id=:magasin and client.id=:client and article.id=:article ");

						 query.setParameter("client", client.getId());
						 query.setParameter("article", article.getId());
					 }
					

					 
				 }else
				 {
					 
					 if(article==null)
					 {
						 query= session.createQuery("select d from DetailTransferProduitFini d where transferStockPF.dateTransfer between :dateDebut and :dateFin and  transferStockPF.statut=:statut and magasinSouce.id=:magasin");

					 }else
					 {
						 query= session.createQuery("select d from DetailTransferProduitFini d where transferStockPF.dateTransfer between :dateDebut and :dateFin and  transferStockPF.statut=:statut and magasinSouce.id=:magasin and article.id=:article");
						 query.setParameter("article", article.getId());
						 
					 }
					 
 
				 }

			 }else
			 {
				 if(article==null)
				 {
					 query= session.createQuery("select d from DetailTransferProduitFini d where transferStockPF.dateTransfer between :dateDebut and :dateFin and  transferStockPF.statut=:statut and magasinDestination.id=:magasin");
 
				 }else
				 {
					 query= session.createQuery("select d from DetailTransferProduitFini d where transferStockPF.dateTransfer between :dateDebut and :dateFin and  transferStockPF.statut=:statut and magasinDestination.id=:magasin and article.id=:article");
					 query.setParameter("article", article.getId());
					 
				 }
 
				
				 
 
			 }
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
				
				
		} else if(dateDebut!=null && dateFin==null)
		{
			if(statut.equals(Constantes.ETAT_TRANSFER_STOCK_SORTIE))
			 {
				
				
				 if(client!=null)
				 {
					 if(article==null)
					 {
						 query= session.createQuery("select d from DetailTransferProduitFini d where  transferStockPF.dateTransfer =:dateDebut and  transferStockPF.statut=:statut and magasinSouce.id=:magasin and client.id=:client");
						 query.setParameter("client", client.getId());
						 
					 }else
					 {
						 query= session.createQuery("select d from DetailTransferProduitFini d where  transferStockPF.dateTransfer =:dateDebut and  transferStockPF.statut=:statut and magasinSouce.id=:magasin and client.id=:client and article.id=:article");
						 query.setParameter("client", client.getId());
						 query.setParameter("article", article.getId());
						 
						 
					 }
					 
				 }else
				 {
					 if(article==null)
					 {
						 query= session.createQuery("select d from DetailTransferProduitFini d where  transferStockPF.dateTransfer =:dateDebut and  transferStockPF.statut=:statut and magasinSouce.id=:magasin");
 
					 }else
					 {
						 query= session.createQuery("select d from DetailTransferProduitFini d where  transferStockPF.dateTransfer =:dateDebut and  transferStockPF.statut=:statut and magasinSouce.id=:magasin and article.id=:article");
						 query.setParameter("article", article.getId());
						 
					 }

				 }

			
			 
			 
			 }else
			 {
				 if(article==null)
				 {
					 
					 query= session.createQuery("select d from DetailTransferProduitFini d where  transferStockPF.dateTransfer =:dateDebut and  transferStockPF.statut=:statut and magasinDestination.id=:magasin");
 
				 }else
				 {
					 query= session.createQuery("select d from DetailTransferProduitFini d where  transferStockPF.dateTransfer =:dateDebut and  transferStockPF.statut=:statut and magasinDestination.id=:magasin and article.id=:article");
					 query.setParameter("article", article.getId());
					 
					 
				 }

				
 
			 }
				query.setParameter("dateDebut", dateDebut);
				 query.setParameter("statut",statut);
				
				 query.setParameter("magasin", magasin.getId());
				
		}
		
				return query.list();
}
	
	
	//list des Articles de Statut X et entre deux date distinct
	
	@Override
	public List<DetailTransferProduitFini> ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(Date dateDebut , Date dateFin , Articles article,String statut,Magasin magasin, Client client ) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
	if(dateDebut!=null && dateFin!=null )
		{
		if(statut.equals(Constantes.ETAT_TRANSFER_STOCK_SORTIE))
		{
			if(client!=null)
			{
				 if(article==null)
				 {
					 query= session.createQuery("select d from DetailTransferProduitFini d where transferStockPF.statut=:statut and  transferStockPF.dateTransfer between :dateDebut and :dateFin and magasinSouce.id=:magasin and client.id=:client group by article");
					 query.setParameter("client", client.getId());
					 
				 }else
				 {
					 query= session.createQuery("select d from DetailTransferProduitFini d where transferStockPF.statut=:statut and  transferStockPF.dateTransfer between :dateDebut and :dateFin and magasinSouce.id=:magasin and client.id=:client and article.id=:article group by article");
					 query.setParameter("client", client.getId());
					 query.setParameter("article", article.getId());
				 }
				
			}else
			{
				 if(article==null)
				 {
					 query= session.createQuery("select d from DetailTransferProduitFini d where transferStockPF.statut=:statut and  transferStockPF.dateTransfer between :dateDebut and :dateFin and magasinSouce.id=:magasin group by article");

				 }else
				 {
					 query= session.createQuery("select d from DetailTransferProduitFini d where transferStockPF.statut=:statut and  transferStockPF.dateTransfer between :dateDebut and :dateFin and magasinSouce.id=:magasin and article.id=:article group by article");
					 query.setParameter("article", article.getId());
				 }

			}
			

		}else
		{
			
			 if(article==null)
			 {
				 query= session.createQuery("select d from DetailTransferProduitFini d where transferStockPF.statut=:statut and  transferStockPF.dateTransfer between :dateDebut and :dateFin and magasinDestination.id=:magasin group by article");
 
				 
			 }else
			 {
				 query= session.createQuery("select d from DetailTransferProduitFini d where transferStockPF.statut=:statut and  transferStockPF.dateTransfer between :dateDebut and :dateFin and magasinDestination.id=:magasin and article.id=:article group by article");
				 query.setParameter("article", article.getId());
			 }

			
			
	
		}
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
			
		}else if(dateDebut!=null && dateFin==null )
		{
			if(statut.equals(Constantes.ETAT_TRANSFER_STOCK_SORTIE))
			{
				if(client!=null)
				{
					 if(article==null)
					 {
						 
						 query= session.createQuery("select d from DetailTransferProduitFini d where transferStockPF.dateTransfer =:dateDebut and  transferStockPF.statut=:statut and magasinSouce.id=:magasin and client.id=:client group by article");
						 query.setParameter("client", client.getId()); 
					 }else
					 {
						 query= session.createQuery("select d from DetailTransferProduitFini d where transferStockPF.dateTransfer =:dateDebut and  transferStockPF.statut=:statut and magasinSouce.id=:magasin and client.id=:client and article.id=:article group by article");
						 query.setParameter("client", client.getId());
						 query.setParameter("article", article.getId());
					 }
				
					 
				}else
				{
					 if(article==null)
					 {
						 query= session.createQuery("select d from DetailTransferProduitFini d where transferStockPF.dateTransfer =:dateDebut and  transferStockPF.statut=:statut and magasinSouce.id=:magasin group by article");
 
					 }else
					 {
						 query= session.createQuery("select d from DetailTransferProduitFini d where transferStockPF.dateTransfer =:dateDebut and  transferStockPF.statut=:statut and magasinSouce.id=:magasin and article.id=:article group by article");
						 query.setParameter("article", article.getId());
					 }

				}

				
			}else
				{
				
				 if(article==null)
				 {
					
					 query= session.createQuery("select d from DetailTransferProduitFini d where transferStockPF.dateTransfer =:dateDebut and  transferStockPF.statut=:statut and magasinDestination.id=:magasin group by article");
 
					 
				 }else
				 {
					 query= session.createQuery("select d from DetailTransferProduitFini d where transferStockPF.dateTransfer =:dateDebut and  transferStockPF.statut=:statut and magasinDestination.id=:magasin and article.id=:article group by article");
					 query.setParameter("article", article.getId());
					 
				 }

				

				}
				
				query.setParameter("dateDebut", dateDebut);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
			
		}
				
			
				
				return query.list();
}
	
/*	@Override
	public List<DetailTransferProduitFini> ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(Date dateDebut , Date dateFin , Articles article,String statut,Magasin magasin) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(dateDebut!=null && dateFin!=null && article!=null)
		{
			 query= session.createQuery("select d from DetailTransferProduitFini d where transferStockPF.dateTransfer between :dateDebut and :dateFin and article.id =:article and magasinDestination.id=:magasin and  transferStockPF.statut=:statut group by article,sousFamille");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("article", article.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
				
		}else if(dateDebut!=null && dateFin!=null && article==null)
		{
			 query= session.createQuery("select d from DetailTransferProduitFini d where transferStockPF.statut=:statut and  transferStockPF.dateTransfer between :dateDebut and :dateFin and magasinDestination.id=:magasin group by article,sousFamille");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
			
		}
		else if(dateDebut==null && dateFin==null && article!=null )
		{
			
			 query= session.createQuery("select d from DetailTransferProduitFini d where  article.id=:article and  transferStockPF.statut=:statut  and magasinDestination.id=:magasin group by article,sousFamille");
			
			 query.setParameter("article", article.getId());
			 query.setParameter("statut",statut);
			 query.setParameter("magasin", magasin.getId());
			
		}
		else if(dateDebut==null && dateFin==null && article==null )
		{
			
			 query= session.createQuery("select d from DetailTransferProduitFini d where transferStockPF.statut=:statut  and magasinDestination.id=:magasin group by article,sousFamille");
			
			 
			 query.setParameter("statut",statut);
			 query.setParameter("magasin", magasin.getId());
			
		}
		else if(dateDebut!=null && dateFin==null && article!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferProduitFini d where transferStockPF.dateTransfer =:dateDebut and article.id=:article and  transferStockPF.statut=:statut and magasinDestination.id=:magasin group by article,sousFamille");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("article", article.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
				
		}else if(dateDebut!=null && dateFin==null && article==null)
		{
			
			 query= session.createQuery("select d from DetailTransferProduitFini d where transferStockPF.dateTransfer =:dateDebut and  transferStockPF.statut=:statut and magasinDestination.id=:magasin group by article,sousFamille");
				query.setParameter("dateDebut", dateDebut);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
			
		}
				
			
				
				return query.list();
}*/
	
	
	@Override
	public DetailTransferProduitFini findAllTransferStockPFByPFInitialiser( Articles article,String statut) {
		
		// TODO Auto-generated method stub
		Query query= session.createQuery("select d from DetailTransferProduitFini d  where article.id=:article and  transferStockPF.statut=:statut");
		query.setParameter("article", article.getId());
		 query.setParameter("statut",statut);
		 return (DetailTransferProduitFini) query.uniqueResult();
				
}
	
	@Override
	public DetailTransferProduitFini findDetailTransferStockPFByPFByTransferPF( Articles article,TransferStockPF transferPF) {
		
		// TODO Auto-generated method stub
		Query query= session.createQuery("select d from DetailTransferProduitFini d  where article.id=:article and  transferStockPF.id=:transferStockPF");
		 query.setParameter("article", article.getId());
		 query.setParameter("transferStockPF",transferPF.getId());
		 return (DetailTransferProduitFini) query.uniqueResult();
				
}
	
	public void ViderSession()
	{
		if(session!=null)
		{
			session.clear();
		}
	}

	

}

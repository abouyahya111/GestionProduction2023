package dao.daoImplManager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.Constantes;
import util.HibernateUtil;
import dao.daoManager.ProductionDAO;
import dao.entity.Articles;
import dao.entity.CoutMP;
import dao.entity.DetailProdGen;
import dao.entity.DetailProduction;
import dao.entity.DetailResponsableProd;
import dao.entity.Machine;
import dao.entity.Magasin;
import dao.entity.Production;

public class ProductionDAOImpl implements ProductionDAO {
	Session session=HibernateUtil.openSession();

	public void add(Production e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public Production edit(Production e) {
		
	session.beginTransaction();
	Production p= (Production)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		Production p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<Production> findAll() {
		return session.createQuery("select c from Production c").list();
		}

	public Production findById(int id) {
		return (Production)session.get(Production.class, id);
		}

	@Override
	public int maxIdProduction() {
		// TODO Auto-generated method stub
		int id =0;
		Query query= session.createQuery("select max(id) from Production");
		
		if(query.uniqueResult()==null)
			id=1;
		else 
			id= (int)query.uniqueResult();
		
		return id;
	}

	@Override
	public Production findByNumOF(String numOF,String codeDepot) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select p from Production p where numOF =:numOF and codeDepot=:codeDepot");
		query.setParameter("numOF", numOF);
		query.setParameter("codeDepot", codeDepot);
		
		return (Production)query.uniqueResult();
	}

	@Override
	public List<CoutMP> listeCoutMP(int idPord) {
		
		Query query= session.createQuery("select p from CoutMP p where prodcutionCM.id =:idPord");
		query.setParameter("idPord", idPord);
		
		return query.list();

	}
	
	@Override
	public List<Production> listeProductionByDateByPeriode(Date dateProd,String periode) {
		
		Query query= session.createQuery("select p from Production p where date =:dateProd and periode=:periode");
		query.setParameter("dateProd", dateProd);
		query.setParameter("periode", periode);
		
		return query.list();

	}
	
	@Override
	public List<DetailProduction> listeDetailProduction(int idPord) {
		
		Query query= session.createQuery("select p from DetailProduction p where production.id =:idPord");
		query.setParameter("idPord", idPord);
		
		return query.list();

	}
	
	@Override
	public List<DetailProdGen> listeDetailProdGen(int idPord) {
		
		Query query= session.createQuery("select p from DetailProdGen p where productionGen.id =:idPord");
		query.setParameter("idPord", idPord);
		
		return query.list();

	}
	
	@Override
	public List<DetailResponsableProd> listeDetailResponsableProd(int idPord) {
		
		Query query= session.createQuery("select p from DetailResponsableProd p where productionDRP.id =:idPord");
		query.setParameter("idPord", idPord);
		
		return query.list();

	}
	
	@Override
	public List<Production> listeProductionEntreDeuxDate(Date dateDebut,Date dateFin) {
		
		Query query= session.createQuery("select p from Production p where date >=:dateDebut and date <=:dateFin");
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		
		
		return query.list();

	}
	
	@Override
	public List<Production> listeProductionTerminerbyDepotEntreDeuxDate(Date dateDebut,Date dateFin,String statut,String depot) {
		
		Query query= session.createQuery("select p from Production p where date >=:dateDebut and date <=:dateFin and statut =:statut and code_depot=:depot");
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		query.setParameter("statut", statut);
		query.setParameter("depot", depot);
		List<Production> list=query.list();
		
		return list;

	}
	
	@Override
	public List<Production> listeProductionTerminerbyDepotEntreDeuxDateByArticle(Date dateDebut,Date dateFin,String statut,String depot,Articles articles) {
		
		Query query= session.createQuery("select p from Production p where date >=:dateDebut and date <=:dateFin and statut =:statut and code_depot=:depot and p.articles.id=:articles");
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		query.setParameter("statut", statut);
		query.setParameter("depot", depot);
		query.setParameter("articles", articles.getId());
		List<Production> list=query.list();
		
		return list;

	}
	
	
	@Override
	public Production findByNumOFStatut(String numOF,String statut) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select p from Production p where numOF =:numOF and statut=:statut");
		query.setParameter("numOF", numOF);
		query.setParameter("statut", statut);
		
		return (Production)query.uniqueResult();
	}
	
	
	@Override
	public List<Production> listeProductionGroupByArticle() {
		
		Query query= session.createQuery("select p from Production p where statut=:statut group by articles.id order by  p.articles.liblle");
		query.setParameter("statut", Constantes.ETAT_OF_TERMINER);
		
		return query.list();

	}
	
	@Override
	public List<CoutMP> listeCoutMPGroupByEnVrac() {
		
		Query query= session.createQuery("select p from CoutMP p group by p.matierePremier.id order by  p.matierePremier.id");
		
		
		return query.list();

	}
	
	
	
	@Override
	public List<CoutMP> listeCoutMPDechetManqueByProduction(int idproduction) {
		
		Query query= session.createQuery("select p from CoutMP p  where p.prodcutionCM.id= :idproduction and (p.quantDechetFournisseur>0 or p.quantiteManquante>0 or p.quantiteManquanteFrPlus>0) ");
		query.setParameter("idproduction", idproduction);
		
		return query.list();

	}
	
	
	@Override
	public List<CoutMP> listeSituationManqueEtPLus(String requete) {
		
		Query query= session.createQuery("select p from CoutMP p  "+requete +" order by p.prodcutionCM.date ,p.prodcutionCM.id ");
		
		
		return query.list();

	}
	

	public void ViderSession()
	{
		if(session!=null)
		{
			session.clear();
		}
	}
	
	
	@Override
	public List<Object[]> listeProductionParArticleParMois(int mois) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
			query= session.createQuery("select p.articles.codeArticle , p.articles.liblle, sum(p.quantiteReel) FROM Production p where MONTH(p.date) = :mois group by p.articles.liblle");
			query.setParameter("mois", mois);
		
		return query.list();

	}
	
	
	
	@Override
	public List<Object[]> listeProductionParEnvracParMois(int mois , String etat) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
			query= session.createQuery("select p.matierePremier.code , p.matierePremier.nom, sum(p.quantConsomme) FROM CoutMP p where MONTH(p.prodcutionCM.date) = :mois and p.prodcutionCM.statut =:statut group by p.matierePremier.nom");
			query.setParameter("mois", mois);
			query.setParameter("statut", etat);
		
		return query.list();

	}
	
	
	
	@Override
	public List<Production> listeProductionEtatCreer(String creer,String lancer,String depot) {
		
		Query query= session.createQuery("select p from Production p where statut =:statutcreer or statut =:statutlancer and code_depot=:depot");		
		query.setParameter("statutcreer", creer);
		query.setParameter("statutlancer", lancer);
		query.setParameter("depot", depot);
		List<Production> list=query.list();
		
		return list;

	}
	
	@Override
	public List<Production> listeProductionEtatTerminer(String terminer,String depot) {
		
		Query query= session.createQuery("select p from Production p where statut =:terminer and code_depot=:depot and p.id in (select c.prodcutionCM.id from CoutMP c  where  (c.quantDechetFournisseur>0 or c.quantiteManquante>0 or c.quantiteManquanteFrPlus>0 )  )");		
	
		query.setParameter("terminer", terminer);
		query.setParameter("depot", depot);
		List<Production> list=query.list();
		
		return list;

	}
	
	public List<Production> listeProductionEtatTerminerEmballage(String terminer,String depot) {
		
		Query query= session.createQuery("select p from Production p where statut =:terminer and code_depot=:depot and p.id in (select c.prodcutionCM.id from CoutMP c  where c.matierePremier.categorieMp.subCategorieMp!=1 and  (c.quantDechetFournisseur>0 or c.quantiteManquante>0 or c.quantiteManquanteFrPlus>0 )  )");		
	
		query.setParameter("terminer", terminer);
		query.setParameter("depot", depot);
		List<Production> list=query.list();
		
		return list;

	}
	
	public List<Production> listeProductionEtatTerminerEnVrac(String terminer,String depot) {
		
		Query query= session.createQuery("select p from Production p where statut =:terminer and code_depot=:depot and p.id in (select c.prodcutionCM.id from CoutMP c  where c.matierePremier.categorieMp.subCategorieMp=1 and  (c.quantDechetFournisseur>0 or c.quantiteManquante>0 or c.quantiteManquanteFrPlus>0 )  )");		
	
		query.setParameter("terminer", terminer);
		query.setParameter("depot", depot);
		List<Production> list=query.list();
		
		return list;

	}
	
	public int NombreProductionTerminerParDate(Date date , String etat) {
		// TODO Auto-generated method stub
		int id =0;
		Long l = null;
		Query query= session.createQuery("select count(*) from Production c where c.date =:date and c.statut =:etat");
		
		query.setParameter("date", date);
		query.setParameter("etat", etat);
		if(query.uniqueResult()==null)
			id=1;
		else 
			 l=(Long)query.uniqueResult();
			id= l.intValue();
		
		
		
		return id;
	}
	
	
	public int NombreProductionTerminerParDateParDepot(Date date , String etat,String codedepot) {
		// TODO Auto-generated method stub
		int id =0;
		Long l = null;
		Query query= session.createQuery("select count(*) from Production c where c.date =:date and c.statut =:etat and codeDepot=:codedepot");
		
		query.setParameter("date", date);
		query.setParameter("etat", etat);
		query.setParameter("codedepot", codedepot);
		if(query.uniqueResult()==null)
			id=0;
		else 
			 l=(Long)query.uniqueResult();
			id= l.intValue();
		
		
		
		return id;
	}
	
	
	@Override
	public List<Production> LesProductionTerminerParDateParDepotGroupByDate(Date date , String etat,String codedepot) {
		// TODO Auto-generated method stub
		int id =0;
		Long l = null;
		Query query= session.createQuery("select c from Production c where c.date !=:date and c.statut =:etat and codeDepot=:codedepot group by c.date");
		
		query.setParameter("date", date);
		query.setParameter("etat", etat);
		query.setParameter("codedepot", codedepot);
		 
		
		
		
		return query.list(); 
	}
	
	
	
	@Override
	public List<Production> LesProductionLancerSansTerminerParDateParDepot(Date date , String etat,String codedepot) {
		// TODO Auto-generated method stub
		int id =0;
		Long l = null;
		Query query= session.createQuery("select c from Production c where c.date <=:date and c.statut =:etat and codeDepot=:codedepot");
		
		query.setParameter("date", date);
		query.setParameter("etat", etat);
		query.setParameter("codedepot", codedepot);
		 
		
		
		
		return query.list(); 
	}
	@Override
	public List<Production> LesProductionTerminerParAnnee(int annee , String etat) {
		// TODO Auto-generated method stub
		int id =0;
		Long l = null;
		Query query= session.createQuery("select c from Production c where year(c.date) =:annee and c.statut =:etat ");		
		query.setParameter("annee", annee);
		query.setParameter("etat", etat);
		 
		 
		
		
		
		return query.list(); 
	}
	
	@Override
	public List<Object[]> listeSituationDechetManque(String requete , String etat) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
			query= session.createQuery("select p.prodcutionCM.date, p.matierePremier.code , p.matierePremier.nom, p.codeFournisseur, sum(p.quantDechet),sum(p.quantDechetFournisseur), sum(p.quantiteManquanteFrPlus),sum(p.quantiteManquante) FROM CoutMP p "+requete+" and p.prodcutionCM.statut =:statut group by p.prodcutionCM.date , p.matierePremier.nom");
		
			query.setParameter("statut", etat);
		
		return query.list();

	}
	
	
	@Override
	public List<Object[]> listeSituationProduction(Date dateDebut,Date dateFin,String statut,String depot) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
		//	query= session.createQuery("select p.date,(select sum(dp.delaiEmploye) from DetailProduction dp where dp.production.id=p.id and dp.absent=0) +(select  sum(dg.delaiEmploye) from DetailProdGen dg where p.id=dg.productionGen.id and dg.absent=0), 14.13,(select count(*) from DetailProduction dp where dp.production.id=p.id and dp.absent=0) + (select count(*) from DetailProdGen dg where  p.id=dg.productionGen.id and dg.absent=0 ),sum(p.quantiteReel),l.machine.nom , p.articles.liblle FROM Production p  ,LigneMachine l where  p.ligneMachine.id=l.id and p.date between :dateDebut and :dateFin and p.statut =:statut and p.codeDepot=:depot group by p.date ,l.machine.nom");
		query= session.createQuery("select p.date, 14.13,sum(p.quantiteReel),l.machine.matricule , p.articles.liblle ,count(p.numOF) , p.articles.centreCout3,p.formeBox.id,p.formeBox.forme.id,p.formeBox.subCategorieMp.id  FROM Production p  ,LigneMachine l where  p.ligneMachine.id=l.id and p.date between :dateDebut and :dateFin and p.statut =:statut and p.codeDepot=:depot group by p.date ,l.machine.nom,p.articles.centreCout3,p.formeBox.forme.id,p.formeBox.subCategorieMp.id");

			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("statut", statut);
			query.setParameter("depot", depot);
		
		return query.list();

	}
	
	@Override
	public List<Object[]> listeSituationProductionGroupByDate(Date dateDebut,Date dateFin,String statut,String depot) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
		//	query= session.createQuery("select p.date,(select sum(dp.delaiEmploye) from DetailProduction dp where dp.production.id=p.id and dp.absent=0) +(select  sum(dg.delaiEmploye) from DetailProdGen dg where p.id=dg.productionGen.id and dg.absent=0), 14.13,(select count(*) from DetailProduction dp where dp.production.id=p.id and dp.absent=0) + (select count(*) from DetailProdGen dg where  p.id=dg.productionGen.id and dg.absent=0 ),sum(p.quantiteReel),l.machine.nom , p.articles.liblle FROM Production p  ,LigneMachine l where  p.ligneMachine.id=l.id and p.date between :dateDebut and :dateFin and p.statut =:statut and p.codeDepot=:depot group by p.date ,l.machine.nom");
		query= session.createQuery("select p.date, 14.13,sum(p.quantiteReel),l.machine.matricule , p.articles.liblle,p.formeBox.id,p.formeBox.forme.id,p.formeBox.subCategorieMp.id FROM Production p  ,LigneMachine l where  p.ligneMachine.id=l.id and p.date between :dateDebut and :dateFin and p.statut =:statut and p.codeDepot=:depot group by p.date");

			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("statut", statut);
			query.setParameter("depot", depot);
		
		return query.list();

	}
	
	
	

	public List<Object[]> listeSituationProductionParAnnee(Integer year,String statut,String depot) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
		//	query= session.createQuery("select p.date,(select sum(dp.delaiEmploye) from DetailProduction dp where dp.production.id=p.id and dp.absent=0) +(select  sum(dg.delaiEmploye) from DetailProdGen dg where p.id=dg.productionGen.id and dg.absent=0), 14.13,(select count(*) from DetailProduction dp where dp.production.id=p.id and dp.absent=0) + (select count(*) from DetailProdGen dg where  p.id=dg.productionGen.id and dg.absent=0 ),sum(p.quantiteReel),l.machine.nom , p.articles.liblle FROM Production p  ,LigneMachine l where  p.ligneMachine.id=l.id and p.date between :dateDebut and :dateFin and p.statut =:statut and p.codeDepot=:depot group by p.date ,l.machine.nom");
		query= session.createQuery("select year(p.date), MONTH(p.date) , sum(p.quantiteReel)  FROM Production p   where  year(p.date)=:year and p.statut =:statut and p.codeDepot=:depot group by year(p.date),MONTH(p.date)");

			query.setParameter("year", year);
			query.setParameter("statut", statut);
			query.setParameter("depot", depot);
		
		return query.list();

	}
	
	
	

	@Override
	public List<Object[]> NombreEmployeProduction(Date dateDebut,String statut,Machine machine , String depot, Boolean absent, BigDecimal grammage) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
			query= session.createQuery("select  sum(dp.delaiEmploye) , count(distinct dp.employe.id) from DetailProduction dp where dp.production.date=:dateDebut and dp.absent=:absent and dp.production.codeDepot=:depot and dp.production.ligneMachine.machine.id=:machine and dp.production.statut =:statut and dp.production.articles.centreCout3=:grammage");
		
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("machine", machine.getId());
			query.setParameter("statut", statut);
			query.setParameter("depot", depot);
			query.setParameter("absent", absent);
			query.setParameter("grammage", grammage);
		
		return query.list();

	}
	
	
	
	@Override
	public List<Object[]> NombreEmployeProductionParDate(Date dateDebut,String statut,String depot, Boolean absent) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
			query= session.createQuery("select  dp.id,  count(distinct dp.employe.id) from DetailProduction dp where dp.production.date=:dateDebut and dp.absent=:absent and dp.production.codeDepot=:depot and dp.production.statut =:statut");
		
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("statut", statut);
			query.setParameter("depot", depot);
			query.setParameter("absent", absent);
		
		
		return query.list();

	}
	
	
	@Override
	public List<Object[]> ListeNombreEmployeProductionParDate(Date dateDebut,String statut,String depot, Boolean absent) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
			query= session.createQuery("select  dp.id,  dp.employe.id  from DetailProduction dp where dp.production.date=:dateDebut and dp.absent=:absent and dp.production.codeDepot=:depot and dp.production.statut =:statut group by dp.employe.id");
		
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("statut", statut);
			query.setParameter("depot", depot);
			query.setParameter("absent", absent);
		
		
		return query.list();

	}
	
	
	@Override
	public List<Object[]> NombreEmployeGeneriqueParDate(Date dateDebut,String statut,String depot, Boolean absent) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
			query= session.createQuery("select  dg.id,  count(distinct dg.employe.id ) from DetailProdGen dg where dg.productionGen.date=:dateDebut and dg.absent=:absent and dg.productionGen.codeDepot=:depot  and dg.productionGen.statut =:statut");

			
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("statut", statut);
			query.setParameter("depot", depot);
			query.setParameter("absent", absent);
		
		
		return query.list();

	}
	
	
	@Override
	public List<Object[]> ListeNombreEmployeGeneriqueParDate(Date dateDebut,String statut,String depot, Boolean absent) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
			query= session.createQuery("select  dg.id,  dg.employe.id    from DetailProdGen dg where dg.productionGen.date=:dateDebut and dg.absent=:absent and dg.productionGen.codeDepot=:depot  and dg.productionGen.statut =:statut group by dg.employe.id ");

			
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("statut", statut);
			query.setParameter("depot", depot);
			query.setParameter("absent", absent);
		
		
		return query.list();

	}

	

	
	
	
	@Override
	public List<Object[]> NombreEmployeRepos(Date dateDebut, String depot) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
			query= session.createQuery("select   id , count(distinct dp.employe.id) from EmployeRepos dp where dp.dateSituation=:dateDebut  and dp.depot.code=:depot ");
		
			query.setParameter("dateDebut", dateDebut);
			
			query.setParameter("depot", depot);

		
		return query.list();

	}
	
	
	
	
	@Override
	public List<Object[]> NombreEmployeGenerique(Date dateDebut,String statut,Machine machine , String depot, Boolean absent, BigDecimal grammage) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
			query= session.createQuery("select  sum(dg.delaiEmploye) , count(distinct dg.employe.id ) from DetailProdGen dg where dg.productionGen.date=:dateDebut and dg.absent=:absent and dg.productionGen.codeDepot=:depot and dg.productionGen.ligneMachine.machine.id=:machine and dg.productionGen.statut =:statut and dg.productionGen.articles.centreCout3=:grammage");
		
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("machine", machine.getId());
			query.setParameter("statut", statut);
			query.setParameter("depot", depot);
			query.setParameter("absent", absent);
			query.setParameter("grammage", grammage);
		return query.list();

	}
	
	
	@Override
	public List<Object[]> NombreEmployeResponsable(Date dateDebut,Boolean absent) {
		// TODO Auto-generated method stub
		Query query=null;
		
			if(absent==true)
			{
				query= session.createQuery("select  sum(dres.delaiEmploye) , count(distinct dres.employe.id ) from DetailProdRes dres where dres.dateProduction=:dateDebut and   dres.delaiEmploye=0 and employe.salarie=0 ");

			}else
			{
				query= session.createQuery("select  sum(dres.delaiEmploye) , count(distinct dres.employe.id ) from DetailProdRes dres where dres.dateProduction=:dateDebut and  dres.delaiEmploye>0 and employe.salarie=0 ");

			}
		
			query.setParameter("dateDebut", dateDebut);		
			
		
		return query.list();

	}
	
	
	
	@Override
	public List<Object[]> ListeNombreEmployeResponsable(Date dateDebut,Boolean absent) {
		// TODO Auto-generated method stub
		Query query=null;
		
			if(absent==true)
			{
				query= session.createQuery("select  sum(dres.delaiEmploye) ,   dres.employe.id   from DetailProdRes dres where dres.dateProduction=:dateDebut and   dres.delaiEmploye=0 and employe.salarie=0 group by dres.employe.id ");

			}else
			{
				query= session.createQuery("select  sum(dres.delaiEmploye) ,   dres.employe.id   from DetailProdRes dres where dres.dateProduction=:dateDebut and  dres.delaiEmploye>0 and employe.salarie=0  group by dres.employe.id");

			}
		
			query.setParameter("dateDebut", dateDebut);		
			
		
		return query.list();

	}
	
	
	@Override
	public List<Object[]> NombreEmployeAdhesif(Date dateDebut,String statut , String depot,Boolean absent) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
			query= session.createQuery("select  sum(dpmp.delaiEmploye) , count(distinct dpmp.employe.id ) from DetailProductionMP dpmp where dpmp.productionMP.dateProduction=:dateDebut and dpmp.absent=:absent and dpmp.productionMP.statut=:statut and dpmp.productionMP.codeDepot=:depot");
		
			query.setParameter("dateDebut", dateDebut);		
			query.setParameter("absent", absent);
			query.setParameter("statut", statut);
			query.setParameter("depot", depot);
			
		return query.list();

	}
	
 
	public List<Object[]> ListeNombreEmployeAdhesif(Date dateDebut,String statut , String depot,Boolean absent) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
			query= session.createQuery("select  sum(dpmp.delaiEmploye) ,  dpmp.employe.id   from DetailProductionMP dpmp where dpmp.productionMP.dateProduction=:dateDebut and dpmp.absent=:absent and dpmp.productionMP.statut=:statut and dpmp.productionMP.codeDepot=:depot group by dpmp.employe.id");
		
			query.setParameter("dateDebut", dateDebut);		
			query.setParameter("absent", absent);
			query.setParameter("statut", statut);
			query.setParameter("depot", depot);
			
		return query.list();

	}
	
	
	@Override
	public List<Object[]> listeCoutMPProductionParArticle(Date dateDebut,Date dateFin,String statut,String depot , Articles article) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
		//	query= session.createQuery("select p.date,(select sum(dp.delaiEmploye) from DetailProduction dp where dp.production.id=p.id and dp.absent=0) +(select  sum(dg.delaiEmploye) from DetailProdGen dg where p.id=dg.productionGen.id and dg.absent=0), 14.13,(select count(*) from DetailProduction dp where dp.production.id=p.id and dp.absent=0) + (select count(*) from DetailProdGen dg where  p.id=dg.productionGen.id and dg.absent=0 ),sum(p.quantiteReel),l.machine.nom , p.articles.liblle FROM Production p  ,LigneMachine l where  p.ligneMachine.id=l.id and p.date between :dateDebut and :dateFin and p.statut =:statut and p.codeDepot=:depot group by p.date ,l.machine.nom");
		query= session.createQuery("select c.prodcutionCM.articles.codeArticle, c.prodcutionCM.articles.liblle,sum(c.prixTotal),sum(c.coutDechet),sum(c.coutDechetFournisseur),sum(c.coutManquante) FROM CoutMP c where c.prodcutionCM.date between :dateDebut and :dateFin and c.prodcutionCM.statut=:statut and c.prodcutionCM.codeDepot=:depot and c.prodcutionCM.articles.id=:article");

			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("statut", statut);
			query.setParameter("depot", depot);
			query.setParameter("article", article.getId());
		return query.list();

	}
	
	@Override
	public List<Object[]> listeCoutProductionParArticle(Date dateDebut,Date dateFin,String statut,String depot , Articles article) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
		//	query= session.createQuery("select p.date,(select sum(dp.delaiEmploye) from DetailProduction dp where dp.production.id=p.id and dp.absent=0) +(select  sum(dg.delaiEmploye) from DetailProdGen dg where p.id=dg.productionGen.id and dg.absent=0), 14.13,(select count(*) from DetailProduction dp where dp.production.id=p.id and dp.absent=0) + (select count(*) from DetailProdGen dg where  p.id=dg.productionGen.id and dg.absent=0 ),sum(p.quantiteReel),l.machine.nom , p.articles.liblle FROM Production p  ,LigneMachine l where  p.ligneMachine.id=l.id and p.date between :dateDebut and :dateFin and p.statut =:statut and p.codeDepot=:depot group by p.date ,l.machine.nom");
		query= session.createQuery("select sum(p.quantiteReel),sum(coutTotalEmployeGen),sum(coutTotalEmployeEmbalage),sum(coutTotalEmploye) FROM Production p where p.date between :dateDebut and :dateFin and p.statut=:statut and p.codeDepot=:depot and p.articles.id=:article");

			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("statut", statut);
			query.setParameter("depot", depot);
			query.setParameter("article", article.getId());
		return query.list();

	}
	
	@Override
	public List<Object[]> listeSituationGlobaleCoutProduction(Date dateDebut,Date dateFin,String statut,String depot ) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
		//	query= session.createQuery("select p.date,(select sum(dp.delaiEmploye) from DetailProduction dp where dp.production.id=p.id and dp.absent=0) +(select  sum(dg.delaiEmploye) from DetailProdGen dg where p.id=dg.productionGen.id and dg.absent=0), 14.13,(select count(*) from DetailProduction dp where dp.production.id=p.id and dp.absent=0) + (select count(*) from DetailProdGen dg where  p.id=dg.productionGen.id and dg.absent=0 ),sum(p.quantiteReel),l.machine.nom , p.articles.liblle FROM Production p  ,LigneMachine l where  p.ligneMachine.id=l.id and p.date between :dateDebut and :dateFin and p.statut =:statut and p.codeDepot=:depot group by p.date ,l.machine.nom");
		query= session.createQuery("select p.articles.codeArticle, p.articles.liblle ,sum(p.quantiteReel),sum(coutTotal) FROM Production p where p.date between :dateDebut and :dateFin and p.statut=:statut and p.codeDepot=:depot group by p.articles");

			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("statut", statut);
			query.setParameter("depot", depot);
			
		return query.list();

	}
	
	
	public List<Production> listeProductionByID() {
		
		Query query= session.createQuery("select p from Production p where p.statut='Terminé' and numOF not in (select CodeTransfer from TransferStockPF c where statut='ENTRE PRODUCTION')");		
	
		
		List<Production> list=query.list();
		
		return list;

	}
	
	
	@Override
	public List<Object[]> listeStatistiqueEnVracConsomme(String requete) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
			query= session.createQuery("select c.matierePremier.code, c.matierePremier.nom,c.matierePremier.categorieMp.nom,sum(c.quantConsomme),sum(c.quantiteManquanteFrPlus),sum(c.quantiteOffre),sum(c.quantDechet),sum(c.quantDechetFournisseur),sum(c.quantiteManquante) FROM CoutMP c where  "+requete+" group by c.matierePremier.code,c.matierePremier.nom,c.matierePremier.categorieMp.nom order by  sum(c.quantConsomme)  desc");
		
		
		return query.list();

	}
	
	@Override
	public List<Object[]> listeStatistiqueMPConsomme(String requete) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
			query= session.createQuery("select c.matierePremier.code, c.matierePremier.nom,sum(c.quantConsomme),sum(c.quantDechet),sum(c.quantDechetFournisseur) FROM CoutMP c where  "+requete+" group by c.matierePremier.code,c.matierePremier.nom order by c.matierePremier.id");
		
		
		return query.list();

	}
	
	

	public List<Object[]> listeSituationProductionTotalparArticle(String requete) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
			query= session.createQuery("select a.codeArticle , a.liblle , sum(p.quantiteReel) , p.id FROM Production p , Articles a  where p.articles.id=a.id  "+requete+" group by a.codeArticle, a.liblle order by  sum(p.quantiteReel)  desc ");
		
		
		return query.list();

	}
	
	
	public List<Object[]> listeTonnageProductionparJour(String requete) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
			query= session.createQuery("select p.date , sum(p.quantiteReel)  FROM Production p  where  p.statut='Terminé' "+requete+" group by p.date order by  p.date  ");
		
		
		return query.list();

	}
	
	public List<Object[]> listeStatistiqueEnVracConsommeLorsDeLaproductionPF(String requete) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
			query= session.createQuery("select  p.articles.codeArticle , c.matierePremier.code , sum(c.quantConsomme)   FROM Production p , Articles a, CoutMP c  where p.articles.id=a.id and  p.id=c.prodcutionCM.id "+requete+"  group by p.articles.codeArticle, c.matierePremier.code order by p.articles.id, sum(c.quantConsomme)  desc");
		
		
		return query.list();

	}
	public List<Object[]> listeStatistiqueEnVracConsommeLorsDeLaproductionPFGroupByArticle(String requete) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
			query= session.createQuery("select  p.articles.codeArticle ,  count( distinct  c.matierePremier.code )  , sum(c.quantConsomme)   FROM Production p , Articles a, CoutMP c  where p.articles.id=a.id and  p.id=c.prodcutionCM.id "+requete+"  group by p.articles.codeArticle  order by p.articles.id, sum(c.quantConsomme)   desc");
		
		
		return query.list();

	}
	
	@Override
	public CoutMP CoutMPParIdProductionGroupByIDCategorie (Integer idProduction) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
		
			query= session.createQuery("select  c  FROM CoutMP c   where  c.prodcutionCM.id=:idproduction and  c.matierePremier.categorieMp.subCategorieMp.id=1 group by c.prodcutionCM.id");

			query.setParameter("idproduction", idProduction);
		
		
	
		return (CoutMP) query.uniqueResult();

	}
	
	
	public List<Object[]> listeArticleFabriqueEtSonType( ) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
			query= session.createQuery("select  p.articles.codeArticle ,c.matierePremier.categorieMp.nom  FROM Production p , Articles a, CoutMP c  where p.articles.id=a.id and  p.id=c.prodcutionCM.id  and c.matierePremier.categorieMp.subCategorieMp=1  group by p.articles.codeArticle  order by p.articles.id ");
		
		
		return query.list();

	}
	
	
	

}

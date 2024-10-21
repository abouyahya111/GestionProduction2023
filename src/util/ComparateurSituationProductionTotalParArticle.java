package util;
import java.util.Comparator;

import dao.entity.EtatStockMP;
import dao.entity.FicheEmploye;
import dao.entity.SituationEnVrac;
import dao.entity.SituationProductionTotalParArticlePFClass;
public class ComparateurSituationProductionTotalParArticle implements Comparator<SituationProductionTotalParArticlePFClass>{
	
	
	
	

	
	    @Override
	    public int compare(SituationProductionTotalParArticlePFClass IdMP1, SituationProductionTotalParArticlePFClass IdMP2) {
	    	
	    	 if (IdMP1.getPourcentage().compareTo( IdMP2.getPourcentage()) <0)  
	             return 1;   
	         else  
	             return -1;
	    	
	    	  
	    }
	
	
	
	

}

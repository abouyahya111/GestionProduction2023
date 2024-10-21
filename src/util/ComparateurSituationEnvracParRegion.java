package util;
import java.util.Comparator;

import dao.entity.EtatStockMP;
import dao.entity.FicheEmploye;
import dao.entity.SituationEnVrac;
public class ComparateurSituationEnvracParRegion implements Comparator<SituationEnVrac>{
	
	
	
	

	
	    @Override
	    public int compare(SituationEnVrac IdMP1, SituationEnVrac IdMP2) {
	    	
	    	 if (IdMP1.getMp().getId() > IdMP2.getMp().getId())  
	             return 1;   
	         else  
	             return -1;
	    	
	    	  
	    }
	
	
	
	

}

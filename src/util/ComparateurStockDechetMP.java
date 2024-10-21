package util;
import java.util.Comparator;

import dao.entity.EtatStockDechetMP;
import dao.entity.EtatStockMP;
import dao.entity.FicheEmploye;
public class ComparateurStockDechetMP implements Comparator<EtatStockDechetMP>{
	
	
	
	

	
	    @Override
	    public int compare(EtatStockDechetMP IdMP1, EtatStockDechetMP IdMP2) {
	    	
	    	 if (IdMP1.getMp().getId() > IdMP2.getMp().getId())  
	             return 1;   
	         else  
	             return -1;
	    	
	    	  
	    }
	
	
	
	

}

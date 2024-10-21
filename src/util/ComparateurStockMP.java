package util;
import java.util.Comparator;

import dao.entity.EtatStockMP;
import dao.entity.FicheEmploye;
public class ComparateurStockMP implements Comparator<EtatStockMP>{
	
	
	
	

	
	    @Override
	    public int compare(EtatStockMP IdMP1, EtatStockMP IdMP2) {
	    	
	    	 if (IdMP1.getMp().getId() > IdMP2.getMp().getId())  
	    	 {
	    		 return 1;  
	    	 }
	             
	         else  if(IdMP1.getMp().getId() < IdMP2.getMp().getId())
	         {
	        	 return -1;
	         }else
	        	 return 0;
	            
	    	
	    	  
	    }
	
	
	
	

}

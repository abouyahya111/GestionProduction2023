package util;
import java.util.Comparator;

import dao.entity.FicheEmploye;
public class Comparateur implements Comparator<FicheEmploye>{
	
	
	
	

	
	    @Override
	    public int compare(FicheEmploye date1, FicheEmploye date2) {
	        return date1.getDateSituation().compareTo(date2.getDateSituation());
	    }
	
	
	
	

}

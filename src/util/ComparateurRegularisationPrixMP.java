package util;
import java.util.Comparator;

import dao.entity.FicheEmploye;
import dao.entity.RegularisationPrixMp;
public class ComparateurRegularisationPrixMP implements Comparator<RegularisationPrixMp>{
	 
	    @Override
	    public int compare(RegularisationPrixMp date1, RegularisationPrixMp date2) {
	        return date1.getDate().compareTo(date2.getDate());
	    }
	
	
	
	

}

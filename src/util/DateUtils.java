package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * Classe principale de traitement des dates.
 * @author EBE
 *
 */
public class DateUtils implements Constantes {
    static final long UNE_HEURE = 60 * 60 * 1000L;


 

    public static String getCurrentDate() {
        Date date = new Date();
        SimpleDateFormat simpleFormat = new SimpleDateFormat(DATE_FORMAT);

        return simpleFormat.format(date);
    }

    public static String getCurrentYear() {
        Date date = new Date();
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy");

        return simpleFormat.format(date);
    }

    public static String getYear(String date) {
        return date.substring(6, 10);
    }
    
    public static Date DateaddDay(Date dt, int i) {
    	
    	Calendar c = Calendar.getInstance(); 
    	c.setTime(dt); 
    	c.add(Calendar.DATE, i);
    	dt = c.getTime();
    	return dt;
    }

    public static int getJour(Date date) {
        
        GregorianCalendar dateInfCalendar = new GregorianCalendar();
        dateInfCalendar.setTime(date);
           
           return dateInfCalendar.get(GregorianCalendar.DAY_OF_MONTH);
       }

    public static int soustraire(Date dateSup, Date dateInf) {
        /*
         * Cette methode retourne le nombre de mois de retard de @dateSup sur @dateInf
         */
        GregorianCalendar dateInfCalendar = new GregorianCalendar();
        dateInfCalendar.setTime(dateInf);

        GregorianCalendar dateSupCalendar = new GregorianCalendar();
        dateSupCalendar.setTime(dateSup);

        if (dateSupCalendar.compareTo(dateInfCalendar) > 0) {
            int diffAnnee = dateSupCalendar.get(GregorianCalendar.YEAR) - dateInfCalendar.get(GregorianCalendar.YEAR);
            int diffMois = dateSupCalendar.get(GregorianCalendar.MONTH) - dateInfCalendar.get(GregorianCalendar.MONTH);
            int diffJours = dateSupCalendar.get(GregorianCalendar.DAY_OF_MONTH) - dateInfCalendar.get(GregorianCalendar.DAY_OF_MONTH);

            if (diffMois < 0) {
                diffAnnee--;
                diffMois = (dateSupCalendar.get(GregorianCalendar.MONTH) + 12) - dateInfCalendar.get(GregorianCalendar.MONTH);
            }

            if (diffJours < 0) {
                diffMois--;
            }

            return ((diffAnnee * 12) + diffMois);
        } else {
            return 0;
        }
    }

    public static long nbJoursEntre(Date d1, Date d2) {
        return ((d2.getTime() - d1.getTime() + UNE_HEURE) / (UNE_HEURE * 24)) + 1;
    }

    public static Date ajoutNbJours(Date date, int nbJours) {
        /*
         * Cette methode retourne la date passé en param moins un nombre de jours
         */
        GregorianCalendar dateCalendar = new GregorianCalendar();
        dateCalendar.setTime(date);
        dateCalendar.add(Calendar.DATE, nbJours);

        return dateCalendar.getTime();
    }

    public static Boolean isLessThanAYear(Date dateDebut, Date datefin) {
        if ((isBissextilYear(datefin) && (nbJoursEntre(dateDebut, datefin) < 366)) || (!isBissextilYear(datefin) && (nbJoursEntre(dateDebut, datefin) < 365))) {
            return true;
        } else {
            return false;
        }
    }

    public static Boolean isLessOrEqualsAYear(Date dateDebut, Date datefin) {
        if ((isBissextilYear(datefin) && (nbJoursEntre(dateDebut, datefin) <= 366)) || (!isBissextilYear(datefin) && (nbJoursEntre(dateDebut, datefin) <= 365))) {
            return true;
        } else {
            return false;
        }
    }

    public static Boolean isBissextilYear(Date dateDebut) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(dateDebut);

        int year = calendar.get(GregorianCalendar.YEAR);

        if ((year % 4) == 0) {
            return true;
        } else {
            return false;
        }
    }

 

 public static int getMois(Date date) {
        
     GregorianCalendar dateInfCalendar = new GregorianCalendar();
     dateInfCalendar.setTime(date);
        
        return dateInfCalendar.get(GregorianCalendar.MONTH)+1;
    }
 
 public static int getAnnee(Date date) {
     
     GregorianCalendar dateInfCalendar = new GregorianCalendar();
     dateInfCalendar.setTime(date);
        
        return dateInfCalendar.get(GregorianCalendar.YEAR);
    }
 
 

    
    /**
     * Methode pour decaler une date d'un nombre de mois donné en paramètre.
     * Cette methode est utlisée uniquement lors du calcul de la date d'exigiblité.
     * @param dateReference
     * @param nbMois
     * @return
     */
    public static Date deplacerDate(Date dateReference, int nbMois) {
        Calendar calender = Calendar.getInstance();
        calender.setTime(dateReference);

        calender.add(Calendar.MONTH, nbMois);
        
        //  La "dateReference" représente la première milliseconde de la date de debut de l'exercice fiscal, aprés l'ajout du nombre de mois, 
        // nous revenons une seconde en arrière, afin de rester dans le dernier jour de la période ajoutée.
        calender.add(Calendar.SECOND, -1);
        

        return calender.getTime();
    }

    /**
     * Methode de verification des wekk-end.
     * @param date
     * @return
     */
    public static boolean isWeekEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        if ((calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) || (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
            return true;
        } else {
            return false;
        }
    }


    
    public static boolean  areOnSameMonth(Date now, Date dateOperationDepot) {

	GregorianCalendar dateCalendar = new GregorianCalendar();
        dateCalendar.setTime(dateOperationDepot);
        dateCalendar.set(Calendar.DAY_OF_MONTH, 1);
        
        if(soustraire(now, dateCalendar.getTime()) == 0 ){
            
            return true;
        }
	
	return false;
    }
}

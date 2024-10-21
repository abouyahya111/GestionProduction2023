package util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;


public class NumberUtils {
    public static Double roundUp(Double valeur, int precision) {
        BigDecimal val = new BigDecimal(valeur.toString());

        return val.setScale(precision, BigDecimal.ROUND_UP).doubleValue();
    }
    
    public static float roundDown(Double valeur, int precision) {
        BigDecimal val = new BigDecimal(valeur.toString());

        return val.setScale(precision, BigDecimal.ROUND_DOWN).floatValue();
    }
    
    public static float roundHalfDown(Double valeur, int precision) {
        BigDecimal val = new BigDecimal(valeur.toString());

        return val.setScale(precision, BigDecimal.ROUND_HALF_DOWN).floatValue();
    }
      
    /**
     * Methode pour arrondir les chiffres. Exemple : 93 -> 100
     *
     * @param valeur
     * @return
     */
    public static Double roundDizaine(Double valeur) {
        return roundUp(valeur, -1);
    }

    /**
     * Methode pour arrondir les chiffres. Exemple : 93.2 -> 94
     *
     * @param valeur
     * @return
     */
    public static Double round(Double valeur) {
        return roundUp(valeur, 0);
    }

    /**
     * Methode pour arrondir les chiffres. Exemple : 99.334 -> 99.34
     *
     * @param valeur
     * @return
     */
    public static Double roundMillieme(Double valeur) {
        return roundUp(valeur, 2);
    }
    
    /**
     * Methode pour arrondir les chiffres. Exemple : 99.33 -> 99.4
     *
     * @param valeur
     * @return
     */
    public static Double roundCentieme(Double valeur) {
        return roundUp(valeur, 1);
    }

    public static String formatToString(Double valeur) {
        DecimalFormatSymbols decimalSymbol = new DecimalFormatSymbols(Locale.FRENCH);
        decimalSymbol.setGroupingSeparator(' ');

        DecimalFormat formatDouble = new DecimalFormat("#,##0.00", decimalSymbol);

        if (valeur == null) {
            return formatDouble.format(0d);
        } else {
            return formatDouble.format(valeur);
        }
    }

    public static String formatToInt(Double valeur) {
        DecimalFormatSymbols decimalSymbol = new DecimalFormatSymbols(Locale.FRENCH);
        decimalSymbol.setGroupingSeparator(' ');

        DecimalFormat formatInt = new DecimalFormat("#,##0", decimalSymbol);

        return formatInt.format(valeur);
    }

    /**
     * Methode de formatage d'un double
     * 22.5533 -> 22.56
     */
    public static double normalizeDouble(double valeur) {
        BigDecimal val = new BigDecimal(valeur);

        return val.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static Double formatToDouble(String chaine) {
        if ((chaine == null) || chaine.equalsIgnoreCase("")) {
            return new Double(0);
        }
try{
    return Double.valueOf(chaine.replaceAll(" ", "").replaceAll(",", "."));
} catch(Exception e){
        return new Double(0);
}
    }

    public static String insertZeros(long numberAsInt, int size) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMinimumIntegerDigits(size);
        nf.setMaximumIntegerDigits(size);
        nf.setGroupingUsed(false);

        String numberAsString = nf.format(numberAsInt);

        return numberAsString;
    }
    
    public static Double multiply(Double valeur1, Double valeur2) {
        BigDecimal val1 = new BigDecimal(valeur1.toString());

        return val1.multiply(new BigDecimal(valeur2.toString())).doubleValue();
    }
    
    public static String GroupingFormatBigDecimal(BigDecimal valeur)
    {
    	
    return	String.format( "%,f",valeur);
    
    }
    
}

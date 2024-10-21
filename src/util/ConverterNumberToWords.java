package util;
import java.text.*;

import javax.swing.JOptionPane;
public class ConverterNumberToWords {
	
	 private static final String[] dizaineNames = {
		    "",
		    "",
		    "VINGT",
		    "TRENTE",
		    "QUARANTE",
		    "CINQUANTE",
		    "SOIXANTE",
		    "SOIXANTE",
		    "QUATRE-VINGT",
		    "QUATRE-VINGT"
		  };

		  private static final String[] uniteNames1 = {
		    "",
		    "UN",
		    "DEUX",
		    "TROIS",
		    "QUATRE",
		    "CINQUE",
		    "SIX",
		    "SEPT",
		    "HUIT",
		    "NEUF",
		    "DIX",
		    "ONZE",
		    "DOUZE",
		    "TREIZE",
		    "QUATORZE",
		    "QUINZE",
		    "SEIZE",
		    "DIX-SEPT",
		    "DIX-HUIT",
		    "DIX-NEUF"
		  };

		  private static final String[] uniteNames2 = {
		    "",
		    "",
		    "DEUX",
		    "TROIS",
		    "QUATRE",
		    "CINQUE",
		    "SIX",
		    "SEPT",
		    "HUIT",
		    "NEUF",
		    "DIX"
		  };

		  private ConverterNumberToWords() {}

		  private static String convertZeroToHundred(int number) {

		    int laDizaine = number / 10;
		    int lUnite = number % 10;
		    String resultat = "";

		    switch (laDizaine) {
		    case 1 :
		    case 7 :
		    case 9 :
		      lUnite = lUnite + 10;
		      break;
		    default:
		    }

		    // s�parateur "-" "et"  ""
		    String laLiaison = "";
		    if (laDizaine > 1) {
		      laLiaison = "-";
		    }
		    // cas particuliers
		    switch (lUnite) {
		    case 0:
		      laLiaison = "";
		      break;
		    case 1 :
		      if (laDizaine == 8) {
		        laLiaison = "-";
		      }
		      else {
		        laLiaison = " ET ";
		      }
		      break;
		    case 11 :
		      if (laDizaine==7) {
		        laLiaison = " ET ";
		      }
		      break;
		    default:
		    }

		    // dizaines en lettres
		    switch (laDizaine) {
		    case 0:
		      resultat = uniteNames1[lUnite];
		      break;
		    case 8 :
		      if (lUnite == 0) {
		        resultat = dizaineNames[laDizaine];
		      }
		      else {
		        resultat = dizaineNames[laDizaine]
		                                + laLiaison + uniteNames1[lUnite];
		      }
		      break;
		    default :
		      resultat = dizaineNames[laDizaine]
		                              + laLiaison + uniteNames1[lUnite];
		    }
		    return resultat;
		  }

		  private static String convertLessThanOneThousand(int number) {

		    int lesCentaines = number / 100;
		    int leReste = number % 100;
		    String sReste = convertZeroToHundred(leReste);

		    String resultat;
		    switch (lesCentaines) {
		    case 0:
		      resultat = sReste;
		      break;
		    case 1 :
		      if (leReste > 0) {
		        resultat = "CENT " + sReste;
		      }
		      else {
		        resultat = "CENT";
		      }
		      break;
		    default :
		      if (leReste > 0) {
		        resultat = uniteNames2[lesCentaines] + " CENT " + sReste;
		      }
		      else {
		        resultat = uniteNames2[lesCentaines] + " CENTS";
		      }
		    }
		    return resultat;
		  }

		  public static String convert(long number) {
		    // 0 � 999 999 999 999
		    if (number == 0) { return "zéro"; }

		    String snumber = Long.toString(number);

		    // pad des "0"
		    String mask = "000000000000";
		    DecimalFormat df = new DecimalFormat(mask);
		    snumber = df.format(number);

		    // XXXnnnnnnnnn
		    int lesMilliards = Integer.parseInt(snumber.substring(0,3));
		    // nnnXXXnnnnnn
		    int lesMillions  = Integer.parseInt(snumber.substring(3,6));
		    // nnnnnnXXXnnn
		    int lesCentMille = Integer.parseInt(snumber.substring(6,9));
		    // nnnnnnnnnXXX
		    int lesMille = Integer.parseInt(snumber.substring(9,12));

		    String tradMilliards;
		    switch (lesMilliards) {
		    case 0:
		      tradMilliards = "";
		      break;
		    case 1 :
		      tradMilliards = convertLessThanOneThousand(lesMilliards)
		         + " MILLIARD ";
		      break;
		    default :
		      tradMilliards = convertLessThanOneThousand(lesMilliards)
		         + " MILLIARDS ";
		    }
		    String resultat =  tradMilliards;

		    String tradMillions;
		    switch (lesMillions) {
		    case 0:
		      tradMillions = "";
		      break;
		    case 1 :
		      tradMillions = convertLessThanOneThousand(lesMillions)
		         + " MILLION ";
		      break;
		    default :
		      tradMillions = convertLessThanOneThousand(lesMillions)
		         + " MILLIONS ";
		    }
		    resultat =  resultat + tradMillions;

		    String tradCentMille;
		    switch (lesCentMille) {
		    case 0:
		      tradCentMille = "";
		      break;
		    case 1 :
		      tradCentMille = "MILLE ";
		      break;
		    default :
		      tradCentMille = convertLessThanOneThousand(lesCentMille)
		         + " MILLE ";
		    }
		    resultat =  resultat + tradCentMille;

		    String tradMille;
		    tradMille = convertLessThanOneThousand(lesMille);
		    resultat =  resultat + tradMille;

		    return resultat;
		  }
		  /*
		  public static String converter(float number)
		  {
			  String number1="";
			  String number2="";
			  String resultat1="";
			  String resultat2="";
			  String resultatfinal="";
			  if(number!=0)
			  {
				  String x=String.valueOf(number);
				  String [] total=x.split(".");
				  JOptionPane.showMessageDialog(null, "total size : "+x);
				  if(total.length>1)
				  {
					  number1= total[0];
					  number2=total[1];
					   resultat1=convert(Long.valueOf(number1));
					   resultat2=convert(Long.valueOf(number2));
					   resultatfinal=resultat1 +"Virgule "+resultat2;
					  
				  }else if(total.length==1)
				  {
					  number1= total[0];
					  resultat1=convert(Long.valueOf(number1));
					  resultatfinal=resultat1;
				  }
				  
			  }
			
			  
			  
			  
			  
			return resultatfinal;
			  
			 
		  }
		  
		  */
		  
		  
		  public static String converter(String number) {
			     
			    String resultat="";
			    if(number!="0")
			    {
			    	 String []total=String.valueOf(number).split(",");
			    	
			    	 if(total.length!=0)
			    	 {
			    		 if(total[1].equals("00") || total[1].equals("0") )
					      {
					         resultat=convert(Long.valueOf(total[0]))+" DIRHAMS";
					      }else
					      {
					         resultat=convert(Long.valueOf(total[0]))+" DIRHAMS ET " +convert(Long.valueOf(total[1]))+" CENTIMES";  
					      } 
			    	 }
				     
				   
			    }
			    
			     
			      return resultat;
			     
			 }

		  

}

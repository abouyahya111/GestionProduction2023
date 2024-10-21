package config;


import java.sql.*;

import javax.swing.JOptionPane;


public class Connect {
	
	// private static String driver= "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // Driver Connection SQL Server
	
	private static String driver= "com.mysql.jdbc.Driver"; // Driver Connection My Sql
	
	public static Connection  connexion=null;
	public static Connection  connexionCommande=null;
	
	
	public static void connecToProduction(){
		try {
          
			
			/****************************************** Connection to Sql Server **********************************************/
			
			//  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
			  //StringconnectionUrl = "jdbc:sqlserver://192.168.1.5;" +"databaseName=FACTURATION;user=SA;password=0000"; 
			//  String connectionUrl = "jdbc:sqlserver://localhost;" +"databaseName=FACTURATION;user=SA;password=0000"; 
			  //String connectionUrl = "jdbc:sqlserver://192.168.1.5;" +"databaseName=DISTRIBUTION;user=SA;password=0000";
			//  Class.forName(driver);
			//  connexion = DriverManager.getConnection(connectionUrl);
			 	
			/*********************************************************************************************************************/
			
			/****************************************** Connection to MySql **********************************************/
			
			Class.forName("com.mysql.jdbc.Driver"); 
			//String connectionUrl = "jdbc:sqlserver://192.168.1.5;" +"databaseName=FACTURATION;user=SA;password=0000"; 
			//connexion = DriverManager.getConnection("jdbc:mysql://192.168.1.5:3306/facturationtest","user","!kal!0687*"); 
			//String connectionUrl = "jdbc:sqlserver://192.168.1.5;" +"databaseName=DISTRIBUTION;user=SA;password=0000"; 
			//Class.forName(driver); 
			//connexion = DriverManager.getConnection("jdbc:mysql://192.168.1.36:3307/facturation_test","user","12345"); 
			//connexion = DriverManager.getConnection("jdbc:mysql://81.192.169.195:3306/Facturation","user","!kal!0687*"); 
			
			
			 
			//connexion = DriverManager.getConnection("jdbc:mysql://81.192.169.195:3306/gestionproduction?useUnicode=true&characterEncoding=utf8","user","!kal!0687*");
			
			 // connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionproduction","root","root");
		 
			   
			// connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/production","root","root");
			
			/*********************************************************************************************************************/
			
			
	
		}
		catch(Exception e){
			
			
			JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données gestionproduction", "Erreur", JOptionPane.ERROR_MESSAGE);
                        
			
		} 
	}
	
	
	
	/*
	 * 
	 * Traitement Reception Via Gestion Commande Finale Serveur
	public static void connecToGestionCommande(){
		try {
    
			
			Class.forName("com.mysql.jdbc.Driver"); 
			//String connectionUrl = "jdbc:sqlserver://192.168.1.5;" +"databaseName=FACTURATION;user=SA;password=0000"; 
			//connexion = DriverManager.getConnection("jdbc:mysql://192.168.1.5:3306/facturationtest","user","!kal!0687*"); 
			//String connectionUrl = "jdbc:sqlserver://192.168.1.5;" +"databaseName=DISTRIBUTION;user=SA;password=0000"; 
			//Class.forName(driver); 
			//connexion = DriverManager.getConnection("jdbc:mysql://192.168.1.36:3307/facturation_test","user","12345"); 
			//connexion = DriverManager.getConnection("jdbc:mysql://81.192.169.195:3306/Facturation","user","!kal!0687*"); 
			
			
			 
			//connexion = DriverManager.getConnection("jdbc:mysql://81.192.169.195:3306/gestionproduction?useUnicode=true&characterEncoding=utf8","user","!kal!0687*");
			
		 
			connexionCommande = DriverManager.getConnection("jdbc:mysql://81.192.169.195:3310/gestion_commande_final?useUnicode=true&characterEncoding=utf8","user","!Kal!0687*");
			   
			// connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/production","root","root");
			
			 
			
			
	
		}
		catch(Exception e){
			
			
			JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données gestion_commande_final", "Erreur", JOptionPane.ERROR_MESSAGE);
                        
			
		} 
	}
	*/
	
	public static void discconnect(){
		try{
			Class.forName(driver);
			connexion.close();
		}
		catch(Exception e){
		}
    }
	public static Connection getConnexion() {
		return connexion;
	}
 
	
	/*   Traitement Reception Via Gestion Commande Finale Serveur
	
	public static Connection getConnexionCommande() {
		return connexionCommande;
	}
	
	public static void discconnectCommande(){
		try{
			Class.forName(driver);
			connexionCommande.close();
		}
		catch(Exception e){
		}
    }
    
	*/
}


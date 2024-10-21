package util;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


public class HibernateUtil {

    //XML based configuration
    private static SessionFactory sessionFactory=buildSessionFactory();
    private static ServiceRegistry serviceRegistry;
    private static Session session=null;
   

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.configure("config/hibernate.cfg.xml");

             serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();

            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            return sessionFactory;
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }



  

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) sessionFactory = buildSessionFactory();
        return sessionFactory;
    }
    public static Session openSession() {
        return sessionFactory.openSession();
    }

    public static Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public static void close() {
    	 if(sessionFactory == null){
    		 sessionFactory.close();
    	 }
    }

}
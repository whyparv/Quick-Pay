package one.connect;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ConnectionFactory {
    static SessionFactory sf;
    static
    {
        sf=new Configuration().configure().buildSessionFactory();
    }
    static public SessionFactory emergencyconnection()
    {
       if(sf.isClosed()) 
           sf=new Configuration().configure().buildSessionFactory();
       return sf;
    }
    
}

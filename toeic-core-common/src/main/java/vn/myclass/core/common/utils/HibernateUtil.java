package vn.myclass.core.common.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by trunght on 28/06/2017.
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();
    private  static SessionFactory buildSessionFactory(){
        //create sessionfactory from hibernate.cfg.xml
        try {
            return new Configuration().configure().buildSessionFactory();
        }
      catch (Throwable ex) {
            System.out.println("Inniital session factory fail. " + ex);
          throw new ExceptionInInitializerError(ex);
        }
    }

    public  static  SessionFactory getSessionFactory(){
        return sessionFactory;
    }


}

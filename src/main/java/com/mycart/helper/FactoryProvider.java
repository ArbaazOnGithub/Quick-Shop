package com.mycart.helper;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryProvider {

    private static SessionFactory factory;

    static {
        try {
            
            factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
           
        } catch (Exception e) {
            e.printStackTrace();
            
            System.err.println("Error creating SessionFactory: " + e.getMessage());
        }
    }

    public static SessionFactory getFactory() {
        return factory;
    }
}

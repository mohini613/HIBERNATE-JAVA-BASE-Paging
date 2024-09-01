package com.pagingST;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

public class HibernateUtill {

    private static SessionFactory sf; // Singleton instance of SessionFactory

    public static SessionFactory getSessionFactory() {
        if (sf == null) {
            Map<String, Object> settings = new HashMap<String, Object>();
            
            // Connection settings
            settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
            settings.put(Environment.URL, "jdbc:mysql://localhost:3306/pagingst");
            settings.put(Environment.USER, "root");
            settings.put(Environment.PASS, "root");

            // Hibernate settings
            settings.put(Environment.HBM2DDL_AUTO, "update");
            settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL55Dialect");
            settings.put(Environment.SHOW_SQL, "true");

            // Build registry and metadata
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .applySettings(settings).build();
            
            MetadataSources ms = new MetadataSources(registry)
                    .addAnnotatedClass(com.pagingST.Student.class)
                    .addAnnotatedClass(com.pagingST.Teacher.class);

            Metadata msd = ms.getMetadataBuilder().build();
            sf = msd.getSessionFactoryBuilder().build(); // Assign to the static SessionFactory variable
        }

        return sf; // Return the singleton SessionFactory instance
    }
}

package ru.stqa.pft.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.mantis.model.User;


import java.util.List;

public class DbHelper {

    private final SessionFactory sessionFactory;
    private final ApplicationManager app;

    public DbHelper(ApplicationManager app) {
        this.app = app;
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }

    public List<User> users() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<User> result = session.createQuery( "from User" ).list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public User selectUserWithMaxId() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = (User) session.createQuery("from User order by id desc")
                .setMaxResults(1)
                .getSingleResult();
        session.getTransaction().commit();
        session.close();
        return user;
    }
}

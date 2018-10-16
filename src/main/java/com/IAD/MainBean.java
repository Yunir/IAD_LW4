package com.IAD;

import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Model;
import javax.persistence.metamodel.EntityType;
import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Model
//@Stateless
//@Dependent
@Stateful
@LocalBean
//TODO: change direction to secure page again and add LocalBean annotation
public class MainBean implements IMainBean, Serializable {
    private static final SessionFactory ourSessionFactory;
    ArrayList<Double> x_values;
    ArrayList<Double> y_values;
    ArrayList<Integer> r_values;
    ArrayList<Boolean> res_values;
    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    @PostConstruct
    public void init(){
        System.out.println(MainBean.class.getName() + " constructed successfully on "+ new Date().toString());
    }

    public Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public MainBean() {
        if(x_values == null) {
            x_values = new ArrayList<>();
            y_values = new ArrayList<>();
            r_values = new ArrayList<>();
            res_values = new ArrayList<>();
        }
    }


    public void getConnection(){
        final Session session = getSession();
        try {
            System.out.println("querying all the managed entities...");
            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                final String entityName = entityType.getName();
                final Query query = session.createQuery("from " + entityName);
                System.out.println("executing: " + query.getQueryString());
                System.out.println("Size " + query.list().size());
                for (Object o : query.list()) {
                    System.out.println("yes");
                    System.out.println("  " + o);
                }
                System.out.println("no");
            }
        } finally {
            session.close();
        }
    }

    public List<Hit> getHitsList(){
        return null;
    }

    public String addToList(){
        //todo: connection
        //todo: get the result from checkArea
        //todo: insert command
        return null;
    }

    private boolean checkArea(double x, double y, double r){
        System.out.println(x + " " + y + " " + r + " " + (x >= 0 && y >= 0) + (x <= r) + (y <= (r/2)) + " " + y + " " + r/2);
        if((x >= 0 && y >= 0) && (x <= r) && (y <= (r/2))){
            System.out.println("1st! rectangle!");
            return true;
        }
        else if(x >= 0 && y <= 0 && y >= (x-(r/2))){
            System.out.println("2nd! triangle!");
            return true;
        }
        else if (x <= 0 && y >= 0 && (r/2) >= Math.sqrt(y*y+x*x)){
            System.out.println("3rd! circle!");
            return true;
        }
        else {
            System.out.println("Why there's mistake?!");
            return false;
        }
    }

    @Override
    public void simple() {
        System.out.println("ALALALLALA");
    }

    @Override
    public void saveDataToDB(double x, double y, double r, boolean res) {
        System.out.println("Results is x: " + x + " y: " + y + " r: " + " " + r + " res: " + res);
        final Session session = getSession();
        session.beginTransaction();

        HitTableEntity hit = new HitTableEntity();
        hit.setX(x);
        hit.setY(y);
        hit.setR((int)r);
        hit.setAnswer(res);
        final Query query = session.createQuery("from HitTableEntity");
        hit.setId(query.list().size());



        session.save(hit);
        session.getTransaction().commit();
    }
}

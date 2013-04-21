package com.mycompany.app;

/**
 * Created with IntelliJ IDEA.
 * User: hudvin
 * Date: 4/17/13
 * Time: 3:37 PM
 * To change this template use File | Settings | File Templates.
 */

import org.hibernate.*;

import java.util.List;

public class HibernateTest {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        Department department = new Department("java");
        session.save(department);

        session.save(new Employee("Jakab Gipsz", department));
        session.save(new Employee("Captain Nemo", department));


        Account account = new Account();
        account.setEmail("firefly@nevilon.com");
        account.setName("firefly");
        account.setPassword("qwerty");
        account.getTags().add(new Tag("this is tag"));

        session.save(account);

        session.getTransaction().commit();


        Query q = session.createQuery("From Employee ");

        List<Employee> resultList = q.list();
        System.out.println("num of employess:" + resultList.size());
        for (Employee next : resultList) {
            System.out.println("next employee: " + next);
        }

        q = session.createQuery("From Tag ");
        List<Tag> tagsList = q.list();

        System.out.println("num of tags:" + tagsList.size());
        for (Tag next : tagsList) {
            System.out.println("next employee: " + next.getValue());
        }

    }

}

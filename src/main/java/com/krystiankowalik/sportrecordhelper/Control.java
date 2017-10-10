package com.krystiankowalik.sportrecordhelper;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class Control {

    @Autowired
    private SessionFactory sessionFactory;


    public void run() {
        Session session = sessionFactory.getCurrentSession();
/*        session.beginTransaction();
        session.close();*/

    }
}

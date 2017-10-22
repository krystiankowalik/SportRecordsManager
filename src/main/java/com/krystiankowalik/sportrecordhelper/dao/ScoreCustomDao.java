package com.krystiankowalik.sportrecordhelper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;

@Repository
public class ScoreCustomDao{

    private final EntityManager entityManager;

    @Autowired
    public ScoreCustomDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public LocalDate findOldestDate() {
        return entityManager
                .createQuery("SELECT s.date FROM scores s order by s.date", LocalDate.class)
                .setMaxResults(1)
                .getSingleResult();
    }
}

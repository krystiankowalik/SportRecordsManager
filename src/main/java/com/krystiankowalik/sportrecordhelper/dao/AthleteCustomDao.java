package com.krystiankowalik.sportrecordhelper.dao;

import com.krystiankowalik.sportrecordhelper.model.Athlete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.text.Bidi;
import java.util.List;

@Repository
public class AthleteCustomDao {

    private final EntityManager entityManager;

    @Autowired
    public AthleteCustomDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<String> findTopCountries(int topCount) {
        return entityManager
                .createQuery("SELECT a.country " +
                        "FROM athletes a " +
                        "GROUP BY a.country " +
                        "ORDER BY count(a.country) DESC", String.class)
                .setMaxResults(topCount)
                .getResultList();
    }

    public Double getThousandMetersAthleteAverageByAthleteId(int id) {
        return entityManager
                .createQuery("SELECT " +
                        "  avg(s.time / s.distance * 1000) " +
                        " FROM scores s " +
                        "  JOIN athletes a " +
                        "    ON  s.athlete = a.id "
                        + " WHERE s.athlete=" + id
                        + " GROUP BY s.athlete ", Double.class)
                .setMaxResults(1)
                .getSingleResult();
    }

    public List<Athlete> findAll() {
        return entityManager.createQuery("SELECT a " +
                " FROM athletes a", Athlete.class)
                .getResultList();
    }
}

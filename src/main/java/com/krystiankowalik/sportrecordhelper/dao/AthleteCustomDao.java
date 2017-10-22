package com.krystiankowalik.sportrecordhelper.dao;

import com.krystiankowalik.sportrecordhelper.model.Athlete;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Arrays;
import java.util.List;

@Repository
public class AthleteCustomDao {

    private final EntityManager entityManager;
    private final JpaRepository<Athlete, Integer> jpaRepository;

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    public AthleteCustomDao(EntityManager entityManager, JpaRepository<Athlete, Integer> jpaRepository) {
        this.entityManager = entityManager;
        this.jpaRepository = jpaRepository;
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
        try {
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
        } catch (NoResultException e) {
            logger.error(e.getMessage() + " id: " + id);
            logger.debug(Arrays.toString(e.getStackTrace()));
            return 0d;
        }
    }

    public List<Athlete> findAll() {
        return jpaRepository.findAll();
    }

    public Athlete save(Athlete athlete) {
        return jpaRepository.save(athlete);
    }

    public List<Athlete> save(List<Athlete> athletes) {
        return jpaRepository.save(athletes);
    }
}

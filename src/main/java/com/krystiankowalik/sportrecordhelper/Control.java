package com.krystiankowalik.sportrecordhelper;

import com.krystiankowalik.sportrecordhelper.dao.AthleteDao;
import com.krystiankowalik.sportrecordhelper.logic.io.FileHelper;
import com.krystiankowalik.sportrecordhelper.logic.parser.AthleteParser;
import com.krystiankowalik.sportrecordhelper.model.Athlete;
import com.krystiankowalik.sportrecordhelper.model.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class Control {


    private FileHelper fileHelper;
    private AthleteParser athleteParser;
    private AthleteDao athleteDao;

    @Autowired
    public Control(FileHelper fileHelper, AthleteParser athleteParser, AthleteDao athleteDao) {
        this.fileHelper = fileHelper;
        this.athleteParser = athleteParser;
        this.athleteDao = athleteDao;
    }

    public void run() {
        // Session session = sessionFactory.getCurrentSession();

        //session.save(new Athlete(0, "Jan Kowalski", "Polska", null));

        System.out.println(fileHelper);
        System.out.println(athleteParser);


        Athlete athlete = new Athlete("Jan Kowalski", "Polska", null);
        Score score1 = new Score( LocalDate.parse("2017-01-01"), 1500, BigDecimal.valueOf(150));
        Score score2 = new Score( LocalDate.parse("2017-01-02"), 15040, BigDecimal.valueOf(150));

        //athleteDao.save(athlete);

        score1.setAthlete(athlete);
        score2.setAthlete(athlete);

        List<Score> scores = new ArrayList<>(Arrays.asList(score1, score2));

        athlete.setRecords(scores);

       /* entityManager.getTransaction().begin();

        entityManager.persist(athlete);
        entityManager.flush();*/
        athleteDao.save(athlete);
        //      session.getTransaction().commit();

        //athleteDao.save(athlete);

//        athleteDao.save(athlete);
//        System.out.println(athlete);

        //      System.out.println(athleteDao.getOne(1));
//
        //session.save(athlete);

        //athlete.setRecords();

        /*athleteDao.save(new Athlete(0, "Jan Kowalski", "Polska",
                new ArrayList<>(Arrays.asList(
                        new Score(0, LocalDate.parse("2017-01-01"),1500, BigDecimal.valueOf(150)),
                        new Score(0, LocalDate.parse("2017-01-02"),15000, BigDecimal.valueOf(1500))))));*/

        //System.out.println(athleteDao.);

        //System.out.println(session.get(Athlete.class,1));;

        /*        session.beginTransaction();
        session.close();*/


    }
}

package com.krystiankowalik.sportrecordhelper;

import com.krystiankowalik.sportrecordhelper.dao.AthleteCustomDao;
import com.krystiankowalik.sportrecordhelper.dao.ScoreCustomDao;
import com.krystiankowalik.sportrecordhelper.logic.io.FileHelper;
import com.krystiankowalik.sportrecordhelper.logic.parser.athlete.AthleteParser;
import com.krystiankowalik.sportrecordhelper.logic.parser.batch.BatchAthleteParser;
import com.krystiankowalik.sportrecordhelper.model.Athlete;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.file.Paths;
import java.util.List;

@Service
@Transactional
public class Control {

    private FileHelper fileHelper;
    private AthleteParser athleteParser;
    private BatchAthleteParser batchAthleteParser;
    private ScoreCustomDao scoreCustomDao;
    private AthleteCustomDao athleteCustomDao;

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    public Control(FileHelper fileHelper, AthleteParser athleteParser, BatchAthleteParser batchAthleteParser, ScoreCustomDao scoreCustomDao, AthleteCustomDao athleteCustomDao) {
        this.fileHelper = fileHelper;
        this.athleteParser = athleteParser;
        this.batchAthleteParser = batchAthleteParser;
        this.scoreCustomDao = scoreCustomDao;
        this.athleteCustomDao = athleteCustomDao;
    }

    public void run() {

        List<Athlete> athletes = batchAthleteParser.parseAll(fileHelper.readAllLines(Paths.get("/home/wd40/Public/dane.txt")));
        athleteCustomDao.save(athletes);

        System.out.println(scoreCustomDao.findOldestDate());

        System.out.println(athleteCustomDao.findTopCountries(3));

        System.out.println(athleteCustomDao.getThousandMetersAthleteAverageByAthleteId(1));

        System.out.println("Top countries: " + athleteCustomDao.findTopCountries(3));

        System.out.println("All athletes: " + athleteCustomDao.findAll());

        List<Athlete> athleteList = athleteCustomDao.findAll();
        athleteList.forEach(a -> System.out.println(a.getName() + " time: " + athleteCustomDao.getThousandMetersAthleteAverageByAthleteId(a.getId())));

    }
}

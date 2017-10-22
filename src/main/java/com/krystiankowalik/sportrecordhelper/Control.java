package com.krystiankowalik.sportrecordhelper;

import com.krystiankowalik.sportrecordhelper.dao.AthleteCustomDao;
import com.krystiankowalik.sportrecordhelper.dao.AthleteDao;
import com.krystiankowalik.sportrecordhelper.dao.ScoreCustomDao;
import com.krystiankowalik.sportrecordhelper.dao.ScoreDao;
import com.krystiankowalik.sportrecordhelper.logic.io.FileHelper;
import com.krystiankowalik.sportrecordhelper.logic.parser.AthleteParser;
import com.krystiankowalik.sportrecordhelper.logic.parser.BatchAthleteParser;
import com.krystiankowalik.sportrecordhelper.model.Athlete;
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
    private AthleteDao athleteDao;
    private ScoreCustomDao scoreCustomDao;
    private ScoreDao scoreDao;
    private AthleteCustomDao athleteCustomDao;

    @Autowired
    public Control(FileHelper fileHelper, AthleteParser athleteParser, BatchAthleteParser batchAthleteParser, AthleteDao athleteDao, ScoreCustomDao scoreCustomDao, ScoreDao scoreDao, AthleteCustomDao athleteCustomDao) {
        this.fileHelper = fileHelper;
        this.athleteParser = athleteParser;
        this.batchAthleteParser = batchAthleteParser;
        this.athleteDao = athleteDao;
        this.scoreCustomDao = scoreCustomDao;
        this.scoreDao = scoreDao;
        this.athleteCustomDao = athleteCustomDao;
    }

    public void run() {

        List<Athlete> athletes = batchAthleteParser.parseAthletes(fileHelper.readAllLines(Paths.get("/home/wd40/Public/dane.txt")));
        athleteDao.save(athletes);

        System.out.println(scoreCustomDao.findOldestDate());

        System.out.println(athleteCustomDao.findTopCountries(3));

        System.out.println(athleteCustomDao.getThousandMetersAthleteAverageByAthleteId(1));

        System.out.println("Top countries: " + athleteCustomDao.findTopCountries(3));

        System.out.println("All athletes: " + athleteCustomDao.findAll());

        List<Athlete> athleteList = athleteCustomDao.findAll();
        athleteList.forEach(a -> System.out.println(a.getName()+ " time: " + athleteCustomDao.getThousandMetersAthleteAverageByAthleteId(a.getId())));
    }
}

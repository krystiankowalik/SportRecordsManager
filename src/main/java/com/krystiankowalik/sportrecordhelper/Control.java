package com.krystiankowalik.sportrecordhelper;

import com.krystiankowalik.sportrecordhelper.dao.AthleteDao;
import com.krystiankowalik.sportrecordhelper.dao.ScoreDao;
import com.krystiankowalik.sportrecordhelper.logic.io.FileHelper;
import com.krystiankowalik.sportrecordhelper.logic.parser.AthleteParser;
import com.krystiankowalik.sportrecordhelper.logic.parser.BatchAthleteParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.file.Paths;

@Service
@Transactional
public class Control {


    private FileHelper fileHelper;
    private AthleteParser athleteParser;
    private BatchAthleteParser batchAthleteParser;
    private AthleteDao athleteDao;
    private ScoreDao scoreDao;

    @Autowired
    public Control(FileHelper fileHelper, AthleteParser athleteParser, BatchAthleteParser batchAthleteParser, AthleteDao athleteDao, ScoreDao scoreDao) {
        this.fileHelper = fileHelper;
        this.athleteParser = athleteParser;
        this.batchAthleteParser = batchAthleteParser;
        this.athleteDao = athleteDao;
        this.scoreDao = scoreDao;
    }

    public void run() {

        athleteDao.deleteAll();
        scoreDao.deleteAll();

/*
        Athlete athlete = new Athlete("Jan Kowalski", "Polska", null);
        Score score1 = new Score( LocalDate.parse("2017-01-01"), 1500, BigDecimal.valueOf(150));
        Score score2 = new Score( LocalDate.parse("2017-01-02"), 15040, BigDecimal.valueOf(150));

        score1.setAthlete(athlete);
        score2.setAthlete(athlete);

        List<Score> scores = new ArrayList<>(Arrays.asList(score1, score2));

        athlete.setRecords(scores);

        athleteDao.save(athlete);
*/

        System.out.println(batchAthleteParser.parseAthletes(fileHelper.readAllLines(Paths.get("/home/wd40/Public/dane.txt"))));
        athleteDao.save(batchAthleteParser.parseAthletes(fileHelper.readAllLines(Paths.get("/home/wd40/Public/dane.txt"))));
    }
}

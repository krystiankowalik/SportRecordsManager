package com.krystiankowalik.sportrecordhelper;

import com.krystiankowalik.sportrecordhelper.dao.AthleteDao;
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

        List<Athlete> athletes = batchAthleteParser.parseAthletes(fileHelper.readAllLines(Paths.get("/home/wd40/Public/dane.txt")));
        athleteDao.save(athletes);

    }
}

package com.krystiankowalik.sportrecordhelper;

import com.krystiankowalik.sportrecordhelper.dao.AthleteCustomDao;
import com.krystiankowalik.sportrecordhelper.dao.ScoreCustomDao;
import com.krystiankowalik.sportrecordhelper.error.ErrorMessage;
import com.krystiankowalik.sportrecordhelper.logic.io.FileHelper;
import com.krystiankowalik.sportrecordhelper.logic.parser.batch.BatchAthleteParser;
import com.krystiankowalik.sportrecordhelper.model.Athlete;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.krystiankowalik.sportrecordhelper.error.ErrorMessage.INVALID_PARAM;
import static com.krystiankowalik.sportrecordhelper.error.ErrorMessage.WRONG_PARAM_COUNT;
import static com.krystiankowalik.sportrecordhelper.util.Constants.*;

@Service
@Transactional
public class Control {

    private final FileHelper fileHelper;
    private final BatchAthleteParser batchAthleteParser;
    private final ScoreCustomDao scoreCustomDao;
    private final AthleteCustomDao athleteCustomDao;

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    public Control(FileHelper fileHelper, BatchAthleteParser batchAthleteParser, ScoreCustomDao scoreCustomDao, AthleteCustomDao athleteCustomDao) {
        this.fileHelper = fileHelper;
        this.batchAthleteParser = batchAthleteParser;
        this.scoreCustomDao = scoreCustomDao;
        this.athleteCustomDao = athleteCustomDao;
    }

    public void run(String[] input) {

        if (input == null) {
            displayHelp();
        } else {
            int paramCount = input.length;

            switch (paramCount) {

                case 0:
                    handleWrongParametersCount(paramCount);
                    break;
                case 1:
                    handleSingleParameter(input[0]);
                    break;
                case 2:
                    handleTwoParameters(input[0], input[1]);
                    break;
                default:
                    handleWrongParametersCount(paramCount);
            }
        }
    }

    private void handleWrongParametersCount(int paramCount) {
        logger.error(WRONG_PARAM_COUNT + Integer.toString(paramCount) + "; expected >1");
        displayHelp();
    }

    private void handleSingleParameter(String parameter) {
        if (parameter.equals(HELP)) {
            displayHelp();
        } else {
            logger.error(INVALID_PARAM + parameter);
        }
    }

    private void handleTwoParameters(String option, String sourcePath) {
        switch (option) {
            case READ_FILE:
                if (isSuccessful(writeFileToDb(sourcePath))) {
                    readFromDb();
                }
                break;
            case READ_DIRECTORY:
                if (isSuccessful(writeDirectoryToDb(sourcePath))) {
                    readFromDb();
                }
                break;
            case HELP:
                logger.error(ErrorMessage.INVALID_PARAM + " used with " + HELP + " option");
                displayHelp();
                break;
            case HELP_VERBOSE:
                logger.error(ErrorMessage.INVALID_PARAM + " used with " + HELP + " option");
                displayHelp();
                break;
            default:
        }
    }

    private void readFromDb() {
        System.out.println("Reading from database...");
        System.out.print(System.lineSeparator());

        System.out.println("List of athletes: ");
        List<Athlete> athleteList = athleteCustomDao.findAll();
        athleteList.forEach(a ->
                System.out.println(a.getName() + " average 1000m time:  " + athleteCustomDao.getThousandMetersAthleteAverageByAthleteId(a.getId()) + " seconds"));
        System.out.print(System.lineSeparator());

        System.out.println("First score registered: " + scoreCustomDao.findOldestDate());
        System.out.print(System.lineSeparator());

        System.out.println("Top 3 countries: " + athleteCustomDao.findTopCountries(3));
        System.out.print(System.lineSeparator());

    }

    private boolean writeFileToDb(String path) {
        System.out.println("Reading File " + path);

        return !athleteCustomDao.save(batchAthleteParser.parseAll(fileHelper.readAllLines(Paths.get(path)))).isEmpty();

    }

    private boolean writeDirectoryToDb(String path) {
        System.out.println("Reading Directory..." + path);

        boolean success = false;

        for (Path file : fileHelper.getAllFilesInDirectory(Paths.get(path))) {
            if (!athleteCustomDao.save(batchAthleteParser.parseAll(fileHelper.readAllLines(file))).isEmpty()) {
                success = true;
            }
        }
        return success;
    }


    private boolean isSuccessful(boolean success) {
        return success;
    }

    private void displayHelp() {
        System.out.println(getHelpContents());
    }
}

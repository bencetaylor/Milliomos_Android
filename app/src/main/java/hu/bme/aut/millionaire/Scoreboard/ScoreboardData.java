package hu.bme.aut.millionaire.Scoreboard;

import com.orm.SugarRecord;

/**
 * Modell osztály az eredmények tárolásához
 */

public class ScoreboardData extends SugarRecord {

    public String playerName;
    public int scoredPoints;
}

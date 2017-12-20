package hu.bme.aut.millionaire;

public class Helper {
    public final static String TIMER = "Timer";
    public final static String TIMER_VALUE = "Value";

    public static int TIMER_ACTUAL_VALUE = 0;

    public static void setActualGameScore(int time) {
        TIMER_ACTUAL_VALUE = time;
    }
}
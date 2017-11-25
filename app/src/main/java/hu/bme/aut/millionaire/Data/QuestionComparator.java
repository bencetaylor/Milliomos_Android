package hu.bme.aut.millionaire.Data;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Comparator;

/**
 * Created by Bence on 2017. 11. 23..
 */

public class QuestionComparator implements Comparator {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int compare(Object o1, Object o2) {
        int s1 = ((Question) o1).getDifficulty();
        int s2 = ((Question) o2).getDifficulty();
        return Integer.compare(s1, s2);
    }
}

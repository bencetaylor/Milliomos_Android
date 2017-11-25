package hu.bme.aut.millionaire.Data;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Bence on 2017. 11. 23..
 */

public class QuestionNumberGenerator {
    public int getNumber(int questionCounter) {
        switch (questionCounter) {

            //TODO questionBorderNumber List for question borders
            /*case 0: return randomNumber(0, Main.questionBorderNumber.get(0));
            case 1: return randomNumber(Main.questionBorderNumber.get(0), Main.questionBorderNumber.get(1));
            case 2: return randomNumber(Main.questionBorderNumber.get(1), Main.questionBorderNumber.get(2));
            case 3: return randomNumber(Main.questionBorderNumber.get(2), Main.questionBorderNumber.get(3));
            case 4: return randomNumber(Main.questionBorderNumber.get(3), Main.questionBorderNumber.get(4));
            case 5: return randomNumber(Main.questionBorderNumber.get(4), Main.questionBorderNumber.get(5));
            case 6: return randomNumber(Main.questionBorderNumber.get(5), Main.questionBorderNumber.get(6));
            case 7: return randomNumber(Main.questionBorderNumber.get(6), Main.questionBorderNumber.get(7));
            case 8: return randomNumber(Main.questionBorderNumber.get(7), Main.questionBorderNumber.get(8));
            case 9: return randomNumber(Main.questionBorderNumber.get(8), Main.questionBorderNumber.get(9));
            case 10: return randomNumber(Main.questionBorderNumber.get(9), Main.questionBorderNumber.get(10));
            case 11: return randomNumber(Main.questionBorderNumber.get(10), Main.questionBorderNumber.get(11));
            case 12: return randomNumber(Main.questionBorderNumber.get(11), Main.questionBorderNumber.get(12));
            case 13: return randomNumber(Main.questionBorderNumber.get(12), Main.questionBorderNumber.get(13));
            case 14: return randomNumber(Main.questionBorderNumber.get(13), Main.questionBorderNumber.get(14));*/
            default:
                return 0;
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static int randomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}

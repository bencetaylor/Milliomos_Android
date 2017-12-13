package hu.bme.aut.millionaire.Data;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

import hu.bme.aut.millionaire.R;

/**
 * Created by Bence on 2017. 11. 23..
 */

public class QuestionManager {

    private ArrayList<Integer> questionBorderNumber;
    private ArrayList<Question> questionArray;

    public ArrayList<Question> currentQuestions;


    public QuestionManager(Context context) throws IOException, ClassNotFoundException {

        load(context);

        sortList();
        initQuestionBorders();

        currentQuestions = new ArrayList<>();

        //TODO 15 kérdés feltöltése
        for(int i=0; i<10; i++){
            currentQuestions.add(questionArray.get(getNumber(i)));
        }
    }

    /**
     * Beolvassa a kérdéseket egy listába a bemenetként kapott fájlból
     * @param context		bemeneti fájl neve
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void load(Context context) throws IOException, ClassNotFoundException{

        InputStream is = context.getResources().openRawResource(R.raw.loim);

        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        try{
            questionArray = new ArrayList<>();
            while(true){
                String line = br.readLine();
                if(line == null)
                    break;
                questionArray.add(new Question(line));
            }
            br.close();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

    /**
     * Rendezi a kérdéseket tároló listát
     */
    public void sortList(){
        Comparator comparator = new QuestionComparator();
        questionArray.sort(comparator);
    }

    /**
     * Meghatározza a különböző nehézségű kérdések határait.
     * Így minden szinten a szintnek megfelelő nehézségű kérdést tudunk választani.
     */
    public void initQuestionBorders(){
        int db=0;
        questionBorderNumber = new ArrayList<>();
        for(int i = 1; i != 15; i++){
            while(questionArray.get(db).getDifficulty() == i){
                db++;
            }
            questionBorderNumber.add(db);
        }
    }

    public int getNumber(int questionCounter) {
        switch (questionCounter) {
            case 0: return randomNumber(0, questionBorderNumber.get(0));
            case 1: return randomNumber(questionBorderNumber.get(0), questionBorderNumber.get(1));
            case 2: return randomNumber(questionBorderNumber.get(1), questionBorderNumber.get(2));
            case 3: return randomNumber(questionBorderNumber.get(2), questionBorderNumber.get(3));
            case 4: return randomNumber(questionBorderNumber.get(3), questionBorderNumber.get(4));
            case 5: return randomNumber(questionBorderNumber.get(4), questionBorderNumber.get(5));
            case 6: return randomNumber(questionBorderNumber.get(5), questionBorderNumber.get(6));
            case 7: return randomNumber(questionBorderNumber.get(6), questionBorderNumber.get(7));
            case 8: return randomNumber(questionBorderNumber.get(7), questionBorderNumber.get(8));
            case 9: return randomNumber(questionBorderNumber.get(8), questionBorderNumber.get(9));
            case 10: return randomNumber(questionBorderNumber.get(9), questionBorderNumber.get(10));
            case 11: return randomNumber(questionBorderNumber.get(10), questionBorderNumber.get(11));
            case 12: return randomNumber(questionBorderNumber.get(11), questionBorderNumber.get(12));
            case 13: return randomNumber(questionBorderNumber.get(12), questionBorderNumber.get(13));
            case 14: return randomNumber(questionBorderNumber.get(13), questionBorderNumber.get(14));
            default:
                return 0;
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static int randomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}

package hu.bme.aut.millionaire.Data;

import java.io.IOException;

/**
 * Created by Bence on 2017. 11. 23..
 */

public class QuestionManager {

    /**
     * Beolvassa a kérdéseket egy listába a bemenetként kapott fájlból
     * @param input		bemeneti fájl neve
     * @throws IOException
     * @throws ClassNotFoundException
     */
    /*
    public static void load(String input) throws IOException, ClassNotFoundException{
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(input)));
        try{
            Main.questionArray = new ArrayList<Question>();
            while(true){
                String line = br.readLine();
                if(line == null)
                    break;
                Main.questionArray.add(new Question(line));
            }
            br.close();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }*/

    /**
     * Rendezi a kérdéseket tároló listát
     */
    /*
    public static void sortList(){
        Main.questionArray.sort(new QuestionComparator());
    }*/

    /**
     * Meghatározza a különböző nehézségű kérdések határait.
     * Így minden szinten a szintnek megfelelő nehézségű kérdést tudunk választani.
     */
    /*
    public static void initQuestionBorders(){
        int db=0;
        Main.questionBorderNumber = new ArrayList<>();
        for(int i = 1; i != 15; i++){
            while(Main.questionArray.get(db).getDifficulty() == i){
                db++;
            }
            Main.questionBorderNumber.add(db);
        }
    }*/
}

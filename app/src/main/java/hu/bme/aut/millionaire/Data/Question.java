package hu.bme.aut.millionaire.Data;

/**
 * Created by Bence on 2017. 11. 23..
 */

public class Question {
    String question;
    private int difficulty;
    String A;
    String B;
    String C;
    String D;
    String corr;

    /**
     * A kérdés osztály konstruktora.
     * A kapott Stringet "feldarabolja" és beállítja a kérdés részeit.
     * @param S		a kérdés és a válaszok egy String-ként
     */
    public Question(String S){
        String[] line = S.split(";");
        int lenght = line.length;
        difficulty = Integer.parseInt(line[lenght-7]);
        question = line[lenght-6];
        A = line[lenght-5];
        B = line[lenght-4];
        C = line[lenght-3];
        D = line[lenght-2];
        corr = line[lenght-1];
    }

    /**
     * Visszaadja a kérdés nehézségét
     * @return	kérdés nehézsége
     */
    public int getDifficulty(){
        return difficulty;
    }
}

package hu.bme.aut.millionaire.Game;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;

import hu.bme.aut.millionaire.Data.QuestionManager;
import hu.bme.aut.millionaire.R;

public class GameActivity extends AppCompatActivity {

    private Button answerA;
    private Button answerB;
    private Button answerC;
    private Button answerD;
    private ImageButton audienceHelp;
    private ImageButton phoneHelp;
    private ImageButton fiftyHelp;
    private TextView questionText;
    private QuestionManager questionManager;
    private TextView timerText;
    private CountDownTimer timer;
    private int score;
    private int timeLeft = 0;

    private static int questionCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Context context = getApplicationContext();

        try {
            questionManager = new QuestionManager(context);
        } catch (IOException e) {
            System.err.print("FileNotFound");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        score = 0;

        questionText = (TextView) findViewById(R.id.questionText);
        questionText.setText(questionManager.currentQuestions.get(questionCounter).question);

        answerA = (Button) findViewById(R.id.answerA);
        answerA.setText(questionManager.currentQuestions.get(questionCounter).A);
        answerA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view){
               if(answerCheck("A"))
                   loadNextQuestion();
               else
                   gameWon();
            }
        });
        answerB = (Button) findViewById(R.id.answerB);
        answerB.setText(questionManager.currentQuestions.get(questionCounter).B);
        answerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view){
                if(answerCheck("B"))
                    loadNextQuestion();
                else
                    gameWon();
            }
        });
        answerC = (Button) findViewById(R.id.answerC);
        answerC.setText(questionManager.currentQuestions.get(questionCounter).C);
        answerC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view){
                if(answerCheck("C"))
                    loadNextQuestion();
                else
                    gameWon();
            }
        });
        answerD = (Button) findViewById(R.id.answerD);
        answerD.setText(questionManager.currentQuestions.get(questionCounter).D);
        answerD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view){
                if(answerCheck("D"))
                    loadNextQuestion();
                else
                    gameWon();
            }
        });

        audienceHelp = (ImageButton) findViewById(R.id.btn_audience);
        audienceHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view){
                if(audienceHelp.isEnabled()){
                    AudienceHelpFragment audienceHelpFragment = AudienceHelpFragment.newInstance(questionManager.currentQuestions.get(questionCounter).corr);
                    audienceHelpFragment.show(getSupportFragmentManager(), AudienceHelpFragment.TAG);

                    audienceHelp.setEnabled(false);
                    audienceHelp.setImageResource(R.drawable.rsz_audience_dis);
                }
            }
        });
        phoneHelp = (ImageButton) findViewById(R.id.btn_phone);
        phoneHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view){
                if(phoneHelp.isEnabled()){
                    new PhoneHelpFragment().show(getSupportFragmentManager(), PhoneHelpFragment.TAG);
                    phoneHelp.setEnabled(false);
                    phoneHelp.setImageResource(R.drawable.rsz_phone_dis);
                }
            }
        });
        fiftyHelp = (ImageButton) findViewById(R.id.btn_fifty);
        fiftyHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view){
                fiftyHelp();
                fiftyHelp.setEnabled(false);
                fiftyHelp.setImageResource(R.drawable.rsz_fifty_dis);
            }
        });

        timerText = (TextView) findViewById(R.id.timer_text);
        timer = newTimer(30000);
        timer.start();
    }

    public boolean answerCheck(String answer){
        if(answer.equals(questionManager.currentQuestions.get(questionCounter).corr))
            return true;
        else
            return false;
    }

    public void loadNextQuestion(){
        questionCounter++;
        if(questionCounter ==15){
            gameWon();
        }
        else{
            questionText.setText(questionManager.currentQuestions.get(questionCounter).question);
            answerA.setText(questionManager.currentQuestions.get(questionCounter).A);
            answerA.setEnabled(true);
            answerB.setText(questionManager.currentQuestions.get(questionCounter).B);
            answerB.setEnabled(true);
            answerC.setText(questionManager.currentQuestions.get(questionCounter).C);
            answerC.setEnabled(true);
            answerD.setText(questionManager.currentQuestions.get(questionCounter).D);
            answerD.setEnabled(true);
            calculateScore();
            if(timer != null)
                timer.cancel();
            timer = newTimer(30000);
            timer.start();
        }
    }

    private void calculateScore(){
        score += timeLeft*(questionCounter+1);
    }

    public void  gameWon(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        GameWonFragment gameWonFragment = new GameWonFragment();
        fragmentTransaction.add(R.id.fragment_container, gameWonFragment, "HELLO");
        fragmentTransaction.commit();
        timer.cancel();
    }

    public void gameOver(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        GameLostFragment gameLostFragment = new GameLostFragment();
        fragmentTransaction.add(R.id.fragment_container, gameLostFragment, "HELLO");
        fragmentTransaction.commit();
        timer.cancel();
    }

    public CountDownTimer newTimer(int startTime){
        return new CountDownTimer(startTime, 1000) {

            public void onTick(long millisUntilFinished) {
                String s = Long.toString((millisUntilFinished + 1000)/1000);
                timeLeft = (int)((millisUntilFinished + 1000)/1000);
                timerText.setText(s);

            }

            public void onFinish() {
                timerText.setText("Game Over!");
                gameOver();
            }
        };
    }

    private void fiftyHelp(){
        if(questionManager.currentQuestions.get(questionCounter).corr.equals("A")) {
            answerB.setEnabled(false);
            answerC.setEnabled(false);
        }
        if(questionManager.currentQuestions.get(questionCounter).corr.equals("B")) {
            answerA.setEnabled(false);
            answerD.setEnabled(false);
        }
        if(questionManager.currentQuestions.get(questionCounter).corr.equals("C")) {
            answerB.setEnabled(false);
            answerD.setEnabled(false);
        }
        if(questionManager.currentQuestions.get(questionCounter).corr.equals("D")) {
            answerA.setEnabled(false);
            answerC.setEnabled(false);
        }
    }
}

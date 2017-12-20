package hu.bme.aut.millionaire.Game;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.orm.SugarContext;

import java.io.IOException;

import hu.bme.aut.millionaire.Data.QuestionManager;
import hu.bme.aut.millionaire.Helper;
import hu.bme.aut.millionaire.R;

public class PlayGameFragment extends android.app.Fragment {

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
    private int timerClock;
    private int score;
    private int timeLeft = 0;

    private static int questionCounter = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SugarContext.init(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_play_game,
                container, false);

        Context context = getActivity().getApplicationContext();

        try {
            questionManager = new QuestionManager(context);
        } catch (IOException e) {
            System.err.print("FileNotFound");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        score = 0;

        questionText = (TextView) view.findViewById(R.id.questionText);
        questionText.setText(questionManager.currentQuestions.get(questionCounter).question);

        answerA = (Button) view.findViewById(R.id.answerA);
        answerA.setText(questionManager.currentQuestions.get(questionCounter).A);
        answerA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view){
                if(answerCheck("A"))
                    loadNextQuestion();
                else
                    gameOver();
            }
        });
        answerB = (Button) view.findViewById(R.id.answerB);
        answerB.setText(questionManager.currentQuestions.get(questionCounter).B);
        answerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view){
                if(answerCheck("B"))
                    loadNextQuestion();
                else
                    gameOver();
            }
        });
        answerC = (Button) view.findViewById(R.id.answerC);
        answerC.setText(questionManager.currentQuestions.get(questionCounter).C);
        answerC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view){
                if(answerCheck("C"))
                    loadNextQuestion();
                else
                    gameOver();
            }
        });
        answerD = (Button) view.findViewById(R.id.answerD);
        answerD.setText(questionManager.currentQuestions.get(questionCounter).D);
        answerD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view){
                if(answerCheck("D"))
                    loadNextQuestion();
                else
                    gameOver();
            }
        });

        audienceHelp = (ImageButton) view.findViewById(R.id.btn_audience);
        audienceHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view){
                if(audienceHelp.isEnabled()){
                    AudienceHelpFragment audienceHelpFragment = AudienceHelpFragment.newInstance(questionManager.currentQuestions.get(questionCounter).corr);
                    audienceHelpFragment.show(getFragmentManager(), "dialog");

                    audienceHelp.setEnabled(false);
                    audienceHelp.setImageResource(R.drawable.rsz_audience_dis);
                }
            }
        });
        phoneHelp = (ImageButton) view.findViewById(R.id.btn_phone);
        phoneHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view){
                if(phoneHelp.isEnabled()){
                    timer.cancel();
                    PhoneHelpFragment phoneHelpFragment = new PhoneHelpFragment();
                    phoneHelpFragment.show(getFragmentManager(), "dialog");
                    phoneHelp.setEnabled(false);
                    phoneHelp.setImageResource(R.drawable.rsz_phone_dis);
                }
            }
        });
        fiftyHelp = (ImageButton) view.findViewById(R.id.btn_fifty);
        fiftyHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view){
                fiftyHelp();
                fiftyHelp.setEnabled(false);
                fiftyHelp.setImageResource(R.drawable.rsz_fifty_dis);
            }
        });

        SharedPreferences game = getActivity().getSharedPreferences(Helper.TIMER, 0);
        Helper.setActualGameScore(game.getInt(Helper.TIMER_VALUE, 0));

        timerClock = Helper.TIMER_ACTUAL_VALUE;
        // A timer default értéke ha nem állítanánk be
        if(timerClock == 0)
            timerClock=30;

        timerClock *= 1000;

        timerText = (TextView) view.findViewById(R.id.timer_text);
        timer = newTimer(timerClock);
        timer.start();

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        if(timer != null)
            timer.start();
    }

    @Override
    public void onPause(){
        super.onPause();
        if(timer != null)
            timer.cancel();
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
            answerA.setText("A: " + questionManager.currentQuestions.get(questionCounter).A);
            answerA.setEnabled(true);
            answerB.setText("B: " + questionManager.currentQuestions.get(questionCounter).B);
            answerB.setEnabled(true);
            answerC.setText("C: " + questionManager.currentQuestions.get(questionCounter).C);
            answerC.setEnabled(true);
            answerD.setText("D: " + questionManager.currentQuestions.get(questionCounter).D);
            answerD.setEnabled(true);
            calculateScore();
            if(timer != null)
                timer.cancel();
            timer = newTimer(timerClock);
            timer.start();
        }
    }

    private void calculateScore(){
        score += timeLeft*(questionCounter+1);
    }

    public void  gameWon(){
        Bundle bundle = new Bundle();
        bundle.putInt("score", score);
        ((GameActivity)getActivity()).switchToWon(bundle);

        timer.cancel();
    }

    public void gameOver(){
        Bundle bundle = new Bundle();
        bundle.putInt("score", score);

        ((GameActivity)getActivity()).swiwtchToGameOver(bundle);

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

package hu.bme.aut.millionaire.Game;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.orm.SugarContext;

import java.io.IOException;

import hu.bme.aut.millionaire.Data.QuestionManager;
import hu.bme.aut.millionaire.R;

/**
 * Created by Bence on 2017. 11. 22..
 */

public class PlayGameFragment extends Fragment {

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
                    gameWon();
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
                    gameWon();
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
                    gameWon();
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
                    gameWon();
            }
        });

        audienceHelp = (ImageButton) view.findViewById(R.id.btn_audience);
        audienceHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view){
                if(audienceHelp.isEnabled()){
                    AudienceHelpFragment audienceHelpFragment = AudienceHelpFragment.newInstance(questionManager.currentQuestions.get(questionCounter).corr);
                    audienceHelpFragment.show(getActivity().getSupportFragmentManager(), AudienceHelpFragment.TAG);

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
                    new PhoneHelpFragment().show(getActivity().getSupportFragmentManager(), PhoneHelpFragment.TAG);
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

        timerText = (TextView) view.findViewById(R.id.timer_text);
        timer = newTimer(30000);
        timer.start();

        return view;
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
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putInt("score", score);
        GameWonFragment gameWonFragment = new GameWonFragment();
        gameWonFragment.setArguments(bundle);

        fragmentTransaction.add(R.id.fragment_container, gameWonFragment, "HELLO");
        fragmentTransaction.commit();
        timer.cancel();
    }

    public void gameOver(){
        FragmentManager fragmentManager = getFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putInt("score", score);
        GameLostFragment gameLostFragment = new GameLostFragment();
        gameLostFragment.setArguments(bundle);

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

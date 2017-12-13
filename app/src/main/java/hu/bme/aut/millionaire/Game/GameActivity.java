package hu.bme.aut.millionaire.Game;

import android.content.Context;
import android.os.Bundle;
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

    private static int questionCounter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);



        audienceHelp = (ImageButton) findViewById(R.id.btn_audience);
        audienceHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view){
                if(audienceHelp.isEnabled()){
                    new AudienceHelpFragment().show(getSupportFragmentManager(), AudienceHelpFragment.TAG);
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
                //TODO randomly disable 2 wrong answer button
                fiftyHelp();
                fiftyHelp.setEnabled(false);
                fiftyHelp.setImageResource(R.drawable.rsz_fifty_dis);
            }
        });

        Context context = getApplicationContext();

        try {
            questionManager = new QuestionManager(context);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        questionText = (TextView) findViewById(R.id.questionText);
        questionText.setText(questionManager.currentQuestions.get(questionCounter).question);

        answerA = (Button) findViewById(R.id.answerA);
        answerA.setText(questionManager.currentQuestions.get(0).A);

        answerB = (Button) findViewById(R.id.answerB);
        answerB.setText(questionManager.currentQuestions.get(0).B);

        answerC = (Button) findViewById(R.id.answerC);
        answerC.setText(questionManager.currentQuestions.get(0).C);

        answerD = (Button) findViewById(R.id.answerD);
        answerD.setText(questionManager.currentQuestions.get(0).D);




    }

    private void fiftyHelp(){
        answerA.setEnabled(false);
        answerC.setEnabled(false);
    }
}

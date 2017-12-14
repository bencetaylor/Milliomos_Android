package hu.bme.aut.millionaire;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import hu.bme.aut.millionaire.Game.AudienceHelpFragment;
import hu.bme.aut.millionaire.Game.GameActivity;
import hu.bme.aut.millionaire.Game.PhoneHelpFragment;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button newGame = (Button) findViewById(R.id.btn_newGame);
        Button scoreboard = (Button) findViewById(R.id.btn_scoreboard);
        Button settings = (Button) findViewById(R.id.btn_settings);
        Button exit = (Button) findViewById(R.id.btn_exit);

        final Intent newgameIntent = new Intent(this, GameActivity.class);
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view){
                startActivity(newgameIntent);
            }
        });

        final Intent scoreboardIntent = new Intent(this, ScoreboardActivity.class);
        scoreboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view){
                startActivity(scoreboardIntent);
            }
        });

        final Intent settingsIntent = new Intent(this, SettingsActivity.class);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view){
                startActivity(settingsIntent);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view){
                finish();
            }
        });

    }
}

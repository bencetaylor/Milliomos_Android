package hu.bme.aut.millionaire;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Button newGame;
    private Button continueGame;
    private Button scoreboard;
    private Button settings;
    private Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        newGame = (Button) findViewById(R.id.btn_newGame);
        continueGame = (Button) findViewById(R.id.btn_continue);
        scoreboard = (Button) findViewById(R.id.btn_scoreboard);
        settings = (Button) findViewById(R.id.btn_settings);
        exit = (Button) findViewById(R.id.btn_exit);

        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view){
                //TODO - new game activity (intent)
            }
        });

        continueGame.setEnabled(false);
        continueGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view){
                //TODO - scoreboard activity (intent)
            }
        });

        scoreboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view){
                //TODO - scoreboard activity (intent)
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view){
                //TODO - settings activity (intent)
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

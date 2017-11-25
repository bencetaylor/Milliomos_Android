package hu.bme.aut.millionaire;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ScoreboardActivity extends AppCompatActivity {

    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        final Intent intent = new Intent(this, MenuActivity.class);

        back = (Button) findViewById(R.id.btn_scoreboard_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view){
                startActivity(intent);
            }
        });
    }
}

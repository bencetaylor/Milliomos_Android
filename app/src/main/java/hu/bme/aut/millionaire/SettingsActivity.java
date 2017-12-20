package hu.bme.aut.millionaire;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.orm.SugarContext;

import hu.bme.aut.millionaire.Scoreboard.ScoreboardAdapter;
import hu.bme.aut.millionaire.Scoreboard.ScoreboardData;

public class SettingsActivity extends AppCompatActivity {

    private Button btn_back;
    private Button reset;
    private Spinner spinner;

    public final static String TIMER="TIMER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SugarContext.init(this);

        btn_back = (Button) findViewById(R.id.btn_settings_back);
        reset = (Button) findViewById(R.id.btn_reset_scoreboard);

        spinner = (Spinner) findViewById(R.id.spinner);
        Integer[] items = new Integer[]{20,30,40,50,60};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,R.layout.spinner_item, items);
        spinner.setAdapter(adapter);

        final Intent intent = new Intent(this, MenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                startActivity(intent);
                saveTime((int)spinner.getSelectedItem());
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                ScoreboardData.deleteAll(ScoreboardData.class);
                Toast.makeText(getApplicationContext(), "Eredmények törölve!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void saveTime(int time) {
        SharedPreferences settings = getSharedPreferences(Helper.TIMER, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(Helper.TIMER_VALUE, time);
        editor.apply();
    }
}

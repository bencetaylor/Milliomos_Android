package hu.bme.aut.millionaire;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SettingsActivity extends AppCompatActivity {

    private Button btn_back;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        btn_back = (Button) findViewById(R.id.btn_settings_back);

        spinner=(Spinner) findViewById(R.id.spinner);
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
            }
        });
    }
}

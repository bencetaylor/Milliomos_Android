package hu.bme.aut.millionaire;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import java.util.List;

import hu.bme.aut.millionaire.Scoreboard.ScoreboardAdapter;
import hu.bme.aut.millionaire.Scoreboard.ScoreboardData;

public class ScoreboardActivity extends AppCompatActivity {

    private Button back;
    private RecyclerView recyclerView;
    private ScoreboardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        /*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
        initRecyclerView();

        final Intent intent = new Intent(this, MenuActivity.class);

        /*back = (Button) findViewById(R.id.btn_scoreboard_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view){
                startActivity(intent);
            }
        });*/
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.ScoreboardRecyclerView);
        adapter = new ScoreboardAdapter();
        loadItemsInBackground();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void loadItemsInBackground() {
        new AsyncTask<Void, Void, List<ScoreboardData>>() {

            @Override
            protected List<ScoreboardData> doInBackground(Void... voids) {
                return ScoreboardData.listAll(ScoreboardData.class);
            }

            @Override
            protected void onPostExecute(List<ScoreboardData> scoreboardItems) {
                super.onPostExecute(scoreboardItems);
                adapter.update(scoreboardItems);
            }
        }.execute();
    }
}

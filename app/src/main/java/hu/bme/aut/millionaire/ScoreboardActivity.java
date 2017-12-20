package hu.bme.aut.millionaire;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.orm.SugarContext;

import java.util.List;

import hu.bme.aut.millionaire.Scoreboard.ScoreboardAdapter;
import hu.bme.aut.millionaire.Scoreboard.ScoreboardData;

public class ScoreboardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ScoreboardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        SugarContext.init(this);

        initRecyclerView();

        final Intent intent = new Intent(this, MenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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

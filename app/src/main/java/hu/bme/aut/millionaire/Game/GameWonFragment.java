package hu.bme.aut.millionaire.Game;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.orm.SugarContext;

import hu.bme.aut.millionaire.MenuActivity;
import hu.bme.aut.millionaire.R;
import hu.bme.aut.millionaire.Scoreboard.ScoreboardData;

/**
 * Created by Bence on 2017. 11. 22..
 */

public class GameWonFragment extends android.app.Fragment {
    private Button back;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SugarContext.init(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_won_game,
                container, false);
        back = (Button) view.findViewById(R.id.btn_won_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view){
                ScoreboardData data = new ScoreboardData();
                data.playerName="Bence";
                data.scoredPoints=12000;
                data.save();

                Intent intent = new Intent(getActivity(), MenuActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        return view;
    }
}

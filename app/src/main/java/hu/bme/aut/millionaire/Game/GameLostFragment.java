package hu.bme.aut.millionaire.Game;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hu.bme.aut.millionaire.R;

/**
 * Created by Bence on 2017. 11. 22..
 */

public class GameLostFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lost_game,
                container, false);
        /*tvMain = (TextView) view.findViewById(R.id.tvMain);
        tvDescription = (TextView) view.findViewById(
                R.id.tvDescription);
        ivIcon = (ImageView) view.findViewById(R.id.ivIcon);
        if (weatherDataHolder.getWeatherData() != null) {
            displayWeatherData();
        }*/
        return view;
    }
}

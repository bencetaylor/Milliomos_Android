package hu.bme.aut.millionaire.Game;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import hu.bme.aut.millionaire.R;

public class GameActivity extends FragmentActivity {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PlayGameFragment playGameFragment = new PlayGameFragment();
        fragmentTransaction.add(R.id.fragment_container, playGameFragment, "HELLO");
        fragmentTransaction.commit();

    }

    public void swiwtchToGameOver(Bundle bundle){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        GameLostFragment gameLostFragment = new GameLostFragment();
        gameLostFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragment_container, gameLostFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void switchToWon(Bundle bundle){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        GameWonFragment gameWonFragment = new GameWonFragment();
        gameWonFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragment_container, gameWonFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}

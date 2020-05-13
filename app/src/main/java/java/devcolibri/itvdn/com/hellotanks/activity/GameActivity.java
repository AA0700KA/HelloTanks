package java.devcolibri.itvdn.com.hellotanks.activity;

import android.app.Activity;
import android.os.Bundle;

import java.devcolibri.itvdn.com.hellotanks.views.GameView;


public class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int indexMap = getIntent().getIntExtra("mapIndex", 0);
        GameView gameView = new GameView(this, indexMap);
        setContentView(gameView);
    }

}

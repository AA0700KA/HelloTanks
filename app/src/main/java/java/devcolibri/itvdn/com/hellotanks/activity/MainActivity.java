package java.devcolibri.itvdn.com.hellotanks.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import java.devcolibri.itvdn.com.hellotanks.R;
import java.devcolibri.itvdn.com.hellotanks.util.BattleFIeld;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardView newGame = findViewById(R.id.new_game);
        CardView randomGame = findViewById(R.id.random_game);
        CardView createMap = findViewById(R.id.create_map);
        CardView scores = findViewById(R.id.scores);
        CardView exit = findViewById(R.id.exit);
        newGame.setOnClickListener(this);
        randomGame.setOnClickListener(this);
        createMap.setOnClickListener(this);
        scores.setOnClickListener(this);
        exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.new_game :
                intent = new Intent(this, ChoseMapActivity.class);
                break;
            case R.id.create_map :
                intent = new Intent(this, CreateMapActivity.class);
                break;
            case R.id.random_game :
                intent = new Intent(this, GameActivity.class);
                intent.putExtra("mapIndex", BattleFIeld.getMaps().size());
                BattleFIeld.initialRandomMap(this);
            case R.id.exit :
                finish();
        }

        if (intent != null)
        startActivity(intent);
    }

}

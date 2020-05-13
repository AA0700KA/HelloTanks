package java.devcolibri.itvdn.com.hellotanks.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.devcolibri.itvdn.com.hellotanks.R;
import java.devcolibri.itvdn.com.hellotanks.adapter.GameMapAdapter;
import java.devcolibri.itvdn.com.hellotanks.pojo.AbstractObjects;
import java.devcolibri.itvdn.com.hellotanks.pojo.Brick;
import java.devcolibri.itvdn.com.hellotanks.pojo.GameMap;
import java.devcolibri.itvdn.com.hellotanks.pojo.Shtab;
import java.devcolibri.itvdn.com.hellotanks.util.BattleFIeld;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ChoseMapActivity extends Activity implements AdapterView.OnItemClickListener {

    private List<GameMap> maps;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chose_map_layout);
        maps = BattleFIeld.getMaps();
        initStandartMap();
        ListView listView = findViewById(R.id.map_list);
        LayoutInflater inflater = getLayoutInflater();
        GameMapAdapter adapter = new GameMapAdapter(maps, inflater);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, "index = " + i, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("mapIndex", i);
        startActivity(intent);
    }

    private void initStandartMap() {
        GameMap map = new GameMap();
        map.setName("Standart");

        List<AbstractObjects> list = new CopyOnWriteArrayList<>();
        DisplayMetrics metrics = getResources().getDisplayMetrics();


        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        Log.d("metrics",String.valueOf(width));
        Log.d("metrics", String.valueOf(height));
        int quadrantX = width/40;
        int quadrantY = height/40;

        for (int i = 0; i < quadrantY; i++) {
            for (int j = 0; j < quadrantX; j++) {
                if (j%3 == 0) {
                    list.add(new Brick(metrics,i*40, j*40,this));
                }
            }
        }

        Shtab shtab = new Shtab(this, metrics);
        shtab.setX(500);
        shtab.setY(500);

        list.add(shtab);

        map.setObjects(list);
        BattleFIeld.addMap(map);
    }

}

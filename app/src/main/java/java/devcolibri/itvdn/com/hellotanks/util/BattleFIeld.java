package java.devcolibri.itvdn.com.hellotanks.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;

import java.devcolibri.itvdn.com.hellotanks.pojo.AbstractObjects;
import java.devcolibri.itvdn.com.hellotanks.pojo.Brick;
import java.devcolibri.itvdn.com.hellotanks.pojo.GameMap;
import java.devcolibri.itvdn.com.hellotanks.pojo.Rock;
import java.devcolibri.itvdn.com.hellotanks.pojo.Shtab;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class BattleFIeld {

    private static List<GameMap> maps = new ArrayList<>();

    public static void addMap(GameMap map) {
        maps.add(map);
    }

    public static List<GameMap> getMaps() {
        return maps;
    }

    public static void initialRandomMap(Context context) {
//        GameMap map = new GameMap();
//        map.setName("Standart");
//
//        List<AbstractObjects> list = new CopyOnWriteArrayList<>();
//        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
//
//
//        int width = metrics.widthPixels;
//        int height = metrics.heightPixels;
//        Log.d("metrics",String.valueOf(width));
//        Log.d("metrics", String.valueOf(height));
//        int quadrantX = width/40;
//        int quadrantY = height/40;
//
//        Random random = new Random();
//
//        for (int i = 0; i < quadrantY; i++) {
//            for (int j = 0; j < quadrantX; j++) {
//
//                int value = random.nextInt(4) + 1;
//                if (value == 1) {
//                    list.add(new Brick(metrics,i*40, j*40,context));
//                } else if (value == 2) {
//                    Rock rock = new Rock(context, metrics);
//                    rock.setY(i*40);
//                    rock.setX(j*40);
//                    list.add(rock);
//                }
//            }
//        }
//
//        Shtab shtab = new Shtab(context, metrics);
//        shtab.setX(random.nextInt(width - 200));
//        shtab.setY(random.nextInt(height - 200));
//
//        list.add(shtab);
//
//        map.setObjects(list);
//        BattleFIeld.addMap(map);
    }

}
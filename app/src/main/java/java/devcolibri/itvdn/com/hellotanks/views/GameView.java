package java.devcolibri.itvdn.com.hellotanks.views;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


import java.devcolibri.itvdn.com.hellotanks.pojo.MovableObject;
import java.devcolibri.itvdn.com.hellotanks.pojo.StopableObject;
import java.devcolibri.itvdn.com.hellotanks.thread.GameThread;
import java.devcolibri.itvdn.com.hellotanks.pojo.AbstractObjects;
import java.devcolibri.itvdn.com.hellotanks.pojo.AttackTank;
import java.devcolibri.itvdn.com.hellotanks.pojo.DefenderTank;
import java.devcolibri.itvdn.com.hellotanks.pojo.GameMap;
import java.devcolibri.itvdn.com.hellotanks.pojo.Tank;
import java.devcolibri.itvdn.com.hellotanks.pojo.TouchPad;
import java.devcolibri.itvdn.com.hellotanks.util.BattleFIeld;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread thread;
    private Context context;
    private int indexMap;
    private Tank playTank;
    private List<TouchPad> touchPads;
    private GameMap map;


    private List<MovableObject> list;

    public GameView(Context context, int indexMap) {
        super(context);
        this.context = context;
        this.indexMap = indexMap;
        getHolder().addCallback(this);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        Tank tank = new AttackTank(metrics, context);
        tank.setX(200);
        tank.setY(600);

        playTank = tank;
        playTank.setPlay(true);

        touchPads = new ArrayList<>();

        Tank tankg = new DefenderTank(metrics, context);
        tankg.setX(300);
        tankg.setY(300);

        TouchPad left = new TouchPad(metrics.heightPixels - 400, metrics.widthPixels/2 - 500, "Left", context, metrics, 3);
        TouchPad down = new TouchPad(metrics.heightPixels - 400, metrics.widthPixels/2 - 300, "Down", context, metrics, 2);
        TouchPad right = new TouchPad(metrics.heightPixels - 400, metrics.widthPixels/2 - 100, "Right", context, metrics, 4);
        TouchPad up = new TouchPad(metrics.heightPixels - 600, metrics.widthPixels/2 - 300, "Up", context, metrics, 1);
        TouchPad fire = new TouchPad(metrics.heightPixels - 400, metrics.widthPixels/2 + 200, "Fire", context, metrics, 0);
        touchPads.add(left);
        touchPads.add(down);
        touchPads.add(right);
        touchPads.add(up);
        touchPads.add(fire);
        //Brick brick = new Brick();
//        Bitmap bitmapb = BitmapFactory.decodeResource(context.getResources(), R.drawable.brick);
//        brick.setBitmap(bitmapb);
//        brick.setX(400);
//        brick.setY(400);
//        Shtab shtab = new Shtab(context, metrics);
////        Bitmap bitmapsh = BitmapFactory.decodeResource(context.getResources(), R.drawable.shtab);
////        Bitmap bitmap3 = Bitmap.createScaledBitmap(bitmapsh, 40, 40, true);
////        shtab.setBitmap(bitmap3);
//        shtab.setX(500);
//        shtab.setY(500);
//        Rock rock = new Rock();
//        Bitmap bitmapr = BitmapFactory.decodeResource(context.getResources(), R.drawable.rock);
//        rock.setBitmap(bitmapr);
//        rock.setX(600);
//        rock.setY(600);
        map = BattleFIeld.getMaps().get(indexMap);
        list = new CopyOnWriteArrayList<>();
//        List<AbstractObjects> list= new CopyOnWriteArrayList<>();
//
//       // list.add(brick);
//
//        //list.add(rock);
//
//
//        int width = metrics.widthPixels;
//        int height = metrics.heightPixels;
//        Log.d("metrics",String.valueOf(width));
//        Log.d("metrics", String.valueOf(height));
//        int quadrantX = width/40;
//        int quadrantY = height/40;
//
//        for (int i = 0; i < quadrantY; i++) {
//            for (int j = 0; j < quadrantX; j++) {
//                if (j%3 == 0) {
//                    list.add(new Brick(metrics,i*40, j*40,context));
//                }
////                else {
////                    Ground ground = new Ground(context, metrics);
////                    ground.setY(i*40);
////                    ground.setX(j*40);
////                    list.add(ground);
////                }
//            }
//        }

        list.add(tank);
        list.add(tankg);
        list.add(left);
        list.add(right);
        list.add(down);
        list.add(up);
        list.add(fire);

        map.setObjects(list);




        thread = new GameThread(getHolder(), context,map);
        thread.start();
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        thread.requestStop();

        boolean retry = true;
        while (retry) {
            try {
                thread.join();
                retry = false;
           } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        BattleFIeld.getMaps().clear();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        for (TouchPad pad : touchPads) {
           if (x >= pad.getX() && x <= pad.getX() + 200 && y >= pad.getY() && y <= pad.getY() + 200) {
               if (!pad.getDirection().equals("Fire")) {
                   playTank.setDirection(pad.getDirectionValue());
               }
               if (pad.getDirection().equals("Fire")) {
                   playTank.setFire(true);
               } else if (canTankMove(pad.getDirectionValue())){
                   playTank.move(pad.getDirectionValue(), 5);
               }
           }
        }
        return true;
    }

    private boolean canTankMove(int direction) {
        int playTankX = (int) (playTank.getX()/40);
        int playTankY = (int) (playTank.getY()/40);

        if (direction == 1 && checkIsLeavingObject(map, playTankY - 1, playTankX)
                || direction == 2 && checkIsLeavingObject(map, playTankY + 1, playTankX)
                  || direction == 3 && checkIsLeavingObject(map, playTankY, playTankX - 1)
                    || direction == 4 && checkIsLeavingObject(map, playTankY, playTankX + 1)) {
            return true;
        }

        return false;

    }

    private boolean checkIsLeavingObject(GameMap map, int y, int x) {
        StopableObject[][] fieldObject = map.getStopableObjects();
        if (y >= 0 && y < map.getHeight() && x >= 0 && x < map.getWidth()) {
            return fieldObject[y][x] == null
                    || fieldObject[y][x] != null && fieldObject[y][x].isDestroyed();
        }
        return false;
    }

}

package java.devcolibri.itvdn.com.hellotanks.pojo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;

import java.devcolibri.itvdn.com.hellotanks.R;
import java.util.List;

public class DefenderTank extends Tank {

    public DefenderTank(DisplayMetrics metrics, Context context) {
        super(context, metrics);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.green_tank_up);
        Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap, 40,40, true);
        setBitmap(bitmap1);
    }


    @Override
    public void setDirection(int direction) {
        super.setDirection(direction);
        initBitmap(direction,
                new int[] {R.drawable.green_tank_up, R.drawable.green_tank_down, R.drawable.green_tank_left, R.drawable.green_tank_right});
    }

    @Override
    public void update(GameMap map) {
        List<MovableObject> list = map.getObjects();
        StopableObject[][] stopableObjects = map.getStopableObjects();
        for (AbstractObjects object : list) {
            gettingFire(object, AttackTank.class);
            if (!isPlay()) {
                attack(this, object, AttackTank.class, stopableObjects);
            }
        }

        getBullet().update(map);
    }



}

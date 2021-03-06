package java.devcolibri.itvdn.com.hellotanks.pojo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;

import java.devcolibri.itvdn.com.hellotanks.R;
import java.util.List;

public class AttackTank extends Tank {

    public AttackTank(DisplayMetrics metrics, Context context) {
        super(context, metrics);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.golden_tank_up);
        Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap, 40,40, true);
        setBitmap(bitmap1);
    }

    @Override
    public void setDirection(int direction) {
        super.setDirection(direction);
        initBitmap(direction,
                new int[] {R.drawable.golden_tank_up, R.drawable.golden_tank_down, R.drawable.golden_tank_left, R.drawable.golden_tank_right});
    }

    @Override
    public void update(GameMap map) {
        List<MovableObject> list = map.getObjects();
        StopableObject[][] fieldsObjects = map.getStopableObjects();
         for (AbstractObjects object : list) {
             gettingFire(object, DefenderTank.class);
             if (!isPlay()) {
                 attack(this, object, Shtab.class, fieldsObjects);
             }
         }
         getBullet().update(map);

    }



}

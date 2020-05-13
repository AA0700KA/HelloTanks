package java.devcolibri.itvdn.com.hellotanks.pojo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.util.Log;

import java.devcolibri.itvdn.com.hellotanks.R;
import java.util.List;

public class DefenderTank extends Tank {

    public DefenderTank(DisplayMetrics metrics, Context context) {
        super(context, metrics);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.green_tank);
        Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap, 40,40, true);
        setBitmap(bitmap1);
    }

    @Override
    public void update(List<AbstractObjects> list) {
        for (AbstractObjects object : list) {
            gettingFire(object, AttackTank.class);
            if (!isPlay()) {
                attack(this, object, AttackTank.class);
            }
        }

        getBullet().update(list);
    }



}

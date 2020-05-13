package java.devcolibri.itvdn.com.hellotanks.pojo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;

import java.devcolibri.itvdn.com.hellotanks.R;
import java.util.List;

public class Ground extends StopableObject {

    public Ground(Context context, DisplayMetrics metrics) {
        super(context, metrics);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ground);
        Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap, 40,40, true);
        setBitmap(bitmap1);
    }


    @Override
    public void update(List<AbstractObjects> list) {

    }

}

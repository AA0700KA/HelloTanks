package java.devcolibri.itvdn.com.hellotanks.pojo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.util.Log;

import java.devcolibri.itvdn.com.hellotanks.R;
import java.devcolibri.itvdn.com.hellotanks.interfaces.Destroyable;
import java.util.List;


public class Brick extends StopableObject {

    public Brick(DisplayMetrics metrics, int y, int x, Context context){
        super(context, metrics);
        setY(y);
        setX(x);

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.brick);
        Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap, 40, 40, true);
        setBitmap(bitmap1);
    }


}
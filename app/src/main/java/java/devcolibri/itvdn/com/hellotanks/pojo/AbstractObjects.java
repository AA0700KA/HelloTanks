package java.devcolibri.itvdn.com.hellotanks.pojo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.DisplayMetrics;

import java.devcolibri.itvdn.com.hellotanks.interfaces.Destroyable;
import java.devcolibri.itvdn.com.hellotanks.interfaces.Drawable;
import java.util.List;

public abstract class AbstractObjects implements Drawable, Destroyable {
    private DisplayMetrics metrics;
    private Context context;
   private float x,y;
   private Bitmap bitmap;


    public abstract void draw(Canvas canvas);

   public abstract void update(List<AbstractObjects> list);

    public AbstractObjects(Context context, DisplayMetrics metrics) {
        this.context = context;
        this.metrics = metrics;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public float getX() {
       return x;
   }

   public void setX(float x) {
       this.x = x;
   }

   public float getY() {
       return y;
   }

   public void setY(float y) {
       this.y = y;
   }

   public Bitmap getBitmap() {
       return bitmap;
   }

   public void setBitmap(Bitmap bitmap) {
       this.bitmap = bitmap;
   }

    public DisplayMetrics getMetrics() {
        return metrics;
    }

    public void setMetrics(DisplayMetrics metrics) {
        this.metrics = metrics;
    }

    private boolean destroyed;

    @Override
    public void destroy() {
        destroyed = true;
    }

    @Override
    public boolean isDestroyed() {
        return destroyed;
    }


}
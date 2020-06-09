package java.devcolibri.itvdn.com.hellotanks.pojo;

import android.content.Context;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.List;

public abstract class StopableObject extends AbstractObjects {

    public StopableObject(Context context, DisplayMetrics metrics) {
        super(context, metrics);
    }

    public void draw(Canvas canvas) {
        if (!isDestroyed()) {
            canvas.drawBitmap(getBitmap(), getX(), getY(), null);
        }
    }

    @Override
    public void update(GameMap map) {
        List<MovableObject> list = map.getObjects();
        for (AbstractObjects object : list) {
            gettingFire(object);
        }
    }


    public void gettingFire(AbstractObjects object) {
        if ((object.getClass() == AttackTank.class || object.getClass() == DefenderTank.class) && !object.isDestroyed()) {
            Tank tank = (Tank) object;

            if (!tank.getBullet().isDestroyed()) {
//                int x = (int) (getX() / 40);
//                int y = (int) (getY() / 40);
                int x = (int) (getX());
                int y = (int) (getY());
//                int xBullet = (int) (tank.getBullet().getX() / 40);
//                int yBullet = (int) (tank.getBullet().getY() / 40);
                int xBullet = (int) (tank.getBullet().getX());
                int yBullet = (int) (tank.getBullet().getY());

                // Log.d("intersect", "x = " +x + " y = " + y + " and bullet x = " + xBullet + " y = " + yBullet);
                if (xBullet >= x && xBullet <= x+40 && yBullet >= y && yBullet <= y+40) {
                    Log.d("intersect", "Yes bullet fire to Shtab");
                    destroy();
                    tank.getBullet().destroy();
                    //    list.remove(this);
                    tank.setFire(false);
                }
            }

            if (!tank.isDestroyed()) {
                int x = (int) (getX());
                int y = (int) (getY());
//                int xBullet = (int) (tank.getBullet().getX() / 40);
//                int yBullet = (int) (tank.getBullet().getY() / 40);
                int xTank = (int) (tank.getX());
                int yTank = (int) (tank.getY());

//                if (...) {
//                    tank.setDontCanMove(true);
//                }


            }

        }
    }

}

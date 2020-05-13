package java.devcolibri.itvdn.com.hellotanks.pojo;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;

public abstract class MovableObject extends AbstractObjects {

    private int lastDirection;

    public MovableObject(Context context, DisplayMetrics metrics) {
        super(context, metrics);
    }

    public void gettingFire(AbstractObjects object, Class defenderClass) {
        if (object.getClass() == defenderClass && !object.isDestroyed()) {
            Tank tank = (Tank) object;
            if (!tank.getBullet().isDestroyed()) {
//                int x = (int) (getX() / 40);
//                int y = (int) (getY() / 40);
//                int xBullet = (int) (tank.getBullet().getX() / 40);
//                int yBullet = (int) (tank.getBullet().getY() / 40);

                int x = (int) (getX());
                int y = (int) (getY());
                int xBullet = (int) (tank.getBullet().getX());
                int yBullet = (int) (tank.getBullet().getY());

                // Log.d("intersect", "x = " +x + " y = " + y + " and bullet x = " + xBullet + " y = " + yBullet);
                if (xBullet >= x && xBullet <= x+40 && yBullet >= y && yBullet <= y+40) {
                    Log.d("intersect", "Yes bullet fire to Tank");
                    destroy();
                    tank.getBullet().destroy();
                    //    list.remove(this);
                    tank.setFire(false);
                }
            }
        }
    }

    public void attack(Tank tank, AbstractObjects object, Class attackedObjectClass) {
        tank.setDontCanMove(false);
        if (object.getClass() == attackedObjectClass && !object.isDestroyed()) {
            int subY = (int) Math.abs(getY() - object.getY());
            int subX = (int) Math.abs(getX() - object.getX());
            Log.d("attack", "attack: " + attackedObjectClass.getSimpleName() + " Y tank = " + ((int) getY()/40) + " Y object = " + ((int) object.getY()/40));
            Log.d("attack", "attack: " + attackedObjectClass.getSimpleName() + " X tank = " +((int) getX()/40) + " X object = " + ((int)object.getX()/40));

            if (subY < subX) {

                boolean canSeeTank = (int)(getY()) <= (int)object.getY() + 5 && (int)(getY()) >= (int)object.getY() - 5;

                if (canSeeTank) {
                    if (getX() < object.getX()) {
                        tank.setDirection(4);
                    } else if (getX() > object.getX()) {
                        tank.setDirection(3);
                    }

                    tank.setFire(true);
                }else if (getY() > object.getY()) {
                    tank.move(1, 5);
                    lastDirection = 1;
                } else if (getY() < object.getY()) {
                    tank.move(2, 5);
                    lastDirection = 2;
                }

            } else {

                boolean canSeeTank = (int)(getX()) <= (int)object.getX() + 5 && (int)(getX()) >= (int)object.getX() - 5;

                if (canSeeTank) {
                    if (getY() < object.getY()) {
                        tank.setDirection(2);
                    } else if (getY() > object.getY()) {
                        tank.setDirection(1);
                    }

                    tank.setFire(true);
                } else if (getX() > object.getX()) {
                    tank.move(3,5);
                    lastDirection = 3;
                } else if (getX() < object.getX()) {
                    tank.move(4,5);
                    lastDirection = 4;
                }

            }

        }
    }

}

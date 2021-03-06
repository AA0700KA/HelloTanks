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

    public void attack(Tank tank, AbstractObjects object, Class attackedObjectClass, StopableObject[][] fieldsObjects) {
        tank.setDontCanMove(false);
        if (object.getClass() == attackedObjectClass && !object.isDestroyed()) {
            int tankXQuadrant = (int) (getX()/40);
            int tankYQuadrant = (int) (getY()/40);
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
                    if (((fieldsObjects[tankYQuadrant - 1][tankXQuadrant] != null
                            && fieldsObjects[tankYQuadrant - 1][tankXQuadrant].isDestroyed())
                            || fieldsObjects[tankYQuadrant - 1][tankXQuadrant] == null)) {
                        tank.move(1, 1);
                    } else {
                        tank.setDirection(1);
                        tank.setFire(true);
                    }
                    lastDirection = 1;
                } else if (getY() < object.getY()) {
                    if (((fieldsObjects[tankYQuadrant + 1][tankXQuadrant] != null
                            && fieldsObjects[tankYQuadrant + 1][tankXQuadrant].isDestroyed())
                            || fieldsObjects[tankYQuadrant + 1][tankXQuadrant] == null)) {
                        tank.move(2, 1);
                    } else {
                        tank.setDirection(2);
                        tank.setFire(true);
                    }
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
                    if (((fieldsObjects[tankYQuadrant][tankXQuadrant - 1] != null
                            && fieldsObjects[tankYQuadrant][tankXQuadrant - 1].isDestroyed())
                            || fieldsObjects[tankYQuadrant ][tankXQuadrant - 1] == null)) {
                        tank.move(3, 1);
                    } else {
                        tank.setDirection(3);
                        tank.setFire(true);
                    }
                    lastDirection = 3;
                } else if (getX() < object.getX()) {
                    if (((fieldsObjects[tankYQuadrant][tankXQuadrant + 1] != null
                            && fieldsObjects[tankYQuadrant][tankXQuadrant + 1].isDestroyed())
                            || fieldsObjects[tankYQuadrant][tankXQuadrant + 1] == null)) {
                        tank.move(4, 1);
                    } else {
                        tank.setDirection(4);
                        tank.setFire(true);
                    }
                    lastDirection = 4;
                }

            }

        }
    }

}

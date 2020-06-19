package java.devcolibri.itvdn.com.hellotanks.pojo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.List;

public class Bullet extends MovableObject {

    private Tank tank;
    private int direction;

    public Bullet(Context context, DisplayMetrics metrics, Tank tank) {
        super(context, metrics);
        setX(-100);
        setY(-100);
        this.tank = tank;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint p = new Paint();
        p.setColor(Color.YELLOW);
        p.setStyle(Paint.Style.FILL);
//        Log.d("tankCord", "Bullet X = " + (getX()) );
//        Log.d("tankCord", "Bullet Y = " + (getY()) );
        canvas.drawRect(getX(), getY(), getX() + 14, getY() + 14, p);
    }

    @Override
    public void update(GameMap map) {
        if (tank.fire()) {
            if (tank.getDirection() == 1 && getX() == -100 && getY() == -100) {
                Log.d("tankCord", "X = " + (tank.getX()));
                Log.d("tankCord", "Y = " + (tank.getY() - 14));
                setY(tank.getY() - 14);
                setX(tank.getX() + 14);
                direction = tank.getDirection();
            } else if (tank.getDirection() == 2 && getY() == -100 && getX() == -100) {
                setY(tank.getY() + 54);
                setX(tank.getX() + 14);
                direction = tank.getDirection();
            } else if (tank.getDirection() == 4 && getY() == -100 && getX() == -100) {
                setX(tank.getX() + 54);
                setY(tank.getY() + 14);
                direction = tank.getDirection();
            } else if (tank.getDirection() == 3 && getX() == -100 && getX() == -100) {
                setX(tank.getX() - 14);
                setY(tank.getY() + 14);
                direction = tank.getDirection();
            }

            if (direction == 1) {
                setY(getY() - 5);
            } else if (direction == 2) {
                setY(getY() + 5);
            } else if (direction == 3) {
                setX(getX() - 5);
            } else if (direction == 4) {
                setX(getX() + 5);
                
            }

            if (isDestroyed()) {
                tank.setFire(false);
                destroy();
            }
        }

    }

    public Tank getTank() {
        return tank;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }

    @Override
    public boolean isDestroyed() {
        return getX() < 0 || getY() < 0 || getX() > getMetrics().widthPixels || getY() > getMetrics().heightPixels;
    }

    @Override
    public void destroy() {
        setY(-100);
        setX(-100);
    }

}

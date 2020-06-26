package java.devcolibri.itvdn.com.hellotanks.pojo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;

import java.devcolibri.itvdn.com.hellotanks.R;

public abstract class Tank extends MovableObject {

    private Bullet bullet;
    private int direction;
    private boolean fire;
    private boolean play;
    private boolean dontCanMove;

    public Tank(Context context, DisplayMetrics metrics) {
        super(context, metrics);
        bullet = new Bullet(context, metrics, this);
    }

    public void setFire(boolean fire) {
        this.fire = fire;
    }

    public Bullet getBullet() {
        return bullet;
    }


    public boolean fire() {
        return fire;
    }


    public void setDontCanMove(boolean dontCanMove) {
        this.dontCanMove = dontCanMove;
    }

    public int getDirection() {
        return direction;
    }



    public void setDirection(int direction) {
        this.direction = direction;
    }

    protected void initBitmap(int direction, int[] idDrawable) {
        if (direction == 1) {
            changeBitmap(idDrawable[0]);
        } else if (direction == 2) {
            changeBitmap(idDrawable[1]);
        } else if (direction == 3) {
            changeBitmap(idDrawable[2]);
        } else if (direction == 4) {
            changeBitmap(idDrawable[3]);
        }
    }

    private void changeBitmap(int idDrawable) {
        Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), idDrawable);
        Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap, 40,40, true);
        setBitmap(bitmap1);
    }

    public void move(int direction, int pixels) {
        setDirection(direction);

        if (dontCanMove) {
            return;
        }

        if (direction == 1 && getY() >= 0) {
            setY(getY()-pixels);
        } else if (direction == 2 && getY() <= getMetrics().heightPixels - 40) {
            setY(getY()+pixels);
        } else if (direction == 3 && getX() >= 0) {
            setX(getX()-pixels);
        } else if (direction == 4 && getX() <= getMetrics().widthPixels - 40) {
            setX(getX()+pixels);
        }
    }

    @Override
    public  void draw(Canvas canvas) {
        if (!isDestroyed()) {
            canvas.drawBitmap(getBitmap(), getX(), getY(), null);
        }
        bullet.draw(canvas);
    }

    public boolean isPlay() {
        return play;
    }

    public void setPlay(boolean play) {
        this.play = play;
    }
}

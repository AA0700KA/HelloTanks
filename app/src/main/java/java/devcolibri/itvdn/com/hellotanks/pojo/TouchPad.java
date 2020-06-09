package java.devcolibri.itvdn.com.hellotanks.pojo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;

import java.util.List;

public class TouchPad extends MovableObject {

    private String direction;
    private int directionValue;

    public TouchPad(float y, float x, String direction, Context context, DisplayMetrics metrics, int directionValue) {
        super(context, metrics);
        this.direction = direction;
        setY(y);
        setX(x);
        this.directionValue = directionValue;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(40f);
        paint.setStrokeWidth(3f);

        canvas.drawRect(getX(), getY(), getX() + 200, getY() + 200, paint);
        canvas.drawText(direction,getX() + 70, getY() + 70, paint);
    }

    @Override
    public void update(GameMap map) {

    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getDirectionValue() {
        return directionValue;
    }

    public void setDirectionValue(int directionValue) {
        this.directionValue = directionValue;
    }

}

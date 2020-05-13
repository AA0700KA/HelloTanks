package java.devcolibri.itvdn.com.hellotanks.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.devcolibri.itvdn.com.hellotanks.pojo.AbstractObjects;
import java.devcolibri.itvdn.com.hellotanks.pojo.Brick;
import java.devcolibri.itvdn.com.hellotanks.pojo.GameMap;
import java.devcolibri.itvdn.com.hellotanks.pojo.Ground;
import java.devcolibri.itvdn.com.hellotanks.pojo.Rock;
import java.devcolibri.itvdn.com.hellotanks.pojo.Shtab;
import java.devcolibri.itvdn.com.hellotanks.util.BattleFIeld;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CreateMapView extends View {

    private DisplayMetrics metrics;
    private int height;
    private int width;
    private Brick brick;
    private Rock rock;
    private Shtab shtab;
    private Paint backgroundPaint = new Paint();
    private AbstractObjects currentObject;
    private List<AbstractObjects> list;
    private boolean haveNotShtab = true;


    public CreateMapView(Context context, AttributeSet set) {
        super(context, set);

        list = new CopyOnWriteArrayList<>();
        backgroundPaint.setColor(Color.parseColor("#008577"));
        backgroundPaint.setStyle(Paint.Style.FILL);

        metrics = context.getResources().getDisplayMetrics();

        height = metrics.heightPixels;
        width = metrics.widthPixels;

        brick = new Brick(metrics, height - 400, width/2 - 240, context);
        rock = new Rock(context, metrics);
        rock.setX(width/2 - 120);
        rock.setY(height - 400);
        shtab = new Shtab(context, metrics);
        shtab.setX(width/2);
        shtab.setY(height - 400);
//        ground = new Ground(context, metrics);
//        ground.setX(width/2 + 120);
//        ground.setY(height - 200);
    }

    public void createNewMap(Activity activity, String name) {
        if (!haveNotShtab) {
            GameMap map = new GameMap();
            map.setName(name);
            map.setObjects(list);
            BattleFIeld.addMap(map);
            activity.finish();
        } else {
            Toast.makeText(getContext(), "Must have only one shtab", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawRect(0,0, canvas.getWidth(), canvas.getHeight(), backgroundPaint);
        Bitmap brickBitmap = Bitmap.createScaledBitmap(brick.getBitmap(), 120,120, true);

        canvas.drawBitmap(brickBitmap, brick.getX(), brick.getY(), null);

        Bitmap rockBitmap = Bitmap.createScaledBitmap(rock.getBitmap(), 120,120, true);

        canvas.drawBitmap(rockBitmap, rock.getX(), rock.getY(), null);

        Bitmap shtabBitmap = Bitmap.createScaledBitmap(shtab.getBitmap(), 120,120, true);

        canvas.drawBitmap(shtabBitmap, shtab.getX(), shtab.getY(), null);

         if (currentObject != null) {
             currentObject.draw(canvas);
         }

         for (AbstractObjects object : list) {
             object.draw(canvas);
         }

    }

    private AbstractObjects createObject(float x, float y) {
        if (x >= brick.getX() && x <= brick.getX() + 120 && y >= brick.getY() && y <= brick.getY() + 120) {
            return new Brick(metrics, (int)y, (int)x, getContext());
        } else if (x >= rock.getX() && x <= rock.getX() + 120 && y >= rock.getY() && y <= rock.getY() + 120) {
            Rock rock = new Rock(getContext(), metrics);
            rock.setY(y);
            rock.setX(x);
            return rock;
        } else if (x >= shtab.getX() && x <= shtab.getX() + 120 && y >= shtab.getY() && y <= shtab.getY() + 120) {
            if (haveNotShtab) {
                Shtab shtab = new Shtab(getContext(), metrics);
                shtab.setY(y);
                shtab.setX(x);
                haveNotShtab = false;
                return shtab;
            } else {
                Toast.makeText(getContext(), "Shtab must be only one in the battleField", Toast.LENGTH_SHORT).show();
            }
        }
        return null;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN :
                currentObject = createObject(x, y);

                for (AbstractObjects object : list) {
                    if (x >= object.getX() && x <= object.getX() + 40 && y >= object.getY() && y <= object.getY() + 40) {
                        list.remove(object);

                        if (object.getClass() == Shtab.class) {
                            haveNotShtab = true;
                        }

                    }
                }

            case MotionEvent.ACTION_MOVE :
                if (currentObject != null) {
                    currentObject.setX(x);
                    currentObject.setY(y);
                }

                break;

            case MotionEvent.ACTION_UP :
                if (currentObject != null) {
                    list.add(currentObject);
                }
                currentObject = null;
                break;
        }

        invalidate();

        return true;
    }
}

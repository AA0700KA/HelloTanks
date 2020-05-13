package java.devcolibri.itvdn.com.hellotanks.thread;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;


import java.devcolibri.itvdn.com.hellotanks.pojo.AbstractObjects;
import java.util.List;

public class GameThread extends Thread {

    private List<AbstractObjects> list;

    private SurfaceHolder holder;
    private volatile boolean running = true;
    private Paint backgroundPaint = new Paint();
    private Paint paint = new Paint();


    private volatile float x = 500f;
    private volatile float y = 500f;
    private Context context;

    public GameThread(SurfaceHolder holder, Context context, List<AbstractObjects> list) {
        this.holder = holder;
        this.context = context;
        this.list = list;


        backgroundPaint.setColor(Color.parseColor("#008577"));
        backgroundPaint.setStyle(Paint.Style.FILL);

        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
    }


    public void requestStop() {
        running = false;
    }

    @Override
    public void run() {


        Canvas canvas = null;
        while (running) {

            canvas = holder.lockCanvas();

            if (canvas != null) {


                try {
//                    x += vx;
//                    y += vy;
//
//                    if (x < 50f || x > canvas.getWidth() - 50f) {
//                        vx = -vx;
//                    }
//
//                   if (y < 50f || y > canvas.getHeight() - 50f) {
//                       vy = -vy;
//                   }


                    canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), backgroundPaint);

                    //                   Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.golden_tank);

                    //                   canvas.drawBitmap(bitmap, x, y, paint);
                    Log.d("count_obj", "count objects: " + list.size());
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).draw(canvas);
                        list.get(i).update(list);

                        if (list.get(i).isDestroyed()) {
                            list.remove(list.get(i));
                        }

                    }


                    //canvas.drawCircle(x, y, 50f, paint);

                    //   canvas.dr
                    //   Log.d("Canvas", "x = " + x + "; y = " + y);


                } finally {
                    holder.unlockCanvasAndPost(canvas);
                }


            }

        }


    }
}

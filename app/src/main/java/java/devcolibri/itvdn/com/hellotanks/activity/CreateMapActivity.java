package java.devcolibri.itvdn.com.hellotanks.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import java.devcolibri.itvdn.com.hellotanks.views.CreateMapView;
import java.devcolibri.itvdn.com.hellotanks.R;


public class CreateMapActivity extends Activity {

    private CreateMapView mapView;
    private EditText textMapName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      //  CreateMapView view = new CreateMapView(this);
        setContentView(R.layout.create_map_layout);

        mapView = findViewById(R.id.create_map_view);
        textMapName = findViewById(R.id.map_name);

//        LinearLayout mapLayout = findViewById(R.id.map);
//        DisplayMetrics metrics = getResources().getDisplayMetrics();
//
//        int height = metrics.heightPixels;
//        int width = metrics.widthPixels;
//
//        LinearLayout columnLayout = new LinearLayout(this);
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        columnLayout.setLayoutParams(params);
//        columnLayout.setOrientation(LinearLayout.HORIZONTAL);
//
//        for (int i = 0; i < height; i++) {
//
//            for (int j = 0; j < width; j++) {
//                ImageView image = new ImageView(this);
//                image.setImageResource(R.drawable.golden_tank);
//                columnLayout.addView(image, 40, 40);
//            }
//
//            mapLayout.addView(columnLayout);
//        }

    }

    public void addMap(View view) {
        String mapName = textMapName.getText().toString();
        mapView.createNewMap(this, mapName);
    }



}

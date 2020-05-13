package java.devcolibri.itvdn.com.hellotanks.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.devcolibri.itvdn.com.hellotanks.R;
import java.devcolibri.itvdn.com.hellotanks.pojo.GameMap;
import java.util.List;

public class GameMapAdapter extends BaseAdapter {

    List<GameMap> gameMaps;
    LayoutInflater inflater;

    public GameMapAdapter(List<GameMap> gameMaps, LayoutInflater inflater) {
        this.gameMaps = gameMaps;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return gameMaps.size();
    }

    @Override
    public Object getItem(int i) {
        return gameMaps.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View convertView = view;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.map_item, viewGroup, false);
        }

        GameMap map = (GameMap) getItem(i);
        TextView text = convertView.findViewById(R.id.map_name);
        text.setText(map.getName());

        return convertView;
    }

}

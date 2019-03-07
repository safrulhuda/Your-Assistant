package id.ac.unsyiah.android.yourassistant.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import id.ac.unsyiah.android.yourassistant.R;

/**
 * Created by USER PC on 14/06/2018.
 */

public class CustomListOpsiAdapter extends ArrayAdapter<ListOpsiData> {
    private Context context;
    private List<ListOpsiData> listOpsiData;

    public CustomListOpsiAdapter(@NonNull Context context, int resource, List<ListOpsiData> listOpsiData) {
        super(context, resource, listOpsiData);
        this.context = context;
        this.listOpsiData = listOpsiData;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return myCustomListOpsiAdapter(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return myCustomListOpsiAdapter(position, convertView, parent);
    }

    private View myCustomListOpsiAdapter(int position, @Nullable View myView, @Nullable ViewGroup parent){
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View costumView = layoutInflater.inflate(R.layout.costum_list, parent, false);

        TextView opsiNama = (TextView) costumView.findViewById(R.id.opsiNama);
        ImageView opsiGambar = (ImageView) costumView.findViewById(R.id.gambarOpsi);

        opsiNama.setText(listOpsiData.get(position).getIconNama());
        opsiGambar.setImageResource(listOpsiData.get(position).getIcon());

        return costumView;
    }
}

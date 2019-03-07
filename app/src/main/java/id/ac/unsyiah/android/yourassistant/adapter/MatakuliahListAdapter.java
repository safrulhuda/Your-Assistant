package id.ac.unsyiah.android.yourassistant.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import id.ac.unsyiah.android.yourassistant.R;
import id.ac.unsyiah.android.yourassistant.database.MataKuliah;
import id.ac.unsyiah.android.yourassistant.database.QueryMK;

/**
 * Created by USER PC on 10/06/2018.
 */

public class MatakuliahListAdapter extends BaseAdapter {

    private Context mContext;
    private List<MataKuliah> mMatakuliahList;

    public MatakuliahListAdapter(Context mContext, List<MataKuliah> mMatakuliahList) {
        this.mContext = mContext;
        this.mMatakuliahList = mMatakuliahList;
    }

    @Override
    public int getCount() {
        return mMatakuliahList.size();
    }

    @Override
    public Object getItem(int position) {
        return mMatakuliahList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(mContext, R.layout.item_matakuliah_list, null);

        TextView inputMK = (TextView) view.findViewById(R.id.mkCode);
        TextView namaMK = (TextView) view.findViewById(R.id.judulMatakuliah);
        TextView namaDosen = (TextView) view.findViewById(R.id.namaDosen);
        TextView namaRuangan = (TextView) view.findViewById(R.id.listRuangan);
        TextView waktuMK = (TextView) view.findViewById(R.id.listWaktu);
        RelativeLayout mainLayout = (RelativeLayout) view.findViewById(R.id.mainLayout);

        if( position % 2 != 0 ){
            mainLayout.setBackgroundColor(Color.rgb(233, 252, 212));
        }

        inputMK.setText(mMatakuliahList.get(position).getKodeMK());
        namaMK.setText(mMatakuliahList.get(position).getNamaMK());
        namaDosen.setText(mMatakuliahList.get(position).getDosen());
        namaRuangan.setText(mMatakuliahList.get(position).getRuangMK());
        waktuMK.setText(mMatakuliahList.get(position).getWaktu());
        return view;
    }
}

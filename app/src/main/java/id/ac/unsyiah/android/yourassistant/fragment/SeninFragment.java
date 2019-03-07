package id.ac.unsyiah.android.yourassistant.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.ac.unsyiah.android.yourassistant.DetailActivity;
import id.ac.unsyiah.android.yourassistant.R;
import id.ac.unsyiah.android.yourassistant.adapter.MatakuliahListAdapter;
import id.ac.unsyiah.android.yourassistant.database.MataKuliah;
import id.ac.unsyiah.android.yourassistant.database.QueryMK;


/**
 * A simple {@link Fragment} subclass.
 */
public class SeninFragment extends Fragment {

    private ListView listView;
    private MatakuliahListAdapter adapterMatakuliah;
    private List<MataKuliah> mMatakuliah;
    private QueryMK db;

    public SeninFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_senin, container, false);

        listView = view.findViewById(R.id.dataSenin);

        db = new QueryMK(getContext());
        mMatakuliah = new ArrayList<>();
        db.open();
        mMatakuliah = db.DataJadwalKuliah("'Monday'");
        db.close();

        adapterMatakuliah = new MatakuliahListAdapter(getContext(), mMatakuliah);
        listView.setAdapter(adapterMatakuliah);
        adapterMatakuliah.notifyDataSetChanged();

        listView.setSelected(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String select = mMatakuliah.get(position).getKodeMK();
                String selectHari = mMatakuliah.get(position).getHariMK();
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                intent.putExtra("kodeMK", select);
                intent.putExtra("hariMK", selectHari);
                startActivity(intent);
            }
        });
        return view;
    }
}

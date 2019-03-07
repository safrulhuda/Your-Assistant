package id.ac.unsyiah.android.yourassistant;

import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import id.ac.unsyiah.android.yourassistant.adapter.CustomListOpsiAdapter;
import id.ac.unsyiah.android.yourassistant.adapter.DetailMatakuliahListAdapter;
import id.ac.unsyiah.android.yourassistant.adapter.ListOpsiData;
import id.ac.unsyiah.android.yourassistant.adapter.TugaskuliahListAdapter;
import id.ac.unsyiah.android.yourassistant.alarm.AlarmReceiver;
import id.ac.unsyiah.android.yourassistant.alarm.NotificationScheduler;
import id.ac.unsyiah.android.yourassistant.database.MataKuliah;
import id.ac.unsyiah.android.yourassistant.database.QueryMK;
import id.ac.unsyiah.android.yourassistant.database.QueryTask;
import id.ac.unsyiah.android.yourassistant.database.TugasKuliah;

public class DetailActivity extends AppCompatActivity {

    private DetailMatakuliahListAdapter adapterMatakuliah;
    private TugaskuliahListAdapter adapterTugaskuliah, loadAdapterTugaskuliah;
    private List<MataKuliah> dMatakuliah;
    private List<TugasKuliah> dTugaskuliah;
    private QueryMK database;
    private QueryTask db;
    private ListView listSelect, listTugas, listDetailOpsi;
    private TextView hari;
    private RelativeLayout layoutTambahTugas, listTugasLayout;
    private Button buttonAddTask;
    private ImageButton opsiDetail;
    private ScrollView scroll;
    private NotificationScheduler notificationScheduler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setTitle("Detail Schedule");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listSelect = findViewById(R.id.listSelected);
        listTugas = findViewById(R.id.listTugas);
        listDetailOpsi = findViewById(R.id.opsiDetailMK);
        hari = findViewById(R.id.textHari);
        buttonAddTask = findViewById(R.id.addTask);
        opsiDetail = findViewById(R.id.opsiMK);
        layoutTambahTugas = findViewById(R.id.ParentF);
        listTugasLayout = findViewById(R.id.listTugasLayout);
        scroll = findViewById(R.id.scrollB);
        layoutTambahTugas.setVisibility(layoutTambahTugas.INVISIBLE);
        listTugasLayout.setVisibility(listTugasLayout.INVISIBLE);
        listDetailOpsi.setVisibility(listDetailOpsi.INVISIBLE);
        scroll.setVisibility(scroll.INVISIBLE);

        database = new QueryMK(this);
        db = new QueryTask(this);
        dMatakuliah = new ArrayList<>();
        dTugaskuliah = new ArrayList<>();

        final String selectedkodeMK = getIntent().getStringExtra("kodeMK");
        final String selectedhariMK = getIntent().getStringExtra("hariMK");
        final String callBackKodeMK = getIntent().getStringExtra("kodeMKCallBack");
        final String callBackHariMK = getIntent().getStringExtra("hariMKCallBack");
        final String callBackUpdateKodeMK = getIntent().getStringExtra("updateKodeMK");
        final String callBackUpdateHariMK = getIntent().getStringExtra("updateHariMK");
        final String callBackUpateKodeMKTask = getIntent().getStringExtra("callUpdateTaskNoMK");
        final String callBackUpdateHariMKTask = getIntent().getStringExtra("callUpdateTaskHariMK");

        if(selectedkodeMK != null){
            database.open();
            dMatakuliah = database.DetailJadwalKuliah(selectedkodeMK);
            database.close();

            db.open();
            dTugaskuliah = db.DataTask(selectedkodeMK);
            db.close();

            hari.setText(selectedhariMK);
        }else if (callBackKodeMK != null){
            db.open();
            dTugaskuliah = db.DataTask(callBackKodeMK);
            db.close();

            database.open();
            dMatakuliah = database.DetailJadwalKuliah(callBackKodeMK);
            database.close();

            hari.setText(callBackHariMK);
        }else if(callBackUpdateKodeMK != null){

            db.open();
            dTugaskuliah = db.DataTask(callBackUpdateKodeMK);
            db.close();

            database.open();
            dMatakuliah = database.DetailJadwalKuliah(callBackUpdateKodeMK);
            database.close();

            hari.setText(callBackUpdateHariMK);
        }else if(callBackUpateKodeMKTask != null){
            db.open();
            dTugaskuliah = db.DataTask(callBackUpateKodeMKTask);
            db.close();

            database.open();
            dMatakuliah = database.DetailJadwalKuliah(callBackUpateKodeMKTask);
            database.close();

            hari.setText(callBackUpdateHariMKTask);
        }

        //Intent intent = new Intent(DetailActivity.this.getApplicationContext(), AddTaskActivity.class);
        //startActivityForResult(intent, 2404);
        adapterMatakuliah = new DetailMatakuliahListAdapter(DetailActivity.this, dMatakuliah);
        listSelect.setAdapter(adapterMatakuliah);
        adapterMatakuliah.notifyDataSetChanged();

        adapterTugaskuliah= new TugaskuliahListAdapter(DetailActivity.this, dTugaskuliah);
        listTugas.setAdapter(adapterTugaskuliah);
        adapterTugaskuliah.notifyDataSetChanged();

        listSelect.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if(layoutTambahTugas.getVisibility() == layoutTambahTugas.INVISIBLE){
                    layoutTambahTugas.setVisibility(layoutTambahTugas.VISIBLE);
                    listTugasLayout.setVisibility(listTugasLayout.VISIBLE);
                    scroll.setVisibility(scroll.VISIBLE);
                }else{
                    layoutTambahTugas.setVisibility(layoutTambahTugas.INVISIBLE);
                    listTugasLayout.setVisibility(listTugasLayout.INVISIBLE);
                    scroll.setVisibility(scroll.INVISIBLE);
                }
            }
        });

        opsiDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final List<ListOpsiData> tipeOpsi = new ArrayList<>();
                tipeOpsi.add(new ListOpsiData(R.drawable.baseline_update_black_18dp,"Update"));
                tipeOpsi.add(new ListOpsiData(R.drawable.baseline_delete_forever_black_18dp, "Delete"));

                CustomListOpsiAdapter customListOpsiAdapter = new CustomListOpsiAdapter(DetailActivity.this, R.layout.costum_list, tipeOpsi);
                listDetailOpsi.setAdapter(customListOpsiAdapter);
                if(listDetailOpsi.getVisibility() == listDetailOpsi.INVISIBLE){
                    listDetailOpsi.setVisibility(listDetailOpsi.VISIBLE);
                }else {
                    listDetailOpsi.setVisibility(listDetailOpsi.INVISIBLE);
                }
            }
        });

        listDetailOpsi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View viewList, int position, long l) {
                if(position == 0){
                    Intent intent = new Intent(DetailActivity.this, AddSchedule.class);
                    if(selectedkodeMK != null){
                        intent.putExtra("kodeMKUpdate", selectedkodeMK);
                        intent.putExtra("hariMKUpdate", selectedhariMK);
                    }else if(callBackKodeMK != null){
                        intent.putExtra("kodeMKUpdate", callBackKodeMK);
                        intent.putExtra("hariMKUpdate", callBackHariMK);
                    }else if(callBackUpdateKodeMK != null){
                        intent.putExtra("kodeMKUpdate", callBackUpdateKodeMK);
                        intent.putExtra("hariMKUpdate", callBackUpdateHariMK);
                    }else if(callBackUpateKodeMKTask != null){
                        intent.putExtra("kodeMKUpdate", callBackUpateKodeMKTask);
                        intent.putExtra("hariMKUpdate", callBackUpdateHariMKTask);
                    }
                    startActivity(intent);
                }else if (position == 1){
                    String chooseDay =dMatakuliah.get(0).getHariMK();
                    String chooseHours = dMatakuliah.get(0).getWaktu();
                    int dayOfWeek = 0 , hoursConvert = 0, minutesConvert = 0;

                    String[] inputNumber = chooseHours.split(":");
                    hoursConvert= Integer.parseInt(inputNumber[0]);
                    minutesConvert = Integer.parseInt(inputNumber[1]);


                    if (chooseDay.equals("Sunday")){
                        dayOfWeek = 2;
                    }else if(chooseDay.equals("Tuesday")){
                        dayOfWeek = 3;
                    }else if(chooseDay.equals("Wednesday")){
                        dayOfWeek = 4;
                    }else if(chooseDay.equals("Thursday")){
                        dayOfWeek = 5;
                    }else if(chooseDay.equals("Friday")){
                        dayOfWeek = 6;
                    }

                    if(selectedkodeMK != null){
                        database.open();
                        database.deleteData(selectedkodeMK);
                        database.close();

                        db.open();
                        db.deleteDataTaskOnDeleteMK(selectedkodeMK);
                        db.close();
                    }else if(callBackKodeMK != null){
                        database.open();
                        database.deleteData(callBackKodeMK);
                        database.close();

                        db.open();
                        db.deleteDataTaskOnDeleteMK(callBackKodeMK);
                        db.close();
                    }else if(callBackUpdateKodeMK != null){
                        database.open();
                        database.deleteData(callBackUpdateKodeMK);
                        database.close();

                        db.open();
                        db.deleteDataTaskOnDeleteMK(callBackUpdateKodeMK);
                        db.close();
                    }else if(callBackUpateKodeMKTask != null){
                        database.open();
                        database.deleteData(callBackUpateKodeMKTask);
                        database.close();

                        db.open();
                        db.deleteDataTaskOnDeleteMK(callBackUpateKodeMKTask);
                        db.close();
                    }
                    notificationScheduler.cancelReminder(DetailActivity.this, AlarmReceiver.class, hoursConvert, minutesConvert,dayOfWeek);
                    startActivity(new Intent(DetailActivity.this, MainActivity.class));
                    Toast.makeText(DetailActivity.this, "Mata Kuliah Berhasil di HAPUS", Toast.LENGTH_SHORT);
                }
            }
        });

        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, AddTaskActivity.class);
                if (selectedkodeMK != null){
                    intent.putExtra("kodeMK", selectedkodeMK);
                    intent.putExtra("sendHariMK", selectedhariMK);
                }else if(callBackUpdateKodeMK != null){
                    intent.putExtra("kodeMK", callBackUpdateKodeMK);
                    intent.putExtra("sendHariMK", callBackUpdateHariMK);
                }else if(callBackKodeMK != null){
                    intent.putExtra("kodeMK", callBackKodeMK);
                    intent.putExtra("sendHariMK", callBackHariMK);
                }else if(callBackUpateKodeMKTask != null){
                    intent.putExtra("kodeMK", callBackUpateKodeMKTask);
                    intent.putExtra("sendHariMK", callBackUpdateHariMKTask);
                }
                startActivity(intent);
            }
        });

        listTugas.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                int action = event.getAction();

                switch (action){
                    case MotionEvent.ACTION_DOWN:
                        listTugas.getParent().requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_UP:
                        listTugas.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                listTugas.onTouchEvent(event);
                return true;
            }
        });

        listTugas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {

                final String noTask = dTugaskuliah.get(position).getNoTask();
                final String kodeMK = dTugaskuliah.get(position).getKodeMK();
                final String hariMK = selectedhariMK;
                final String hariMKDua = callBackHariMK;
                final String hariMKTiga = callBackUpdateHariMK;

                final List<ListOpsiData> tipeOpsiTask = new ArrayList<>();
                tipeOpsiTask.add(new ListOpsiData(R.drawable.baseline_update_black_18dp,"Update"));
                tipeOpsiTask.add(new ListOpsiData(R.drawable.baseline_delete_forever_black_18dp, "Delete"));
                CustomListOpsiAdapter customOpsi = new CustomListOpsiAdapter(DetailActivity.this, R.layout.costum_list, tipeOpsiTask);

                new AlertDialog.Builder(DetailActivity.this).setTitle("Menu Pilihan").setAdapter(customOpsi, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {

                        if(position == 0){
                            Intent intent = new Intent(DetailActivity.this, AddTaskActivity.class);
                            intent.putExtra("updateNoTask", noTask);
                            intent.putExtra("updateKodeMKTask", kodeMK);
                            if(hariMK != null){
                                intent.putExtra("updateHariMKTask", hariMK);
                            }else if(hariMKDua != null){
                                intent.putExtra("updateHariMKTask", hariMKDua);
                            }else if(hariMKTiga != null){
                                intent.putExtra("updateHariMKTask", hariMKTiga);
                            }
                            startActivity(intent);

                        }else if(position == 1){
                            db.open();
                            db.deleteDataTask(noTask);
                            db.close();

                            db.open();
                            dTugaskuliah = db.DataTask(kodeMK);
                            db.close();

                            adapterTugaskuliah= new TugaskuliahListAdapter(DetailActivity.this, dTugaskuliah);
                            listTugas.setAdapter(adapterTugaskuliah);
                            adapterTugaskuliah.notifyDataSetChanged();
                            Toast.makeText(DetailActivity.this, "Data Berhasil di HAPUS", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == 2404){
            if(data != null){
                String value = data.getStringExtra("kodeMKBack");
            }
        }
    }
}
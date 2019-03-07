package id.ac.unsyiah.android.yourassistant;


import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import id.ac.unsyiah.android.yourassistant.alarm.AlarmReceiver;
import id.ac.unsyiah.android.yourassistant.alarm.NotificationScheduler;
import id.ac.unsyiah.android.yourassistant.database.MataKuliah;
import id.ac.unsyiah.android.yourassistant.database.QueryMK;

public class AddSchedule extends AppCompatActivity {

    EditText chooseTime, inputKodeMK, inputMK, inputRuangan, inputDosen;
    TextView formHari;
    TimePickerDialog timePickerDialog;
    Spinner pilihHari;
    Calendar calendar;
    int currentHour, currentMinutes, hourNotice, minutesNotice;
    QueryMK dataTambahJadwal;
    Button simpan;

    String tempWaktuSama;

    List<MataKuliah> data, jadwalBeradu;
    ArrayList<String> listMKBeradu;

    NotificationScheduler notificationScheduler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_add);

        getSupportActionBar().setTitle("Add Schedule");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        inputKodeMK = findViewById(R.id.kodeMK);
        inputMK = findViewById(R.id.mataKuliah);
        inputRuangan = findViewById(R.id.ruangan);
        inputDosen = findViewById(R.id.dosen);
        pilihHari = findViewById(R.id.hari);
        formHari = findViewById(R.id.formHari);
        chooseTime = findViewById(R.id.waktu);
        simpan = findViewById(R.id.simpanJadwal);

        dataTambahJadwal = new QueryMK(this);
        notificationScheduler = new NotificationScheduler();

        final String selectedkodeMK = getIntent().getStringExtra("kodeMKUpdate");
        final String selectedhariMK = getIntent().getStringExtra("hariMKUpdate");

        final List<String> hariList = new ArrayList<String>();
        hariList.add("Monday");
        hariList.add("Tuesday");
        hariList.add("Wednesday");
        hariList.add("Thursday");
        hariList.add("Friday");

        ArrayAdapter<String> hariAdapter = new ArrayAdapter<>(AddSchedule.this, R.layout.support_simple_spinner_dropdown_item, hariList);
        pilihHari.setAdapter(hariAdapter);

        pilihHari.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        chooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinutes = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(AddSchedule.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {

                        chooseTime.setText(String.format("%02d:%02d", hourOfDay, minutes));

                        if(hourOfDay <=12 && hourOfDay != 0){
                            hourNotice = hourOfDay - 1;
                            minutesNotice = minutes;
                        }else if(hourOfDay >= 12){
                            hourNotice = hourOfDay - 1;
                            minutesNotice = minutes;
                        }else {
                            hourNotice = hourOfDay;
                            minutesNotice = minutes;
                        }
                    }
                },currentHour,currentMinutes,false);
                timePickerDialog.show();
            }
        });

        inputKodeMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputKodeMK.requestFocus();
                inputKodeMK.setFocusableInTouchMode(true);

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(inputKodeMK, InputMethodManager.SHOW_FORCED);
            }
        });

        inputMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputMK.requestFocus();
                inputMK.setFocusableInTouchMode(true);

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(inputMK, InputMethodManager.SHOW_FORCED);
            }
        });

        inputRuangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputRuangan.requestFocus();
                inputRuangan.setFocusableInTouchMode(true);

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(inputRuangan, InputMethodManager.SHOW_FORCED);
            }
        });

        inputDosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputDosen.requestFocus();
                inputDosen.setFocusableInTouchMode(true);

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(inputDosen, InputMethodManager.SHOW_FORCED);
            }
        });

        if(selectedkodeMK != null){
            data =new ArrayList<>();
            dataTambahJadwal.open();
            data = dataTambahJadwal.DetailJadwalKuliah(selectedkodeMK);
            dataTambahJadwal.close();

            inputKodeMK.setText(data.get(0).getKodeMK());
            inputKodeMK.setEnabled(false);
            inputMK.setText(data.get(0).getNamaMK());
            inputDosen.setText(data.get(0).getDosen());
            inputRuangan.setText(data.get(0).getRuangMK());
            chooseTime.setText(data.get(0).getWaktu());
            tempWaktuSama = data.get(0).getWaktu();

            if("Monday".equals(data.get(0).getHariMK())){
                selectValue(pilihHari, "Monday");
            }else if("Tuesday".equals(data.get(0).getHariMK())){
                selectValue(pilihHari, "Tuesday");
            }else if("Wednesday".equals(data.get(0).getHariMK())){
                selectValue(pilihHari, "Wednesday");
            }else if("Thursday".equals(data.get(0).getHariMK())){
                selectValue(pilihHari, "Thursday");
            }else if("Friday".equals(data.get(0).getHariMK())){
                selectValue(pilihHari, "Friday");
            }

            formHari.setText(data.get(0).getHariMK());
        }

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listMKBeradu = new ArrayList<String>();

                if (selectedkodeMK != null){
                    if(inputKodeMK.getText().toString().equals("")){
                        inputKodeMK.setError("Kode MK wajib diisi");
                    }else if(inputMK.getText().toString().equals("")){
                        inputMK.setError("Nama MK wajib diisi");
                    }else if(inputDosen.getText().toString().equals("")){
                        inputDosen.setError("Nama Dosen wajib diisi");
                    }else if(inputRuangan.getText().toString().equals("")){
                        inputRuangan.setError("Ruangan wajib diisi");
                    }else if(chooseTime.getText().toString().equals("")){
                        chooseTime.setError("Waktu harus dipilih");
                    }else{
                        MataKuliah dataKuliah = new MataKuliah();
                        dataKuliah.setKodeMK(inputKodeMK.getText().toString());
                        dataKuliah.setNamaMK(inputMK.getText().toString());
                        dataKuliah.setDosen(inputDosen.getText().toString());
                        dataKuliah.setRuangMK(inputRuangan.getText().toString());
                        dataKuliah.setHariMK(pilihHari.getSelectedItem().toString());
                        dataKuliah.setWaktu(chooseTime.getText().toString());

                        dataTambahJadwal.open();
                        dataTambahJadwal.updateData(dataKuliah);
                        dataTambahJadwal.close();

                        if(pilihHari.getSelectedItem().toString().equals(selectedhariMK) && chooseTime.getText().toString().equals(tempWaktuSama)){
                            Toast.makeText(AddSchedule.this, "Data Sudah ada ALARM", Toast.LENGTH_SHORT).show();
                        }else{

                            String chooseDay = pilihHari.getSelectedItem().toString();
                            String tempWaktu = chooseTime.getText().toString();
                            int dayOfWeek = 0;

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

                            dataTambahJadwal.open();
                            jadwalBeradu = dataTambahJadwal.ListJadwalBeradu(chooseDay,tempWaktu);
                            dataTambahJadwal.close();

                            int hitungJadwalBeradu = jadwalBeradu.size();
                            String temp = "";
                            if(hitungJadwalBeradu > 1){
                                for(int i=0 ; i < jadwalBeradu.size(); i++){
                                    listMKBeradu.add(jadwalBeradu.get(i).getNamaMK().toString());
                                }
                                for (String s: listMKBeradu){
                                    temp += "/" + s ;
                                }
                                NotificationScheduler.showNotificationWarning(AddSchedule.this, MainActivity.class,
                                        "Your Schedule Warning", "" + temp);
                            }else {
                                NotificationScheduler.setReminder(AddSchedule.this,AlarmReceiver.class,
                                        hourNotice,minutesNotice, dayOfWeek);
                            }
                        }

                        Intent intent = new Intent(AddSchedule.this, DetailActivity.class);
                        intent.putExtra("updateKodeMK", selectedkodeMK);
                        intent.putExtra("updateHariMK", dataKuliah.getHariMK().toString());
                        startActivity(intent);
                        Toast.makeText(AddSchedule.this, "Data berhasil di UPDATE", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    tambahData();
                }
            }
        });
    }

    private void tambahData(){
        jadwalBeradu = new ArrayList<>();

        if(inputKodeMK.getText().toString().equals("")){
            inputKodeMK.setError("Kode MK wajib diisi");
        }else if(inputMK.getText().toString().equals("")){
            inputMK.setError("Nama MK wajib diisi");
        }else if(inputDosen.getText().toString().equals("")){
            inputDosen.setError("Nama Dosen wajib diisi");
        }else if(inputRuangan.getText().toString().equals("")){
            inputRuangan.setError("Ruangan wajib diisi");
        }else if(chooseTime.getText().toString().equals("")){
            chooseTime.setError("Waktu harus dipilih");
        }else{
            MataKuliah dataKuliah = new MataKuliah();
            dataKuliah.setKodeMK(inputKodeMK.getText().toString());
            dataKuliah.setNamaMK(inputMK.getText().toString());
            dataKuliah.setDosen(inputDosen.getText().toString());
            dataKuliah.setRuangMK(inputRuangan.getText().toString());
            dataKuliah.setHariMK(pilihHari.getSelectedItem().toString());
            dataKuliah.setWaktu(chooseTime.getText().toString());

            dataTambahJadwal.open();
            dataTambahJadwal.addData(dataKuliah);
            dataTambahJadwal.close();

            String chooseDay = pilihHari.getSelectedItem().toString();
            String tempWaktu = chooseTime.getText().toString();
            int dayOfWeek = 0;

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

            dataTambahJadwal.open();
            jadwalBeradu = dataTambahJadwal.ListJadwalBeradu(chooseDay,tempWaktu);
            dataTambahJadwal.close();

            int hitungJadwalBeradu = jadwalBeradu.size();
            String temp = "";

            if(hitungJadwalBeradu > 1 ){
                for(int i=0 ; i < jadwalBeradu.size(); i++){
                    listMKBeradu.add(jadwalBeradu.get(i).getNamaMK().toString());
                }
                for (String s: listMKBeradu){
                    temp += "/" + s ;
                }
                NotificationScheduler.showNotificationWarning(AddSchedule.this, MainActivity.class,
                        "Your Schedule Warning", "" + temp);
            }else {
                NotificationScheduler.setReminder(AddSchedule.this,AlarmReceiver.class, hourNotice,minutesNotice, dayOfWeek);
            }

            startActivity(new Intent(AddSchedule.this, MainActivity.class));
            Toast.makeText(this, "Data berhasil di INPUT", Toast.LENGTH_SHORT).show();
        }
    }

    private void selectValue(Spinner spinner, Object value){
        for(int position =0; position< spinner.getCount(); position++){
            if(spinner.getItemAtPosition(position).equals(value)){
                spinner.setSelection(position);
                break;
            }
        }
    }
}
package id.ac.unsyiah.android.yourassistant;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import id.ac.unsyiah.android.yourassistant.alarm.AlarmReceiver;
import id.ac.unsyiah.android.yourassistant.alarm.NotificationScheduler;
import id.ac.unsyiah.android.yourassistant.database.QueryTask;
import id.ac.unsyiah.android.yourassistant.database.TugasKuliah;

public class AddTaskActivity extends AppCompatActivity {

    EditText namaTugas, deskripsiTugas, pilihTanggal, pilihWaktu;
    Button simpanTugas;
    TimePickerDialog timePickerDialog;
    Spinner pilihTipeTugas, pilihPengingat;
    Calendar calendar;
    QueryTask query;
    int year,month, dayofMonth, currentHour, currentMinutes, tempYear, tempMonth, tempDay, tempHour, tempMinutes;

    List<TugasKuliah> dataTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        getSupportActionBar().setTitle("Add Task Schedule");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        namaTugas = findViewById(R.id.namaTugas);
        deskripsiTugas = findViewById(R.id.deskripsiTugas);
        pilihTanggal = findViewById(R.id.DLDate);
        pilihWaktu = findViewById(R.id.DLTime);
        pilihTipeTugas = findViewById(R.id.pilihTipeTugas);
        pilihPengingat = findViewById(R.id.hariPengingat);
        simpanTugas = findViewById(R.id.simpanTugas);

        query = new QueryTask(this);
        final String selectedNoTask = getIntent().getStringExtra("updateNoTask");

        final List<String> tipeTugas = new ArrayList<String>();
        tipeTugas.add("Quiz");
        tipeTugas.add("Tugas");
        tipeTugas.add("Project");
        tipeTugas.add("Midterm");
        tipeTugas.add("Final");
        ArrayAdapter<String> tugasAdapter = new ArrayAdapter<>(AddTaskActivity.this, R.layout.support_simple_spinner_dropdown_item, tipeTugas);
        pilihTipeTugas.setAdapter(tugasAdapter);

        final List<String> hariPengingat = new ArrayList<String>();
        hariPengingat.add("1Hari");
        hariPengingat.add("2Hari");
        hariPengingat.add("3Hari");
        ArrayAdapter<String> pengingatAdapter = new ArrayAdapter<>(AddTaskActivity.this, R.layout.support_simple_spinner_dropdown_item, hariPengingat);
        pilihPengingat.setAdapter(pengingatAdapter);

        namaTugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                namaTugas.requestFocus();
                namaTugas.setFocusableInTouchMode(true);

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(namaTugas, InputMethodManager.SHOW_FORCED);
            }
        });

        deskripsiTugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deskripsiTugas.requestFocus();
                deskripsiTugas.setFocusableInTouchMode(true);

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(deskripsiTugas, InputMethodManager.SHOW_FORCED);
            }
        });

        pilihTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayofMonth = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddTaskActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month +=1;
                        pilihTanggal.setText(String.format("%02d.%02d.%02d", year, month, day));
                        tempYear = year;
                        tempMonth = month;
                        tempDay = day;
                    }
                }, year,month,dayofMonth);
                datePickerDialog.show();
            }
        });

        pilihWaktu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinutes = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(AddTaskActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {

                        tempHour = hourOfDay;
                        tempMinutes = minutes;

                        pilihWaktu.setText(String.format("%02d:%02d", hourOfDay, minutes));

                    }
                },currentHour,currentMinutes,false);
                timePickerDialog.show();
                String timeNotice =String.format("%02d", currentHour);
                Toast.makeText(AddTaskActivity.this, "" +timeNotice, Toast.LENGTH_SHORT).show();
            }
        });

        if(selectedNoTask != null){
            dataTask =new ArrayList<>();
            query.open();
            dataTask = query.UpdateDataTask(selectedNoTask);
            query.close();

            namaTugas.setText(dataTask.get(0).getJudulTask());
            deskripsiTugas.setText(dataTask.get(0).getDeskripsiTask());
            pilihTanggal.setText(dataTask.get(0).getD_dateTask());
            pilihWaktu.setText(dataTask.get(0).getD_timeTask());

            if("Quiz".equals(dataTask.get(0).getTipeTask())){
                selectValue(pilihTipeTugas, "Quiz");
            }else if("Tugas".equals(dataTask.get(0).getTipeTask())){
                selectValue(pilihTipeTugas, "Tugas");
            }else if("Project".equals(dataTask.get(0).getTipeTask())){
                selectValue(pilihTipeTugas, "Project");
            }else if("Midterm".equals(dataTask.get(0).getTipeTask())){
                selectValue(pilihTipeTugas, "Midterm");
            }else if("Final".equals(dataTask.get(0).getTipeTask())){
                selectValue(pilihTipeTugas, "Final");
            }

            if("1Hari".equals(dataTask.get(0).getPengingatTask())){
                selectValue(pilihPengingat, "1Hari");
            }else if("2Hari".equals(dataTask.get(0).getPengingatTask())){
                selectValue(pilihPengingat, "2Hari");
            }else if("3Hari".equals(dataTask.get(0).getPengingatTask())){
                selectValue(pilihPengingat, "3Hari");
            }
        }

        simpanTugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            final String selectedkodeMK = getIntent().getStringExtra("kodeMK");
            final String selectedhariMK = getIntent().getStringExtra("sendHariMK");
            final String selectedKodeMKTask = getIntent().getStringExtra("updateKodeMKTask");
            final String selectedHariMKTask = getIntent().getStringExtra("updateHariMKTask");

            if(namaTugas.getText().toString().equals("")){
                namaTugas.setError("Nama Tugas wajib diisi");
            }else if(deskripsiTugas.getText().toString().equals("")){
                deskripsiTugas.setError("Deskripsi Tugas wajib diisi");
            }else if(pilihTanggal.getText().toString().equals("")){
                pilihTanggal.setError("Tanggal wajib diisi");
            }else if(pilihWaktu.getText().toString().equals("")){
                pilihWaktu.setError("Waktu harus diisi");
            }else{
                TugasKuliah dataTask = new TugasKuliah();

                if(selectedNoTask != null){
                    dataTask.setKodeMK(selectedKodeMKTask);
                    dataTask.setNoTask(selectedNoTask);
                    dataTask.setJudulTask(namaTugas.getText().toString());
                    dataTask.setDeskripsiTask(deskripsiTugas.getText().toString());
                    dataTask.setTipeTask(pilihTipeTugas.getSelectedItem().toString());
                    dataTask.setD_dateTask(pilihTanggal.getText().toString());
                    dataTask.setD_timeTask(pilihWaktu.getText().toString());
                    dataTask.setPengingatTask(pilihPengingat.getSelectedItem().toString());

                    query.open();
                    query.updateDataTaskProses(dataTask);
                    query.close();
                    Toast.makeText(AddTaskActivity.this, "Data berhasil di UPDATE", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddTaskActivity.this, DetailActivity.class);
                    intent.putExtra("callUpdateTaskNoMK", selectedKodeMKTask);
                    intent.putExtra("callUpdateTaskHariMK", selectedHariMKTask);
                    startActivity(intent);
                }else{

                    dataTask.setKodeMK(selectedkodeMK);
                    dataTask.setJudulTask(namaTugas.getText().toString());
                    dataTask.setDeskripsiTask(deskripsiTugas.getText().toString());
                    dataTask.setTipeTask(pilihTipeTugas.getSelectedItem().toString());
                    dataTask.setD_dateTask(pilihTanggal.getText().toString());
                    dataTask.setD_timeTask(pilihWaktu.getText().toString());
                    dataTask.setPengingatTask(pilihPengingat.getSelectedItem().toString());

                    String tempPengingat = pilihPengingat.getSelectedItem().toString();

                    /*if(tempPengingat.equals("1Hari")){
                        calendar = Calendar.getInstance();
                        calendar.set(Calendar.YEAR, tempYear);
                        calendar.set(Calendar.MONTH, tempMonth);
                        calendar.set(Calendar.DAY_OF_MONTH, tempDay);

                        calendar.add(Calendar.DATE, -1);

                        //String dayOfTask = (String) DateFormat.format("dd", calendar.DAY_OF_MONTH);
                        Toast.makeText(AddTaskActivity.this, "" +calendar, Toast.LENGTH_LONG).show();
                    }*/

                    query.open();
                    query.addDataTask(dataTask);
                    query.close();

                    NotificationScheduler.scheduleNotification(AddTaskActivity.this, AlarmReceiver.class, tempHour, tempMinutes,
                            tempDay,tempMonth, tempYear, tempPengingat);

                    Intent intent = new Intent(AddTaskActivity.this, DetailActivity.class);
                    intent.putExtra("kodeMKCallBack", selectedkodeMK);
                    intent.putExtra("hariMKCallBack", selectedhariMK);
                    startActivity(intent);
                    Toast.makeText(AddTaskActivity.this, "Data berhasil di INPUT", Toast.LENGTH_SHORT).show();
                }
            }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                final String selectedkodeMK = getIntent().getStringExtra("kodeMK");
                Intent intent = new Intent();
                intent.putExtra("kodeMKBack", selectedkodeMK);
                setResult(RESULT_OK, intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
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
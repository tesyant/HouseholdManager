package com.lulua.tesyant.householdmanager.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lulua.tesyant.householdmanager.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

public class FixedNeedsAddActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText edtNama, edtHarga, edtJumlah;
    Button btnPickDate, btnSave;
    Calendar calendar;
    DatePickerDialog datePickerDialog;
    int Year, Month, Day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixed_needs_add);

//        edtNama = findViewById(R.id.edt_fixedName);
//        edtHarga = findViewById(R.id.edt_fixedPrice);
//        edtJumlah = findViewById(R.id.edt_fixedQuantity);
        btnPickDate = findViewById(R.id.btn_pickdate);
        btnSave = findViewById(R.id.btn_save);

        calendar = Calendar.getInstance();
        Year = calendar.get(Calendar.YEAR);
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);

        btnPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog = DatePickerDialog.newInstance(FixedNeedsAddActivity.this, Year, Month, Day);
                datePickerDialog.setThemeDark(false);
                datePickerDialog.showYearPickerFirst(false);
                datePickerDialog.setAccentColor(Color.parseColor("#67087B"));
                datePickerDialog.setTitle("Select Date");
                datePickerDialog.show(getFragmentManager(), "DatePickerDialog");
            }
        });

//        String nama = edtNama.getText().toString();
//        String harga = edtHarga.getText().toString();
//        String jumlah = edtJumlah.getText().toString();

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date ="Selected Date : " + Day + " - " + Month + " - " + Year;
        Toast.makeText(FixedNeedsAddActivity.this, date, Toast.LENGTH_SHORT).show();

    }
}

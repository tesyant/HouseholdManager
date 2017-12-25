package com.lulua.tesyant.householdmanager.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.lulua.tesyant.householdmanager.R;
import com.lulua.tesyant.householdmanager.db.FixedNeedsHelper;
import com.lulua.tesyant.householdmanager.models.FixedNeeds;

import java.util.Calendar;

public class AddFixedNeedsActivity extends AppCompatActivity implements View.OnClickListener{

    public static String EXTRA_FIXED_NEEDS = "extra_fixed";
    public static String EXTRA_POSITION = "position";

    private boolean isEdit = false;
    public static int REQUEST_ADD = 100;
    public static int RESULT_ADD = 101;
    public static int REQUEST_UPDATE = 200;
    public static int RESULT_UPDATE = 201;
    public static int RESULT_DELETE = 301;

    private FixedNeeds fixedNeeds;
    private int position;
    private FixedNeedsHelper fixedNeedsHelper;

    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fixed_needs);

        dateView =(TextView) findViewById(R.id.tv_date);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month +1, day);

        fixedNeedsHelper = new FixedNeedsHelper(this);
        fixedNeedsHelper.open();

        fixedNeeds = getIntent().getParcelableExtra(EXTRA_FIXED_NEEDS);

        if (fixedNeeds != null) {
            position = getIntent().getIntExtra(EXTRA_POSITION, 0);
            isEdit = true;
        }

        String actionBarTitle = null;
        String btnTitle = null;

        if (isEdit) {
            actionBarTitle = "Ubah";

        }
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view){
        showDialog(999);
        Toast.makeText(getApplicationContext(), "Pilih Tanggal", Toast.LENGTH_SHORT).show();
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener(){
    @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        showDate(i, i1+1, i2);
        }
    };

    private void showDate(int year, int i, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
        .append(month).append("/").append(year));
    }

    @Override
    public void onClick(View view) {

    }
}

package com.lulua.tesyant.householdmanager.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lulua.tesyant.householdmanager.R;
import com.lulua.tesyant.householdmanager.db.UnfixedNeedsHelper;
import com.lulua.tesyant.householdmanager.models.UnfixedNeeds;

public class AddUnfixedNeedsActivity extends AppCompatActivity {

    public static String EXTRA_UNFIXED_NEEDS = "extra_unfixed";
    public static String EXTRA_UNFIXED_POSITION = "unfixed_position";

    private boolean isEdit = false;
    public static int REQUEST_ADD = 100;
    public static int RESULT_ADD = 101;
    public static int REQUEST_UPDATE = 200;
    public static int RESULT_UPDATE = 201;
    public static int RESULT_DELETE = 301;

    private UnfixedNeeds unfixedNeeds;
    private int position;
    private UnfixedNeedsHelper unfixedNeedsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_unfixed_needs);

        unfixedNeedsHelper = new UnfixedNeedsHelper(this);
        unfixedNeedsHelper.open();

        unfixedNeeds = getIntent().getParcelableExtra(EXTRA_UNFIXED_NEEDS);

        if (unfixedNeeds != null) {
            position = getIntent().getIntExtra(EXTRA_UNFIXED_POSITION, 0);
            isEdit = true;
        }

        String actionBarTitle = null;
        String btnTitle = null;

        if (isEdit) {
            actionBarTitle = "Ubah";

        }

    }
}

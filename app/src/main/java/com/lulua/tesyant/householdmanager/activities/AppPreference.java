package com.lulua.tesyant.householdmanager.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.lulua.tesyant.householdmanager.R;

/**
 * Created by tesyant on 24/12/17.
 */

public class AppPreference {

    SharedPreferences preferences;
    Context context;

    public AppPreference(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.context = context;
    }

    public void setFirstRun(Boolean input) {
        SharedPreferences.Editor editor = preferences.edit();
        String key = context.getResources().getString(R.string.app_name);
        editor.putBoolean(key, input);
        editor.commit();
    }

    public Boolean getFirstRun() {
        String key = context.getResources().getString(R.string.app_name);
        return preferences.getBoolean(key, true);
    }
}

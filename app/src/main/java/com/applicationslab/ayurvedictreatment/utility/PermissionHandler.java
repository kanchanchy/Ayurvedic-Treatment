package com.applicationslab.ayurvedictreatment.utility;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.applicationslab.ayurvedictreatment.R;


public class PermissionHandler {

    private static final int REQUEST_SETTINGS = 444;

    private Activity activity;

    public PermissionHandler(Context context) {
        this.activity = (Activity) context;
    }

    public boolean hasAccessFineLocationPermission() {
        return ContextCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;
    }

    public boolean hasWriteExternalStoragePermission() {
        return ContextCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;
    }

    public void showPermissionSettingsSnackbar(String message) {

        View view = activity.findViewById(android.R.id.content);
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .setAction("Settings", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent settingsIntent = new Intent();
                        settingsIntent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        settingsIntent.addCategory(Intent.CATEGORY_DEFAULT);
                        settingsIntent.setData(Uri.parse("package:" + activity.getPackageName()));
                        settingsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        settingsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        settingsIntent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                        activity.startActivityForResult(settingsIntent, REQUEST_SETTINGS);
                    }
                })
                .setActionTextColor(ContextCompat.getColor(activity, R.color.colorPrimary))
                .show();
    }
}

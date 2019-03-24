package com.example.android.assginment7.filedownload;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class DownloadNotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
            Toast.makeText(context, "Download Completed!", Toast.LENGTH_SHORT).show();
        }
    }
}

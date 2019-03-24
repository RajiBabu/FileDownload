package com.example.android.assginment7.filedownload;

import android.app.DownloadManager;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.Nullable;

public class DownloadIntentService extends IntentService {


    public DownloadIntentService() {
        super("DownloadIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String[] urls = intent.getStringArrayExtra("URLs");
        for (int index = 0; index < urls.length; index++) {
            long downloadId = downloadFile(urls[index], "pdf" + index + ".pdf");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException exception) {

            }
        }

    }

    public long downloadFile(String urlStr, String fileName) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(urlStr));
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);
        DownloadManager manager = (DownloadManager) getBaseContext().getSystemService(Context.DOWNLOAD_SERVICE);
        long downloadId = manager.enqueue(request);
        return downloadId;
    }

}

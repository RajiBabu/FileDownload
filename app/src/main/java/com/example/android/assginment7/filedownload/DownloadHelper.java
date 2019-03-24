package com.example.android.assginment7.filedownload;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;


public class DownloadHelper {

    private Context context;

    public DownloadHelper(Context context) {
        this.context = context;
    }

    public long downloadFile(String urlStr, String fileName) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(urlStr));
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);
        DownloadManager manager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        long downloadId = manager.enqueue(request);
        return downloadId;
    }

    public class DownloadTask extends AsyncTask<String, Integer, Long> {
        @Override
        protected Long doInBackground(String... urls) {
            int count = urls.length;

            return 0L;
        }

        protected void onProgressUpdate(Integer... progress) {
            Log.d("Downloading files",
                    String.valueOf(progress[0]) + "% downloaded");
            Toast.makeText(context,
                    String.valueOf(progress[0]) + "% downloaded",
                    Toast.LENGTH_LONG).show();
        }

        protected void onPostExecute(Long result) {
            Toast.makeText(context,
                    "Downloaded " + result + " bytes",
                    Toast.LENGTH_LONG).show();
        }
    }
}

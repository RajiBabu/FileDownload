package com.example.android.assginment7.filedownload;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    DownloadBindService mService;
    boolean mBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkStoragePermission();

        EditText pdfURL1 = (EditText) findViewById(R.id.pdf1);
        EditText pdfURL2 = (EditText) findViewById(R.id.pdf2);
        EditText pdfURL3 = (EditText) findViewById(R.id.pdf3);
        EditText pdfURL4 = (EditText) findViewById(R.id.pdf4);
        EditText pdfURL5 = (EditText) findViewById(R.id.pdf5);

        pdfURL1.setText("http://www.sjsu.edu/map/docs/campus-map.pdf");
        pdfURL2.setText("http://www.cisco.com/web/about/ac79/docs/innov/IoE_Economy.pdf");
        pdfURL3.setText("http://www.cisco.com/web/strategy/docs/gov/everything-for-cities.pdf");
        pdfURL4.setText("http://www.sjsu.edu/up/docs/up_org_chart.pdf");
        pdfURL5.setText("http://www.sjsu.edu/isa/docs/declaration_of_finance.pdf");
    }


    public void startDownload(View view) {
        Intent intent = new Intent(getBaseContext(), DownloadIntentService.class);
        try {
            String[] urls = new String[5];
            urls[0] = "http://www.sjsu.edu/map/docs/campus-map.pdf";
            urls[1] = "http://www.cisco.com/web/about/ac79/docs/innov/IoE_Economy.pdf";
            urls[2] = "http://www.cisco.com/web/strategy/docs/gov/everything-for-cities.pdf";
            urls[3] = "http://www.sjsu.edu/up/docs/up_org_chart.pdf";
            urls[4] = "http://www.sjsu.edu/isa/docs/declaration_of_finance.pdf";
            intent.putExtra("URLs", urls);

            startService(intent);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public boolean checkStoragePermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.e("Permission error", "You have permission");
                return true;
            } else {

                Log.e("Permission error", "You have asked for permission");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else {
            Log.e("Permission error", "You already have the permission");
            return true;
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {

            DownloadBindService.DownloadBinder binder = (DownloadBindService.DownloadBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };
}

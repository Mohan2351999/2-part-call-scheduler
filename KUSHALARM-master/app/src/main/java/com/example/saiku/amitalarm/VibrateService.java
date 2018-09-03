package com.example.saiku.amitalarm;

import android.Manifest;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.os.Handler;

public class VibrateService extends IntentService {
    public VibrateService() {
        super("SERVICE");
        // TODO Auto-generated constructor stub
    }

    public void onHandleIntent(Intent intent) {
        // Get Vibrator instance
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        long pattern[] = {2000, 1000, 2000, 1000, 2000, 1000};
        v.vibrate(pattern, -1);


}

}




package com.example.saiku.amitalarm;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.saiku.amitalarm.R;
import com.example.saiku.amitalarm.VibrateService;
import com.karan.churi.PermissionManager.PermissionManager;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    int done = 1;

    PermissionManager permissionManager;
    private static void myTask() {
        System.out.println("Running");
    }

    TimePicker datePicker;
    long millitime1;


    Button check;
    @SuppressLint({"WrongViewCast", "DefaultLocale"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        permissionManager  =new PermissionManager() {};
        permissionManager.checkAndRequestPermissions(this);
        datePicker=(TimePicker) findViewById(R.id.timepicker);
        check=(Button)findViewById(R.id.check);
        //SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");


        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour = datePicker.getCurrentHour();
                int min = datePicker.getCurrentMinute();
                int ss=00;

                String hr=Integer.toString(hour);
                String mn=Integer.toString(min);
                String sc=Integer.toString(ss);

                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                String dateInString = hr+":"+mn+":"+sc;
                Date date = null;
                try {
                    date = sdf.parse(dateInString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //System.out.println(dateInString);
                //System.out.println("Date - Time in milliseconds : " + date.getTime());
                long endtime =  date.getTime();

                //Calendar calendar = Calendar.getInstance();
                //calendar.setTime(date);
                //System.out.println("Calender - Time in milliseconds : " + calendar.getTimeInMillis());
                //int starttime = (int) calendar.getTimeInMillis();

                SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");

                final String str= sdf.format(new Date());
                Date date1 = null;
                try {
                    date1 = sdf.parse(str);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                long starttime = date1.getTime();

                starttime+=19800000;
                endtime+=19800000;

                if(endtime>=starttime)
                {
                    //Toast.makeText(MainActivity.this, String.format("The test string is %s\nThe time  present is :%d \nThe time left is : %d",dateInString,starttime, (endtime-starttime)),Toast.LENGTH_LONG).show();
                    millitime1=(endtime-starttime);
                    Toast.makeText(MainActivity.this,String.format("The time remaining is %d",millitime1),Toast.LENGTH_LONG).show();
                }

                else
                {
                    //Toast.makeText(MainActivity.this, String.format("The test strikng is %s\nThe time  present is :%d \nThe time left is : %d",dateInString,starttime, (endtime+(86400000-starttime))),Toast.LENGTH_LONG).show();
                    millitime1=(endtime+(86400000-starttime));
                    Toast.makeText(MainActivity.this,String.format("The time remaining is %d",millitime1),Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionManager.checkResult(requestCode,permissions,grantResults);
    }


    public void alarm(View view) {

        Toast.makeText(MainActivity.this,"The alarm activated!!!!",Toast.LENGTH_LONG).show();

        int hour = datePicker.getCurrentHour();
        int min = datePicker.getCurrentMinute();
        int ss=00;

        String hr=Integer.toString(hour);
        String mn=Integer.toString(min);
        String sc=Integer.toString(ss);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String dateInString = hr+":"+mn+":"+sc;
        Date date = null;
        try {
            date = sdf.parse(dateInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //System.out.println(dateInString);
        //System.out.println("Date - Time in milliseconds : " + date.getTime());
        long endtime =  date.getTime();

        //Calendar calendar = Calendar.getInstance();
        //calendar.setTime(date);
        //System.out.println("Calender - Time in milliseconds : " + calendar.getTimeInMillis());
        //int starttime = (int) calendar.getTimeInMillis();

        SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");

        final String str= sdf.format(new Date());
        Date date1 = null;
        try {
            date1 = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long starttime = date1.getTime();
        starttime+=19800000;
        endtime+=19800000;

        if(endtime>=starttime)
        {
            //Toast.makeText(MainActivity.this, String.format("The test string is %s\nThe time  present is :%d \nThe time left is : %d",dateInString,starttime, (endtime-starttime)),Toast.LENGTH_LONG).show();
            millitime1=(endtime-starttime);
            Toast.makeText(MainActivity.this,String.format("The time remaining is %d",millitime1),Toast.LENGTH_LONG).show();
        }

        else
        {
            //Toast.makeText(MainActivity.this, String.format("The test strikng is %s\nThe time  present is :%d \nThe time left is : %d",dateInString,starttime, (endtime+(86400000-starttime))),Toast.LENGTH_LONG).show();
            millitime1=(endtime+(86400000-starttime));
            Toast.makeText(MainActivity.this,String.format("The time remaining is %d",millitime1),Toast.LENGTH_LONG).show();
        }

        //Toast.makeText(MainActivity.this,String.format("The alarm is activated !!\n The total time is %d",millitime1),Toast.LENGTH_LONG).show();

        long wait_time = millitime1;
        long wait_time2 = millitime1;
        long millis = System.currentTimeMillis();
        long millis2 = System.currentTimeMillis();

        long check_time = wait_time + millis;
        while (System.currentTimeMillis() <= check_time) {
            if (check_time == System.currentTimeMillis()) {
                Intent intent = new Intent(this, VibrateService.class);
                startService(intent);


                new Thread(){
                    public void run(){
                        try {
                            sleep(15000);
                            Intent o=new Intent (MainActivity.this,Main2Activity.class);
                            startActivity(o);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        }
    }

    public void onCall() {
        long check_time1 = System.currentTimeMillis();
        while (System.currentTimeMillis() <= check_time1)
            if (check_time1 == System.currentTimeMillis()) {
                if (done == 2) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL); //use ACTION_CALL class
                    callIntent.setData(Uri.parse("tel:9949197203"));    //this is the phone number calling
                    //check permission
                    //If the device is running Android 6.0 (API level 23) and the app's targetSdkVersion is 23 or higher,
                    //the system asks the user to grant approval.
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        //request permission from user if the app hasn't got the required permission
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.CALL_PHONE},   //request specific permission from user
                                10);
                    } else {     //have got permission
                        try {
                            startActivity(callIntent); //call activity and make phone call
                        } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(getApplicationContext(), "yourActivity is not founded", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
    }

    /*public long seconds() {
        EditText time_wait = (EditText) findViewById(R.id.sec);
        long time = Long.parseLong(time_wait.getText().toString());

        //long time = millitime1;
        return millitime1;
    }*/

    public void stop(View view) {
        done = done + 1;
        onCall();
    }
}


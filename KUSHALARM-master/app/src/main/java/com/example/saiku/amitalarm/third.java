package com.example.saiku.amitalarm;

import android.content.Intent;
        import android.net.Uri;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.widget.Toast;

/**
 * Created by Amit on 12/10/2017.
 */


public class third extends AppCompatActivity{
    public void makeph() {

    }
    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Toast.makeText(getApplicationContext(),"entered third class",Toast.LENGTH_LONG).show();
        Intent call = new Intent(Intent.ACTION_CALL);
        call.setData(Uri.parse("tel:7604836263"));
        Toast.makeText(getApplicationContext(),"entered third class",Toast.LENGTH_LONG).show();
        if (call.resolveActivity(getPackageManager()) != null) {
            startActivity(call);
        }

    }


}

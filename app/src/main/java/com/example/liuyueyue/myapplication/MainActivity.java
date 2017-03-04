package com.example.liuyueyue.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {
private Button mSendSmsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSendSmsButton = (Button) findViewById(R.id.btn_sendsms);
        mSendSmsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri smsToUri = Uri.parse("smsto:14785697905");
                Intent intent = new Intent(Intent.ACTION_SENDTO,smsToUri);
                intent.putExtra("sms_body","测试发送短信");
                startActivity(intent);
            }
        });
        Button makeCall = (Button) findViewById(R.id.make_call);
        makeCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE)!=
        PackageManager.PERMISSION_GRANTED){
    ActivityCompat.requestPermissions(MainActivity.this,
            new String[]{Manifest.permission.CALL_PHONE},1);
            }else {
    call();
}
}
            });
            }
    private void call(){
        try {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:14785697905"));
            startActivity(intent);
        }catch(SecurityException e){
            e.printStackTrace();
        }

    }
    public void onRequestPermissionsResult(int requestCode,String[]permission,int[] grantResults){
        switch(requestCode) {
            case 1:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    call();
                    Toast.makeText(this,"you denid the permission",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }

        }
    }


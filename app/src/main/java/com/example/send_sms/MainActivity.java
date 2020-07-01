package com.example.send_sms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText txtPhone,txtMessage;
    Button btn_Send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtPhone=(EditText)findViewById(R.id.txt_Phone);
        txtMessage=(EditText)findViewById(R.id.txt_Message);
        btn_Send=(Button)findViewById(R.id.btnSend);
    }

    public void btn_send(View view) {
        int Permissioncheck= ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        if (Permissioncheck== PackageManager.PERMISSION_GRANTED){

            MyMessage();

        }else {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},0);
        }
    }

    private void MyMessage() {
        String phoneNo=txtPhone.getText().toString().trim();
        String Message=txtMessage.getText().toString().trim();
        if (!txtPhone.getText().toString().equals("")||!txtMessage.getText().toString().equals("")) {

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, Message, null, null);
            Toast.makeText(this, "Message Send", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, "Please Enter Number Or Message", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 0:
                if (grantResults.length>=0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    MyMessage();

                }else {
                    Toast.makeText(this, "You don't Reqired permission to make this Action", Toast.LENGTH_SHORT).show();
                }


                break;

        }
    }
}
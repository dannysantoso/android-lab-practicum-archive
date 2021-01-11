package com.example.bookwormproject;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import android.Manifest;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import static android.text.TextUtils.isEmpty;

public class ConfirmationCode extends AppCompatActivity {

    TextView resend;
    EditText code;
    Button codeButton;
    String generateCode, username, fullname, password, address, email, phone;

    SmsManager smsManager;
    int smsPermissionSend;


    DatabaseHelper db;

    Register regis = new Register();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_code);
        db = new DatabaseHelper(this);

        code = findViewById(R.id.code);
        codeButton = findViewById(R.id.codeButton);
        resend = findViewById(R.id.resend);

        //passing data dari register class
        username = getIntent().getStringExtra("username");
        fullname = getIntent().getStringExtra("fullname");
        password = getIntent().getStringExtra("password");
        address = getIntent().getStringExtra("address");
        email = getIntent().getStringExtra("email");
        phone = getIntent().getStringExtra("phone");
        generateCode = getIntent().getStringExtra("code");

        smsManager = SmsManager.getDefault();

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                smsManager = SmsManager.getDefault();
                smsPermissionSend = ContextCompat.checkSelfPermission(ConfirmationCode.this, Manifest.permission.SEND_SMS);

                if(smsPermissionSend != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(ConfirmationCode.this, new String[]{Manifest.permission.SEND_SMS},1);
                }
                generateCode  = regis.generateCode();
                /*smsManager.sendTextMessage("5554", null, generateCode, null, null); ini untuk defaultnya buat ngirim sms*/
                smsManager.sendTextMessage(phone, null, generateCode, null, null);
            }
        });

        codeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEmpty(code.getText().toString())){
                    code.setError("you must fill this field");
                }else {
                    if (code.getText().toString().equals(generateCode)) {

                        //generate new member id
                        Cursor data2 = db.getLastID();
                        data2.moveToNext();

                        String lastID = data2.getString(0);
                        Integer idNumber = Integer.parseInt(lastID.substring(2, 5));
                        idNumber++;
                        String MemberID = "MB" + String.format("%03d", idNumber);

                        //input data to database
                        boolean result = db.insertMember(MemberID, username, fullname, password, address, email, phone);
                        if (result) {
                            Toast.makeText(ConfirmationCode.this, "Insertion Success", Toast.LENGTH_SHORT).show();
                        }

                        Intent intent = new Intent(ConfirmationCode.this, Home.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(ConfirmationCode.this, "Insertion Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}

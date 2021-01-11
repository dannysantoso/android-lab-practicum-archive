package com.example.bookwormproject;

import androidx.appcompat.app.AlertDialog;
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




import java.util.Random;


import static android.text.TextUtils.isEmpty;

public class Register extends AppCompatActivity {

    private EditText etUsername, etPassword, etAddress, etFullname, etConfirm, etEmail, etPhone;
    private Button btRegister, btLogin;

    SmsManager smsManager;
    int smsPermissionSend;
    String generateCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        etUsername = findViewById(R.id.username);
        etFullname = findViewById(R.id.fullname);
        etPassword = findViewById(R.id.password);
        etConfirm = findViewById(R.id.confirmPassword);
        etAddress = findViewById(R.id.address);
        etEmail = findViewById(R.id.email);
        etPhone = findViewById(R.id.phoneNumber);


        btRegister = findViewById(R.id.registerButton);
        btLogin = findViewById(R.id.loginButton);

        smsManager = SmsManager.getDefault();
        smsPermissionSend = ContextCompat.checkSelfPermission(Register.this, Manifest.permission.SEND_SMS);

        if(smsPermissionSend != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(Register.this, new String[]{Manifest.permission.SEND_SMS},1);
        }

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Register.this, ConfirmationCode.class);


                String username = etUsername.getText().toString();
                String fullname = etFullname.getText().toString();
                String password = etPassword.getText().toString();
                String confirm = etConfirm.getText().toString();
                String address = etAddress.getText().toString();
                String email = etEmail.getText().toString();
                String phone = etPhone.getText().toString();

                if(isEmpty(username)){
                    etUsername.setError("you must fill this field");
                }else if (username.length() < 3) {
                    etUsername.setError("username must be more than 3 character");
                }else if (isEmpty(fullname)) {
                    etFullname.setError("you must fill this field");
                } else if (fullnameAlphabet() == false) {
                    etFullname.setError("fullname must be alphabet only");
                }else if (isEmpty(password)) {
                    etPassword.setError("you must fill this field");
                }else if (password.length() < 6) {
                    etPassword.setError("password must be more than 6 character");
                }else if(passwordContainAlphaNumeric() == false){
                    etPassword.setError("password must contain letter and numeric");
                }else if (!confirm.equals(password)) {
                    etConfirm.setError("Confirm password must same as password");
                }else if(isEmpty(address)) {
                    etAddress.setError("you must fill this field");
                }else if(isEmpty(email)){
                    etEmail.setError("you must fill this field");
                }else if(validateEmail() == false) {
                    etEmail.setError("error email format");
                }else if (!email.endsWith("@bookparadise.com")){
                    etEmail.setError("error email format");
                }else if(isEmpty(phone)) {
                    etPhone.setError("you must fill this field");
                }else if(phone.length() < 9 || phone.length() > 15) {
                    etPhone.setError("phone number must between 9 - 15");
                }else if(phoneNumeric() == false){
                    etPhone.setError("phone must be number only");
                }else{
                    sms();

                    intent.putExtra("username",username);
                    intent.putExtra("fullname",fullname);
                    intent.putExtra("password",password);
                    intent.putExtra("address",address);
                    intent.putExtra("email",email);
                    intent.putExtra("phone",phone);
                    intent.putExtra("code",generateCode);

                    startActivity(intent);
                }

            }
        });
    }

    boolean validateEmail(){
        int container1 = 0;
        int container2 = 0;
        String email = etEmail.getText().toString();

        for(int i = 0; i<email.length();i++){
            if(email.charAt(i)=='@'){
                container1 = container1 + 1;
            }else if(email.charAt(i)=='.'){
                container2 = container2 + 1;
            }
        }
        if(container1 > 1 || container2 < 1){
            return false;
        }else{
            return true;
        }
    }

    boolean phoneNumeric(){
        String phone = etPhone.getText().toString();
        if(phone.matches("[0-9]+")){
            return true;
        }else{
            return false;
        }
    }

    boolean passwordContainAlphaNumeric(){
        String pass = etPassword.getText().toString();
        if(pass.matches("([A-Za-z]+[0-9]|[0-9]+[A-Za-z])[A-Za-z0-9]*")){
            return true;
        }else{
            return false;
        }
    }

    boolean fullnameAlphabet(){
        String name = etFullname.getText().toString();
        if(name.matches("[A-Za-z]+")){
            return true;
        }else{
            return false;
        }
    }

    void sms(){

            generateCode  = generateCode();
            smsManager.sendTextMessage(etPhone.getText().toString(), null, generateCode, null, null);
            /*smsManager.sendTextMessage("5554", null, generateCode, null, null);*/

    }

    public String generateCode(){
        String angka2 = "";
        Random random = new Random();
        for(int i=0; i<6; i++ ){
            int randomInt = random.nextInt(9-0+1)+0;
            String angka = Integer.toString(randomInt);
            angka2 = angka2+angka;
        }
        return angka2;
    }


}


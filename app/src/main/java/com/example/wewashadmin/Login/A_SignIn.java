package com.example.wewashadmin.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.wewashadmin.MainActivity;
import com.example.wewashadmin.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class A_SignIn extends AppCompatActivity {
    TextInputEditText Email,Password;
    TextInputLayout Email1,Password1;
    String email = "wewash@gmail.com";
    String pass = "Admin@1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Email=findViewById(R.id.sign_in_txt_edit_txt_email);
        Password=findViewById(R.id.sign_in_txt_edit_txt_password);
        Email1=findViewById(R.id.sign_in_txt_layout_1);
        Password1=findViewById(R.id.sign_in_txt_layout_2);

    }

    public void LogAdmin(View view) {
        String EmailData = Email.getEditableText().toString();
        String PassData = Password.getEditableText().toString();

        if (checkEmail(EmailData) && checkPass(PassData)){
            if (email.equals(EmailData) && pass.equals(PassData))
            {
                Toast.makeText(this, "Welcome Admin", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(A_SignIn.this, MainActivity.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean checkPass(String passData) {

        if (passData.isEmpty()){
            Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            return true;
        }
    }

    private boolean checkEmail(String emailData) {

        if (emailData.isEmpty()){
            Toast.makeText(this, "Email Cannot be Empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            return true;
        }
    }
}

package com.example.tylerperdue.concertapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class LoginView extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText emailTxt = (EditText) findViewById(R.id.emailTxt);
        final EditText passwordTxt = (EditText) findViewById(R.id.passwordTxt);
        final TextView result = (TextView) findViewById(R.id.resultTextView);

        Button signin = (Button) findViewById(R.id.signinBtn);
        signin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String email = emailTxt.getText().toString();
                System.out.println(email);
                String password = passwordTxt.getText().toString();
                System.out.println(password);
                LoginController login =  new LoginController(email, password);
                boolean authenticated = login.authenticate();
                if(authenticated){
                    Intent myIntent = new Intent(LoginView.this, NavigationView.class);
//                  myIntent.putExtra("key", value); //Optional parameters
                    LoginView.this.startActivity(myIntent);
                    System.out.println(authenticated);
                }else{
                    System.out.println(authenticated);
                    result.setText("Incorrect parameters. Please try again.");
                }


            }
        });

        Button createAccount = (Button) findViewById(R.id.createAccountBtn);
        createAccount.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent myIntent = new Intent(LoginView.this, RegisterView.class);
                LoginView.this.startActivity(myIntent);
            }
        });
    }
}

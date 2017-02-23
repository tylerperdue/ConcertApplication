package com.example.tylerperdue.concertapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.List;


public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final EditText useridTxt = (EditText) findViewById(R.id.useridTxt);
        final EditText passwordTxt = (EditText) findViewById(R.id.passwordTxt);
        final TextView result = (TextView) findViewById(R.id.resultTextView);

        Button signin = (Button) findViewById(R.id.signinBtn);
        signin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String username = useridTxt.getText().toString();
                String password = passwordTxt.getText().toString();
                boolean authenticated = authenticate(username, password);
                if(authenticated){
                    Intent myIntent = new Intent(Login.this, Navigation.class);
//                    myIntent.putExtra("key", value); //Optional parameters
                    Login.this.startActivity(myIntent);
                }else{
                    result.setText("Incorrect parameters. Please try again.");
                }
            }
        });

        Button createAccount = (Button) findViewById(R.id.createAccountBtn);
        createAccount.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent myIntent = new Intent(Login.this, Registration.class);
                Login.this.startActivity(myIntent);
            }
        });
    }

    public boolean authenticate(String username, String password){
        boolean authenticated = false;
        DBHandler db = new DBHandler(getBaseContext());
        List<User> userList = db.getAllUsers();
        for(int i=0; i<userList.size(); i++){
            if(username.equals(userList.get(i).getUsername())){
                if(password.equals(userList.get(i).getPassword())){
                    authenticated = true;
                }
            }
        }
        return authenticated;
    }
}

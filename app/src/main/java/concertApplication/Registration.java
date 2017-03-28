package concertApplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        final EditText nameTxt = (EditText) findViewById(R.id.nameRegisterTxt);
        final EditText usernameTxt = (EditText) findViewById(R.id.usernameRegisterTxt);
        final EditText passwordTxt = (EditText) findViewById(R.id.passwordRegisterTxt);
        final EditText verifyPasswordTxt = (EditText) findViewById(R.id.verifyPasswordRegisterTxt);
        final TextView resultTextView = (TextView) findViewById(R.id.resultRegisterTextView);


        Button createAccount = (Button) findViewById(R.id.createRegisterBtn);
        createAccount.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String name = nameTxt.getText().toString();
                String username = usernameTxt.getText().toString();
                String password = passwordTxt.getText().toString();
                String verifyPassword = verifyPasswordTxt.getText().toString();
                String result;
                if(nameTxt.getText().toString().matches("")){
                    resultTextView.setText("Please enter a name.");
                }else{
                    result = register(name, username, password, verifyPassword);
                    if(result.equals("User created.")){
                        Intent myIntent = new Intent(Registration.this, Login.class);
                        Registration.this.startActivity(myIntent);
                    }else if(result.equals("Username already exists.")) {
                        resultTextView.setText("Username already exists. Please try again.");
                    }else {
                        resultTextView.setText("Passwords do not match. Please try again.");
                    }
                }
            }
        });
    }

    public String register(String name, String username, String password, String verifyPassword){
        String result;
        DBHandler db = new DBHandler(getBaseContext());
        List<User> userList = db.getAllUsers();
        for(int i=0; i<userList.size(); i++){
            if(username.equals(userList.get(i).getUsername())){
                result = "Username already exists.";
                return result;
            }
        }
        if(!password.equals(verifyPassword)){
            result = "Passwords do not match";
        } else{
            User user = new User();
            user.setName(name);
            user.setUsername(username);
            user.setPassword(password);
            db.insertUser(user);
            result = "User created.";
        }
        db.close();
        return result;
    }
}

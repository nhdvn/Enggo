package Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import Object.User;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.enggo.R;

import java.sql.SQLException;

import SQLServerConnection.UserModel;

public class Login extends AppCompatActivity {

    private Context mContext;
    private EditText mEmail, mPassword;
    Button bLogin, bSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mContext = Login.this;
        mEmail = (EditText) findViewById(R.id.input_email);
        mPassword = (EditText) findViewById(R.id.input_password);
        bLogin = (Button) findViewById(R.id.btn_login);
        bSignUp = (Button) findViewById(R.id.btn_signup);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                queryUser(); // comment out for testing without database
            }
        });

        bSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSignUp();
            }
        });
    }

    private void queryUser()
    {
        UserModel userModel = new UserModel();
        String email = mEmail.getText().toString();
        String pass = mPassword.getText().toString();
        User user = new User(email, pass);

        try {
            if(email.equals("") || pass.equals("")){
                Toast.makeText(getBaseContext(), "Your username or password wrong !", Toast.LENGTH_LONG).show();
            }
            else if (!userModel.checkUser(user))
            {
               Toast.makeText(getBaseContext(), "Your username or password wrong !", Toast.LENGTH_LONG).show();
            }
            else goToChooseTopic();
        }
        catch (SQLException error)
        {
            error.printStackTrace();
        }
    }

    private void goToSignUp(){
        Intent intent = new Intent(mContext, Signup.class);
        startActivity(intent);
    }

    private void goToChooseTopic(){
        Intent intent = new Intent(mContext, Home.class);
        startActivity(intent);
        this.finish();  // can't come back when clicked backPress button
    }
}


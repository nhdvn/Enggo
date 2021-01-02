package Login;

import Quiz.QuizList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import Object.User;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.enggo.MainActivity;
import com.example.enggo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.sql.SQLException;
import java.util.List;

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
                UserModel x = new UserModel();
                User user = new User(mEmail.getText().toString(), mPassword.getText().toString());
                try {
                    if (!x.checkUser(user)) {
                        Toast.makeText(getBaseContext(), "Your username or password wrong !", Toast.LENGTH_LONG).show();
                    }
                    else {
                        goToMainActivity();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        bSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSignUp();
            }
        });
    }

    private void goToSignUp(){
        Intent intent = new Intent(mContext, Signup.class);
        startActivity(intent);
    }

    private void goToMainActivity(){
        Intent intent = new Intent(mContext, QuizList.class);
        startActivity(intent);
        this.finish();  // can't come back when clicked backPress button
    }
}


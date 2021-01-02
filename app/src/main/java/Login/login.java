package Login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.enggo.MainActivity;
import com.example.enggo.R;

public class login extends AppCompatActivity {

    private Context mContext;
    private EditText mEmail, mPassword;
    Button bLogin, bSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mContext = login.this;
        mEmail = (EditText) findViewById(R.id.input_email);
        mPassword = (EditText) findViewById(R.id.input_password);
        bLogin = (Button) findViewById(R.id.btn_login);
        bSignUp = (Button) findViewById(R.id.btn_signup);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMainActivity();
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
        Intent intent = new Intent(mContext, signup.class);
        startActivity(intent);
    }

    private void goToMainActivity(){
        Intent intent = new Intent(mContext, MainActivity.class);
        startActivity(intent);
        this.finish();  // can't come back when clicked backPress button
    }
}


package Login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.enggo.MainActivity;
import com.example.enggo.R;

public class signup extends AppCompatActivity {
    private Context mContext;
    private EditText mPassword, mPassword2, mName, mEmail;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        mContext = signup.this;
        mPassword = (EditText) findViewById(R.id.signup_password);
        mPassword2 = (EditText) findViewById(R.id.signup_password2);
        mName = (EditText) findViewById(R.id.signup_name);
        mEmail = (EditText) findViewById(R.id.signup_email);
        btnRegister = (Button) findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMainActivity();
            }
        });
    }

    private void goToMainActivity(){
        Intent intent = new Intent(mContext, MainActivity.class);
        startActivity(intent);
        this.finish();// can't come back when clicked backPress button
    }

}
package Login;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.enggo.R;

public class login extends AppCompatActivity {
    TextView bytes;
    TextView lin, sup;
    EditText usr,pswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usr = findViewById(R.id.usrusr);
        pswd = findViewById(R.id.passwrd);
        lin = findViewById(R.id.logiin);
        sup = findViewById(R.id.sup);
        bytes = findViewById(R.id.bytes);
//
//        Typeface custom_font = Typeface.createFromAsset(getAssets(),"fonts/Lato-Light.ttf");
//        bytes.setTypeface(custom_font);
//        pswd.setTypeface(custom_font);
//        sup.setTypeface(custom_font);
//        lin.setTypeface(custom_font);
//        usr.setTypeface(custom_font);
        sup.setOnClickListener(v -> {
            Intent it = new Intent(Login.login.this, Login.signup.class);
            startActivity(it);
        });
    }
}


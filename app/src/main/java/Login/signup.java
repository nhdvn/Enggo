package Login;

import android.content.Intent;
import android.graphics.Typeface;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.enggo.R;

public class signup extends AppCompatActivity {
    EditText passwordd,mobphone,mail,usrusr;
    TextView login,signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        usrusr = (EditText) findViewById(R.id.usrusr);
        passwordd = (EditText)findViewById(R.id.passwrd);
        mail = (EditText) findViewById(R.id.mail);
        mobphone = (EditText) findViewById(R.id.mobphone);
        login = findViewById(R.id.logiin);
        signup = findViewById(R.id.sup);

//        Typeface custom_font = Typeface.createFromAsset(getAssets(),"fonts/Lato-Light.ttf");
//        signup.setTypeface(custom_font);
//        mail.setTypeface(custom_font);
//        mobphone.setTypeface(custom_font);
//        passwordd.setTypeface(custom_font);
//        usrusr.setTypeface(custom_font);
//        login.setTypeface(custom_font);
        login.setOnClickListener(v -> {
            Intent it = new Intent(signup.this, login.class);
            startActivity(it);
        });
    }
}
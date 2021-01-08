package Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.enggo.R;


public class Profile extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setActionForButton();
    }

    private void setActionForButton()
    {
        Button btnHome = (Button) findViewById(R.id.btn_home);
        Button btnInsert = (Button) findViewById(R.id.btn_insert);
        Button btnLogout = (Button) findViewById(R.id.btn_logout);

        btnLogout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                goToLogin();
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToInsert();
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToHome();
            }
        });
    }

    private void goToLogin()
    {
        Intent intent = new Intent(Profile.this, Login.class);
        startActivity(intent);
        this.finish();
    }

    private void goToHome()
    {
        Intent intent = new Intent(Profile.this, Home.class);
        startActivity(intent);
        this.finish();
    }

    private void goToInsert()
    {
        Intent intent = new Intent(Profile.this, Insert.class);
        startActivity(intent);
        this.finish();
    }
}

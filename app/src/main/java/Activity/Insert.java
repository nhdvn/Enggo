package Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.enggo.R;

public class Insert extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        setActionForButton();
        setDropDown();
    }

    private void setDropDown()
    {
        Spinner dropdown = findViewById(R.id.dropdown_topic);

        String[] items = new String[]{"None First", "No Second", "Not Third"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);

        dropdown.setAdapter(adapter);
    }

    private void setActionForButton()
    {
        Button btnHome = (Button) findViewById(R.id.btn_home);
        Button btnProfile = (Button) findViewById(R.id.btn_profile);
        Button btnInsert = (Button) findViewById(R.id.btn_insert_word);

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToProfile();
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToHome();
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // insert to database
            }
        });
    }

    private void goToProfile()
    {
        Intent intent = new Intent(Insert.this, Profile.class);
        startActivity(intent);
        this.finish();
    }

    private void goToHome()
    {
        Intent intent = new Intent(Insert.this, Home.class);
        startActivity(intent);
        this.finish();
    }
}

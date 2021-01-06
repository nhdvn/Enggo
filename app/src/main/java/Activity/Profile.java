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
        Button btnTopic = (Button) findViewById(R.id.btn_home);
        Button btnProfile = (Button) findViewById(R.id.btn_profile);

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // do nothing
            }
        });

        btnTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToTopicList();
            }
        });
    }

    private void goToTopicList()
    {
        Intent intent = new Intent(Profile.this, Home.class);
        startActivity(intent);
        this.finish();
    }
}

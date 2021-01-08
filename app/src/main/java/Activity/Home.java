package Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.enggo.R;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Object.Vocab;
import Object.Topic;
import SQLServerConnection.TopicModel;


public class Home extends AppCompatActivity
{
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mContext = Home.this;
        setActionForButton();
        realCode();
    }



    private void realCode() // PLEASE NOTICE ME SENPAI
    {
        TopicModel model = new TopicModel();

        try {
            List<String> topicList = model.GetTopicList();

            for (String newTopic : topicList) {
                insertTopic(newTopic);
            }
        }
        catch (SQLException error)
        {
            error.printStackTrace();
        }
    }

    private void setActionForButton()
    {
        Button btnTopic = (Button) findViewById(R.id.btn_home);
        Button btnProfile = (Button) findViewById(R.id.btn_profile);

        btnTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // do nothing
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToProfile();
            }
        });
    }

    private void goToProfile()
    {
        Intent intent = new Intent(mContext, Profile.class);
        startActivity(intent);
        this.finish();
    }

    private void insertTopic(String newTopic)
    {
        GridLayout gridLayout = (GridLayout) findViewById(R.id.topic_grid);

        TextView textView = new TextView(this);

        textView.setText(newTopic.trim());

        GridLayout.LayoutParams param = new GridLayout.LayoutParams(
                GridLayout.spec(GridLayout.UNDEFINED, GridLayout.FILL,1f),
                GridLayout.spec(GridLayout.UNDEFINED, GridLayout.FILL,1f)
        );

        param.width = 100;

        float scale = getResources().getDisplayMetrics().density;

        int dp30 = (int) (20 * scale + 0.5f);

        textView.setPadding(0, dp30, 0, dp30);

        textView.setTextSize(20);

        textView.setTextColor(Color.BLACK);

        textView.setGravity(Gravity.CENTER);

        textView.setBackgroundResource(R.drawable.quiz_border);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLesson(newTopic);
            }
        });

        gridLayout.addView(textView, param);
    }

    private void goToLesson(String topic)
    {
        Intent intent = new Intent(mContext, Lesson.class);
        intent.putExtra("Topic", topic);
        startActivity(intent);
    }
}


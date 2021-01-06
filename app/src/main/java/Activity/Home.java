package Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
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
        testCode();
    }

    private void testCode()
    {
        List<Topic> tempList = new ArrayList<Topic>();
        List<Vocab> vocab = new ArrayList<Vocab>();

        tempList.add(new Topic("hello1", vocab));
        tempList.add(new Topic("hello2", vocab));
        tempList.add(new Topic("hello3", vocab));

        for (Topic newTopic : tempList) {
            insertTopic(newTopic);
        }
    }

    private void realCode() // PLEASE NOTICE ME SENPAI
    {
        TopicModel model = new TopicModel();

        try {
            List<Topic> topicList = model.getTopiclist();

            for (Topic newTopic : topicList) {
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

    private void insertTopic(Topic newTopic)
    {
        GridLayout gridLayout = (GridLayout) findViewById(R.id.topic_grid);

        TextView textView = new TextView(this);

        textView.setText(newTopic.get_name());

        GridLayout.LayoutParams param= new GridLayout.LayoutParams(GridLayout.spec(
                GridLayout.UNDEFINED,GridLayout.FILL,1f),
                GridLayout.spec(GridLayout.UNDEFINED,GridLayout.FILL,1f));

        float scale = getResources().getDisplayMetrics().density;

        int dp30 = (int) (30 * scale + 0.5f);

        textView.setPadding(dp30, dp30, dp30, dp30);

        textView.setGravity(Gravity.CENTER);

        textView.setBackgroundResource(R.drawable.quiz_border);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLesson();
            }
        });

        gridLayout.addView(textView, param);
    }

    private void goToLesson()
    {
        Intent intent = new Intent(mContext, Lesson.class);
        startActivity(intent);
    }
}


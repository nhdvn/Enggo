package Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.enggo.R;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Object.Topic;
import Object.Vocab;
import SQLServerConnection.TopicModel;


public class ChooseTopic extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_topic);

        /*
        List<Topic> temp = new ArrayList<Topic>();
        List<Vocab> vocab = new ArrayList<Vocab>();

        temp.add(new Topic("hello1", vocab));
        temp.add(new Topic("hello2", vocab));
        temp.add(new Topic("hello3", vocab));

        List<Topic> topicList = temp;

        for (Topic newTopic : temp) {
            insertTopic(newTopic);
        }*/

        TopicModel x = new TopicModel();
        try {
            List<Topic> topicList = x.getTopiclist();

            for (Topic newTopic : topicList) {
            insertTopic(newTopic);
        }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void insertTopic(Topic newTopic) {
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

        gridLayout.addView(textView, param);
    }
}


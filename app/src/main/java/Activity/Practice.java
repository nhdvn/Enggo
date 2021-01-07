package Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.enggo.R;

import java.util.ArrayList;
import java.util.List;


public class Practice extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        setActionForButton();

        testCode();
    }

    private void realCode()
    {

    }

    private void testCode()
    {
        List<String> tokenList = new ArrayList<>();
        tokenList.add("bạn");
        tokenList.add("là");
        tokenList.add("ai");
        tokenList.add("vậy");
        tokenList.add("ta");
        tokenList.add("ta");
        tokenList.add("ta");
        tokenList.add("ta");
        tokenList.add("ta");
        tokenList.add("ta");
        tokenList.add("ta");

        for (String option : tokenList) {
            insertToken(option);
        }
    }

    private void insertToken(String option)
    {
        GridLayout gridLayout = (GridLayout) findViewById(R.id.token_board);

        TextView textView = new TextView(this);

        textView.setText(option);

        GridLayout.LayoutParams param = new GridLayout.LayoutParams(
                GridLayout.spec(GridLayout.UNDEFINED, GridLayout.FILL,1f),
                GridLayout.spec(GridLayout.UNDEFINED, GridLayout.FILL,1f)
        );

        float scale = getResources().getDisplayMetrics().density;

        int dp = (int) (10 * scale + 0.5f);

        textView.setPadding(dp, dp, dp, dp);

        textView.setTextColor(Color.BLACK);

        textView.setGravity(Gravity.CENTER);

        textView.setBackgroundResource(R.drawable.quiz_border);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // add this option to user answer
            }
        });

        gridLayout.addView(textView, param);
    }

    private void setActionForButton()
    {
        Button btnLesson = (Button) findViewById(R.id.btn_lesson);
        Button btnQuiz = (Button) findViewById(R.id.btn_quiz);

        btnLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLesson();
            }
        });

        btnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // do nothing
            }
        });
    }

    private void goToLesson()
    {
        Intent intent = new Intent(Practice.this, Lesson.class);
        startActivity(intent);
        this.finish();
    }
}


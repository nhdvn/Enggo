package Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.enggo.R;

import java.util.ArrayList;
import java.util.List;


public class Practice extends AppCompatActivity
{
    int start;
    int index;
    int finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);


        start = 0;
        index = 0;
        finish = 0;

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
                TextView input = (TextView) findViewById(R.id.quiz_answer);

                input.setText(input.getText().toString() + ' ' + textView.getText());
            }
        });

        gridLayout.addView(textView, param);
    }

    private void setActionForButtonOnStart()
    {
        Button btnLeft = (Button) findViewById(R.id.left_btn);
        Button btnRight = (Button) findViewById(R.id.right_btn);

        btnLeft.setText("LESSON");
        btnRight.setText("NEXT");

        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLesson();
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToQuiz(index + 1);
            }
        });
    }

    private void setActionForButtonOnMiddle()
    {
        Button btnLeft = (Button) findViewById(R.id.left_btn);
        Button btnRight = (Button) findViewById(R.id.right_btn);

        btnLeft.setText("PREV");
        btnRight.setText("NEXT");

        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToQuiz(index - 1);
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToQuiz(index + 1);
            }
        });
    }

    private void setActionForButtonOnEnd()
    {
        Button btnLeft = (Button) findViewById(R.id.left_btn);
        Button btnRight = (Button) findViewById(R.id.right_btn);

        btnLeft.setText("PREV");
        btnRight.setText("HOME");

        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToQuiz(index - 1);
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToHome();
            }
        });
    }

    private void goToQuiz(int newIndex)
    {
        GridLayout board = (GridLayout) findViewById(R.id.token_board);

        board.removeAllViews();

        index = newIndex;

        if (index == start)
        {
            setActionForButtonOnStart();
        }
        else if (index == finish)
        {
            setActionForButtonOnEnd();
        }
        else setActionForButtonOnMiddle();

        // get quiz answer

        // split into token

        // insert all token randomly
    }


    private void goToLesson()
    {
        Intent intent = new Intent(Practice.this, Lesson.class);
        startActivity(intent);
        this.finish();
    }

    private void goToHome()
    {
        Intent intent = new Intent(Practice.this, Home.class);
        startActivity(intent);
        this.finish();
    }
}


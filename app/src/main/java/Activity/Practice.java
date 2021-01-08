package Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.enggo.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Practice extends AppCompatActivity
{
    int start, index, finish;

    List<String> quest = null;
    List<String> trans = null;
    List<String> token = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        start = 0;
        index = 0;
        finish = 2;

        quest = new ArrayList<>();
        trans = new ArrayList<>();
        token = new ArrayList<>();

        mockData();

        goToQuiz(0);

        TextView submit = (TextView)findViewById(R.id.quiz_submit);

        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TextView answer = (TextView)findViewById(R.id.quiz_answer);

                String temp = answer.getText().toString().trim();

                if (temp.length() > 0 && temp.equals(quest.get(index)))
                {
                    Toast.makeText(Practice.this, "You got it right !!!", Toast.LENGTH_LONG).show();
                }
                else Toast.makeText(Practice.this, "Maybe try again", Toast.LENGTH_LONG).show();
            }
        });

        TextView clear = (TextView)findViewById(R.id.quiz_clear);

        clear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TextView answer = (TextView)findViewById(R.id.quiz_answer);
                answer.setText("");
            }
        });
    }

    private void mockData()
    {
        quest.add("math is a core subject at school");
        quest.add("we love our parents and siblings");
        quest.add("please take the medicine after meals");

        trans.add("Toán là môn học quan trọng ở trường");
        trans.add("Chúng tôi yêu quý bố mẹ và anh em");
        trans.add("Hãy nhớ uống thuốc sau mỗi bữa ăn");

        String temp = "study the and your did mother father person was his to science street young between";

        token = Arrays.asList(temp);
    }

    private void insertToken(String option)
    {
        GridLayout gridLayout = (GridLayout) findViewById(R.id.token_board);

        TextView textView = new TextView(this);

        textView.setText(option);

        GridLayout.LayoutParams param = new GridLayout.LayoutParams(
                GridLayout.spec(GridLayout.UNDEFINED, GridLayout.FILL, 1.0f),
                GridLayout.spec(GridLayout.UNDEFINED, GridLayout.FILL, 1.0f)
        );

        float scale = getResources().getDisplayMetrics().density;

        int dp = (int) (5 * scale + 0.5f);

        textView.setPadding(dp, dp, dp, dp);

        textView.setTextColor(Color.BLACK);

        textView.setGravity(Gravity.CENTER);

        textView.setBackgroundResource(R.drawable.quiz_border);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView input = (TextView) findViewById(R.id.quiz_answer);

                input.setText(input.getText().toString() + textView.getText() + ' ');
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
                goToHome();
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

        TextView quiz = (TextView)findViewById(R.id.practice_question);

        quiz.setText(trans.get(index));

        List<String> askList = Arrays.asList(quest.get(index).split(" "));

        Collections.shuffle(askList);


        for (int i = 0; i < askList.size(); i++)
        {
            insertToken(askList.get(i));
        }
    }

    private void goToHome()
    {
        this.finish();
    }
}


package Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.enggo.R;


public class Quiz extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        setActionForButton();
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
        Intent intent = new Intent(Quiz.this, Lesson.class);
        startActivity(intent);
        this.finish();
    }
}


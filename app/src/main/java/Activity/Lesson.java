package Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.enggo.R;


public class Lesson extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        setActionForButton();
    }

    private void setActionForButton()
    {
        Button btnLesson = (Button) findViewById(R.id.btn_lesson);
        Button btnQuiz = (Button) findViewById(R.id.btn_quiz);

        btnLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // do nothing
            }
        });

        btnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToQuizList();
            }
        });
    }

    private void goToQuizList()
    {
        Intent intent = new Intent(Lesson.this, Quiz.class);
        startActivity(intent);
        this.finish();
    }
}


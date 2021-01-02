package Quiz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.enggo.R;

import java.util.ArrayList;

public class QuizList extends AppCompatActivity
{
    private class Quiz
    {
        String Question;
        String Answer;
        ArrayList<String> Tokens = new ArrayList<>();
    }

    ArrayList<Quiz> Content = new ArrayList<>();

    int index = 0;

    Quiz thisQuiz;

    Button Submit;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Submit = (Button)findViewById(R.id.quiz_submit);

        thisQuiz = Content.get(index);

        Submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                thisQuiz = Content.get(index + 1);
            }
        });
    }
}

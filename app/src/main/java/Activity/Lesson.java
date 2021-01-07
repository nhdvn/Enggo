package Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.enggo.R;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import Object.Vocab;
import SQLServerConnection.VocabModel;

public class Lesson extends AppCompatActivity
{
    int start;
    int index;
    int finish;

    List<Vocab> listVocab = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        Intent i = getIntent();
        String topic = i.getStringExtra("Topic");
        try {
            listVocab = new VocabModel().GetVocabList(topic);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        start = 0;
        index = 0;
        finish = listVocab.size() - 1;

        goToVocab(0);
    }


    private void insertVocabToLesson(String word)
    {
        LinearLayout parent = new LinearLayout(this);

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        param.setMargins(0, 0, 0, 30);

        parent.setLayoutParams(param);

        parent.setOrientation(LinearLayout.HORIZONTAL);

        ImageView image = createHeadphone(word);

        ImageView micro = createMicro();

        TextView text = createSentence(word);

        parent.addView(micro);

        parent.addView(image);

        parent.addView(text);

        LinearLayout board = (LinearLayout)findViewById(R.id.lesson_board);

        board.addView(parent);
    }

    private ImageView createMicro()
    {
        ImageView image = new ImageView(this);

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                3.0f
        );

        float scale = getResources().getDisplayMetrics().density;

        int dp = (int) (5 * scale + 0.5f);

        image.setPadding(dp, dp, dp, dp);

        image.setLayoutParams(param);

        image.setImageResource(R.drawable.edit);

        recordWord(image);

        return image;
    }

    private void recordWord(ImageView micro)
    {
        micro.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
    }

    private ImageView createHeadphone(String text)
    {
        ImageView image = new ImageView(this);

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                3.0f
        );

        float scale = getResources().getDisplayMetrics().density;

        int dp = (int) (5 * scale + 0.5f);

        image.setPadding(dp, dp, dp, dp);

        image.setLayoutParams(param);

        image.setImageResource(R.drawable.headphone);

        speakOut(image, text);

        return image;
    }

    private void speakOut(ImageView headPhone, String text)
    {
        headPhone.setOnClickListener(new View.OnClickListener() {
            TextToSpeech textToSpeech;
            @Override
            public void onClick(View view) {
                textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status != TextToSpeech.ERROR) {
                            textToSpeech.setLanguage(Locale.ENGLISH);
                            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                        }
                    }
                });

            }
        });

    }
    private TextView createSentence(String example)
    {
        TextView text = new TextView(this);

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1.0f
        );

        float scale = getResources().getDisplayMetrics().density;

        int dp = (int) (10 * scale + 0.5f);

        text.setPadding(dp, dp, dp, dp);

        text.setText(example);

        text.setTextColor(Color.BLACK);

        text.setLayoutParams(param);

        text.setBackgroundResource(R.drawable.quiz_border);

        return text;
    }

    private void setActionForButtonOnStart()
    {
        Button btnLeft = (Button) findViewById(R.id.lesson_left_btn);
        Button btnRight = (Button) findViewById(R.id.lesson_right_btn);

        btnLeft.setText("HOME");
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
                goToVocab(index + 1);
            }
        });
    }

    private void setActionForButtonOnMiddle()
    {
        Button btnLeft = (Button) findViewById(R.id.lesson_left_btn);
        Button btnRight = (Button) findViewById(R.id.lesson_right_btn);

        btnLeft.setText("PREV");
        btnRight.setText("NEXT");

        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToVocab(index - 1);
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToVocab(index + 1);
            }
        });
    }

    private void setActionForButtonOnEnd()
    {
        Button btnLeft = (Button) findViewById(R.id.lesson_left_btn);
        Button btnRight = (Button) findViewById(R.id.lesson_right_btn);

        btnLeft.setText("PREV");
        btnRight.setText("QUIZ");

        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToVocab(index - 1);
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToPractice();
            }
        });
    }

    private void goToVocab(int newIndex)
    {
        LinearLayout board = (LinearLayout)findViewById(R.id.lesson_board);

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

        Vocab word = listVocab.get(index);

        insertVocabToLesson(word.get_name());

        insertVocabToLesson(word.get_sentence());
    }


    private void goToHome()
    {
        Intent intent = new Intent(Lesson.this, Home.class);
        startActivity(intent);
        this.finish();
    }

    private void goToPractice()
    {
        Intent intent = new Intent(Lesson.this, Practice.class);
        startActivity(intent);
        this.finish();
    }
}


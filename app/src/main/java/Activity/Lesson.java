package Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.enggo.R;
import com.example.enggo.STT;

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
    STT stt;
    List<Vocab> listVocab = null;
    TextView result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        result = findViewById(R.id.status);
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


    private void insertVocabToLesson(String word, String meaning)
    {
        LinearLayout parent = new LinearLayout(this);

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        parent.setLayoutParams(param);

        parent.setOrientation(LinearLayout.VERTICAL);

        param.setMargins(0, 50, 0, 0);

        LinearLayout button = createUtilsButton(word);

        parent.addView(button);

        TextView text = createSentence(word + meaning);

        parent.addView(text);

        LinearLayout board = (LinearLayout)findViewById(R.id.lesson_board);

        board.addView(parent);
    }

    private LinearLayout createUtilsButton(String word)
    {
        LinearLayout parent = new LinearLayout(this);

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        parent.setPadding(20, 0, 0, 0);

        parent.setLayoutParams(param);

        parent.setOrientation(LinearLayout.HORIZONTAL);

        ImageView speak = createHeadphone(word);

        ImageView micro = createMicro(word);

        parent.addView(speak);

        parent.addView(micro);

        return parent;
    }


    private ImageView createMicro(String word)
    {
        ImageView image = new ImageView(this);

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(75, 75, 1.0f
        );

        param.setMargins(0, 10, 0, 0);

        image.setLayoutParams(param);

        image.setImageResource(R.drawable.micro);
        stt = new STT(Lesson.this, word);
        recordWord(image, word);

        return image;
    }

    private void recordWord(ImageView micro, String word)
    {
        micro.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                stt.set_compare(word);
                stt.Listening();
            }
        });
    }

    private ImageView createHeadphone(String text)
    {
        ImageView image = new ImageView(this);

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(70, 70, 1.0f);

        param.setMargins(0, 10, 0, 0);

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
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        float scale = getResources().getDisplayMetrics().density;

        int dp = (int) (10 * scale + 0.5f);

        text.setPadding(dp, dp, dp, dp);

        text.setText(example);

        text.setTextSize(20);

        text.setTextColor(Color.BLACK);

        text.setLayoutParams(param);

        text.setBackgroundResource(R.drawable.quiz_border);

        return text;
    }

    private void setActionForButtonOnStart()
    {
        Button btnLeft = (Button) findViewById(R.id.left_btn);
        Button btnRight = (Button) findViewById(R.id.right_btn);

        btnLeft.setText("HOME");
        btnRight.setText("NEXT");
        ImageView btnNote = (ImageView) findViewById(R.id.btn_note);

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

        btnNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToTakeNote();
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
        Button btnLeft = (Button) findViewById(R.id.left_btn);
        Button btnRight = (Button) findViewById(R.id.right_btn);

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

        insertVocabToLesson(word.get_name().trim(), ": " + word.get_meaning());

        insertVocabToLesson(word.get_sentence(), "");
    }


    private void goToHome()
    {
        this.finish();
    }

    private void goToPractice()
    {
        Intent intent = new Intent(Lesson.this, Practice.class);
        startActivity(intent);
        this.finish();
    }

    private void goToTakeNote()
    {
        Intent intent = new Intent(Lesson.this, Takenote.class);
        startActivity(intent);
    }
}


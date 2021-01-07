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
import android.widget.Toast;

import com.example.enggo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import Object.Vocab;


public class Lesson extends AppCompatActivity
{
    MediaPlayer mPlayer = new MediaPlayer();
    String currentURL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        setActionForButton();

        fakeVocab();
    }

    private void fakeVocab()
    {
        List<Vocab> tempList = new ArrayList<Vocab>();

        String format = "https://docs.google.com/uc?export=download&id=";
        String fileID1 = "1Y_iOF08i9imJY3pFo-95ZN1_KqQh-7_c";
        String fileID2 = "1_T6COeyzkHMsjZMCvT2d3jUUzSCk2Y9j";

        tempList.add(new Vocab("Apple", "Poisonous Fruit", "Pen Pineapple Apple Pen", format + fileID1));
        tempList.add(new Vocab("Carrot", "Stupid Vegetable", "Long Stupid Temp Sentence", format + fileID2));

        for (Vocab word : tempList)
        {
            insertVocabToLesson(word);
        }
    }

    private void insertVocabToLesson(Vocab word)
    {
        LinearLayout parent = new LinearLayout(this);

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        param.setMargins(0, 0, 0, 30);

        parent.setLayoutParams(param);

        parent.setOrientation(LinearLayout.HORIZONTAL);

        ImageView image = createHeadphone(word.get_sentence());

        TextView text = createSentence(word.get_sentence());

        parent.addView(image);

        parent.addView(text);

        LinearLayout board = (LinearLayout)findViewById(R.id.lesson_board);

        board.addView(parent);
    }

    private ImageView createHeadphone(String text)
    {
        ImageView image = new ImageView(this);

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                5.0f
        );

        float scale = getResources().getDisplayMetrics().density;

        int dp = (int) (10 * scale + 0.5f);

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
        Intent intent = new Intent(Lesson.this, Practice.class);
        startActivity(intent);
        this.finish();
    }
}


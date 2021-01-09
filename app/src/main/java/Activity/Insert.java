package Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import Object.Vocab;
import androidx.appcompat.app.AppCompatActivity;

import com.example.enggo.R;

import java.sql.SQLException;

import SQLServerConnection.TopicModel;
import SQLServerConnection.VocabModel;
public class Insert extends AppCompatActivity
{
    Spinner dropdown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        setActionForButton();
        setDropDown();
    }

    private void setDropDown()
    {
        dropdown = findViewById(R.id.dropdown_topic);
        String[] items = null;
        TopicModel listTopic = new TopicModel();
        try {
            items = listTopic.GetTopicList().toArray(new String[0]);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

        dropdown.setAdapter(adapter);
    }

    private void setActionForButton()
    {
        Button btnHome = (Button) findViewById(R.id.btn_home);
        Button btnProfile = (Button) findViewById(R.id.btn_profile);
        Button btnInsert = (Button) findViewById(R.id.btn_insert_word);

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToProfile();
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToHome();
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EditText word = findViewById(R.id.word);
                EditText sentence = findViewById(R.id.example);
                EditText mean = findViewById(R.id.meaning);
                VocabModel insert = new VocabModel();

                Vocab newVocab = new Vocab(word.getText().toString(),mean.getText().toString(),
                        sentence.getText().toString(), "");
                if (word.getText().toString().equals("") || mean.getText().toString().equals("")
                        || sentence.getText().toString().equals("")){
                    Toast.makeText(Insert.this, "Insert failed !!!", Toast.LENGTH_LONG).show();
                }
                else {
                    try {
                        insert.InsertVocab(newVocab, dropdown.getSelectedItem().toString());
                        Toast.makeText(Insert.this, "Insert successful !!!", Toast.LENGTH_LONG).show();
                    } catch (SQLException throwables) {
                        Toast.makeText(Insert.this, "Insert failed !!!", Toast.LENGTH_LONG).show();
                        throwables.printStackTrace();
                    }
                }
            }
        });
    }

    private void goToProfile()
    {
        Intent intent = new Intent(Insert.this, Profile.class);
        startActivity(intent);
        this.finish();
    }

    private void goToHome()
    {
        Intent intent = new Intent(Insert.this, Home.class);
        startActivity(intent);
        this.finish();
    }
}

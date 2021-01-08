package Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.enggo.R;

import java.util.ArrayList;

import Fragments.TakeNoteEditFragment;

public class Takenote extends AppCompatActivity {
    private TextView textNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takenote);

        textNote = (TextView)findViewById(R.id.text_note);

        receiveData();
    }

    public void showEdit(View view){
        TakeNoteEditFragment takenoteEdit = new TakeNoteEditFragment();
        FragmentTransaction fragmentTransaction = Takenote.this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.layout_takenote, takenoteEdit);
        // fragment unlike activity, it don't keep track of their stack, need to add it manually
        fragmentTransaction.addToBackStack(getString(R.string.takenote_edit_fragment));
        fragmentTransaction.commit();
    }

    private void receiveData(){
        Intent intent = getIntent();
        String note = intent.getStringExtra("NOTE");

        textNote.setText(note);
    }

    private void goToLesson(){
        Intent i = new Intent(this,  Lesson.class);
        startActivity(i);
    }
}

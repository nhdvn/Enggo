package Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.preference.PreferenceManager;

import com.example.enggo.R;

import Activity.Takenote;

import static android.content.Context.MODE_PRIVATE;

public class TakeNoteEditFragment extends Fragment {
    private EditText editNote;
    private SharedPreferences prefs;
    private ImageView saveChange;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_takenote,container,false);
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String note = prefs.getString("MY_NOTE", "");
        saveChange = (ImageView)view.findViewById(R.id.saveChange);
        editNote = (EditText)view.findViewById(R.id.edit_note);

        editNote.setText(note);


        saveChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Takenote)getContext()).getSupportFragmentManager().popBackStack(R.string.takenote_edit_fragment, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                sendData();
            }
        });
        return view;
    }

    private void sendData(){
        Intent intent = new Intent(getActivity().getBaseContext(),Takenote.class);
        intent.putExtra("NOTE", editNote.getText().toString());
        getActivity().startActivity(intent);
        getActivity().finish();
    }

}

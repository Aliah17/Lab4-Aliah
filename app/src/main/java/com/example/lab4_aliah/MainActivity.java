package com.example.lab4_aliah;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    EditText editTextName;
    Button buttonAdd;
    DatabaseReference databaseArtists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);

        databaseArtists = FirebaseDatabase.getInstance().getReference("artists");

        editTextName = (EditText) findViewById(R.id.editTextName);
        buttonAdd = (Button) findViewById(R.id.buttonAddArtist);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                addArtist();
            }
            private void addArtist() {
                String name = editTextName.getText().toString().trim();

                //check if the name is not empty
                if (!TextUtils.isEmpty(name)) {
                    //if exist push data to firebase database
                    //store inside id in database
                    //every time data stored the id will be unique
                    String id = databaseArtists.push().getKey();
                    //store
                    Artist artist = new Artist(id, name);
                    //store artist inside unique id
                    assert id != null;
                    databaseArtists.child(id).setValue(artist);
                    Toast.makeText(getApplicationContext(), "Artist added", Toast.LENGTH_LONG).show();

                } else {
                    //if the name is empty;
                    //if the value is not given displaying a toast
                    Toast.makeText(getApplicationContext(), "Please enter a name", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
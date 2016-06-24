package com.example.tyler.firebasepocwriting;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView.OnEditorActionListener;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //set up a path to the url here
    private static final String FIREBASE_URL = "https://proof-of-concept-b3c8b.firebaseio.com/";
    //connect to the fireback
    private Firebase firebaseDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //First thing that you have to do is set the android context
        Firebase.setAndroidContext(this);//use this to make things easier
        //Connect set here
        firebaseDb = new Firebase(FIREBASE_URL);
        EditText inputText = (EditText) findViewById(R.id.databaseInput);
        inputText.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
                if(actionId == EditorInfo.IME_ACTION_SEND){
                    sendMessage();
                }
                return true;
            }

        });
        findViewById(R.id.buttonPush).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    public void sendMessage(){
        EditText textInput = (EditText) findViewById(R.id.databaseInput);
        String message = textInput.getText().toString();
        if (!message.equals("")){
            Random rand = new Random();
            String author = "testUser" + rand.nextInt(100);
            ChatMessage cMessage = new ChatMessage(author, message);
            firebaseDb.push().setValue(cMessage);

            //set the text back to null
            textInput.setText("");
        }
    }
}

package com.marscode.pwn.jokelibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShowJoke extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_joke);

        TextView joke_text = (TextView) findViewById(R.id.joke_txt);

        //Retrieve the joke from the Intent Extras
        String JokeResult = null;
        Intent intent = getIntent();
        JokeResult = intent.getStringExtra(getString(R.string.joke));

        if (JokeResult != null) {
            joke_text.setText(JokeResult);
        } else {
            joke_text.setText("SomeThing Went Wrong!");
        }
    }
}
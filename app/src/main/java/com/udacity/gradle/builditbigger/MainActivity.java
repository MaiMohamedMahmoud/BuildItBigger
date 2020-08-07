package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.marscode.pwn.javajoker.Joker;
import com.marscode.pwn.jokelibrary.ShowJoke;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {

        Joker joker = new Joker();

        /**
         * this Toast for the first step
         * Toast.makeText(this, joker.tellJoke(), Toast.LENGTH_SHORT).show();
         */


        //this is the second step
        // Create Intent to launch JokeFactory Activity
        Intent intent = new Intent(this, ShowJoke.class);
        // Save the string
        intent.putExtra(getString(R.string.joke), joker.tellJoke());
        startActivity(intent);

    }


}

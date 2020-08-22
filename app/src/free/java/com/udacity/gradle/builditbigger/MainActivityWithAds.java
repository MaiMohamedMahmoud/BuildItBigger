package com.udacity.gradle.builditbigger;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.marscode.pwn.javajoker.Joke;
import com.marscode.pwn.jokelibrary.DisplayJoke;

public class MainActivityWithAds extends AppCompatActivity implements JokesListener {
    Joke joke;
    private InterstitialAd mInterstitialAd;
    private ProgressBar spinner;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ad);
        spinner = (ProgressBar) findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);
        progressDialog = new ProgressDialog(this);
        initializeAd();
    }

    // Method to show Progress bar
    private void showProgressDialog(String substring) {
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //Without this user can hide loader by tapping outside screen
        progressDialog.setCancelable(false);
        progressDialog.setMessage(substring);
        progressDialog.show();

    }

    // Method to hide/ dismiss Progress bar
    private void hideProgressDialog() {
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.dismiss();
    }

    public void initializeAd() {
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        loadAd();

    }

    public void loadAd() {
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
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


        /**
         * this fun show Toast for the first step
         * showTextJoke();
         * */

        /**
         * this is the second step open the showJoke Intent
         *  joke = new Joke();
         *  String randomJoke = joke.getJoke();
         *  displayJokeIntent(randomJoke);
         */

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    //spinner.setVisibility(View.VISIBLE);
                    new JokesAsyncTask(MainActivityWithAds.this).execute();
                    loadAd();
                }
            });
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
            //spinner.setVisibility(View.VISIBLE);

            showProgressDialog(getString(R.string.loadingJoke));
            new JokesAsyncTask(MainActivityWithAds.this).execute();

        }

    }

    public void showTextJoke() {
        joke = new Joke();
        Toast.makeText(this, joke.getJoke(), Toast.LENGTH_SHORT).show();
    }

    public void displayJokeIntent(String joke) {

        hideProgressDialog();
        // Create Intent to launch DisplayJoke Activity
        Intent intent = new Intent(this, DisplayJoke.class);
        // Save the string
        intent.putExtra(getString(R.string.joke), joke);
        startActivity(intent);


    }


    @Override
    public void onLoad(String joke) {
        //spinner.setVisibility(View.GONE);
        showProgressDialog(getString(R.string.loadingJoke));
        displayJokeIntent(joke);
    }
}

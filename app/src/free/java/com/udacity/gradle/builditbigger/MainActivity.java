package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.cl.joke.JokeActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity {
    private InterstitialAd mInterstitialAd;
    private  boolean canLaunch=false;
    private String joke;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
                if (canLaunch) {
                    lauchJokeActivity();
                } else {
                    canLaunch=true;
                }

            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    mInterstitialAd.loadAd(new AdRequest.Builder().build());
                }
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
                fetchJokes();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
            }
        });

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
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            canLaunch=true;
            fetchJokes();
        }
    }

    private void fetchJokes() {
        new FetchJokeAsynctask(this,new FetchJokeAsynctask.onSuccessListener() {
            @Override
            public void onSuccess(final String result) {
                if (!TextUtils.isEmpty(result)) {
                    joke=result;
                    if (canLaunch) {
                        lauchJokeActivity();
                    } else {
                        canLaunch=true;
                    }
                } else {
                    Toast.makeText(getBaseContext(), "There is no joke today.", Toast.LENGTH_SHORT).show();
                }
            }
        }).execute();
    }

    private void lauchJokeActivity() {
        Intent intent = new Intent(MainActivity.this, JokeActivity.class);
        intent.putExtra("joke", joke);
        startActivity(intent);
        canLaunch=false;

    }


}

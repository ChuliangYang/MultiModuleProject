package com.example.cl.joke;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {
    TextView tv_joke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        tv_joke= (TextView) findViewById(R.id.tv_joke);
        if (getSupportActionBar()!=null) {
            getSupportActionBar().setTitle("Joke");
        }
        if (!TextUtils.isEmpty(getIntent().getStringExtra("joke"))) {
            tv_joke.setText(getIntent().getStringExtra("joke"));
        } else {
            tv_joke.setText("There is no joke!");
        }

    }
}

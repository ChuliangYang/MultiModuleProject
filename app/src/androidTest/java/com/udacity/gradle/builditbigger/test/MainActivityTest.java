package com.udacity.gradle.builditbigger.test;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;

import com.example.Joker;
import com.udacity.gradle.builditbigger.FetchJokeAsynctask;
import com.udacity.gradle.builditbigger.MainActivity;

import java.util.List;

/**
 * Created by CL on 9/30/17.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Context context;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        context=getActivity();
    }

    @android.test.UiThreadTest
    public void testFetchJokeTask() throws Exception {
        final List jokes=new Joker().getJokes();
        new FetchJokeAsynctask(context, new FetchJokeAsynctask.onSuccessListener() {
                    @Override
                    public void onSuccess(String result) {
                        assertTrue(jokes.contains(result));
                    }
                }).execute();
    }

}
package com.udacity.gradle.builditbigger;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.example.cl.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by CL on 9/29/17.
 */

public class FetchJokeAsynctask extends AsyncTask<Void, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    private onSuccessListener onSuccessListener;
    public static ProgressDialog progressDialog;
    public static boolean testFlag=false;

    public FetchJokeAsynctask(Context context,onSuccessListener onSuccessListener) {
        this.onSuccessListener = onSuccessListener;
        this.context=context;
        if (progressDialog==null) {
            progressDialog=WidgetHelper.createProgressDialog(context);
        }
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (!testFlag) {
            progressDialog.show();
        }
    }

    @Override
    protected String doInBackground(Void... params) {
        if(myApiService == null) {  // Only do this once
//            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
//                    new AndroidJsonFactory(), null)
//                    // options for running against local devappserver
//                    // - 10.0.2.2 is localhost's IP address in Android emulator
//                    // - turn off compression when running against local devappserver
//                    .setRootUrl("https://fit-rig-181518.appspot.com/_ah/api/");
////                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
////                        @Override
////                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
////                            abstractGoogleClientRequest.setDisableGZipContent(true);
////                        }
////                    });

            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/") // 10.0.2.2 is localhost's IP address in Android emulator
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            myApiService = builder.build();
        }

        try {
            return myApiService.fetchJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }



    @Override
    protected void onPostExecute(String result) {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        if (onSuccessListener!=null) {
            onSuccessListener.onSuccess(result);
        }
    }

    public interface onSuccessListener{
        void onSuccess(String result);
    }

}

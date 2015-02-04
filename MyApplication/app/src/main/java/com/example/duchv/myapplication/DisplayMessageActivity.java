package com.example.duchv.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;

import static java.lang.Runtime.getRuntime;


public class DisplayMessageActivity extends ActionBarActivity {

    File logFile,tmpLogFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_message);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        TextView m = (TextView) findViewById(R.id.message);
        m.setText(message);

        TextView lon = (TextView) findViewById(R.id.lon);
        lon.setText(""+getFilesDir());
        TextView lat = (TextView) findViewById(R.id.lat);
        getLogFileToString(lat);
        lat.setTextSize(11);



//        lat.setText("latitude :");
//        try {
////            Process process = getRuntime().exec("logcat-d  | grep `ps | grep com.example.duchv.myapplication | cut -c10-15`");
//            Process process = getRuntime().exec("logcat -v time com.example.duchv.myapplication:D *:S");
//            BufferedReader bufferedReader = new BufferedReader(
//                    new InputStreamReader(process.getInputStream()));
//
//            StringBuilder log=new StringBuilder();
//            String line;
//            while ((line = bufferedReader.readLine()) != null) {
//                log.append(line);
//            }
//
//            lat.setText(log.toString());
//        } catch (IOException e) {
//        }

//        //Create TextView
//        TextView textview = new TextView(this);
//        textview.setTextSize(40);
//        textview.setText(message);
//
//        //set layout for activity
//        setContentView(textview);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void getLogFileToString(TextView v){

        String line = null;
        tmpLogFile = new File(Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_DCIM + "/tmp_log.txt");
        try{
            BufferedReader br = new BufferedReader(new FileReader(tmpLogFile));

            while((line = br.readLine()) != null)
            {
                v.append(line);
                v.append("\n");

            }
        }catch (Exception e){
            Log.e("?READ?","Can't read Log");
        }
//        for(int i =0; i< 200000;i++){
//            Log.d("DEBUG","PRINT TEST LOG");
//        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater =  getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }




    public void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }

        in.close();
        out.flush();
        out.close();
    }

    public void sendMail(View view){
        logFile = new File(Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_DCIM + "/log.txt");
        ArrayList<Uri> uris = new ArrayList<Uri>();
        uris.add(Uri.fromFile(logFile));
        uris.add(Uri.fromFile(tmpLogFile));
        Intent emailIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
        // set the type to 'email'
        emailIntent .setType("vnd.android.cursor.dir/email");
        String to[] = {};
        emailIntent .putExtra(Intent.EXTRA_EMAIL, to);
        // the attachment
        emailIntent .putExtra(Intent.EXTRA_STREAM, uris);
        // the mail subject
        emailIntent .putExtra(Intent.EXTRA_SUBJECT, "Log Test");
        startActivity(Intent.createChooser(emailIntent , "Send email..."));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
        switch (id){
            case  R.id.action_search:
//                openSearch();
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

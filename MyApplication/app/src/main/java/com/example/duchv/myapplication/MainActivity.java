package com.example.duchv.myapplication;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;


public class MainActivity extends ActionBarActivity {

    public final static String EXTRA_MESSAGE = "com.example.duchv.myapplication.MESSAGE";

    private PendingIntent pendingIntent;

    public void sendMessage(View view){
        Intent itent = new Intent(this,DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        itent.putExtra(EXTRA_MESSAGE,message);
        startActivity(itent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater =  getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);

        Button show = (Button) findViewById(R.id.button_show);

        show.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                showDialog();
            }

        });
        return super.onCreateOptionsMenu(menu);
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

    public void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Ok ->show notification or Cancel -> set Arlam ")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //test send and show notifi by broadcast
                        Intent sendPush = new Intent();
                        sendPush.setAction("com.google.android.c2dm.intent.RECEIVE");
                        sendOrderedBroadcast(sendPush, null);
                    }
                });
//                .setNegativeButton("CANCEL",new DialogInterface.OnClickListener(){
//                    public void onClick(DialogInterface dialog, int id){
//                        //
//                        setAlarm();
//                    }
//                });

        AlertDialog alert = builder.create();
        alert.show();
}

    private void setAlarm(){
        //set alarm to show notification.
        //create intent
        Intent myIntent = new Intent(this,MyNotificationService.class);
        //create alarm
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        pendingIntent= PendingIntent.getService(this,0,myIntent,0) ;

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 13);
        calendar.set(Calendar.MINUTE,8);
        calendar.set(Calendar.SECOND, 0);

        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
//        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
//        int interval = 8000;
//
//        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
//        Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
    }


}

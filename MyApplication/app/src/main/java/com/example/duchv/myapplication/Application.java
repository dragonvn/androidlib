package com.example.duchv.myapplication;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Runtime.getRuntime;

/**
 * Created by duchv on 1/29/15.
 */
public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        final File logFile,tmpLogFile;
        logFile= new File(Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_DCIM + "/log.txt");
        tmpLogFile= new File(Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_DCIM + "/tmp_log.txt");

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
//                    Process process = getRuntime().exec("logcat -v time com.example.duchv.myapplication:V *:S");
//                    Process process = getRuntime().exec("logcat -v time | grep --line-buffered com.example.duchv.myapplication");
                    Process process = getRuntime().exec("logcat -v time | grep --line-buffered com.example.duchv.myapplication");//全部ログ取る





//                    Process process = getRuntime().exec("logcat -v time | grep com.example.duchv.myapplication");

                    BufferedReader bufferedReader= new BufferedReader(
                            new InputStreamReader(process.getInputStream())
                    );


                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    FileOutputStream fos = new FileOutputStream(logFile,true);
                    FileOutputStream fost = new FileOutputStream(tmpLogFile,false);


                    //新しいログだけ書き出す
                    while((bufferedReader.readLine())!= null) {//true
                        line = bufferedReader.readLine()+"\n";
                        stringBuilder.append(line);
                        fos.write(line.getBytes());
                        fost.write(line.getBytes());
                        fos.flush();
                        fost.flush();
                    }
                } catch (IOException  e) {
                    Log.e("%%%", "Error when writing log to file", e);
                }

            }
        };

        thread.start();
    }
}

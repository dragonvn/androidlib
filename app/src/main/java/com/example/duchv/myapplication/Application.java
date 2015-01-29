package com.example.duchv.myapplication;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import static java.lang.Runtime.getRuntime;

/**
 * Created by duchv on 1/29/15.
 */
public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        final File logFile;
        logFile= new File(Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_DCIM + "/log.txt");
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
//                    Process process = getRuntime().exec(" logcat -v time com.example.duchv.myapplication:V *:S");
//                    Process process = getRuntime().exec("logcat -v time | grep --line-buffered com.example.duchv.myapplication");
                    Process process = getRuntime().exec("logcat -v time | grep --line-buffered com.example.duchv.myapplication");//全部ログ取る





//                    Process process = getRuntime().exec("logcat -v time | grep com.example.duchv.myapplication");

                    BufferedReader bufferedReader= new BufferedReader(
                            new InputStreamReader(process.getInputStream())
                    );


                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    FileOutputStream fos = new FileOutputStream(logFile,true);


                    //新しいログだけ書き出す
                    while(true) {//true
                        line = bufferedReader.readLine()+"\n";
                        stringBuilder.append(line);
                        final byte[] aaaaaaa = line.getBytes();
                        fos.write(aaaaaaa);

//                        android.util.Log.d("tagu", new String(aaaaaaa, Charset.defaultCharset()) + "\n" );


                    }
                } catch (IOException  e) {
                    Log.e("%%%", "Error when writing log to file", e);
                }
            }
        };

        thread.start();
    }
}

package com.nguyenhuutin.multithread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static android.widget.LinearLayout.*;

public class MainActivity extends AppCompatActivity {
    EditText edtnumber;
    Button btnDraw;
    TextView txtpercent;
    ProgressBar pgpercent;
    LinearLayout layoutControls;
    //int numb = 0, percent, value;
    Random random = new Random();

    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(200, LinearLayout.LayoutParams.WRAP_CONTENT);
//    Thread thread = new Thread(new Runnable() {
//        @Override
//        public void run() {
//            for (int i=1;i<=numb;i++){
//                percent = i*100/numb;
//                value = random.nextInt(100);
//                handler.post(foregroundThread);
//                SystemClock.sleep(100);
//        }
//        }
//    });
//    Handler handler = new Handler();
//    Runnable foregroundThread = new Runnable() {
//        @Override
//        public void run() {
//            if(percent==100){
//                Toast.makeText(MainActivity.this,"Done",Toast.LENGTH_LONG).show();
//            }
//            else {
//
//                ImageButton imageButton = new ImageButton(MainActivity.this);
//                if(value%2==0){
//                    imageButton.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_baseline_brightness_4_24));
//                }
//                else {
//                    imageButton.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_baseline_brightness_3_24));
//                }
//                imageButton.setLayoutParams(layoutParams);
//                layoutControls.addView(imageButton);
//            }
//            txtpercent.setText(percent + "%");
//            pgpercent.setProgress(percent);
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinkView();
        addEvent();
    }
    private void LinkView() {
        edtnumber = findViewById(R.id.edtnumber);
        btnDraw = findViewById(R.id.btnDraw);
        txtpercent = findViewById(R.id.txtpercent);
        pgpercent = findViewById(R.id.pgpercent);
        layoutControls = findViewById(R.id.layoutControl);
    }
    private void addEvent() {
        btnDraw.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // cach 1:
                //drawUI();

                //cach 2:
//                layoutControls.removeAllViews();
//                numb = Integer.parseInt(edtnumber.getText().toString());
//                thread.start();

                //cach 3:
                int numb = Integer.parseInt(edtnumber.getText().toString());
                MyAsyncTask myAsyncTask = new MyAsyncTask();
                myAsyncTask.execute(numb);
            }
        });
    }

// Cach 1:
//    Handler handler = new Handler(new Handler.Callback() {
//        @Override
//        public boolean handleMessage(@NonNull Message msg) {
//            int percent = msg.arg1;
//            if(percent==100){
//                Toast.makeText(MainActivity.this,"Done",Toast.LENGTH_LONG).show();
//            }
//            else {
//                int value = (int) msg.obj;
//                ImageButton imageButton = new ImageButton(MainActivity.this);
//                if(value%2==0){
//                    imageButton.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_baseline_brightness_3_24));
//                }
//                else {
//                    imageButton.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_baseline_brightness_4_24));
//                }
//                imageButton.setLayoutParams(layoutParams);
//                layoutControls.addView(imageButton);
//            }
//            txtpercent.setText(percent + "%");
//            pgpercent.setProgress(percent);
//            return false;
//        }
//    });
//
//    private void drawUI(){
//        layoutControls.removeAllViews();
//        numb = Integer.parseInt(edtnumber.getText().toString());
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i=1;i<=numb;i++){
//                    Message message = handler.obtainMessage();
//                    message.arg1 = i*100/numb;
//                    message.obj = random.nextInt(100);
//                    handler.sendMessage(message);
//                    SystemClock.sleep(100);
//                }
//            }
//        });
//        thread.start();
//    }

    //Cach 3: Su dung MyAsyncTask
    private  class MyAsyncTask extends AsyncTask<Integer, Integer, Void>{
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        txtpercent.setText("0%");
        layoutControls.removeAllViews();
    }

    @Override
    protected Void doInBackground(Integer... integers) {
        int n = integers[0], percent, value;
        for (int i=1;i<=n;i++){
                percent = i*100/n;
                value = random.nextInt(100);
                publishProgress(percent, value);
                SystemClock.sleep(100);
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        int percent = values[0];
        int value = values[1];
        if(percent==100){
                Toast.makeText(MainActivity.this,"Done",Toast.LENGTH_LONG).show();
            }
            else {

                ImageButton imageButton = new ImageButton(MainActivity.this);
                if(value%2==0){
                    imageButton.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_baseline_brightness_4_24));
                }
                else {
                    imageButton.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_baseline_brightness_3_24));
                }
                imageButton.setLayoutParams(layoutParams);
                layoutControls.addView(imageButton);
            }
        txtpercent.setText(percent + "%");
        pgpercent.setProgress(percent);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        txtpercent.setText("Done");
    }
}
}
package com.gjg.sanjiaoview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.math.BigInteger;

public class MainActivity extends AppCompatActivity {
    SanJiaoTextView sanJiaoTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sanJiaoTextView = (SanJiaoTextView) findViewById(R.id.stv);
//        final Handler handler = new Handler();
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                sanJiaoTextView.setText("募集完成");
//                sanJiaoTextView.setBgColor(getResources().getColor(R.color.colorAccent));
//                handler.postDelayed(this,1000);
//            }
//        };
//        handler.postDelayed(runnable,1000);
        sanJiaoTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uuid = "b3fdd56560e34989ac218373c7a9b2fc";
//                  String uuid = "396797f9c3ba4c29b53daabed91d2407";
//                String uuid = "b3fdd56560e34989ac218373c7a9b2ff";
//                String uuid = "03fdd56560e34989ac218373c7a9b2ff";
//                String uuid = "f3fdd56560e34989ac218373c7a9b2f0";
//                String uuid = "ac218373c7a9b25c";
//                String uuid = "b3";
                try {
                    BigInteger bigInteger = new BigInteger(uuid,16);
                    long id = bigInteger.longValue();
//                    long id = Long.parseLong(uuid,16);
                    Toast.makeText(MainActivity.this,""+id,Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    String eStr = e.getMessage();
                    Toast.makeText(MainActivity.this,""+eStr,Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

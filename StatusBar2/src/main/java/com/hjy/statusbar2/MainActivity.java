package com.hjy.statusbar2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hjy.statusbar2.ui.Scene1Activity;
import com.hjy.statusbar2.ui.Scene2Activity;
import com.hjy.statusbar2.ui.Scene3Activity;
import com.hjy.statusbar2.ui.Scene4Activity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main2_btn1:
                startActivity(new Intent(this, Scene1Activity.class));
                break;
            case R.id.main2_btn2:
                startActivity(new Intent(this, Scene2Activity.class));
                break;
            case R.id.main2_btn3:
                startActivity(new Intent(this, Scene3Activity.class));
                break;
            case R.id.main2_btn4:
                startActivity(new Intent(this, Scene4Activity.class));
                break;
        }
    }
}

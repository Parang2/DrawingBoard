package com.example.drawingboard;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    // SetMain 클래스 선언
    Setmain csetmain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // SetMain 객체생성
        csetmain = new Setmain(this);
    }
}
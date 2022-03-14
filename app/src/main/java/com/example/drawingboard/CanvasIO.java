package com.example.drawingboard;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import java.io.FileInputStream;
import java.io.IOException;


public class CanvasIO {
    private final static String TAG = Canvas.class.getName();
    private final static String FILE_NAME = "draw_file.jpg";

    public static Bitmap openBitmap(Context context) {
        Bitmap result = null;

        try {
            // 파일을 읽거나 쓸 수 있도록 하는 클래스
            // 액티비티와 애플리케이션에 대한 정보를 얻기 위해
            // 안드로이드 내부 저장장치 에 파일을 생성하여 쓰고 읽기(그림파일)

            FileInputStream fis = context.openFileInput(FILE_NAME);

            // 안드로이드에서 비트맵을 표현하기 위한 자료형 Bitmap
            // BitmapFactory 그림파일의 정보를 얻어온다
            // decodeStream 안드로이드에서 사용할 비트맵 그림파일 로드
            result = BitmapFactory.decodeStream(fis);
            
            // FileInputStream 종료
            fis.close();
        } catch (IOException e) {
            Log.e(TAG, "Don't open canvas");
            e.printStackTrace();
        }

        return result;
    }
}

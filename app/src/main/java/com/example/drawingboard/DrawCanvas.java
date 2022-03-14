package com.example.drawingboard;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DrawCanvas extends View {

    public static final int MODE_PEN = 1;                     //모드 (펜)
    public static final int MODE_ERASER = 0;                  //모드 (지우개)
    final int PEN_SIZE = 2;                                   //펜 사이즈
    final int ERASER_SIZE = 30;                               //지우개 사이즈

    ArrayList<Pen> drawCommandList;                           //그리기 경로가 기록된 리스트
    Paint paint;                                              //펜
    Bitmap loadDrawImage;                                     //호출된 이전 그림
    int color;                                                //현재 펜 색상
    int size;                                                 //현재 펜 크기

    public DrawCanvas(Context context) {
        super(context);
        init();
    }

    public DrawCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawCanvas(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 초기화
     */
    void init() {
        //화면에 좀 더 부드럽게 나타나게 하는 펜을 생성한다
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //그린 경로를 담는 ArrayList를 새로 만든다
        drawCommandList = new ArrayList<>();
        //이전 그림을 없다고 설정한다.
        loadDrawImage = null;
        //펜 색을 검은색으로 설정한다
        color = Color.BLACK;
        size = PEN_SIZE ;
    }


    void changeColor(int color) {
        this.color = color;     //색 변경
    }

    void changeTool(int toolMode) {
        //만약 펜모드로 설정되어있을때
        if (toolMode == MODE_PEN) {
            //펜 색상을 검은색으로    지정
            this.color = Color.BLACK;
            size = PEN_SIZE;
        } else {
            //펜 색상을 흰색으로 지정
            this.color = Color.WHITE;
            size = ERASER_SIZE;
        }
        paint.setColor(color);
    }

    /**
     * @param canvas
     * View 클래스에서 상속받아 강제구현
     * 화면을 다시 그릴 필요가 있을 때 자동호출
     *
     */
    @Override
    protected void onDraw(Canvas canvas) {
        // 배경 색상 흰색
        canvas.drawColor(Color.WHITE);

        // 이전그림이 있다면
        if (loadDrawImage != null) {
            // 그 위에 그림을 그린다.
            canvas.drawBitmap(loadDrawImage, 0, 0, null);
        }

        /**
         * 시작좌표부터 끝 좌표까지 점 단위로 선이 표현되며
         * 색상, 두께, xy좌표를 각각의 점마다 리스트 형태로 데이터를 저장한다.
         * 터치가 끝난 이후 이미지 형태로 저장하여 drawBitmap으로 다시 화면에 출력한다
         *
         */
        for (int i = 0; i < drawCommandList.size(); i++) {
            //p 변수에 리스트 데이터를 저장
            // 지나온 좌표를 저장한다.
            Pen p = drawCommandList.get(i);
            //선 색상 지정
            paint.setColor(p.color);
            //선 두께 지정
            paint.setStrokeWidth(p.size);

            //펜이 움직이고 있을 때
            if (p.isMove()) {
                Pen p1 = drawCommandList.get(i - 1);
                //좌표를 받아 선의 시작과 끝을 잇는 선을 그린다
                canvas.drawLine(p1.x, p1.y, p.x, p.y, paint);
            }
        }
    }


    /**
     * 화면을 터치했을 때
     */
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        int action = e.getAction();
        //(ACTION_DOWN)처음 눌렀을때
        //(STATE_START)펜이 움직이기 시작할때
        //(STATE_MOVE)펜이 움직이고 있을때
        int state = action == MotionEvent.ACTION_DOWN ? Pen.STATE_START : Pen.STATE_MOVE;
        //리스트 값에 펜의 x좌표, y좌표, 움직임, 색상, 크기 값들을 추가한다
        drawCommandList.add(new Pen(e.getX(), e.getY(), state, color, size));
        invalidate();   //화면 초기화
        return true;
    }
}

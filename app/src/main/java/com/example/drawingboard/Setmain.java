package com.example.drawingboard;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Setmain{

    AppCompatActivity asetmain;
    DrawCanvas drawCanvas;
    private FloatingActionButton fbPen;          //펜 모드 버튼
    private FloatingActionButton fbEraser;       //지우개 모드 버튼
    private FloatingActionButton fbOpen;         //그림 호출 버튼
    private ConstraintLayout canvasContainer;    //캔버스 root vi

    public Setmain(AppCompatActivity appCompatActivity){
        // 현재 위치의 Activity를 받아와 저장한다.
        asetmain = appCompatActivity;
        // DrawCanvas 객체생성
        drawCanvas = new DrawCanvas(asetmain);
        //findId 메소드 호출
        findId();
        // canvasContainer에 뷰를 추가한다.
        // 그림판에 액션이 호출되며 선이 추가될 때 뷰에 이미지를 추가로 등록한다.
        canvasContainer.addView(drawCanvas);
        // 화면에 클릭 이벤트가 발생했을 때 
        setOnClickListener();
        // 버튼 이벤트 처리
        setButton();
    }

    public void setButton() {
        /**
         * 버튼 ID연결
         */
        Button buttonRed = (Button) asetmain.findViewById(R.id.button1);
        Button buttonBlue = (Button) asetmain.findViewById(R.id.button2);
        Button buttonYellow = (Button) asetmain.findViewById(R.id.button3);
        Button buttonGreen = (Button) asetmain.findViewById(R.id.button4);
        Button buttonBlack = (Button) asetmain.findViewById(R.id.button5);

        /**
         * "빨강"버튼을 눌렀을 때
         */
        buttonRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //선 색을 빨간색으로 변환
                drawCanvas.changeColor(Color.RED);
            }
        });

        /**
         *"파랑"버튼을 눌렀을 때
         */
        buttonBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //선 색을 파란색으로 변환
                drawCanvas.changeColor(Color.BLUE);
            }
        });

        /**
         * "노랑"버튼을 눌렀을 때
         */
        buttonYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //선 색을 노란색으로 변환
                drawCanvas.changeColor(Color.YELLOW);
            }
        });

        /**
         * "초록"버튼을 눌렀을 때
         */
        buttonGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //선 색을 초록색으로 변환
                drawCanvas.changeColor(Color.GREEN);
            }
        });

        /**
         * "검정"버튼을 눌렀을 때
         */
        buttonBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //선 색을 검정색으로 변환
                drawCanvas.changeColor(Color.BLACK);
            }
        });

    }


    /**
     * 그림판에 필요한 각 View를 변수선언 후 id와 연결
     */
    private void findId() {
        canvasContainer = asetmain.findViewById(R.id.lo_canvas);
        fbPen = asetmain.findViewById(R.id.fb_pen);
        fbEraser = asetmain.findViewById(R.id.fb_eraser);
        fbOpen = asetmain.findViewById(R.id.fb_open);
        drawCanvas = new DrawCanvas(asetmain);
    }

    /**
     * FloatingActionButton을 눌렀을 때
     */
    private void setOnClickListener() {
        //펜 모양 버튼을 눌렀을 때
        fbPen.setOnClickListener((v) -> {
            //펜모드로 전환
            drawCanvas.changeTool(DrawCanvas.MODE_PEN);
        });

        //지우개 모양 버튼을 눌렀을 때
        fbEraser.setOnClickListener((v) -> {
            //지우개 모드로 변환
            drawCanvas.changeTool(DrawCanvas.MODE_ERASER);
        });


        //오픈(파일 문서)버튼을 눌렀을 때
        fbOpen.setOnClickListener((v) -> {
            drawCanvas.init();
            drawCanvas.loadDrawImage = CanvasIO.openBitmap(asetmain);
            drawCanvas.invalidate();        //화면을 갱신한다
        });
    }

}

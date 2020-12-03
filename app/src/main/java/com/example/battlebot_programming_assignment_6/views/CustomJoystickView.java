package com.example.battlebot_programming_assignment_6.views;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.battlebot_programming_assignment_6.HostInfoDialog;
import com.example.battlebot_programming_assignment_6.Joystick;
import com.example.battlebot_programming_assignment_6.MainActivity;
import com.example.battlebot_programming_assignment_6.MySocket;
import com.example.battlebot_programming_assignment_6.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomJoystickView extends View {
     public Joystick joystick;
    public CustomJoystickView joystickView1;



    public CustomJoystickView(Context context) {
        super(context);


        initJoystick(null);
    }

    public CustomJoystickView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


        initJoystick(null);

    }

    public CustomJoystickView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        initJoystick(attrs);

    }

    public CustomJoystickView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);


        initJoystick(attrs);

    }

    private void initJoystick(@Nullable AttributeSet set)
    {
        joystick= new Joystick(200, 300, 200,100);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        joystick.draw(canvas);
        joystick.update();
    }





    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean value= super.onTouchEvent(event);
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                if(joystick.isPressed((int) event.getX(), (int) event.getY()))
                {
                    joystick.setIsPressed(true);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(joystick.getIsPressed())
                {
                    joystick.setActuator((int) event.getX(), (int) event.getY());
                    joystick.update();
                    String moveMessage = "move " + (int) event.getX() + " " + (int) event.getX();
                    String testMoveUp= "move " + 1 + " " + 0;
                    String testMoveDown= "move " + -1 + " " + 0;
                    String testMoveLeft= "move " + 0 + " " + -1;
                    String testMoveRight= "move " + 0 + " " + -1;
                    ExecutorService service= Executors.newFixedThreadPool(1);
                    service.execute(()->{
                        MySocket socket = new MySocket();
                        socket.writeLine(moveMessage);
                        socket.writeLine(testMoveDown);
                        socket.writeLine(testMoveLeft);
                        socket.writeLine(testMoveRight);
                        socket.getLine();
                    });
                }
                break;
            case MotionEvent.ACTION_UP:
                joystick.setIsPressed(false);
                joystick.resetActuator();
                break;


        }
        return value;

    }




    @Override
    public void setOnTouchListener(OnTouchListener l) {
        super.setOnTouchListener(l);

    }




    public interface MovementInfoListener
    {
        void sendMovement(int moveX, int moveY, boolean isPressed);
    }
}


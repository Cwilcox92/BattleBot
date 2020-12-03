package com.example.battlebot_programming_assignment_6;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Joystick {
    private int innerCircleRadius;
    private int innerCircleCenterPositionX;
    private int innerCircleCenterPositionY;
    private int outerCircleRadius;
    private int outerCircleCenterPositionX;
    private int outerCircleCenterPositionY;
    private Paint mPaintOuter;
    private Paint mPaintInner;
    private double joystickCenterToTouchDistance;
    private boolean isPressed;
    private double actuatorX;
    private double actuatorY;



    public Joystick(int centerPositionX, int centerPositionY, int outerCircleRadius, int innerCircleRadius)
{
    mPaintOuter= new Paint(Paint.ANTI_ALIAS_FLAG);
    mPaintInner= new Paint(Paint.ANTI_ALIAS_FLAG);


    outerCircleCenterPositionX= centerPositionX;
    outerCircleCenterPositionY= centerPositionY;
    innerCircleCenterPositionX= centerPositionX;
    innerCircleCenterPositionY= centerPositionY;

    //Radii of Circles
    this.outerCircleRadius= outerCircleRadius;
    this.innerCircleRadius= innerCircleRadius;

    //color of circles
    mPaintOuter.setColor(Color.GRAY);
    mPaintOuter.setStyle(Paint.Style.FILL_AND_STROKE);
    mPaintInner.setColor(Color.BLUE);
    mPaintInner.setStyle(Paint.Style.FILL_AND_STROKE);

}

public void draw(Canvas canvas)
{
    //Draws outer circle
    canvas.drawCircle(
            outerCircleCenterPositionX,
            outerCircleCenterPositionY,
            outerCircleRadius,
            mPaintOuter);

    //Draws Inner circle
    canvas.drawCircle(
            innerCircleCenterPositionX,
            innerCircleCenterPositionY,
            innerCircleRadius,
            mPaintInner);
    update();
}

    public void update(){
    updateInnerCirclePosition();
    }

    private void updateInnerCirclePosition() {
        innerCircleCenterPositionX = (int) (outerCircleCenterPositionX + actuatorX*outerCircleRadius);
        System.out.println(innerCircleCenterPositionX);
        innerCircleCenterPositionY = (int) (outerCircleCenterPositionY + actuatorY*outerCircleRadius);
    }

    public int getXPosition()
    {
        return innerCircleCenterPositionX;
    }

    public int getYPosition()
    {
        return innerCircleCenterPositionY;
    }





    public boolean isPressed(double touchPositionX, double touchPositionY) {
        joystickCenterToTouchDistance= Math.sqrt(
                Math.pow(outerCircleCenterPositionX - touchPositionX, 2) +
                        Math.pow(outerCircleCenterPositionY- touchPositionY, 2)
        );
    return joystickCenterToTouchDistance < outerCircleRadius;
    }

    public void setIsPressed(boolean isPressed) {
        this.isPressed = isPressed;
    }

    public boolean getIsPressed() {
        return isPressed= true;
    }

    // 0 means we are not pulling the joystick
    // 1 means that we are
    public void setActuator(double touchPositionX, double touchPositionY) {
        double deltaX = touchPositionX - outerCircleCenterPositionX;
        double deltaY= touchPositionY - outerCircleCenterPositionY;
        double deltaDistance= Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY,2));

        if(deltaDistance < outerCircleRadius)
        {
            actuatorX = deltaX/outerCircleRadius;
            actuatorY= deltaY/outerCircleRadius;
        } else {
            actuatorX= deltaX/deltaDistance;
            actuatorY= deltaY/deltaDistance;
        }
    }

    public void resetActuator() {
        actuatorX= 0.0;
        actuatorY=0.0;
    }




}

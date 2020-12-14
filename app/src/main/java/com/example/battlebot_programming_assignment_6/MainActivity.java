package com.example.battlebot_programming_assignment_6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.battlebot_programming_assignment_6.views.CustomJoystickView;

import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// This activity will be the init setup of the bot
// The user will assign the point values to the bot here
// Here we will send the info to the server communicator or just communicate directly from here
public class MainActivity extends AppCompatActivity implements HostInfoDialog.HostInfoListener, CustomJoystickView.MovementInfoListener {
    private Button rdyBtn;
    private Button armorInc;
    private Button armorDec;
    private Button shotPwrInc;
    private Button shotPwrDec;
    private Button scanRngInc;
    private Button scanRngDec;

    public TextView hostIpName;
    public TextView portNumber;
    public TextView pntsRemaining;
    public TextView armorStatDisplay;
    public TextView shotStatDisplay;
    public TextView scanStatDisplay;

    public EditText hostDisplayName;

    public int skillPoints= 5;
    public int armorStat=0;
    public int bulletStat=0;
    public int scanStat=0;
    public int moveRate;
    public int bulletPwr;
    public int bulletDist;
    public int rateOfFire;
    public int scanDist;
    public int portNumberActual;
    public int moveBotX;
    public int moveBotY;

    public  String hostNameActual;
    public String displayName;

    public boolean isMovementPressed= false;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // The name that the user gives
        hostDisplayName= (EditText) findViewById(R.id.editTextDisplayName);
        //host ip and port # that they provide
        hostIpName= (TextView) findViewById(R.id.ipDisplay);
        portNumber= (TextView) findViewById(R.id.portDisplay);
        //Skill points
        pntsRemaining= (TextView) findViewById(R.id.remainingPointsDisplay);
        pntsRemaining.setText(String.valueOf(skillPoints));
        //Stats displays
        armorStatDisplay= (TextView) findViewById(R.id.botArmorStats);
        shotStatDisplay= (TextView) findViewById(R.id.botShotPwrStat);
        scanStatDisplay= (TextView) findViewById(R.id.botScanRngStat);
        //Opens the dialog that prompts teh user for their host name/ip and port
        openHostDialog();
        // ready up button needs to direct us to the controller activity
        rdyBtn= findViewById(R.id.rdyUpButton);
        //bttns for the player stats
        armorInc= findViewById(R.id.incArmor);
        armorDec= findViewById(R.id.decArmor);
        shotPwrInc= findViewById(R.id.incShotPwr);
        shotPwrDec= findViewById(R.id.decShotPwr);
        scanRngInc= findViewById(R.id.incScanRng);
        scanRngDec= findViewById(R.id.decScanRng);
        rdyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String serverStats= hostDisplayName.getText().toString()+ " "+ armorStat+ " "+ bulletStat+ " "+ scanStat;
                ExecutorService executorService= Executors.newFixedThreadPool(1);
                executorService.execute(()->{
                    MySocket socket= new MySocket();
                    //need to put localIP
                    socket.MakeClientCon(hostNameActual,portNumberActual);
                    socket.writeLine(serverStats);
                });
                openControllerActivtiy();
            }
        });
        armorInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(skillPoints != 0)
                {
                    skillPoints--;
                    pntsRemaining.setText(String.valueOf(skillPoints));
                    if(armorStat != 5)
                    {
                        armorStat++;
                        armorStatDisplay.setText(String.valueOf(armorStat+1));
                        moveRate= armorStat-1;
                    }

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"No more available points", Toast.LENGTH_SHORT).show();
                }

            }
        });
        armorDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(skillPoints == 0 || skillPoints<5)
                {
                    skillPoints++;
                    pntsRemaining.setText(String.valueOf(skillPoints));
                    if(armorStat <= 5)
                    {
                        armorStat--;
                        armorStatDisplay.setText(String.valueOf(armorStat));
                        moveRate=armorStat + 1;
                    }
                }
                else if(skillPoints == 5)
                {
                    Toast.makeText(getApplicationContext(),"No more available points", Toast.LENGTH_SHORT).show();
                }
            }
        });
        shotPwrInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(skillPoints != 0)
                {
                    skillPoints--;
                    pntsRemaining.setText(String.valueOf(skillPoints));
                    if(bulletStat != 5)
                    {
                        bulletStat++;
                        shotStatDisplay.setText(String.valueOf(bulletStat+1));
                        rateOfFire= bulletStat * 10;
                        //bulletDist=
                    }

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"No more available points", Toast.LENGTH_SHORT).show();
                }

            }
        });
        shotPwrDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(skillPoints == 0 || skillPoints<5)
                {
                    skillPoints++;
                    pntsRemaining.setText(String.valueOf(skillPoints));
                    if(bulletStat <= 5)
                    {
                        bulletStat--;
                        shotStatDisplay.setText(String.valueOf(bulletStat));
                        if(bulletStat > 1)
                        {
                            bulletPwr= bulletStat - 10;
                        }

                    }
                }
                else if(skillPoints == 5)
                {
                    Toast.makeText(getApplicationContext(),"No more available points", Toast.LENGTH_SHORT).show();
                }
            }
        });
        scanRngInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(skillPoints != 0)
                {
                    skillPoints--;
                    pntsRemaining.setText(String.valueOf(skillPoints));
                    if(scanStat != 5)
                    {
                        scanStat++;
                        scanStatDisplay.setText(String.valueOf(scanStat+1));
                        scanDist= scanStat * 500;
                    }


                }
                else
                {
                    Toast.makeText(getApplicationContext(),"No more available points", Toast.LENGTH_SHORT).show();
                }

            }
        });
        scanRngDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(skillPoints == 0 || skillPoints<5)
                {
                    skillPoints++;
                    pntsRemaining.setText(String.valueOf(skillPoints));
                    if(scanStat <= 5)
                    {
                        scanStat--;
                        scanStatDisplay.setText(String.valueOf(scanStat));
                        if(scanStat > 1)
                        {
                            scanDist= scanStat - 500;

                        }
                    }
                }
                else if(skillPoints == 5)
                {
                    Toast.makeText(getApplicationContext(),"No more available points", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void openControllerActivtiy()
    {
        Intent intent = new Intent(this, ControllerActivity.class);
        startActivity(intent);
    }

    public void openHostDialog()
    {
        HostInfoDialog hostInfoDialog= new HostInfoDialog();
        hostInfoDialog.show(getSupportFragmentManager(),"host info");
    }

    @Override
    public void applyTexts(String host, String port) {
        //hostIpName.setText(host);
        portNumber.setText(port);
        hostNameActual= host;
        portNumberActual= Integer.parseInt(port);

    }

    @Override
    public void sendMovement(int moveX, int moveY, boolean isPressed) {
        moveBotX= moveX;
        moveBotY= moveY;
        isMovementPressed= isPressed;
    }
}
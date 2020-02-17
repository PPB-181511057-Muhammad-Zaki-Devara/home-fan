package com.deva.homefan;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity{

    ToggleButton toggleButton;
    ImageView fan;
    ObjectAnimator rotateAnimator;
    Switch lightSwitch;
    SeekBar speedBar;
    final int SPEED[] = {0, 5000, 3000, 100};
    GradientDrawable gd = new GradientDrawable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        fan = (ImageView) findViewById(R.id.fan);
        lightSwitch = (Switch) findViewById(R.id.lightSwitch);
        speedBar = (SeekBar) findViewById(R.id.speedBar);

        gd.setShape(GradientDrawable.OVAL);
        gd.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        gd.setGradientRadius(200);

        rotateAnimator = ObjectAnimator.ofFloat(fan, "rotation", 0, 360);
        rotateAnimator.setDuration(1000);
        rotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
        rotateAnimator.setInterpolator(new LinearInterpolator());

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked){
                    rotateAnimator.setDuration(SPEED[speedBar.getProgress()]);
                    rotateAnimator.start();
                }else{
                    rotateAnimator.end();
                }
            }
        });

        lightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked){
                    gd.setColors(new int[]{Color.RED, Color.TRANSPARENT});
                    fan.setBackground(gd);
                }else{
                    fan.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });

        speedBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(toggleButton.isChecked()){
                    rotateAnimator.setDuration(SPEED[progress]);
                    rotateAnimator.start();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}

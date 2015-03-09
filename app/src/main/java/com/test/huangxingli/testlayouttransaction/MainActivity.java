package com.test.huangxingli.testlayouttransaction;

import android.animation.Animator;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;


public class MainActivity extends ActionBarActivity {
    Animator currentAnimator,alphaAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentAnimator= ObjectAnimator.ofFloat(null,"rotationY",0,360,0);
        currentAnimator.setDuration(5000);
        alphaAnimation=ObjectAnimator.ofFloat(null,"alpha",1,0.5f);
        LinearLayout parent= (LinearLayout) findViewById(R.id.parent);
        final FixedGridLayout fixedGridLayout=new FixedGridLayout(this);
        fixedGridLayout.setCellWidth(100);
        fixedGridLayout.setCellHeight(90);
        //该效果也可以通过gridLayout来实现而不用自定义布局了
       // final GridLayout gridLayout=new GridLayout(getApplicationContext());
       // gridLayout.setColumnCount(5);

       // parent.addView(gridLayout);
        fixedGridLayout.setClipChildren(false);
        parent.setClipChildren(false);
        LayoutTransition layoutTransition=new LayoutTransition();
        layoutTransition.setAnimator(LayoutTransition.APPEARING,currentAnimator);
        layoutTransition.setAnimator(LayoutTransition.APPEARING,alphaAnimation);
        fixedGridLayout.setLayoutTransition(layoutTransition);
       // gridLayout.setLayoutTransition(layoutTransition);
        final Button button= (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button1=new Button(getApplicationContext());
                button1.setText(String.valueOf(fixedGridLayout.getChildCount()));
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      //  gridLayout.removeView(v);
                        fixedGridLayout.removeView(v);
                    }
                });
              //  gridLayout.addView(button1, fixedGridLayout.getChildCount());
                fixedGridLayout.addView(button1,fixedGridLayout.getChildCount());
                //可以通过这个来调整新添加的button的放置位置
              //  fixedGridLayout.addView(button1,Math.min(1,fixedGridLayout.getChildCount()));

            }
        });




    }



}

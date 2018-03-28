package com.smartboys.physicslabcalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.smartboys.physicslabcalculator.R;


public class SelectActivity extends Activity{
    private Button[] buttons = new Button[10];
    private ButtonListener listener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_activity);

        buttons[0]=(Button)findViewById(R.id.Button1);
        buttons[1]=(Button)findViewById(R.id.Button2);
        buttons[2]=(Button)findViewById(R.id.Button3);
        buttons[3]=(Button)findViewById(R.id.Button4);
        buttons[0].setOnClickListener(new ButtonListener());
        buttons[1].setOnClickListener(new ButtonListener());
        buttons[2].setOnClickListener(new ButtonListener());
        buttons[3].setOnClickListener(new ButtonListener());

        buttons[4]=(Button)findViewById(R.id.Button5);
        buttons[5]=(Button)findViewById(R.id.Button6);
        buttons[6]=(Button)findViewById(R.id.Button7);
        buttons[7]=(Button)findViewById(R.id.Button8);
        buttons[8]=(Button)findViewById(R.id.Button9);
        buttons[9]=(Button)findViewById(R.id.Button10);
        for(int i = 4; i < 10; i++)
            buttons[i].setOnClickListener(new CrapButtonListener());


    }




    public class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(SelectActivity.this, SettingListActivity.class);
            intent.putExtra("id", v.getId());
            startActivity(intent);
        }
    }

    public class CrapButtonListener implements View.OnClickListener{
           @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(SelectActivity.this,CrapActivity.class);
            startActivity(intent);
            }
        }
}


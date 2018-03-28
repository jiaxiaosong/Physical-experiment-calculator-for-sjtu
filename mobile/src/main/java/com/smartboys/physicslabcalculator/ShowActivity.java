package com.smartboys.physicslabcalculator;
import android.app.ListActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.SimpleAdapter;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.smartboys.physicslabcalculator.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 89364 on 2017/5/15.
 */

public class ShowActivity extends ListActivity{
    private HashMap<Integer, Integer> numMap = new HashMap<Integer, Integer>();
    private HashMap<Integer, String[]> nameMap = new HashMap<Integer, String[]>();
    private ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
    private double[] values;
    private Integer num ;
    private String[] names;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showlist_main_lmz);
        Intent intent = getIntent();

        values = intent.getDoubleArrayExtra("RESULTS");

        String[] names = intent.getStringArrayExtra("RESULTNAME");
        num = intent.getIntExtra("RESULTNUMBER",0);




        HashMap<String,String>[] maps = new HashMap[num];

        for (int i = 0; i < num; i++) {
            maps[i] = new HashMap<String,String>();
            maps[i].put("variable_name", names[i]);
            maps[i].put("variable_num",Double.toString( values[i]));
            list.add(maps[i]);
        }
        SimpleAdapter listAdapter = new SimpleAdapter(this,list,R.layout.showlist_sub_lmz,new String[]{"variable_name","variable_num"},new int[]{R.id.textView_li1,R.id.textView_li2});
        //listAdapter = new Adapter(this,list);

        setListAdapter(listAdapter);
        }
}

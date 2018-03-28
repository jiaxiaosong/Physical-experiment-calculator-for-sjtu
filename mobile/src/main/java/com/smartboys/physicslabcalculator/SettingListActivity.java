package com.smartboys.physicslabcalculator;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
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
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.smartboys.physicslabcalculator.R;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by doylle on 2017/5/11.
 */




public class SettingListActivity extends ListActivity {
    private HashMap<Integer, Integer> numMap = new HashMap<Integer, Integer>(), selectionMap = new HashMap<Integer, Integer>();
    private HashMap<Integer, String[]> nameMap = new HashMap<Integer, String[]>();
    private ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
    private Adapter listAdapter;
    private int[] values;
    private Button button;
    private int selection;
    private Integer num;
    private String[] names;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_list_main);

        button = (Button) findViewById(R.id.settinglistButton);
        button.setOnClickListener(new SettingButtonListener());


        Integer id = this.getIntent().getExtras().getInt("id");

        numMap.put(R.id.Button1, names1.length);
        nameMap.put(R.id.Button1, names1);
        selectionMap.put(R.id.Button1,4);
        numMap.put(R.id.Button2, names2.length);
        nameMap.put(R.id.Button2, names2);
        selectionMap.put(R.id.Button2, 1);
        numMap.put(R.id.Button3, names3.length);
        nameMap.put(R.id.Button3, names3);
        selectionMap.put(R.id.Button3, 2);
        numMap.put(R.id.Button4, names4.length);
        nameMap.put(R.id.Button4, names4);
        selectionMap.put(R.id.Button4, 3);


        num = numMap.get(id);
        names = nameMap.get(id);
        selection = selectionMap.get(id);

        HashMap<String, String>[] maps = new HashMap[num];


        for (int i = 0; i < num; i++) {
            maps[i] = new HashMap<String, String>();
            maps[i].put("variable_name", names[i]);
            list.add(maps[i]);
        }


        //SimpleAdapter listAdapter = new SimpleAdapter(this,list,R.layout.setting_list_sub,new String[]{"variable_name"},new int[]{R.id.settinglist_text});
        listAdapter = new Adapter(this, list);

        setListAdapter(listAdapter);

    }


    public class Adapter extends BaseAdapter {
        private LayoutInflater inflater;
        private ArrayList<HashMap<String, String>> list;
        private String[] data;

        public Adapter(Context context, ArrayList<HashMap<String, String>> list) {
            this.inflater = LayoutInflater.from(context);
            this.list = list;
            data = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                data[i] = "1";
            }
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final  SettingListActivity.Adapter.ViewHolder vh;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.setting_list_sub, null);
                vh = new SettingListActivity.Adapter.ViewHolder();
                vh.textview = (TextView) convertView.findViewById(R.id.settinglist_text);
                vh.edittext = (EditText) convertView.findViewById(R.id.settinglist_edittext);
                convertView.setTag(vh);
            } else {
                vh = ( SettingListActivity.Adapter.ViewHolder) convertView.getTag();
            }


            final HashMap<String, String> map = list.get(position);

            vh.textview.setText(map.get("variable_name"));
            vh.edittext.setHint("请输入实验数据");
            //把Bean与输入框进行绑定
            vh.edittext.setTag(position);


            //清除焦点
            vh.edittext.clearFocus();


            vh.edittext.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    //获得Edittext所在position里面的Bean，并设置数据
                    int position = (int) vh.edittext.getTag();
                    data[position] = s.toString();
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });

            //大部分情况下，Adapter里面有if必须有else
            if (!TextUtils.isEmpty(data[position] + "")) {
                vh.edittext.setText(data[position] + "");
            } else {
                vh.edittext.setText(" ");
            }

            return convertView;
        }

        class ViewHolder {
            private EditText edittext;
            private TextView textview;
        }

        public String[] getdata() {
            return data;
        }

    }

    public class SettingButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String[] data = listAdapter.getdata();
            values = new int[num];
            try {
                for (int i = 0; i < num; i++) {
                    values[i] = Integer.parseInt(data[i]);
                }
                Intent intent = new Intent();
                intent.putExtra("NAMES", names);
                intent.putExtra("DATA", values);
                intent.putExtra("SIZE", num);
                intent.putExtra("SELECTION", selection);
                intent.setClass(SettingListActivity.this, get_data.class);
                startActivity(intent);

            } catch (NumberFormatException e) {
                Toast.makeText(SettingListActivity.this, "请完成数据输入", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }


    }






    private String[] names1={"砝码质量","圆盘质量","圆环质量","重力加速度","圆环内径","圆环外径","塔轮直径",
            "圆盘直径","时间t(无样品无砝码)","时间t(无样品有砝码时)","时间t(有圆盘无砝码)","时间t(有圆盘有砝码)",
            "时间t(有圆环无砝码)","时间t(有圆环有砝码)"};
    private String[] names2={"螺线管长度L","励磁电流I1(850V倒转前)","励磁电流I1(850V倒转后)","励磁电流I2(850V倒转前)","励磁电流I2(850V倒转后)",
            "励磁电流I3(850V倒转前)","励磁电流I3(850V倒转后)","励磁电流I1(950V倒转前)","励磁电流I1(950V倒转后)","励磁电流I2(950V倒转前)",
            "励磁电流I2(950V倒转后)","励磁电流I3(950V倒转前)","励磁电流I3(950V倒转后)","励磁电流I(850V,pi/4)","励磁电流I(950V,pi/4)",
            "励磁电流I(850V,pi/2)","励磁电流I(950V,pi/2)","励磁电流I(850V,pi)","励磁电流I(950V,pi)","螺线管外径D_ex","螺线管内径D_in","第一聚焦点到光屏的距离l","X偏转板的中间位置到光屏的距离l_mid",
            "螺线管匝数"};
    private String[] names3={"物屏位置(自准法)","成像位置1(自准法)","成像位置2(自准法)","物屏位置(共轭法)","像屏位置(共轭法)",
            "大像位置(共轭法)","小像位置(共轭法)","物屏位置","像屏位置","针尖位置(视差法)","凹透镜位置(物距像距法)","凸透镜成像位置(物距像距法)",
            "凹透镜成像位置1(物距像距法)","凹透镜成像位置2(物距像距法)"};
    private String[] names4={"反射法φ1","反射法φ2","反射法φ'1","反射法φ'2","自准法θ1","自准法θ2","自准法θ'1","自准法 θ'2"," 游标方位角读数 θ","游标方位角读数 θ'","游标方位角读数 θ0","游标方位角读数 θ'0"};
}

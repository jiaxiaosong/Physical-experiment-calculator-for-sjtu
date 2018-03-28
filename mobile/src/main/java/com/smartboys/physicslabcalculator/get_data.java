package com.smartboys.physicslabcalculator;



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

import static android.R.attr.name;
import static android.R.attr.searchSuggestIntentData;
import static android.R.attr.windowClipToOutline;

/**
 * Created by 89364 on 2017/5/14.
 */


public class get_data extends ListActivity {
    private HashMap<Integer, Integer> numMap = new HashMap<Integer, Integer>();
    private HashMap<Integer, String[]> nameMap = new HashMap<Integer, String[]>();
    private ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
    private Adapter listAdapter;
    String[] result_name;
    private Button button;
    double[] results;
    int[] result_num = {38, 4, 4, 15};
    int result_id;
    int[] datas;
    int size;
    int total;
    int result_number;
    private String[] resname0 = {"常数K ", " pi/4常数C ", " pi/2常数C ", " pi常数C ", "平均励磁电流(850V) ", "平均励磁电流(850V) ", "平均励磁电流(850V) ", "平均励磁电流(850V) ", "平均励磁电流(850V) ", "850V平均励磁电流(第一次聚焦) ", "850V平均励磁电流(第二次聚焦)  ", "850V平均励磁电流(第三次聚焦)  ", "励磁电流均值（850V）", "电子荷质比 ", "平均励磁电流(950V) ", "平均励磁电流(950V) ", "平均励磁电流(950V) ", "平均励磁电流(950V) ", "平均励磁电流(950V) ", "950V平均励磁电流(第一次聚焦) ", "950V平均励磁电流(第二次聚焦)  ", "950V平均励磁电流(第三次聚焦)  ", "励磁电流均值（950V）", "电子荷质比 ", "电场偏转法平均励磁电流的大小",  "电场偏转法平均励磁电流的大小", "电场偏转法平均励磁电流的大小", "电场偏转法平均励磁电流的大小", "电场偏转法平均励磁电流的大小", "电场偏转法平均励磁电流的大小","电场偏转法测得电子荷质比","电场偏转法测得电子荷质比","电场偏转法测得电子荷质比","电场偏转法测得电子荷质比","电场偏转法测得电子荷质比","电场偏转法测得电子荷质比"};
    private String[] resname1 = {"自准法测得凸透镜焦距","共轭法测得凸透镜焦距","视差法测得凸透镜焦距","物距像距法测得凹透镜焦距"};
    private String[] resname2 = {"反射法测得三棱镜顶角角度","自准法测得三棱镜顶角角度","最小偏向角","单色光的折射率"};
    private String[] resname3 = {"无样品无砝码时时间 ", "无样品有砝码时时间", "有圆盘无砝码时时间 ", "有圆盘有砝码时时间 ", "有圆环无砝码时时间", "有圆环有砝码时时间 ", "无样品时转动惯量 ", "有圆盘时转动惯量", "圆盘转动惯量 ", "圆盘转动惯量理论值", "误差 ", "有圆环时转动惯量  ", "圆环转动惯量", "圆环转动惯量理论值 ", "误差 "};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_list_main_jia);

        button = (Button) findViewById(R.id.settinglistButton_jia);
        button.setOnClickListener(new SettingButtonListener());

        Integer id = this.getIntent().getExtras().getInt("id");

        Intent intent = getIntent();
        datas = intent.getIntArrayExtra("DATA");
        size = intent.getIntExtra("SIZE", 0);
        String[] names = intent.getStringArrayExtra("NAMES");
        result_id = intent.getIntExtra("SELECTION", 0) - 1;


        int total = 0;
        for (int i = 0; i < size; ++i)
            total += datas[i];
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String>[] maps = new HashMap[total];

        int counter = 0;
        for (int i = 0; i < size; i++)
            for (int j = 0; j < datas[i]; ++j) {
                maps[counter] = new HashMap<String, String>();
                if (j == 0)
                    maps[counter].put("variable_name", names[i]);
                else
                    maps[counter].put("varible_name", "      ");
                list.add(maps[counter]);
                counter++;
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
                data[i] = "0";
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
            final ViewHolder vh;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.setting_list_sub_jia, null);
                vh = new ViewHolder();
                vh.textview = (TextView) convertView.findViewById(R.id.settinglist_text_jia);
                vh.edittext = (EditText) convertView.findViewById(R.id.settinglist_edittext_jia);
                convertView.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
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
                vh.edittext.setText("1");
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
            String[] temp = listAdapter.getdata();
            double[][] data = new double[size][];

            for (int i = 0; i < size; ++i)
                data[i] = new double[datas[i]];

            try{
                int count = 0;
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < datas[i]; ++j) {
                        data[i][j] = Double.parseDouble(temp[count++]);
                    }
                }
            }

            catch (NumberFormatException e) {
                Toast.makeText(get_data.this, "请完成数据输入", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }


            switch (result_id) {
                case 0:
                    results = Electron_Charge_Mass_Ratio.get_result(data);
                    result_name = resname0;
                    result_number = result_num[0];
                    break;
                case 1:
                    results = Focal_Distance.get_result(data);
                    result_name = resname1;
                    result_number = result_num[1];
                    break;
                case 2:
                    results = Optical_Angel_Gauge.get_result(data);
                    result_name = resname2;
                    result_number = result_num[2];
                    break;
                case 3:
                    results = Moment_of_Inertia.get_result(data);
                    result_name = resname3;
                    result_number = result_num[3];
                    break;
            }
            Intent intent = new Intent();
            intent.putExtra("RESULTS", results);
            intent.putExtra("RESULTNAME", result_name);
            intent.putExtra("RESULTNUMBER",result_number);
            intent.setClass(get_data.this, ShowActivity.class);
            startActivity(intent);



            }
        }
    }


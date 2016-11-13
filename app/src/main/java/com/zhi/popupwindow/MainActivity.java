package com.zhi.popupwindow;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener{

    private int[] images = {R.mipmap.i1, R.mipmap.i2,R.mipmap.i3,R.mipmap.i4,
            R.mipmap.i5,R.mipmap.i6,R.mipmap.i7,R.mipmap.i8};

    private String[] titles = {"搜索", "文件管理", "下载管理", "全屏", "网址",
            "书签", "加入书签", "分享页面"};

    private Button mBtnOpenPopup;
    private PopupWindow popupWindow;
    private View view;
    private View mMainView;
    private GridView mGridView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainView = findViewById(R.id.mainView);
        mBtnOpenPopup = (Button) findViewById(R.id.btn_open_popup);
        mBtnOpenPopup.setOnClickListener(this);

        view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_popup, null);
        mGridView = (GridView) view.findViewById(R.id.gridView);
        mGridView.setAdapter(new GridViewAdapter());

        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        popupWindow.setFocusable(true);  // 可以点击pupopWindow上的条目
        popupWindow.setBackgroundDrawable(new BitmapDrawable()); // 点击非poppupWindow的部分就消失
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_open_popup:
                popupWindow.showAtLocation(mMainView, Gravity.BOTTOM, 0, 0);  // 显示在父控件的底部
                break;
        }
    }

    public class GridViewAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int position) {
            return images[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder = new Holder();
            if(null == convertView){
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_gridview, null);
                holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
                holder.textView = (TextView) convertView.findViewById(R.id.textView);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }
            holder.imageView.setImageResource(images[position]);
            holder.textView.setText(titles[position]);
            return convertView;
        }

        class Holder{
            private ImageView imageView;
            private TextView textView;
        }
    }
}
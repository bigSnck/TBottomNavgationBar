package com.yt.tbottomnavgationbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.yt.tbottomnavgationlibrary.TBottomNavigationBar;
import com.yt.tbottomnavgationlibrary.base.adapter.DefaultBottomAdapter;
import com.yt.tbottomnavgationlibrary.base.bean.DefaultDataBean;
import com.yt.tbottomnavgationlibrary.base.callback.IBottomNavSelectedCallback;
import com.yt.tbottomnavgationlibrary.base.callback.IBottomStyle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<DefaultDataBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        LinearLayout mLlBottom = findViewById(R.id.ll_bottom);
        mList = new ArrayList<>();

        mList.add(new DefaultDataBean("首页", R.mipmap.index1, R.mipmap.index, R.color.unselected_text_color, R.color.selected_text_color, true));
        mList.add(new DefaultDataBean("发现", R.mipmap.find1, R.mipmap.find, R.color.unselected_text_color, R.color.selected_text_color, false));
        mList.add(new DefaultDataBean("消息", R.mipmap.message1, R.mipmap.message, R.color.unselected_text_color, R.color.selected_text_color, false));
        mList.add(new DefaultDataBean("我的", R.mipmap.me1, R.mipmap.me, R.color.unselected_text_color, R.color.selected_text_color, false));


        TBottomNavigationBar bottomNavigationBar = new TBottomNavigationBar.Builder(this, new DefaultBottomAdapter(this, mList))
                .showBottomLine(true, getResources().getColor(R.color.colorPrimary), 2)
                .showTopLine(true, getResources().getColor(R.color.colorPrimary), 2)
                .clickItemAnimation(true)
                .setSelectedPosition(2)
                .setTabStyle(IBottomStyle.STYLE_QQ)
                .setTabClickStyle(true)
                .setSelectedCallback(new IBottomNavSelectedCallback() {
                    @Override
                    public void filterSelect(int postion, View view) {
                        Toast.makeText(MainActivity.this, "filter=" + postion, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void unFilterSelect(int postion, View view) {
                        Toast.makeText(MainActivity.this, "unFilter=" + postion, Toast.LENGTH_SHORT).show();

                    }
                }).build();

        mLlBottom.addView(bottomNavigationBar.getBottomView());
    }
}

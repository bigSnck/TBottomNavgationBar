package com.yt.tbottomnavgationlibrary.base.callback;

import android.view.View;

public interface IBottomNavSelectedCallback {
   void filterSelect(int postion, View view);//过滤重复点击

   void unFilterSelect(int postion, View view);//不过滤重复点击，比如点击一次刷新一次
}

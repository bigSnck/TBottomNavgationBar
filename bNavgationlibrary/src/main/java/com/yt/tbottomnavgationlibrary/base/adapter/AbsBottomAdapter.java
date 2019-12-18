package com.yt.tbottomnavgationlibrary.base.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.yt.tbottomnavgationlibrary.base.callback.IBottomStyle;

import java.util.List;


public abstract class AbsBottomAdapter {

    public abstract int getCount();//个数

    public abstract View getView(Context context, ViewGroup parent, int position, IBottomStyle bottomStyle, boolean isShowClickStyle, int drawableId);//view

    public abstract void updataSelectedState(int position);

    public abstract List<View> getAllView();

    public abstract void setAllView(List<View> list);


}

package com.yt.tbottomnavgationlibrary.base;

import android.content.Context;

import com.yt.tbottomnavgationlibrary.R;
import com.yt.tbottomnavgationlibrary.base.adapter.AbsBottomAdapter;
import com.yt.tbottomnavgationlibrary.base.callback.IBottomNavSelectedCallback;
import com.yt.tbottomnavgationlibrary.base.callback.IBottomStyle;

public abstract class AbsBottomNavBar {
    protected Builder mBuilder;

    public AbsBottomNavBar(Builder builder) {
        this.mBuilder = builder;
    }

    public static abstract class Builder<T extends Builder> {

        protected Context mContext;

        public AbsBottomAdapter mAdapter;
        protected boolean mIsTopLine = false;//默认不显示
        protected boolean mIsBottomLine = false;//默认不显示


        protected int mTopLineViewColor= R.color.t_bottom_selected_color;
        protected int mBottomLineViewColor=R.color.t_bottom_selected_color;

        protected int mTopLineViewHeight = 1;
        protected int mBottomTLineViewHeight = 1;

        protected boolean mIsClickAnimation = false;

        protected IBottomStyle mStyle = IBottomStyle.STYLE_Common;

        protected IBottomNavSelectedCallback mIBottomNavSelectedCallback;

        protected int mBottomClickStyleDrawable;
        protected boolean mIsBottomClickStyle;


        public Builder(Context context, AbsBottomAdapter adapter) {
            this.mContext = context;
            this.mAdapter = adapter;
        }


        public T showTopLine(boolean isShow) {
            mIsTopLine = isShow;
            return (T) this;
        }

        public T showBottomLine(boolean isShow) {

            mIsBottomLine = isShow;
            return (T) this;
        }

        public T showTopLine(boolean isShow, int topLineViewColor) {
            mIsTopLine = isShow;
            mTopLineViewColor = topLineViewColor;
            return (T) this;
        }

        public T showBottomLine(boolean isShow, int bottomLineViewColor) {

            mIsBottomLine = isShow;
            mBottomLineViewColor = bottomLineViewColor;

            return (T) this;
        }

        public T showTopLine(boolean isShow, int topLineViewColor, int topLineViewHeight) {
            mIsTopLine = isShow;
            mTopLineViewColor = topLineViewColor;
            mTopLineViewHeight = topLineViewHeight;
            return (T) this;
        }

        public T showBottomLine(boolean isShow, int bottomLineViewColor, int bottomTLineViewHeight) {

            mIsBottomLine = isShow;
            mBottomLineViewColor = bottomLineViewColor;
            mBottomTLineViewHeight = bottomTLineViewHeight;
            return (T) this;
        }

        public T clickItemAnimation(boolean b) {
            mIsClickAnimation = b;
            return (T) this;
        }


        public T setSelectedCallback(IBottomNavSelectedCallback iBottomNavSelectedCallback) {
            mIBottomNavSelectedCallback = iBottomNavSelectedCallback;
            return (T) this;
        }

        public abstract AbsBottomNavBar build();


        public T setTabStyle(IBottomStyle style) {
            mStyle = style;
            return (T) this;
        }

        public T setTabClickStyleDrawable(int drawableId) {
            mIsBottomClickStyle = true;
            mBottomClickStyleDrawable = drawableId;
            return (T) this;
        }

        public T setTabClickStyle(boolean isShow) {
            mIsBottomClickStyle = isShow;

            return (T) this;
        }
    }
}

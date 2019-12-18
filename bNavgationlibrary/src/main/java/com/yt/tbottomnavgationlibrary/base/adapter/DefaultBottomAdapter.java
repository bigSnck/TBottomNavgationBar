package com.yt.tbottomnavgationlibrary.base.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;


import com.yt.tbottomnavgationlibrary.base.TouchMoveLinearLayout;
import com.yt.tbottomnavgationlibrary.base.bean.DefaultDataBean;
import com.yt.tbottomnavgationlibrary.base.callback.IBottomStyle;

import java.util.ArrayList;
import java.util.List;

public class DefaultBottomAdapter extends AbsBottomAdapter {

    private List<DefaultDataBean> mListData;
    private Context mContext;

    private List<View> mListViews;

    public DefaultBottomAdapter(Context context, List<DefaultDataBean> list) {

        this.mListData = list;
        this.mContext = context;

        mListViews = new ArrayList<>();

    }


    @Override
    public int getCount() {
        return mListData.size();
    }

    @Override
    public View getView(Context context, ViewGroup parent, int position, IBottomStyle bottomStyle, boolean isShowClickStyle, int drawableId) {
        DefaultDataBean dataBean = mListData.get(position);

        TouchMoveLinearLayout touchMoveLinearLayout = new TouchMoveLinearLayout(mContext);
        touchMoveLinearLayout.initLayout(bottomStyle, isShowClickStyle, drawableId);
        touchMoveLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        touchMoveLinearLayout.setMoveRadius(10);

        setView(dataBean, touchMoveLinearLayout);


        return touchMoveLinearLayout;
    }

    @Override
    public void updataSelectedState(int position) {
        if (!mListViews.isEmpty() && mListData.size() != 0) {
            int count = mListData.size();

            for (int i = 0; i < count; i++) {

                if (position == i) {
                    if (!mListData.get(i).isSelected()) {
                        mListData.get(i).setSelected(true);
                        setView(mListData.get(i), (TouchMoveLinearLayout) mListViews.get(i));
                    }
                } else {
                    mListData.get(i).setSelected(false);
                    setView(mListData.get(i), (TouchMoveLinearLayout) mListViews.get(i));

                }

            }
        }
    }

    @Override
    public void setAllView(List<View> list) {
        mListViews = list;
    }

    @Override
    public List<View> getAllView() {

        return mListViews;
    }

    private void setView(DefaultDataBean dataBean, TouchMoveLinearLayout view) {
        if (dataBean.isSelected()) {
            view.setSelectContentState(dataBean.getSeletctedRes(), dataBean.getText(), dataBean.getSelectedTextColor());
        } else {
            view.setSelectContentState(dataBean.getUnSelectedRes(), dataBean.getText(), dataBean.getUnSelectedTextColor());
        }


    }


}

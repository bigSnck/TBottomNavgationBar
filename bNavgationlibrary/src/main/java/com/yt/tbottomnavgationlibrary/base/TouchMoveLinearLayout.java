package com.yt.tbottomnavgationlibrary.base;


import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;


import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.yt.tbottomnavgationlibrary.R;
import com.yt.tbottomnavgationlibrary.base.callback.IBottomStyle;
import com.yt.tbottomnavgationlibrary.base.callback.IMoveClickCallback;

public class TouchMoveLinearLayout extends LinearLayout {
    private Context mContext;
    private View mContainerView;
    private View mTouchView;
    private TextView mTextView;
    private boolean mIsFocus;

    private int mDownX;
    private int mDownY;

    private int mMoveX;
    private int mMoveY;

    private int mOraginX;
    private int mOraginY;


    private IMoveClickCallback mClickCallback;


    private boolean mIsSelectState = false;//选中状态

    private int mMoveRadius = 3;//移动的距离

    private int mDrawableId;


    public TouchMoveLinearLayout(Context context) {
        this(context, null);
    }

    public TouchMoveLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TouchMoveLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

    }


    /**
     * 目前是2中类型
     *
     * @param bottomStyle
     */
    public void initLayout(IBottomStyle bottomStyle, boolean isShowClickStyle, int drawableId) {
        if (drawableId != 0) {
            mDrawableId = drawableId;
        } else {
            mDrawableId = R.drawable.t_bottom_click_drawable;
        }
        mContainerView = LayoutInflater.from(mContext).inflate(R.layout.adapter_default_bottom_layout, this, false);

        mTouchView = mContainerView.findViewById(R.id.adapter_bottom_iv_icon);
        mTextView = mContainerView.findViewById(R.id.adapter_bottom_tv_text);

        //mTouchView.setPadding(mMoveRadius, mMoveRadius, mMoveRadius, mMoveRadius);


        setMoveRadius(mMoveRadius);

        mTouchView.post(new Runnable() {
            @Override
            public void run() {
                mOraginX = (int) mTouchView.getLeft();
                mOraginY = (int) mTouchView.getTop();
            }
        });


        if (bottomStyle == IBottomStyle.STYLE_Common) {
            if (isShowClickStyle) {
                mContainerView.setBackgroundResource(mDrawableId);
                mContainerView.setClickable(true);
            }
            setCommonStyleEvent();
        } else {
            setQQStyleEvent();
        }


        addView(mContainerView);
    }


    /**
     * 设置image可移动的半径
     *
     * @param moveRadius
     */
    public void setMoveRadius(int moveRadius) {
        mMoveRadius = moveRadius;
        if (null != mTouchView) {
            LinearLayout.LayoutParams params = (LayoutParams) mTouchView.getLayoutParams();
            params.bottomMargin = moveRadius;
            params.topMargin = moveRadius;
            params.leftMargin = moveRadius;
            params.rightMargin = moveRadius;

            mTouchView.setLayoutParams(params);

        }
    }

    /**
     * 设置通用的样式
     */
    private void setCommonStyleEvent() {

        mContainerView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mClickCallback) {
                    mClickCallback.onTextClick();//点击事件
                }


            }
        });
    }

    /**
     * 设置仿QQ样式
     */
    private void setQQStyleEvent() {

        setCommonStyleEvent();

        mTextView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        if (null != mClickCallback) {
                            mClickCallback.onTextClick();
                        }

                        break;

                }
                return true;
            }
        });

        mTouchView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.i("AA", "点击事件touch");

                        if (null != mClickCallback) {
                            mClickCallback.onViewClick();
                        }

                        mDownX = (int) event.getRawX();
                        mDownY = (int) event.getRawY();
                        mIsFocus = true;

                        break;

                    case MotionEvent.ACTION_MOVE:

                        mMoveX = (int) event.getRawX();
                        mMoveY = (int) event.getRawY();

                        int distanceX = mMoveX - mDownX;
                        int distanceY = mMoveY - mDownY;

                        moveEvent(mTouchView, distanceX, distanceY, mMoveRadius);

                        break;
                    case MotionEvent.ACTION_UP:

                        mTouchView.setX(mOraginX);
                        mTouchView.setY(mOraginY);
                        mIsFocus = false;
                        break;

                }
                return true;
            }
        });

    }


    public void setMoveClickCallback(IMoveClickCallback clickCallback) {
        mClickCallback = clickCallback;
    }

    /**
     * 设置选中状态
     *
     * @param resId //图片资源
     */
    public void setSelectContentState(int resId, String text, int textColor) {

        setViewParam(resId, text, textColor);

    }


    /**
     * 设置选中状态
     *
     * @param resId //图片资源
     */
    public void setSelectContentState(int resId, String text, int textColor, int textSize) {

        setViewParam(resId, text, textColor, textSize);

    }


    /**
     * 获取当前的选择状态
     *
     * @return
     */
    public boolean getSelectState() {

        return mIsSelectState;
    }

    private void setViewParam(int resId, String text, int textColor) {
        if (null != mTextView) {
            mTextView.setText(text);
            mTextView.setTextColor(ColorStateList.valueOf(mContext.getResources().getColor(textColor)));

        }

        if (null != mTouchView) {
            if (mTouchView instanceof ImageView) {
                ((ImageView) mTouchView).setImageResource(resId);
            }
        }
    }

    private void setViewParam(int resId, String text, int textColor, int textSize) {
        if (null != mTextView) {
            mTextView.setText(text);

            mTextView.setTextColor(ColorStateList.valueOf(mContext.getResources().getColor(textColor)));
            mTextView.setTextSize(textSize);
        }

        if (null != mTouchView) {
            if (mTouchView instanceof ImageView) {
                ((ImageView) mTouchView).setImageResource(resId);
            }
        }
    }


    /**
     * 拖动View
     *
     * @param view
     * @param deltaX
     * @param deltaY
     * @param radius
     */
    private void moveEvent(View view, float deltaX, float deltaY, float radius) {

        // 先计算拖动距离
        float distance = getDistance(deltaX, deltaY);
        // 拖动的方位角，atan2出来的角度是带正负号的
        double degree = Math.atan2(deltaY, deltaX);

        // 如果大于临界半径就不能再往外拖了
        if (distance > radius) {
            view.setX(view.getLeft() + (float) (radius * Math.cos(degree)));
            view.setY(view.getTop() + (float) (radius * Math.sin(degree)));
        } else {
            view.setX(view.getLeft() + deltaX);
            view.setY(view.getTop() + deltaY);
        }
    }

    private float getDistance(float x, float y) {
        return (float) Math.sqrt(x * x + y * y);
    }


}

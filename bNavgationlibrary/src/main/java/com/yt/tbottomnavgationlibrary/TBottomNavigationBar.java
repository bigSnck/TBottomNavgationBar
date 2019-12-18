package com.yt.tbottomnavgationlibrary;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;

import com.yt.tbottomnavgationlibrary.base.adapter.AbsBottomAdapter;
import com.yt.tbottomnavgationlibrary.base.AbsBottomNavBar;
import com.yt.tbottomnavgationlibrary.base.callback.IMoveClickCallback;
import com.yt.tbottomnavgationlibrary.base.TouchMoveLinearLayout;

import java.util.ArrayList;
import java.util.List;

public class TBottomNavigationBar extends AbsBottomNavBar {

    private Builder mBuilder;

    public TBottomNavigationBar(Builder builder) {
        super(builder);
        mBuilder = builder;
    }

    public ViewGroup getBottomView() {
        return (ViewGroup) mBuilder.mView;
    }

    public TBottomNavigationBar setSelectedPosition(int position) {

        mBuilder.mCurrentPosition = position;
        mBuilder.mAdapter.updataSelectedState(position);
        return this;
    }

    public static class Builder extends AbsBottomNavBar.Builder<Builder> {

        private View mView;

        private int mSelectedPosition;

        private int mCurrentPosition = 0;

        private List<View> mViews;

        private ScaleAnimation mStartAnimator;
        private ScaleAnimation mRestoreAnimator;
        private boolean mAnimationState = false;//false：没有开始，true:正在进行

        public Builder(Context context, AbsBottomAdapter adapter) {
            super(context, adapter);
            mViews = new ArrayList<>();
            mViews.clear();

        }

        @Override
        public TBottomNavigationBar build() {

            mView = createView();

            return new TBottomNavigationBar(this);

        }

        public Builder setSelectedPosition(int position) {
            mSelectedPosition = position;
            mCurrentPosition = position;
            return this;
        }


        private View createView() {

            LinearLayout bottomContainer = new LinearLayout(mContext);
            bottomContainer.setOrientation(LinearLayout.VERTICAL);
            bottomContainer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));


            if (mIsTopLine) {
                View mTopLineView = new View(mContext);
                mTopLineView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, mTopLineViewHeight));
                mTopLineView.setBackgroundColor(mTopLineViewColor);
                bottomContainer.addView(mTopLineView);
            }

            LinearLayout contentContainer = new LinearLayout(mContext);
            contentContainer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));


            if (null != mAdapter) {
                int count = mAdapter.getCount();

                for (int i = 0; i < count; i++) {
                    final View itemView = mAdapter.getView(mContext, contentContainer, i,mStyle,mIsBottomClickStyle,mBottomClickStyleDrawable);
                    LinearLayout.LayoutParams childViewParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    childViewParams.weight = 1;

                    itemView.setLayoutParams(childViewParams);


                    contentContainer.addView(itemView);
                    mViews.add(itemView);

                    setClick(i, itemView);

                }

                mAdapter.setAllView(mViews);
                mAdapter.updataSelectedState(mSelectedPosition);


            }


            contentContainer.setOrientation(LinearLayout.HORIZONTAL);
            bottomContainer.addView(contentContainer);

            if (mIsBottomLine) {
                View mBottomLineView = new View(mContext);
                mBottomLineView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, mTopLineViewHeight));
                mBottomLineView.setBackgroundColor(mBottomLineViewColor);

                bottomContainer.addView(mBottomLineView);
            }

            return bottomContainer;
        }

        /**
         * 设置点击事件
         * @param i
         * @param itemView
         */
        private void setClick(final int i, final View itemView) {


            if (itemView instanceof TouchMoveLinearLayout) {
                ((TouchMoveLinearLayout) itemView).setMoveClickCallback(new IMoveClickCallback() {
                    @Override
                    public void onTextClick() {
                        setClickEvent(false, i, itemView);
                    }

                    @Override
                    public void onViewClick() {
                        setClickEvent(true, i, itemView);
                    }
                });
            }

        }


        /**
         * 点击文字的时候不要缩放动画，点击图片的时候需要缩放动画
         * @param isAnim
         * @param i
         * @param itemView
         */
        private void setClickEvent(boolean isAnim, int i, View itemView) {

            if (i != mCurrentPosition) {
                mCurrentPosition = i;
                if (null != mIBottomNavSelectedCallback) {
                    mAdapter.updataSelectedState(i);

                    if (isAnim) {
                        startScaleAnim((ViewGroup) itemView);

                    }

                    mIBottomNavSelectedCallback.filterSelect(i, itemView);//设置监听事件


                }
            }

            mCurrentPosition = i;
            mIBottomNavSelectedCallback.unFilterSelect(i, itemView);//设置监听事件
        }


        private void startScaleAnim(final ViewGroup itemView) {
            try {
                ViewGroup mViewGroup = (ViewGroup) itemView.getChildAt(0);

                final View mTouchView = mViewGroup.getChildAt(0);

                if (mStartAnimator == null) {
                    mStartAnimator = new ScaleAnimation(1f, 0.9f, 1f, 0.9f, mTouchView.getWidth() / 2, mTouchView.getHeight() / 2);
                }

                mStartAnimator.setDuration(500);

                mStartAnimator.setFillAfter(true);
                mStartAnimator.setRepeatCount(0);
                if (!mAnimationState) {

                    mTouchView.startAnimation(mStartAnimator);
                    mStartAnimator.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            mAnimationState = true;

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            mAnimationState = false;
                            restoreScaleAnim(mTouchView);

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                }
            } catch (Exception e) {
                e.getCause();
            }


        }

        private void restoreScaleAnim(View view) {


            if (mRestoreAnimator == null) {
                mRestoreAnimator = new ScaleAnimation(0.9f, 1f, 0.9f, 1f, view.getWidth() / 2, view.getHeight() / 2);
            }
            mRestoreAnimator.setDuration(1000);
            mRestoreAnimator.setFillAfter(true);
            mRestoreAnimator.setRepeatCount(0);
            mRestoreAnimator.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    mAnimationState = true;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    mAnimationState = false;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            if (!mAnimationState) {
                view.startAnimation(mRestoreAnimator);
            }
        }

    }


}

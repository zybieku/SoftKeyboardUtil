package www.znq.com.myapplication.proxy;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import www.znq.com.myapplication.util.KeyBoardUtils;

/**
 * @author jiiiiiin
 * @version 1.0
 */
public class ActivityKeyBoardProxy {

    private final Activity mActivity;
    private final int[] mHideSoftByEditViewIds;
    private final View[] mFilterViewByIds;
    private final OnHideInputForceListener mOnHideInputForceListener;

    private ActivityKeyBoardProxy(@NonNull Activity activity, @NonNull int[] hideSoftByEditViewIds, View[] filterViewByIds, OnHideInputForceListener onHideInputForceListener) {
        this.mActivity = activity;
        this.mHideSoftByEditViewIds = hideSoftByEditViewIds;
        this.mFilterViewByIds = filterViewByIds;
        this.mOnHideInputForceListener = onHideInputForceListener;
    }

    public static ActivityKeyBoardProxy newInstance(@NonNull Activity activity, @NonNull int[] hideSoftByEditViewIds, View[] filterViewByIds, OnHideInputForceListener onHideInputForceListener) {
        if (filterViewByIds == null) {
            filterViewByIds = new View[]{};
        }
        return new ActivityKeyBoardProxy(activity, hideSoftByEditViewIds, filterViewByIds, onHideInputForceListener);
    }

    public interface OnHideInputForceListener {
        /**
         * 调用了隐藏系统键盘的功能
         *
         * @param motionEvent
         */
        void onHideInputForce(MotionEvent motionEvent);
    }

    public void onDispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            if (isTouchView(mFilterViewByIds, motionEvent) || mHideSoftByEditViewIds.length == 0) {
                return;
            }
            View v = mActivity.getCurrentFocus();
            if (isFocusEditText(v, mHideSoftByEditViewIds)) {
                if (isTouchView(mHideSoftByEditViewIds, motionEvent)) {
                    return;
                }
                //隐藏键盘
                KeyBoardUtils.hideInputForce(mActivity);
                clearViewFocus(v, mHideSoftByEditViewIds);
                if (mOnHideInputForceListener != null) {
                    mOnHideInputForceListener.onHideInputForce(motionEvent);
                }
            }
        }
    }


    //region软键盘的处理

    /**
     * 清除editText的焦点
     *
     * @param v   焦点所在View
     * @param ids 输入框
     */
    public void clearViewFocus(View v, int... ids) {
        if (null != v && null != ids && ids.length > 0) {
            for (int id : ids) {
                if (v.getId() == id) {
                    v.clearFocus();
                    break;
                }
            }
        }


    }

    /**
     * 隐藏键盘
     *
     * @param v   焦点所在View
     * @param ids 输入框
     * @return true代表焦点在edit上
     */
    private boolean isFocusEditText(View v, int... ids) {
        if (v instanceof EditText) {
            EditText tmp_et = (EditText) v;
            for (int id : ids) {
                if (tmp_et.getId() == id) {
                    return true;
                }
            }
        }
        return false;
    }

    //是否触摸在指定view上面,对某个控件过滤
    public boolean isTouchView(View[] views, MotionEvent motionEvent) {
        if (views == null || views.length == 0) {
            return false;
        }
        int[] location = new int[2];
        for (View view : views) {
            view.getLocationOnScreen(location);
            int x = location[0];
            int y = location[1];
            if (motionEvent.getX() > x && motionEvent.getX() < (x + view.getWidth())
                    && motionEvent.getY() > y && motionEvent.getY() < (y + view.getHeight())) {
                return true;
            }
        }
        return false;
    }

    //是否触摸在指定view上面,对某个控件过滤
    private boolean isTouchView(int[] ids, MotionEvent motionEvent) {
        int[] location = new int[2];
        for (int id : ids) {
            View view = mActivity.findViewById(id);
            if (view == null) {
                continue;
            }
            view.getLocationOnScreen(location);
            int x = location[0];
            int y = location[1];
            if (motionEvent.getX() > x && motionEvent.getX() < (x + view.getWidth())
                    && motionEvent.getY() > y && motionEvent.getY() < (y + view.getHeight())) {
                return true;
            }
        }
        return false;
    }
    //endregion
}

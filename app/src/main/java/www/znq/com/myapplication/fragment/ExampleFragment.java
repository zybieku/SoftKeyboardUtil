package www.znq.com.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import www.znq.com.myapplication.R;
import www.znq.com.myapplication.proxy.ActivityKeyBoardProxy;
import www.znq.com.myapplication.proxy.ActivityKeyBoardProxyBuild;
import www.znq.com.myapplication.proxy.FragmentOnTouchMng;

/**
 * @author jiiiiiin
 * @version 1.0
 */
public class ExampleFragment extends Fragment {

    private static final String TAG = "ExampleFragment";
    private ActivityKeyBoardProxy mActivityKeyBoardProxy;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final FragmentActivity activity = getActivity();
        if (activity instanceof FragmentOnTouchMng.ITouchMngProxy) {
            FragmentOnTouchMng.ITouchMngProxy touchMngProxy = (FragmentOnTouchMng.ITouchMngProxy) activity;
            touchMngProxy.registerOnTouchListener(new FragmentOnTouchMng.OnTouchListener() {
                @Override
                public void onTouch(MotionEvent ev) {
                    if (mActivityKeyBoardProxy != null) {
                        mActivityKeyBoardProxy.onDispatchTouchEvent(ev);
                    }
                }
            });
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.ui_activity_host_login, container, false);
        final View btnCode = rootView.findViewById(R.id.btn_code);
        try {
            mActivityKeyBoardProxy = ActivityKeyBoardProxyBuild.getInstance()
                    .withActivity(getActivity())
//                    .withFragment(this)
                    .withHideSoftByEditViewIds(new int[]{R.id.et_phone, R.id.et_check_code, R.id.et_city_code})
                    .withFilterViewByIds(new View[]{btnCode})
                    .withOnHideInputForceListener(new ActivityKeyBoardProxy.OnHideInputForceListener() {
                        @Override
                        public void onHideInputForce(MotionEvent motionEvent) {
                            Log.d(TAG, "隐藏了系统键盘");
                        }
                    })
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage());
        }
        return rootView;
    }
}

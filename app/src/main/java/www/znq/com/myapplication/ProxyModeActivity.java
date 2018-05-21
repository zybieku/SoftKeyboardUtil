package www.znq.com.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import www.znq.com.myapplication.proxy.ActivityKeyBoardProxy;
import www.znq.com.myapplication.proxy.ActivityKeyBoardProxyBuild;
import www.znq.com.myapplication.util.KeyBoardUtils;

/**
 * 非侵入式控制
 * @author jiiiiiin
 */
public class ProxyModeActivity extends AppCompatActivity {

    private static final String TAG = "ProxyModeActivity";
    private View mBtnCode;
    private ActivityKeyBoardProxy mActivityKeyBoardProxy;

    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, ProxyModeActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_activity_host_login);
        mBtnCode = findViewById(R.id.btn_code);
        try {
            mActivityKeyBoardProxy = ActivityKeyBoardProxyBuild.getInstance()
                    .withActivity(this)
                    .withHideSoftByEditViewIds(new int[]{R.id.et_phone, R.id.et_check_code, R.id.et_city_code})
                    .withFilterViewByIds(new View[]{mBtnCode})
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
    }


    @Override
    public boolean dispatchTouchEvent(final MotionEvent ev) {
        if (mActivityKeyBoardProxy != null) {
            mActivityKeyBoardProxy.onDispatchTouchEvent(ev);
        }
        return super.dispatchTouchEvent(ev);
    }

}

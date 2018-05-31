package www.znq.com.myapplication.proxy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ContentFrameLayout;
import android.util.Log;
import android.view.MotionEvent;

import www.znq.com.myapplication.R;
import www.znq.com.myapplication.fragment.ExampleFragment;

/**
 * @author jiiiiiin
 * @version 1.0
 */
public class ProxyModeFragmentActivity extends AppCompatActivity implements FragmentOnTouchMng.ITouchMngProxy {

    private static final String TAG = "ExampleFragment";
    private FragmentOnTouchMng mFragmentOnTouchMng;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ExampleFragment fragment = new ExampleFragment();
        fragmentTransaction.replace(R.id.delegate_container, fragment);
        fragmentTransaction.commit();

        mFragmentOnTouchMng = FragmentOnTouchMng.newInstance();
    }


    @Override
    public boolean dispatchTouchEvent(final MotionEvent ev) {
        try {
            mFragmentOnTouchMng.dispatchTouchEvent(ev);
        } catch (FragmentOnTouchMng.FragmentOnTouchMngException e) {
            Log.e(TAG, "dispatchTouchEvent err:: " + e.getMessage());
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void registerOnTouchListener(@NonNull FragmentOnTouchMng.OnTouchListener listener) {
        mFragmentOnTouchMng.registerOnTouchListener(listener);
    }
}

package www.znq.com.myapplication.extendss;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import www.znq.com.myapplication.R;

/**
 * 方式一
 * 继承BaseActivity
 */
public class MainActivity extends BaseActivity {

    private EditText mPhone;
    private View mBtnCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_activity_host_login);
        mPhone = (EditText) findViewById(R.id.et_phone);
        mBtnCode = findViewById(R.id.btn_code);

    }

    @Override
    public View[] filterViewByIds() {
        View[] views = {mBtnCode};
        return views;
    }

    @Override
    public int[] hideSoftByEditViewIds() {
        int[] ids = {R.id.et_phone, R.id.et_check_code, R.id.et_city_code};
        return ids;
    }

}

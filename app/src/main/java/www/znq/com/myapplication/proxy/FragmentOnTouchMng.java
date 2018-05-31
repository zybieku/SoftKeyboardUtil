package www.znq.com.myapplication.proxy;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiiiiiin
 * @version 1.0
 */
public class FragmentOnTouchMng {

    private List<OnTouchListener> onTouchListeners;

    public void dispatchTouchEvent(MotionEvent ev) throws FragmentOnTouchMngException {
        try {
            for (OnTouchListener listener : onTouchListeners) {
                listener.onTouch(ev);
            }
        } catch (Exception e) {
            throw new FragmentOnTouchMngException(e);
        }
    }

    public interface OnTouchListener {
        void onTouch(MotionEvent ev);
    }

    public interface ITouchMngProxy {
        void registerOnTouchListener(@NonNull OnTouchListener listener);
    }

    private FragmentOnTouchMng() {
        this.onTouchListeners = new ArrayList<>();
    }

    public static FragmentOnTouchMng newInstance() {
        FragmentOnTouchMng mng = new FragmentOnTouchMng();
        return mng;
    }

    public void registerOnTouchListener(@NonNull OnTouchListener listener) {
        onTouchListeners.add(listener);
    }

    public class FragmentOnTouchMngException extends Exception {
        public FragmentOnTouchMngException() {
        }

        public FragmentOnTouchMngException(String message) {
            super(message);
        }

        public FragmentOnTouchMngException(String message, Throwable cause) {
            super(message, cause);
        }

        public FragmentOnTouchMngException(Throwable cause) {
            super(cause);
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        public FragmentOnTouchMngException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
    }

}

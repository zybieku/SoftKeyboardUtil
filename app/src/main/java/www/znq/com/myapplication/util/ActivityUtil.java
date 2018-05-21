package www.znq.com.myapplication.util;

import android.app.Activity;
import android.support.annotation.NonNull;

/**
 * @author jiiiiiin
 * @version 1.0
 */

public class ActivityUtil {

    public static boolean activityIsLiving(Activity activity) {
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            return false;
        } else {
            return true;
        }
    }

    public interface IActivityIsLivingCanByRunCallBack {
        void doIt(@NonNull Activity activity);

        void onActivityIsNotLiving();
    }

    /**
     * https://www.jianshu.com/p/e46b843b95f4
     * @param activity
     * @param activityIsLivingCanByRunCallBack
     */
    public static void activityIsLivingCanByRun(Activity activity, @NonNull IActivityIsLivingCanByRunCallBack activityIsLivingCanByRunCallBack) {
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            activityIsLivingCanByRunCallBack.onActivityIsNotLiving();
        } else {
            activityIsLivingCanByRunCallBack.doIt(activity);
        }
    }
}

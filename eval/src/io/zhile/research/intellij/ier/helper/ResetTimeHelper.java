/* Decompiler 11ms, total 297ms, lines 32 */
package io.zhile.research.intellij.ier.helper;

import com.intellij.ide.Prefs;
import io.zhile.research.intellij.ier.common.Resetter;

public class ResetTimeHelper {
    public static final long RESET_PERIOD = 2160000000L;
    private static final String RESET_KEY;

    public static long getLastResetTime() {
        return Prefs.getLong(RESET_KEY, 0L);
    }

    public static void resetLastResetTime() {
        Prefs.putLong(RESET_KEY, System.currentTimeMillis());
        Resetter.syncPrefs();
    }

    public static boolean overResetPeriod() {
        return System.currentTimeMillis() - getLastResetTime() > 2160000000L;
    }

    public static String getLastResetTimeStr() {
        long lastResetTime = getLastResetTime();
        return lastResetTime > 0L ? DateTime.getStringFromTimestamp(lastResetTime) : "Not yet";
    }

    static {
        RESET_KEY = "Ide-Eval-Reset.reset_time." + Constants.IDE_NAME + Constants.IDE_BASELINE_VERSION;
    }
}

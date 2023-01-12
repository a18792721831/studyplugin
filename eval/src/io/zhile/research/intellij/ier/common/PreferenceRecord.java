/* Decompiler 5ms, total 290ms, lines 55 */
package io.zhile.research.intellij.ier.common;

import com.intellij.ide.Prefs;
import java.util.prefs.Preferences;

public class PreferenceRecord implements EvalRecord {
    private static final String DEFAULT_VALUE = null;
    private final String type;
    private final String key;
    private final String value;
    private final boolean isRaw;
    private final KeepCondition keepCondition;

    public PreferenceRecord(String key) {
        this(key, false, (KeepCondition)null);
    }

    public PreferenceRecord(String key, boolean isRaw) {
        this(key, isRaw, (KeepCondition)null);
    }

    public PreferenceRecord(String key, boolean isRaw, KeepCondition keepCondition) {
        this.type = "PREFERENCE";
        this.key = key;
        this.isRaw = isRaw;
        this.keepCondition = keepCondition;
        this.value = isRaw ? Preferences.userRoot().get(key, DEFAULT_VALUE) : Prefs.get(key, DEFAULT_VALUE);
    }

    public String getKey() {
        return this.key;
    }

    public String getValue() {
        return this.value;
    }

    public void reset() throws Exception {
        if (null == this.keepCondition || !this.keepCondition.needKeep()) {
            if (this.isRaw) {
                Preferences.userRoot().remove(this.key);
            } else {
                Prefs.remove(this.key);
            }

            Resetter.syncPrefs();
        }
    }

    public String toString() {
        String v = null == this.value ? "" : this.value;
        return "PREFERENCE: " + this.key + " = " + v.substring(0, Math.min(36, v.length()));
    }
}

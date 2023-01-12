/* Decompiler 4ms, total 312ms, lines 39 */
package io.zhile.research.intellij.ier.plugins;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import io.zhile.research.intellij.ier.common.PluginRecord;
import io.zhile.research.intellij.ier.helper.ReflectionHelper;
import java.lang.reflect.Method;

public final class MyBatisCodeHelper extends PluginRecord {
    private static final String PLUGIN_NAME = "MyBatisCodeHelperPro (Marketplace Edition)";

    public void reset() throws Exception {
        PersistentStateComponent component = (PersistentStateComponent)ApplicationManager.getApplication().getComponent("MyBatisCodeHelper");
        if (null != component) {
            Object state = component.getState();
            if (null != state) {
                Method method = ReflectionHelper.getMethod(state.getClass(), "getProfile", new Class[0]);
                if (null != method) {
                    Object profile = method.invoke(state);
                    method = ReflectionHelper.getMethod(profile.getClass(), "setValid", new Class[]{Boolean.TYPE});
                    if (null != method) {
                        method.invoke(profile, true);
                    }

                    method = ReflectionHelper.getMethod(profile.getClass(), "setTheUsageCount", new Class[]{String.class});
                    if (null != method) {
                        method.invoke(profile, "-1");
                    }

                }
            }
        }
    }

    public String getName() {
        return "MyBatisCodeHelperPro (Marketplace Edition)";
    }
}

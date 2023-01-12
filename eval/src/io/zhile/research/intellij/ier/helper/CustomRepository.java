/* Decompiler 4ms, total 310ms, lines 47 */
package io.zhile.research.intellij.ier.helper;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.updateSettings.impl.UpdateSettings;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class CustomRepository {
    public static final String DEFAULT_HOST = "https://plugins.zhile.io";

    public static void checkAndAdd(@NotNull String host) {
        if (host == null) {
            $$$reportNull$$$0(0);
        }

        List<String> hosts = UpdateSettings.getInstance().getStoredPluginHosts();
        Iterator var2 = hosts.iterator();

        String s;
        do {
            if (!var2.hasNext()) {
                hosts.add(host);
                Method method = ReflectionHelper.getMethod(UpdateSettings.class, "setThirdPartyPluginsAllowed", new Class[]{Boolean.TYPE});
                if (method != null) {
                    try {
                        method.invoke(UpdateSettings.getInstance(), true);
                    } catch (Exception var4) {
                        NotificationHelper.showError((Project)null, "Enable third party plugins failed!");
                    }
                }

                return;
            }

            s = (String)var2.next();
        } while(!s.equalsIgnoreCase(host));

    }

    // $FF: synthetic method
    private static void $$$reportNull$$$0(int var0) {
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "host", "io/zhile/research/intellij/ier/helper/CustomRepository", "checkAndAdd"));
    }
}

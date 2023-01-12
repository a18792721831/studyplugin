/* Decompiler 10ms, total 304ms, lines 30 */
package io.zhile.research.intellij.ier.helper;

import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.openapi.extensions.PluginId;

public class PluginHelper {
    public static PluginId getPluginId() {
        return PluginId.getId("io.zhile.research.ide-eval-resetter");
    }

    public static IdeaPluginDescriptor getPluginDescriptor() {
        return PluginManager.getPlugin(getPluginId());
    }

    public static String getPluginName() {
        IdeaPluginDescriptor pluginDescriptor = getPluginDescriptor();
        return pluginDescriptor == null ? "UNKNOWN" : pluginDescriptor.getName();
    }

    public static String getPluginVersion() {
        IdeaPluginDescriptor pluginDescriptor = getPluginDescriptor();
        return pluginDescriptor == null ? "UNKNOWN" : pluginDescriptor.getVersion();
    }

    public static boolean myself(IdeaPluginDescriptor pluginDescriptor) {
        return "io.zhile.research.ide-eval-resetter".equals(pluginDescriptor.getPluginId().getIdString());
    }
}

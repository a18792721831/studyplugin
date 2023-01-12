/* Decompiler 8ms, total 313ms, lines 65 */
package io.zhile.research.intellij.ier.listener;

import com.intellij.ide.plugins.DynamicPluginListener;
import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.project.Project;
import io.zhile.research.intellij.ier.common.Resetter;
import io.zhile.research.intellij.ier.helper.NotificationHelper;
import io.zhile.research.intellij.ier.helper.PluginHelper;
import io.zhile.research.intellij.ier.helper.ResetTimeHelper;
import io.zhile.research.intellij.ier.tw.MainToolWindowFactory;
import org.jetbrains.annotations.NotNull;

public class PluginListener implements DynamicPluginListener {
    public void pluginLoaded(@NotNull IdeaPluginDescriptor descriptor) {
        if (descriptor == null) {
            $$$reportNull$$$0(0);
        }

        if (PluginHelper.myself(descriptor)) {
            ActionManager.getInstance().getAction("io.zhile.research.intellij.ier.action.ResetAction");
            String link = "https://zhile.io/2020/11/18/jetbrains-eval-reset-da33a93d.html";
            String versionTip = "Plugin version: <b>v" + descriptor.getVersion() + "</b>";
            String autoResetTip = "Auto reset option state: " + (Resetter.isAutoReset() ? "<b>on</b>" : "<b>off</b>");
            String autoLogoutTip = "Auto logout option state: " + (Resetter.isAutoLogout() ? "<b>on</b>" : "<b>off</b>");
            String lastResetTime = "Last reset time: <b>" + ResetTimeHelper.getLastResetTimeStr() + "</b>";
            String content = String.format("Plugin installed successfully!<br>For more information, <a href='%s'>visit here</a>.<br><br>%s<br>%s<br>%s<br>%s", link, versionTip, autoResetTip, autoLogoutTip, lastResetTime);
            NotificationHelper.showInfo((Project)null, content);
        }
    }

    public void beforePluginUnload(@NotNull IdeaPluginDescriptor descriptor, boolean isUpdate) {
        if (descriptor == null) {
            $$$reportNull$$$0(1);
        }

        if (PluginHelper.myself(descriptor)) {
            AnAction optionsGroup = ActionManager.getInstance().getAction("WelcomeScreen.Options");
            if (optionsGroup instanceof DefaultActionGroup) {
                ((DefaultActionGroup)optionsGroup).remove(ActionManager.getInstance().getAction("io.zhile.research.intellij.ier.action.ResetAction"));
            }

            ListenerConnector.dispose();
            MainToolWindowFactory.unregisterAll();
        }
    }

    // $FF: synthetic method
    private static void $$$reportNull$$$0(int var0) {
        Object[] var10001 = new Object[]{"descriptor", "io/zhile/research/intellij/ier/listener/PluginListener", null};
        switch(var0) {
            case 0:
            default:
                var10001[2] = "pluginLoaded";
                break;
            case 1:
                var10001[2] = "beforePluginUnload";
        }

        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", var10001));
    }
}

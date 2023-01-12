/* Decompiler 7ms, total 297ms, lines 46 */
package io.zhile.research.intellij.ier.listener;

import com.intellij.ide.AppLifecycleListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Ref;
import io.zhile.research.intellij.ier.common.Resetter;
import io.zhile.research.intellij.ier.helper.BrokenPlugins;
import io.zhile.research.intellij.ier.helper.ResetTimeHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AppEventListener implements AppLifecycleListener {
    public void appFrameCreated(String[] commandLineArgs, @NotNull Ref<Boolean> willOpenProject) {
        if (willOpenProject == null) {
            $$$reportNull$$$0(0);
        }

    }

    public void appStarting(@Nullable Project projectFromCommandLine) {
    }

    public void projectFrameClosed() {
    }

    public void projectOpenFailed() {
    }

    public void welcomeScreenDisplayed() {
    }

    public void appClosing() {
        BrokenPlugins.fix();
        Resetter.touchLicenses();
        if (Resetter.isAutoReset()) {
            Resetter.reset(Resetter.getEvalRecords());
            ResetTimeHelper.resetLastResetTime();
        }
    }

    // $FF: synthetic method
    private static void $$$reportNull$$$0(int var0) {
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "willOpenProject", "io/zhile/research/intellij/ier/listener/AppEventListener", "appFrameCreated"));
    }
}

/* Decompiler 3ms, total 332ms, lines 27 */
package io.zhile.research.intellij.ier.listener;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.PreloadingActivity;
import com.intellij.openapi.progress.ProgressIndicator;
import org.jetbrains.annotations.NotNull;

public class AppInitActivity extends PreloadingActivity {
    public void preload(@NotNull ProgressIndicator indicator) {
        if (indicator == null) {
            $$$reportNull$$$0(0);
        }

        ApplicationManager.getApplication().executeOnPooledThread(new Runnable() {
            public void run() {
                ActionManager.getInstance().getAction("io.zhile.research.intellij.ier.action.ResetAction");
            }
        });
    }

    // $FF: synthetic method
    private static void $$$reportNull$$$0(int var0) {
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indicator", "io/zhile/research/intellij/ier/listener/AppInitActivity", "preload"));
    }
}

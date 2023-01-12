/* Decompiler 12ms, total 328ms, lines 72 */
package io.zhile.research.intellij.ier.listener;

import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.PluginInstaller;
import com.intellij.ide.plugins.PluginStateListener;
import com.intellij.openapi.application.ApplicationManager;
import io.zhile.research.intellij.ier.common.Resetter;
import io.zhile.research.intellij.ier.helper.ReflectionHelper;
import java.lang.reflect.Method;
import org.jetbrains.annotations.NotNull;

public class PluginInstallListener implements PluginStateListener {
    private static final PluginStateListener stateListener = new PluginInstallListener();

    private static void reflectionCall(String methodName) throws Exception {
        Method[] methods = new Method[]{ReflectionHelper.getMethod(PluginInstaller.class, methodName, new Class[]{PluginStateListener.class}), ReflectionHelper.getMethod("com.intellij.ide.plugins.PluginStateManager", methodName, new Class[]{PluginStateListener.class})};
        Method[] var2 = methods;
        int var3 = methods.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Method method = var2[var4];
            if (null != method) {
                method.invoke((Object)null, stateListener);
                return;
            }
        }

    }

    public static void setup() throws Exception {
        reflectionCall("addStateListener");
    }

    public static void remove() throws Exception {
        reflectionCall("removeStateListener");
    }

    public void install(@NotNull final IdeaPluginDescriptor descriptor) {
        if (descriptor == null) {
            $$$reportNull$$$0(0);
        }

        ApplicationManager.getApplication().invokeLater(new Runnable() {
            public void run() {
                Resetter.addPluginLicense(descriptor);
            }
        });
    }

    public void uninstall(@NotNull IdeaPluginDescriptor descriptor) {
        if (descriptor == null) {
            $$$reportNull$$$0(1);
        }

    }

    // $FF: synthetic method
    private static void $$$reportNull$$$0(int var0) {
        Object[] var10001 = new Object[]{"descriptor", "io/zhile/research/intellij/ier/listener/PluginInstallListener", null};
        switch(var0) {
            case 0:
            default:
                var10001[2] = "install";
                break;
            case 1:
                var10001[2] = "uninstall";
        }

        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", var10001));
    }
}
